package com.example.sof23.behavior.mediator;

/**
 * �н���ģʽ
 * ��ͼ����һ���н��������װһϵ�еĶ��󽻻����н���ʹ������
 * ����Ҫ��ʽ���໥���ã��Ӷ�ʹ�������ɢ�����ҿ��Զ����ظı�����֮��Ľ�����
 *
 * ����ͨ��������ʵ������ʾ�н���ģʽ��ʵ���У�����û������������ҷ�����Ϣ��������
 * �����е��û���ʾ��Ϣ�����ǽ����������� ChatRoom �� User��User ����ʹ�� ChatRoom
 * �������������ǵ���Ϣ��
 * Created by Longwj on 2017/8/28.
 */

public class Client {
    public static void main(String[] args) {
        User robert = new User("Robert");
        User john = new User("John");

        robert.sendMessage("Hi! John!");
        john.sendMessage("Hello! Robert!");
    }
}
