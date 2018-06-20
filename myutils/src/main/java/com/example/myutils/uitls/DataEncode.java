package com.example.myutils.uitls;

/**
 * 数据类型转换
 * <p>
 * Created by Jack.Yan on 2017/7/17.
 */
public class DataEncode {
    private final static String[] hexDigits =
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    private static String byte2HexStr(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 将二进制数组转为16进制字符串
     *
     * @param b 二进制数据
     * @return 16进抽字符串
     */
    public static String bytes2HexStr(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        try {
            for (int i = 0; i < b.length; i++) {
                resultSb.append(byte2HexStr(b[i]));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSb.toString();
    }

    /**
     * 将16进制字符串转为2进制数组
     *
     * @param src 16进制字符串
     * @return byte[] 二进制数组
     */
    public static byte[] hexStr2Bytes(String src) {
        src = src.trim().replace(" ", "").toUpperCase();
        int m = 0, n = 0;
        int iLen = src.length() / 2;
        byte[] ret = new byte[iLen];
        for (int i = 0; i < iLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = (byte) (Integer.decode("0x" + src.substring(i * 2, m) + src.substring(m, n)) & 0xFF);
        }
        return ret;
    }
}
