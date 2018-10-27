package Algorithms.S3P1;

public class BinarySearch {
    public static int sort(Comparable[] a, Comparable key)
    {
        return sort(a, key, 0, a.length - 1);
    }

    private static int sort(Comparable[] a, Comparable key, int low, int high)
    {
        // key不存在返回-1
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

/**
 * 三类二分查找：
 * 传统二分 search:（查找特定元素的位置，不存在返回 -1 ）
 * 首位置二分 find、get:（查找特定元素第一次出现的位置，不存在返回 -1 ）
 * 末位置二分 getMaxPosition:（查找特定元素最后一次出现的位置，不存在返回 -1 ）
 */
class imporveBinarySearch
{
    public static int search(int[] a, int target)
    {
        int low = 0, high = a.length - 1;
        // 范围内元素量大于 1 （low < high） 或等于 1 （low == high）时，只要中间值与目标相等就返回 mid
        while (low <= high)
        {
            int mid = low + (high - low) / 2; // 不用 (low + high) / 2 是为了防止溢出
            if (target > a[mid])
                low = mid + 1;
            else if (target < a[mid])
                high = mid - 1;
            else
                return mid;
        }
        return -1;
    }

    public static int find(int[] a, int target)
    {
        int low = 0, high = a.length - 1;
        // 只有在 low == high 时才会对元素进行判断，在此之前移动 high 向左循环二分直至范围缩小到只有一个元素
        // 这样就避免了返回元素非首次出现位置的情况
        while (low < high)
        {
            int mid = low + (high - low) / 2;
            if (target > a[mid])
                low = mid + 1;
            else if (target < a[mid])
                high = mid - 1;
            else
                high = mid;
        }
        if (a[low] == target)
            return low;
        return -1;
    }

    public static int get(int[] a, int target)
    {
        int low = 0, high = a.length - 1;
        while (low <= high)
        {
            int mid = low + (high - low) / 2;
            if (target > a[mid])
                low = mid + 1;
            // 中间值等于目标但左侧有一样的元素或中间值大于目标，high左移
            else if ((target == a[mid] && mid > 0 && target == a[mid - 1]) || target < a[mid])
                high = mid - 1;
            else
                return mid;

        }
        return -1;
    }

    public static int getMaxPosition(int[] a, int target)
    {
        int low = 0, high = a.length - 1;
        while (low <= high)
        {
            int mid = low + (high - low) / 2;
            if (target < a[mid])
                high = mid - 1;
            // 中间值等于目标但右侧有一样的元素或中间值小于目标，high左移
            else if ((target == a[mid] && (mid + 1) < a.length && target == a[mid + 1]) || target > a[mid])
                low = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] test = new int[]{1, 3, 6, 7, 7, 7, 7, 9, 9, 9, 12, 26, 26};
        System.out.println(find(test, 7));
        System.out.println(get(test, 7 ));
        System.out.println(getMaxPosition(test, 7));
    }
}
