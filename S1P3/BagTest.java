package Algorithms.S1P3;

import java.util.Iterator;

public class BagTest {
    public static void main(String[] args)
    {
     Bag<String> bag = new Bag<>();
     bag.add("hello");
     System.out.println(bag.size());
    }
}


class Bag<Item> implements Iterable<Item>
{
    private Node first = new Node();
    private int N;

    private class Node
    {
        Node next;
        Item item;
    }

    public void add(Item item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public int size()
    {
        return N;
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public Iterator<Item> iterator()
    {
        return new MyBagIterator();
    }

    private class MyBagIterator implements Iterator<Item>
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
