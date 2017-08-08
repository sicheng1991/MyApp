package com.example.jui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 * java图形界面
 * Created by Longwj on 2017/8/4.
 */

public class MenuDemo extends JFrame {
    public MenuDemo() {
        super("MenuDemo");

        initContent1();
        showView();
    }

    private void initContent() {
        JPanel root = new JPanel();
        root.add(new JButton("我是JButton"));
        root.add(new JToggleButton("我是JToggleButton"));
        root.add(new JLabel("我是JLabel"));
        root.add(new JCheckBox("我是JCheckBox"));
        root.add(new JRadioButton("我是JRadioButton"));
        root.add(new JTextField("我是JTextField"));
        root.add(new JPasswordField("我是JPasswordField"));
        root.add(new JTextArea("我是JTextArea"));
        add(root);
    }

    private void initContent1() {
        JPanel root = new JPanel();
        // 实例化元素组件类，然后将对象添加到窗体可见
        //实例化ImageIcon类的对象，从磁盘中提取出图片
        javax.swing.ImageIcon img=new javax.swing.ImageIcon("D:\\qq.png");
        //实例化JLbel类的对象，用来显示img
        javax.swing.JLabel labImg=new javax.swing.JLabel(img);
        //将JLabel类对象添加到窗口上
        root.add(labImg);

        //使用JLbel类显示文字
        javax.swing.JLabel labName1=new javax.swing.JLabel("账号:");
        root.add(labName1);

        //实例化文本框
        javax.swing.JTextField account=new javax.swing.JTextField();
        //封装组件的大小和高度   Dimentsion类的对象的实例化  dim对象可以重复利用
        java.awt.Dimension dim=new java.awt.Dimension(330,30);
        //设置组件的首先大小为Dimension类的对象
        account.setPreferredSize(dim);
        root.add(account);

        //显示密码及对应文本框 利用JLabel类和JPasswordField类
        javax.swing.JLabel labName2=new javax.swing.JLabel("密码:");
        root.add(labName2);
        javax.swing.JPasswordField password =new javax.swing.JPasswordField();
        password.setPreferredSize(dim);
        root.add(password);

        //利用JCheckBox类显示记住密码 和自动登录窗口
        javax.swing.JCheckBox checkbox1=new javax.swing.JCheckBox("   记 住 密 码");
        java.awt.Dimension box=new java.awt.Dimension(150,150);
        checkbox1.setPreferredSize(box);
        root.add(checkbox1);
        javax.swing.JCheckBox checkbox2=new javax.swing.JCheckBox("   自 动 登 录");
        checkbox2.setPreferredSize(box);
        root.add(checkbox2);

        //利用JButton类 设置窗口登录
        javax.swing.JButton button=new javax.swing.JButton("登录");
        java.awt.Dimension dimbutton=new java.awt.Dimension(150,80);
        button.setPreferredSize(dimbutton);
        root.add(button);

        MenuListener dl=new MenuListener(this,account,password);//初始化监听类对象
        button.addActionListener(dl);//对按钮button对象实现监听

        add(root);
    }

    private void showView() {
        setSize(400, 700);//设置大小
        setTitle("图形界面");//设置标题
        setDefaultCloseOperation(EXIT_ON_CLOSE);//界面关闭方式
        setLocationRelativeTo(null);//显示的界面居中
        setResizable(true);//设置能否改变大小
        setVisible(true);//界面的可见

        //dispose();//关闭当前页
    }

}
