package com.sicheng.game.snakegame.util;

/**
 * Created by yangzteL on 2018/10/30 0030.
 */

/**
 *
 * @author Nan
 *
 */
public class UnicodeUtil {

    /**
     * 将utf-8的汉字转换成unicode格式汉字码
     * @param string
     * @return
     */
    public static String stringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }

    /**
     * 将unicode的汉字码转换成utf-8格式的汉字
     * @param unicode
     * @return
     */
    public static String unicodeToString(String unicode) {

        String str = unicode.replace("0x", "\\");

        StringBuffer string = new StringBuffer();
        String[] hex = str.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);
            string.append((char) data);
        }
        return string.toString();
    }
    /**
     * 这个方法，看着爽，效率低(时间长，耗内存)
     *
     * @param text
     * @return
     */
    public static String unicode2Utf8(String text) {
        try {
            byte[] converttoBytes = text.getBytes("UTF-8");
            String s2 = new String(converttoBytes, "UTF-8");
            return s2;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
