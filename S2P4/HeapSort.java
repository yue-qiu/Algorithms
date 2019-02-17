package Algorithms.S2P4;

public class HeapSort {
    public static void sort(Comparable[] pq)
    {
        int N = pq.length;
        // floyd 法建堆，O(n) 复杂度
        for (int i = N / 2; i >= 1; i--)
            sink(pq, i, N);

        while (N > 1)
        {
            exch(pq, 1, N--);
            sink(pq, 1, N);
        }
    }

    private static void sink(Comparable[] a, int i, int j)
    {
        while (2 * i <= j)
        {
            int k = 2 * i;
            if (k < j && less(a, k, k+1))
                k++;
            if (less(a, i, k))
            {
                exch(a, i, k);
                i = k;
            }
            else
                break;
        }
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

    // 对堆使用
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    // 对数组使用
    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    public static boolean isSorted(Comparable[] a)
    {
        // 测试数组元素是否有序
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))
                return false;
        return true;
    }
}
