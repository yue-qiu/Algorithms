package Algorithms.S1P3.Exercise;

import java.util.Iterator;

public class LinkListTest {
    public static void main(String[] args)
    {
        MyLinkList<String> myLinkList = new MyLinkList<>();
        myLinkList.add("hello");
        myLinkList.add("world");
        myLinkList.add("bye");
        myLinkList.add("~");

        System.out.println("-------test deleteLast--------");
        myLinkList.deleteLast();
        for (String s: myLinkList) System.out.println(s);

        System.out.println("-----test delete------");
        myLinkList.delete(0);
        for (String s: myLinkList) System.out.println(s);

        System.out.println("------test find-------");
        for (String s: myLinkList) System.out.println(s);
        System.out.println("key: bye,result: " + myLinkList.find("bye"));
        System.out.println("key: see,result: " + myLinkList.find("see"));

        System.out.println("------test remove------");
        myLinkList.add("bye");
        for (String s: myLinkList) System.out.println(s);
        System.out.println("----------");
        myLinkList.remove("bye");
        for (String s: myLinkList) System.out.println(s);
    }
}

class MyLinkList<Item> implements Iterable<Item>
{
    private Node first;
    private Node last;
    private int N;

    private class Node
    {
        private Item item;
        private Node next;
    }

    public int size()
    {
        return N;
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public void add(Item item)
    {
        Node oldlast= last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (this.isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    /**
     * 接受一个int参数k，删除链表的第k个元素（如果存在）
     * @param index 参数索引
     */
    public void delete(int index)
    {
        Node temp1 = first;
        if (index == 0)
        {
            first = first.next;
            N--;
        }
        for (int i = 0; i < this.size(); i++)
        {
            if ( i < index - 1) temp1 = temp1.next; // temp1指向index前1节点
            else
            {
                Node temp2 = temp1.next; // temp2指向index节点
                temp1.next = temp2.next;
                N--;
                break;
            }
        }
    }

    /**
     * 删除链表的尾节点，其中链表的首节点为first
     */
    public void deleteLast()
    {
        if (this.size() == 1)
        {
            first = last = null;
            N--;
        }
        else if (this.size() == 2)
        {
            last = first;
            first.next = null;
            N--;
        }
        else
        {
            for (Node node = first; node != null; node = node.next)
            {
                if (node.next.equals(last))
                {
                    node.next = null;
                    last = node;
                    N--;
                }
            }
        }
    }

    /**
     * 编写一个方法remove，接受一个链表和一个字符串key作为参数，删除链表中所有item域为key的节点
     * 遍历链表，删除目标节点，注意别断链。复杂度O(n)
     * @param item 字符串参数
     */
    public void remove(Item item) {
        while (first != null && item.equals(first.item)) {
            first = first.next;
        }

        Node current = first;
        Node node;
        while (current != null && current.next != null) {
            node = current.next;
            if (item.equals(node.item)) {
                current.next = node.next;
            } else {
                current = node;
            }
        }
    }

    /**
     * 接受一个key，如果链表含有key返回true，否则返回false
     * @param key
     * @return
     */
    public boolean find(Item key)
    {
//        Node temp = first;
//        for (int i = 0; i < this.size() - 1; i++)
//        {
//            if (temp.item.equals(key)) return true;
//            else temp = temp.next;
//        }
//        return false;
        for (Node node = first; node != null; node = node.next)
        {
            if (node.item.equals(key)) return true;
        }
        return false;
    }

    public Iterator<Item> iterator()
    {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Item>
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
