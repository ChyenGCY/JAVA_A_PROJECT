package controller;

import components.*;
import view.ChessBoardPanel;
import view.GameFrame;

public class GameRule extends BasicComponent{

    final static int[] x_domain = {-1,-1,-1, 1,1,1,0, 0};
    final static int[] y_domain = {-1, 0, 1,-1,0,1,1,-1};
    public static ChessBoardPanel chessBoardPanel;
    // private int[][] chessBoard;

    public GameRule(ChessBoardPanel chessBoardPanel){
        this.chessBoardPanel = chessBoardPanel;
    }

    public static boolean isEmpty(int x,int y){
        if(ChessBoard.instance()[x][y]==0) return true;
        return false;
    }

    public static boolean isAvailable(int x,int y){
        for(int k=0;k<8;k++){
            int new_x = x+x_domain[k];
            int new_y = y+y_domain[k];
            int num = 0;
            while(new_x>=0 && new_y>=0 && new_x<8 && new_y<8 && ChessBoard.instance()[new_x][new_y] == GameFrame.controller.getCurrentPlayer().getType()*-1){
                new_x += x_domain[k];
                new_y += y_domain[k];
                ++num;
            }
            if(num>=1 && new_x>=0 && new_y>=0 && new_x<8 && new_y<8 && ChessBoard.instance()[new_x][new_y] == GameFrame.controller.getCurrentPlayer().getType())
                return true;
        }
        return false;
    }

    public static void updateBoard(int x,int y) {
        int[][] new_board = ChessBoard.instance();
        int last_player = GameFrame.controller.getCurrentPlayer().getType()*-1;
        for(int k=0;k<8;k++){
            int new_x = x+x_domain[k];
            int new_y = y+y_domain[k];
            int num = 0;
            while(new_x>=0 && new_y>=0 && new_x<8 && new_y<8 && new_board[new_x][new_y] == last_player*-1){
                new_x += x_domain[k];
                new_y += y_domain[k];
                ++num;
            }
            if(num>=1 && new_x>=0 && new_y>=0 && new_x<8 && new_y<8 && new_board[new_x][new_y] == last_player)
                for (int i = 0;i<=num;i++){
                    new_board[x+i*x_domain[k]][y+i*y_domain[k]] = last_player;
                }
        }
            
        
        chessBoardPanel.redraw(new_board);
        for (int a = 0;a<8;a++){
            for (int b = 0;b<8;b++){
                System.out.print(new_board[a][b]+" ");
            }
            System.out.println();
        }
    }

    @Override
    public void onMouseClicked() {
        // TODO Auto-generated method stub
        // updateBoard();
    }
}
