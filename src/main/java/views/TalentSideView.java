package views;

import utilities.TDButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Shows the Talent Tree side panel
 *
 * @author Scorpion
 */

public class TalentSideView extends JPanel {

    private static final long serialVersionUID = 1L;

    private TDButton back = new TDButton(DriverView.getImage("Back Button.png", 75, 30));
    private TDButton accept = new TDButton(DriverView.getImage("AcceptButton.png", 150, 50));
    private JLabel talentIcon = new JLabel("");
    private JLabel talentName = new JLabel("");
    private JLabel talentDescription = new JLabel("");
    private JLabel talentLine2 = new JLabel("");

    /**
     * Constructor for the talent side view. Contains all of the information about a talent that the user has selected.
     */

    public TalentSideView() {

        accept.setPreferredSize(new Dimension(150, 50));
        accept.setEnabled(false);

        JPanel backPanel = new JPanel();
        back.setPreferredSize(new Dimension(75, 30));
        backPanel.add(back);

        JPanel talentPanel = new JPanel();
        talentPanel.add(talentIcon);
        talentPanel.add(talentName);
        talentPanel.add(talentDescription);
        talentPanel.add(talentLine2);

        JPanel acceptPanel = new JPanel();
        acceptPanel.add(accept);

        this.setLayout(new BoxLayout(this, 1));
        this.setPreferredSize(new Dimension(194, 400));

        this.add(backPanel);
        this.add(talentPanel);
        this.add(acceptPanel);
    }

    /**
     * Sets all of the information about a talent inside the side panel.
     *
     * @param image
     * @param name
     * @param line1
     * @param line2
     */

    public void setTalentInfo(BufferedImage image, String name, String line1, String line2) {

        talentIcon.setIcon(new ImageIcon(image));
        talentName.setText(name);
        talentDescription.setText(line1);
        talentLine2.setText(line2);

    }

    /**
     * Getter for the back button.
     *
     * @return TDButton
     */

    public TDButton getBack() {
        return this.back;
    }

    /**
     * Getter for the accept button.
     *
     * @return TDButton
     */

    public TDButton getAccept() {
        return this.accept;
    }
}
