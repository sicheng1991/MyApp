package com.chimu.myapp.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by Longwj on 2017/11/7.
 */

public class User implements Parcelable {
    private int age;
    private String name;


    private UserManager mUserManager = new UserManager();

    public void login(String username, String password) {
        if (username == null || username.length() == 0) return;
        if (password == null || password.length() < 6) return;
        //假设我们对密码强度有一定要求，使用一个专门的validator来验证密码的有效性
        if (!mUserManager.verifyPassword(password)) {
            return;
        }
        mUserManager.performLogin(username, password, new UserManager.NetworkCallback() {
            @Override
            public void success(String s) {
                System.out.println(s);
            }

            @Override
            public void onFailure(int code, String msg) {
                System.out.println("code:" + code + " msg:" + msg);
            }
        });
    }

    public boolean verifyPassword(String pwd){
        return mUserManager.verifyPassword(pwd);
    }

    public void setmUserManager(UserManager mUserManager) {
        this.mUserManager = mUserManager;
    }

    protected User(Parcel in) {
        age = in.readInt();
        name = in.readString();
    }

    public User() {
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
    }
}
