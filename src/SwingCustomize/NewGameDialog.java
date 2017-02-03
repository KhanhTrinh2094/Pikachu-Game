package SwingCustomize;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NewGameDialog extends JDialog {

    public JButton btnChicken, btnNormal, btnSuperMan;
    
    public NewGameDialog() {
        this.setSize(467, 200);
        this.setLocationRelativeTo(this);
        this.setModal(true);
        this.setResizable(false);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(NewGameDialog.class.getResource("/Images/iconLogo.png")));
        JPanel mainPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Toolkit.getDefaultToolkit().getImage(NewGameDialog.class.getResource("/Images/pnExit.jpg")), 0, 0, 467, 200, this);
            }
        };
        this.setContentPane(mainPanel);
        this.setTitle("Select Your Game");
        mainPanel.setLayout(null);
        mainPanel.add(Box.createVerticalStrut(20));

        JLabel lbMes = new JLabel("Choose Difficulty");
        lbMes.setForeground(Color.BLACK);
        lbMes.setFont(new Font("Serif", Font.BOLD, 30));
        lbMes.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        lbMes.setBounds(110, 5, 300, 80);
        mainPanel.add(lbMes);

        mainPanel.add(Box.createVerticalStrut(20));

        btnChicken = new JButton();
        btnChicken.setIcon(new ImageIcon(NewGameDialog.class.getResource("/Images/btnChicken.png")));
        btnChicken.setPressedIcon(new ImageIcon(NewGameDialog.class.getResource("/Images/btnChickenClick.png")));
        btnChicken.setContentAreaFilled(false);
        btnChicken.setOpaque(false);
        btnChicken.setBorderPainted(false);
        btnChicken.setSize(130, 44);
        btnChicken.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        btnChicken.setBounds(20, 90, 130, 44);
        mainPanel.add(btnChicken);
        
        btnNormal = new JButton();
        btnNormal.setIcon(new ImageIcon(NewGameDialog.class.getResource("/Images/btnNormal.png")));
        btnNormal.setPressedIcon(new ImageIcon(NewGameDialog.class.getResource("/Images/btnNormalClick.png")));
        btnNormal.setContentAreaFilled(false);
        btnNormal.setOpaque(false);
        btnNormal.setBorderPainted(false);
        btnNormal.setSize(130, 44);
        btnNormal.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        btnNormal.setBounds(160, 90, 130, 44);
        mainPanel.add(btnNormal);
        
        btnSuperMan = new JButton();
        btnSuperMan.setIcon(new ImageIcon(NewGameDialog.class.getResource("/Images/btnSuperMan.png")));
        btnSuperMan.setPressedIcon(new ImageIcon(NewGameDialog.class.getResource("/Images/btnSuperManClick.png")));
        btnSuperMan.setContentAreaFilled(false);
        btnSuperMan.setOpaque(false);
        btnSuperMan.setBorderPainted(false);
        btnSuperMan.setSize(280, 44);
        btnSuperMan.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        btnSuperMan.setBounds(300, 90, 130, 44);
        mainPanel.add(btnSuperMan);
    }
}
