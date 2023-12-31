package com.youkenhei.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyJFrame2 extends JFrame implements MouseListener {
    JButton jb = new JButton("按钮1");

    public MyJFrame2() throws HeadlessException {
        JFrame jFrame = new JFrame();
        jFrame.setSize(603, 680);
        jFrame.setTitle("MyJFrame");
        jFrame.setAlwaysOnTop(true);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);


        jb.setBounds(0, 0, 100, 50);

        jb.addMouseListener(this);

        jFrame.getContentPane().add(jb);


        jFrame.setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("单击");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("按下");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("松开");

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("划入");

    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("滑出");

    }
}
