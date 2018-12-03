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

    /**
     * 深度优先搜索
     * @param G 图
     * @param v 起始结点
     * @param u 与 v 相邻的上一结点
     */
    private void dfs(Graph G, int v, int u)
    {
        marked[v] = true;
        for (int w: G.adj(v))
        {
            if (!marked[w])
                dfs(G, w, v);
            // 对于无环图，如果 w 已被标记，w 与 u 是相等的
            else if (w != u)
                hasCycle = true;
        }
    }

    public boolean hasCycle()
    {
        return hasCycle;
    }
}
