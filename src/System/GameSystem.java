package System;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GameSystem {
    private static ArrayList<Player> playerList = new ArrayList<>();
    public static GameSystem gameSystem;

    public GameSystem() {
        // // read from file
        // this.gameSystem = gameSystem;
        // this.playerList = new ArrayList<>();
        // loadFromFIle();
        // new StartingInterface(this);
    }

    public void loadFromFIle() {
        ArrayList<String> fileData = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("savings/gameSystem.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileData.add(line);
            }
            // fileData.forEach(System.out::println);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String str : fileData) {
            String[] player_str = str.trim().split(" ");
            Player player = new Player(player_str[0], player_str[1], player_str[2], player_str[3]);
            playerList.add(player);
        }
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public Player findPlayer(int pid) {
        for (int i = 0; i < playerList.size(); i++)
            if (playerList.get(i).getPid() == pid)
                return playerList.get(i);
        return null;
    }

    public Player findPlayer(String name) {
        for (int i = 0; i < playerList.size(); i++)
            if (playerList.get(i).getName().equals(name))
                return playerList.get(i);
        return null;
    }

    public boolean checkPlayer(int pid) {
        if (playerList.size() == 0)
            return false;
        for (int i = 0; i < playerList.size(); i++) {
            if (pid == playerList.get(i).getPid())
                return true;
        }
        return false;
    }

    public boolean checkPlayer(String name) {
        if (playerList.size() == 0)
            return false;
        for (int i = 0; i < playerList.size(); i++) {
            if (name.equals(playerList.get(i).getName()))
                return true;
        }
        return false;
    }

    public boolean addPlayer(Player player) {
        if (!checkPlayer(player.getPid())) {
            playerList.add(player);
            return true;
        }
        return false;
    }

    public float calculatePlayerWinRate(int pid) {
        if (findPlayer(pid).getGameparticipated() == 0)
            return 0;
        return findPlayer(pid).getwinRate();
    }

    public static void saveToFiles() {
        try {
            FileWriter fileWriter = new FileWriter("savings/gameSystem.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Player player : playerList) {
                bufferedWriter.write(player.toString());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getRankList() {
        ArrayList<String> rank_list = new ArrayList<>();
        rank_list.add(String.format("%5s   %5s\n", "Name", "WinRate"));
        ArrayList<Player> newPlaylist = playerList;
        Collections.sort(newPlaylist, Player.winRateComparator);
        for (Player player : newPlaylist) {
            rank_list.add(String.format("%5s   %5.2f %%", player.getName(), player.getwinRate() * 100));
        }
        return rank_list;
    }
}
