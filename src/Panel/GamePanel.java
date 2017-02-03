package Panel;

import Source.ArrayGeneral;
import Source.Check;
import Source.Level;
import SwingCustomize.ExitDialog;
import SwingCustomize.GameOverDialog;
import SwingCustomize.LevelUpDialog;
import SwingCustomize.MusicPlayer;
import SwingCustomize.TimeProgress;
import Source.Settings;
import SwingCustomize.FadeLabel;
import SwingCustomize.VictoryDialog;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GamePanel extends JPanel implements Serializable {

    private JPanel pnPause;
    private ButtonPanel pnButton;
    private TimeProgress timeSlider;
    private JButton btnRandom, slOne, slTwo, btnHelp, btnPause, btnMute, btnHelpOne, btnHelpTwo, btnExit;
    public JButton btnNewGame;
    private LineBorder slBorder, helpBorder;
    private ArrayList<JButton> lstButton = new ArrayList<>();
    private int[] arrButton;
    private int click = 0, score = 0, btnCount = 0, level = 1, time = 0, maxTime = 0;
    private float timeHelpCount = 1f;
    public MusicPlayer backgroundSound;
    public Timer timeHelp, timeGame;
    private JLabel lbScore, lbRandom, lbRandomImage, lbHelp, lbHelpImage, lbLevel;
    private FadeLabel lbHelpCount;
    private boolean isHelp = false, isResume = false;
    private Check check;
    private LevelUpDialog lvDialog;
    public GameOverDialog overDialog;
    public ExitDialog exitDialog = new ExitDialog(true);
    private Level levelAction;

    public GamePanel(int width, int height) {
        general();
        buttonStart();
        musicStart();
    }

    public GamePanel(int width, int height, boolean resume, ArrayList<JButton> lstButton, int level, int score, int maxTime, int btnCount) {
        this.isResume = resume;
        this.lstButton = lstButton;
        this.level = level;
        this.score = score;
        this.maxTime = maxTime;
        this.btnCount = btnCount;
        general();
        buttonStart();
        musicStart();
    }

    private void general() {
        if (maxTime == 0) {
            maxTime = Settings.ThoiGian;
        }
        pnButton = new ButtonPanel();
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);
        setBackground(Color.BLACK);
        pnButton.setBounds((Settings.pnWidth - Settings.Dai * Settings.Cot) / 2 + 50, (Settings.pnHeight - Settings.Cao * Settings.Dong) / 2, Settings.Dai * Settings.Cot, Settings.Cao * Settings.Dong);

        pnButton.setLayout(new GridLayout(Settings.Dong, Settings.Cot, 0, 0));
        pnButton.setOpaque(false);
        add(pnButton);

        lbScore = new JLabel("" + score);
        lbScore.setFont(new Font("Jokerman", Font.BOLD, 30));
        lbScore.setForeground(Color.green);
        lbScore.setBounds(200, 10, 100, 40);
        add(lbScore);

        lbLevel = new JLabel("Level " + level + "/13");
        lbLevel.setFont(new Font("Serif", Font.BOLD, 20));
        lbLevel.setForeground(Color.green);
        lbLevel.setBounds(300, 8, 100, 40);
        add(lbLevel);

        lbRandomImage = new JLabel(new ImageIcon(GamePanel.class.getResource("/Images/lbRandom.png")));
        lbRandomImage.setBounds(870, 19, 15, 20);
        add(lbRandomImage);

        lbRandom = new JLabel("" + Settings.randomCount);
        lbRandom.setFont(new Font("Serif", Font.BOLD, 20));
        lbRandom.setForeground(Color.white);
        lbRandom.setBounds(845, 18, 30, 20);
        add(lbRandom);

        lbHelpImage = new JLabel(new ImageIcon(GamePanel.class.getResource("/Images/lbHelp.png")));
        lbHelpImage.setBounds(930, 19, 15, 20);
        add(lbHelpImage);

        lbHelp = new JLabel();
        lbHelp.setText("" + Settings.helpCount);
        lbHelp.setFont(new Font("Serif", Font.BOLD, 20));
        lbHelp.setForeground(Color.white);
        lbHelp.setBounds(907, 18, 30, 20);
        add(lbHelp);

        lbHelpCount = new FadeLabel();
        lbHelpCount.setFont(new Font("Serif", Font.BOLD, 20));
        lbHelpCount.setForeground(Color.red);
        lbHelpCount.setBounds(Settings.pnWidth / 2 - 150, 58, 360, 20);
        add(lbHelpCount);

        timeSlider = new TimeProgress();
        timeSlider.setMinimum(1);
        timeSlider.setMaximum(maxTime);
        timeSlider.setValue(Settings.ThoiGian);
        timeSlider.setEnabled(false);
        timeSlider.setBounds(Settings.pnWidth / 2 - 380 / 2, 20, 400, 18);
        timeSlider.setOpaque(true);
        timeSlider.setBackground(Color.black);
        add(timeSlider);

        timeGame = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evtTime();
            }
        });

        btnHelp = new JButton();
        btnHelp.setIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnHelp.png")));
        btnHelp.setBounds(38, 70, 94, 51);
        btnHelp.setOpaque(false);
        btnHelp.setContentAreaFilled(false);
        btnHelp.setBorderPainted(false);
        btnHelp.setPressedIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnHelpClick.png")));
        btnHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                help();
            }
        });
        add(btnHelp);
        if (Settings.helpCount == 0) {
            btnHelp.setVisible(false);
        }

        btnPause = new JButton();
        btnPause.setName("Play");
        btnPause.setIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnPause.png")));
        btnPause.setSize(146, 52);
        btnPause.setBounds(38, 150, 94, 51);
        btnPause.setOpaque(false);
        btnPause.setBorderPainted(false);
        btnPause.setContentAreaFilled(false);
        btnPause.setPressedIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnPauseClick.png")));
        btnPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evtPause();
            }
        });
        add(btnPause);

        btnRandom = new JButton();
        btnRandom.setIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnRandom.png")));
        btnRandom.setSize(146, 52);
        btnRandom.setBounds(38, 230, 94, 51);
        btnRandom.setOpaque(false);
        btnRandom.setBorderPainted(false);
        btnRandom.setContentAreaFilled(false);
        btnRandom.setPressedIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnRandomClick.png")));
        btnRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reRandom();
            }
        });
        add(btnRandom);
        if (Settings.randomCount == 0) {
            btnRandom.setVisible(false);
        }

        btnNewGame = new JButton();
        btnNewGame.setIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnNew.png")));
        btnNewGame.setSize(146, 52);
        btnNewGame.setBounds(38, 380, 94, 51);
        btnNewGame.setOpaque(false);
        btnNewGame.setBorderPainted(false);
        btnNewGame.setContentAreaFilled(false);
        btnNewGame.setPressedIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnNewClick.png")));
        add(btnNewGame);

        btnExit = new JButton();
        btnExit.setIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnMenu.png")));
        btnExit.setSize(146, 52);
        btnExit.setBounds(38, 460, 94, 51);
        btnExit.setOpaque(false);
        btnExit.setBorderPainted(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setPressedIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnMenuClick.png")));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitDialog.setVisible(true);
            }
        });
        add(btnExit);

        String statusMusic;
        btnMute = new JButton();
        if (Settings.Music) {
            statusMusic = "Music";
        } else {
            statusMusic = "Mute";
        }
        btnMute.setIcon(new ImageIcon(GamePanel.class.getResource("/Images/mn" + statusMusic + ".png")));
        btnMute.setPressedIcon(new ImageIcon(GamePanel.class.getResource("/Images/mn" + statusMusic + "Click.png")));
        btnMute.setSize(146, 52);
        btnMute.setBounds(38, 540, 94, 51);
        btnMute.setOpaque(false);
        btnMute.setBorderPainted(false);
        btnMute.setContentAreaFilled(false);
        btnMute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Settings.Music) {
                    btnMute.setIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnMute.png")));
                    btnMute.setPressedIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnMuteClick.png")));
                    Settings.Music = false;
                    backgroundSound.stop();
                } else {
                    btnMute.setIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnMusic.png")));
                    btnMute.setPressedIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnMusicClick.png")));
                    Settings.Music = true;
                    musicStart();
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
        add(btnMute);

        timeGame.start();

        slOne = new JButton();
        slTwo = new JButton();
        btnHelpOne = new JButton();
        btnHelpTwo = new JButton();
        slBorder = new LineBorder(Color.red, 3);
        helpBorder = new LineBorder(Color.green, 3);
        time = Settings.ThoiGian;
        pnButton.validate();
        check = new Check(pnButton);
    }

    public void buttonStart() {
        if (isResume == false) {
            timeSlider.setMaximum(Settings.ThoiGian);
            ArrayGeneral array = new ArrayGeneral(Settings.Total(), Settings.Cot, lstButton, false);
            arrButton = array.createArray();

            for (int i = 0; i < Settings.Total(); i++) {
                lstButton.add(new JButton(new ImageIcon(GamePanel.class.getResource("/Images/h" + arrButton[i] + ".jpg"))));
                lstButton.get(i).setName("" + arrButton[i]);
                lstButton.get(i).setBorder(null);
                pnButton.add(lstButton.get(i));
                if (i <= (Settings.Cot - 1) || i >= (Settings.Total() - Settings.Cot) || i % Settings.Cot == 0 || (i + 1) % Settings.Cot == 0) {
                    lstButton.get(i).setVisible(false);
                }
                lstButton.get(i).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        clickEvent(evt);
                    }
                });
            }
        } else {
            int total = lstButton.size();
            for (int i = 0; i < total; i++) {
                lstButton.get(i).setIcon(new ImageIcon(GamePanel.class.getResource("/Images/h" + lstButton.get(i).getName() + ".jpg")));
                pnButton.add(lstButton.get(i));
                lstButton.get(i).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        clickEvent(evt);
                    }
                });
            }
            isResume = false;
        }
    }

    private void reRandom() {
        if (isHelp) {
            btnHelpOne.setBorder(null);
            btnHelpTwo.setBorder(null);
        }
        if (Settings.ThoiGian > 0 && timeGame.getDelay() == 1000) {
            Settings.randomCount--;
            lbRandom.setText("" + Settings.randomCount);
            if (Settings.randomCount == 0) {
                btnRandom.setVisible(false);
            }
            ArrayGeneral array = new ArrayGeneral(Settings.Total(), Settings.Cot, lstButton, true);
            arrButton = array.reRandom();
            for (int i = 0; i < Settings.Total(); i++) {
                if (lstButton.get(i).isVisible() == true) {
                    lstButton.get(i).setName("" + arrButton[i]);
                    lstButton.get(i).setIcon(new ImageIcon(GamePanel.class.getResource("/Images/h" + arrButton[i] + ".jpg")));
                }
            }
        }
    }

    private void help() {
        if (Settings.ThoiGian > 0 && timeGame.getDelay() == 1000) {
            Settings.helpCount--;
            lbHelp.setText("" + Settings.helpCount);
            if (Settings.helpCount == 0) {
                btnHelp.setVisible(false);
            }
            int findCount = 0;
            for (int i = 0; i < Settings.Total(); i++) {
                for (int j = 0; j < Settings.Total(); j++) {
                    if (i != j && lstButton.get(i).isVisible() && lstButton.get(j).isVisible() && lstButton.get(i).getName().equals(lstButton.get(j).getName())) {
                        if (check.check(lstButton.get(i), lstButton.get(j), lstButton, false)) {
                            lstButton.get(i).setBorder(helpBorder);
                            lstButton.get(j).setBorder(helpBorder);
                            btnHelpOne = lstButton.get(i);
                            btnHelpTwo = lstButton.get(j);
                            isHelp = true;
                            findCount++;
                            return;
                        }
                    }
                }
            }
            if (findCount == 0) {
                if (timeHelp != null) {
                    timeHelp.stop();
                }
                timeHelp = new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String text;
                        if (Settings.randomCount > 0) {
                            text = "Not Found ! Please Reload The Game !";
                        } else {
                            text = "Not Found ! Please Start New Game";
                        }
                        lbHelpCount.setText(text);
                        lbHelpCount.setOpacity(timeHelpCount);
                        timeHelpCount -= 0.02f;
                        if (timeHelpCount <= 0f) {
                            timeHelpCount = 1f;
                            lbHelpCount.setText("");
                            timeHelp.stop();
                        }
                    }
                });
                timeHelp.start();
            }
        }
    }

    private void evtTime() {
        Settings.ThoiGian--;
        timeSlider.setValue(Settings.ThoiGian);
        if (Settings.ThoiGian <= 0) {
            timeGame.stop();
            JPanel pnGameOver = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(Toolkit.getDefaultToolkit().getImage(GamePanel.class.getResource("/Images/pnGameOver.jpg")), 0, 0, this);
                }
            };
            pnGameOver.setBackground(Color.black);
            pnGameOver.setBounds((Settings.pnWidth - Settings.Dai * Settings.Cot) / 2 + 50 + pnButton.getWidth() / 2 - 120, (Settings.pnHeight - Settings.Cao * Settings.Dong) / 2 + pnButton.getHeight() / 2 - 60, 240, 200);
            remove(pnButton);
            add(pnGameOver);
            repaint();
            evtHighScore();
        }
    }

    private void clickEvent(java.awt.event.ActionEvent evt) {
        JButton btnSource = (JButton) evt.getSource();
        if (click == 0) {
            slOne = btnSource;
            slOne.setBorder(slBorder);
            click = 1;
            if (isHelp) {
                if (btnSource != btnHelpOne && btnSource != btnHelpTwo) {
                    btnHelpOne.setBorder(null);
                    btnHelpTwo.setBorder(null);
                    isHelp = false;
                }
            }
        } else if (click == 1) {
            btnSource.setBorder(slBorder);
            if (isHelp) {
                btnHelpOne.setBorder(null);
                btnHelpTwo.setBorder(null);
                isHelp = false;
            }
            if (slOne != btnSource) {
                slTwo = btnSource;
                if (slOne.getName().equals(slTwo.getName()) && Settings.ThoiGian > 1) {
                    if (check.check(slOne, slTwo, lstButton, true)) {
                        ++score;
                        ++btnCount;

                        lbScore.setText("" + score);

                        levelAction = new Level(lstButton, level, slOne, slTwo);
                        Thread thread = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(200);
                                    levelAction.Action();
                                    pnButton.Restart();
                                    pnButton.repaint();
                                } catch (InterruptedException ex) {

                                }
                            }
                        });
                        thread.start();

                        if (btnCount == ((Settings.Total() - Settings.Sub()) / 2)) {
                            timeGame.setDelay(9999999);
                            timeGame.restart();
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException ex) {
                            }
                            if (level >= 13) {
                                score += Settings.ThoiGian;
                                VictoryDialog victory;
                                if (score > Settings.HighScore) {
                                    Settings.HighScore = score;
                                    victory = new VictoryDialog(score, true, Settings.HighScore);
                                    try {
                                        FileOutputStream fos = new FileOutputStream("tmp_second.tmp");
                                        OutputStreamWriter osw = new OutputStreamWriter(fos);
                                        osw.write("" + score);
                                        osw.flush();
                                        fos.close();
                                    } catch (FileNotFoundException ex) {
                                    } catch (IOException ex) {
                                    }
                                } else {
                                    victory = new VictoryDialog(score, false, Settings.HighScore);
                                }
                                
                                victory.setVisible(true);
                                
                                JPanel pnVictory = new JPanel() {
                                    @Override
                                    public void paintComponent(Graphics g) {
                                        super.paintComponent(g);
                                        g.drawImage(Toolkit.getDefaultToolkit().getImage(GamePanel.class.getResource("/Images/victory.jpg")), 0, 0, this);
                                    }
                                };
                                pnVictory.setBackground(Color.black);
                                pnVictory.setBounds((Settings.pnWidth - Settings.Dai * Settings.Cot) / 2 + 50 + pnButton.getWidth() / 2 - 120, (Settings.pnHeight - Settings.Cao * Settings.Dong) / 2 + pnButton.getHeight() / 2 - 60, 240, 200);
                                remove(pnButton);
                                add(pnVictory);
                                repaint();
                            } else {
                                ++Settings.helpCount;
                                ++Settings.randomCount;
                                btnHelp.setVisible(true);
                                btnRandom.setVisible(true);
                                lbHelp.setText("" + Settings.helpCount);
                                lbRandom.setText("" + Settings.randomCount);

                                lvDialog = new LevelUpDialog(score, Settings.ThoiGian);
                                lvDialog.btnNext.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        level++;
                                        score += Settings.ThoiGian;
                                        Settings.ThoiGian = time;
                                        timeSlider.setValue(Settings.ThoiGian);
                                        lbLevel.setText("Level " + level + "/13");
                                        lbScore.setText("" + score);
                                        btnCount = 0;
                                        pnButton.removeAll();
                                        lstButton = new ArrayList<>();
                                        timeGame.setDelay(1000);
                                        timeGame.restart();
                                        buttonStart();
                                        validate();
                                        lvDialog.setVisible(false);
                                    }
                                });
                                lvDialog.setVisible(true);
                            }
                        }
                    } else {
                        slOne.setBorder(null);
                        slTwo.setBorder(null);
                    }
                } else {
                    slOne.setBorder(null);
                    slTwo.setBorder(null);
                }
                click = 0;
            } else {
                slOne.setBorder(null);
                click = 0;
            }
        } else {
            slOne.setBorder(null);
            slTwo.setBorder(null);
            click = 0;
        }
    }

    private void musicStart() {
        if (Settings.Music) {
            backgroundSound = new MusicPlayer("/Music/bg.mp3", true);
            backgroundSound.start();

        }
    }

    private void evtPause() {
        if (Settings.ThoiGian > 0) {
            if (btnPause.getName().equals("Play") && timeGame.getDelay() == 1000) {
                btnPause.setIcon(new ImageIcon(GamePanel.class
                        .getResource("/Images/mnPlay.png")));
                btnPause.setPressedIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnPlayClick.png")));
                btnPause.setName("Pause");
                timeGame.setDelay(99999999);
                timeGame.restart();

                remove(pnButton);
                pnPause = new JPanel(null) {
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.setColor(Color.red);
                        g.drawImage(Toolkit.getDefaultToolkit().getImage(GamePanel.class.getResource("/Images/pnPause.jpg")), 0, 0, this);
                    }
                };

                pnPause.addMouseListener(
                        new MouseAdapter() {
                            @Override
                            public void mouseReleased(MouseEvent e
                            ) {
                                evtPause();
                            }
                        }
                );
                pnPause.setBackground(Color.black);

                pnPause.setBounds(
                        (Settings.pnWidth - Settings.Dai * Settings.Cot) / 2 + 50 + pnButton.getWidth() / 2 - 120, (Settings.pnHeight - Settings.Cao * Settings.Dong) / 2 + pnButton.getHeight() / 2 - 60, 240, 200);
                add(pnPause);

                repaint();
            } else if(timeGame.getDelay() == 99999999) {
                btnPause.setIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnPause.png")));
                btnPause.setPressedIcon(new ImageIcon(GamePanel.class.getResource("/Images/mnPauseClick.png")));
                btnPause.setName("Play");
                timeGame.setDelay(1000);
                timeGame.restart();
                remove(pnPause);
                add(pnButton);
                repaint();
            }
        }
    }

    public void evtSaveGame() {
        timeGame.stop();
        if (Settings.ThoiGian > 0 && btnCount != ((Settings.Total() - Settings.Sub()) / 2)) {
            String write = "" + level + "/" + score + "/" + Settings.ThoiGian + "/" + Settings.Cot + "/" + Settings.Dong + "/" + Settings.helpCount + "/" + Settings.randomCount + "/" + Settings.HighScore + "/" + maxTime + "/" + btnCount;
            try {
                int total = lstButton.size();
                for (int i = 0; i < total; i++) {
                    lstButton.get(i).setIcon(null);
                }
                FileOutputStream fos = new FileOutputStream("tmp.bin");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(lstButton);
                fos.close();

                FileOutputStream fos_status = new FileOutputStream("tmp_status.bin");
                OutputStreamWriter osw = new OutputStreamWriter(fos_status);
                BufferedWriter out = new BufferedWriter(osw);
                out.write(write);
                out.flush();
                fos_status.close();
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            }
        }
    }

    private void evtHighScore() {
        if (score > Settings.HighScore) {
            Settings.HighScore = score;
            overDialog = new GameOverDialog(score, true, Settings.HighScore);
            try {
                FileOutputStream fos = new FileOutputStream("tmp_second.tmp");
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                osw.write("" + score);
                osw.flush();
                fos.close();
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            }
        } else {
            overDialog = new GameOverDialog(score, false, Settings.HighScore);
        }
        overDialog.setVisible(true);
    }
}
