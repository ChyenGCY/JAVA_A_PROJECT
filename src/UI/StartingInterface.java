package UI;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class StartingInterface extends JFrame {

    public String account;

    public StartingInterface(){
        super("开始界面");
        JLabel l1 = new JLabel("欢迎来到杰哥快乐棋");add(l1);
        l1.setFont(new Font("楷体",Font.BOLD,30));
        l1.setBounds(180,100,600,100);
        JLabel l2 = new JLabel("请创建一个您的账户:");add(l2);
        l2.setFont(new Font("宋体",Font.PLAIN,15));
        l2.setBounds(250,250,200,20);

        ImageIcon icon = new ImageIcon();//100,100
        JButton b1 = new JButton();add(b1);
        b1.setBounds(240,360,40,40);

        JTextField accountText = new JTextField();add(accountText);
        accountText.setBounds(150,300,300,30);


        setSize(640,640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300,200);
        setLayout(null);
        setVisible(true);

        b1.addActionListener(e -> {
            account = accountText.getText();
            dispose();
            new modeInterface(account);
        });

//        URL cb;
//        File f = new File("D://Childhood Dreams.wav");
//        cb = f.toURL();
//        AudioClip aau;
//        aau = Applet.newAudioClip(cb);
//        aau.play();
//        aau.loop();
    }
}
