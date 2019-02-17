package Algorithms.S1P3;

import java.util.Iterator;

public class QueueTest {
    public static void main(String[] args)
    {
        MyQueue<String> myQueue = new MyQueue<>();
        myQueue.enqueue("hello");
        System.out.println(myQueue.size());
        myQueue.enqueue("world");
        myQueue.enqueue("end");
        String string1 = myQueue.dequeue();
        System.out.println(string1);
        for (String s: myQueue)
            System.out.println(s);

        System.out.println("---------------------------");

        ResizingArrayQueue<String> resizingArrayQueue = new ResizingArrayQueue<>();
        resizingArrayQueue.enqueue("hello");
        resizingArrayQueue.enqueue("world");
        resizingArrayQueue.enqueue("end");
        String string2 = resizingArrayQueue.dequeue();
        System.out.println(string2);
        System.out.println(resizingArrayQueue.size());
        for (String s: resizingArrayQueue)
            System.out.println(s);
    }
}


/**
 * 先进先出队列（链表实现）
 * @param <Item> 泛型类型
 */
class MyQueue<Item> implements Iterable<Item>
{
    private Node first = new Node(); // 指向链表第一个节点
    private Node last = new Node(); // 指向链表最后一个节点
    private int N; // 链表中的元素数量

    private class Node
    {
        // 定义节点的嵌套类
        Item item;
        Node next;
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public int size()
    {
        return N;
    }

    public void enqueue(Item item)
    {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        // 如果是空queue，first与last指向同一个结点
        if (this.isEmpty())
            first = last;
        else
            oldlast.next = last;
        N++;
    }

    public Item dequeue()
    {
        Node oldfirst = first;
        // first置为下一结点，如果queue只有一个结点first被置为null
        first = first.next;
        // 如果queue只有一个节点，需要将last置null
        if (this.isEmpty()) last = null;
        N--;
        return oldfirst.item;
    }

    public Iterator<Item> iterator()
    {
        return new MyQueueIterator();
    }

    private class MyQueueIterator implements Iterator<Item>
    {
        // current保存当前首节点
        private Node current = first;

        public boolean hasNext()
        {
            // 迭代器位于相邻两元素之间，如：A -迭代器-> B，此时current指向B，通过判断current是否非空检测判断是否有下一个元素
            return current != null;
        }

        public Item next()
        {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {}
    }
}


/**
 * 先进先出队列（动态调整数组实现）
 * @param <Item> 泛型数据类型
 */
class  ResizingArrayQueue<Item> implements Iterable<Item>
{
    private int N; // 数组中元素量，队列尾
    private int M; // 已pop次数，队列头
    private Item[] items = (Item[]) new Object[1];

    public int size()
    {
        return N;
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    private void resize(int value)
    {
        Item[] temp = (Item[]) new Object[value];
        for (int i = 0; i < N; i++)
        {
            temp[i] = items[i];
        }
        items = temp;
    }

    public void enqueue(Item item)
    {
        if (N == items.length)
            this.resize(2 * items.length);
        items[N++] = item;
    }

    public Item dequeue()
    {
        if (N == items.length / 4)
            this.resize(items.length / 2);
        return items[M++];
    }

    public Iterator<Item> iterator()
    {
        return new MyQueueIterator();
    }

    private class MyQueueIterator implements Iterator<Item>
    {
        private int K = M; // 迭代器起始位置

        public boolean hasNext()
        {
            return K < N;
        }

        public Item next()
        {
            return items[K++];
        }

        public void remove() {}
    }

}