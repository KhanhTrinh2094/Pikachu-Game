package SwingCustomize;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HighScorePanel extends JDialog {

    public HighScorePanel(int Score) {
        this.setSize(500, 200);
        this.setLocationRelativeTo(this);
        this.setModal(true);
        this.setResizable(false);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(GameOverDialog.class.getResource("/Images/iconLogo.png")));

        JPanel mainPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Toolkit.getDefaultToolkit().getImage(GameOverDialog.class.getResource("/Images/pnExit.jpg")), 0, 0, 500, 200, this);
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
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
        this.setTitle("HighScore");
        mainPanel.add(Box.createVerticalStrut(15));

        JLabel lbScore = new JLabel("HighScore : " + Score);
        lbScore.setFont(new Font("Jokerman", Font.BOLD, 40));
        lbScore.setForeground(Color.black);
        lbScore.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        mainPanel.add(lbScore);
        mainPanel.add(Box.createVerticalStrut(10));

        JLabel lbContinue = new JLabel("Tap To Continue");
        lbContinue.setFont(new Font("Serif", Font.BOLD, 30));
        lbContinue.setForeground(Color.green);
        lbContinue.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));

        mainPanel.add(lbContinue);
    }
}
