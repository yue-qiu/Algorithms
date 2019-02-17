package Algorithms.S2P4;

import java.util.Comparator;

// ��ȫ�����
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
     * �ϸ���
     * 1.�ж�k�Ƿ���ڸ��ڵ㣬�Ƿ���Ҫ����
     * 2.����kֵ
     * 3.�ظ�1
     * @param k �ϸ��ڵ�
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
     * �³���jΪ��k�����Ľڵ�
     * 1.�ж�k�Ƿ�����ӽڵ�
     * 2.�ж��Ƿ���Ҫ����
     * 3.�ж�����2k����������2k+1������
     * 4.����kλ��
     * 5.�ظ�1
     * @param k �³��ڵ�
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
