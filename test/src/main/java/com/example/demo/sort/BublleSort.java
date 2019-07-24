package com.example.demo.sort;

/**
 * 冒泡
 *
 * @author hfj
 * @date 2019/2/13
 */
public class BublleSort {

    public static void main(String[] args) {
        int[] arr = {2, 7,3, 8,4, 5,6, 0,1};
        int count=0;
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {

            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
                count++;
            }
        }
        System.out.println(count);
        System.out.println("排序后的数组为：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
