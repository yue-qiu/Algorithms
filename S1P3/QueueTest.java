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
 * �Ƚ��ȳ����У�����ʵ�֣�
 * @param <Item> ��������
 */
class MyQueue<Item> implements Iterable<Item>
{
    private Node first = new Node(); // ָ�������һ���ڵ�
    private Node last = new Node(); // ָ���������һ���ڵ�
    private int N; // �����е�Ԫ������

    private class Node
    {
        // ����ڵ��Ƕ����
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
        // ����ǿ�queue��first��lastָ��ͬһ�����
        if (this.isEmpty())
            first = last;
        else
            oldlast.next = last;
        N++;
    }

    public Item dequeue()
    {
        Node oldfirst = first;
        // first��Ϊ��һ��㣬���queueֻ��һ�����first����Ϊnull
        first = first.next;
        // ���queueֻ��һ���ڵ㣬��Ҫ��last��null
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
        // current���浱ǰ�׽ڵ�
        private Node current = first;

        public boolean hasNext()
        {
            // ������λ��������Ԫ��֮�䣬�磺A -������-> B����ʱcurrentָ��B��ͨ���ж�current�Ƿ�ǿռ���ж��Ƿ�����һ��Ԫ��
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
 * �Ƚ��ȳ����У���̬��������ʵ�֣�
 * @param <Item> ������������
 */
class  ResizingArrayQueue<Item> implements Iterable<Item>
{
    private int N; // ������Ԫ����������β
    private int M; // ��pop����������ͷ
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
        private int K = M; // ��������ʼλ��

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