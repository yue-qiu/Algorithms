package Algorithms.S2P1;

/**
 * �Ľ���������򣬲�������h�ݼ���1ʹ�����Ϊ��������h��Ϊ1ʱ���Ǵ�ͳ��������
 */
public class Shell extends Example{
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        int h = 1;
        while (h < N/3)
            h = 3 * h + 1;
        while (h >= 1)
        {
            //�������Ϊh����
            for (int i = h; i < N; i++)
                //��a[i]���뵽a[i-h].a[i-2*h].a[i-3*h]...֮��
                for (int j = i; j >= h && less(a[j], a[j - h]); j-=h)
                    exch(a, j, j-h);
            h = h / 3;
        }
    }
}
