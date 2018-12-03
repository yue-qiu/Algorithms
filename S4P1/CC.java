package Algorithms.S4P1;

/**
 * 找出图中所有连接分量。CC （Connected components）
 */
public class CC {
    private boolean[] marked;
    private int[] id; // 以顶点为索引，将同一联通分量中的顶点与联通分量标识符联系起来
    private int count; // 联通分量数

    public CC(Graph G)
    {
        marked = new boolean[G.getV()];
        id = new int[G.getV()];
        for (int i = 0; i < G.getV(); i++)
            if (!marked[i])
            {
                dfs(G, i);
                count++;
            }
    }

    private void dfs(Graph G, int v)
    {
        marked[v] = true;
        id[v] = count;
        for (int w: G.adj(v))
            if (!marked[w])
                dfs(G, w);
    }

    public boolean connected(int v, int w)
    {
        return id[v] == id[w];
    }

    public int id(int v)
    {
        return id[v];
    }

    public int count()
    {
        return count;
    }
}
