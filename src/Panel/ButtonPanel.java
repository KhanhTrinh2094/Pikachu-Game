package Panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

    ArrayList<Integer> arr = new ArrayList();
    Graphics2D g2d;

    public ButtonPanel() {
    }

    public void Go(ArrayList<Integer> arr) {
        this.arr = arr;
        paintComponent(g2d);
    }
    
    public void Restart() {
        arr.clear();
        paintComponent(g2d);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g2d = (Graphics2D) g;
        this.g2d.setColor(Color.red);
        this.g2d.setStroke(new BasicStroke(3));

        if (arr.size() == 12) {
            g2d.draw(new Line2D.Float(arr.get(0), arr.get(1), arr.get(2), arr.get(3)));
            g2d.draw(new Line2D.Float(arr.get(4), arr.get(5), arr.get(6), arr.get(7)));
            g2d.draw(new Line2D.Float(arr.get(8), arr.get(9), arr.get(10), arr.get(11)));
        } else if(arr.size() == 4){
            g2d.draw(new Line2D.Float(arr.get(0), arr.get(1), arr.get(2), arr.get(3)));
        }
    }
}
