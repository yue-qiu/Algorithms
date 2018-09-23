package Algorithms.S3P2;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root; // BST�ĸ��ڵ�

    private class Node
    {
        private Key key; //��
        private Value value; //ֵ
        private int N; // �Ըýڵ�Ϊ�����ӽڵ�����
        private Node left, right; // ���ҽڵ�

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
        // ����key���ҵ����������ֵ������Ϊ������һ���µĽڵ�
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value)
    {
        // ���key��������xΪ���ڵ�����������������ֵ
        // ������key��valueΪ��ֵ�Ե��½ڵ���뵽��������
        if (x == null)
            return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, value); // keyС�ڽڵ㣬�������ӽڵ�
        else if (cmp > 0)
            x.right = put(x.right, key, value); // key���ڽڵ㣬�������ӽڵ�
        else
            x.value = value; // key���ڽڵ㣬���½ڵ�value
        x.N = size(x.left) + size(x.right) + 1; // ���¼�����
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
