package com.example.demo.sort;

/**
 * 选择
 *
 * @author hfj
 * @date 2019/2/14
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {2, 7, 3, 8, 4, 5, 6, 0, 1};

        for (int i = 0; i < arr.length - 1; i++) {
            int k = i;
            for (int j = k + 1; j < arr.length; j++) {
                if (arr[j] < arr[k]) {
                    k = j;
                }
            }
            if (i != k) {
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
        }
        System.out.println("排序后的数组为：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

}
