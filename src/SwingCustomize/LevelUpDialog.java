package SwingCustomize;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelUpDialog extends JDialog {

    public JButton btnNext = new JButton();
    private int score, bonus;

    public LevelUpDialog(int sc, int bn) {
        this.score = sc;
        this.bonus = bn;
        this.setTitle("Level Complete !!!");
        this.setSize(360, 242);
        this.setLocationRelativeTo(this);
        this.setModal(true);
        this.setResizable(false);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(LevelUpDialog.class.getResource("/Images/iconLogo.png")));
        JPanel mainPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Toolkit.getDefaultToolkit().getImage(LevelUpDialog.class.getResource("/Images/pnExit.jpg")), 0, 0, 375, 300, this);
            }
        };
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.red);

        mainPanel.add(Box.createVerticalStrut(10));

        JLabel lbMes = new JLabel("LEVEL COMPLETED");
        lbMes.setForeground(Color.black);
        lbMes.setFont(new Font("Serif", Font.BOLD, 25));
        lbMes.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        mainPanel.add(lbMes);

        JLabel lbScore = new JLabel("" + score);
        lbScore.setFont(new Font("Jokerman", Font.BOLD, 40));
        lbScore.setForeground(Color.green);
        lbScore.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        mainPanel.add(lbScore);

        JLabel lbBonus = new JLabel("Bonus : " + bonus);
        lbBonus.setForeground(Color.black);
        lbBonus.setFont(new Font("Serif", Font.BOLD, 30));
        lbBonus.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        mainPanel.add(lbBonus);

        mainPanel.add(Box.createVerticalStrut(8));

        btnNext.setIcon(new ImageIcon(LevelUpDialog.class.getResource("/Images/mnNext.png")));
        btnNext.setPressedIcon(new ImageIcon(LevelUpDialog.class.getResource("/Images/mnNextClick.png")));
        btnNext.setBorderPainted(false);
        btnNext.setContentAreaFilled(false);
        btnNext.setOpaque(false);
        btnNext.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        btnNext.setBorder(null);

        mainPanel.add(btnNext);

    }
}
