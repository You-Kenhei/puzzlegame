package com.youkenhei.ui;

import javax.swing.*;

/**
 * RegisterJFrame为注册界面
 * <br>
 * 跟注册相关的逻辑都在其中
 */
public class RegisterJFrame extends JFrame {
    public RegisterJFrame(){
        this.setSize(488, 500);
        this.setTitle("拼图 注册");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 设置默认的关闭模式


        this.setVisible(true);
    }
}
