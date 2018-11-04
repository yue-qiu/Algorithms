package Algorithms.S1P5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class UnionFind {
    private int[] id;
    private int count; // ������

    public UnionFind(int N){
        // ��ʼ������id����
        count = N;
        id = new int[N];
        for (int i = 0; i<N; i++){
            id[i] = i;
        }
    }
// ------------------------------------------------
    // quick-find��union()������������Ĵ����ڣ�N+3������2N+1����
    public void union(int p, int q){
        int pID = find(p);
        int qID = find(q);

        // ���p��q��һ�������в���Ҫ��ȡ�κ��ж�
        if (pID == qID) return;

        for (int i = 0; i<id.length; i++){
            if (id[i] == pID){
                id[i] = qID;
            }
            // ÿ�޸�һ�� id ֵ, ���������� 1
            count--;
        }
    }

    public int find(int p){
        return id[p];
    }

// -------------------------------------------------
    // quick-union
    public void quickUnion(int p, int q){
        // ��p��q�ĸ��ڵ�ͳһ
        int pRoot = qfind(p);
        int qRoot = qfind(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }

    private int qfind(int p){
        // �ҳ���������
        while (p != id[p]) p = id[p];
        return p;
    }
// -------------------------------------------------

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public int count(){
        return count;
    }

    public static void main(String[] args){
        int N = StdIn.readInt();
        UnionFind unionFind = new UnionFind(N);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (unionFind.connected(p, q)) continue;
            unionFind.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(unionFind.count() + "��ɣ�");
    }
}
