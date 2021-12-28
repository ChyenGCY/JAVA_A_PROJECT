package controller;

import view.ChessBoardPanel;
import view.GameFrame;

public class GameRule { // all the game rules

    final static int[] x_domain = { -1, -1, -1, 1, 1, 1, 0, 0 };
    final static int[] y_domain = { -1, 0, 1, -1, 0, 1, 1, -1 };
    public static ChessBoardPanel chessBoardPanel;
    // private int[][] chessBoard;

    public GameRule(ChessBoardPanel chessBoardPanel) {
        GameRule.chessBoardPanel = chessBoardPanel;
    }

    public static boolean isEmpty(int x, int y) {// chech if this is empty
        if (ChessBoard.instance()[x][y] == 0)
            return true;
        return false;
    }

    public static boolean isAvailable(int x, int y) {// chect if it is available for current player
        for (int k = 0; k < 8; k++) {
            int new_x = x + x_domain[k];
            int new_y = y + y_domain[k];
            int num = 0;
            while (new_x >= 0 && new_y >= 0 && new_x < 8 && new_y < 8
                    && ChessBoard.instance()[new_x][new_y] == GameFrame.controller.getCurrentPlayer().getType() * -1) {
                new_x += x_domain[k];
                new_y += y_domain[k];
                ++num;
            }
            if (num >= 1 && new_x >= 0 && new_y >= 0 && new_x < 8 && new_y < 8
                    && ChessBoard.instance()[new_x][new_y] == GameFrame.controller.getCurrentPlayer().getType())
                return true;
        }
        return false;
    }

    public static boolean isStepAvailable(int x, int y, int player) {// chect if it is available for specific player
        for (int k = 0; k < 8; k++) {
            int new_x = x + x_domain[k];
            int new_y = y + y_domain[k];
            int num = 0;
            while (new_x >= 0 && new_y >= 0 && new_x < 8 && new_y < 8
                    && ChessBoard.instance()[new_x][new_y] == player * -1) {
                new_x += x_domain[k];
                new_y += y_domain[k];
                ++num;
            }
            if (num >= 1 && new_x >= 0 && new_y >= 0 && new_x < 8 && new_y < 8
                    && ChessBoard.instance()[new_x][new_y] == player)
                return true;
        }
        return false;
    }

    public static int numCanFlip(int x, int y) {// count the number can flip in a specific chess grid
        int max_flip = 0;
        int[][] new_board = ChessBoard.instance();
        for (int k = 0; k < 8; k++) {
            int new_x = x + x_domain[k];
            int new_y = y + y_domain[k];
            int num = 0;
            while (new_x >= 0 && new_y >= 0 && new_x < 8 && new_y < 8 && new_board[new_x][new_y] == -1) {
                new_x += x_domain[k];
                new_y += y_domain[k];
                ++num;
            }
            if (num >= 1 && new_x >= 0 && new_y >= 0 && new_x < 8 && new_y < 8 && new_board[new_x][new_y] == 1)
                max_flip += num;
        }

        // for (int a = 0;a<8;a++){
        // for (int b = 0;b<8;b++){
        // System.out.print(new_board[a][b]+" ");
        // }
        // System.out.println();
        // }
        // System.out.println(max_flip);
        return max_flip;
    }

    public static int[][] getAvailableMatrix() {// get the chess board if the grid is available mark 1
                                                // and not available mark 0
        int[][] available = new int[8][8];
        // int max = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isEmpty(i, j)) {
                    available[i][j] = isAvailable(i, j) ? 1 : 0;
                }
            }
        }
        // for (int a = 0;a<8;a++){
        // for (int b = 0;b<8;b++){
        // System.out.print(expect[a][b]+" ");
        // }
        // System.out.println();
        // }
        return available;
    }

    public static int[][] getExpectMatrix() {// get the chess board and the each grid corresponds to the number it can
                                             // flip
        int[][] expect = new int[8][8];
        // int max = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isEmpty(i, j)) {
                    expect[i][j] = numCanFlip(i, j);
                }
            }
        }
        // for (int a = 0;a<8;a++){
        // for (int b = 0;b<8;b++){
        // System.out.print(expect[a][b]+" ");
        // }
        // System.out.println();
        // }
        return expect;
    }

    public static void updateBoard(int x, int y, int last_player) { // given the step and player draw the step and flip
        int[][] new_board = ChessBoard.instance();
        // int last_player = GameFrame.controller.getCurrentPlayer().getType();
        for (int k = 0; k < 8; k++) {
            int new_x = x + x_domain[k];
            int new_y = y + y_domain[k];
            int num = 0;
            while (new_x >= 0 && new_y >= 0 && new_x < 8 && new_y < 8 && new_board[new_x][new_y] == last_player * -1) {
                new_x += x_domain[k];
                new_y += y_domain[k];
                ++num;
            }
            if (num >= 1 && new_x >= 0 && new_y >= 0 && new_x < 8 && new_y < 8
                    && new_board[new_x][new_y] == last_player)
                for (int i = 0; i <= num; i++) {
                    new_board[x + i * x_domain[k]][y + i * y_domain[k]] = last_player;
                }
        }

        // System.out.println(numCanFlip(x, y));
        chessBoardPanel.redraw(new_board);
        // for (int a = 0;a<8;a++){
        // for (int b = 0;b<8;b++){
        // System.out.print(new_board[a][b]+" ");
        // }
        // System.out.println();
        // }
    }

    public static int[][] virtualFlip(int x, int y, int last_player, int[][] map) {// virtually given the step and
                                                                                   // player draw the
                                                                                   // step and flip (used in ai and will
                                                                                   // not show in the chessboard)
        int[][] new_board = map;
        // int last_player = GameFrame.controller.getCurrentPlayer().getType();
        for (int k = 0; k < 8; k++) {
            int new_x = x + x_domain[k];
            int new_y = y + y_domain[k];
            int num = 0;
            while (new_x >= 0 && new_y >= 0 && new_x < 8 && new_y < 8 && new_board[new_x][new_y] == last_player * -1) {
                new_x += x_domain[k];
                new_y += y_domain[k];
                ++num;
            }
            if (num >= 1 && new_x >= 0 && new_y >= 0 && new_x < 8 && new_y < 8
                    && new_board[new_x][new_y] == last_player) {
                for (int i = 0; i <= num; i++) {
                    new_board[x + i * x_domain[k]][y + i * y_domain[k]] = last_player;
                }
            }
        }
        return new_board;
    }

    public static int virtual_numCanFlip(int x, int y, int type, int[][] map) {// similiar with the non virtual one, but
                                                                               // for a specific map
                                                                               // (used in ai)
        int max_flip = 0;
        int[][] new_board = map;
        for (int k = 0; k < 8; k++) {
            int new_x = x + x_domain[k];
            int new_y = y + y_domain[k];
            int num = 0;
            while (new_x >= 0 && new_y >= 0 && new_x < 8 && new_y < 8 && new_board[new_x][new_y] == -1 * type) {
                new_x += x_domain[k];
                new_y += y_domain[k];
                ++num;
            }
            if (num >= 1 && new_x >= 0 && new_y >= 0 && new_x < 8 && new_y < 8 && new_board[new_x][new_y] == type)
                max_flip += num;
        }

        // for (int a = 0;a<8;a++){
        // for (int b = 0;b<8;b++){
        // System.out.print(new_board[a][b]+" ");
        // }
        // System.out.println();
        // }
        // System.out.println(max_flip);
        return max_flip;
    }
}
