package com.halloween_tower_defense.views;

import com.halloween_tower_defense.utilities.TDButton;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Shows the Talent Tree side panel
 *
 * @author Scorpion
 */

public class TalentSideView extends JPanel {

  private static final long serialVersionUID = 1L;

  private final TDButton back = new TDButton(GameView.getImage("Back Button.png", 75, 30));
  private final TDButton accept = new TDButton(GameView.getImage("AcceptButton.png", 150, 50));
  private final JLabel talentIcon = new JLabel("");
  private final JLabel talentName = new JLabel("");
  private final JLabel talentDescription = new JLabel("");
  private final JLabel talentLine2 = new JLabel("");

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
