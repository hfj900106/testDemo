package com.example.demo.algorithm;

/**
 * Trie 树
 *
 * 假设我们的字符串中只有从a到z这26个小写字母，我们在数组中下标为0的位置，存储指向子节点a的指针，
 * 下标为1的位置存储指向子节点b的指针，以此类推，下标为25的位置，存储的是指向的子节点z的指针。
 * 如果某个字符的子节点不存在，我们就在对应的下标的位置存储null。
 *
 * @author hfj
 * @date 2019-11-28
 */
public class Trie {

    /**
     * 存储无意义字符
     */
    private TrieNode root = new TrieNode('/');

    /**
     * 往Trie树中插入一个字符串
     */
    public void insertNode(char[] inserChar) {
        TrieNode p = root;
        for (int i = 0; i < inserChar.length; ++i) {
            // 为了数组下标从0开始，相同的字母只存一次然后移到下一个节点
            int index = inserChar[i] - 'a';
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(inserChar[i]);
                p.children[index] = newNode;
            }
            // 移到下一个节点
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    /**
     * 在Trie树中查找一个字符串
     */
    public boolean isExist(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; ++i) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null) {
                // 不存在pattern
                return false;
            }
            // 移到下一个节点
            p = p.children[index];
        }
        if (p.isEndingChar) {
            // 最后一个字符是结束字符，那么找到pattern
            return true;
        } else {
            // 否则不能完全匹配，只是前缀
            return false;
        }
    }

    public class TrieNode {
        char data;
        TrieNode[] children = new TrieNode[26];
        /**
         * 是否结束字符
         */
        boolean isEndingChar = false;

        TrieNode(char data) {
            this.data = data;
        }
    }

}
