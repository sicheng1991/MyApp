package com.example.sof23.behavior.visitor;

/**
 * ������ģʽ
 *
 * ��ͼ����Ҫ�����ݽṹ�����ݲ������롣
 ��Ҫ������ȶ������ݽṹ���ױ�Ĳ���������⡣
 ��ʱʹ�ã���Ҫ��һ������ṹ�еĶ�����кܶ಻ͬ�Ĳ��Ҳ���صĲ���������Ҫ��������Щ����"��Ⱦ"
 ��Щ������࣬ʹ�÷�����ģʽ����Щ��װ�����С�
 ��ν�����ڱ����ʵ��������һ�������ṩ�Ӵ������ߵĽӿڡ�
 �ؼ����룺�����ݻ�����������һ���������ܷ����ߣ����������ô�������ߡ�
 * Created by Longwj on 2017/8/30.
 */

public class Client {

    public static void main(String[] args) {

        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
