package Main;

import Source.Settings;

import Panel.StartPanel;
import Panel.GamePanel;
import SwingCustomize.AboutUsPanel;
import SwingCustomize.ExitDialog;
import SwingCustomize.FadeLabel;
import SwingCustomize.HighScorePanel;
import SwingCustomize.NewGameDialog;
import SwingCustomize.SettingPanel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

public class StartFrame extends JFrame {

    StartPanel startPanel;
    ExitDialog exitDialog;
    NewGameDialog newGame;
    GamePanel pnGame;

    public StartFrame() {
        this.setSize(Settings.pnWidth, Settings.pnHeight);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Nguyễn Cảnh Khánh Trình");
        this.setLocationRelativeTo(this);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(StartFrame.class.getResource("/Images/iconLogo.png")));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitDialog = new ExitDialog(false);
                exitDialog.btnOk.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                exitDialog.setVisible(true);
            }
        });

        startPanel();
    }

    private void startPanel() {
        startPanel = new StartPanel();
        startPanel.lbNewGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                evtClickMenu(e);
            }
        });
        startPanel.lbContinue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                evtClickMenu(e);
            }
        });
        startPanel.lbSettings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                evtClickMenu(e);
            }
        });
        startPanel.lbHighScore.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                evtClickMenu(e);
            }
        });
        startPanel.lbAboutUs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                evtClickMenu(e);
            }
        });
        startPanel.lbExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                evtClickMenu(e);
            }
        });
        this.add(startPanel);
    }

    private void evtClickMenu(MouseEvent e) {
        FadeLabel lbSelect = (FadeLabel) e.getSource();
        switch (lbSelect.getName()) {
            case "NewGame":
                evtNewGame();
                break;
            case "Continue":
                FileInputStream fis,
                 fis_status;
                int total;
                String write;
                ArrayList<JButton> lstButton;
                try {
                    fis_status = new FileInputStream("tmp_status.bin");
                    InputStreamReader isw = new InputStreamReader(fis_status);
                    BufferedReader out = new BufferedReader(isw);
                    write = out.readLine();
                    String[] list = write.split("/");
                    int level = Integer.parseInt(list[0]);
                    int score = Integer.parseInt(list[1]);
                    Settings.ThoiGian = Integer.parseInt(list[2]);
                    Settings.Cot = Integer.parseInt(list[3]);
                    Settings.Dong = Integer.parseInt(list[4]);
                    Settings.helpCount = Integer.parseInt(list[5]);
                    Settings.randomCount = Integer.parseInt(list[6]);
                    Settings.HighScore = Integer.parseInt(list[7]);
                    int maxTime = Integer.parseInt(list[8]);
                    int btnCount = Integer.parseInt(list[9]);
                    fis_status.close();

                    fis = new FileInputStream("tmp.bin");

                    ObjectInputStream ois = new ObjectInputStream(fis);
                    lstButton = (ArrayList<JButton>) ois.readObject();
                    fis.close();
                    total = lstButton.size();
                    pnGame = new GamePanel(Settings.pnWidth, Settings.pnHeight, true, lstButton, level, score, maxTime, btnCount);
                    pnGame.btnNewGame.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            evtNewGame();
                        }
                    });
                    pnGame.exitDialog.btnOk.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            pnGame.evtSaveGame();
                            if (pnGame.backgroundSound != null) {
                                pnGame.backgroundSound.stop();
                            }
                            remove(pnGame);
                            repaint();
                            add(startPanel);
                            validate();
                            pnGame.exitDialog.setVisible(false);
                        }
                    });
                    remove(startPanel);
                    repaint();
                    add(pnGame);
                    validate();
                } catch (FileNotFoundException | ClassNotFoundException ex) {
                    lbSelect.setName("NewGame");
                    evtClickMenu(e);
                } catch (IOException ex) {
                    lbSelect.setName("NewGame");
                    evtClickMenu(e);
                }
                break;
            case "Settings":
                SettingPanel setting = new SettingPanel(Settings.Music);
                setting.setVisible(true);
                break;
            case "HighScore":
                HighScorePanel highScore = new HighScorePanel(Settings.HighScore);
                highScore.setVisible(true);
                break;
            case "AboutUs":
                AboutUsPanel aboutUs = new AboutUsPanel();
                aboutUs.setVisible(true);
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
                break;
        }
    }

    private void evtChicken() {
        Settings.Cot = 12;
        Settings.Dong = 6;
        Settings.ThoiGian = 180;
        Settings.helpCount = 2;
        Settings.randomCount = 2;
        if (pnGame != null) {
            remove(pnGame);
            repaint();
        }
        pnGame = new GamePanel(Settings.pnWidth, Settings.pnHeight);
        pnGame.btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evtNewGame();
            }
        });
        pnGame.exitDialog.btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnGame.evtSaveGame();
                if (pnGame.backgroundSound != null) {
                    pnGame.backgroundSound.stop();
                }
                remove(pnGame);
                repaint();
                add(startPanel);
                validate();
                pnGame.exitDialog.setVisible(false);
            }
        });
        remove(startPanel);
        repaint();
        add(pnGame);
        validate();
        newGame.setVisible(false);
    }

    private void evtNormal() {
        Settings.Cot = 16;
        Settings.Dong = 8;
        Settings.ThoiGian = 300;
        Settings.helpCount = 3;
        Settings.randomCount = 3;
        if (pnGame != null) {
            remove(pnGame);
            repaint();
        }
        pnGame = new GamePanel(Settings.pnWidth, Settings.pnHeight);
        pnGame.btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evtNewGame();
            }
        });
        pnGame.exitDialog.btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnGame.evtSaveGame();
                if (pnGame.backgroundSound != null) {
                    pnGame.backgroundSound.stop();
                }
                remove(pnGame);
                repaint();
                add(startPanel);
                validate();
                pnGame.exitDialog.setVisible(false);
            }
        });
        remove(startPanel);
        repaint();
        add(pnGame);
        validate();
        newGame.setVisible(false);
    }

    private void evtSuperMan() {
        Settings.Cot = 20;
        Settings.Dong = 9;
        Settings.ThoiGian = 720;
        Settings.helpCount = 4;
        Settings.randomCount = 4;
        if (pnGame != null) {
            remove(pnGame);
            repaint();
        }
        pnGame = new GamePanel(Settings.pnWidth, Settings.pnHeight);
        pnGame.btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evtNewGame();
            }
        });
        pnGame.exitDialog.btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnGame.evtSaveGame();
                if (pnGame.backgroundSound != null) {
                    pnGame.backgroundSound.stop();
                }
                remove(pnGame);
                repaint();
                add(startPanel);
                validate();
                pnGame.exitDialog.setVisible(false);
            }
        });
        remove(startPanel);
        repaint();
        add(pnGame);
        validate();
        newGame.setVisible(false);
    }

    private void evtNewGame() {
        newGame = new NewGameDialog();
        newGame.btnChicken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pnGame != null) {
                    if (pnGame.timeGame != null) {
                        pnGame.timeGame.stop();
                    }
                }
                evtChicken();
            }
        });
        newGame.btnNormal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pnGame != null) {
                    if (pnGame.timeGame != null) {
                        pnGame.timeGame.stop();
                    }
                }
                evtNormal();
            }
        });
        newGame.btnSuperMan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pnGame != null) {
                    if (pnGame.timeGame != null) {
                        pnGame.timeGame.stop();
                    }
                }
                evtSuperMan();
            }
        });
        newGame.setVisible(true);
    }
}
