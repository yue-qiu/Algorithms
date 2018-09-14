package Algorithms.S2P1;

/**
 * 改进版插入排序，插入间隔由h递减至1使数组变为部分有序，h变为1时既是传统插入排序
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
            //将数组变为h有序
            for (int i = h; i < N; i++)
                //将a[i]插入到a[i-h].a[i-2*h].a[i-3*h]...之中
                for (int j = i; j >= h && less(a[j], a[j - h]); j-=h)
                    exch(a, j, j-h);
            h = h / 3;
        }
    }
}
