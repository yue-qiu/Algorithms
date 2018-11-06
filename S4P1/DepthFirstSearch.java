package Algorithms.S4P1;


import edu.princeton.cs.algs4.Stack;

/**
 * ���������Ѱ: һ�����ߵ���,ֱ����ͷ�Ż�����·�� ����ȣ�
 *
 * ���� Graph ���˱������Ƚ���������������Ӷ��㣬���� dfs �����ȴ����������Ķ���
 *
 * ԭ���������Թ����ԣ�
 * ���Թ�ʱ������ǣ��һ�����ӣ�����������·���Ѿ����������˵���һ��·������ѡ��
 * ������·�����е�·���߹��ˣ��ٻ��˵���һ��·��
 *
 * �����ͼ�ı�ʾ�йأ�����������ṹ���㷨�й�
 *
 */
public class DepthFirstSearch {
    private boolean[] marked; // ����������ϵ��ù� dfs ����?
    private int[] edgeTo; // ����㵽һ���������֪·���ϵ����һ������
    private final int s; // ���


    public DepthFirstSearch(Graph G, int s)
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

        // �յ���ݵ����
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);

        return path;
    }
}

class TestDFS
{
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 2);
        graph.addEdge(0, 1);
        graph.addEdge(0, 5);
        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 5);
        graph.addEdge(3, 4);


        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph, 0);
        System.out.println(depthFirstSearch.hasPathTo(5));
        for (Integer i: depthFirstSearch.pathTo(5))
            System.out.print(i + " ");
    }
}


