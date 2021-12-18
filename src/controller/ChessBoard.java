package controller;

import view.*;
// import components.*;
// import model.*;

public class ChessBoard {
    private static int[][] board = new int[8][8];

    public ChessBoard(){}

    public static int[][] instance(){
        for (int i = 0;i<8;i++)
            for (int j = 0;j<8;j++){
                if(ChessBoardPanel.getChessGrids()[i][j].getChessPiece()==null){
                    board[i][j] = 0;
                    continue;
                }
                switch (ChessBoardPanel.getChessGrids()[i][j].getChessPiece().getType()){
                    case(-1):
                        board[i][j] = -1;
                        break;
                    case(1):
                        board[i][j] = 1;
                        break;
                    default:
                        board[i][j] = 0;
                        break;
                }    
            }
        return board;
    }
}
