package com.example.demo.algorithm;

/**
 * 二分法查找
 *
 * @author hfj
 * @date 2019-11-04
 */
public class BinarySelect {

    public static void main(String[] args) {
        int[] sortBefore = {1, 2, 3, 5, 6, 8, 9, 12, 55};
        System.out.println("是否存在目标值：" + existVal(sortBefore, 9));

    }

    private static int existVal(int[] arr, int target) {
        return exist(arr, 0, arr.length - 1, target);
    }

    private static int exist(int[] arr, int low, int hight, int target) {
        if (low >= hight) {
            return -1;
        }
        int mid = (low + hight) >> 1;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return exist(arr, mid + 1, hight, target);
        } else {
            return exist(arr, low, mid - 1, target);
        }
    }

}
