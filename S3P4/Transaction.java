package Algorithms.S3P4;

import java.util.Date;

public class Transaction {
    private  final  String who;
    private final Date when;
    private final double amount;

    public Transaction(String who, Date when, double amount)
    {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    // 为自定义类重写 hashCode 方法
    @Override
    public int hashCode() {
        // 这里的 17 与 31 是惯例
        // 对各个成员使用 hashCode 方法算出自定义类的 hash
        int hash = 17;
        hash = 31 * hash + who.hashCode();
        hash = 31 * hash + when.hashCode();
        hash = 31 * hash + ((Double) amount).hashCode();
        return hash;
    }
}
