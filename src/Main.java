import view.GameFrame;
import UI.*;
import javax.swing.*;



///                       fake                        ///
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // GameFrame mainFrame = new GameFrame(800,false,false,true);       // LOCALMODE, ONLINEMODE, VSAIMODE
            // mainFrame.setVisible(true);
            new StartingInterface();
        });
    }
}
