package Algorithms.S4P1;


import edu.princeton.cs.algs4.Stack;

/**
 * �������Ѱ��·��: һ�����ߵ���,ֱ����ͷ�Ż�����·�� ����ȣ�
 *
 * ���� Graph ���˱������Ƚ���������������Ӷ��㣬���� dfs �����ȴ����������Ķ���
 *
 * ԭ���������Թ����ԣ�
 * ���Թ�ʱ������ǣ��һ�����ӣ�����������·���Ѿ����������˵���һ��·������ѡ��
 * ������·�����е�·���߹��ˣ��ٻ��˵���һ��·��
 *
 * �����ͼ�ı�ʾ�йأ�����������ṹ���㷨�й�
 *
 * �����һ�������·��
 *
 */
public class DepthFirstPath {
    private boolean[] marked; // ����������ϵ��ù� dfs ����?
    private int[] edgeTo; // ����㵽һ���������֪·���ϵ����һ������
    private final int s; // ���


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

        // �յ���ݵ����
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);

        return path;
    }
}



