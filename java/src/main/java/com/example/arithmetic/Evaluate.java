package com.example.arithmetic;

import java.util.Scanner;
import java.util.Stack;

/**
 * 双stack实现计算
 *
 * Created by Longwj on 2017/9/27.
 */

public class Evaluate {

    public static  double eval(String s){


        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        s = getSuffixString(s);

        if(s != null){
            char [] chars = s.toCharArray();

            for (char aChar : chars) {
                String c = String.valueOf(aChar);
                if(c.equals("(")){

                }else if(c.equals("+")){
                    ops.push(c);
                } else if(c.equals("-")){
                    ops.push(c);
                } else if(c.equals("*")){
                    ops.push(c);
                } else if(c.equals("/")){
                    ops.push(c);
                } else if(c.equals("=")){
                    int size  = ops.size();
                    for(int i = 0;i < size;i++){
                        String op = ops.pop();
                        double v = vals.pop();
                        if(op.equals("+")){
                            v = vals.pop() + v;
                        }else if(op.equals("-")){
                            v = vals.pop() - v;
                        }else if(op.equals("*")){
                            v = vals.pop() * v;
                        }else if(op.equals("/")){
                            v = vals.pop() * v;
                        }
                        vals.push(v);
                    }
                }else {
                    vals.push(Double.valueOf(c));
                }

            }

        }
        return vals.pop();
    }

    /**
     * 得到后缀表达式
     * @param s
     * @return
     */
    private static String getSuffixString(String s){
        StringBuffer sb = new StringBuffer();

        s = s.replace(" ","");
        Stack<String> stack = new Stack<>();

        int num = s.length();
        for(int i = 0;i < num;i++){
            char c = s.charAt(i);
            if('0' <= c && c <= '9'){
                sb.append(c);
            }else if(c == '+' || c == '-'){
                while (!stack.isEmpty() || !stack.peek().equals("(")){
                    sb.append(stack.pop());
                }

            }else if(c == '*'|| c == '/' ){
                while (!stack.isEmpty() || !stack.peek().equals("(") || !stack.peek().equals("+") || !stack.peek().equals("-")){
                    sb.append(stack.pop());
                }
            }else if(c == '(' ){
                stack.push("(");
            } else if(c == ')' ){
                while (!stack.isEmpty() ){
                    if (!stack.peek().equals("(")){
                        stack.pop();
                        break;
                    }
                    sb.append(stack.pop());
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println("输入表达式:");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        System.out.println( Evaluate.eval(read));
    }
}
