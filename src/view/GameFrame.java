package view;

import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import System.GameSystem;
import System.Player;
import UI.modeInterface;
import controller.GameController;
import model.ChessPiece;

public class GameFrame extends JFrame implements KeyListener, MouseMotionListener {
    public Player player;
    public static GameController controller;
    private static ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;
    public static boolean LOCALMODE;
    public static boolean ONLINEMODE;
    public static boolean VSAIMODE;
    public static int difficulty;
    public static boolean host;
    public static int cheating;
    // private static boolean AITime = false;

    public GameFrame(int frameSize, boolean LOCALMODE, boolean ONLINEMODE, boolean VSAIMODE, int difficulty,
            boolean host, Player player) {

        this.player = player;
        if (!LOCALMODE)
            player.participateGame();
        GameSystem.saveToFiles();
        this.setTitle("2021F CS102A Project Reversi");
        this.setLayout(null);

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
        GameFrame.cheating = 0;

        JButton restartBtn = new JButton("Restart");
        restartBtn.setSize(120, 50);
        restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2,
                (this.getHeight() + chessBoardPanel.getHeight()) / 2);
        add(restartBtn);
        restartBtn.addKeyListener(this);
        restartBtn.addActionListener(e -> {
            if (!LOCALMODE)
                player.participateGame();
            GameSystem.saveToFiles();
            controller.restartGame();
        });

        JButton loadGameBtn = new JButton("Load");
        loadGameBtn.setSize(120, 50);
        loadGameBtn.setLocation(restartBtn.getX() + restartBtn.getWidth() + 20, restartBtn.getY());
        add(loadGameBtn);
        loadGameBtn.addActionListener(e -> {
            System.out.println("clicked Load Btn");
            JFrame frame = new JFrame();
            JFileChooser chooser = new JFileChooser("./savings/" + player.getName());
            System.out.println(chooser.showOpenDialog(frame));
            File file = chooser.getSelectedFile();
            controller.readFileData(file);
            this.requestFocusInWindow();
        });

        JButton saveGameBtn = new JButton("Save");
        saveGameBtn.setSize(120, 50);
        saveGameBtn.setLocation(loadGameBtn.getX() + restartBtn.getWidth() + 20, restartBtn.getY());
        add(saveGameBtn);
        saveGameBtn.addKeyListener(this);
        saveGameBtn.addActionListener(e -> {
            System.out.println("clicked Save Btn");
            try {
                controller.writeDataToFile();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        JButton AIBtn = new JButton("AI-GO");
        AIBtn.setSize(120, 50);
        AIBtn.setLocation(saveGameBtn.getX() + restartBtn.getWidth() + 20, restartBtn.getY());
        add(AIBtn);
        AIBtn.addKeyListener(this);
        AIBtn.addActionListener(e -> {
            if (VSAIMODE) {
                controller.AIGame();
                System.out.println("AI done.");
            }
        });

        if (LOCALMODE) {
            controller = new GameController(chessBoardPanel, statusPanel, player);
            controller.setGamePanel(chessBoardPanel);
        }

        if (ONLINEMODE) {
            controller = new GameController(chessBoardPanel, statusPanel, player);
            controller.setGamePanel(chessBoardPanel);
            if (!host)
                controller.swapPlayer();
        }

        if (VSAIMODE) {
            controller = new GameController(chessBoardPanel, statusPanel, player);
            controller.setGamePanel(chessBoardPanel);
            // this.addKeyListener(this);
            // this.requestFocusInWindow();
        }
        this.add(chessBoardPanel);
        this.add(statusPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.requestFocusInWindow();
        this.addMouseMotionListener(this);
        System.out.println("frame set finish.");
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
            new modeInterface(player);
        }
        if (e.getKeyChar() == 'a') {
            controller.setTurn(ChessPiece.WHITE.name());
            cheating = 1;
        }
        if (e.getKeyChar() == 'd') {
            controller.setTurn(ChessPiece.BLACK.name());
            cheating = -1;
        }
        if (e.getKeyChar() == 's') {
            controller.showTurn();
            cheating = 0;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        // System.out.println(e.getX());
    }

}
