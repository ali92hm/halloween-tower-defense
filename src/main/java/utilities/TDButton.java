package utilities;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * custom button for the tower defence game
 *
 * @author Scorpion
 */

public class TDButton extends JButton implements MouseListener {

    private static final long serialVersionUID = 1L;
    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage image3;
    private boolean toggleButton = true;
    private int buttonState = 0;

    /**
     * constructor for the custom
     * Tower Defense button without
     * a name
     */

    public TDButton() {
        super();
        this.setUpButton();
    }

    /**
     * constructor for the custom
     * Tower Defense button with
     * a name
     *
     * @param name
     */

    public TDButton(final String name) {
        super(name);
        this.setUpButton();
    }

    /**
     * constructor for the custom
     * Tower Defense button with
     * an image
     *
     * @param image
     */

    public TDButton(final BufferedImage image) {
        super();
        this.setIcon(new ImageIcon(image));
        this.setUpButton();
    }

    /**
     * constructor for the custom
     * Tower defense button with
     * three button states
     *
     * @param image1
     * @param image2
     * @param image3
     */

    public TDButton(final BufferedImage image1, final BufferedImage image2, final BufferedImage image3) {
        super();
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.setIcon(new ImageIcon(this.image1));
        this.setUpButton();
    }

    /**
     * sets the button up to have no
     * border, be see through, and
     * have a new mouse listener
     */

    private void setUpButton() {
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.addMouseListener(this);
    }

    /**
     * resets the button back to its
     * starting state with image 1
     */

    public void resetButton() {
        if (this.image1 != null && this.image2 != null) {
            this.setIcon(new ImageIcon(this.image1));
            this.buttonState = 0;
        }
    }

    /**
     * sets the buttons image to
     * image 1
     */

    public void setImage1() {
        this.setIcon(new ImageIcon(this.image1));
        this.buttonState = 0;
    }

    /**
     * sets the buttons image to
     * image 2
     */

    public void setImage2() {
        this.setIcon(new ImageIcon(this.image2));
        this.toggleButton = false;
        this.buttonState = 1;
    }

    /**
     * sets the buttons image to
     * image 3
     */

    public void setImage3() {
        this.setIcon(new ImageIcon(this.image3));
        this.toggleButton = true;
        this.buttonState = 2;
    }

    /**
     * reset the toggle state setting
     * the active image to image 2
     */

    public void resetToggle() {
        this.toggleButton = true;
    }

    /**
     * toggles the button between
     * state 1 and 2 using images
     * 2 and 3
     */

    public void setToggleImage() {
        if (this.image2 != null && this.image3 != null) {
            if (this.toggleButton) {
                this.setIcon(new ImageIcon(this.image3));
                this.buttonState = 2;
            } else {
                this.setIcon(new ImageIcon(this.image2));
                this.buttonState = 1;
            }
        }
    }

    /**
     * triggered any time the mouse clicks this button
     */

    public void mouseClicked(MouseEvent arg0) {
    }

    /**
     * triggered any time the mouse enters
     * the area over the button
     */

    public void mouseEntered(MouseEvent arg0) {
        if (this.isEnabled()) {
            this.setBorder(new TDBorder(3));
        }
    }

    /**
     * triggered any time the mouse leaves
     * the area over the button
     */

    public void mouseExited(MouseEvent arg0) {
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    /**
     * triggered anytime the mouse is pressed over this button
     */

    public void mousePressed(MouseEvent arg0) {
        if (!this.isEnabled()) {
            return;
        }
    }

    /**
     * triggered any time the mouse
     * releases over this button
     */

    public void mouseReleased(MouseEvent arg0) {
        if (!this.isEnabled()) {
            return;
        }
    }

    /**
     * returns the button state
     * which is based on which image
     * is active
     *
     * @return int
     */

    public int getButtonState() {
        return buttonState;
    }
}
