package Algorithms.S3P1;

public class BinarySearch {
    public static int sort(Comparable[] a, Comparable key)
    {
        return sort(a, key, 0, a.length - 1);
    }

    private static int sort(Comparable[] a, Comparable key, int low, int high)
    {
        // key²»´æÔÚ·µ»Ø-1
        if (high < low)
            return -1;

        int mid = low + (high - low) / 2;
        int cmp = a[mid].compareTo(key);
        if (cmp > 0)
            return sort(a, key, low, mid - 1);
        else if (cmp < 0)
            return sort(a, key, mid + 1, high);
        else
            return mid;
    }

    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 4, 5};
        System.out.println(sort(integers, 8));
    }
}
