package com.chimu.mylib.util;

import android.content.pm.PackageManager;
import android.os.Environment;

import com.chimu.mylib.LibApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
        PackageManager pm = LibApplication.application.getPackageManager();
        String appName = LibApplication.application.getApplicationInfo().loadLabel(pm).toString();
        String url = Environment.getExternalStorageDirectory() + "/"+appName+"/";
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


    /**
     *
     * 保存文件
     * @param path
     * @param content
     * @throws Exception
     */
    public static void saveFile(String path, byte[] content) throws Exception {
        PackageManager pm = LibApplication.application.getPackageManager();
        String appName = LibApplication.application.getApplicationInfo().loadLabel(pm).toString();
        String url = Environment.getExternalStorageDirectory() + "/"+appName+"/";
        File file1 = new File(url);
        if(!file1.exists()){
            file1.mkdirs();
        }

        File file = new File(path);
        if(!file1.exists()){
            file.createNewFile();
        }
        FileOutputStream output = new FileOutputStream(file,true);
        output.write(content);
        output.close();
    }

    /**
     * 复制文件夹  fg:复制data/data/appname/ 下的文件
     * File f = new File(Environment.getDataDirectory().getPath()+"/data/"+this.getPackageName() + "/");
     FileUtil.copyPath(f,Environment.getExternalStorageDirectory());
     * @param oldPath
     * @param newPath
     */
    public static void copyPath(final File oldPath, final File newPath){
        if(!oldPath.isDirectory() || !newPath.isDirectory()){
            new IllegalArgumentException("File mast is Directory");
        }
        if(newPath.exists()){
            new IllegalArgumentException("newFile not is exists");
        }
        try{
            //io耗时操作，放在子线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    copyFiles(oldPath,oldPath.getPath(),newPath.getPath());
                }
            }).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void copyFiles(File oldFile,String oldPath,String newPath){
        File[] files = oldFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getPath());
            if(files[i].isDirectory()){
                createDirectory(files[i],oldPath,newPath);
                File[] fs = files[i].listFiles();
                if(fs != null && fs.length != 0){//如果这个文件夹的下一层内容不为空则继续遍历
                    copyFiles(files[i],oldPath,newPath);
                }
            }else if(files[i].isFile()){
                copyFile(files[i],oldPath,newPath);
            }
        }
    }

    public static void copyFile(File file,String oldPath,String newPath){
        String path = file.getPath();
        String nPath = path.replace(oldPath, newPath);
        File nfile = new File(nPath);
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new FileOutputStream(nfile);
            byte[] bs = new byte[1024];
            int len = -1;
            while((len = in.read(bs))!= -1){
                out.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(in != null){
                    in.close();
                }
                if(out != null){
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void createDirectory(File dir,String oldPath,String newPath){
        String path = dir.getPath();
        String nPath = path.replace(oldPath, newPath);
        File file = new File(nPath);
        file.mkdirs();
    }
}
