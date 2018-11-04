package Algorithms.S4P1;

/**
 * ���������Ѱ��
 *
 * �������Թ����ԣ�
 * ���Թ�ʱ������ǣ��һ�����ӣ�����������·���Ѿ����������˵���һ��·������ѡ��
 * ������·�����е�·���߹��ˣ��ٻ��˵���һ��·��
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
