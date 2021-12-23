package UI;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import System.Player;
import view.GameFrame;

public class DifficultyChooseInterface extends JFrame implements KeyListener {
    public Player player;

    public DifficultyChooseInterface(Player player) {
        super("单人模式");
        this.player = player;
        setLayout(null);
        setVisible(true);
        setLocation(400, 200);
        setSize(500, 500);
        this.addKeyListener(this);
        this.requestFocusInWindow();
        JLabel l1 = new JLabel("请选择您想挑战的难度：");
        add(l1);
        l1.setBounds(150, 100, 400, 50);
        l1.setFont(new Font("宋体", Font.BOLD, 20));

        JButton b1, b2, b3;
        b1 = new JButton("Easy Mode");
        add(b1);
        b2 = new JButton("Middle Mode");
        add(b2);
        b3 = new JButton("Hard MOde");
        add(b3);
        b1.setBounds(150, 100, 200, 50);
        b2.setBounds(150, 200, 200, 50);
        b3.setBounds(150, 300, 200, 50);

        b1.addActionListener(e -> {
            dispose();// 关掉当前窗口
            // new Simple(this.account);//创建一个新窗口
            GameFrame mainFrame = new GameFrame(800, false, false, true, 1, false, player);
            mainFrame.setVisible(true);
        });

        b2.addActionListener(e -> {
            dispose();// 关掉当前窗口
            // new Simple(this.account);//创建一个新窗口
            GameFrame mainFrame = new GameFrame(800, false, false, true, 2, false, player);
            mainFrame.setVisible(true);
        });

        b3.addActionListener(e -> {
            dispose();// 关掉当前窗口
            // new Simple(this.account);//创建一个新窗口
            GameFrame mainFrame = new GameFrame(800, false, false, true, 3, false, player);
            mainFrame.setVisible(true);
        });
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
            new modeInterface(player);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
