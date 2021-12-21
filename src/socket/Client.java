package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

import view.GameFrame;

public class Client {
    BufferedReader br;
    BufferedWriter bw;
    Socket s;

    public Client(String iP) throws IOException {
        this.s = new Socket(iP, 8888);
        Scanner sc = new Scanner(System.in);

        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();

        this.bw = new BufferedWriter(new OutputStreamWriter(os));

        bw.write("testing the communication, both will receive the message\n");
        bw.flush();
        this.br = new BufferedReader(new InputStreamReader(is));
        String mess = br.readLine();
        System.out.println("sever：" + mess);
        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
        new Thread(new Client_listen(s, ois)).start();
        new Thread(new Client_send(s, oos)).start();
        // while (true) {
        // String str = sc.nextLine();
        // bw.write(str);
        // bw.write("\n");
        // bw.flush();
        // }

    }

    public void send(String str) throws IOException {
        bw.write(str);
        bw.flush();
    }

    public String receive() throws IOException {
        return br.readLine();
    }
}

class Client_listen implements Runnable {
    private Socket socket;
    private ObjectInputStream ois;

    Client_listen(Socket socket, ObjectInputStream ois) {
        this.socket = socket;
        this.ois = ois;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String str = (String) ois.readObject();
                System.out.println(str);
                GameFrame.controller.performOnline(str);
                GameFrame.controller.countScore();
                GameFrame.controller.checkWin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Client_send implements Runnable {
    private Socket socket;
    private ObjectOutputStream oos;

    Client_send(Socket socket, ObjectOutputStream oos) {
        this.socket = socket;
        this.oos = oos;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            GameFrame frame = new GameFrame(800, false, true, false, 0, false);
            frame.setVisible(true);
            System.out.println(100);
            while (true) {
                System.out.print(""); ////// very important
                if (GameFrame.controller.getThis_step()[0] != 0) {
                    int[] step = GameFrame.controller.this_step();
                    // Thread.sleep(2000);
                    oos.writeObject(Arrays.toString(step));
                    // oos.writeObject("\n");
                    oos.flush();
                }
            }
            // }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }
}