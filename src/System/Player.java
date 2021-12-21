package System;

public class Player {
    private int pid;
    private String name;
    private float gameparticipated;
    private float gamewon;
    private String passWord;
    private static int playerCnt = 1;

    public Player(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
        this.pid = playerCnt;
        this.gamewon = 0;
        this.gameparticipated = 0;
        playerCnt++;
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
        return String.format("%s %s\n", name, passWord);
    }
}
