package views;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Image panel extends JPanel. It is just a JPanel with a background image.
 *
 * @author Reggie
 */

public class ImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final Integer x;
    private final Integer y;
    protected BufferedImage image;

    /**
     * Constructor for an image panel. Takes an image and a location to place the image.
     *
     * @param image
     * @param x
     * @param y
     */

    public ImagePanel(final BufferedImage image, final Integer x, final Integer y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    /**
     * Paints the image on top of the panel.
     */

    public void paintComponent(final Graphics g) {
        super.paintComponents(g);
        final int x = (this.x == null) ? (this.getWidth() - image.getWidth()) / 2 : this.x;
        final int y = (this.y == null) ? (this.getHeight() - image.getHeight()) / 2 : this.y;
        g.drawImage(image, x, y, this);
    }
}
