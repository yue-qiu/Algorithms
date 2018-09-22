package Algorithms.S3P1;

public class ArrayST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private final static int DEFAULT_CAPACITY = 1;
    private int count;

    public ArrayST()
    {
        keys = (Key[])new Comparable[DEFAULT_CAPACITY];
        values = (Value[])new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    public int size()
    {
        return count;
    }

    public boolean isEmpty()
    {
        return count == 0;
    }

    public Value get(Key key)
    {
        for (int i = 0; i < count; i++) {
            if (keys[i].equals(key))
                return values[i];
        }
        return null;
    }

    public void put(Key key, Value value)
    {
        for (int i = 0; i < count; i++) {
            if (keys[i].equals(key))
            {
                values[i] = value;
                return;
            }
        }
        keys[count] = key;
        values[count] = value;
        count++;
        if (count == keys.length)
            resize(2 * keys.length);
    }

    public void delete(Key key)
    {
        for (int i = 0; i < count; i++) {
            if (keys[i].equals(key))
            {
                for (int j = count; j > i; j--)
                {
                    keys[j-1] = keys[j];
                    values[j-1] = values[j];
                }
                count--;
                if (count == keys.length / 4)
                    resize(keys.length / 2);
                break;
            }
        }
    }

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

    public static void main(String[] args) {
        ArrayST<String, Integer> arrayST = new ArrayST<>();
        arrayST.put("hello", 2);
        arrayST.put("hi", 3);
        arrayST.put("hello", 4);

        System.out.println(arrayST.get("hello"));
        arrayST.delete("hi");
        System.out.println(arrayST.size());
        arrayST.delete("hello");
        System.out.println(arrayST.size());
    }
}
