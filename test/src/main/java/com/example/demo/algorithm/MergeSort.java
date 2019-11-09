package com.example.demo.sort;

import com.alibaba.fastjson.JSON;

/**
 * 归并排序
 *
 * @author hfj
 * @date 2019/2/15
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {12, 3, 2, 1, 4, 6, 5, 8, 7};
        int length = arr.length;
        int[] temp = new int[length];
        System.out.println("排序之前：" + JSON.toJSONString(arr));
        mergeSort(arr, 0, length - 1, temp);
        System.out.println("排序之后：" + JSON.toJSONString(arr));
    }

    public static void mergeSort(int[] arr, int low, int high, int[] tmp) {
        int mid = (low + high) >>1;
        if (low < high) {
            //对左边序列进行归并排序
            mergeSort(arr, low, mid, tmp);
            //对右边序列进行归并排序
            mergeSort(arr, mid + 1, high, tmp);
            //合并两个有序序列
            merge(arr, low, mid, high, tmp);
        }
    }

    public static void merge(int[] arr, int low, int mid, int high, int[] tmp) {
        int i = 0;
        int j = low, k = mid + 1;
        while (j <= mid && k <= high) {
            if (arr[j] < arr[k]) {
                tmp[i++] = arr[j++];
            } else {
                tmp[i++] = arr[k++];
            }
        }
        // 还有剩余，则将其全部拷贝进temp[]中
        while (j <= mid) {
            tmp[i++] = arr[j++];
        }

        while (k <= high) {
            tmp[i++] = arr[k++];
        }

        for (int t = 0; t < i; t++) {
            arr[low + t] = tmp[t];
        }
    }

}
