package com.example.sof23.behavior.command;

/**
 * ����ģʽ
 ����ģʽ��Client Pattern����һ���������������ģʽ����������Ϊ��ģʽ��
 �������������ʽ�����ڶ����У����������ö��󡣵��ö���Ѱ�ҿ��Դ��������ĺ��ʵĶ���
 �����Ѹ��������Ӧ�Ķ��󣬸ö���ִ�����
 *
 * Created by Longwj on 2017/8/28.
 */

public class Client {
    public static void main(String[] args) {
        Stock abcStock = new Stock();

        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }
}
