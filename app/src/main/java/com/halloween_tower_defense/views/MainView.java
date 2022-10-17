package com.halloween_tower_defense.views;

import com.halloween_tower_defense.utilities.ImageUtility;
import com.halloween_tower_defense.utilities.SelectButtonGroup;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * The main view of the game displaying the
 * maps, difficulty, and start button to the user
 *
 * @author Scorpion
 */

public class MainView extends ImagePanel {

  private static final long serialVersionUID = 1L;
  private final Border selectionBorder = new LineBorder(Color.YELLOW, 5);
  private final Border deselectionBorder = new LineBorder(Color.WHITE, 5);
  private final JToggleButton map1 = new JToggleButton();
  private final JToggleButton map2 = new JToggleButton();
  private final JToggleButton map3 = new JToggleButton();
  private final JToggleButton easy =
      new JToggleButton(new ImageIcon(ImageUtility.getImage("EasyButton.png", 65, 28)));
  private final JToggleButton medium =
      new JToggleButton(new ImageIcon(ImageUtility.getImage("MediumButton.png", 60, 28)));
  private final JToggleButton hard =
      new JToggleButton(new ImageIcon(ImageUtility.getImage("HardButton.png", 65, 28)));
  private final JButton start =
      new JButton(new ImageIcon(ImageUtility.getImage("StartButton.png", 125, 50)));
  private final JButton exit =
      new JButton(new ImageIcon(ImageUtility.getImage("ExitButton.png", 90, 40)));
  private final JButton info = new JButton(new ImageIcon(
      ImageUtility.getImage("InfoButton.png", 90, 40)));
  private final SelectButtonGroup mapGroup = new SelectButtonGroup();
  private final SelectButtonGroup difficultyGroup = new SelectButtonGroup();
  private final JPanel buttonPanel = new JPanel();
  private final GridBagConstraints c = new GridBagConstraints();

  /**
   * Constructor for the main view. Takes an image to set as the background.
   *
   * @param image
   */

  public MainView(BufferedImage image) {

    super(image, null, null);

    map1.setBorder(deselectionBorder);
    map2.setBorder(deselectionBorder);
    map3.setBorder(deselectionBorder);
    easy.setBorder(deselectionBorder);
    medium.setBorder(deselectionBorder);
    hard.setBorder(deselectionBorder);
    mapGroup.add(map1);
    mapGroup.add(map2);
    mapGroup.add(map3);
    difficultyGroup.add(easy);
    difficultyGroup.add(medium);
    difficultyGroup.add(hard);
    easy.setVisible(false);
    medium.setVisible(false);
    hard.setVisible(false);

    start.setContentAreaFilled(false);
    exit.setContentAreaFilled(false);
    info.setContentAreaFilled(false);
    start.setBorder(BorderFactory.createEmptyBorder());
    exit.setBorder(BorderFactory.createEmptyBorder());
    info.setBorder(BorderFactory.createEmptyBorder());

    this.start.setEnabled(false);
    this.layoutView();

  }

  /**
   * Adds the buttons and layouts the view.
   */

  public void layoutView() {

    this.setLayout(new GridBagLayout());
    this.setOpaque(true);

    JLabel mapLabel = new JLabel("Select A Map");
    mapLabel.setFont(new Font("Serif", Font.BOLD, 48));
    mapLabel.setForeground(Color.yellow);

    JPanel filler1 = new JPanel();
    JPanel filler2 = new JPanel();

    filler1.setPreferredSize(new Dimension(0, 30));
    filler2.setPreferredSize(new Dimension(0, 30));
    easy.setPreferredSize(new Dimension(50, 30));
    medium.setPreferredSize(new Dimension(60, 30));
    hard.setPreferredSize(new Dimension(50, 30));

    buttonPanel.add(easy);
    buttonPanel.add(filler1);
    buttonPanel.add(medium);
    buttonPanel.add(filler2);
    buttonPanel.add(hard);
    buttonPanel.setOpaque(false);

    JPanel map1Panel = new JPanel(new BorderLayout());
    map1Panel.add(map1, BorderLayout.CENTER);
    map1Panel.add(buttonPanel, BorderLayout.SOUTH);
    map1Panel.setOpaque(false);

    map2.setEnabled(false);
    map3.setEnabled(false);

    c.anchor = GridBagConstraints.NORTH;
    c.weightx = 1.0;
    c.weighty = 1.0;
    c.gridx = 1;
    c.gridy = 0;
    this.add(mapLabel, c);


    c.gridx = 0;
    c.gridy = 1;
    c.ipadx = -32;
    c.ipady = -11;
    map1.setIcon(new ImageIcon(ImageUtility.getImage("map1thumbnail.png")));
    this.add(map1Panel, c);

    c.gridx = 1;
    c.gridy = 1;
    c.ipadx = -32;
    c.ipady = -11;
    map2.setIcon(new ImageIcon(ImageUtility.getImage("map2thumbnail.png")));
    this.add(map2, c);

    c.gridx = 2;
    c.gridy = 1;
    map3.setIcon(new ImageIcon(ImageUtility.getImage("map3thumbnail.png")));
    this.add(map3, c);


    JPanel startPanel = new JPanel(new GridBagLayout());
    startPanel.setOpaque(false);

    c.gridx = 0;
    c.gridy = 0;
    c.ipadx = 165;
    c.ipady = 50;
    c.gridwidth = 2;
    startPanel.add(start, c);

    c.gridwidth = 1;
    c.ipadx = 60;
    c.gridx = 0;
    c.gridy = 1;
    startPanel.add(exit, c);

    c.gridx = 1;
    c.gridy = 1;
    startPanel.add(info, c);

    c.ipadx = 200;
    c.ipady = 90;
    c.gridx = 1;
    c.gridy = 4;
    this.add(startPanel, c);

    disableDifficulty();
  }

  /**
   * Disables all of the selections the user has made on the main view.
   */

  public void resetMainView() {
    map1.setBorder(deselectionBorder);
    map2.setBorder(deselectionBorder);
    map3.setBorder(deselectionBorder);
    this.disableDifficulty();
    this.start.setEnabled(false);

  }

  /**
   * Disables all of the selections the user has made on the difficulty.
   */

  public void disableDifficulty() {
    difficultyGroup.clearSelection();
    easy.setVisible(false);
    medium.setVisible(false);
    hard.setVisible(false);
  }

  /**
   * Enables the difficulty to be selected after the user has selected a map.
   */

  public void enableDifficulty() {
    easy.setVisible(true);
    medium.setVisible(true);
    hard.setVisible(true);
  }

  /**
   * Getter for the border.
   *
   * @return Border
   */

  public Border getSelectionBorder() {
    return this.selectionBorder;
  }

  /**
   * Getter for the deselected border.
   *
   * @return Border
   */

  public Border getDeelectionBorder() {
    return this.deselectionBorder;
  }

  /**
   * Getter for the first map
   *
   * @return JToggleButton
   */

  public JToggleButton getMap1() {
    return this.map1;
  }

  /**
   * Getter for the second map.
   *
   * @return JToggleButton
   */

  public JToggleButton getMap2() {
    return this.map2;
  }

  /**
   * Getter for the third map.
   *
   * @return JToggleButton
   */

  public JToggleButton getMap3() {
    return this.map3;
  }

  /**
   * Getter for the easy difficulty.
   *
   * @return JToggleButton
   */

  public JToggleButton getEasyButton() {
    return this.easy;
  }

  /**
   * Getter for the medium difficulty.
   *
   * @return JToggleButton
   */

  public JToggleButton getMediumButton() {
    return this.medium;
  }

  /**
   * Getter for the hard difficulty.
   *
   * @return JToggleButton
   */

  public JToggleButton getHardButton() {
    return this.hard;
  }

  /**
   * Getter for the start button.
   *
   * @return JButton
   */

  public JButton getStartButton() {
    return this.start;
  }

  /**
   * Getter for the exit button.
   *
   * @return JButton
   */

  public JButton getExitButton() {
    return this.exit;
  }

  /**
   * Getter for the info button.
   *
   * @return JButton
   */

  public JButton getInfoButton() {
    return this.info;
  }

  /**
   * Getter for the button group for the maps.
   *
   * @return SelectButtonGroup
   */

  public SelectButtonGroup getMapGroup() {
    return mapGroup;
  }

  /**
   * Getter for the button group for the difficulty
   *
   * @return SelectButtonGroup
   */

  public SelectButtonGroup getDifficultyGroup() {
    return difficultyGroup;
  }

  /**
   * Enables the start button once the user has selected a map and a difficulty.
   */

  public void enableStartButton() {
    this.start.setEnabled(true);
  }
}
