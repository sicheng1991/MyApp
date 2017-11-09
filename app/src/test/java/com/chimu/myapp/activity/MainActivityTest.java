package com.chimu.myapp.activity;

import android.content.Intent;
import android.support.v7.appcompat.BuildConfig;

import com.chimu.myapp.R;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.*;

/**
 *
 * robolectic 测试Android代码示例
 *
 * Created by Longwj on 2017/11/9.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25,manifest=Config.NONE)
public class MainActivityTest {

    private MainActivity mainActivity;

    @Before
    public void setUp(){
        //Robolectric.setupActivity返回的时候，这个Activity已经完成了onCreate、onStart、onResume这几个生命周期的回调了。
        mainActivity = Robolectric.setupActivity(MainActivity.class);//创建Activity
    }

    @Test
    public void onClick() throws Exception {
        mainActivity.findViewById(R.id.tv_camera2).performClick(); //用来触发点击事件。
        //用来获取mainActivity对应的ShadowActivity的instance。
        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        //用来获取mainActivity调用的startActivity的intent。这也是正常的Activity类里面不具有的一个接口。
        Intent intent = shadowActivity.getNextStartedActivity();
        //最后，调用Assert.assertEquals来assert启动的intent是我们期望的intent。
        Assert.assertEquals(intent.getComponent().getClassName(),"Camera2Activity.class");
    }

}