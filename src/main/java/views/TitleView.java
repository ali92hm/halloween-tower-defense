package views;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The starting view of the game
 *
 * @author Scorpion
 */

public class TitleView extends ImagePanel {

    private static final long serialVersionUID = 1L;

    /**
     * constructs the title view with
     * given image
     *
     * @param image
     */

    public TitleView(BufferedImage image) {
        super(image, null, null);
        this.addGridBagLayout();
    }

    /**
     * sets the text on the panel
     */

    public void addGridBagLayout() {

        this.setOpaque(true);
        this.setLayout(new GridBagLayout());
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        final JLabel title = new JLabel("Halloween TD");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 50));
        title.setForeground(Color.ORANGE);
        constraints.ipady = 200;
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(title, constraints);


        JLabel filler = new JLabel();
        constraints.ipady = 200;
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(filler, constraints);

        final JLabel startText = new JLabel("Press Any Key To Start");
        startText.setHorizontalAlignment(SwingConstants.CENTER);
        startText.setFont(new Font("Serif", Font.BOLD, 20));
        startText.setForeground(Color.YELLOW);
        constraints.ipady = 100;
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(startText, constraints);

        filler = new JLabel();
        constraints.ipady = 150;
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(filler, constraints);
    }
}
