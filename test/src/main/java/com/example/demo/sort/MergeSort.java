package com.example.demo.sort;

/**
 * 归并
 *
 * @author hfj
 * @date 2019/2/15
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {12, 3, 2, 1, 4, 6, 5, 8, 7};
        int length = arr.length;
        int[] temp = new int[length];
        mergeSort(arr, 0, length - 1, temp);

        System.out.println("排序后的数组为：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void mergeSort(int[] arr, int low, int high, int[] tmp) {
        if (low < high) {
            int mid = (low + high) / 2;
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
