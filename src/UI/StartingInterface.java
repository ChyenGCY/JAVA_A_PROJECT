package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import System.GameSystem;
import System.Player;

public class StartingInterface extends JFrame implements KeyListener {
    GameSystem gameSystem;
    Player player;

    public StartingInterface(GameSystem gameSystem) {
        super("开始界面");
        gameSystem.loadFromFIle();
        this.gameSystem = gameSystem;
        // JLabel l1 = new JLabel("欢迎来到杰哥快乐棋");
        // add(l1);
        // l1.setFont(new Font("楷体", Font.BOLD, 30));
        // l1.setBounds(180, 100, 600, 100);
        ImageIcon start = new ImageIcon("./Picture/start.jpg");// 150,160
        ImageIcon bg = new ImageIcon("./Picture/startBg.gif");// 386,800

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
        l1.setBounds(0, 0, 386, 800);

        setSize(bg.getIconWidth(), bg.getIconHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300, 100);
        setLayout(null);
        setVisible(true);

        b1.addActionListener(e -> {
            String account_name = accountText.getText();
            // System.out.println(account_name);
            String password = JOptionPane.showInputDialog(this, "input the password");
            if (!gameSystem.checkPlayer(account_name)) {
                player = new Player(account_name, password, "0", "0");
                gameSystem.addPlayer(player);
                GameSystem.saveToFiles();
                new JFrame("account created");
            } else {
                while (!gameSystem.findPlayer(account_name).getPassWord().equals(password)) {
                    password = JOptionPane.showInputDialog(this, "wrong password,input again");
                }
                player = gameSystem.findPlayer(account_name);
                GameSystem.saveToFiles();
            }
            dispose();
            new modeInterface(player);
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
