package com.example.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.bean.Dialbean;
import com.example.bean.GroupBean;
import com.example.bean.HostBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Longwj on 2017/8/2.
 */

public class Utils {
    /**
     * speedDiqlתMarkDown
     */
    public static void SpeedDial2MD() throws Exception{
        String name = "D:\\host";

        File file = new File(name);
        String s = FileUtil.readTxtFile(file);
        HostBean hostBean1 =  JSON.parseObject(s,HostBean.class);
        JSONObject jsonObj = JSON.parseObject(hostBean1.getGroups().toString());
        List<GroupBean> groups = new ArrayList<>();
        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            GroupBean bean =  JSON.parseObject(String.valueOf(entry.getValue()),GroupBean.class);
//            System.out.println(bean.toString());
            groups.add(bean);
        }
        JSONObject jsonObj1 = JSON.parseObject(hostBean1.getDials().toString());
        List<Dialbean> dials = new ArrayList<>();
        for (Map.Entry<String, Object> entry : jsonObj1.entrySet()) {
            Dialbean bean =  JSON.parseObject(String.valueOf(entry.getValue()),Dialbean.class);
//            System.out.println(bean.toString());
            dials.add(bean);
        }

        createMD(groups, dials);
    }

    private static void createMD(List<GroupBean> groups, List<Dialbean> dials) throws IOException {
        File mdFile = new File("host.md");
        if (!mdFile.exists()) {
            mdFile.createNewFile();
        }
        String end = "  \n";
        StringBuilder sb = new StringBuilder();
        sb.append("## Speed Dial Website  \n");
        OutputStream os = new FileOutputStream(mdFile);
        for(GroupBean bean : groups){
            sb.append("## " + bean.getTitle()).append(end);
            for(Dialbean dialbean : dials){
                if(dialbean.getIdgroup() == bean.getId()){
                    sb.append("- " + dialbean.getTitle() + ":" + dialbean.getUrl()).append(end);
                }
            }
        }
        String newStr = new String(sb.toString().getBytes(), "UTF-8");
        System.out.println(newStr);
        byte[] bytes =  newStr.getBytes();
        os.write(bytes);
        os.flush();
        os.close();
        System.out.println("down");
    }
}
