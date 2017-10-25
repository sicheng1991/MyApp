package com.chimu.mylib.util;

/**
 * Created by Longwj on 2017/10/25.
 */

public class TryCatchUtil {

    public static void doCatch(iTryCatch t){
        try{
            t.ETry();
        }catch (Exception e){
            e.printStackTrace();
            //错误存入本地
            try {
                FileUtil.saveFile("123.txt",e.getMessage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            t.ECatch();
        }finally {
            t.EFinally();
        }

    }
    public interface iTryCatch{
        void ETry();
        void ECatch();
        void EFinally();
    }
}
