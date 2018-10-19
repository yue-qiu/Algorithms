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

    // Ϊ�Զ�������д hashCode ����
    @Override
    public int hashCode() {
        // ����� 17 �� 31 �ǹ���
        // �Ը�����Աʹ�� hashCode ��������Զ������ hash
        int hash = 17;
        hash = 31 * hash + who.hashCode();
        hash = 31 * hash + when.hashCode();
        hash = 31 * hash + ((Double) amount).hashCode();
        return hash;
    }
}
