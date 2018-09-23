package Algorithms.S3P2;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root; // BST的根结点

    private class Node
    {
        private Key key; //键
        private Value value; //值
        private int N; // 以该结点为根的子树中的结点总数。作用类似于数组下标：结点在树中的排名就是其左结点的N值
        private Node left, right; // 左右结点

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
        // 查找key，找到则更新它的值，否则为它创建一个新的结点
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value)
    {
        // 如果key存在于以x为根结点的子树中则更新它的值
        // 否则将以key和value为键值对的新结点插入到该子树中
        if (x == null)
            return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, value); // key小于结点，更新左子结点
        else if (cmp > 0)
            x.right = put(x.right, key, value); // key大于结点，更新右子结点
        else
            x.value = value; // key等于结点，更新结点value
        x.N = size(x.left) + size(x.right) + 1; // 更新计数器
        return x;
    }

    public Key min()
    {
        return min(root).key;
    }

    private Node min(Node x)
    {
        if (x.left == null)
            return x;
        return min(x.left);
    }

    public Key max()
    {
        return max(root).key;
    }

    private Node max(Node x)
    {
        if (x.right == null)
            return x;
        return max(x.right);
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

    public int rank(Key key)
    {
        // 返回以x为根结点的子树中小于x.key的键的数量
        return rank(root, key);
    }

    private int rank(Node x, Key key)
    {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp > 0)
            return 1 + size(x.left) + rank(x.right, key);
        else if (cmp < 0)
            return rank(x.left, key);
        else
            return size(x.left);
    }
    
    public Key select(int k)
    {
        // 返回排名为k的结点（0为起点）
        return select(root, k);
    }
    
    private Key select(Node x, int k)
    {
        if (x == null)
            return null;
        int t = size(x.left);
        if (t > k)
            return select(x.left, t);
        else if (t < k)
            return select(x.right, k-t-1);
        else
            return x.key;
    }

    public void deleteMin()
    {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x)
    {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax()
    {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x)
    {
        if (x.right == null)
            return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key)
    {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key)
    {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else
        {
            if (x.right == null)
                return x.left;
            if (x.left == null)
                return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
}
