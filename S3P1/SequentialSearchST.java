package Algorithms.S3P1;

public class SequentialSearchST<Key, Value> {
    private Node first;
    private int count;

    public Value get(Key key)
    {
        // 遍历链表查找key
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.value; // 命中
        return null; // 未命中
    }

    public void put(Key key, Value value)
    {
        if (value == null)
        {
            delete(key);
            return;
        }
        // 遍历链表查找key，找到则更新对应value，否则新建Node
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
            {
                x.value = value;
                return;
            }
        first = new Node(key, value, first);
        count++;
    }

    public int size()
    {
        return count;
    }

    public boolean isEmpty()
    {
        return count == 0;
    }

    public void delete(Key key)
    {
        while (first.key == key)
        {
            first = first.next;
            count--;
        }

        for (Node x = first; x != null; x = x.next)
        {
            Node nextNode = x.next;
            if (nextNode != null && key.equals(nextNode.key))
            {
                x.next = nextNode.next;
                count--;
                return;
            }
        }
    }

    private class Node
    {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next)
        {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        st.put("hello", 1);
        st.put("world", 2);
        st.put("bye", 3);
        st.put("hello", 4);
        System.out.println(st.get("hello"));
        st.delete("hello");
        System.out.println(st.size());
    }
}
