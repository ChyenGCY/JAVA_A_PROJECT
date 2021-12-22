package System;

import java.io.File;

public class Player {
    private int pid;
    private String name;
    private float gameparticipated;
    private float gamewon;
    private String passWord;
    private static int playerCnt = 1;

    public Player(String name, String passWord, String player_str, String player_str2) {
        this.name = name;
        this.passWord = passWord;
        this.pid = playerCnt;
        this.gamewon = 0;
        this.gameparticipated = 0;
        gameparticipated = Integer.parseInt(player_str);
        gamewon = Integer.parseInt(player_str2);
        playerCnt++;
        createPlayerFolder();
    }

    private void createPlayerFolder() {
        File file1 = new File("./savings/" + this.getName());
        if (!file1.exists())
            file1.mkdirs();
    }

    public String getPassWord() {
        return passWord;
    }

    public int getPid() {
        return pid;
    }

    public float getGameparticipated() {
        return gameparticipated;
    }

    public void setGameparticipated(float gameparticipated) {
        this.gameparticipated = gameparticipated;
    }

    public void setGameWon(float gamewon) {
        this.gamewon = gamewon;
    }

    public float getGamewon() {
        return gamewon;
    }

    public String getName() {
        return name;
    }

    public void participateGame() {
        gameparticipated++;
    }

    public void winGame() {
        gamewon++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getPlayerCnt() {
        return playerCnt;
    }

    public float getwinRate() {
        return gamewon / gameparticipated;
    }

    public String toString() {
        return String.format("%s %s %.0f %.0f\n", name, passWord, gameparticipated, gamewon);
    }
}
