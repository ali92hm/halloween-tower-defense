package views;

import javax.swing.*;
import java.awt.*;

public class MapTutorialPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton escButton;

    public MapTutorialPanel(final String imageName) {
        this.setLayout(new BorderLayout());

        ImagePanel tutorial = new ImagePanel(DriverView.getImage(imageName, 700, 650), 0, 0);
        JPanel buttonPanel = new JPanel();
        JButton skipButton = new JButton("Skip Tutorial");
        JPanel fillerPanel = new JPanel();
        JButton continueButton = new JButton("Continue Tutorial");

        buttonPanel.setOpaque(false);
        fillerPanel.setOpaque(false);
        fillerPanel.setPreferredSize(new Dimension(30, 30));

        buttonPanel.add(skipButton);
        buttonPanel.add(fillerPanel);
        buttonPanel.add(continueButton);

        this.add(tutorial, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

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
