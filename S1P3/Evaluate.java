package Algorithms.S1P3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * ��������( 1 + 2 + ( ( 3 * 4 )  / 5) )����ʽ�����֧�ּӼ��˳���
 */
public class Evaluate {
    public static void main(String[] args)
    {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();


        while (!StdIn.isEmpty())
        {
            // ��ȡ������
            String s = StdIn.readString();
            if (s.equals("("));
            else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) ops.push(s);
            else if (s.equals(")"))
            {
                // ����ַ�Ϊ��)��������������Ͳ���������������ջ
                String op = ops.pop();
                Double val = vals.pop();

                if (op.equals("+"))
                    val = vals.pop() + val;
                else if (op.equals("-"))
                    val = vals.pop() - val;
                else if (op.equals("*"))
                    val = vals.pop() * val;
                else if (op.equals("/"))
                    val = vals.pop() / val;
                vals.push(val);
            } // ����Ȳ�������Ҳ�����������������ջ
            else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }
}
