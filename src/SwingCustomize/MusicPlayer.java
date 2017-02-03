package SwingCustomize;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer extends Thread {

    private String filePath;
    private boolean loop;
    private Player player;

    public MusicPlayer(String filePath, boolean loop) {
        this.filePath = filePath;
        this.loop = loop;
    }

    @Override
    public void run() {
        do {
            InputStream urlAsString = MusicPlayer.class.getResourceAsStream(filePath);
            try {
                this.player = new Player(urlAsString);
                player.play();
            } catch (JavaLayerException ex) {
            }
        } while (loop);
    }

    public void stopThread() {
            try {
                MusicPlayer.sleep(9999);
            } catch (InterruptedException e) {
            }
    }
}
