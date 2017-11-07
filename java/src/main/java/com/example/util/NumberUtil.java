package com.example.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Longwj on 2017/11/6.
 */

public class NumberUtil {
    private static char[][] cc = new char[][]{
            {0},
            {1},
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'},
    };
    private static int[] len = new int[]{1, 1, 3, 3, 3, 3, 3, 4, 3, 4};

    /**
     * @param phone
     * @return
     */
    public static String getWord(long phone) {
        StringBuffer sb = new StringBuffer();
        String word = FileUtil.readTxtFile(new File("D:\\wordlist.TXT"));
        while (phone != 0) {
            int aa = (int) (phone % 10);
            for (int j = 0; j < len[aa]; j++) {
                char c = cc[aa][j];
                sb.append(c);
                int leng = sb.length();
                for (int i = 0; i < leng / 2; i++) {
                    char tmp = sb.charAt(i);
                    sb.setCharAt(i, sb.charAt(leng - i - 1));
                    sb.setCharAt(leng - i - 1, tmp);
                }
                if (word.contains(sb.toString())) {
                    System.out.println(sb.toString());
                } else {
//                System.out.println("no word");
                }
            }
            phone /= 10;
        }
//        int leng = sb.length();
//        for (int i = 0; i < leng / 2; i++) {
//            char tmp = sb.charAt(i);
//            sb.setCharAt(i, sb.charAt(leng - i - 1));
//            sb.setCharAt(leng - i - 1, tmp);
//        }

        return sb.toString();
    }

    /**
     * 方法还有问题
     *
     * @param number
     * @param answer
     * @param index
     * @param leng
     */
    static void recursiveResarch(int[] number, List<String> answer, int index, int leng) {
        if (index < leng) {
            int size = answer.size();
            if(size == 0){
                for (int i = 0; i < len[number[index]]; i++) {
                    answer.add(new String(String.valueOf(cc[number[index]][i])));
                }
                recursiveResarch(number, answer,index + 1, leng);
                return;
            }
            System.out.println("index:" + index +" size:" + answer.size());
            for (int i = 0; i < len[number[index]]; i++) {
                for (int j = 0; j < size; j++) {
                    String s = answer.get(j);
                    if (i == 0) {
                        s = s + cc[number[index]][i];
//                        System.out.println(s);
                    } else {
                        answer.add(new String(s.substring(index - 1) + cc[number[index]][i]));
//                        System.out.println("i = " + i + "j = "+ j  +  " " + s.substring(index - 1) + cc[number[index]][i]);
                    }
                    recursiveResarch(number, answer,index + 1, leng);
                }
            }
        } else {
//            for (String s : answer) {
//                System.out.println(s);
//            }
//            System.out.println("大小：" + answer.size());
        }

    }

    public static void getReasrch() {
        int[] number = new int[]{5, 3, 9, 7, 6, 3, 9, 8, 7, 4};
        List<String> answer = new ArrayList<>();
        recursiveResarch(number, answer, 0, 3);
    }

}
