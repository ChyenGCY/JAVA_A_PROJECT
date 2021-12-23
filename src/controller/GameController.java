package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import AIPlayer.EasyAI;
import System.GameSystem;
import System.Player;
import System.Step;
import UI.WinnerFrame;
import model.ChessPiece;
import view.ChessBoardPanel;
import view.GameFrame;
import view.StatusPanel;

public class GameController {

    private ChessBoardPanel gamePanel;
    private StatusPanel statusPanel;
    private ChessPiece currentPlayer;
    private int blackScore;
    private int whiteScore;
    private GameRule gameRule;
    private EasyAI easyAI;
    public int[] this_step;
    private Step step;
    private Player player;

    public GameController(ChessBoardPanel gamePanel, StatusPanel statusPanel, Player player) {
        this.gamePanel = gamePanel;
        this.statusPanel = statusPanel;
        this.currentPlayer = ChessPiece.BLACK;
        blackScore = 2;
        whiteScore = 2;
        gameRule = new GameRule(gamePanel);
        this.easyAI = new EasyAI(ChessPiece.WHITE);
        this.this_step = new int[3];
        this.step = new Step();
        this.player = player;
    }

    public Step getStep() {
        return step;
    }

    public GameRule getGameRule() {
        return gameRule;
    }

    public void swapPlayer() {
        countScore();
        currentPlayer = (currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
        statusPanel.setPlayerText(currentPlayer.name());
        statusPanel.setScoreText(blackScore, whiteScore);
    }

    public void setTurn(String name) {
        countScore();
        statusPanel.setPlayerText(name);
        statusPanel.setScoreText(blackScore, whiteScore);
    }

    public void showTurn() {
        countScore();
        statusPanel.setPlayerText(currentPlayer.name());
        statusPanel.setScoreText(blackScore, whiteScore);
    }

    public int[] this_step() {
        int[] new_step = new int[3];
        for (int i = 0; i < 3; i++)
            new_step[i] = this_step[i];
        this_step[0] = 0;
        return new_step;
    }

    public int[] getThis_step() {
        return this_step;
    }

    public void countScore() {
        // //todo: modify the countScore method
        // if (currentPlayer == ChessPiece.BLACK) {
        // blackScore++;
        // } else {
        // whiteScore++;
        // }
        int black_point = 0;
        int white_point = 0;
        int[][] current_board = ChessBoard.instance();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (current_board[i][j] == 1) {
                    white_point++;
                } else if (current_board[i][j] == -1) {
                    black_point++;
                }
            }
        }
        blackScore = black_point;
        whiteScore = white_point;
        statusPanel.setScoreText(blackScore, whiteScore);
    }

    public int[] AI_DO() {
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

    public boolean checkWin() {
        // int[][] chessboard = ChessBoard.instance();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (GameRule.isAvailable(i, j) && GameRule.isEmpty(i, j)) {
                    GameSystem.saveToFiles();
                    return false;
                }
            }
        if (GameFrame.VSAIMODE) {
            player.winGame();
            GameSystem.saveToFiles();
        }
        if (GameFrame.ONLINEMODE) {
            if (GameFrame.host && checkWinner().equals("BlackPlayer")) {

                player.winGame();
                GameSystem.saveToFiles();
            }
            if (!GameFrame.host && checkWinner().equals("WhitePlayer")) {

                player.winGame();
                GameSystem.saveToFiles();
            }
        }
        new WinnerFrame(checkWinner());
        System.out.print(checkWinner() + "win!");
        return true;
    }

    private String checkWinner() {
        countScore();
        if (blackScore > whiteScore)
            return "BlackPlayer";
        if (blackScore < whiteScore)
            return "WhitePlayer";
        else
            return "Both";
    }

    public void readFileData(File file) {
        // todo: read date from file
        ArrayList<String> fileData = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileData.add(line);
            }
            // fileData.forEach(System.out::println);
            bufferedReader.close();
            reloadSteps(fileData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reloadSteps(ArrayList<String> fileData) {
        restartGame();
        step = new Step();
        ArrayList<String> sp = new ArrayList<String>();
        for (String str : fileData) {
            String[] step = str.trim().split(" ");
            int last_player = Integer.parseInt(step[0]);
            int x = Integer.parseInt(step[1]);
            int y = Integer.parseInt(step[2]);
            GameRule.updateBoard(x, y, last_player);
            sp.add(str.trim());
        }
        step.setSteps(sp);
        countScore();
    }

    public void writeDataToFile() throws IOException {
        // todo: write data into file
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH_mm_ss z");
        Date date = new Date(System.currentTimeMillis());
        String dir = "./savings/" + player.getName() + "/step_file" + formatter.format(date) + ".txt";
        File file = new File(dir);
        File file1 = new File("./savings/" + player.getName());
        if (!file1.exists())
            file1.mkdirs();
        // file.mkdirs();
        if (!file.exists())
            file.createNewFile();
        FileWriter writer = new FileWriter(file);
        for (String str : step.getSteps()) {
            writer.write(str + "\n");
            writer.flush();
        }
        writer.close();
    }

    public boolean canClick(int row, int col) {
        return gamePanel.canClickGrid(row, col, currentPlayer);
    }

    public void restartGame() {
        gamePanel.restartGame();
    }

    public void performOnline(String str) {
        int[] step = new int[2];
        step[0] = Integer.valueOf(str.substring(4, 5));
        step[1] = Integer.valueOf(str.substring(7, 8));
        gamePanel.performOnline(step);
    }

    public void AIGame() {
        gamePanel.AIGame();
    }

    public void withdrawLastStep() {
        boolean canWithdraw = step.removeLastStep();
        if (canWithdraw) {
            restartGame();
            for (String str : step.getSteps()) {
                String[] step = str.trim().split(" ");
                int last_player = Integer.parseInt(step[0]);
                int x = Integer.parseInt(step[1]);
                int y = Integer.parseInt(step[2]);
                GameRule.updateBoard(x, y, last_player);
            }
            swapPlayer();
        }
        countScore();
    }
}
