package SwingCustomize;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class AboutUsPanel extends JDialog {

    public AboutUsPanel() {
        this.setSize(500, 310);
        this.setLocationRelativeTo(this);
        this.setModal(true);
        this.setResizable(false);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(GameOverDialog.class.getResource("/Images/iconLogo.png")));

        JPanel mainPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Toolkit.getDefaultToolkit().getImage(GameOverDialog.class.getResource("/Images/pnExit.jpg")), 0, 0, 500, 310, this);
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
        this.setTitle("About Us");

        JLabel lbScore = new JLabel("About Us");
        lbScore.setFont(new Font("Jokerman", Font.BOLD, 40));
        lbScore.setForeground(Color.RED);
        lbScore.setBounds(135, 20, 220, 50);
        mainPanel.add(lbScore);

        JLabel lbName = new JLabel("Code By : ");
        lbName.setFont(new Font("Serif", Font.BOLD, 25));
        lbName.setForeground(Color.BLACK);
        lbName.setBounds(20, 95, 220, 30);
        mainPanel.add(lbName);
        
        JLabel lbNameValue = new JLabel("Nguyễn Cảnh Khánh Trình");
        lbNameValue.setFont(new Font("Serif", Font.BOLD, 25));
        lbNameValue.setForeground(Color.BLUE);
        lbNameValue.setBounds(180, 95, 320, 30);
        mainPanel.add(lbNameValue);
        
        JLabel lbDesign = new JLabel("Design By : ");
        lbDesign.setFont(new Font("Serif", Font.BOLD, 25));
        lbDesign.setForeground(Color.BLACK);
        lbDesign.setBounds(20, 135, 220, 30);
        mainPanel.add(lbDesign);
        
        JLabel lbDesignValue = new JLabel("Nguyễn Hồng Phong");
        lbDesignValue.setFont(new Font("Serif", Font.BOLD, 25));
        lbDesignValue.setForeground(Color.BLUE);
        lbDesignValue.setBounds(180, 135, 320, 30);
        mainPanel.add(lbDesignValue);
        
        JLabel lbMobile = new JLabel("Mobile : ");
        lbMobile.setFont(new Font("Serif", Font.BOLD, 25));
        lbMobile.setForeground(Color.BLACK);
        lbMobile.setBounds(20, 180, 220, 30);
        mainPanel.add(lbMobile);
        
        JLabel lbMobileValue = new JLabel("01685.000.113");
        lbMobileValue.setFont(new Font("Serif", Font.BOLD, 25));
        lbMobileValue.setForeground(Color.BLUE);
        lbMobileValue.setBounds(180, 180, 320, 30);
        mainPanel.add(lbMobileValue);
        
        JLabel lbEmail = new JLabel("Email : ");
        lbEmail.setFont(new Font("Serif", Font.BOLD, 25));
        lbEmail.setForeground(Color.BLACK);
        lbEmail.setBounds(20, 215, 220, 30);
        mainPanel.add(lbEmail);
        
        JLabel lbEmailValue = new JLabel("4me.vnn@gmail.com");
        lbEmailValue.setFont(new Font("Serif", Font.BOLD, 25));
        lbEmailValue.setForeground(Color.BLUE);
        lbEmailValue.setBounds(180, 215, 320, 30);
        mainPanel.add(lbEmailValue);
    }
}
