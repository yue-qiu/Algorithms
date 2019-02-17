package Algorithms.S3P2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * 二叉搜索树，极端情况下会退化成单链
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root; // BST的根结点

    // 中序遍历二叉树，得到一个递增序列
    private class Node
    {
        private Key key; //键
        private Value value; //值
        private int N; // 以该结点为根的子树中的结点总数
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

    /**
     * 找出大于等于 key 最小的结点
     * @param x 根节点
     * @param key 目标key
     * @return 大于等于 key 的最小结点
     */
    private Node ceiling(Node x, Key key)
    {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0)
            return ceiling(x.right, key); // cmp 大于 0，肯定在右子树，向右递归
        if (cmp == 0)
            return x; // 等于 0，找到目标结点
        Node t = ceiling(x.left, key); // cmp < 0，可能在左子树，如果向左递归返回 null 说明当前结点即为目标结点，否则返回左子树递归结果
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
        if (cmp > 0) // key > x.key，说明要从右子树寻找key结点。更新 rank = rank累计 + 1 + 左子结点总数
            return 1 + size(x.left) + rank(x.right, key);
        else if (cmp < 0) // key < x.key，说明要从左子树寻找key结点，此时key结点位置为 key在左子树中的位置，不需要增加 rank 值
            return rank(x.left, key);
        else
            return size(x.left); // key == x.key，x 即是key结点，递归结束，返回左子结点总数。rank = rank累计 + 左子结点总数
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
        if (t > k) // t > k，说明 k结点在x的左子树内
            return select(x.left, t);
        else if (t < k) // t < k，说明 k结点在x的右子树内，且 k在右子树的位置为 k - 1(当前节点) - t
            return select(x.right, k-t-1);
        else // t == k，
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
        // 待删除key结点有三种可能：无子结点，1个子结点，2个子结点
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else
        {
            // 对应情况1与情况2：左结点为null、右结点为null、均为null
            if (x.right == null)
                return x.left; // 右结点为null，将key的父结点指向左结点
            if (x.left == null)
                return x.right; // 左结点为null，将key的父结点指向右结点
            // 对应情况3：找出继承结点，用继承结点替换key结点
            Node t = x;
            x = min(t.right); // 找出继承结点
            x.right = deleteMin(t.right); // 继承结点覆盖删除最小值后的key右子树
            x.left = t.left; // 继承结点覆盖key左子树
        }
        // 重置计数器
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void print()
    {
        printLMR(root);
    }

    /**
     * 中序遍历, 左根右
     * @param x 根节点
     */
    private void printLMR(Node x)
    {
        if (x == null)
            return;
        printLMR(x.left); // 遍历根节点左子树
        System.out.println(x.key); // 打印根节点
        printLMR(x.right); // 遍历根节点右子树
    }

    /**
     * 非递归中序遍历
     */
    private void printLMRWithoutRecursion()
    {
        Stack<Node> nodeStack = new Stack<>();
        Node cur = root;
        // cur 为 null 且栈为空标志着遍历结束
        while (cur != null || !nodeStack.isEmpty())
        {
            if (cur != null)
            {
                nodeStack.push(cur);
                cur = cur.left;
            }
            else
            {
                cur = nodeStack.pop();
                // 在出栈时打印，实现中序遍历的效果
                System.out.println(cur.key);
                cur = cur.right;
            }
        }
    }

    /**
     * 先序遍历, 根左右
     * @param x 根节点
     */
    private void printMLR(Node x)
    {
        if (x == null)
            return;
        System.out.println(x.key);
        printMLR(x.left);
        printMLR(x.right);
    }

    /**
     * 非递归先序遍历
     */
    private void printMLRWithoutRecursion()
    {
        Stack<Node> nodeStack = new Stack<>();
        Node cur = root;
        // cur 为 null 且栈为空标志着遍历结束
        while (cur != null || !nodeStack.isEmpty() )
        {
            if (cur != null)
            {
                // 遍历过程中立即打印，实现先序遍历效果
                System.out.println(cur.key);
                nodeStack.push(cur);
                cur = cur.left;
            }
            else
            {
                cur = nodeStack.pop();
                cur = cur.right;
            }
        }
    }

    /**
     * 后序遍历, 左右根
     * @param x 根节点
     */
    private void printLRM(Node x)
    {
        if (x == null)
            return;
        printLRM(x.left);
        printLRM(x.right);
    }

    /**
     * 非递归后序遍历
     */
    private void printLRMWithoutRecursion()
    {
        Stack<Node> nodeStack = new Stack<>();
        Node cur = root;
        Node lastVisit = root; // 记录上一个已打印结点
        while (cur != null || !nodeStack.isEmpty())
        {
            nodeStack.push(cur);
            cur = cur.left;
        }

        cur = nodeStack.peek();
        // 当右结点为 null 或 右结点等于上一个已遍历结点，意味着左右都已完成遍历，可以打印当前结点
        if (cur.right == null || cur.right == lastVisit)
        {
            System.out.println(cur.key);
            nodeStack.pop();
            // lastVisit 设为当前结点，cur 设为 null 下一轮就可以访问栈顶元素
            lastVisit = cur;
            cur = null;
        }
        else
            cur = cur.right;
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
        if (cmplo < 0) // low 小于x.key，向x左子树递归遍历
            keys(x.left, queue, low, high);
        if (cmplo <= 0 && cmphi >= 0) // x.key 落在 low 与 high 之间，将x.key入队
            queue.enqueue(x.key);
        if (cmphi > 0) // high 大于x.key，向x右子树递归遍历
            keys(x.right, queue, low, high);
    }

    public void reversal()
    {
        root = reversal(root);
    }

    /**
     * 反转二叉树
     * @param x 根结点
     * @return 左右结点反转的根结点
     */
    private Node reversal(Node x)
    {
        if (x == null)
            return null;
        x.left = reversal(x.left);
        x.right = reversal(x.right);

        Node temp = x.left;
        x.left = x.right;
        x.right = temp;
        return x;
    }
}

