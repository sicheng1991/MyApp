package com.example.sof23.behavior.mediator;

import java.util.Date;

/**
 * Created by Longwj on 2017/8/28.
 */

public class ChatRoom {
    public static void showMessage(User user, String message){
        System.out.println(new Date().toString()
                + " [" + user.getName() +"] : " + message);
    }
}
