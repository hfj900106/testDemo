package com.example.demo.sort;

/**
 * 鸡尾酒
 *
 * @author hfj
 * @date 2019/2/13
 */
public class CocktailSort {
    public static void main(String[] args) {
        int[] arr = {2, 7,3, 8,4, 5,6, 0,1};
        int i, temp;
        int low = 0;
        int up = arr.length - 1;


        while (up > low) {
            for (i = up; i > low; i--) {
                if (arr[i] > arr[i - 1]) {
                    temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                }
                low++;
            }

            for (i = low; i < up; i++) {
                if (arr[i + 1] > arr[i]) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
                up--;
            }
        }
        System.out.println(low+up);
    }
}
