package Algorithms.S3P2;


public class RBBST<Key extends Comparable<Key>, Value> {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node
    {
        Key key;
        Value value;
        Node left, right;
        boolean color;
        int N;

        public Node(Key key, Value value, boolean color, int N)
        {
            this.key = key;
            this.value = value;
            this.color = color;
            this.N = N;
        }
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
            get(x.left, key);
        else if (cmp > 0)
            get(x.right, key);
        else
            return x.value;
        return null;
    }

    public int size()
    {
        return size(root);
    }

    private int size(Node x)
    {
        if (x == null)
            return 0;
        return x.N;
    }

    public void put(Key key, Value value)
    {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value)
    {
        if (x == null)
            return new Node(key, value, RED, 1);
        /*
        ����δ���ŵ������Ч�ʣ���Ϊ����ÿ��ֻ�Լ��������в���
        �������� 2-3-4 ��
        if (isRED(x.left) && isRED(x.right))
            filpColors(x);
        * */
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, value);
        else if (cmp > 0)
            x.right = put(x.right, key, value);
        else
            x.value = value;

        // �������Ľṹ���������㡢�ֽ� 4-���

        if (isRed(x.right) && !isRed(x.left)) // ����������
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) // ��������������
            x = rotateRight(x);
        // �з� 4-node������ÿ������󶼽���ʱ����� 4-node ����з֣�Ҳ�ͱ���� 2-3 ��
        if (isRed(x.left) && isRed(x.right)) // �������Ӿ�Ϊ�����ӣ���һ�� 4-node ���
            filpColors(x);

        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    private boolean isRed(Node x)
    {
        if (x == null)
            return false;
        return x.color;
    }

    /**
     * ����ת��� h �ĺ�ɫ������
     * @param h ��ɫ�����ӵĽ��
     * @return ��ɫ������ת����ߵ� h ���
     */
    private Node rotateLeft(Node h)
    {
        Node x = h.right;
        h.right = x.left;
        x.color = h.color;
        x.left = h;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h)
    {
        Node x = h.left;
        h.left = x.right;
        x.color = h.color;
        x.right = h;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void filpColors(Node h)
    {
        // �ֽ� 4- ��㣬���м��������븸���
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public void deleteMin()
    {
        root = deleteMin(root);
        root.color = BLACK;
    }

    private Node deleteMin(Node h)
    {
        if (h.left == null)
            return null;
        // ��� h.left �� h.left.left ���Ǻڣ�˵�� h.left ��һ�� 2-���
        // Ϊ�˷�ֹɾ�� 2-��㵼�º����ƽ�ⱻ�ƻ�����Ҫ������ h.left ��� 3-���� 4-���
        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balacne(h);
    }

    private Node moveRedLeft(Node h)
    {
        // �� h��h.left��h.right ��ϳ�һ�� 4-���
        filpColors(h);
        // ��� h.right��������һ�� 2-��㣬��Ȼ����ֱ���� h��h.left���
        // תΪ�� h.right��һ������� h.left ���һ�� 3-���
        if (isRed(h.right.left))
        {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            filpColors(h);
        }
        return h;
    }

    public void deleteMax()
    {
        root = deleteMax(root);
        root.color = BLACK;
    }

    private Node deleteMax(Node h)
    {
        // �������������Ӷ�����ߣ�Ϊ���ҵ����ֵ���������Ƶ��ұ�
        if (isRed(h.left))
            h = rotateRight(h);
        if (h == null)
            return null;
        // ��� h.right �� h.right.left ��Ϊ�ڣ�˵�� h.right �� 2-��㣬��Ҫ����
        // (��Ϊ������������ͨ�� h.right.left �Ƿ�Ϊ���ж� h.right �Ƿ����²������ 3-���)
        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balacne(h);
    }

    private Node moveRedRight(Node h)
    {
        // �� h��h.left��h.right ��ϳ�һ�� 4-���
        filpColors(h);
        // ��� h.left.left Ϊ�죬˵�� h.left ������һ�� 2-��㣬����ֱ���� h.right��h���
        // �� h.left ������ h.right ��� 3-���
        if (isRed(h.left.left))
        {
            h = rotateRight(h);
            filpColors(h);
        }
        return h;
    }

    public void delete(Key key)
    {
        root = delete(root, key);
        if (!isEmpty())
            root.color = BLACK;
    }

    private Node delete(Node h, Key key)
    {
        int cmp = key.compareTo(h.key);
        // key �����
        if (cmp < 0)
        {
            // ��� h.left ��һ�� 2-��㣬����
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            // �ݹ�ɾ��
            h.left = delete(h.left, key);
        }
        // key ���Ҳ�� h.key == key
        else
        {
            // ��������ת���Ҳ�
            if (isRed(h.left))
                h = rotateRight(h);
            // �����ĵײ���ȣ�ֱ��ɾ��
            if (cmp == 0 && (h.right == null))
                return null;
            // h.right ���� 2-node����Ҫ����
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            // ����������ȣ��ü̳н�㸲�� h ���������������ɾ��
            if (cmp == 0)
            {
                h.key = min(h.right);
                h.value = get(h.key);
                h.right = deleteMin(h.right);
            }
            else
                h.right = delete(h.right, key);
        }
        return balacne(h);
    }

    /**
     * �����������ӣ��ֽ� 4-���
     * @param x ���
     * @return ���㡢�� 4-���Ľڵ�
     */
    private Node  balacne(Node x)
    {
        if (isRed(x.right))
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left))
            x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right))
            filpColors(x);

        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Key min(Node h)
    {
        if (h.left == null)
            return h.key;
        return min(h.left);
    }

    private boolean isEmpty()
    {
        return root.N == 0;
    }
}
