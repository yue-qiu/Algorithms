package Algorithms.S4P1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * 广度优先遍历: 对一个路口所有可能的路进行一次向前试探 （广度）, 第一个到达终点的那个人所走过的路即为最短路径
 *
 *对于 bfs，队列的使用让算法可以对最早加入的顶点进行计算
 *
 */
public class BreadthFirstPath {
    private boolean[] marked; // 到达该顶点的最短路径已知吗？
    private int[] edgeTo; // 到达该顶点的已知路径上的最后一个顶点
    private final int s; // 起点

    public BreadthFirstPath(Graph G, int s)
    {
        marked = new boolean[G.getV()];
        edgeTo = new int[G.getV()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s)
    {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true; // 标记起点
        queue.enqueue(s); // 将它加入队列
        while (!queue.isEmpty())
        {
            int v = queue.dequeue(); // 从队中删去下一顶点
            for (int w: G.adj(v))
            {
                if (!marked[w]) // 对于每一个未被标记的下一顶点
                {
                    edgeTo[w] = v; // 保存最短路径的最后一条边
                    marked[w] = true; // 标记它,因为最短路径已知
                    queue.enqueue(w); // 将它添加到队列中
                }
            }
        }
    }

    public boolean hasPathTo(int v)
    {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v)
    {
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);

        return path;
    }
}
