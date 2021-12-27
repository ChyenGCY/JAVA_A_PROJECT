package UI;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WinnerFrame extends JFrame implements KeyListener {
    public WinnerFrame(String string) {
        super(string);
        JLabel l1 = new JLabel(string + "win！");
        add(l1);
        l1.setFont(new Font("楷体", Font.BOLD, 30));
        l1.setBounds(180, 100, 50, 50);
        setSize(300, 300);
        this.addKeyListener(this);
        this.requestFocusInWindow();
        setVisible(true);
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
