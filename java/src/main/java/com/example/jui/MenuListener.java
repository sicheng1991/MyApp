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

    //д���캯��������Ҫ�õ��Ķ��󴫲ν���
    public MenuListener(JFrame frame, JTextField account, JPasswordField password) {

        this.frame = frame;
        this.account = account;
        this.password = password;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
//password.getPasswird()�õ�����char[]���͵����ݣ���ת��ΪString�࣬��String.valueOf()ʵ��
        if (String.valueOf(password.getPassword()).equals("123456") && account.getText().equals("123456")) {
            System.out.println("��¼�ɹ�");
            frame.dispose();//�ر�ԭ���ĵ�¼���棬
        } else{
            System.out.println("�˺Ż�������������µ�¼~");
        }

    }
}
