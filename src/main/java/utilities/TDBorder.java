package utilities;

import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 * this is a custom border for our
 * buttons on the main view
 *
 * @author Scorpion
 */

public class TDBorder extends AbstractBorder {

    private static final long serialVersionUID = 1L;
    private int thickness;

    /**
     * constructer for the TDBorder
     *
     * @param thickness
     */

    public TDBorder(int thickness) {
        this.thickness = thickness;
    }

    /**
     * paints the border to the component
     */

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(0.81f, 0.81f, 0.81f, 0.2f));
        g2.setStroke(new BasicStroke(thickness));
        g2.drawRect(x, y, width - 1, height - 1);
    }

    /**
     * sets the insets of the border
     */

    public Insets getBorderInsets(Component c) {
        return new Insets(thickness, thickness, thickness, thickness);
    }

    /**
     * sets the insets of the border
     */

    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = thickness;
        return insets;
    }
}
