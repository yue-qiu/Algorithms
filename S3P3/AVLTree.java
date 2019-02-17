package Algorithms.S3P3;

public class AVLTree<T extends Comparable<T>>
{
    private Node root;

    public int height()
    {
        return height(root);
    }

    /**
     *获得结点高度,如: 空节点高度 -1, 叶子高度 0
      */
    private int height(Node x)
    {
        if (x == null)
            return -1;
        return x.height;
    }

    /**
     * 递归中序遍历
     */
    public void printMLR()
    {
        printMLR(root);
    }

    
    private void printMLR(Node x)
    {
        if (x == null)
            return;

        System.out.println(x.data);
        printMLR(x.left);
        printMLR(x.right);
    }

    public T min()
    {
        if (min(root) != null)
            return min(root).data;
        return null;
    }

    private Node min(Node x)
    {
        if (x.left == null)
            return x;
        return min(x.left);
    }

    /**
     * 左左旋转. x 左子树高度比右子树大 2 且 x 左结点的左子树高于右子树时用这个方法调整二叉树
     * @param x 失衡结点
     */
    private Node singleRotateLeft(Node x)
    {
        Node temp = x.left;
        x.left = temp.right;
        temp.right = x;

        // 重新结算调整后结点的高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        temp.height = Math.max(height(temp.left), x.height) + 1;

        return temp;
    }

    /**
     * 右右旋转. 左左旋转的镜像. x 右子树高度比左子树大 2 且 x 右结点的右子树高于左子树时用这个方法调整二叉树
     * @param x 失衡结点
     */
    private Node singleRotateRight(Node x)
    {
        Node temp = x.right;
        x.right = temp.left;
        temp.left = x;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        temp.height = Math.max(height(temp.right), x.height) + 1;

        return temp;
    }

    /**
     * 左右旋转. x 的左子树高度比右子树大 2 且 x 左结点的右子树高于左子树时用这个方法调整二叉树
     * @param x 失衡结点
     */
    private Node douleRotateWithLeft(Node x)
    {
        x.left = singleRotateRight(x.left);
        return singleRotateLeft(x);
    }

    /**
     * 右左旋转. x 的右子树高度比左子树大 2 且 x 右结点的左子树高于右子树时用这个方法调整二叉树
     * @param x 失衡结点
     */
    private Node doubleRotateWithRight(Node x)
    {
        x.right = singleRotateLeft(x.right);
        return singleRotateRight(x);
    }

    /**
     * 平衡函数. put、delete 方法改变结点位置后需要向上调整二叉树里各个结点的位置
     * @param x 待调整结点
     */
    private Node balance(Node x)
    {
        // 左子树比右子树高度大 2
        if (height(x.left) - height(x.right) == 2)
        {
            // 左左旋转
            if (height(x.left.left) >= height(x.left.right))
                x = singleRotateLeft(x);
            // 左右旋转
            else
                x = douleRotateWithLeft(x);
        }
        // 右子树比左子树高度大 2
        else if (height(x.right) - height(x.left) == 2)
        {
            // 右右旋转
            if (height(x.right.right) >= height(x.right.left))
                x = singleRotateRight(x);
            // 右左旋转
            else
                x = doubleRotateWithRight(x);
        }

        // 重新计算结点高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    public void put(T data)
    {
        root = put(root, data);
    }

    private Node put(Node x, T data)
    {
        if (x == null)
            return new Node(data);

        int cmp = data.compareTo(x.data);
        if (cmp < 0)
            x.left = put(x.left, data);
        if (cmp > 0)
            x.right = put(x.right, data);

        return balance(x);
    }

    public void delete(T data)
    {
        root = delete(root, data);
    }

    /**
     * 类似于普通二叉树的 delete 方法. 不过在删除结点后需要进行平衡二叉树的操作
     */
    private Node delete(Node x, T data)
    {
        if (x == null)
            return null;

        int cmp = data.compareTo(x.data);
        if (cmp < 0)
            x.left = delete(x.left, data);
        else if (cmp > 0)
            x.right = delete(x.right, data);
        else
        {
            // 两个 if 对应只有一个子结点的情况
            if (x.right == null)
                return x.left;
            if (x.left == null)
                return x.right;
            // 对于有两个子结点的结点, 用继承结点覆盖之
            x.data = min(x.right).data;
            x.right = delete(x.right, x.data);
        }

        return balance(x);
    }

    private class Node
    {
        T data;
        int height; // 存放结点高度
        Node left, right;

        public Node(T data)
        {
            this.data = data;
        }
    }
}