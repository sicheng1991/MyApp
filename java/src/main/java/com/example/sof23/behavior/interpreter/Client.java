package com.example.sof23.behavior.interpreter;

/**
 * ������ģʽ
 * ������ģʽ��Interpreter Pattern���ṩ���������Ե��﷨����ʽ�ķ�ʽ����������Ϊ��ģʽ��
 * ����ģʽʵ����һ�����ʽ�ӿڣ��ýӿڽ���һ���ض��������ġ�����ģʽ������ SQL ���������Ŵ�������ȡ�
 * ��ͼ������һ�����ԣ����������ķ���ʾ��������һ�������������������ʹ�øñ�ʶ�����������еľ��ӡ�
 ��Ҫ���������һЩ�̶��ķ�����һ�����;��ӵĽ�������
 ��ʱʹ�ã����һ���ض����͵����ⷢ����Ƶ���㹻�ߣ���ô���ܾ�ֵ�ý�������ĸ���ʵ������Ϊһ����
 �����еľ��ӡ������Ϳ��Թ���һ�����������ý�����ͨ��������Щ��������������⡣
 ��ν���������﷨���������ս������ս����
 �ؼ����룺���������࣬����������֮���һЩȫ����Ϣ��һ���� HashMap��
 *
 * Created by Longwj on 2017/8/30.
 */

public class Client {
    //����Robert �� John ������
    public static Expression getMaleExpression(){
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    //����Julie ��һ���ѻ��Ů��
    public static Expression getMarriedWomanExpression(){
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }
    public static void main(String[] args) {
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();

        System.out.println("John is male? " + isMale.interpret("John"));
        System.out.println("Julie is a married women? "
                + isMarriedWoman.interpret("Married Julie"));
    }

}
