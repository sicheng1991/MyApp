package com.example.jui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Created by Longwj on 2017/8/4.
 */

public class MenuListener implements ActionListener {

    private JFrame frame;
    private JTextField account;
    private JPasswordField password;

    //写构造函数，将需要用到的对象传参进来
    public MenuListener(JFrame frame, JTextField account, JPasswordField password) {

        this.frame = frame;
        this.account = account;
        this.password = password;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
//password.getPasswird()得到的是char[]类型的数据，需转化为String类，用String.valueOf()实现
        if (String.valueOf(password.getPassword()).equals("123456") && account.getText().equals("123456")) {
            System.out.println("登录成功");
            frame.dispose();//关闭原来的登录界面，
        } else{
            System.out.println("账号或密码错误，请重新登录~");
        }

    }
}
