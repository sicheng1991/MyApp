package com.example.sof23.struct.facade;

/**
 *
 * ���ģʽ������ϵͳ�ĸ����ԣ�����ͻ����ṩ��һ���ͻ��˿��Է���ϵͳ�Ľӿڡ�
 * �������͵����ģʽ���ڽṹ��ģʽ���������е�ϵͳ���һ���ӿڣ�������ϵͳ�ĸ����ԡ�
 *
 * Created by Longwj on 2017/8/21.
 */

public class Client {
   public static void main(String[] a){
       ShapeMaker shapeMaker = new ShapeMaker();

       shapeMaker.drawCircle();
       shapeMaker.drawRectangle();
       shapeMaker.drawSquare();
   }
}
