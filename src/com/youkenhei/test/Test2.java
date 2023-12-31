package com.youkenhei.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test2 {
    public static void main(String[] args) {
        // 窗口的一般初始化
        JFrame jFrame = new JFrame();
        jFrame.setSize(603, 680);
        jFrame.setTitle("事件演示");
        jFrame.setAlwaysOnTop(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);

        // 创建JF按钮
        JButton jb = new JButton("点我啊");

        // 设置按钮的位置和宽高
        jb.setBounds(0, 0, 100, 50);

        // 添加动作监听，参数为接口ActionListener的实现类
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("按钮被点击了");
            }
        });
        jFrame.getContentPane().add(jb); // 想窗口中的隐藏容器添加按钮


        jFrame.setVisible(true);
    }
}
