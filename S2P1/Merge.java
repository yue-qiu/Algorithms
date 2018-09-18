package Algorithms.S2P1;

/**
 * �������򣬸��Ӷ�Ϊ NlgN ���ʺϴ�����
 */
public class Merge extends Example{
//    private static Comparable[] aux; ����ȫ

    public static void sort(Comparable[] a)
    {
        // �������Ṳ��һ����̬�������ڶ������ͬʱ����ʱ����aux����ȫ����Ϊ������������Ϊ��������
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int low, int high)
    {
        // ������a[low..high]����
        if (high <= low)
            return;
        else if ((high - low) < 15)
            // С�����ò������򣬼��ٵݹ飨��߲���Ч�ʣ�
            sortWithInsertion(a, low, high);
        else {
            int mid = low + (high - low) / 2;
            sort(a, aux, low, mid); // ��������������
            sort(a, aux, mid + 1, high); // �������Ұ������
            // ���a[mid]С��a[mid+1]���a[low..high]�Ѿ�������ϣ�����Ҫmerge����߲���Ч�ʣ�
            if (less(a[mid + 1], a[mid]))
                merge(a, aux, low, mid, high); // �鲢���
        }
    }

    private static void sortWithInsertion(Comparable[] a, int low, int high)
    {
        for (int i = low; i <= high; i++)
            for (int j = i; j > low && less(a[j], a[j-1]); j--)
                exch(a, j, j - 1);
    }

    /**
     * mergeֻ�ǽ����������������кϲ�������������������á�����Ҫ�ϲ�����������ʱ��merge����ȷ���ϲ��������Ҳ�������
     * @param a ���ϲ�����
     * @param low ͷ������
     * @param mid �в�����
     * @param high β������
     */
    protected static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high)
    {
        //aux = new Comparable[a.length];
        for (int k = low; k <= high; k++)
            aux[k] = a[k];

        int i = low;
        int j = mid + 1;

        // ͨ����������ķ�ʽ����aux[mid]�������Ե�Ԫ����a
        // i > mid,�����Ϊ�գ�ȡ�ұ�Ԫ�ط���a
        // j > high,���ұ�Ϊ�գ�ȥ���Ԫ�ط���a
        // ȡaux[i]��aux[j]�н�СԪ�ط���a
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

        System.out.println("׼����ʼ��");
        long begin = System.currentTimeMillis();
        sort(test);
        long end = System.currentTimeMillis();
        System.out.printf("10���Ԫ�س�������ʱ��Ϊ��%.4f��\n", (end - begin) / (double)1000);
        System.out.println(isSorted(test));
    }
}

