package com.youkenhei.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyJFrame extends JFrame implements ActionListener {
    JButton jb1 = new JButton("按钮1");
    JButton jb2 = new JButton("按钮2");

    public MyJFrame() throws HeadlessException {
        JFrame jFrame = new JFrame();
        jFrame.setSize(603, 680);
        jFrame.setTitle("MyJFrame");
        jFrame.setAlwaysOnTop(true);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);


        jb1.setBounds(0, 0, 100, 50);
        jb2.setBounds(100, 0, 100, 50);

        jb1.addActionListener(this);
        jb2.addActionListener(this);

        jFrame.getContentPane().add(jb1);
        jFrame.getContentPane().add(jb2);


        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == jb1) {
            jb1.setSize(200, 200);
        } else if (source == jb2) {
            Random r = new Random();
            jb2.setBounds(r.nextInt(503), r.nextInt(630), 100, 50);
        }
    }
}
