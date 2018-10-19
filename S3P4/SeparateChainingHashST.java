package Algorithms.S3P4;

import Algorithms.S3P1.SequentialSearchST;

public class SeparateChainingHashST<Key, Value> {
    private int N; // 键值总对数
    private int M; // 散列表大小
    private SequentialSearchST<Key, Value>[] st; // 存放链表对象的数组

    public SeparateChainingHashST()
    {
        this(997);
    }

    @SuppressWarnings("unchecked")
    public SeparateChainingHashST(int M)
    {
        this.M = M;
        // java 不允许创建泛型数组，只能通过 类型擦除数组强转
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST();
    }

    private int hash(Key key)
    {
        // 除留余数法保证散列值都在 0 —— M-1 之间
        // hashCode 返回 32 位有符号整数，用 0x7fffffff 屏蔽符号位得到 31 位非负整数
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value value)
    {
        st[hash(key)].put(key, value);
        N++;
    }

    public Value get(Key key)
    {
        return st[hash(key)].get(key);
    }

    public void delete(Key key)
    {
        st[key.hashCode()].delete(key);
    }
}
