package view;

import components.ChessGridComponent;
import controller.GameRule;
import model.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class ChessBoardPanel extends JPanel {
    private final int CHESS_COUNT = 8;
    protected static ChessGridComponent[][] chessGrids;

    public ChessBoardPanel(int width, int height) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        // this.setBackground(Color.BLACK);
        int length = Math.min(width, height);
        this.setSize(length, length);
        ChessGridComponent.gridSize = length / CHESS_COUNT;
        ChessGridComponent.chessSize = (int) (ChessGridComponent.gridSize * 0.8);
        System.out.printf("width = %d height = %d gridSize = %d chessSize = %d\n",
                width, height, ChessGridComponent.gridSize, ChessGridComponent.chessSize);
        initialChessGrids();// return empty chessboard
        initialGame();// add initial four chess
        setBackground(null);
        setOpaque(false);
        // MouseHandler handler = new MouseHandler();

        // repaint();
    }

    /**
     * set an empty chessboard
     */
    public void initialChessGrids() {
        chessGrids = new ChessGridComponent[CHESS_COUNT][CHESS_COUNT];

        // draw all chess grids
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                ChessGridComponent gridComponent = new ChessGridComponent(i, j);
                gridComponent.setLocation(j * ChessGridComponent.gridSize, i * ChessGridComponent.gridSize);
                chessGrids[i][j] = gridComponent;
                this.add(chessGrids[i][j]);
            }
        }
    }

    /**
     * initial origin four chess
     */
    public void initialGame() {
        chessGrids[3][3].setChessPiece(ChessPiece.BLACK);
        chessGrids[3][4].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][3].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][4].setChessPiece(ChessPiece.BLACK);
    }

    public void redraw(int[][] board) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 1)
                    chessGrids[i][j].setChessPiece(ChessPiece.WHITE);
                if (board[i][j] == -1)
                    chessGrids[i][j].setChessPiece(ChessPiece.BLACK);
                if (board[i][j] == 0)
                    chessGrids[i][j].setChessPiece(null);
            }
        repaint();
    }

    public static ChessGridComponent[][] getChessGrids() {
        return chessGrids;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        // g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public boolean canClickGrid(int row, int col, ChessPiece currentPlayer) {
        // todo: complete this method
        return true;
    }

    public String AIGame() {

        int[] next_step = GameFrame.controller.AI_DO();
        String str = String.format("1 %d %d 0", next_step[0], next_step[1]);
        // System.out.println(GameRule.getExpectMatrix());
        // GameFrame.controller.swapPlayer();
        GameRule.updateBoard(next_step[0], next_step[1], ChessPiece.WHITE.getType());
        String step = ChessPiece.WHITE.getType() + " " + next_step[0] + " " + next_step[1] + " 0";
        GameFrame.controller.getStep().addstep(step);
        GameFrame.controller.countScore();
        GameFrame.controller.checkWin();
        repaint();
        return str;
    }

    public void performOnline(int[] next_step) {
        // System.out.println(GameRule.getExpectMatrix());
        // GameFrame.controller.swapPlayer();
        GameRule.updateBoard(next_step[0], next_step[1], GameFrame.controller.getCurrentPlayer().getType() * -1);
        GameFrame.controller.countScore();
        GameFrame.controller.checkWin();
        repaint();
    }

    public void restartGame() {
        int[][] fresh_board = new int[8][8];
        fresh_board[3][3] = -1;
        fresh_board[4][4] = -1;
        fresh_board[3][4] = 1;
        fresh_board[4][3] = 1;
        redraw(fresh_board);
        GameFrame.controller.countScore();
        repaint();
    }

    public void setChess(int x, int y, int last_player) {
        if (last_player == 1)
            chessGrids[x][y].setChessPiece(ChessPiece.WHITE);
        if (last_player == -1)
            chessGrids[x][y].setChessPiece(ChessPiece.BLACK);
        repaint();
    }
}
