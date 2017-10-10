package com.sicheng.mydatabinding.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sicheng.mydatabinding.BR;
import com.sicheng.mydatabinding.MyApplication;
import com.sicheng.mydatabinding.R;

/**
 * Created by Longwj on 2017/10/9.
 */

public class Student extends BaseObservable{

    public String name;

    public int age;

    public String password;


    private String face;//头像


    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    @BindingAdapter("face")
    public static void getInternetImage(ImageView iv, String userface) {
       iv.setImageBitmap(BitmapFactory.decodeResource(MyApplication.myApplication.getResources(),R.mipmap.a1));
    }

    public void onItemClick(View view) {
        Toast.makeText(view.getContext(), "点击事件", Toast.LENGTH_SHORT).show();
    }
    @Bindable
    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
