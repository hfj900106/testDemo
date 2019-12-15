package test.src.main.java.com.example.demo.algorithm;

/**
 * 八皇后问题
 *
 * @author hfj
 * @date 2019\11\16 0016
 */
public class Queen8 {

    /**
     * 全局或成员变量,下标表示行,值表示queen存储在哪一列
     */
    static int[] result = new int[8];

    public static void main(String[] args) {
        for (int i = 0; i <8 ; i++) {
            cal8queens(i);
        }
        printQueens(result);
    }


    /**
     * 添加Q
     */
    public static void cal8queens(int row) {
        // 8个棋子都放置好了，打印结果
        if (row == 8) {
            printQueens(result);
            return;
        }
        // 每一行都有8中放法
        for (int column = 0; column < 8; ++column) {
            if (isOk(row, column)) {
                // 第row行的棋子放到了column列
                result[row] = column;
                // 考察下一行
                cal8queens(row + 1);
            }
        }
    }

    /**
     * row行column列放置是否合适
     */
    private static boolean isOk(int row, int column) {
        int leftup = column - 1, rightup = column + 1;
        // 逐行往上考察每一行
        for (int i = row - 1; i >= 0; --i) {
            // 第i行的column列有棋子吗？
            if (result[i] == column) {
                return false;
            }
            // 考察左上对角线：第i行leftup列有棋子吗？
            if (leftup >= 0) {
                if (result[i] == leftup) {
                    return false;
                }
            }
            // 考察右上对角线：第i行rightup列有棋子吗？
            if (rightup < 8) {
                if (result[i] == rightup) {
                    return false;
                }
            }
            --leftup;
            ++rightup;
        }
        return true;
    }

    /**
     * 打印出一个二维矩阵
     */
    private static void printQueens(int[] result) {
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
