package Algorithms.S1P5;


public class WeightedQuickUnionUF {
    private int[] id;
    private int[] sz; // （由触点索引的）各个根节点所对应的的分量大小
    private int count;

    public WeightedQuickUnionUF(int N)
    {
        count = N;
        id = new int[N];
        for (int i = 0;i < id.length; i++) id[i] = i;
        sz = new int[N];
        for (int i = 0; i < id.length; i++) sz[i] = 1;
    }

    public int count()
    {
        return count;
    }

    public boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }

    public int find(int p)
    {
        while (id[p] != p) p = id[p];
        return p;
    }

    public void union(int p, int q)
    {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        if (sz[pRoot] < sz[qRoot])
        {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        else
        {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }
}
