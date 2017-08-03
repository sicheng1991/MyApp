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
     * speedDiql转MarkDown
     */
    public static void SpeedDial2MD() throws Exception{
        //获取数据
        File file = new File("D:\\host.txt");
        String s = FileUtil.readTxtFile(file);

        //动态解析
        HostBean hostBean1 =  JSON.parseObject(s,HostBean.class);
        //组
        JSONObject jsonObj = JSON.parseObject(hostBean1.getGroups().toString());
        List<GroupBean> groups = new ArrayList<>();
        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            GroupBean bean =  JSON.parseObject(String.valueOf(entry.getValue()),GroupBean.class);
            System.out.println(bean.toString());
            groups.add(bean);
        }
        //网址
        JSONObject jsonObj1 = JSON.parseObject(hostBean1.getDials().toString());
        List<Dialbean> dials = new ArrayList<>();
        for (Map.Entry<String, Object> entry : jsonObj1.entrySet()) {
            Dialbean bean =  JSON.parseObject(String.valueOf(entry.getValue()),Dialbean.class);
            System.out.println(bean.toString());
            dials.add(bean);
        }

        //开始生产markdown文件
        createMD(groups, dials);
    }

    private static void createMD(List<GroupBean> groups, List<Dialbean> dials) throws IOException {
        File mdFile = new File("host.md");
        if (!mdFile.exists()) {
            mdFile.createNewFile();
        }
        String end = "  \n";
        StringBuilder sb = new StringBuilder();
        sb.append("## Speed Dial 网址  \n");
        OutputStream os = new FileOutputStream(mdFile);
        for(GroupBean bean : groups){
            sb.append("## " + bean.getTitle()).append(end);
            for(Dialbean dialbean : dials){
                if(dialbean.getIdgroup() == bean.getId()){
                    sb.append("- " + dialbean.getTitle() + ":" + dialbean.getUrl()).append(end);
                }
            }
        }
        byte[] bytes =  sb.toString().getBytes();
        os.write(bytes);
        os.flush();
        os.close();
        System.out.println("down");
    }
}
