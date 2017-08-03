package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Longwj on 2017/8/2.
 */

public class FileUtil {
    public  static String readTxtFile(File file){
        if (file.exists()) {
            System.out.println("file is exists");
        }else{
            System.out.println("file not exists");
        }
        InputStream in = null;

        byte[] bytes = null;
        String s = null;
        try {
            in = new FileInputStream(file);
            int x = in.available();
            bytes = new byte[x];
            in.read(bytes);
            s = new String(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

}
