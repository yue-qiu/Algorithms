package Algorithms.S1P3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 计算类似( 1 + 2 + ( ( 3 * 4 )  / 5) )的算式结果，支持加减乘除。
 */
public class Evaluate {
    public static void main(String[] args)
    {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();


        while (!StdIn.isEmpty())
        {
            // 读取操作符
            String s = StdIn.readString();
            if (s.equals("("));
            else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) ops.push(s);
            else if (s.equals(")"))
            {
                // 如果字符为“)”，弹出运算符和操作数，计算结果入栈
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
            } // 如果既不是括号也不是运算符，入数字栈
            else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }
}
