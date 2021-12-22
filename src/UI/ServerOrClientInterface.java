package UI;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import System.Player;
import socket.Client;
import socket.Sever;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ServerOrClientInterface extends JFrame implements KeyListener {
    public Player player;

    public ServerOrClientInterface(Player player) throws UnknownHostException {
        super("Server Or Client");
        this.player = player;
        setSize(640, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300, 200);
        setLayout(null);
        setVisible(true);
        this.addKeyListener(this);
        this.requestFocusInWindow();
        JButton serverButton = new JButton("Server");
        add(serverButton);
        JButton clientButton = new JButton("Client");
        add(clientButton);
        serverButton.setBounds(220, 100, 200, 40);
        clientButton.setBounds(220, 300, 200, 40);

        JLabel IPLabel = new JLabel();
        add(IPLabel);
        IPLabel.setText("Your IP :" + InetAddress.getLocalHost());
        IPLabel.setBounds(250, 250, 400, 200);
        // IPLabel.setFont(new Font())

        serverButton.addActionListener(e -> {
            dispose();
            // try {
            // new IPInterface(account);
            // } catch (UnknownHostException e2) {
            // // TODO Auto-generated catch block
            // e2.printStackTrace();
            // }
            try {
                new Sever(player);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        clientButton.addActionListener(e -> {
            String IP = JOptionPane.showInputDialog(this, "input the IP here");
            dispose();
            // try {
            // new IPInterface(account);
            // } catch (UnknownHostException e2) {
            // // TODO Auto-generated catch block
            // e2.printStackTrace();
            // }
            try {
                new Client(IP, player);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
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
