package Source;

import java.util.ArrayList;
import javax.swing.JButton;

public class Level {

    private ArrayList<JButton> lstButton;
    private int level;
    private JButton btnOne, btnTwo;

    public Level() {

    }

    public Level(ArrayList<JButton> lstButton, int level, JButton btnOne, JButton btnTwo) {
        this.lstButton = lstButton;
        this.level = level;
        this.btnOne = btnOne;
        this.btnTwo = btnTwo;
    }

    public void Action() {
        switch (level) {
            case 1:
                Level_1(btnOne);
                Level_1(btnTwo);
                break;
            case 2:
                if (btnOne.getLocation().y < btnTwo.getLocation().y) {
                    Level_2(btnTwo);
                    Level_2(btnOne);
                } else {
                    Level_2(btnOne);
                    Level_2(btnTwo);
                }
                break;
            case 3:
                if (btnOne.getLocation().y < btnTwo.getLocation().y) {
                    Level_3(btnOne);
                    Level_3(btnTwo);
                } else {
                    Level_3(btnTwo);
                    Level_3(btnOne);
                }
                break;
            case 4:
                if (btnOne.getLocation().x < btnTwo.getLocation().x) {
                    Level_4(btnOne);
                    Level_4(btnTwo);
                } else {
                    Level_4(btnTwo);
                    Level_4(btnOne);
                }
                break;
            case 5:
                if (btnOne.getLocation().x < btnTwo.getLocation().x) {
                    Level_5(btnTwo);
                    Level_5(btnOne);
                } else {
                    Level_5(btnOne);
                    Level_5(btnTwo);
                }
                break;
            case 6:
                int index = lstButton.indexOf(btnOne);
                int total = (Settings.Dong * Settings.Cot) / 2;
                if (index < total) {
                    if (btnOne.getLocation().y > btnTwo.getLocation().y) {
                        Level_6(btnOne);
                        Level_6(btnTwo);
                    } else {
                        Level_6(btnTwo);
                        Level_6(btnOne);
                    }
                } else {
                    if (btnOne.getLocation().y < btnTwo.getLocation().y) {
                        Level_6(btnOne);
                        Level_6(btnTwo);
                    } else {
                        Level_6(btnTwo);
                        Level_6(btnOne);
                    }
                }
                break;
            case 7:
                index = lstButton.indexOf(btnOne);
                total = index / Settings.Cot * Settings.Cot + (Settings.Cot - 2) / 2;
                if (index < total) {
                    if (btnOne.getLocation().x > btnTwo.getLocation().x) {
                        Level_7(btnOne);
                        Level_7(btnTwo);
                    } else {
                        Level_7(btnTwo);
                        Level_7(btnOne);
                    }
                } else {
                    if (btnOne.getLocation().x > btnTwo.getLocation().x) {
                        Level_7(btnTwo);
                        Level_7(btnOne);
                    } else {
                        Level_7(btnOne);
                        Level_7(btnTwo);
                    }
                }
                break;
            case 8:
                index = lstButton.indexOf(btnOne);
                total = (Settings.Dong * Settings.Cot) / 2;
                if (index < total) {
                    if (btnOne.getLocation().y < btnTwo.getLocation().y) {
                        Level_8(btnOne);
                        Level_8(btnTwo);
                    } else {
                        Level_8(btnTwo);
                        Level_8(btnOne);
                    }
                } else {
                    if (btnOne.getLocation().y > btnTwo.getLocation().y) {
                        Level_8(btnOne);
                        Level_8(btnTwo);
                    } else {
                        Level_8(btnTwo);
                        Level_8(btnOne);
                    }
                }
                break;
            case 9:
                index = lstButton.indexOf(btnOne);
                total = index / Settings.Cot * Settings.Cot + (Settings.Cot - 2) / 2;
                if (index < total) {
                    if (btnOne.getLocation().x < btnTwo.getLocation().x) {
                        Level_9(btnOne);
                        Level_9(btnTwo);
                    } else {
                        Level_9(btnTwo);
                        Level_9(btnOne);
                    }
                } else {
                    if (btnOne.getLocation().x < btnTwo.getLocation().x) {
                        Level_9(btnTwo);
                        Level_9(btnOne);
                    } else {
                        Level_9(btnOne);
                        Level_9(btnTwo);
                    }
                }
                break;
            case 10:
                if (btnOne.getLocation().x > btnTwo.getLocation().x) {
                    Level_10(btnOne);
                    Level_10(btnTwo);
                } else {
                    Level_10(btnTwo);
                    Level_10(btnOne);
                }
                break;
            case 11:
                if (btnOne.getLocation().x < btnTwo.getLocation().x) {
                    Level_11(btnOne);
                    Level_11(btnTwo);
                } else {
                    Level_11(btnTwo);
                    Level_11(btnOne);
                }
                break;
            case 12:
                if (btnOne.getLocation().x > btnTwo.getLocation().x) {
                    Level_12(btnOne);
                    Level_12(btnTwo);
                } else {
                    Level_12(btnTwo);
                    Level_12(btnOne);
                }
                break;
            case 13:
                if (btnOne.getLocation().x < btnTwo.getLocation().x) {
                    Level_13(btnOne);
                    Level_13(btnTwo);
                } else {
                    Level_13(btnTwo);
                    Level_13(btnOne);
                }
                break;
            default:
                break;
        }
    }

    private void Level_1(JButton btnOne) {
        btnOne.setVisible(false);
    }

    private void Level_2(JButton btnOne) {
        int total = Settings.Dong * Settings.Cot;
        int index = lstButton.indexOf(btnOne);
        for (int i = index; i < total; i = i + Settings.Cot) {
            if (lstButton.get(i + Settings.Cot).isVisible() == true) {
                lstButton.get(i).setIcon(lstButton.get(i + Settings.Cot).getIcon());
                lstButton.get(i).setName(lstButton.get(i + Settings.Cot).getName());
                lstButton.get(i).setBorder(null);
            } else {
                lstButton.get(i).setVisible(false);
                break;
            }
        }
    }

    private void Level_3(JButton btnOne) {
        int index = lstButton.indexOf(btnOne);
        for (int i = index; i > Settings.Cot - 1; i = i - Settings.Cot) {
            if ((lstButton.get(i - Settings.Cot).isVisible()) == true) {
                lstButton.get(i).setIcon(lstButton.get(i - Settings.Cot).getIcon());
                lstButton.get(i).setName(lstButton.get(i - Settings.Cot).getName());
                lstButton.get(i).setBorder(null);
            } else {
                lstButton.get(i).setVisible(false);
                break;
            }
        }
    }

    private void Level_4(JButton btnOne) {
        int index = lstButton.indexOf(btnOne);
        int total = Settings.Cot * Settings.Cot;
        for (int i = index; i > index / total; i--) {
            if (lstButton.get(i - 1).isVisible() == true) {
                lstButton.get(i).setIcon(lstButton.get(i - 1).getIcon());
                lstButton.get(i).setName(lstButton.get(i - 1).getName());
                lstButton.get(i).setBorder(null);
            } else {
                lstButton.get(i).setVisible(false);
                break;
            }
        }
    }

    private void Level_5(JButton btnOne) {
        int index = lstButton.indexOf(btnOne);
        for (int i = index; i < (index / Settings.Cot + 1) * Settings.Cot - 1; i++) {
            if (lstButton.get(i + 1).isVisible() == true) {
                lstButton.get(i).setIcon(lstButton.get(i + 1).getIcon());
                lstButton.get(i).setName(lstButton.get(i + 1).getName());
                lstButton.get(i).setBorder(null);
            } else {
                lstButton.get(i).setVisible(false);
                break;
            }
        }
    }

    private void Level_6(JButton btnOne) {
        int index = lstButton.indexOf(btnOne);
        int total = (Settings.Dong * Settings.Cot) / 2;
        if (index < total) {
            for (int i = index; i < total; i = i + Settings.Cot) {
                if (i > total - Settings.Cot) {
                    lstButton.get(i).setVisible(false);
                } else {
                    if (lstButton.get(i + Settings.Cot).isVisible() == true) {
                        lstButton.get(i).setIcon(lstButton.get(i + Settings.Cot).getIcon());
                        lstButton.get(i).setName(lstButton.get(i + Settings.Cot).getName());
                        lstButton.get(i).setBorder(null);
                    } else {
                        lstButton.get(i).setVisible(false);
                    }
                }
            }
        } else {
            for (int i = index; i > total; i = i - Settings.Cot) {
                if (i < total + Settings.Cot) {
                    lstButton.get(i).setVisible(false);
                } else {
                    if (lstButton.get(i - Settings.Cot).isVisible() == true) {
                        lstButton.get(i).setIcon(lstButton.get(i - Settings.Cot).getIcon());
                        lstButton.get(i).setName(lstButton.get(i - Settings.Cot).getName());
                        lstButton.get(i).setBorder(null);
                    } else {
                        lstButton.get(i).setVisible(false);
                    }
                }
            }
        }
    }

    private void Level_7(JButton btnOne) {
        int i;
        int index = lstButton.indexOf(btnOne);
        int total = index / Settings.Cot * Settings.Cot + (Settings.Cot - 2) / 2;
        if (index <= total) {
            for (i = index; i <= total; i++) {
                if (i + 1 <= total && (lstButton.get(i + 1).isVisible() == true)) {
                    lstButton.get(i).setIcon(lstButton.get(i + 1).getIcon());
                    lstButton.get(i).setName(lstButton.get(i + 1).getName());
                    lstButton.get(i).setBorder(null);
                } else {
                    lstButton.get(i).setVisible(false);
                    break;
                }
            }
        } else {
            for (i = index; i > total; i--) {
                if (i - 1 > total && (lstButton.get(i - 1).isVisible() == true)) {
                    lstButton.get(i).setIcon(lstButton.get(i - 1).getIcon());
                    lstButton.get(i).setName(lstButton.get(i - 1).getName());
                    lstButton.get(i).setBorder(null);
                } else {
                    lstButton.get(i).setVisible(false);
                    break;
                }
            }
        }
    }

    private void Level_8(JButton btnOne) {
        int index = lstButton.indexOf(btnOne);
        int round = (Settings.Dong * Settings.Cot) / 2;
        int total = Settings.Dong * Settings.Cot;
        if (index < round) {
            for (int i = index; i > Settings.Cot; i = i - Settings.Cot) {
                if (lstButton.get(i - Settings.Cot).isVisible() == true) {
                    lstButton.get(i).setIcon(lstButton.get(i - Settings.Cot).getIcon());
                    lstButton.get(i).setName(lstButton.get(i - Settings.Cot).getName());
                    lstButton.get(i).setBorder(null);
                } else {
                    lstButton.get(i).setVisible(false);
                }
            }
        } else {
            for (int i = index; i < total - Settings.Cot; i = i + Settings.Cot) {
                if (lstButton.get(i + Settings.Cot).isVisible() == true) {
                    lstButton.get(i).setIcon(lstButton.get(i + Settings.Cot).getIcon());
                    lstButton.get(i).setName(lstButton.get(i + Settings.Cot).getName());
                    lstButton.get(i).setBorder(null);
                } else {
                    lstButton.get(i).setVisible(false);
                }
            }
        }
    }

    private void Level_9(JButton btnOne) {
        int i;
        int index = lstButton.indexOf(btnOne);
        int total = index / Settings.Cot * Settings.Cot + (Settings.Cot - 2) / 2;
        int round = index / Settings.Cot * Settings.Cot;
        if (index <= total) {
            for (i = index; i > round; i--) {
                if (i - 1 <= total && (lstButton.get(i - 1).isVisible() == true)) {
                    lstButton.get(i).setIcon(lstButton.get(i - 1).getIcon());
                    lstButton.get(i).setName(lstButton.get(i - 1).getName());
                    lstButton.get(i).setBorder(null);
                } else {
                    lstButton.get(i).setVisible(false);
                    break;
                }
            }
        } else {
            round = index / Settings.Cot * Settings.Cot + Settings.Cot - 1;
            for (i = index; i < round; i++) {
                if (i + 1 > total && (lstButton.get(i + 1).isVisible() == true)) {
                    lstButton.get(i).setIcon(lstButton.get(i + 1).getIcon());
                    lstButton.get(i).setName(lstButton.get(i + 1).getName());
                    lstButton.get(i).setBorder(null);
                } else {
                    lstButton.get(i).setVisible(false);
                    break;
                }
            }
        }
    }

    private void Level_10(JButton btnOne) {
        int i;
        int index = lstButton.indexOf(btnOne);
        int total = Settings.Cot * Settings.Dong;
        for (i = index; i < total - Settings.Cot; i += (1 + Settings.Cot)) {
            if (i + 1 + Settings.Cot < total - Settings.Cot && (lstButton.get(i + 1 + Settings.Cot).isVisible() == true)) {
                lstButton.get(i).setIcon(lstButton.get(i + 1 + Settings.Cot).getIcon());
                lstButton.get(i).setName(lstButton.get(i + 1 + Settings.Cot).getName());
                lstButton.get(i).setBorder(null);
            } else {
                lstButton.get(i).setVisible(false);
                break;
            }
        }
    }

    private void Level_11(JButton btnOne) {
        int i;
        int index = lstButton.indexOf(btnOne);
        int total = Settings.Cot * Settings.Dong;
        for (i = index; i < total - Settings.Cot; i += (Settings.Cot - 1)) {
            if (i - 1 + Settings.Cot < total - Settings.Cot && (lstButton.get(i - 1 + Settings.Cot).isVisible() == true)) {
                lstButton.get(i).setIcon(lstButton.get(i - 1 + Settings.Cot).getIcon());
                lstButton.get(i).setName(lstButton.get(i - 1 + Settings.Cot).getName());
                lstButton.get(i).setBorder(null);
            } else {
                lstButton.get(i).setVisible(false);
                break;
            }
        }
    }

    private void Level_12(JButton btnOne) {
        int i;
        int index = lstButton.indexOf(btnOne);
        for (i = index; i > Settings.Cot; i = (i - Settings.Cot + 1)) {
            if (i + 1 - Settings.Cot > Settings.Cot && (lstButton.get(i + 1 - Settings.Cot).isVisible() == true)) {
                lstButton.get(i).setIcon(lstButton.get(i + 1 - Settings.Cot).getIcon());
                lstButton.get(i).setName(lstButton.get(i + 1 - Settings.Cot).getName());
                lstButton.get(i).setBorder(null);
            } else {
                lstButton.get(i).setVisible(false);
                break;
            }
        }
    }
    
    private void Level_13(JButton btnOne) {
        int i;
        int index = lstButton.indexOf(btnOne);
        for (i = index; i > Settings.Cot; i = (i - Settings.Cot - 1)) {
            if (i - 1 - Settings.Cot > Settings.Cot && (lstButton.get(i - 1 - Settings.Cot).isVisible() == true)) {
                lstButton.get(i).setIcon(lstButton.get(i - 1 - Settings.Cot).getIcon());
                lstButton.get(i).setName(lstButton.get(i - 1 - Settings.Cot).getName());
                lstButton.get(i).setBorder(null);
            } else {
                lstButton.get(i).setVisible(false);
                break;
            }
        }
    }
}
