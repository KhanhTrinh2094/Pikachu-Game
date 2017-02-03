package SwingCustomize;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JProgressBar;

public class TimeProgress extends JProgressBar {

    private static GradientPaint gradient;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth() - 1;
        int height = getHeight() - 1;
        gradient = new GradientPaint(0, 0, Color.RED, width, height, new Color(0x00ff00));

        if (isOpaque()) {
            g2d.setColor(getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        g2d.setPaint(gradient);
        g2d.drawLine(1, 0, width - 1, 0);
        g2d.drawLine(1, height, width - 1, height);
        g2d.drawLine(0, 1, 0, height - 1);
        g2d.drawLine(width, 1, width, height - 1);

        int min = getMinimum();
        int max = getMaximum();
        int total = max - min;
        float dx = (float) (width - 2) / (float) total;
        int value = getValue() - 1;
        int progress = 0;
        if (value == max) {
            progress = width - 1;
        } else {
            progress = (int) (dx * getValue());
        }
        if (value != 0) {
            g2d.fillRect(1, 1, progress, height - 1);
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
    }
}
