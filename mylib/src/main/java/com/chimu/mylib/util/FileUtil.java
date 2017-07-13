package com.chimu.mylib.util;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Longwj on 2017/7/13.
 */

public class FileUtil {

    /**
     *
     * 保存文件
     * @param name
     * @param content
     * @throws Exception
     */
    public static void saveFile(String name, String content) throws Exception {
        String url = Environment.getExternalStorageDirectory() + "/pdalogs/";
        File file1 = new File(url);
        if(!file1.exists()){
            file1.mkdirs();
        }

        File file = new File(url + name);
        if(!file1.exists()){
            file.createNewFile();
        }
        FileOutputStream output = new FileOutputStream(file,true);
        output.write(content.getBytes());
        output.close();
    }
}
