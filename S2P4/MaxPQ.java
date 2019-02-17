package Algorithms.S2P4;

import java.util.Comparator;

// 完全二叉堆
public class MaxPQ<key extends Comparable<key>>{
    private key[] pq; // priority queue
    private int N = 0;
    private static Comparator comparator;
    private final static int DEFAULT_CAPACITY = 1;

    @SuppressWarnings("unchecked")
    public MaxPQ(int max)
    {
        pq = (key[]) new Comparable[max+1];
    }

    @SuppressWarnings("unchecked")
    public MaxPQ()
    {
        pq = (key[])new Comparable[DEFAULT_CAPACITY];

    }

    public MaxPQ(key[] keys)
    {
        for (key key : keys) {
            insert(key);
        }
    }

    public MaxPQ(Comparator<key> keyComparator)
    {
        comparator = keyComparator;
    }

    public void insert(key v)
    {
        if (N + 1 == pq.length)
            resize(2 * pq.length);
        pq[++N] = v;
        swim(N);
   }

    public key delMax()
    {
        key max = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        if (N + 1 == pq.length / 4)
            resize(pq.length / 2);
        return max;
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public int size()
    {
        return N;
    }

    private void resize(int size)
    {
        @SuppressWarnings("unchecked")
        key[] temp = (key[]) new Comparable[size];
        for (int i = 1; i <= N; i++)
            temp[i] = pq[i];
        pq = temp;
    }

    @SuppressWarnings("unchecked")
    private boolean less(int i, int j)
    {
        if (comparator != null)
            return comparator.compare(pq[i], pq[j]) < 0;
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j)
    {
        key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /**
     * 上浮。
     * 1.判断k是否存在父节点，是否需要交换
     * 2.更新k值
     * 3.重复1
     * @param k 上浮节点
     */
    private void swim(int k)
    {
        while (k > 1 && less(k / 2, k))
        {
            exch(k / 2, k);
            k /= 2;
        }
    }

    /**
     * 下沉。j为与k交换的节点
     * 1.判断k是否存在子节点
     * 2.判断是否需要交换
     * 3.判断是与2k交换还是与2k+1交换。
     * 4.更新k位置
     * 5.重复1
     * @param k 下沉节点
     */
    private void sink(int k)
    {
        while (2 * k <= N)
        {
            int j = 2 * k;
            if (j < N && less(j, j+1))
                j++;
            if (less(j, k))
                break;
            exch(k, j);
            k = j;
        }
    }
}
