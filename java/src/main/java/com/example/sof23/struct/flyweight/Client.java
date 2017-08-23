package com.example.sof23.struct.flyweight;

/**
 * Ԫģʽ��Flyweight Pattern����Ҫ���ڼ��ٴ���������������Լ����ڴ�ռ�ú�������ܡ�
 * �������͵����ģʽ���ڽṹ��ģʽ�����ṩ�˼��ٶ��������Ӷ�����Ӧ������Ķ���ṹ�ķ�ʽ��
 *
 * ��Ҫ��������д�������ʱ���п��ܻ�����ڴ���������ǰ����й�ͬ�Ĳ��ֳ��������
 * �������ͬ��ҵ������ֱ�ӷ������ڴ������еĶ��󣬱������´�����
 *
 * ��ν������Ψһ��ʶ���жϣ�������ڴ����У��򷵻����Ψһ��ʶ������ʶ�Ķ���
 �ؼ����룺�� HashMap �洢��Щ����
 *
 * Created by Longwj on 2017/8/23.
 */

public class Client {
    private static final String colors[] =
            { "Red", "Green", "Blue", "White", "Black" };
    public static void main(String[] args) {

        for(int i=0; i < 20; ++i) {
            Circle circle =
                    (Circle)ShapeFactory.getCircle(getRandomColor());
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
    }
    private static String getRandomColor() {
        return colors[(int)(Math.random()*colors.length)];
    }
    private static int getRandomX() {
        return (int)(Math.random()*100 );
    }
    private static int getRandomY() {
        return (int)(Math.random()*100);
    }
}
