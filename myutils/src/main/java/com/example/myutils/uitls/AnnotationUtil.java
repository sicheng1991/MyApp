package com.example.myutils.uitls;

import android.content.Context;
import android.content.pm.PackageManager;

import com.example.annotation.IPerson;

import java.io.IOException;
import java.util.List;

//import com.example.annotation.Person;

/**
 * Created by Longwj on 2017/7/21.
 */

public class AnnotationUtil {
    static AnnotationUtil mAnnotationUtil;

    public static AnnotationUtil getInstance() {
        if (mAnnotationUtil == null) {
            synchronized (AnnotationUtil.class) {
                if (mAnnotationUtil == null) {
                    mAnnotationUtil = new AnnotationUtil();
                }
            }
        }
        return mAnnotationUtil;
    }

    private AnnotationUtil() {

    }

    /**
     * @param context
     * @return
     */
    public static String getPerson(Context context) {
        String s = "";
        try {
            List<String> classFileNames = ClassUtils.getFileNameByPackageName(context, "com.chimu.myprocessor");
            for (int i = 0; i < classFileNames.size(); i++) {
                String name = classFileNames.get(i);
                if (name.endsWith("Person")) {
                    String info = ((IPerson) (Class.forName(name).newInstance())).getPersonInfo();
                    s = s + info  + ";";
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return s;
    }
}
