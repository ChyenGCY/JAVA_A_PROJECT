package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

// import controller.GameController;
import controller.GameRule;
import model.ChessPiece;
import view.GameFrame;

public class ChessGridComponent extends BasicComponent {
    public static int chessSize;
    public static int gridSize;
    public static Color gridColor = new Color(255, 150, 50);

    private ChessPiece chessPiece;
    private int row;
    private int col;

    public ChessGridComponent(int row, int col) {
        this.setSize(gridSize, gridSize);

        this.row = row;
        this.col = col;
    }

    @Override
    public void onMouseClicked() {
        System.out.printf("%s clicked (%d, %d)\n", GameFrame.controller.getCurrentPlayer(), row, col);
        // todo: complete mouse click method
        if (GameFrame.cheating == 0) {
            if (((this.chessPiece == null) || (this.chessPiece == ChessPiece.GRAY))
                    && GameRule.isAvailable(row, col)) {
                this.chessPiece = GameFrame.controller.getCurrentPlayer();
                GameFrame.controller.countScore();
                GameRule.updateBoard(row, col, this.chessPiece.getType());
                repaint();
                GameFrame.controller.this_step[0] = 1;
                GameFrame.controller.this_step[1] = row;
                GameFrame.controller.this_step[2] = col;
                String step = GameFrame.controller.getCurrentPlayer().getType() + " " + row + " " + col;
                GameFrame.controller.getStep().addstep(step);
                GameFrame.controller.countScore();
                // GameFrame.controller.checkWin();
                if (GameFrame.LOCALMODE)
                    GameFrame.controller.swapPlayer();
                // GameFrame.controller.restartGame();
            } else
                System.out.println("invalid click");
        } else if (GameFrame.cheating == 1) {
            this.chessPiece = ChessPiece.WHITE;
            GameRule.updateBoard(row, col, this.chessPiece.getType());
            GameFrame.controller.countScore();
        } else if (GameFrame.cheating == -1) {
            this.chessPiece = ChessPiece.BLACK;
            GameRule.updateBoard(row, col, this.chessPiece.getType());
            GameFrame.controller.countScore();
        }
        GameFrame.controller.checkWin();
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void drawPiece(Graphics g) {
        g.setColor(gridColor);
        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        if (this.chessPiece != null) {
            g.setColor(chessPiece.getColor());
            g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
        }
    }

    public void drawShit(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval((gridSize) / 2 - 4, (gridSize) / 2 - 4, chessSize / 5, chessSize / 5);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        drawPiece(g);
        if (GameRule.getAvailableMatrix()[row][col] != 0)
            drawShit(g);
    }

    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        String s = "当前鼠标坐标:(" + x + ", " + y + ")";
        System.out.println(s);
    }

    @Override
    protected void onMouseEntered() {
        // TODO Auto-generated method stub
        if (((this.chessPiece == null) || (this.chessPiece == ChessPiece.GRAY)) && GameRule.isAvailable(row, col)) {
            this.chessPiece = ChessPiece.GRAY;
            repaint();
        }
    }

    @Override
    protected void onMouseExited() {
        // TODO Auto-generated method stub
        if (((this.chessPiece == null) || (this.chessPiece == ChessPiece.GRAY)) && GameRule.isAvailable(row, col)) {
            this.chessPiece = null;
            repaint();
        }
    }
}
