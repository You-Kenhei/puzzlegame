package com.youkenhei.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


/**
 * GameJFrame是游的主界面
 * <br>
 * 跟游戏相关的所有逻辑都写在这个类当中
 */
public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int[][] data = new int[4][4];
    int x = 0;
    int y = 0;
    String path = "puzzlegame/image/animal/animal3/";
    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    int step = 0;
    // 创建条目
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("公众号");

    public GameJFrame() {
        initJFrame();

        initJMenuBar();

        initData();

        initImage();

        this.setVisible(true);
    }


    private void initData() {
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();

        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tempArr[i];
        }
    }

    /**
     * 初始化图片
     * <br>
     * 创建ImageIcon，创建JLabel，将ImageIcon添加至JLabel中，再添加至窗口中
     */
    private void initImage() {
        this.getContentPane().removeAll(); // 清空隐藏容器中的所有内容

        if (victory()) {
            JLabel winJLabel = new JLabel(new ImageIcon("puzzlegame/image/win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }
        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);

        for (int i = 0; i < 4; i++) {
            int y = i * 105;
            for (int j = 0; j < 4; j++) {
                int x = j * 105;
                // 创建一个图片ImageIcon对象,创建一个JLabel对象
                JLabel jLabel = new JLabel(new ImageIcon(path + data[i][j] + ".jpg"));

                // 指定图片位置
                jLabel.setBounds(x + 84, y + 134, 105, 105);

                // 给图片添加边框
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));

                // 向窗口中的隐藏容器添加jLabel对象
                this.getContentPane().add(jLabel);
            }
        }

        JLabel backRound = new JLabel(new ImageIcon("puzzlegame/image/background.png"));
        backRound.setBounds(40, 40, 508, 560);
        this.getContentPane().add(backRound);

        this.getContentPane().repaint();
    }

    /**
     * 初始化菜单
     */
    private void initJMenuBar() {
        // 初始化菜单条
        JMenuBar jmenuBar = new JMenuBar(); // 创建菜单条

        // 创建两个选项对象
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        JMenu changePic = new JMenu("更改图片");

        // 向选项中添加条目
        functionJMenu.add(changePic);
        changePic.add(girl);
        changePic.add(animal);
        changePic.add(sport);

        functionJMenu.add(replayItem); // 重开游戏
        functionJMenu.add(reLoginItem); // 重新登录
        functionJMenu.add(closeItem); // 关闭游戏

        aboutJMenu.add(accountItem); // 公众号

        // 给条目Item绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);

        // 向菜单条中添加选项
        jmenuBar.add(functionJMenu);
        jmenuBar.add(aboutJMenu);

        // 向窗口添加菜单条
        this.setJMenuBar(jmenuBar);
    }

    /**
     * 初始化窗口
     */
    private void initJFrame() {
        this.setSize(588, 680);
        this.setTitle("拼图单机版 V1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null); // 设置窗口位置于屏幕中心
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 设置默认的关闭模式
        this.setLayout(null); // 取消默认的居中放置，使组件（此处为JLabel）可以按设定放置
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            this.getContentPane().removeAll();

            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(84, 134, 420, 420);
            this.getContentPane().add(all);

            JLabel backRound = new JLabel(new ImageIcon("puzzlegame/image/background.png"));
            backRound.setBounds(40, 40, 508, 560);
            this.getContentPane().add(backRound);

            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 判断当前游戏是否胜利，如若胜利应当停止执行移动代码
        if (victory()) {
            return;
        }

        int code = e.getKeyCode();
        if (code == 37 && y < 3) {
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            step++;
            initImage();
        } else if (code == 38 && x < 3) {
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            step++;
            initImage();
        } else if (code == 39 && y > 0) {
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            step++;
            initImage();
        } else if (code == 40 && x > 0) {
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            step++;
            initImage();
        } else if (code == 65) {
            initImage();
        } else if (code == 87) {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }
    }

    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == replayItem) {
            System.out.println("重新游戏");
            initData();
            step = 0;
            initImage();

        } else if (obj == reLoginItem) {
            System.out.println("重新登录");
            this.setVisible(false);
            new LoginJFrame();

        } else if (obj == closeItem) {
            System.out.println("关闭游戏");
            System.exit(0);

        } else if (obj == accountItem) {
            System.out.println("公众号");
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("puzzlegame/image/about.png"));
            jLabel.setBounds(0, 0, 258, 258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344, 344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true); // 弹窗不关闭则无法操作其下面的界面
            jDialog.setVisible(true);
        } else if (obj == girl) {
            System.out.println("美女");
            Random r = new Random();
            path = "puzzlegame/image/girl/girl"+(r.nextInt(13) + 1)+"/";
            initData();
            initImage();

        } else if (obj == animal) {
            System.out.println("动物");
            Random r = new Random();
            path = "puzzlegame/image/animal/animal"+(r.nextInt(13) + 1)+"/";
            initData();
            initImage();

        } else if (obj == sport) {
            System.out.println("运动");
            Random r = new Random();
            path = "puzzlegame/image/sport/sport"+(r.nextInt(13) + 1)+"/";
            initData();
            initImage();
        }
    }
}
