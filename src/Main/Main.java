package Main;

import Source.Settings;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        Thread readFile = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileInputStream fis = new FileInputStream("tmp_Second.tmp");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    String string = br.readLine();
                    Settings.HighScore = Integer.parseInt(string);
                    isr.close();
                    fis.close();
                } catch (FileNotFoundException ex) {
                    Settings.HighScore = 0;
                } catch (IOException ex) {
                    Settings.HighScore = 0;
                }

                FileInputStream fis;
                try {
                    fis = new FileInputStream("tmp_Third.tmp");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    String string = br.readLine();
                    Settings.Music = Boolean.parseBoolean(string);
                } catch (FileNotFoundException ex) {
                    Settings.Music = true;
                } catch (IOException ex) {
                    Settings.Music = true;
                }
            }
        });
        readFile.start();

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartFrame().setVisible(true);
            }
        });
    }
}
