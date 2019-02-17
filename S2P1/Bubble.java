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
    public static void postfixFlagSort(Comparable[] a)
    {
        int len = a.length;
        for (boolean flag = false; flag = !flag; len--)
            for (int j = 1; j < len; j++)
                if (less(a[j], a[j-1]))
                {
                    exch(a, j, j-1);
                    flag = false;
                }
    }

    /**
     * 冒泡再改进。
     * 记录每次遍历最后的交换位置，在这个位置之后的元素都是有序的，那么下次遍历只需要以这个位置为终点
     */
    public static void prefixFlagSort(Comparable[] a)
    {
        int lo = 0;
        int hi = a.length;
        while (lo < (hi = prefixFlagBubble(a, lo, hi)));
    }

    private static int prefixFlagBubble(Comparable[] a, int lo, int hi)
    {
        int last = lo; // 记录上一次遍历中进行交换的位置

        while (++lo < hi)
        {
            if (less(a[lo], a[lo-1])) // 检查每对相邻元素是否逆序
            {
                exch(a, hi, lo);
                last = lo; // 更新交换位置
            }
        }
        return last;
    }
}
