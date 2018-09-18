package Algorithms.S2P1;

/**
 * 并归排序，复杂度为 NlgN 。适合大数组
 */
public class Merge extends Example{
//    private static Comparable[] aux; 不安全

    public static void sort(Comparable[] a)
    {
        // 多个对象会共用一个静态变量，在多个程序同时运行时变量aux不安全，改为将辅助数组作为参数传递
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int low, int high)
    {
        // 将数组a[low..high]排序
        if (high <= low)
            return;
        else if ((high - low) < 15)
            // 小数组用插入排序，减少递归（提高并归效率）
            sortWithInsertion(a, low, high);
        else {
            int mid = low + (high - low) / 2;
            sort(a, aux, low, mid); // 将数组左半边排序
            sort(a, aux, mid + 1, high); // 将数组右半边排序
            // 如果a[mid]小于a[mid+1]则该a[low..high]已经排序完毕，不需要merge（提高并归效率）
            if (less(a[mid + 1], a[mid]))
                merge(a, aux, low, mid, high); // 归并结果
        }
    }

    private static void sortWithInsertion(Comparable[] a, int low, int high)
    {
        for (int i = low; i <= high; i++)
            for (int j = i; j > low && less(a[j], a[j-1]); j--)
                exch(a, j, j - 1);
    }

    /**
     * merge只是将数组的两个子组进行合并，本身并不起到排序的作用。而当要合并的子组有序时，merge可以确保合并后的数组也是有序的
     * @param a 待合并数组
     * @param low 头部索引
     * @param mid 中部索引
     * @param high 尾部索引
     */
    protected static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high)
    {
        //aux = new Comparable[a.length];
        for (int k = low; k <= high; k++)
            aux[k] = a[k];

        int i = low;
        int j = mid + 1;

        // 通过遍历数组的方式并归aux[mid]左右两旁的元素至a
        // i > mid,即左边为空，取右边元素放入a
        // j > high,即右边为空，去左边元素放入a
        // 取aux[i]、aux[j]中较小元素放入a
        for (int k = low; k <= high; k++)
        {
            if (i > mid) a[k] = aux[j++];
            else if (j > high) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void main(String[] args)
    {
        Integer[] test = new Integer[100000];
        for (int i = 0; i < test.length; i++)
            test[i] = (int)(Math.random() * 100);

        System.out.println("准备开始：");
        long begin = System.currentTimeMillis();
        sort(test);
        long end = System.currentTimeMillis();
        System.out.printf("10万个元素程序运行时间为：%.4f秒\n", (end - begin) / (double)1000);
        System.out.println(isSorted(test));
    }
}

