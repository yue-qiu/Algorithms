package Algorithms.S1P3;

import java.util.Iterator;

public class StackTest {
    public static void main(String[] args)
    {
        System.out.println("---------test MyFixedCapacityStack-------------");
        MyFixedCapacityStack<Double> fixedCapacityStack = new MyFixedCapacityStack<>(20);
        System.out.println(fixedCapacityStack.size());
        fixedCapacityStack.push(2.0);
        fixedCapacityStack.push(3.0);
        Double d = fixedCapacityStack.pop();
        System.out.println(d);
        System.out.println("---------test Stack-------------");
        Stack<String> stack = new Stack<>();
        Iterator<String> stringIterator = stack.iterator();
        stack.push("hello");
        Iterator<String> iterator = stack.iterator();
        System.out.println(iterator.hasNext());
        System.out.println(stringIterator.hasNext());
        String string = stack.pop();
        System.out.println(string);
        System.out.println(stack.size());
        Stack<Integer> integerStack = new Stack<>();
        int N = 50;
        while (N > 0)
        {
            integerStack.push(N % 2);
            N = N / 2;
        }
        for (int i: integerStack)
        {
         System.out.print(i);
        }
        stack.push("hello");
        System.out.println('\n' + stack.peek());
    }
}

/**
 * 固定大小的栈
 * @param <Item> 泛型类型
 */
class MyFixedCapacityStack<Item>
{
    private Item[] items;
    private int N;

    public MyFixedCapacityStack(int cap)
    {
        items = (Item[]) new Object[cap];
    }

    public void push(Item item)
    {
        items[N++] = item;
    }

    public Item pop()
    {
        return items[--N];
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public boolean isFull()
    {
        return N == items.length;
    }

    public int size()
    {
        return N;
    }
}


/**
 * 下压栈（动态调整数组大小实现）
 * 缺点在于某些push或pop操作会调整数组大小，这需要的时间与数组大小有关
 * @param <Item> 泛型类型
 */
class  MyResizingArraryStack<Item> implements Iterable<Item>
{
    private int N;
    // 创建泛型数组在Java中是不允许的，所以无法使用 new Item[1]这样的语句
    private Item[] items = (Item[]) new Object[1];

    public boolean isEmpty()
    {
        return N == 0;
    }

    public int size()
    {
        return N;
    }

    public void resize(int max)
    {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) temp[i] = items[i];
        items = temp;
    }

    public void push(Item item)
    {
        if (N == items.length) this.resize(2 * items.length);
        items[N++] = item;
    }

    public Item pop()
    {
        Item temp = items[--N];
        items[N] = null;
        //if (N == items.length / 4) this.resize(items.length / 2); // N > 0 可删
        if (N > 0 && N == items.length / 4) this.resize(items.length / 2);
        return temp;
    }

    public Iterator<Item> iterator()
    {
        return new MyReverseArraryIterator();
    }

    private class MyReverseArraryIterator implements Iterator<Item>
    {
        private int i = N;

        public boolean hasNext()
        {
            return N > 0;
        }

        public Item next()
        {
            return items[--i];
        }

        public void remove(){}
    }
}

/**
 * 下压堆栈（链表实现）
 * @param <Item>
 */
class Stack<Item> implements Iterable<Item>
{
    private int N;
    private Node first;

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

    public void push(Item item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Item pop()
    {
        Node oldfirst = first;
        first = first.next;
        N--;
        return oldfirst.item;
    }

    public Item peek()
    {
        return first.item;
    }

    public Iterator<Item> iterator()
    {
        return new MyStackIterator();
    }

    private class MyStackIterator implements Iterator<Item>
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