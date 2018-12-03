package Algorithms.S4P1;

/**
 * 深度优先搜索。判断单点连通性
 */
public class DepthFirstSearch {
    private boolean[] marked;

    public DepthFirstSearch(Graph G, int s)
    {
        marked = new boolean[G.getV()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v)
    {
        marked[v] = true;
        for (int w: G.adj(v))
            if (!marked[v])
                dfs(G, w);
    }

    public boolean marked(int v)
    {
        return marked[v];
    }
}
