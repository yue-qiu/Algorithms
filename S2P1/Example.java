package Algorithms.S2P1;

import java.lang.Comparable;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public abstract class Example {
    public static void sort(Comparable[] a)
    {
        return;
    }

    protected static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    protected static void exch(Comparable[] a, int i, int j)
    {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    protected static void show(Comparable[] a) {
        for (Comparable c: a) StdOut.print(c + " ");
        StdOut.println();

    }

    public static boolean isSorted(Comparable[] a)
    {
        // 测试数组元素是否有序
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))
                return false;
        return true;
    }

    public static void main(String[] args)
    {
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
