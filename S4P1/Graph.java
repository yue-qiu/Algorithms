package Algorithms.S4P1;

import edu.princeton.cs.algs4.Bag;

/**
 * 邻接表的方式表示图结构。以顶点为数组索引，保存其相邻顶点
 * 如果不能允许有平行边，可用 set 代替 bag
 * 如果要求实现添加顶点，可用符号表代替数组
 */
public class Graph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Graph(int V)
    {
        this.V = V; // vertices, 顶点数
        this.E = 0; // edges， 边数
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    public int getV()
    {
        return this.V;
    }

    public int getE()
    {
        return this.E;
    }

    public void addEdge(int v, int w)
    {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v)
    {
        return adj[v];
    }

    public String toString()
    {
        String s = V + " 个顶点，" + E + " 条边\n";
        for (int v = 0; v < V; v++)
        {
            s += v + "：";
            for (int w: this.adj(v))
                s += w + ", ";
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        System.out.println(graph.toString());
    }
}
