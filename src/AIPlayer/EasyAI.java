package AIPlayer;

import controller.*;
import model.ChessPiece;
import view.GameFrame;
import components.*;

public class EasyAI {

    private ChessPiece chessPiece;
    
    public EasyAI(ChessPiece chessPiece){
        this.chessPiece = chessPiece;
    }

    public ChessPiece getChessPiece(){
        return chessPiece;
    }
    
    public int[] AIStep(){
        int max_num = 0;
        int[] step_cordinate = new int[2];
        for(int i = 0;i<8;i++){
            for(int j =0;j<8;j++){
                if(GameRule.getExpectMatrix()[i][j]!=0 && GameRule.getExpectMatrix()[i][j]>max_num){
                    step_cordinate[0] = i;
                    step_cordinate[1] = j;
                    max_num = GameRule.getExpectMatrix()[i][j];
                }
            }
        }
        return step_cordinate;
    }

}
