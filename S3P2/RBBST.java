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
        将这段代码放到这可以效率，因为这样每次只对几个结点进行操作
        这样就是 2-3-4 树
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

        // 调整树的结构，修正右倾、分解 4-结点

        if (isRed(x.right) && !isRed(x.left)) // 红链接在右
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) // 两个红链接相连
            x = rotateRight(x);
        // 切分 4-node，由于每次在最后都将临时构造的 4-node 结点切分，也就变成了 2-3 树
        if (isRed(x.left) && isRed(x.right)) // 左右链接均为红链接，即一个 4-node 结点
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
        // 分解 4- 结点，将中间结点变红送入父结点
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
        // 如果 h.left 与 h.left.left 都是黑，说明 h.left 是一个 2-结点
        // 为了防止删除 2-结点导致红黑树平衡被破坏，需要借结点与 h.left 组成 3-结点或 4-结点
        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balacne(h);
    }

    private Node moveRedLeft(Node h)
    {
        // 将 h、h.left、h.right 结合成一个 4-结点
        filpColors(h);
        // 如果 h.right本来不是一个 2-结点，显然不能直接与 h、h.left结合
        // 转为从 h.right借一个结点与 h.left 组成一个 3-结点
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
        // 左倾红黑树红链接都在左边，为了找到最大值将红链接移到右边
        if (isRed(h.left))
            h = rotateRight(h);
        if (h == null)
            return null;
        // 如果 h.right 和 h.right.left 均为黑，说明 h.right 是 2-结点，需要借结点
        // (因为是左倾红黑树，通过 h.right.left 是否为红判断 h.right 是否与下层结点组成 3-结点)
        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balacne(h);
    }

    private Node moveRedRight(Node h)
    {
        // 将 h、h.left、h.right 结合成一个 4-结点
        filpColors(h);
        // 如果 h.left.left 为红，说明 h.left 本身不是一个 2-结点，不能直接与 h.right、h结合
        // 从 h.left 借结点与 h.right 组成 3-结点
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
        // key 在左侧
        if (cmp < 0)
        {
            // 如果 h.left 是一个 2-结点，借结点
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            // 递归删除
            h.left = delete(h.left, key);
        }
        // key 在右侧或 h.key == key
        else
        {
            // 将红链接转到右侧
            if (isRed(h.left))
                h = rotateRight(h);
            // 在树的底部相等，直接删除
            if (cmp == 0 && (h.right == null))
                return null;
            // h.right 不是 2-node，需要借结点
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            // 不在树底相等，用继承结点覆盖 h 并将其从右子树中删除
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
     * 修正右倾链接，分解 4-结点
     * @param x 结点
     * @return 左倾、非 4-结点的节点
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
