package UI;

import javax.swing.*;

import view.GameFrame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartingInterface extends JFrame implements KeyListener {

    public String account;

    public StartingInterface() {
        super("开始界面");
        ImageIcon start = new ImageIcon("D://java_proj//src//Picture//start.jpg");//150,160
        ImageIcon bg = new ImageIcon("D://java_proj//src//Picture//startBg.gif");//386,800

        JLabel l2 = new JLabel("请创建一个您的账户:");
        add(l2);
        l2.setFont(new Font("宋体", Font.PLAIN, 20));
        l2.setForeground(Color.WHITE);
        l2.setBounds(50, 480, 200, 20);

        JButton b1 = new JButton(start);
        add(b1);
        b1.setBounds(130, 580, start.getIconWidth(), start.getIconHeight());

        JTextField accountText = new JTextField();
        add(accountText);
        accountText.setBounds(50, 520, 300, 30);

        JLabel l1 = new JLabel(bg);
        add(l1);
        l1.setBounds(0,0,386,800);

        setSize(bg.getIconWidth(), bg.getIconHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300, 100);
        setLayout(null);
        setVisible(true);

        b1.addActionListener(e -> {
            account = accountText.getText();
            dispose();
            new modeInterface(account);
        });

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
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
