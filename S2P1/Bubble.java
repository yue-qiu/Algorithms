package Algorithms.S2P1;

import java.lang.*;

public class Bubble extends Example{
    public static void sort(Comparable[] a)
    {
        for (int i = 0; i < a.length; i++)
            for (int j = 1; j < a.length - i; j++)
            {
                if (less(a[j], a[j-1]))
                    exch(a, j, j-1);
            }
    }

    /**
     * 改进冒泡，数组大部分有序情况下能减少比较次数
     * 每趟排序设置一个flag，如果一趟排序下来发现没有发生交换则认为这个数组已经是有序的，无需继续比较了
     * @param a 待排序数组
     */
    public static void flagSort(Comparable[] a)
    {
        boolean flag = true;
        int len = a.length;
        while (flag)
        {
            flag = false;
            for (int j = 1; j < len; j++)
            {
                if (less(a[j], a[j-1]))
                {
                    exch(a, j, j-1);
                    flag = true;
                }
            }
            len--;
        }
    }
}
