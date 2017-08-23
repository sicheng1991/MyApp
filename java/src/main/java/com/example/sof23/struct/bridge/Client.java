package com.example.sof23.struct.bridge;

/**
 * �Žӣ�Bridge�������ڰѳ�����ʵ�ֻ����ʹ�ö��߿��Զ����仯���������͵����ģʽ���ڽṹ��ģ
 * ʽ����ͨ���ṩ���󻯺�ʵ�ֻ�֮����Žӽṹ����ʵ�ֶ��ߵĽ��
 ����ģʽ�漰��һ����Ϊ�ŽӵĽӿڣ�ʹ��ʵ����Ĺ��ܶ����ڽӿ�ʵ���ࡣ
 ���������͵���ɱ��ṹ���ı������Ӱ�졣
 ����ͨ�������ʵ������ʾ�Ž�ģʽ��Bridge Pattern�����÷������У�����ʹ����ͬ�ĳ����෽��
 ���ǲ�ͬ���Ž�ʵ���࣬��������ͬ��ɫ��Բ��

 �ŵ㣺 1�������ʵ�ֵķ��롣 2���������չ������ 3��ʵ��ϸ�ڶԿͻ�͸����
 ȱ�㣺�Ž�ģʽ�����������ϵͳ�����������Ѷȣ����ھۺϹ�����ϵ�����ڳ���㣬
 Ҫ�󿪷�����Գ������������̡�
 * �Ž�ģʽ
 *
 * Created by Longwj on 2017/8/23.
 */

public class Client {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new BuleCircle());

        redCircle.draw();
        greenCircle.draw();
    }

}
