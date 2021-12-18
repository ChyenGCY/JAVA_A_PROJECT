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
        GameRule.chessBoardPanel = chessBoardPanel;
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

    public static int numCanFlip(int x,int y){
        int max_flip = 0;
        int[][] new_board = ChessBoard.instance();
        for(int k=0;k<8;k++){
            int new_x = x+x_domain[k];
            int new_y = y+y_domain[k];
            int num = 0;
            while(new_x>=0 && new_y>=0 && new_x<8 && new_y<8 && new_board[new_x][new_y] == -1){
                new_x += x_domain[k];
                new_y += y_domain[k];
                ++num;
            }
            if(num>=1 && new_x>=0 && new_y>=0 && new_x<8 && new_y<8 && new_board[new_x][new_y] == 1)
                max_flip+=num;
        }
        
        // for (int a = 0;a<8;a++){
        //     for (int b = 0;b<8;b++){
        //         System.out.print(new_board[a][b]+" ");
        //     }
        //     System.out.println();
        // }
        // System.out.println(max_flip);
        return max_flip;
    }

    public static int[][] getExpectMatrix(){
        int[][] expect = new int[8][8];
        // int max = 0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(isEmpty(i,j)){
                    expect[i][j] = numCanFlip(i,j);
                }
            }
        }
        // for (int a = 0;a<8;a++){
        //         for (int b = 0;b<8;b++){
        //             System.out.print(expect[a][b]+" ");
        //         }
        //         System.out.println();
        //     }
        return expect;
    }

    public static void updateBoard(int x,int y,int last_player) {
        int[][] new_board = ChessBoard.instance();
        // int last_player = GameFrame.controller.getCurrentPlayer().getType();
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
        
        // System.out.println(numCanFlip(x, y));
        chessBoardPanel.redraw(new_board);

        // for (int a = 0;a<8;a++){
        //     for (int b = 0;b<8;b++){
        //         System.out.print(new_board[a][b]+" ");
        //     }
        //     System.out.println();
        // }
    }

    public static int[][] virtualFlip(int x,int y,int last_player,int[][] map) {
        int[][] new_board = map;
        // int last_player = GameFrame.controller.getCurrentPlayer().getType();
        for(int k=0;k<8;k++){
            int new_x = x+x_domain[k];
            int new_y = y+y_domain[k];
            int num = 0;
            while(new_x>=0 && new_y>=0 && new_x<8 && new_y<8 && new_board[new_x][new_y] == last_player*-1){
                new_x += x_domain[k];
                new_y += y_domain[k];
                ++num;
            }
            if(num>=1 && new_x>=0 && new_y>=0 && new_x<8 && new_y<8 && new_board[new_x][new_y] == last_player){
                for (int i = 0;i<=num;i++){
                    new_board[x+i*x_domain[k]][y+i*y_domain[k]] = last_player;
                }
            }
        }
        return new_board;
    }

    public static int virtual_numCanFlip(int x,int y,int type,int[][] map){
        int max_flip = 0;
        int[][] new_board = map;
        for(int k=0;k<8;k++){
            int new_x = x+x_domain[k];
            int new_y = y+y_domain[k];
            int num = 0;
            while(new_x>=0 && new_y>=0 && new_x<8 && new_y<8 && new_board[new_x][new_y] == -1*type){
                new_x += x_domain[k];
                new_y += y_domain[k];
                ++num;
            }
            if(num>=1 && new_x>=0 && new_y>=0 && new_x<8 && new_y<8 && new_board[new_x][new_y] == type)
                max_flip+=num;
        }
        
        // for (int a = 0;a<8;a++){
        //     for (int b = 0;b<8;b++){
        //         System.out.print(new_board[a][b]+" ");
        //     }
        //     System.out.println();
        // }
        // System.out.println(max_flip);
        return max_flip;
    }

    @Override
    public void onMouseClicked() {
        // TODO Auto-generated method stub
        // updateBoard();
        // System.out.println("hahahha");
    }
}
