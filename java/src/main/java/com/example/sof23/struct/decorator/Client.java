package com.example.sof23.struct.decorator;

/**
 * װ��ģʽ
 * װ����ģʽ��Decorator Pattern��������һ�����еĶ�������µĹ��ܣ�ͬʱ�ֲ��ı���ṹ��
 * �������͵����ģʽ���ڽṹ��ģʽ��������Ϊ���е����һ����װ��
 ����ģʽ������һ��װ���࣬������װԭ�е��࣬���ڱ����෽��ǩ�������Ե�ǰ���£��ṩ�˶���Ĺ��ܡ�
 ����ͨ�������ʵ������ʾװ����ģʽ���÷������У����ǽ���һ����״װ���ϲ�ͬ����ɫ��
 ͬʱ�ֲ��ı���״�ࡣ
 * Created by Longwj on 2017/8/23.
 */

public class Client {
    public static void main(String[] args){
        Shape circle = new Square();

        Shape redCircle = new RedShapeDecorator(new Square());

        Shape redRectangle = new RedShapeDecorator(new Rectangle());

        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}
