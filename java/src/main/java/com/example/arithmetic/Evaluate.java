package com.example.arithmetic;

import java.util.Stack;

/**
 * À´stack µœ÷º∆À„
 *
 * Created by Longwj on 2017/9/27.
 */

public class Evaluate {

    public static  double eval(String s){
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        s = s.replace(" ","");

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
}
