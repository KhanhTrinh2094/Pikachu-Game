package Panel;

import SwingCustomize.FadeLabel;
import Source.Settings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StartPanel extends JPanel {

    private JLabel lbBackground;
    public FadeLabel lbNewGame, lbContinue, lbSettings, lbHighScore, lbAboutUs, lbExit;
    private JButton btnNext, btnBack;
    private int btnClick;
    private float opacity, opacityFade;
    private Timer fadeTime;

    public StartPanel() {
        general();
        btnNextStart();
        btnBackStart();
    }
    
    private void general() {
        setLayout(null);
        lbBackground = new JLabel(new ImageIcon(StartPanel.class.getResource("/Images/pnBackground.jpg")));
        lbBackground.setBounds(0, 0, 1200, 700);
        add(lbBackground);

        this.setSize(Settings.pnWidth, Settings.pnHeight);

        lbNewGame = new FadeLabel();
        lbNewGame.setName("NewGame");
        lbNewGame.setIcon(new ImageIcon(StartPanel.class.getResource("/Images/NewGame.png")));
        lbNewGame.setBounds(460, 545, 275, 80);
        lbNewGame.setVisible(true);
        lbBackground.add(lbNewGame);

        lbContinue = new FadeLabel();
        lbContinue.setName("Continue");
        lbContinue.setIcon(new ImageIcon(StartPanel.class.getResource("/Images/Continue.png")));

        lbContinue.setOpacity(0f);
        lbContinue.setVisible(false);
        lbBackground.add(lbContinue);

        lbSettings = new FadeLabel();
        lbSettings.setName("Settings");
        lbSettings.setIcon(new ImageIcon(StartPanel.class.getResource("/Images/Settings.png")));

        lbSettings.setOpacity(0f);
        lbSettings.setVisible(false);
        lbBackground.add(lbSettings);

        lbHighScore = new FadeLabel();
        lbHighScore.setName("HighScore");
        lbHighScore.setIcon(new ImageIcon(StartPanel.class.getResource("/Images/HighScore.png")));

        lbHighScore.setOpacity(0f);
        lbHighScore.setVisible(false);
        lbBackground.add(lbHighScore);

        lbAboutUs = new FadeLabel();
        lbAboutUs.setName("AboutUs");
        lbAboutUs.setIcon(new ImageIcon(StartPanel.class.getResource("/Images/AboutUs.png")));

        lbAboutUs.setOpacity(0f);
        lbAboutUs.setVisible(false);
        lbBackground.add(lbAboutUs);

        lbExit = new FadeLabel();
        lbExit.setName("Exit");
        lbExit.setIcon(new ImageIcon(StartPanel.class.getResource("/Images/Exit.png")));

        lbExit.setOpacity(0f);
        lbExit.setVisible(false);
        lbBackground.add(lbExit);

    }
    
    private void btnNextStart() {

        btnNext = new JButton();
        btnNext.setIcon(new ImageIcon(StartPanel.class.getResource("/Images/btnNextFront.png")));
        btnNext.setBounds(820, 525, 50, 107);
        btnNext.setOpaque(false);
        btnNext.setContentAreaFilled(false);
        btnNext.setBorderPainted(false);

        lbBackground.add(btnNext);
        btnNext.addMouseListener(new MouseAdapter() {
            private Timer timeClick;

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    timeClick = new Timer(100, new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (btnClick == 0) {
                                btnNext.setIcon(new ImageIcon(StartPanel.class.getResource("/Images/btnNextEnd.png")));
                                btnNext.setBounds(820, 532, 50, 98);
                                btnClick++;

                                switch (getButtonVisible()) {
                                    case "lbNewGame":
                                        lbNewGame.setBounds(460, 545, 275, 80);
                                        lbContinue.setBounds(750, 545, 260, 80);
                                        lbContinue.setVisible(true);
                                        opacity = 1f;
                                        opacityFade = 0f;
                                        fadeTime = new Timer(50, new ActionListener() {

                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                opacity -= 0.1f;
                                                opacityFade += 0.1f;
                                                if (opacity < 0) {
                                                    opacity = 0f;
                                                    fadeTime.stop();
                                                }
                                                if (opacityFade > 1) {
                                                    opacityFade = 1f;
                                                    fadeTime.stop();
                                                }

                                                lbNewGame.setOpacity(opacity);
                                                lbNewGame.setBounds(lbNewGame.getLocation().x - 10, 545, 275, 80);
                                                lbContinue.setBounds(lbContinue.getLocation().x - 30, 545, 260, 80);
                                                lbContinue.setOpacity(opacityFade);
                                                
                                                if (lbNewGame.getLocation().x <= 360) {
                                                    lbNewGame.setVisible(false);
                                                }
                                            }
                                        });
                                        fadeTime.start();
                                        break;
                                    case "lbContinue":
                                        lbSettings.setBounds(750, 545, 260, 80);
                                        lbContinue.setBounds(450, 545, 260, 80);
                                        lbSettings.setVisible(true);
                                        opacity = 1f;
                                        opacityFade = 0f;
                                        fadeTime = new Timer(50, new ActionListener() {

                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                opacity -= 0.1f;
                                                opacityFade += 0.1f;
                                                if (opacity < 0) {
                                                    opacity = 0f;
                                                    fadeTime.stop();
                                                }
                                                if (opacityFade > 1) {
                                                    opacityFade = 1f;
                                                    fadeTime.stop();
                                                }

                                                lbContinue.setOpacity(opacity);
                                                lbContinue.setBounds(lbContinue.getLocation().x - 10, 545, 260, 80);
                                                lbSettings.setBounds(lbSettings.getLocation().x - 30, 545, 260, 80);
                                                lbSettings.setOpacity(opacityFade);
                                                if (lbContinue.getLocation().x <= 350) {
                                                    lbContinue.setVisible(false);
                                                }

                                            }
                                        });
                                        fadeTime.start();
                                        break;
                                    case "lbSettings":
                                        lbHighScore.setBounds(750, 545, 275, 80);
                                        lbSettings.setBounds(450, 545, 260, 80);
                                        lbHighScore.setVisible(true);
                                        opacity = 1f;
                                        opacityFade = 0f;
                                        fadeTime = new Timer(50, new ActionListener() {

                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                opacity -= 0.1f;
                                                opacityFade += 0.1f;
                                                if (opacity < 0) {
                                                    opacity = 0f;
                                                    fadeTime.stop();
                                                }
                                                if (opacityFade > 1) {
                                                    opacityFade = 1f;
                                                    fadeTime.stop();
                                                }

                                                lbSettings.setOpacity(opacity);
                                                lbSettings.setBounds(lbSettings.getLocation().x - 10, 545, 260, 80);
                                                lbHighScore.setBounds(lbHighScore.getLocation().x - 30, 545, 275, 80);
                                                lbHighScore.setOpacity(opacityFade);
                                                if (lbSettings.getLocation().x <= 350) {
                                                    lbSettings.setVisible(false);
                                                }

                                            }
                                        });
                                        fadeTime.start();
                                        break;
                                    case "lbHighScore":
                                        lbAboutUs.setVisible(true);
                                        opacity = 1f;
                                        opacityFade = 0f;
                                        lbAboutUs.setBounds(750, 545, 260, 80);
                                        lbHighScore.setBounds(450, 545, 275, 80);
                                        fadeTime = new Timer(50, new ActionListener() {

                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                opacity -= 0.1f;
                                                opacityFade += 0.1f;
                                                if (opacity < 0) {
                                                    opacity = 0f;
                                                    fadeTime.stop();
                                                }
                                                if (opacityFade > 1) {
                                                    opacityFade = 1f;
                                                    fadeTime.stop();
                                                }

                                                lbHighScore.setOpacity(opacity);
                                                lbHighScore.setBounds(lbHighScore.getLocation().x - 10, 545, 275, 80);
                                                lbAboutUs.setBounds(lbAboutUs.getLocation().x - 30, 545, 260, 80);
                                                lbAboutUs.setOpacity(opacityFade);
                                                if (lbHighScore.getLocation().x <= 350) {
                                                    lbHighScore.setVisible(false);
                                                }
                                            }
                                        });
                                        fadeTime.start();
                                        break;
                                    case "lbAboutUs":
                                        lbExit.setBounds(750, 545, 260, 80);
                                        lbAboutUs.setBounds(450, 545, 260, 80);
                                        opacity = 1f;
                                        opacityFade = 0f;
                                        lbExit.setVisible(true);
                                        fadeTime = new Timer(50, new ActionListener() {

                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                opacity -= 0.1f;
                                                opacityFade += 0.1f;
                                                if (opacity < 0) {
                                                    opacity = 0f;
                                                    fadeTime.stop();
                                                }
                                                if (opacityFade > 1) {
                                                    opacityFade = 1f;
                                                    fadeTime.stop();
                                                }
                                                lbAboutUs.setOpacity(opacity);
                                                lbAboutUs.setBounds(lbAboutUs.getLocation().x - 10, 545, 260, 80);
                                                lbExit.setBounds(lbExit.getLocation().x - 30, 545, 260, 80);
                                                lbExit.setOpacity(opacityFade);
                                                if (lbAboutUs.getLocation().x <= 350) {
                                                    lbAboutUs.setVisible(false);
                                                }
                                            }
                                        });
                                        btnNext.setVisible(false);
                                        fadeTime.start();
                                        break;
                                }

                            } else if (btnClick == 1) {
                                btnNext.setIcon(new ImageIcon(StartPanel.class.getResource("/Images/btnNextFront.png")));
                                btnNext.setBounds(820, 525, 50, 107);
                                btnClick++;
                            } else {
                                btnClick = 0;
                                timeClick.stop();
                                timeClick.setRepeats(false);
                            }
                        }
                    });

                    timeClick.start();
                    timeClick.setRepeats(true);
                    btnBack.setVisible(true);
                }
            }
        });
    }

    private void btnBackStart() {
        btnBack = new JButton();
        btnBack.setIcon(new ImageIcon(StartPanel.class.getResource("/Images/btnBackFront.png")));
        btnBack.setBounds(330, 525, 50, 107);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setVisible(false);
        lbBackground.add(btnBack);
        btnBack.addMouseListener(new MouseAdapter() {
            private Timer timeClick;

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    timeClick = new Timer(100, new ActionListener() {
                        private int btnClick;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (btnClick == 0) {
                                btnBack.setIcon(new ImageIcon(StartPanel.class.getResource("/Images/btnBackEnd.png")));
                                btnBack.setBounds(330, 532, 50, 98);
                                btnClick++;

                                switch (getButtonVisible()) {
                                    case "lbContinue":
                                        lbNewGame.setBounds(155, 545, 275, 80);
                                        lbContinue.setBounds(460, 545, 260, 80);
                                        lbNewGame.setVisible(true);
                                        opacity = 1f;
                                        opacityFade = 0f;
                                        fadeTime = new Timer(50, new ActionListener() {

                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                opacity -= 0.1f;
                                                opacityFade += 0.1f;
                                                if (opacity < 0) {
                                                    opacity = 0f;
                                                    fadeTime.stop();
                                                }
                                                if (opacityFade > 1) {
                                                    opacityFade = 1f;
                                                    fadeTime.stop();
                                                }

                                                lbContinue.setOpacity(opacity);
                                                lbContinue.setBounds(lbContinue.getLocation().x + 10, 545, 260, 80);
                                                lbNewGame.setBounds(lbNewGame.getLocation().x + 30, 545, 275, 80);
                                                lbNewGame.setOpacity(opacityFade);
                                                if (lbContinue.getLocation().x >= 560) {
                                                    lbContinue.setVisible(false);
                                                }

                                            }
                                        });
                                        btnBack.setVisible(false);
                                        fadeTime.start();
                                        break;
                                    case "lbSettings":
                                        lbContinue.setBounds(155, 545, 260, 80);
                                        lbSettings.setBounds(460, 545, 260, 80);
                                        lbContinue.setVisible(true);
                                        opacity = 1f;
                                        opacityFade = 0f;
                                        fadeTime = new Timer(50, new ActionListener() {

                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                opacity -= 0.1f;
                                                opacityFade += 0.1f;
                                                if (opacity < 0) {
                                                    opacity = 0f;
                                                    fadeTime.stop();
                                                }
                                                if (opacityFade > 1) {
                                                    opacityFade = 1f;
                                                    fadeTime.stop();
                                                }
                                                lbSettings.setOpacity(opacity);
                                                lbSettings.setBounds(lbSettings.getLocation().x + 10, 545, 260, 80);
                                                lbContinue.setBounds(lbContinue.getLocation().x + 30, 545, 260, 80);
                                                lbContinue.setOpacity(opacityFade);
                                                if (lbSettings.getLocation().x >= 560) {
                                                    lbSettings.setVisible(false);
                                                }
                                            }
                                        });
                                        fadeTime.start();
                                        break;
                                    case "lbHighScore":
                                        lbSettings.setBounds(155, 545, 260, 80);
                                        lbHighScore.setBounds(460, 545, 275, 80);
                                        lbSettings.setVisible(true);
                                        opacity = 1f;
                                        opacityFade = 0f;
                                        fadeTime = new Timer(50, new ActionListener() {

                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                opacity -= 0.1f;
                                                opacityFade += 0.1f;
                                                if (opacity < 0) {
                                                    opacity = 0f;
                                                    fadeTime.stop();
                                                }
                                                if (opacityFade > 1) {
                                                    opacityFade = 1f;
                                                    fadeTime.stop();
                                                }
                                                lbHighScore.setOpacity(opacity);
                                                lbHighScore.setBounds(lbHighScore.getLocation().x + 10, 545, 275, 80);
                                                lbSettings.setBounds(lbSettings.getLocation().x + 30, 545, 260, 80);
                                                lbSettings.setOpacity(opacityFade);
                                                if (lbHighScore.getLocation().x >= 560) {
                                                    lbHighScore.setVisible(false);
                                                }
                                            }
                                        });
                                        fadeTime.start();
                                        break;
                                    case "lbAboutUs":
                                        lbHighScore.setBounds(155, 545, 275, 80);
                                        lbAboutUs.setBounds(460, 545, 260, 80);
                                        lbHighScore.setVisible(true);
                                        opacity = 1f;
                                        opacityFade = 0f;
                                        fadeTime = new Timer(50, new ActionListener() {

                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                opacity -= 0.1f;
                                                opacityFade += 0.1f;
                                                if (opacity < 0) {
                                                    opacity = 0f;
                                                    fadeTime.stop();
                                                }
                                                if (opacityFade > 1) {
                                                    opacityFade = 1f;
                                                    fadeTime.stop();
                                                }
                                                lbAboutUs.setOpacity(opacity);
                                                lbAboutUs.setBounds(lbAboutUs.getLocation().x + 10, 545, 260, 80);
                                                lbHighScore.setBounds(lbHighScore.getLocation().x + 30, 545, 275, 80);
                                                lbHighScore.setOpacity(opacityFade);
                                                if (lbAboutUs.getLocation().x >= 560) {
                                                    lbAboutUs.setVisible(false);
                                                }
                                            }
                                        });
                                        fadeTime.start();
                                        break;
                                    case "lbExit":
                                        lbAboutUs.setBounds(155, 545, 260, 80);
                                        lbExit.setBounds(460, 545, 260, 80);
                                        lbAboutUs.setVisible(true);
                                        opacity = 1f;
                                        opacityFade = 0f;
                                        fadeTime = new Timer(50, new ActionListener() {

                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                opacity -= 0.1f;
                                                opacityFade += 0.1f;
                                                if (opacity < 0) {
                                                    opacity = 0f;
                                                    fadeTime.stop();
                                                }
                                                if (opacityFade > 1) {
                                                    opacityFade = 1f;
                                                    fadeTime.stop();
                                                }
                                                lbExit.setOpacity(opacity);
                                                lbExit.setBounds(lbExit.getLocation().x + 10, 545, 260, 80);
                                                lbAboutUs.setBounds(lbAboutUs.getLocation().x + 30, 545, 260, 80);
                                                lbAboutUs.setOpacity(opacityFade);
                                                if (lbExit.getLocation().x >= 560) {
                                                    lbExit.setVisible(false);
                                                }
                                            }
                                        });
                                        btnNext.setVisible(true);
                                        fadeTime.start();
                                        break;
                                }

                            } else if (btnClick == 1) {
                                btnBack.setIcon(new ImageIcon(StartPanel.class.getResource("/Images/btnBackFront.png")));
                                btnBack.setBounds(330, 525, 50, 107);
                                btnClick++;
                            } else {
                                btnClick = 0;
                                timeClick.stop();
                                timeClick.setRepeats(false);
                            }
                        }
                    });

                    timeClick.start();
                    timeClick.setRepeats(true);
                }
            }
        });
    }

    private String getButtonVisible() {
        if (lbNewGame.isVisible()) {
            return "lbNewGame";
        } else if (lbContinue.isVisible()) {
            return "lbContinue";
        } else if (lbSettings.isVisible()) {
            return "lbSettings";
        } else if (lbHighScore.isVisible()) {
            return "lbHighScore";
        } else if (lbAboutUs.isVisible()) {
            return "lbAboutUs";
        } else {
            return "lbExit";
        }
    }

}
