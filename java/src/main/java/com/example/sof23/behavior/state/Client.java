package com.example.sof23.behavior.state;

/**
 * ״̬ģʽ
 *
 * ��ͼ������������ڲ�״̬�����ı�ʱ�ı�������Ϊ���������������޸��������ࡣ
 ��Ҫ������������Ϊ����������״̬�����ԣ������ҿ��Ը�������״̬�ı���ı����������Ϊ��
 ��ʱʹ�ã������а������������״̬�йص�������䡣
 ��ν���������־����״̬����������

 * Created by Longwj on 2017/8/30.
 */

public class Client {

    public static void main(String[] args) {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);

        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());
    }
}
