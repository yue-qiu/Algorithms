package Algorithms.S1P5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class UnionFind {
    private int[] id;
    private int count; // 分类数

    public UnionFind(int N){
        // 初始化分量id数组
        count = N;
        id = new int[N];
        for (int i = 0; i<N; i++){
            id[i] = i;
        }
    }
// ------------------------------------------------
    // quick-find，union()操作访问数组的次数在（N+3）到（2N+1）次
    public void union(int p, int q){
        int pID = find(p);
        int qID = find(q);

        // 如果p和q在一个分量中不需要采取任何行动
        if (pID == qID) return;

        for (int i = 0; i<id.length; i++){
            if (id[i] == pID){
                id[i] = qID;
            }
            // 每修改一次 id 值, 将类型数减 1
            count--;
        }
    }

    public int find(int p){
        return id[p];
    }

// -------------------------------------------------
    // quick-union
    public void quickUnion(int p, int q){
        // 将p与q的根节点统一
        int pRoot = qfind(p);
        int qRoot = qfind(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }

    private int qfind(int p){
        // 找出分量名称
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
        StdOut.println(unionFind.count() + "完成！");
    }
}
