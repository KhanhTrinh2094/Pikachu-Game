package SwingCustomize;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExitDialog extends JDialog {

    public JButton btnOk, btnCancel;
    private boolean isGame = false;
    private String strText;

    public ExitDialog(boolean is) {
        this.setLayout(null);
        this.setModal(true);
        this.setSize(475, 180);
        this.isGame = is;
        this.setTitle("Back To Menu ?");
        this.setResizable(false);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(ExitDialog.class.getResource("/Images/iconLogo.png")));
        JPanel mainPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Toolkit.getDefaultToolkit().getImage(ExitDialog.class.getResource("/Images/pnExit.jpg")), 0, 0, 475, 180, this);
            }
        };
        mainPanel.setLayout(null);

        JLabel lbText = new JLabel("(Your game will be saved automaticaly)");
        lbText.setForeground(Color.magenta);
        lbText.setFont(new Font("Serif", Font.BOLD, 25));
        lbText.setBounds(24, 42, 420, 30);
        if (isGame) {
            strText = "Are you want back to menu ?";
            mainPanel.add(lbText);
        } else {
            strText = "Are you want exit the game ?";
        }
        
        JLabel lbMes = new JLabel(strText);
        lbMes.setForeground(Color.red);
        lbMes.setFont(new Font("Serif", Font.BOLD, 25));
        lbMes.setBounds(68, 10, 420, 30);
        mainPanel.add(lbMes);

        
        btnOk = new JButton();
        btnOk.setBounds(130, 85, 83, 42);
        btnOk.setIcon(new ImageIcon(ExitDialog.class.getResource("/Images/btnOK.png")));
        btnOk.setPressedIcon(new ImageIcon(ExitDialog.class.getResource("/Images/btnOKClick.png")));
        btnOk.setContentAreaFilled(false);
        btnOk.setOpaque(false);
        btnOk.setBorderPainted(false);
        mainPanel.add(btnOk);
        this.add(mainPanel);

        btnCancel = new JButton();
        btnCancel.setBounds(240, 85, 83, 42);
        btnCancel.setIcon(new ImageIcon(ExitDialog.class.getResource("/Images/btnCancel.png")));
        btnCancel.setPressedIcon(new ImageIcon(ExitDialog.class.getResource("/Images/btnCancelClick.png")));
        btnCancel.setContentAreaFilled(false);
        btnCancel.setOpaque(false);
        btnCancel.setBorderPainted(false);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        mainPanel.add(btnCancel);
        mainPanel.setBounds(0, 0, 475, 200);
        this.add(mainPanel);
        this.setLocationRelativeTo(this);
    }
}
