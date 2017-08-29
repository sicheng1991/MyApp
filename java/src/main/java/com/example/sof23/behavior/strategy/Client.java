package com.example.sof23.behavior.strategy;

/**
 *�ڲ���ģʽ��Strategy Pattern���У�һ�������Ϊ�����㷨����������ʱ���ġ��������͵����ģʽ������Ϊ��ģʽ��
 �ڲ���ģʽ�У����Ǵ�����ʾ���ֲ��ԵĶ����һ����Ϊ���Ų��Զ���ı���ı�� context ����
 x���Զ���ı� context �����ִ���㷨
 *
 * Created by Longwj on 2017/8/29.
 */

public class Client {

    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubstract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}
