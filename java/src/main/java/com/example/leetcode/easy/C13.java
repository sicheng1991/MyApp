package com.example.leetcode.easy;


import java.util.HashMap;
import java.util.Map;

/**
 * ：罗马数字是由七个不同的符号来表示  I，V，X，L，C，D和M。
 * <p>
 * 符号       值
 * 我1
 * V 5
 * X 10
 * L 50
 * C 100
 * D 500
 * M 1000
 * 例如，两个是用II 罗马数字写成的，只有两个是加在一起的。十二写作，，XII简单地说X+ II。数字二十七写作XXVII，这是XX+ V+ II。
 * <p>
 * 罗马数字通常从左到右写成最大到最小。但是，四的数字不是IIII。相反，数字四写成IV。因为那个是在五个之前我们减去四个。同样的原则适用于编号为9的数字IX。有六种情况使用减法：
 * <p>
 * I可以放在V（5）和X（10）之前来制作4和9。
 * X可以在L（50）和C（100）之前放置40和90。
 * C可以在D（500）和M（1000）之前放置400和900。
 * 给定一个罗马数字，将其转换为整数。输入保证在1到3999的范围内。
 * <p>
 * Created by yangzteL on 2018/5/15 0015.
 */

public class C13 {

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }


    public static int romanToInt(String s) {
//        Map<Byte, Integer> map = new HashMap<>();
//        map.put((byte) 73, 1);//I
//        map.put((byte) 86, 5);//V
//        map.put((byte) 88, 10);//X
//        map.put((byte) 76, 50);//L
//        map.put((byte) 67, 100);//C
//        map.put((byte) 68, 500);//D
//        map.put((byte) 77, 1000);//M

        byte[] by = s.getBytes();


        int sum = 0;
        for (int i = 0; i < by.length; i++) {
            if (i + 1 < by.length) {
                if (by[i] == 73) {
                    if(by[i + 1] == 86){
                        sum += 4;
                        i++;
                        continue;
                    }else if(by[i + 1] == 88){
                        sum += 9;
                        i++;
                        continue;
                    }
                }else if (by[i] == 88) {
                    if(by[i + 1] == 76){
                        sum += 40;
                        i++;
                        continue;
                    }else if(by[i + 1] == 67){
                        sum += 90;
                        i++;
                        continue;
                    }
                }else  if (by[i] == 67) {
                    if(by[i + 1] == 68){
                        sum += 400;
                        i++;
                        continue;
                    }else if(by[i + 1] == 77){
                        sum += 900;
                        i++;
                        continue;
                    }
                }
            }
            switch (by[i]){
                case 73:
                    sum += 1;
                    break;
                case 86:
                    sum += 5;
                    break;
                case 88:
                    sum += 10;
                    break;
                case 76:
                    sum += 50;
                    break;
                case 67:
                    sum += 100;
                    break;
                case 68:
                    sum += 500;
                    break;
                case 77:
                    sum += 1000;
                    break;
            }
        }
        return sum;
    }

}

