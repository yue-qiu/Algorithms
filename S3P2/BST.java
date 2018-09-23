package Algorithms.S3P2;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root; // BST的根节点

    private class Node
    {
        private Key key; //键
        private Value value; //值
        private int N; // 以该节点为根的子节点总数
        private Node left, right; // 左右节点

        public Node(Key key, Value value, int N)
        {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    public int size()
    {
        return size(root);
    }

    private int size(Node x)
    {
        if (x == null)
            return 0;
        else
            return x.N;
    }

    public Value get(Key key)
    {
        return get(root, key);
    }

    private Value get(Node x, Key key)
    {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.value;
    }

    public void put(Key key, Value value)
    {
        // 查找key，找到则更新它的值，否则为它创建一个新的节点
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value)
    {
        // 如果key存在于以x为根节点的子树中则更新它的值
        // 否则将以key和value为键值对的新节点插入到该子树中
        if (x == null)
            return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, value); // key小于节点，更新左子节点
        else if (cmp > 0)
            x.right = put(x.right, key, value); // key大于节点，更新右子节点
        else
            x.value = value; // key等于节点，更新节点value
        x.N = size(x.left) + size(x.right) + 1; // 更新计数器
        return x;
    }

    public Key min(Key key)
    {
        return min(root, key);
    }

    private Key min(Node x, Key key)
    {
        if (x.left == null)
            return x.key;
        return min(x.left, key);
    }

    public Key max(Key key)
    {
        return max(root, key);
    }

    private Key max(Node x, Key key)
    {
        if (x.right == null)
            return x.key;
        return max(x.right, key);
    }

    public Key ceiling(Key key)
    {
        Node x = ceiling(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key)
    {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0)
            return ceiling(x.right, key);
        if (cmp == 0)
            return x;
        Node t = ceiling(x.left, key);
        if (t != null)
            return t;
        else
            return x;
    }


    public Key floor(Key key)
    {
        Node x = floor(root, key);
        if (x == null)
            return null;
        return x.key;

    }

    private Node floor(Node x, Key key)
    {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp < 0)
            return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null)
            return t;
        else
            return x;
    }
}
