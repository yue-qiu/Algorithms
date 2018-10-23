package Algorithms.S3P4;

public class LinearProbingHashST<Key, Value> {
    private int N; // 符号表中键值对总数
    private int M = 16; // 线性探测表的大小
    private Key[] keys; // 键
    private Value[] values; // 值

    @SuppressWarnings("unchecked")
    public LinearProbingHashST()
    {
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    @SuppressWarnings("unchecked")
    public LinearProbingHashST(int M)
    {
        this.M = M;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    private int hash(Key key)
    {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int size)
    {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(size);
        for (int i = 0; i < M; i++)
            if (keys[i] != null)
                temp.put(keys[i], values[i]);

        keys = temp.keys;
        values = temp.values;
        M = temp.M;
    }

    public void put(Key key, Value value)
    {
        if (N >= M / 2)
            resize(2 * M);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M)
        {
            if (keys[i].equals(key))
            {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    public Value get(Key key)
    {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return values[i];
        return null;
    }

    public boolean contain(Key key)
    {
        if (key == null)
            return false;
        return get(key) != null;
    }

    /**
     * 不能只把键所在的位置则为 null，否则可能会导致与该键右相邻的键无法被 get 方法访问
     * 需要对相邻的键重新排列
     * @param key 目标键
     */
    public void delete(Key key)
    {
        if (!contain(key))
            return;

        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % M;
        keys[i] = null;
        values[i] = null;

        i = (i + 1) % M;
        while (keys[i] != null)
        {
            Key keyToRedo = keys[i];
            Value valueTodo = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            put(keyToRedo, valueTodo);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N == M / 8)
            resize(M / 2);
    }
}
