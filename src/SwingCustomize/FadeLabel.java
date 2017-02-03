
package SwingCustomize;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

public class FadeLabel extends JLabel {
    
    float opacity;
    
    public FadeLabel(){
        setOpacity(1f);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getOpacity()));
        super.paintComponent(g2d);
        g2d.dispose();
    }
    
    public void setOpacity(float opacity){
        this.opacity = opacity;
        repaint();
    }
    
    public float getOpacity(){
        return this.opacity;
    }
    
    
}
