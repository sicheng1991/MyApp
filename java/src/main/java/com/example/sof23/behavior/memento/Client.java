package com.example.sof23.behavior.memento;

/**
 * ����¼ģʽ
 ����¼ģʽ��Memento Pattern������һ�������ĳ��״̬���Ա����ʵ���ʱ��ָ����󡣱���¼ģʽ������Ϊ��ģʽ��
 *
 * ��ʱʹ�ã��ܶ�ʱ������������Ҫ��¼һ��������ڲ�״̬����������Ŀ�ľ���Ϊ�������û�ȡ����ȷ�����ߴ���Ĳ������ܹ��ָ�����ԭ�ȵ�״̬��ʹ������"���ҩ"�ɳԡ�
 ��ν����ͨ��һ������¼��ר�Ŵ洢����״̬��
 �ؼ����룺�ͻ����뱸��¼����ϣ��뱸��¼��������ϡ�
 Ӧ��ʵ���� 1�����ҩ�� 2������Ϸʱ�Ĵ浵�� 3��Windows ��� ctri + z�� 4��IE �еĺ��ˡ� 4�����ݿ���������
 *
 * Created by Longwj on 2017/8/30.
 */

public class Client {
    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #4");

        System.out.println("Current State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }
}
