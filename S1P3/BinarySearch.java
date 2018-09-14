package Algorithms.S1P3;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BinarySearch {
    /**
     * 二分查找，对一个已经排序的数组，找出key的index
     * @param key 查找目标
     * @param arr 已排序数组
     * @return key的对应index
     */
    public static int rank(int key, int[] arr)
    {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end)
        {
            int mid = start + (end - start) / 2;
            if (key < arr[mid]) end = mid - 1;
            else if (key > arr[mid]) start = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args)
    {
        int[] arr = {3, 2345, 124, 65765, 3453};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int index = rank(3453, arr);
        if (index != -1)
        {
            System.out.println("\n" + index);
        }
    }
}
