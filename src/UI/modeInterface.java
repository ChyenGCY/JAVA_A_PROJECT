package UI;

import javax.swing.*;

import System.GameSystem;
import System.Player;
import view.GameFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class modeInterface extends JFrame implements KeyListener {
    public Player player;

    public modeInterface(Player player) {
        super("模式选择界面");
        this.player = player;
        setSize(800, 800);

        JButton b1 = new JButton("单人模式");
        add(b1);
        JButton b2 = new JButton("联机模式");
        add(b2);
        JButton b3 = new JButton("双人模式");
        add(b3);

        JLabel l1 = new JLabel("杰哥快乐棋");
        add(l1);
        l1.setFont(new Font("宋体", Font.BOLD, 30));
        ImageIcon icon = new ImageIcon();
        JLabel l3 = new JLabel("HELLO," + player);
        add(l3);
        JLabel l2 = new JLabel(icon);
        add(l2);

        // b3.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // dispose();
        // new Simple();
        // }
        //
        // });
        b1.addActionListener(e -> {
            dispose();
            new DifficultyChooseInterface(this.player);
        });

        b2.addActionListener(e -> {
            dispose();
            try {
                new ServerOrClientInterface(this.player);
            } catch (UnknownHostException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        b3.addActionListener(e -> {
            dispose();// 关掉当前窗口
            // new Simple(this.account);//创建一个新窗口
            GameFrame mainFrame = new GameFrame(800, true, false, false, 0, false, player);
            mainFrame.setVisible(true);
        });

        b1.setBounds(220, 100, 200, 40);
        b2.setBounds(220, 300, 200, 40);
        b3.setBounds(220, 500, 200, 40);
        l1.setBounds(270, 50, 300, 40);
        l2.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());// 640,640
        l3.setBounds(10, 10, 500, 20);

        // setSize(icon.getIconWidth(), icon.getIconHeight());//窗口大小
        setLayout(null);// using no layout managers
        setVisible(true);// making the frame visible
        setLocation(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关掉窗口时结束程序
        this.addKeyListener(this);
        this.requestFocusInWindow();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println(e.getKeyChar());
        if (e.getKeyCode() == 27) {
            dispose();
            new StartingInterface(new GameSystem());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
