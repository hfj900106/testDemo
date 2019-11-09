package com.example.demo.sort;

import com.alibaba.fastjson.JSON;

/**
 * 归并排序
 *
 * @author hfj
 * @date 2019-11-01
 */
public class MergeSort2 {
    public static void main(String[] args) {
        int[] sortBefore = {11, 5, 2, 6, 5, 1, 4, 3, 9};

        System.out.println("排序之前：" + JSON.toJSONString(sortBefore));
        sort(sortBefore);
        System.out.println("排序之后：" + JSON.toJSONString(sortBefore));
    }

    private static void sort(int[] arr) {
        int[] temp = new int[arr.length];
        sortMerge(arr, 0, arr.length - 1, temp);
    }


    private static void sortMerge(int[] arr, int low, int hight, int[] temp) {
        int mid = (low + hight) >> 1;
        if (low < hight) {
            // 排序左边
            sortMerge(arr, low, mid, temp);
            // 排序右边
            sortMerge(arr, mid + 1, hight, temp);
            // 合并
            merge(arr, low, mid, hight, temp);
        }
    }

    private static void merge(int[] arr, int low, int mid, int hight, int[] temp) {
        int i = 0;
        int l = low;
        int h = mid + 1;
        while (l <= mid && h <= hight) {
            if (arr[l] < arr[h]) {
                temp[i++] = arr[l++];
            } else {
                temp[i++] = arr[h++];
            }
        }

        // 还有剩余
        while (l <= mid) {
            temp[i++] = arr[l++];
        }

        while (h <= hight) {
            temp[i++] = arr[h++];
        }

        for (int j = 0; j < i; j++) {
            arr[low + j] = temp[j];
        }

    }
}


