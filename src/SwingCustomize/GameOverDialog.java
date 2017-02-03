package SwingCustomize;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverDialog extends JDialog {

    private JButton btnNew;

    public GameOverDialog(int score, boolean top, int topScore) {
        this.setSize(375, 255);
        this.setLocationRelativeTo(this);
        this.setModal(true);
        this.setResizable(false);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(GameOverDialog.class.getResource("/Images/iconLogo.png")));
        
        JPanel mainPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Toolkit.getDefaultToolkit().getImage(GameOverDialog.class.getResource("/Images/pnExit.jpg")), 0, 0, 375, 255, this);
            }
        };
        this.setContentPane(mainPanel);
        this.setTitle("Game Over");
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.red);

        mainPanel.add(Box.createVerticalStrut(10));

        JLabel lbMes = new JLabel("GAME OVER");
        lbMes.setForeground(Color.black);
        lbMes.setFont(new Font("Serif", Font.BOLD, 30));
        lbMes.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        mainPanel.add(lbMes);

        JLabel lbScore = new JLabel("" + score);
        lbScore.setFont(new Font("Jokerman", Font.BOLD, 40));
        lbScore.setForeground(Color.green);
        lbScore.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        mainPanel.add(lbScore);

        String text;
        if (top) {
            text = "New Top Score";
        } else {
            text = "TOP : " + topScore;
        }
        
        JLabel lbTop = new JLabel(text);
        lbTop.setForeground(Color.black);
        lbTop.setFont(new Font("Serif", Font.BOLD, 30));
        lbTop.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        mainPanel.add(lbTop);
        mainPanel.add(Box.createVerticalStrut(10));

        btnNew = new JButton();
        btnNew.setIcon(new ImageIcon(GameOverDialog.class.getResource("/Images/btnClose.png")));
        btnNew.setPressedIcon(new ImageIcon(GameOverDialog.class.getResource("/Images/btnCloseClick.png")));
        btnNew.setContentAreaFilled(false);
        btnNew.setOpaque(false);
        btnNew.setBorderPainted(false);
        btnNew.setBorder(null);
        btnNew.setSize(83, 42);
        btnNew.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        mainPanel.add(btnNew);

    }
}
