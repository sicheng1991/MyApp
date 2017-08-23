package com.example.sof23.behavior.observer;

/**
 * ����������һ�Զ��ϵʱ����ʹ�ù۲���ģʽ��Observer Pattern����
 * ���磬��һ�������޸�ʱ������Զ�֪ͨ�����������󡣹۲���ģʽ������Ϊ��ģʽ
 *
 * �ؼ����룺�ڳ���������һ�� ArrayList ��Ź۲����ǡ�
 * �ŵ㣺 1���۲��ߺͱ��۲����ǳ�����ϵġ� 2������һ�״������ơ�
 ȱ�㣺 1�����һ�����۲��߶����кܶ��ֱ�Ӻͼ�ӵĹ۲��ߵĻ��������еĹ۲��߶�֪ͨ����
 ���Ѻܶ�ʱ�䡣 2������ڹ۲��ߺ͹۲�Ŀ��֮����ѭ�������Ļ����۲�Ŀ��ᴥ������֮�����
 ѭ�����ã����ܵ���ϵͳ������ 3���۲���ģʽû����Ӧ�Ļ����ù۲���֪�����۲��Ŀ�������
 ��ô�����仯�ģ�������ֻ��֪���۲�Ŀ�귢���˱仯��
 *
 * Created by Longwj on 2017/8/23.
 */

public class Client {
    public static void main(String[] args) {
        Subscriber subscriber = new Subscriber();

        new HexaObserver(subscriber);
        new OctalObserver(subscriber);
        new BinaryObserver(subscriber);

        System.out.println("First state change: 15");
        subscriber.setState(15);
        System.out.println("Second state change: 10");
        subscriber.setState(10);
    }
}
