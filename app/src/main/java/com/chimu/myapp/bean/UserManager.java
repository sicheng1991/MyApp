package com.chimu.myapp.bean;

import android.util.Log;

/**
 * Created by Longwj on 2017/11/8.
 */

public class UserManager {
//    public void performLogin(String username, String password) {
////        Log.i("UserManager","performLogin:"+ username + "：" + password);
//        System.out.println("performLogin:"+ username + "：" + password);
//    }

    public void performLogin(String username, String password,NetworkCallback callback) {
//        Log.i("UserManager","performLogin:"+ username + "：" + password);
        System.out.println("performLogin:"+ username + "：" + password);
    }

    public boolean verifyPassword(String password) {
        if (password == null || password.length() < 6) return false;
        return true;
    }
    public interface NetworkCallback{
        void success(String s);
        void onFailure(int code, String msg);
    }
}
