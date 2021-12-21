package view;

import controller.GameController;
import controller.GameRule;

import javax.swing.*;

import AIPlayer.EasyAI;
import UI.modeInterface;

import java.awt.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame implements KeyListener, MouseListener {
    public static GameController controller;
    private ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;
    public static boolean LOCALMODE;
    public static boolean ONLINEMODE;
    public static boolean VSAIMODE;
    public static int difficulty;
    public static boolean host;
    private GameRule gamerule;
    private EasyAI ai;
    // private static boolean AITime = false;

    public GameFrame(int frameSize, boolean LOCALMODE, boolean ONLINEMODE, boolean VSAIMODE, int difficulty,
            boolean host) {

        this.setTitle("2021F CS102A Project Reversi");
        this.setLayout(null);

        // 获取窗口边框的长度，将这些值加到主窗口大小上，这能使窗口大小和预期相符
        Insets inset = this.getInsets();
        this.setSize(frameSize + inset.left + inset.right, frameSize + inset.top + inset.bottom);

        this.setLocationRelativeTo(null);

        chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7));
        chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2,
                (this.getHeight() - chessBoardPanel.getHeight()) / 3);

        statusPanel = new StatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1));
        statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);

        GameFrame.LOCALMODE = LOCALMODE;
        GameFrame.ONLINEMODE = ONLINEMODE;
        GameFrame.VSAIMODE = VSAIMODE;
        GameFrame.difficulty = difficulty;
        GameFrame.host = host;

        JButton restartBtn = new JButton("AI-GO");
        restartBtn.setSize(120, 50);
        restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2,
                (this.getHeight() + chessBoardPanel.getHeight()) / 2);
        add(restartBtn);
        restartBtn.addKeyListener(this);
        restartBtn.addActionListener(e -> {
            controller.restartGame();
            System.out.println("AI done.");
        });

        JButton loadGameBtn = new JButton("Load");
        loadGameBtn.setSize(120, 50);
        loadGameBtn.setLocation(restartBtn.getX() + restartBtn.getWidth() + 30, restartBtn.getY());
        add(loadGameBtn);
        loadGameBtn.addActionListener(e -> {
            System.out.println("clicked Load Btn");
            String filePath = JOptionPane.showInputDialog(this, "input the path here");
            controller.readFileData(filePath);
        });

        JButton saveGameBtn = new JButton("Save");
        saveGameBtn.setSize(120, 50);
        saveGameBtn.setLocation(loadGameBtn.getX() + restartBtn.getWidth() + 30, restartBtn.getY());
        add(saveGameBtn);
        saveGameBtn.addKeyListener(this);
        saveGameBtn.addActionListener(e -> {
            System.out.println("clicked Save Btn");
            String filePath = JOptionPane.showInputDialog(this, "input the path here");
            controller.writeDataToFile(filePath);
        });

        if (LOCALMODE) {
            controller = new GameController(chessBoardPanel, statusPanel);
            controller.setGamePanel(chessBoardPanel);
        }

        if (ONLINEMODE) {
            controller = new GameController(chessBoardPanel, statusPanel);
            controller.setGamePanel(chessBoardPanel);
            if (!host)
                controller.swapPlayer();
        }

        if (VSAIMODE) {
            controller = new GameController(chessBoardPanel, statusPanel);
            controller.setGamePanel(chessBoardPanel);
            this.addKeyListener(this);
            this.requestFocusInWindow();
        }
        this.add(chessBoardPanel);
        this.add(statusPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        System.out.println("frame set finish.");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        // System.out.println("qwe");
        // dispose();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        // System.out.println(e.getKeyChar());

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        // System.out.println(e.getKeyChar());
        // dispose();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println(e.getKeyChar());
        // dispose();
        if (e.getKeyCode() == 27) {
            dispose();
            // if (ONLINEMODE)
            // Sever.getS;
            new modeInterface("");
        }

    }

}
