package Algorithms.S2P1;

import java.lang.*;

public class Bubble extends Example{
    public static void sort(Comparable[] a)
    {
        for (int i = 0; i < a.length; i++)
            for (int j = 1; j < a.length - i; j++)
            {
                if (less(a[j], a[j-1]))
                    exch(a, j, j-1);
            }
    }

    /**
     * �Ľ�ð�ݣ�����󲿷�����������ܼ��ٱȽϴ���
     * ÿ����������һ��flag�����һ��������������û�з�����������Ϊ��������Ѿ�������ģ���������Ƚ���
     * @param a ����������
     */
    public static void flagSort(Comparable[] a)
    {
        boolean flag = true;
        int len = a.length;
        while (flag)
        {
            flag = false;
            for (int j = 1; j < len; j++)
            {
                if (less(a[j], a[j-1]))
                {
                    exch(a, j, j-1);
                    flag = true;
                }
            }
            len--;
        }
    }
}
