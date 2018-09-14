package Algorithms.S2P1;

/**
 * 选择排序，复杂度为 N*N（与输入无关）
 */
public class Selection extends Example {
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
        {
            // 将a[i]和a[i+1..N]中的元素交换
            int min = i;
            for (int j = i+1; j < N; j++)
                if (less(a[j], a[min])) min = j;
            exch(a, i, min);
        }
    }
}
