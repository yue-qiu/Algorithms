package Algorithms.S2P1;

/**
 * ��������ƽ�����Ӷ�Ϊ N*N(�������й�)���ʺ�С�����벿����������
 */
public class Insertion extends Example{
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int i = 1; i < N; i++)
        {
            //��a[i]���뵽a[i-1].a[i-2].a[i-3]...֮��
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exch(a, j, j-1);
        }
    }

    public static void sortWithoutExch(Comparable[] a)
    {
        for (int i = 1; i < a.length; i++)
        {
            Comparable temp = a[i];
            int j = i;
            for (; j > 0 && less(temp, a[j-1]); j--)
                a[j] = a[j-1];
            a[j] = temp;
        }
    }

    public static void sortWithSentry(Comparable[] a)
    {
        // �ڱ�ģʽ������СԪ�ط�����λ��ȷ����j=1ʱless(a[j], a[j-1])�ض�����false
        int min = 0;
        for (int i = 0; i < a.length; i++)
            if (less(a[i], a[min]))
                min = i;
        exch(a, 0, min);

        for (int i = 1; i < a.length; i++)
            for (int j = i; less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }
}
