package com.example.sof23.behavior.iterator;

import java.util.ArrayList;

/**
 * ������ģʽ
 * ������ģʽ��Iterator Pattern���� Java �� .Net ��̻����зǳ����õ����ģʽ��
 * ����ģʽ����˳����ʼ��϶����Ԫ�أ�����Ҫ֪�����϶���ĵײ��ʾ��
 *
 * ArrayList.iterator()
 *
 * Created by Longwj on 2017/8/24.
 */

public class Client {
    public static void main(String[] args){
        NameRepository namesRepository = new NameRepository();

        for(Iterator iter = namesRepository.getIterator(); iter.hasNext();){
            String name = (String)iter.next();
            System.out.println("Name : " + name);
        }
    }
}
