package Algorithms.S2P1;

public class MergeBU extends Merge{

    public static void sort(Comparable[] a)
    {
        int N = a.length;
        Comparable[] aux = new Comparable[N];

        for (int sz = 1; sz < N; sz = sz + sz) // sz子数组大小
            for (int lo = 0; lo < N - sz; lo += sz + sz) // lo:子数组索引
                merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
    }
}
