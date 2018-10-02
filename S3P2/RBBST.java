package Algorithms.S3P2;

import java.security.Key;

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

    public int size()
    {
        return size(root);
    }

    private int size(Node x)
    {
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
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, value);
        else if (cmp > 0)
            x.right = put(x.right, key, value);
        else
            x.value = value;

        if (isRed(x.right) && !isRed(x.left))
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left))
            x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right))
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
     * 左旋转结点 h 的红色右链接
     * @param h 红色右链接的结点
     * @return 红色链接旋转到左边的 h 结点
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
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
}
