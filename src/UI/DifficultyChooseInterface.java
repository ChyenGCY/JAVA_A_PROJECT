package UI;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import System.Player;
import view.GameFrame;

public class DifficultyChooseInterface extends JFrame implements KeyListener { // interface for choose difficulty
    public Player player;

    public DifficultyChooseInterface(Player player) {
        super("单人模式");
        this.player = player;
        setLayout(null);
        setVisible(true);
        setLocation(400, 200);
        setSize(400, 500);
        this.addKeyListener(this);
        this.requestFocusInWindow();
        ImageIcon diffBg = new ImageIcon("./Picture/middleBg.png");
        ImageIcon simple = new ImageIcon("./Picture/simple.png");
        ImageIcon middle = new ImageIcon("./Picture/middle.png");
        ImageIcon difficult = new ImageIcon("./Picture/difficult.png");

        JButton simpleButton, middleButton, diffButton;
        simpleButton = new JButton(simple);
        add(simpleButton);
        middleButton = new JButton(middle);
        add(middleButton);
        diffButton = new JButton(difficult);
        add(diffButton);

        simpleButton.setBounds(100, 30, 200, 100);
        middleButton.setBounds(100, 180, 200, 100);
        diffButton.setBounds(100, 330, 200, 100);

        JLabel bg = new JLabel(diffBg);
        add(bg);
        bg.setBounds(0, 0, 400, 500);

        simpleButton.addActionListener(e -> {
            dispose();// 关掉当前窗口
            // new Simple(this.account);//创建一个新窗口
            GameFrame mainFrame = new GameFrame(800, false, false, true, 1, false, player);
            mainFrame.setVisible(true);
        });

        middleButton.addActionListener(e -> {
            dispose();// 关掉当前窗口
            // new Simple(this.account);//创建一个新窗口
            GameFrame mainFrame = new GameFrame(800, false, false, true, 2, false, player);
            mainFrame.setVisible(true);
        });

        diffButton.addActionListener(e -> {
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
