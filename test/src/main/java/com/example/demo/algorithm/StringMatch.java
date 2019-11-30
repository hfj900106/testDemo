package com.example.demo.algorithm;

/**
 * 字符串匹配算法
 *
 * @author hfj
 * @date 2019-11-28
 */
public class StringMatch {

    /**
     * 散列表数组大小
     */
    private static final int SIZE = 256;

    /**
     * 初始化散列表bc
     *
     * @param patternStr 模式串字符数组
     * @param len        模式串的长度
     * @param ht         模式串散列数组
     */
    private void generateHashTable(char[] patternStr, int len, int[] ht) {
        for (int i = 0; i < SIZE; ++i) {
            // 初始化bc
            ht[i] = -1;
        }
        for (int i = 0; i < len; ++i) {
            // 计算b[i]的ASCII值
            int ascii = (int) patternStr[i];
            // 数组的下标对应字符的ASCII码值，数组中存储这个字符在模式串中出现的位置
            ht[ascii] = i;
        }
    }


    /**
     * BM 算法，仅用坏字符规则，并且不考虑si-xi计算得到的移动位数可能会出现负数的情况
     *
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return 主串与模式串第一个匹配的字符的位置，不存在返回-1
     */
    public int bmBad(char[] a, int n, char[] b, int m) {
        // 记录模式串中每个字符最后出现的位置
        int[] bc = new int[SIZE];
        // 构建模式串哈希表
        generateHashTable(b, m, bc);
        // i表示主串与模式串对齐的第一个字符下标，0 <= i <= (n-m)
        int i = 0;
        while (i <= n - m) {
            int j;
            // 模式串从后往前匹配
            for (j = m - 1; j >= 0; --j) {
                // 找到第一个坏字符
                if (a[i + j] != b[j]) {
                    break;
                }
            }
            if (j < 0) {
                // 匹配成功，返回主串与模式串第一个匹配的字符的位置
                return i;
            }
            // 这里等同于将模式串往后滑动j-bc[(int)a[i+j]]位
            i = i + (j - bc[(int) a[i + j]]);
        }
        return -1;
    }


    /**
     * BM 算法，坏字符规则 + 好后缀规则
     *
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return 主串与模式串第一个匹配的字符的位置，不存在返回-1
     */
    public int bmBadGood(char[] a, int n, char[] b, int m) {
        // 记录模式串中每个字符最后出现的位置
        int[] bc = new int[SIZE];
        // 构建模式串符哈希表
        generateHashTable(b, m, bc);
        // suffix数组的下标k，表示后缀子串的长度，下标对应的数组值存储的是，在模式串中跟好后缀{u}相匹配的子串{u*}的起始下标值
        int[] suffix = new int[m];
        // boolean类型的prefix数组，来记录模式串的后缀子串是否能匹配模式串的前缀子串。
        boolean[] prefix = new boolean[m];

        generateGS(b, m, suffix, prefix);
        int i = 0;
        while (i <= n - m) {
            // j表示主串与模式串匹配的第一个字符
            int j;
            // 模式串从后往前匹配
            for (j = m - 1; j >= 0; --j) {
                // 坏字符对应模式串中的下标是j
                if (a[i + j] != b[j]) {
                    break;
                }
            }
            if (j < 0) {
                // 匹配成功，返回主串与模式串第一个匹配的字符的位置
                return i;
            }
            int x = j - bc[(int) a[i + j]];
            int y = 0;
            // 如果有好后缀的话
            if (j < m - 1) {
                y = moveByGS(j, m, suffix, prefix);
            }
            i = i + Math.max(x, y);
        }
        return -1;
    }

    /**
     * j表示坏字符对应的模式串中的字符下标; m表示模式串长度
     */
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        // 好后缀长度
        int k = m - 1 - j;
        if (suffix[k] != -1) {
            return j - suffix[k] + 1;
        }
        for (int r = j + 2; r <= m - 1; ++r) {
            if (prefix[m - r] == true) {
                return r;
            }
        }
        return m;
    }

    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        // 初始化
        for (int i = 0; i < m; ++i) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        for (int i = 0; i < m - 1; ++i) {
            int j = i;
            // 公共后缀子串长度
            int k = 0;
            // 与b[0, m-1]求公共后缀子串
            while (j >= 0 && b[j] == b[m - 1 - k]) {
                --j;
                ++k;
                // j+1表示公共后缀子串在b[0, i]中的起始下标
                suffix[k] = j + 1;
            }
            // 如果公共后缀子串也是模式串的前缀子串
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }

    /**
     * KMP
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return
     */
    public static int kmp(char[] a, int n, char[] b, int m) {
       // next数组（失效函数）：下标是每个前缀结尾字符下标，数组的值是这个前缀的最长可以匹配前缀子串的结尾字符下标
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; ++i) {
            // 一直找到a[i]和b[j]
            while (j > 0 && a[i] != b[j]) {
                j = next[j - 1] + 1;
            }
            if (a[i] == b[j]) {
                ++j;
            }
            // 找到匹配模式串的了
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; ++i) {
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }
            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }

}
