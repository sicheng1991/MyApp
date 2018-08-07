package com.example;


import java.util.regex.Pattern;

public class JavaTest {
    public static void main(String[] args) {
        System.out.println("main is run");

        deleteNullStr("{\n" +
                "    \"code\": \"300\",\n" +
                "    \"data\": null,\n" +
                "    \"message\": \"系统拒绝\",\n" +
                "    \"status\": \"2041\"\n" +
                "}");


    }
    public static boolean checkStr(String phone) {
        phone = phone.replace(" ", "");
        String regex = "^(0.[0-9]{2}$)|(1.00)$";

        return Pattern.matches(regex, phone);
    }

    public static String deleteNullStr(String str){
        str = str.replace(" ","");
        String regex = "^({|,)([\\s\\S]*):null(}|,)$";
        str = str.replace(regex,"");
        System.out.println(str);
        return  str;
    }

}


