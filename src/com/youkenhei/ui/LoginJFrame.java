package com.youkenhei.ui;

import com.youkenhei.userinfo.User;
import com.youkenhei.util.CodeUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * LoginJFrame是登录界面
 * <br>
 * 跟登录相关的逻辑都在其中
 */
public class LoginJFrame extends JFrame implements MouseListener {
    static ArrayList<User> list = new ArrayList<>();
    static{
        list.add(new User("zhangsan", "123qwe"));
        list.add(new User("zhaosi", "qwe123"));
    }
    JLabel rightCode = new JLabel();
    JButton login = new JButton();
    JButton register = new JButton();
    JTextField code = new JTextField();
    JTextField userName = new JTextField();
    JPasswordField passWord = new JPasswordField();





    String rightCodeStr = "";
    public LoginJFrame() {
        initJFrame();
        initView();

        this.setVisible(true);
    }

    private void initView() {
        JLabel userNameText = new JLabel(new ImageIcon("/Users/yangjianping/Desktop/JavaStudy/puzzlegame/image/login/用户名.png"));
        userNameText.setBounds(116,135,47,17);
        this.getContentPane().add(userNameText);

        userName.setBounds(195,134,200,30);
        this.getContentPane().add(userName);

        JLabel passWordText = new JLabel(new ImageIcon("puzzlegame/image/login/密码.png"));
        passWordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passWordText);

        passWord.setBounds(195, 195, 200, 30);
        this.getContentPane().add(passWord);

        JLabel codeText = new JLabel(new ImageIcon("puzzlegame/image/login/验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);

        String codeStr = CodeUtil.getCode();
        rightCodeStr = codeStr;
        rightCode.setText(codeStr);
        rightCode.setBounds(300, 256, 50, 30);
        rightCode.addMouseListener(this);
        this.getContentPane().add(rightCode);

        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("puzzlegame/image/login/登录按钮.png"));
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.addMouseListener(this);
        this.getContentPane().add(login);

        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("puzzlegame/image/login/注册按钮.png"));
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        register.addMouseListener(this);
        this.getContentPane().add(register);

        JLabel backRound = new JLabel(new ImageIcon("puzzlegame/image/login/background.png"));
        backRound.setBounds(9, 0, 470, 390);
        this.getContentPane().add(backRound);

        this.getContentPane().repaint();
    }
    private void initJFrame() {
        this.setSize(488, 430);
        this.setTitle("拼图单机版 登录页");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 设置默认的关闭模式
        this.setLayout(null);
    }
   private void showJDialog(String content) {
       JDialog jDialog = new JDialog();
       jDialog.setSize(200,150);
       jDialog.setAlwaysOnTop(true);
       jDialog.setLocationRelativeTo(null);
       jDialog.setModal(true);

       JLabel warning = new JLabel(content);
       warning.setBounds(0, 0, 200,150);
       jDialog.getContentPane().add(warning);

       jDialog.setVisible(true);
   }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();
        if (source == login) {
            login.setIcon(new ImageIcon("puzzlegame/image/login/登录按下.png"));
            this.getContentPane().repaint();

        } else if (source == register) {
            register.setIcon(new ImageIcon("puzzlegame/image/login/注册按下.png"));
            this.getContentPane().repaint();

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object source = e.getSource();
        if (source == rightCode) {
            rightCodeStr = CodeUtil.getCode();
            rightCode.setText(rightCodeStr);
            this.getContentPane().repaint();
            System.out.println(rightCodeStr);

        }else if (source == login) {
            login.setIcon(new ImageIcon("puzzlegame/image/login/登录按钮.png"));
            this.getContentPane().repaint();

            if (!code.getText().equalsIgnoreCase(rightCodeStr)) {
                showJDialog("验证码错误");
                String rightCodeStr = CodeUtil.getCode();
                rightCode.setText(rightCodeStr);
                this.getContentPane().repaint();
                return;
            }
            if (userName.getText().length() == 0 || passWord.getText().length() == 0) {
                showJDialog("账号或密码不能为空");
                String rightCodeStr = CodeUtil.getCode();
                rightCode.setText(rightCodeStr);
                this.getContentPane().repaint();
                return;
            }
            User cUser = new User(userName.getText(), passWord.getText());
            if (checkUserInfo(list, cUser)) {
                    this.setVisible(false);
                    new GameJFrame();
                    return;
            }
            showJDialog("账号或密码有误");
            String rightCodeStr = CodeUtil.getCode();
            rightCode.setText(rightCodeStr);
            this.getContentPane().repaint();
        }else if (source == register) {
            register.setIcon(new ImageIcon("puzzlegame/image/login/注册按钮.png"));
            this.getContentPane().repaint();
        }
    }

    private boolean checkUserInfo(ArrayList<User> list, User user) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserName().equals(user.getUserName()) && list.get(i).getPassWord().equals(user.getPassWord())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
