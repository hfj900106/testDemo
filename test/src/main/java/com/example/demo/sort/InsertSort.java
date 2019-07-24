package com.example.demo.sort;

/**
 * @author hfj
 * @date 2019/2/14
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {2, 7, 3, 8, 4, 5, 6, 0, 1};
        int i, j;
        int n = arr.length;
        int target;

        //假定第一个元素被放到了正确的位置上
        //这样，仅需遍历 1 至 n-1
        for (i = 1; i < n; i++) {
            j = i;
            target = arr[i];
            while (j > 0 && target < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = target;
        }

        System.out.println("排序后的数组为：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
