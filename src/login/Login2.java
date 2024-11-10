package login;

import client.ChatRoom;
import function.ClientBean;
import util.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Properties;

public class Login2 extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    public static HashMap<String, ClientBean> onlines;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // 启动登陆界面
                    Login2 frame = new Login2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login2() {
        setTitle("登录\n");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 160, 450, 300);
        contentPane = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon(
                                "images\\11.jpg").getImage(), 0,
                        0, getWidth(), getHeight(), null);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        textField = new JTextField();
        textField.setForeground(Color.red);
        textField.setBounds(180, 42, 104, 20);//登录用户名
        textField.setOpaque(false);
        contentPane.add(textField);
        textField.setColumns(10);

        //提示信息，用户名
        JLabel YHM = new JLabel("用户名：");
        YHM.setBounds(130, 42, 185, 20);
        YHM.setForeground(Color.blue);
        contentPane.add(YHM);

        passwordField = new JPasswordField();
        passwordField.setForeground(Color.red);
        passwordField.setEchoChar('*');
        passwordField.setOpaque(false);
        passwordField.setBounds(180, 100, 104, 25);//登录密码
        contentPane.add(passwordField);

        //提示信息，用户名
        JLabel DLMM= new JLabel("登录密码：");
        DLMM.setBounds(120, 100, 185, 20);
        DLMM.setForeground(Color.blue);
        contentPane.add(DLMM);

        final JButton btnNewButton = new JButton();
        btnNewButton.setText("登录");
        btnNewButton.setBounds(120, 180, 80, 40);//登录
        getRootPane().setDefaultButton(btnNewButton);
        contentPane.add(btnNewButton);

        final JButton btnNewButton_1 = new JButton();
        btnNewButton_1.setText("注册");
        btnNewButton_1.setBounds(250, 180, 80, 40);//注册
        contentPane.add(btnNewButton_1);

        // 提示信息
        final JLabel lblNewLabel = new JLabel();
        lblNewLabel.setBounds(60, 220, 151, 21);
        lblNewLabel.setForeground(Color.red);
        getContentPane().add(lblNewLabel);

        // 监听登陆按钮
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Properties userPro = new Properties();
                File file = new File("Users.properties");
                Util.loadPro(userPro, file);
                String u_name = textField.getText();
                if (file.length() != 0) {

                    if (userPro.containsKey(u_name)) {
                        String u_pwd = new String(passwordField.getPassword());
                        if (u_pwd.equals(userPro.getProperty(u_name))) {

                            try {
                                Socket client = new Socket("localhost", 8520);

                                btnNewButton.setEnabled(false);
                                ChatRoom frame = new ChatRoom(u_name,
                                        client);
                                frame.setVisible(true);// 显示聊天界面
                                setVisible(false);// 隐藏掉登陆界面

                            } catch (UnknownHostException e1) {
                                // TODO Auto-generated catch block
                                errorTip("The connection with the server is interrupted, please login again");
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                errorTip("The connection with the server is interrupted, please login again");
                            }

                        } else {
                            lblNewLabel.setText("您输入的密码有误！");
                            textField.setText("");
                            passwordField.setText("");
                            textField.requestFocus();
                        }
                    } else {
                        lblNewLabel.setText("您输入昵称不存在！");
                        textField.setText("");
                        passwordField.setText("");
                        textField.requestFocus();
                    }
                } else {
                    lblNewLabel.setText("您输入昵称不存在！");
                    textField.setText("");
                    passwordField.setText("");
                    textField.requestFocus();
                }
            }
        });

        //注册按钮监听
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnNewButton_1.setEnabled(false);
                Resign frame = new Resign();
                frame.setVisible(true);// 显示注册界面
                setVisible(false);// 隐藏掉登陆界面
            }
        });
    }

    protected void errorTip(String str) {
        // TODO Auto-generated method stub
        JOptionPane.showMessageDialog(contentPane, str, "Error Message",
                JOptionPane.ERROR_MESSAGE);
        textField.setText("");
        passwordField.setText("");
        textField.requestFocus();
    }
}