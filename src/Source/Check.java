package Source;

import Panel.ButtonPanel;
import java.util.ArrayList;
import javax.swing.JButton;

public class Check {

    ButtonPanel pnButton;
    int timeCount = 0;
    ArrayList<Integer> arr = new ArrayList();
    boolean draw;

    public Check(ButtonPanel pnButton) {
        this.pnButton = pnButton;
    }

    public boolean check(JButton btnA, JButton btnB, ArrayList<JButton> lstButton, boolean draw) {
        this.draw = draw;
        boolean flag = false;
        if (checkNgang(btnA, btnB, lstButton, draw)) {
            flag = true;
        } else if (checkDoc(btnA, btnB, lstButton, draw)) {
            flag = true;
        } else if (checkBa(btnA, btnB, lstButton)) {
            flag = true;
        } else {
            flag = checkCheo(btnA, btnB, lstButton, draw);
        }

        if (flag) {
            pnButton.Go(arr);
            pnButton.repaint();
        }
        return flag;
    }

    private boolean checkNgang(JButton j1, JButton j2, ArrayList<JButton> lstButton, boolean draw) {
        int value1, value2;
        if (j1.getLocation().y == j2.getLocation().y) {
            if (lstButton.indexOf(j1) < lstButton.indexOf(j2)) {
                value1 = lstButton.indexOf(j1);
                value2 = lstButton.indexOf(j2);
            } else {
                value1 = lstButton.indexOf(j2);
                value2 = lstButton.indexOf(j1);
            }

            for (int i = value1 + 1; i < value2; i++) {
                if (lstButton.get(i).isVisible() == true) {
                    return false;
                }
            }
            if (draw) {
                add(j1, j2);
            }
            pnButton.repaint();
            return true;
        }
        return false;
    }

    private boolean checkDoc(JButton j1, JButton j2, ArrayList<JButton> lstButton, boolean draw) {

        int value1, value2;
        if (j1.getLocation().x == j2.getLocation().x) {
            if (lstButton.indexOf(j1) < lstButton.indexOf(j2)) {
                value1 = lstButton.indexOf(j1);
                value2 = lstButton.indexOf(j2);
            } else {
                value1 = lstButton.indexOf(j2);
                value2 = lstButton.indexOf(j1);
            }
            for (int i = (value1 + Settings.Cot); i < value2; i += (Settings.Cot)) {
                if (lstButton.get(i).isVisible() == true) {
                    return false;
                }
            }
            if (draw) {
                add(j1, j2);
            }
            return true;
        }
        return false;
    }

    private boolean checkCheo(JButton image_1, JButton image_2, ArrayList<JButton> lstButton, boolean draw) {
        int j1 = image_1.getLocation().y;
        int j2 = image_2.getLocation().y;
        if (image_1.getLocation().x != image_2.getLocation().x && j1 != j2) {
            int index_1, index_2;
            JButton btHight, btLow;
            if (j1 < j2) {
                btHight = image_1;
                btLow = image_2;
            } else {
                btHight = image_2;
                btLow = image_1;
            }
            if (btHight.getLocation().x > btLow.getLocation().x) {
                index_1 = lstButton.indexOf(btHight) - Settings.Cot + (lstButton.indexOf(btLow) - lstButton.indexOf(btHight)) % Settings.Cot;
                index_2 = lstButton.indexOf(btLow) + Settings.Cot - (lstButton.indexOf(btLow) - lstButton.indexOf(btHight)) % Settings.Cot;
                if ((lstButton.get(index_1)).isVisible() == false && checkNgang(btHight, lstButton.get(index_1), lstButton, false) && checkDoc(btLow, lstButton.get(index_1), lstButton, false)) {
                    if (draw) {
                        add(btLow, lstButton.get(index_1));
                        add(btHight, lstButton.get(index_1));
                    }
                    return true;
                }
                if ((lstButton.get(index_2)).isVisible() == false && checkNgang(btLow, lstButton.get(index_2), lstButton, false) && checkDoc(btHight, lstButton.get(index_2), lstButton, false)) {
                    if (draw) {
                        add(btLow, lstButton.get(index_2));
                        add(btHight, lstButton.get(index_2));
                    }
                    return true;
                }
            } else {
                index_1 = lstButton.indexOf(btHight) + (lstButton.indexOf(btLow) - lstButton.indexOf(btHight)) % Settings.Cot;
                index_2 = lstButton.indexOf(btLow) - (lstButton.indexOf(btLow) - lstButton.indexOf(btHight)) % Settings.Cot;
                if ((lstButton.get(index_1)).isVisible() == false && checkNgang(btHight, lstButton.get(index_1), lstButton, false) && checkDoc(btLow, lstButton.get(index_1), lstButton, false)) {
                    if (draw) {
                        add(btHight, lstButton.get(index_1));
                        add(btLow, lstButton.get(index_1));
                    }
                    return true;
                }
                if ((lstButton.get(index_2)).isVisible() == false && checkNgang(btLow, lstButton.get(index_2), lstButton, false) && checkDoc(btHight, lstButton.get(index_2), lstButton, false)) {
                    if (draw) {
                        add(btLow, lstButton.get(index_2));
                        add(btHight, lstButton.get(index_2));
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkBa(JButton image_1, JButton image_2, ArrayList<JButton> lstButton) {
        // Chay ve phai
        for (int i = lstButton.indexOf(image_1) + 1; i < (lstButton.indexOf(image_1) / Settings.Cot + 1) * Settings.Cot; i++) {
            if ((lstButton.get(i)).isVisible() == false) {
                if (checkNgang(image_1, lstButton.get(i), lstButton, false) && checkCheo(image_2, lstButton.get(i), lstButton, false)) {
                    checkCheo(image_2, lstButton.get(i), lstButton, true);
                    add(image_1, lstButton.get(i));
                    return true;
                }
            } else {
                break;
            }
        }

        // Chay ve trai
        for (int i = lstButton.indexOf(image_1) - 1; i >= (lstButton.indexOf(image_1) / Settings.Cot) * Settings.Cot; i--) {
            if ((lstButton.get(i)).isVisible() == false) {
                if (checkNgang(image_1, lstButton.get(i), lstButton, false) && checkCheo(image_2, lstButton.get(i), lstButton, false)) {
                    add(image_1, lstButton.get(i));
                    checkCheo(image_2, lstButton.get(i), lstButton, true);
                    return true;
                }
            } else {
                break;
            }
        }

        // Chay len tren
        for (int j = lstButton.indexOf(image_1) + Settings.Cot; j < Settings.Total(); j = j + Settings.Cot) {
            if ((lstButton.get(j)).isVisible() == false) {
                if (checkDoc(image_1, lstButton.get(j), lstButton, false) && checkCheo(image_2, lstButton.get(j), lstButton, false)) {
                    add(image_1, lstButton.get(j));
                    checkCheo(image_2, lstButton.get(j), lstButton, true);
                    return true;
                }
            } else {
                break;
            }
        }

        // Chay xuong duoi
        for (int j = lstButton.indexOf(image_1) - Settings.Cot; j >= 0; j = j - Settings.Cot) {
            if ((lstButton.get(j)).isVisible() == false) {
                if (checkDoc(image_1, lstButton.get(j), lstButton, false) && checkCheo(image_2, lstButton.get(j), lstButton, false)) {
                    add(image_1, lstButton.get(j));
                    checkCheo(image_2, lstButton.get(j), lstButton, true);
                    return true;
                }
            } else {
                break;
            }
        }

        return false;
    }

    private void add(JButton btnOne, JButton btnTwo) {
        if (draw) {
            arr.add(btnOne.getLocation().x + 26);
            arr.add(btnOne.getLocation().y + 32);
            arr.add(btnTwo.getLocation().x + 26);
            arr.add(btnTwo.getLocation().y + 32);
        }
    }
}
