package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import System.Player;
import view.GameFrame;

public class Sever {
    BufferedReader br;
    BufferedWriter bw;
    Socket s;

    public Sever(Player player) throws IOException {
        try (ServerSocket ss = new ServerSocket(8888)) {
            System.out.println("init server....");
            this.s = ss.accept();
        }
        s.getInetAddress();
        System.out.println("client: " + InetAddress.getLocalHost() + " connected");

        this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String mess = br.readLine();
        System.out.println("client：" + mess);
        this.bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        bw.write(mess + "\n");
        bw.flush();
        // while (true){
        new Thread(new Server_listen(s)).start();
        new Thread(new Server_send(s, player)).start();
        // }

        // String str;

        // while ((str = br.readLine()) != null) {

        // System.out.println(str);
        // }
    }

    public Socket getSocket() {
        return s;
    }

    // public void send(String str) throws IOException{
    // bw.write(str);
    // bw.flush();
    // }

    // public String receive() throws IOException{
    // return br.readLine();
    // }
}

class Server_listen implements Runnable {
    private Socket socket;

    Server_listen(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            while (true) {
                String str = (String) ois.readObject();
                System.out.println(str);
                GameFrame.controller.performOnline(str);
                GameFrame.controller.countScore();
                GameFrame.controller.checkWin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Server_send implements Runnable {
    private Socket socket;
    private Player player;

    Server_send(Socket socket, Player player) {
        this.socket = socket;
        this.player = player;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            GameFrame frame = new GameFrame(800, false, true, false, 0, true, player);

            frame.setVisible(true);
            System.out.println(999);
            // Thread.sleep(1000);
            while (true) {
                int[] this_step = GameFrame.controller.getThis_step();
                System.out.print(""); /////// very important
                if (this_step[0] != 0) {
                    // System.out.println("123");
                    int[] step = GameFrame.controller.this_step();
                    // Thread.sleep(2000);
                    oos.writeObject(Arrays.toString(step));
                    oos.flush();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
