package Algorithms.S4P1;

/**
 * 深度优先搜寻。
 *
 * 类似于迷宫策略：
 * 走迷宫时常常会牵着一条绳子，如果发现这个路口已经来过，回退到上一个路口重新选择，
 * 如果这个路口所有道路都走过了，再回退到上一个路口
 *
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s)
    {
        marked = new boolean[G.getV()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v)
    {
        marked[v] = true;
        count++;
        for (int w: G.adj(v))
            if (!marked[w])
                dfs(G, w);
    }

    public boolean marked(int w)
    {
        return marked[w];
    }

    public int Count()
    {
        return count;
    }
}
