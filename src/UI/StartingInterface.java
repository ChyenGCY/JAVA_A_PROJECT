package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;

import System.GameSystem;
import System.Player;

// for the login and rank

public class StartingInterface extends JFrame implements KeyListener {

    private final class DefaultListCellRendererExtension extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList jlist, Object o, int i, boolean bln, boolean bln1) {
            Component listCellRendererComponent = super.getListCellRendererComponent(jlist, o, i, bln, bln1);
            JLabel label = (JLabel) listCellRendererComponent;
            label.setOpaque(false);
            return label;
        }
    }

    GameSystem gameSystem;
    Player player;
    musicStuff music = new musicStuff();

    public StartingInterface(GameSystem gameSystem) {
        super("开始界面");
        music.playMusic("./music/inno.wav");
        gameSystem.loadFromFIle();
        this.gameSystem = gameSystem;
        // JLabel l1 = new JLabel("欢迎来到杰哥快乐棋");
        // add(l1);
        // l1.setFont(new Font("楷体", Font.BOLD, 30));
        // l1.setBounds(180, 100, 600, 100);
        ImageIcon start = new ImageIcon("./Picture/start.jpg");// 150,160
        ImageIcon bg = new ImageIcon("./Picture/startBg2.gif");// 800,800

        // ranklist adding

        JLabel rankListLabel = new JLabel("Ranklist：");
        rankListLabel.setForeground(Color.WHITE);
        rankListLabel.setFont(new Font("宋体", Font.BOLD, 25));
        rankListLabel.setBounds(500, 100, 200, 40);
        add(rankListLabel);

        JPanel rankListPanel = new JPanel();
        ArrayList<String> rank_list = gameSystem.getRankList();
        String[] items = AL2Array(rank_list);
        JList rankList = new JList(items);
        extracted(rankList);
        rankList.setForeground(Color.WHITE);
        rankList.setBackground(null);
        rankList.setOpaque(false);
        rankListPanel.add(rankList);
        // rankListPanel.add(rankListLabel);
        // rankListPanel.setSize(400, 800);
        // rankListPanel.setLocation(400, 400);
        rankListPanel.setBounds(450, 200, 300, 400);
        rankListPanel.setBackground(null);
        rankListPanel.setOpaque(false);
        rankList.setFont(new Font("宋体", Font.BOLD, 20));
        add(rankListPanel);

        // account adding

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
        l1.setBounds(0, 0, 800, 800);

        setSize(bg.getIconWidth(), bg.getIconHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300, 100);
        setLayout(null);

        // button functions

        b1.addActionListener(e -> {
            String account_name = accountText.getText();
            String password = "";
            if (!account_name.equals("")) {
                password = JOptionPane.showInputDialog(this, "input the password");
            }
            // System.out.println(account_name);
            if (password != null && !password.equals("")) {
                if (!gameSystem.checkPlayer(account_name)) {
                    player = new Player(account_name, password, "0", "0");
                    gameSystem.addPlayer(player);
                    GameSystem.saveToFiles();
                    new JFrame("account created");
                } else {
                    while (!gameSystem.findPlayer(account_name).getPassWord().equals(password) && (password != null
                            && !password.equals(""))) {
                        password = JOptionPane.showInputDialog(this, "wrong password,input again");
                    }
                    if (password != null && !password.equals("")) {
                        player = gameSystem.findPlayer(account_name);
                        GameSystem.saveToFiles();
                    }
                }
                if (password != null && !password.equals("")) {
                    music.stop();
                    dispose();
                    new modeInterface(player);
                }

            }

        });

        this.addKeyListener(this);
        this.requestFocusInWindow();
        setVisible(true);
    }

    private void extracted(JList rankList) {
        rankList.setCellRenderer(new DefaultListCellRendererExtension());
    }

    private String[] AL2Array(ArrayList<String> rank_list) {
        String[] string = new String[rank_list.size()];
        for (String str : rank_list) {
            string[rank_list.indexOf(str)] = str;
        }
        return string;
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
            music.stop();
            dispose();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
