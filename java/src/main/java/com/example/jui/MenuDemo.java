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
 *
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
        root.add(new JButton("����JButton"));
        root.add(new JToggleButton("����JToggleButton"));
        root.add(new JLabel("����JLabel"));
        root.add(new JCheckBox("����JCheckBox"));
        root.add(new JRadioButton("����JRadioButton"));
        root.add(new JTextField("����JTextField"));
        root.add(new JPasswordField("����JPasswordField"));
        root.add(new JTextArea("����JTextArea"));
        add(root);
    }

    private void initContent1() {
        JPanel root = new JPanel();
        // ʵ����Ԫ������࣬Ȼ�󽫶�����ӵ�����ɼ�
        //ʵ����ImageIcon��Ķ��󣬴Ӵ�������ȡ��ͼƬ
        javax.swing.ImageIcon img=new javax.swing.ImageIcon("D:\\qq.png");
        //ʵ����JLbel��Ķ���������ʾimg
        javax.swing.JLabel labImg=new javax.swing.JLabel(img);
        //��JLabel�������ӵ�������
        root.add(labImg);

        //ʹ��JLbel����ʾ����
        javax.swing.JLabel labName1=new javax.swing.JLabel("�˺�:");
        root.add(labName1);

        //ʵ�����ı���
        javax.swing.JTextField account=new javax.swing.JTextField();
        //��װ����Ĵ�С�͸߶�   Dimentsion��Ķ����ʵ����  dim��������ظ�����
        java.awt.Dimension dim=new java.awt.Dimension(330,30);
        //������������ȴ�СΪDimension��Ķ���
        account.setPreferredSize(dim);
        root.add(account);

        //��ʾ���뼰��Ӧ�ı��� ����JLabel���JPasswordField��
        javax.swing.JLabel labName2=new javax.swing.JLabel("����:");
        root.add(labName2);
        javax.swing.JPasswordField password =new javax.swing.JPasswordField();
        password.setPreferredSize(dim);
        root.add(password);

        //����JCheckBox����ʾ��ס���� ���Զ���¼����
        javax.swing.JCheckBox checkbox1=new javax.swing.JCheckBox("   �� ס �� ��");
        java.awt.Dimension box=new java.awt.Dimension(150,150);
        checkbox1.setPreferredSize(box);
        root.add(checkbox1);
        javax.swing.JCheckBox checkbox2=new javax.swing.JCheckBox("   �� �� �� ¼");
        checkbox2.setPreferredSize(box);
        root.add(checkbox2);

        //����JButton�� ���ô��ڵ�¼
        javax.swing.JButton button=new javax.swing.JButton("��¼");
        java.awt.Dimension dimbutton=new java.awt.Dimension(150,80);
        button.setPreferredSize(dimbutton);
        root.add(button);

        MenuListener dl=new MenuListener(this,account,password);//��ʼ�����������
        button.addActionListener(dl);//�԰�ťbutton����ʵ�ּ���

        add(root);
    }

    private void showView() {
        setSize(400, 700);//���ô�С
        setTitle("ͼ�ν���");//���ñ���
        setDefaultCloseOperation(EXIT_ON_CLOSE);//����رշ�ʽ
        setLocationRelativeTo(null);//��ʾ�Ľ������
        setResizable(true);//�����ܷ�ı��С
        setVisible(true);//����Ŀɼ�

        //dispose();//�رյ�ǰҳ
    }

}
