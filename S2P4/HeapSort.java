package Algorithms.S2P4;

public class HeapSort {
    public static void sort(Comparable[] pq)
    {
        int N = pq.length;
        // floyd �����ѣ�O(n) ���Ӷ�
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

    // �Զ�ʹ��
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    // ������ʹ��
    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    public static boolean isSorted(Comparable[] a)
    {
        // ��������Ԫ���Ƿ�����
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))
                return false;
        return true;
    }
}
