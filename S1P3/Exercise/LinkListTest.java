package Algorithms.S1P3.Exercise;

import java.util.Iterator;

public class LinkListTest {

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
         * ����һ��int����k��ɾ������ĵ�k��Ԫ�أ�������ڣ�
         * @param index ��������
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
                if ( i < index - 1) temp1 = temp1.next; // temp1ָ��indexǰ1�ڵ�
                else
                {
                    Node temp2 = temp1.next; // temp2ָ��index�ڵ�
                    temp1.next = temp2.next;
                    N--;
                    break;
                }
            }
        }

        /**
         * ɾ�������β�ڵ㣬����������׽ڵ�Ϊfirst
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
         * ��дһ������remove������һ�������һ���ַ���key��Ϊ������ɾ������������item��Ϊkey�Ľڵ�
         * ��������ɾ��Ŀ��ڵ㣬ע�����������Ӷ�O(n)
         * @param item �ַ�������
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
         * ����һ��key�����������key����true�����򷵻�false
         * @param key
         * @return
         */
        public boolean find(Item key)
        {
            for (Node node = first; node != null; node = node.next)
            {
                if (node.item.equals(key)) return true;
            }
            return false;
        }

        public Iterator<Item> iterator()
        {
            return new Iterator<Item>() {
                private  Node cur = first;

                @Override
                public boolean hasNext() {
                    // ������λ��������Ԫ��֮�䣬�磺A -������-> B����ʱcurrentָ��B��ͨ���ж�current�Ƿ�ǿռ���ж��Ƿ�����һ��Ԫ��
                    return cur != null;
                }

                @Override
                public Item next() {
                    Item item = cur.item;
                    cur = cur.next;
                    return item;
                }
            };
        }
    }
}
