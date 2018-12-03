package Algorithms.S4P1;

/**
 * 判断是否为无环图，
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle = false;

    public Cycle(Graph G)
    {
        marked = new boolean[G.getV()];
        for (int i = 0; i < G.getV(); i++)
            if (!marked[i])
                dfs(G, i, i);
    }

    private void dfs(Graph G, int v, int u)
    {
        marked[v] = true;
        for (int w: G.adj(v))
        {
            if (!marked[w])
                dfs(G, w, v);
            else if (w != u)
                hasCycle = true;
        }
    }

    public boolean hasCycle()
    {
        return hasCycle;
    }
}
