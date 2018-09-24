package Algorithms.S3P1;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;

import java.util.Iterator;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int count;
    private static final int DEFAULT_CAPACITY = 1;

//    @SuppressWarnings("unchecked")
//    public BinarySearchST(int capacity)
//    {
//        keys = (Key[])new Comparable[capacity];
//        values = (Value[])new Object[capacity];
//    }

    @SuppressWarnings("unchecked")
    public BinarySearchST()
    {
        keys = (Key[])new Comparable[DEFAULT_CAPACITY];
        values = (Value[])new Object[DEFAULT_CAPACITY];
    }

    public int size()
    {
        return count;
    }

    public Value get(Key key)
    {
        if (isEmpty())
            return null;
        int i = rank(key);

        if (i < count && keys[i].compareTo(key) == 0)
            return values[i];
        else
            return null;
    }

    public int rank(Key key)
    {
        int low = 0;
        int high = count - 1;
        while (low <= high)
        {
            int mid = low + (high - low) / 2;
            int cmp = keys[mid].compareTo(key);
            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid;
        }
        return low;
    }

    public boolean isEmpty()
    {
        return count == 0;
    }

    public void put(Key key, Value value)
    {
        if (value == null)
            return;

        int i = rank(key);

        // 找到key则更新值
        if (i < count && keys[i].compareTo(key) == 0)
        {
            values[i] = value;
            return;
        }

        // 找不到则插入key与value
        for (int j = count; j > i; j--)
        {
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }
        keys[i] = key;
        values[i] = value;
        count++;
        if (count == keys.length)
            resize(2 * keys.length);
    }

    public void delete(Key key)
    {
        if (get(key) == null)
            return;
        int i = rank(key);
        for (int j = i; j < count; j++)
        {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }
        count--;
        if (count == keys.length / 4)
            resize(keys.length / 2);
    }

    @SuppressWarnings("unchecked")
    private void resize(int size)
    {
        Key[] tempKeys = (Key[])new Comparable[size];
        Value[] tempValues = (Value[])new Object[size];
        for (int i = 0; i < count; i++) {
            tempKeys[i] = keys[i];
            tempValues[i] = values[i];
        }
        keys = tempKeys;
        values = tempValues;
    }

    public Key min()
    {
        return keys[0];
    }

    public Key max()
    {
        return keys[count-1];
    }

    public Key select(int k)
    {
        return keys[k];
    }

    /**
     * 小于等于key的最小键
     */
    public Key floor(Key key)
    {
        return keys[rank(key)];
    }

    /**
     * 大于等于key的最大键
     */
    public Key ceiling(Key key)
    {
        return keys[rank(key)];
    }

    /**
     * [low...high]之间的所有键
     * @return 可迭代对象
     */
    public Iterable<Key> keys(Key low, Key high)
    {
        if (rank(low) > rank(high))
            return null;

        return new keysIterable<>(rank(low), rank(high), keys);

    }

    /**
     * 所有键的集合
     * @return 可迭代对象
     */
    public Iterable<Key> keys()
    {
        return new keysIterable<>(rank(min()), rank(max()), keys);
    }

    
    private class keysIterable<Key> implements Iterable<Key>
    {
        private Key[] a;

        @SuppressWarnings("unchecked")
        public keysIterable(int low, int high, Key[] temp)
        {
            a = (Key[]) new Object[high - low + 1];
            int j = 0;
            for (int i = low; i <= high; i++) {
                a[j++] = temp[i];
            }
        }

        @Override
        public Iterator<Key> iterator() {
            return new iterator<>(a);
        }

        private class iterator<Key> implements Iterator<Key>
        {
            private Key[] a;
            private int N = 0;

            public iterator(Key[] temp)
            {
                a = temp;
            }

            @Override
            public Key next() {
                return a[N++];
            }

            @Override
            public boolean hasNext() {
                return N < a.length;
            }
        }
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<>();
        binarySearchST.put("hello", 1);
        binarySearchST.put("hi", 2);
        binarySearchST.put("bye", 3);
        binarySearchST.put("hello", 4);

        Iterable<String> strings = binarySearchST.keys();
        Iterator iterator = strings.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");

        System.out.print("\n");

        Iterable<String> iterable = binarySearchST.keys("bye", "hello");
        Iterator stringIterator = iterable.iterator();
        while (stringIterator.hasNext())
            System.out.print(stringIterator.next() + " ");

        System.out.print("\n");

        System.out.println(binarySearchST.ceiling("hello"));

        System.out.println(binarySearchST.get("hello"));

        binarySearchST.delete("hello");
        System.out.println(binarySearchST.get("hello"));
    }
}
