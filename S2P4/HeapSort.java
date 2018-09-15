package Algorithms.S2P4;

public class HeapSort {
    public static void sort(Comparable[] pq)
    {
        int N = pq.length;
        for (int i = N / 2; i >= 1; i--)
            sink(pq, i, N);
        while (N > 1)
        {
            exch(pq, 1, N--);
            sink(pq, 1, N);
        }
    }
    private static void sink(Comparable[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1)) {
                j++;
            }
            if (!less(pq, k, j)) {
                break;
            }
            exch(pq, k, j);
            k = j;
        }
    }
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

    public static void main(String[] args)
    {
        Integer[] test = new Integer[10000000];
        for (int i = 0; i < test.length; i++)
            test[i] = (int)(Math.random() * 100);

        System.out.println("׼����ʼ��");
        long begin = System.currentTimeMillis();
        sort(test);
        long end = System.currentTimeMillis();
        System.out.printf("10���Ԫ�س�������ʱ��Ϊ��%.4f��\n", (end - begin) / (double)1000);
    }
}
