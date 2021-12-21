package controller;

import model.ChessPiece;
import view.*;
import controller.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import AIPlayer.EasyAI;


public class GameController {


    private ChessBoardPanel gamePanel;
    private StatusPanel statusPanel;
    private ChessPiece currentPlayer;
    private int blackScore;
    private int whiteScore;
    private GameRule gameRule;
    private EasyAI easyAI;
    public int[] this_step;

    public GameController(ChessBoardPanel gamePanel, StatusPanel statusPanel) {
        this.gamePanel = gamePanel;
        this.statusPanel = statusPanel;
        this.currentPlayer = ChessPiece.BLACK;
        blackScore = 2;
        whiteScore = 2;
        gameRule = new GameRule(gamePanel);
        this.easyAI = new EasyAI(ChessPiece.WHITE);
        this.this_step = new int[3];
    }

    public GameRule getGameRule(){
        return gameRule;
    }

    public void swapPlayer() {
        countScore();
        currentPlayer = (currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
        statusPanel.setPlayerText(currentPlayer.name());
        statusPanel.setScoreText(blackScore, whiteScore);
    }

    public int[] this_step(){
        int[] new_step = new int[3];
        for (int i = 0 ;i<3;i++)
            new_step[i] = this_step[i];
        this_step[0] = 0;
        return new_step;
    }

    public int[] getThis_step(){
        return this_step;
    }

    public void countScore() {
        // //todo: modify the countScore method
        // if (currentPlayer == ChessPiece.BLACK) {
        //     blackScore++;
        // } else {
        //     whiteScore++;
        // }
        int black_point = 0;
        int white_point = 0;
        int[][] current_board = ChessBoard.instance();
        for (int i = 0;i<8;i++){
            for (int j = 0;j<8;j++){
                if (current_board[i][j]==1){
                    white_point++;
                }
                else if(current_board[i][j]==-1){
                    black_point++;
                }
            }
        }
        blackScore = black_point;
        whiteScore = white_point;
        statusPanel.setScoreText(blackScore, whiteScore);
    }

    public int[] AI_DO(){
        int[] step = new int[2];
        if (GameFrame.difficulty == 1)
            step = easyAI.AIStep();
        if (GameFrame.difficulty == 2)
            step = easyAI.diff_AIStep();
        
        ChessBoardPanel.getChessGrids()[step[0]][step[1]].setChessPiece(easyAI.getChessPiece());
        return step;
    }

    public ChessPiece getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessBoardPanel getGamePanel() {
        return gamePanel;
    }


    public void setGamePanel(ChessBoardPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public boolean checkWin(){
        // int[][] chessboard = ChessBoard.instance();
        for(int i = 0;i<8;i++)
            for(int j = 0;j<8;j++){
                if (GameRule.isAvailable(i, j)&&GameRule.isEmpty(i,j)){
                    return false;
                }
            }
        System.out.print(checkWinner()+"win!");
        return true;
    }

    private String checkWinner() {
        countScore();
        if (blackScore>whiteScore)
            return "BlackPlayer";
        if (blackScore<whiteScore)
            return "WhitePlayer";
        else
            return "Both";
    }


    public void readFileData(String fileName) {
        //todo: read date from file
        List<String> fileData = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileData.add(line);
            }
            fileData.forEach(System.out::println);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDataToFile(String fileName) {
        //todo: write data into file
    }

    public boolean canClick(int row, int col) {
        return gamePanel.canClickGrid(row, col, currentPlayer);
    }

    public void restartGame() {
        gamePanel.restartGame();
    }

    public int getStep() {
        return 1;
    }

    public void performOnline(String str) {
        int[] step = new int[2];
        step[0] = Integer.valueOf(str.substring(4,5));
        step[1] = Integer.valueOf(str.substring(7,8));
        gamePanel.performOnline(step);
    }
}
