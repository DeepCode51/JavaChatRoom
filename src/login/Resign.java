package login;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import util.Util;

public class Resign extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private JLabel lblNewLabel;

    public Resign() {
        setTitle("注册\n");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(350, 250, 450, 300);
        contentPane = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon("images\\22.jpg").getImage(), 0,0, getWidth(), getHeight(), null);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(180, 42, 104, 20);//用户名
        textField.setOpaque(false);
        contentPane.add(textField);
        textField.setColumns(10);

        //提示信息，用户名
        JLabel YHM = new JLabel("用户名：");
        YHM.setBounds(130, 42, 185, 20);
        YHM.setForeground(Color.blue);
        contentPane.add(YHM);

        passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        passwordField.setOpaque(false);
        passwordField.setBounds(180, 98, 104, 20);//密码
        contentPane.add(passwordField);

        //提示信息，密码
        JLabel MM = new JLabel("密码：");
        MM.setBounds(130, 98, 185, 20);
        MM.setForeground(Color.blue);
        contentPane.add(MM);

        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(180, 152, 104, 21);//确认密码
        passwordField_1.setOpaque(false);
        contentPane.add(passwordField_1);

        //提示信息，密码
        JLabel QRMM = new JLabel("确认密码：");
        QRMM.setBounds(130, 152, 185, 20);
        QRMM.setForeground(Color.blue);
        contentPane.add(QRMM);

        //注册按钮
        final JButton btnNewButton_1 = new JButton();
        btnNewButton_1.setText("注册");
        btnNewButton_1.setBounds(120, 198, 80, 40);
        getRootPane().setDefaultButton(btnNewButton_1);
        contentPane.add(btnNewButton_1);

        //返回按钮
        final JButton btnNewButton_2 = new JButton("");
        btnNewButton_2.setText("返回");
        btnNewButton_2.setBounds(250, 198, 80, 40);
        contentPane.add(btnNewButton_2);

        //提示信息
        lblNewLabel = new JLabel();
        lblNewLabel.setBounds(55, 218, 185, 20);
        lblNewLabel.setForeground(Color.red);
        contentPane.add(lblNewLabel);

        //返回按钮监听
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnNewButton_2.setEnabled(false);
                //返回登陆界面
                Login frame = new Login();
                frame.setVisible(true);
                setVisible(false);
            }
        });

        //注册按钮监听
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Properties userPro = new Properties();
                File file = new File("Users.properties");
                Util.loadPro(userPro, file);

                String u_name = textField.getText();
                String u_pwd = new String(passwordField.getPassword());
                String u_pwd_ag = new String(passwordField_1.getPassword());

                // 判断用户名是否在普通用户中已存在
                if (u_name.length() != 0) {

                    if (userPro.containsKey(u_name)) {
                        lblNewLabel.setText("用户名已存在!");
                    } else {
                        isPassword(userPro, file, u_name, u_pwd, u_pwd_ag);
                    }
                } else {
                    lblNewLabel.setText("用户名不能为空！");
                }
            }

            private void isPassword(Properties userPro,
                                    File file, String u_name, String u_pwd, String u_pwd_ag) {
                if (u_pwd.equals(u_pwd_ag)) {
                    if (u_pwd.length() != 0) {
                        userPro.setProperty(u_name, u_pwd_ag);
                        try {
                            userPro.store(new FileOutputStream(file),
                                    "Copyright (c) Boxcode Studio");
                        } catch (FileNotFoundException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        btnNewButton_1.setEnabled(false);
                        //返回登陆界面
                        Login frame = new Login();
                        frame.setVisible(true);
                        setVisible(false);
                    } else {
                        lblNewLabel.setText("密码为空！");
                    }
                } else {
                    lblNewLabel.setText("密码不一致！");
                }
            }
        });
    }
}

