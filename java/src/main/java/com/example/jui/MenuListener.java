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

    public MenuListener(JFrame frame, JTextField account, JPasswordField password) {

        this.frame = frame;
        this.account = account;
        this.password = password;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (String.valueOf(password.getPassword()).equals("123456") && account.getText().equals("123456")) {
            System.out.println("haha");
            frame.dispose();//
        } else{
            System.out.println("1232发范德萨");
        }

    }
}
