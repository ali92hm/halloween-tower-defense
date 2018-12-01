package views;

import javax.swing.*;

/**
 * Panel not Used
 *
 * @author Scorpion
 */

public class MapStartPanel extends ImagePanel {

    private static final long serialVersionUID = 1L;
    private JButton escButton;

    /**
     * Creates the start panel for the map.
     *
     * @param imageName
     */

    public MapStartPanel(final String imageName) {
        super(DriverView.getImage(imageName, 900, 650), 0, 0);
        this.setLayout(null);
        this.escButton = new JButton("Esc");
        this.escButton.setBounds(625, 604, 75, 35);
        this.add(this.escButton);
    }

    /**
     * returns the esc button
     *
     * @return JButton
     */

    public JButton getEscButton() {
        return this.escButton;
    }
}
