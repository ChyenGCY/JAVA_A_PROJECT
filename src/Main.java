import view.GameFrame;
import UI.*;
import javax.swing.*;

///                       fake                        ///
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            GameFrame mainFrame = new GameFrame(800, true, false, false, 0, false); // LOCALMODE, ONLINEMODE, VSAIMODE
            mainFrame.setVisible(true);
            // new StartingInterface();
        });
    }
}

//djsioajflisajfljsafjisajdio
