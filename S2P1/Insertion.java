package Algorithms.S2P1;

/**
 * 插入排序，平均复杂度 N*N(与输入有关，input-sensitive)，在最好情况下为 n
 *
 * 每次插入的比较次数可以理解为 已排序元素与待插入元素之间逆序数 + 1
 * 对每个待插入元素逆序数求和结果记为 i，插入操作求和记为 n，得插入排序整体复杂度为 i+n
 * 最好情况下，i = 0。通常情况下 i = n(n-1)/2。这是插入排序复杂度更本质的理解
 *
 * 插入排序适合小数组与部分有序数组
 */
public class Insertion extends Example{
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int i = 1; i < N; i++)
        {
            //将a[i]插入到a[i-1].a[i-2].a[i-3]...之中
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
        // 哨兵模式，将最小元素放在首位，确保当j=1时less(a[j], a[j-1])必定返回false
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
