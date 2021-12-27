package UI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import System.GameSystem;
import System.Player;
import view.GameFrame;

public class modeInterface extends JFrame implements KeyListener {
    public Player player;
    public musicStuff music = new musicStuff();

    public modeInterface(Player player) {
        super("模式选择界面");
        music.playMusic("./music/keli.wav");
        this.player = player;
        ImageIcon he = new ImageIcon("./Picture/he.JPG");
        setSize(600, 600);

        ImageIcon bg = new ImageIcon("./Picture/modernBg.png");
        ImageIcon dan = new ImageIcon("./Picture/dan.png");
        ImageIcon shuang = new ImageIcon("./Picture/shuang.png");
        ImageIcon lian = new ImageIcon("./Picture/lian.png");

        JButton danButton = new JButton(dan);
        add(danButton);
        JButton shuangButton = new JButton(shuang);
        add(shuangButton);
        JButton lianButton = new JButton(lian);
        add(lianButton);

        JLabel hello = new JLabel("你好," + player.getName());
        add(hello);
        hello.setFont(new Font("宋体", Font.BOLD, 25));
        hello.setForeground(Color.WHITE);
        JLabel modernBG = new JLabel(bg);
        add(modernBG);

        // b3.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // dispose();
        // new Simple();
        // }
        //
        // });
        danButton.addActionListener(e -> {
            dispose();
            music.stop();
            new DifficultyChooseInterface(this.player);
        });

        lianButton.addActionListener(e -> {
            dispose();
            music.stop();
            try {
                new ServerOrClientInterface(this.player);
            } catch (UnknownHostException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        shuangButton.addActionListener(e -> {
            dispose();// 关掉当前窗口
            music.stop();
            // new Simple(this.account);//创建一个新窗口
            GameFrame mainFrame = new GameFrame(800, true, false, false, 0, false, player);
            mainFrame.setVisible(true);
        });

        danButton.setBounds(30, 150, 150, 200);
        lianButton.setBounds(220, 150, 150, 200);
        shuangButton.setBounds(410, 150, 150, 200);
        modernBG.setBounds(0, 0, 600, 600);
        hello.setBounds(30, 10, 600, 50);

        // setSize(icon.getIconWidth(), icon.getIconHeight());//窗口大小
        setLayout(null);// using no layout managers
        setVisible(true);// making the frame visible
        setLocation(300, 200);
        JLabel background = new JLabel(he);
        add(background);
        background.setBounds(0, 0, 800, 800);
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
            music.stop();
            new StartingInterface(new GameSystem());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
