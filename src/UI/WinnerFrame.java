package UI;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WinnerFrame extends JFrame implements KeyListener {// for winner show
    public WinnerFrame(String string) {
        super(string);
        if (string.equals("BlackPlayer")) {
            JLabel player = new JLabel("黑方");
            add(player);
            player.setFont(new Font("楷体", Font.BOLD, 30));
            player.setBounds(60, 100, 200, 50);
            player.setForeground(Color.WHITE);

            JLabel win = new JLabel("获胜!");
            add(win);
            win.setFont(new Font("楷体", Font.BOLD, 30));
            win.setBounds(100, 200, 100, 50);
            win.setForeground(Color.WHITE);
        }

        if (string.equals("WhitePlayer")) {
            JLabel player = new JLabel("白方");
            add(player);
            player.setFont(new Font("楷体", Font.BOLD, 30));
            player.setBounds(60, 100, 200, 50);
            player.setForeground(Color.WHITE);

            JLabel win = new JLabel("获胜!");
            add(win);
            win.setFont(new Font("楷体", Font.BOLD, 30));
            win.setBounds(50, 150, 100, 50);
            win.setForeground(Color.WHITE);
        }
        if (string.equals("Both")) {
            JLabel player = new JLabel("平局");
            add(player);
            player.setFont(new Font("楷体", Font.BOLD, 30));
            player.setBounds(115, 150, 200, 50);
            player.setForeground(Color.WHITE);
        }
        ImageIcon smallBg = new ImageIcon("./Picture/smallBg.png");
        JLabel bg = new JLabel(smallBg);
        add(bg);
        bg.setBounds(0, 0, 300, 400);

        setSize(300, 400);
        setVisible(true);
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
