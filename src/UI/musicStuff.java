package UI;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class musicStuff {
    AudioInputStream audioInput;
    Clip clip;

    public void playMusic(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);

            if (musicPath.exists()) {
                audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void stop() {
        clip.stop();
        clip.close();
    }

    public static void main(String[] args) {
        musicStuff music = new musicStuff();
        music.playMusic("./music/keli.wav");
        music.stop();
    }
}