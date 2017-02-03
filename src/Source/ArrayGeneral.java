package Source;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;

public class ArrayGeneral {

    private int total, cot;
    private ArrayList<JButton> lstButton;
    private int arrButton[];
    private boolean random;

    public ArrayGeneral(){
        
    }
    
    public ArrayGeneral(int total, int cot, ArrayList<JButton> lstButton, boolean random) {
        this.total = total;
        this.cot = cot;
        this.lstButton = lstButton;
        this.random = random;
        this.arrButton = new int[total];
    }
    
    public int[] createArray() {
        Random intRandom = new Random();
        for (int i = 0; i < total; i++) {
            if ((i <= (cot - 1) || i >= (total - cot) || i % cot == 0 || (i + 1) % cot == 0) == false) {
                arrButton[i] = intRandom.nextInt(35);
            } else {
                arrButton[i] = -1;
            }
        }
        
        for (int i = 0; i < 35; i++) {
            if (checkArray(i) % 2 != 0) {
                moveArray(i);
            }
        }
        return arrButton;
    }
    
    public int[] reRandom() {
        Random intRandom = new Random();
        for (int i = 0; i < total; i++) {
            if (lstButton.get(i).isVisible() == true) {
                arrButton[i] = intRandom.nextInt(36);
            }
        }
        for (int i = 0; i < 35; i++) {

            if (checkArray(i) % 2 != 0) {
                moveArray(i);
            }
        }
        return arrButton;
    }

    private int checkArray(int m) {
        int d = 0;
        for (int i = 0; i < total; i++) {
            if (random) {
                if (lstButton.get(i).isVisible() == true) {
                    if (m == arrButton[i]) {
                        d++;
                    }
                }
            } else {
                if (m == arrButton[i]) {
                    d++;
                }
            }
        }
        return d;
    }

    private void moveArray(int m) {
        for (int i = 0; i < total; i++) {
            if (random) {
                if (lstButton.get(i).isVisible() == true) {
                    if (arrButton[i] == m) {
                        arrButton[i]++;
                        return;
                    }
                }
            } else {
                if (arrButton[i] == m) {
                    arrButton[i]++;
                    return;
                }
            }
        }
    }
}
