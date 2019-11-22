package com.example.demo.algorithm;

import com.alibaba.fastjson.JSON;

/**
 * 回溯
 *
 * @author hfj
 * @date 2019-11-19
 */
public class HuiSu {
    // 回溯算法实现。注意：我把输入的变量都定义成了成员变量。
    /**
     * 结果放到maxW中
     */
    private static int maxW = Integer.MIN_VALUE;
    /**
     * 物品重量
     */
    private static int[] weight = {2, 2, 4, 6, 3};
    /**
     * 物品个数
     */
    private static int n = 5;
    /**
     * 背包承受的最大重量
     */
    private static int w = 9;


    public static void addGoods(int i, int cw) { // 调用f(0, 0)
        System.out.println(i+":"+cw);
        // cw==w表示装满了，i==n表示物品都考察完了
        if (cw == w || i == n) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        // 选择不装第i个物品
        addGoods(i + 1, cw);

        if (cw + weight[i] <= w) {
            // 选择装第i个物品
            addGoods(i + 1, cw + weight[i]);
        }
    }

    public static void main(String[] args) {
        addGoods(0,0);

    }

}
