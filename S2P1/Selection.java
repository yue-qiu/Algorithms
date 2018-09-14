package Algorithms.S2P1;

/**
 * ѡ�����򣬸��Ӷ�Ϊ N*N���������޹أ�
 */
public class Selection extends Example {
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
        {
            // ��a[i]��a[i+1..N]�е�Ԫ�ؽ���
            int min = i;
            for (int j = i+1; j < N; j++)
                if (less(a[j], a[min])) min = j;
            exch(a, i, min);
        }
    }
}
