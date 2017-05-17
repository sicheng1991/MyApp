package com.example.chimu.myrxjava;

import org.junit.Before;
import org.junit.Test;


/**
 * Created by longwj on 2017/3/29.
 */
public class MainActivityTest {
    private MainActivity  activity;

    @Before
    public void setUp() throws Exception {
        activity = new MainActivity();
    }
    @Test
    public void rxjava6() throws Exception {
        activity.Rxjava6();
    }

}