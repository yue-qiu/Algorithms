package Algorithms.S3P2;

import edu.princeton.cs.algs4.Queue;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root; // BST�ĸ����

    private class Node
    {
        private Key key; //��
        private Value value; //ֵ
        private int N; // �Ըý��Ϊ���������еĽ�����������������������±꣺��������е����������������Nֵ
        private Node left, right; // ���ҽ��

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

    private Value getWithoutRecursion(Node x, Key key)
    {
        if (x == null)
            return null;
        while (true)
        {
            int cmp = key.compareTo(x.key);
            if (cmp > 0)
                x = x.right;
            else if (cmp < 0)
                x = x.left;
            else
                return x.value;
        }
    }

    public void put(Key key, Value value)
    {
        // ����key���ҵ����������ֵ������Ϊ������һ���µĽ��
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value)
    {
        // ���key��������xΪ���������������������ֵ
        // ������key��valueΪ��ֵ�Ե��½����뵽��������
        if (x == null)
            return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, value); // keyС�ڽ�㣬�������ӽ��
        else if (cmp > 0)
            x.right = put(x.right, key, value); // key���ڽ�㣬�������ӽ��
        else
            x.value = value; // key���ڽ�㣬���½��value
        x.N = size(x.left) + size(x.right) + 1; // ���¼�����
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
        // ������xΪ������������С��x.key�ļ�������
        return rank(root, key);
    }

    private int rank(Node x, Key key)
    {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) // key > x.key��˵��Ҫ��������Ѱ��key��㣬��ʱkey���λ��Ϊ ���������� + 1(��ǰ�ڵ�) + key���������е�λ��
            return 1 + size(x.left) + rank(x.right, key);
        else if (cmp < 0) // key < x.key��˵��Ҫ��������Ѱ��key��㣬��ʱkey���λ��Ϊ key���������е�λ��
            return rank(x.left, key);
        else
            return size(x.left); // key == x.key��x����key��㣬��ʱkey���λ��Ϊ x����������
    }
    
    public Key select(int k)
    {
        return select(root, k);
    }
    
    private Key select(Node x, int k)
    {
        if (x == null)
            return null;
        int t = size(x.left);
        if (t > k) // t > k��˵�� k�����x����������
            return select(x.left, t);
        else if (t < k) // t < k��˵�� k�����x���������ڣ��� k����������λ��Ϊ k - 1(��ǰ�ڵ�) - t
            return select(x.right, k-t-1);
        else // t == k��
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
        // ��ɾ��key��������ֿ��ܣ����ӽ�㣬1���ӽ�㣬2���ӽ��
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else
        {
            // ��Ӧ���1�����2������Ϊnull���ҽ��Ϊnull����Ϊnull
            if (x.right == null)
                return x.left; // �ҽ��Ϊnull����key�ĸ����ָ������
            if (x.left == null)
                return x.right; // ����Ϊnull����key�ĸ����ָ���ҽ��
            // ��Ӧ���3���ҳ��̳н�㣬�ü̳н���滻key���
            Node t = x;
            x = min(t.right); // �ҳ��̳н��
            x.right = deleteMin(t.right); // �̳н�㸲��ɾ����Сֵ���key������
            x.left = t.left; // �̳н�㸲��key������
        }
        // ���ü�����
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void print()
    {
        print(root);
    }

    private void print(Node x)
    {
        if (x == null)
            return;
        print(x.left); // �������ڵ�������
        System.out.println(x.key); // ��ӡ���ڵ�
        print(x.right); // �������ڵ�������
    }

    public Iterable<Key> keys()
    {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key low, Key high)
    {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, low, high);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key low, Key high)
    {
        if (x == null)
            return;
        int cmplo = low.compareTo(x.key);
        int cmphi = high.compareTo(x.key);
        if (cmplo < 0) // low С��x.key����x�������ݹ����
            keys(x.left, queue, low, high);
        if (cmplo <= 0 && cmphi >= 0) // x.key ���� low �� high ֮�䣬��x.key���
            queue.enqueue(x.key);
        if (cmphi > 0) // high ����x.key����x�������ݹ����
            keys(x.right, queue, low, high);
    }
}

