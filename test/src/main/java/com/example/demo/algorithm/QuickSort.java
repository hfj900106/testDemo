package com.example.demo.sort;

import com.alibaba.fastjson.JSON;

/**
 * 快速排序 - 原地算法
 *
 * @author hfj
 * @date 2019-11-04
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {11, 5, 2, 6, 5, 1, 4, 3, 9};
        System.out.println("排序前：" + JSON.toJSONString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序前：" + JSON.toJSONString(arr));
    }

    private static void quickSort(int[] arr, int low, int hight) {
        if (low >= hight) {
            return;
        }
        // 获取下一个标准点
        int p = getPoint(arr, low, hight);
        // 排序左边
        quickSort(arr, low, p - 1);
        // 排序左边
        quickSort(arr, p + 1, hight);
    }

    private static int getPoint(int[] arr, int low, int hight) {
        int pointVal = arr[hight];
        int i = low;
        for (int j = i; j < hight; j++) {
            if (arr[j] < pointVal) {
                if (i == j) {
                    i++;
                } else {
                    swap(arr, i, j);
                    i++;
                }
            }
        }
        swap(arr, i, hight);
        return i;
    }

    private static void swap(int[] arr, int low, int hight) {
        int temp = arr[low];
        arr[low] = arr[hight];
        arr[hight] = temp;
    }

}
