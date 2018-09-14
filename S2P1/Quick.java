package Algorithms.S2P1;

/**
 * �������򣬸��Ӷ�Ϊ N*logN������������ N*N
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
     * �����зַ���������ֳ�С��pivot������pivot������pivot���ݣ��Ӷ������˶��ظ�Ԫ�ض������
     * @param a ����������
     * @param low ��ʼ����
     * @param high β����
     */
    private static void sort3ways(Comparable[] a, int low, int high)
    {
        if (high <= low + 15)
        {
            sortWithInsertion(a, low, high);
            return;
        }
        //lt��ʾ�����С�ڱȽ�ֵ���±�λ��
        //gt��ʾ�Ҳ�洢���ڱȽ�ֵ���±�λ��
        //i�ǵ�ǰ��Ҫ���бȽϵ��±�λ��,iֵ����಻�����Ҳ�ǰ��,
        //��i��gt����ʱ,����ÿ�η����Ѿ����.
        int lt = low, gt = high, i = low + 1;
        while (i <= gt)
        {
            //1 �����ǰiֵС�ڱȽ�ֵ,�򽫵�ǰi�±�ֵ��lt�±�ֵ���н���,
            //  ͬʱlt����,׼���洢��һ��С��ֵ, ��ǰ����iҲ���еݽ�

            //2 �����ǰiֵ���ڱȽ�ֵ,�򽫵�ǰi�±�ֵ��gt�±�ֵ���н���,
            //  ͬʱgt�ݼ�,׼���洢��һ�����ֵ, ע�����ﵱǰ����i��û��
            //  �ݽ�,������Ϊi�Ǵ��������ƶ���,����С�ڱȽ�ֵ�Ĵ���,��Ϊ
            //  i�Ѿ�֪������ֵ���Ǿ����������,һ������С�ڱȽ�ֵ��,��
            //  �Խ��е�1��С�ڱȽ�ֵ�Ĵ���ʱ,��ֱ�ӵ���,����ǰ�������
            //  �Ƚ�ֵʱ,���Ҳ��������ʲô������������,���Ե��Ҳ�����
            //  ��������ǰi����ֵ,i���ܵݽ�,����Ҫ���Ҳ�������ֵ���з���

            //3 �����ǰiֵ��Ƚ�ֵ���,�����轻������,ֱ�ӵ�i�����ݽ�

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
        // �������з�Ϊa[low..i-1]��a[i]��a[i+1..high]
        int i = low, j = high + 1; // ����ɨ��ָ��
        Comparable v = a[low]; // �з�Ԫ��
        while (true)
        {
            // ɨ�����ң����ɨ���Ƿ����������
            while (less(a[++i], v))
                if (i == high)
                    break;
            while (less(v, a[--j]))
                if (j == low)
                    break;
            if (i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, low, j); // ��v=a[j]������ȷ��λ��
        return j; // a[low..j-1] <= a[j] <= a[j+1..high]���
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

//  �����ظ�����ʱ�����зַ���������
//        {
//            if (i < 100000)
//                test[i] = 1;
//            else if (i < 200000)
//                test[i] = 2;
//            else test[i] = 3;
//        }

        System.out.println("׼����ʼ��");
        long begin = System.currentTimeMillis();
        sort(test);
        long end = System.currentTimeMillis();
        System.out.printf("30���Ԫ�س�������ʱ��Ϊ��%.4f��\n", (end - begin) / (double)1000);
        System.out.println(isSorted(test));
    }
}
