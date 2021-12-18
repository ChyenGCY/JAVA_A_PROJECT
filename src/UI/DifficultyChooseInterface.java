package UI;

import javax.swing.*;

import view.GameFrame;

import java.awt.*;

public class DifficultyChooseInterface extends JFrame {
    public String account;
    public DifficultyChooseInterface(String account){
        super("单人模式");
        setLayout(null);
        setVisible(true);
        setLocation(400,200);
        setSize(500,500);

        JLabel l1 = new JLabel("请选择您想挑战的难度：");add(l1);
        l1.setBounds(150,100,400,50);
        l1.setFont(new Font("宋体",Font.BOLD,20));

        JButton b1,b2;
        b1 = new JButton("简单模式");add(b1);
        b2 = new JButton("困难模式");add(b2);
        b1.setBounds(150,200,200,50);
        b2.setBounds(150,300,200,50);

        b1.addActionListener(e -> {
            dispose();//关掉当前窗口
            // new Simple(this.account);//创建一个新窗口
            GameFrame mainFrame = new GameFrame(800,false,false,true,1); 
            mainFrame.setVisible(true);
        });

        b2.addActionListener(e -> {
            dispose();//关掉当前窗口
            // new Simple(this.account);//创建一个新窗口
            GameFrame mainFrame = new GameFrame(800,false,false,true,2); 
            mainFrame.setVisible(true);
        });
    }


}
