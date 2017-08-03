package com.example.util;

import com.example.bean.GroupBean;
import com.example.bean.HostBean;
import com.google.gson.Gson;

import java.io.File;

/**
 * Created by Longwj on 2017/8/2.
 */

public class Utils {
    /**
     * speedDiql转MarkDown
     */
    public static void SpeedDial2MD(){
        //获取数据
        File file = new File("D:\\host.txt");
        String s = FileUtil.readTxtFile(file);
//        System.out.println(s);

        //解析
        int index1 = s.indexOf("\"groups\": ");
        int size1 = "\"groups\": ".length();
        int index2 = s.indexOf("\"dials\": ");
        int size2 = "\"dials\": ".length();
        int index3 = s.indexOf("\"settings\"");
        String group = s.substring(index1+ size1,index2 - 5);
        String dial = s.substring(index2 + size2,index3 - 5);
//        System.out.println(group);
//        System.out.println(dial);
//        String[] groups = group.split(",");
//        for(String g : groups){
//            g.substring(g.indexOf("{"),g.indexOf("}"));
//            System.out.println(g);
//        }
        group.substring(group.indexOf("{") + 2);
        while (group.indexOf("{") != -1){
            System.out.println(group);
            String gg = group.substring(group.indexOf("{"),group.indexOf("}")+ 1);
            System.out.println(gg);
            group = group.substring(group.indexOf("}"));
        }

    }
}
