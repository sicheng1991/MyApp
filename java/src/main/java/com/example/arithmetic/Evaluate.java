package com.example.arithmetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ˫stackʵ
 * <p>
 * Created by Longwj on 2017/9/27.
 */

public class Evaluate {

    public static double eval(String s) {
        Stack<Double> vals = new Stack<>();
        if (!checkString(s)) {
            return 0;
        }
        List<String> list = getSuffixString(s);

        for (String c: list) {
            if (c.matches("[0-9]+")) {
                vals.push(Double.valueOf(c));
            } else if (c.equals("+")) {
                Double d1 = vals.pop();
                Double d2 = vals.pop();
                vals.push(d2 + d1);
            } else if (c.equals("-")) {
                Double d1 = vals.pop();
                Double d2 = vals.pop();
                vals.push(d2 - d1);
            } else if (c.equals("*")) {
                Double d1 = vals.pop();
                Double d2 = vals.pop();
                vals.push(d2 * d1);
            } else if (c.equals("/")) {
                Double d1 = vals.pop();
                Double d2 = vals.pop();
                vals.push(d2 / d1);
            }
        }

        return vals.pop();
    }

    private static boolean checkString(String s) {
//        String str = "[0-9+-*/]+";
//        Pattern pattern = Pattern.compile(str, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(s);
//        return matcher.matches();
        return true;
    }

    /**
     * �õ���׺���ʽ
     *
     * @param s
     * @return
     */
    private static List<String> getSuffixString(String s) {
//        StringBuffer sb = new StringBuffer();

        s = s.replace(" ", "");
        List<String> list = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        int num = s.length();
        for (int i = 0; i < num; i++) {
            char c = s.charAt(i);
            if ('0' <= c && c <= '9') {
                char c1 = '0';
                String ss = String.valueOf(c);
                while (i < num - 1 && (c1 = s.charAt(i + 1)) <= '9' && c1 >= '0') {
                    i++;
                    ss = ss + c1;
                }
                list.add(String.valueOf(ss));
            } else if (c == '+' || c == '-') {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    list.add(String.valueOf(stack.pop()));
                }
                stack.push(String.valueOf(c));
            } else if (c == '*' || c == '/') {
                while (!stack.isEmpty() & !stack.peek().equals("(") && !stack.peek().equals("+") && !stack.peek().equals("-")) {
                    list.add(String.valueOf(stack.pop()));
                }
                stack.push(String.valueOf(c));
            } else if (c == '(') {
                stack.push("(");
            } else if (c == ')') {
                while (!stack.isEmpty()) {
                    if (stack.peek().equals("(")) {
                        stack.pop();
                        break;
                    }
                    list.add(String.valueOf(stack.pop()));
                }
            }
        }
        while (!stack.isEmpty()) {
            list.add(String.valueOf(stack.pop()));
        }
        return list;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String read = "";
        while(true){
            System.out.println("������ʽ:end����");
            read = scan.nextLine();
            if("end".equals(read)){
                System.out.println("лл");
                break;
            }
            System.out.println("�����"+ Evaluate.eval(read));
        }
    }
}
