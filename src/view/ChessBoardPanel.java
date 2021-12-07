package view;

import components.ChessGridComponent;
import controller.GameRule;
import model.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class ChessBoardPanel extends JPanel {
    private final int CHESS_COUNT = 8;
    private static ChessGridComponent[][] chessGrids;

    public ChessBoardPanel(int width, int height) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        int length = Math.min(width, height);
        this.setSize(length, length);
        ChessGridComponent.gridSize = length / CHESS_COUNT;
        ChessGridComponent.chessSize = (int) (ChessGridComponent.gridSize * 0.8);
        System.out.printf("width = %d height = %d gridSize = %d chessSize = %d\n",
                width, height, ChessGridComponent.gridSize, ChessGridComponent.chessSize);
        initialChessGrids();//return empty chessboard
        initialGame();//add initial four chess

        // repaint();
    }

    /**
     * set an empty chessboard
     */
    public void initialChessGrids() {
        chessGrids = new ChessGridComponent[CHESS_COUNT][CHESS_COUNT];

        //draw all chess grids
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

    public void redraw(int[][] board){
        for (int i = 0;i<8;i++)
            for(int j = 0;j<8;j++){
                if(board[i][j]==1) chessGrids[i][j].setChessPiece(ChessPiece.WHITE);
                if(board[i][j]==-1) chessGrids[i][j].setChessPiece(ChessPiece.BLACK);
            }
        repaint();
    }

    public static ChessGridComponent[][] getChessGrids(){
        return chessGrids;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public boolean canClickGrid(int row, int col, ChessPiece currentPlayer) {
        //todo: complete this method
        return true;
    }

    public void restartGame() {
        int[] next_step = GameFrame.controller.AI_DO();
                // System.out.println(GameRule.getExpectMatrix());
                // GameFrame.controller.swapPlayer();
                GameRule.updateBoard(next_step[0],next_step[1],ChessPiece.WHITE.getType());
                GameFrame.controller.countScore();
                repaint();
    }
}
