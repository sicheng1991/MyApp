package com.example.sof23.behavior.template;

/**
 * ģ��ģʽ
 *��ģ��ģʽ��Template Pattern���У�һ�������๫��������ִ�����ķ����ķ�ʽ/ģ�塣
 * ����������԰���Ҫ��д����ʵ�֣������ý��Գ������ж���ķ�ʽ���С��������͵����ģʽ������Ϊ��ģʽ��
 *
 * ��ͼ������һ�������е��㷨�ĹǼܣ�����һЩ�����ӳٵ������С�ģ�巽��ʹ��������Բ��ı�һ���㷨�Ľṹ�����ض�����㷨��ĳЩ�ض����衣
 *
 * ��Ҫ�����һЩ����ͨ�ã�ȴ��ÿһ�����඼����д����һ������
 ��ʱʹ�ã���һЩͨ�õķ�����
 ��ν��������Щͨ���㷨���������
 �ؼ����룺�ڳ�����ʵ�֣���������������ʵ�֡�

 *
 * Created by Longwj on 2017/8/29.
 */

public class Client {

    public static void main(String[] args) {

        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }

}
