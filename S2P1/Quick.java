package Algorithms.S2P1;

/**
 * 快速排序，复杂度为 N*logN，最差情况下是 N*N
 */
public class Quick extends Example{
    public static void sort(Comparable[] a)
    {
        sort(a, 0, a.length-1);
        //sort3ways(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int low, int high)
    {
        if (high <= low + 15)
        {
            sortWithInsertion(a, low, high);
            return;
        }
        int j = partition(a, low, high);
        sort(a, low, j-1);
        sort(a, j+1, high);
    }

    /**
     * 三向切分法：将数组分成小于pivot，等于pivot，大于pivot三份，从而避免了对重复元素多次排序
     * @param a 待排序数组
     * @param low 起始索引
     * @param high 尾索引
     */
    private static void sort3ways(Comparable[] a, int low, int high)
    {
        if (high <= low + 15)
        {
            sortWithInsertion(a, low, high);
            return;
        }
        //lt表示左侧存放小于比较值的下标位置
        //gt表示右侧存储大于比较值的下标位置
        //i是当前需要进行比较的下标位置,i值从左侧不断向右侧前行,
        //当i与gt相遇时,表明每次分析已经完成.
        int lt = low, gt = high, i = low + 1;
        while (i <= gt)
        {
            //1 如果当前i值小于比较值,则将当前i下标值与lt下标值进行交换,
            //  同时lt递增,准备存储下一个小的值, 当前索引i也进行递进

            //2 如果当前i值大于比较值,则将当前i下标值与gt下标值进行交换,
            //  同时gt递减,准备存储下一个大的值, 注意这里当前索引i并没有
            //  递进,这是因为i是从左向右移动的,对于小于比较值的处理,因为
            //  i已经知道左侧的值都是经过它处理的,一定都是小于比较值的,所
            //  以进行第1步小于比较值的处理时,它直接递增,而当前处理大于
            //  比较值时,最右侧的数据是什么情况它并不清楚,所以当右侧数据
            //  交换到当前i索引值,i不能递进,它需要对右侧移来的值进行分析

            //3 如果当前i值与比较值相等,则无需交换处理,直接当i索引递进

            int cmp = a[i].compareTo(a[low]);
            if (cmp < 0) exch(a, i++, lt++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        sort3ways(a, low, lt - 1);
        sort3ways(a, gt + 1, high);

    }

    private static int partition(Comparable[] a, int low, int high)
    {
        // 将数组切分为a[low..i-1]，a[i]，a[i+1..high]
        int i = low, j = high + 1; // 左右扫描指针
        Comparable v = a[low]; // 切分元素
        while (true)
        {
            // 扫描左右，检查扫描是否结束并交换
            while (less(a[++i], v) && i != high) ;
            while (less(v, a[--j]) && j != low) ;
            if (i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, low, j); // 将v=a[j]放入正确的位置
        return j; // a[low..j-1] <= a[j] <= a[j+1..high]达成
    }

    private static void sortWithInsertion(Comparable[] a, int low, int high)
    {
        for (int i = low; i <= high; i++)
            for (int j = i; j > low && less(a[j], a[j-1]); j--)
                exch(a, j, j - 1);
    }

    public static void main(String[] args)
    {
        Integer[] test = new Integer[300000];
        for (int i = 0; i < test.length; i++)
            test[i] = (int)(Math.random() * 100);

//  大量重复输入时三向切分法优势明显
//        {
//            if (i < 100000)
//                test[i] = 1;
//            else if (i < 200000)
//                test[i] = 2;
//            else test[i] = 3;
//        }

        System.out.println("准备开始：");
        long begin = System.currentTimeMillis();
        sort(test);
        long end = System.currentTimeMillis();
        System.out.printf("30万个元素程序运行时间为：%.4f秒\n", (end - begin) / (double)1000);
        System.out.println(isSorted(test));
    }
}
