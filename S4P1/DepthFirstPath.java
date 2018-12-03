package Algorithms.S4P1;


import edu.princeton.cs.algs4.Stack;

/**
 * 深度优先寻找路径: 一条道走到黑,直到尽头才回来换路走 （深度）
 *
 * 由于 Graph 用了背包（先进后出）来保存连接顶点，所以 dfs 会优先处理最晚加入的顶点
 *
 * 原理类似于迷宫策略：
 * 走迷宫时常常会牵着一条绳子，如果发现这个路口已经来过，回退到上一个路口重新选择，
 * 如果这个路口所有道路都走过了，再回退到上一个路口
 *
 * 结果与图的表示有关，而不仅是与结构或算法有关
 *
 * 结果不一定是最短路径
 *
 */
public class DepthFirstPath {
    private boolean[] marked; // 在这个顶点上调用过 dfs 了吗?
    private int[] edgeTo; // 从起点到一个顶点的已知路径上的最后一个顶点
    private final int s; // 起点


    public DepthFirstPath(Graph G, int s)
    {
        marked = new boolean[G.getV()];
        edgeTo = new int[G.getV()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v)
    {
        marked[v] = true;
        for (int w: G.adj(v))
            if (!marked[w])
            {
                edgeTo[w] = v;
                dfs(G, w);
            }
    }

    public boolean marked(int w)
    {
        return marked[w];
    }

    public boolean hasPathTo(int v)
    {
        return marked(v);
    }

    public Iterable<Integer> pathTo(int v)
    {
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<>();

        // 终点回溯到起点
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);

        return path;
    }
}



