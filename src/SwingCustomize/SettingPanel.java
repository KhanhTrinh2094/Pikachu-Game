package SwingCustomize;

import Source.Settings;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettingPanel extends JDialog {

    JButton btnMusic;
    public SettingPanel(boolean Music) {
        this.setSize(400, 220);
        this.setLocationRelativeTo(this);
        this.setModal(true);
        this.setResizable(false);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(GameOverDialog.class.getResource("/Images/iconLogo.png")));

        JPanel mainPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Toolkit.getDefaultToolkit().getImage(GameOverDialog.class.getResource("/Images/pnExit.jpg")), 0, 0, 500, 220, this);
            }
        };
        mainPanel.setLayout(null);
        mainPanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                setVisible(false);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }
        });

        this.setContentPane(mainPanel);
        this.setTitle("Settings");

        JLabel lbScore = new JLabel("Settings");
        lbScore.setFont(new Font("Jokerman", Font.BOLD, 40));
        lbScore.setForeground(Color.black);
        lbScore.setBounds(110, 20, 180, 55);
        mainPanel.add(lbScore);


        JLabel lbMusic = new JLabel("Music : ");
        lbMusic.setFont(new Font("Serif", Font.BOLD, 30));
        lbMusic.setForeground(Color.black);
        lbMusic.setBounds(40, 100, 180, 55);
        mainPanel.add(lbMusic);
        
        btnMusic = new JButton();
        String statusMusic;
        if (Music) {
            statusMusic = "Music";
        } else {
            statusMusic = "Mute";
        }
        
        btnMusic.setIcon(new ImageIcon(SettingPanel.class.getResource("/Images/mn" + statusMusic + ".png")));
        btnMusic.setPressedIcon(new ImageIcon(SettingPanel.class.getResource("/Images/mn" + statusMusic + "Click.png")));
        btnMusic.setSize(146, 52);
        btnMusic.setBounds(250, 102, 94, 51);
        btnMusic.setOpaque(false);
        btnMusic.setBorderPainted(false);
        btnMusic.setContentAreaFilled(false);
        btnMusic.setBorder(null);
        btnMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Settings.Music) {
                    btnMusic.setIcon(new ImageIcon(SettingPanel.class.getResource("/Images/mnMute.png")));
                    btnMusic.setPressedIcon(new ImageIcon(SettingPanel.class.getResource("/Images/mnMuteClick.png")));
                    Settings.Music = false;
                } else {
                    btnMusic.setIcon(new ImageIcon(SettingPanel.class.getResource("/Images/mnMusic.png")));
                    btnMusic.setPressedIcon(new ImageIcon(SettingPanel.class.getResource("/Images/mnMusicClick.png")));
                    Settings.Music = true;
                }
                try {
                    FileOutputStream fos = new FileOutputStream("tmp_Third.tmp");
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    osw.write(String.valueOf(Settings.Music));
                    osw.close();
                    fos.close();
                } catch (FileNotFoundException ex) {
                } catch (IOException ex) {
                }
            }
        });
        mainPanel.add(btnMusic);
        
        JLabel lbContinue = new JLabel("Tap To Continue");
        lbContinue.setFont(new Font("Serif", Font.BOLD, 30));
        lbContinue.setForeground(Color.green);
        mainPanel.add(lbContinue);
    }
}
