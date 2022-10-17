package com.halloween_tower_defense.views;


import com.halloween_tower_defense.towers.Tower;
import com.halloween_tower_defense.utilities.ImageUtility;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

/**
 * An old test view that is no longer
 * used in our game
 *
 * @author Scorpion
 */

public class TestTrackView extends ImagePanel {

  private static final long serialVersionUID = 1L;
  private final BufferedImage background;
  private final ButtonGroup buildGroup = new ButtonGroup();
  private JButton startButton;
  private JButton jbtTalentTree;
  private JToggleButton jtbBuildDamageTower;
  private JToggleButton jtbBuildAOETower;
  private JToggleButton jtbBuildSlowTower;
  private JToggleButton jtbBuildDarkMatterTower;

  public TestTrackView(final BufferedImage image) {
    super(image, 0, 0);
    background = image;
    this.addGridBagLayout();
  }

  public void addGridBagLayout() {

    this.setOpaque(true);
    this.setLayout(null);

    BufferedImage buttonImage = ImageUtility.getImage("start-button.jpg", 175, 75);
    startButton = new JButton(new ImageIcon(buttonImage));
    startButton.setBounds(700, 550, 200, 100);
    this.add(startButton);

    // TODO Fixing the position
    BufferedImage damageButtonImage = ImageUtility.getImage("DamageTower.png", 50, 50);
    jtbBuildDamageTower = new JToggleButton(new ImageIcon(damageButtonImage, "DamageTower.png"));
    jtbBuildDamageTower.setBounds(700, 150, 50, 50);
    buildGroup.add(jtbBuildDamageTower);
    this.add(jtbBuildDamageTower);

    BufferedImage AOEButtonImage = ImageUtility.getImage("FireTower.png", 50, 50);
    jtbBuildAOETower = new JToggleButton(new ImageIcon(AOEButtonImage, "FireTower.png"));
    jtbBuildAOETower.setBounds(800, 150, 50, 50);
    buildGroup.add(jtbBuildAOETower);
    this.add(jtbBuildAOETower);

    BufferedImage slowButtonImage = ImageUtility.getImage("IceTower.png", 50, 50);
    jtbBuildSlowTower = new JToggleButton(new ImageIcon(slowButtonImage, "IceTower.png"));
    jtbBuildSlowTower.setBounds(700, 250, 50, 50);
    buildGroup.add(jtbBuildSlowTower);
    this.add(jtbBuildSlowTower);

    BufferedImage straightShotButtonImage =
        ImageUtility.getImage("DenseLightningTower.png", 50, 50);
    jtbBuildDarkMatterTower =
        new JToggleButton(new ImageIcon(straightShotButtonImage, "DenseLightningTower.png"));
    jtbBuildDarkMatterTower.setBounds(800, 250, 50, 50);
    buildGroup.add(jtbBuildDarkMatterTower);
    this.add(jtbBuildDarkMatterTower);

    BufferedImage patchOfFireButtonImage = ImageUtility.getImage("PatchOfFireTower.png", 50, 50);
    jtbBuildDarkMatterTower =
        new JToggleButton(new ImageIcon(patchOfFireButtonImage, "DenseLightningTower.png"));
    jtbBuildDarkMatterTower.setBounds(800, 250, 50, 50);
    buildGroup.add(jtbBuildDarkMatterTower);
    this.add(jtbBuildDarkMatterTower);

    //TODO Does the talent tree button need an image? or stay at text?
    //BufferedImage talentButtonImage = DriverView.getImage("tower.png",
    //30, 30);

    // talent tree will need and image and a popup tag describing it
    jbtTalentTree = new JButton("Talent Tree");
    jbtTalentTree.setBounds(700, 350, 200, 100);
    this.add(jbtTalentTree);
  }

  public ButtonGroup getBuildButtonGoup() {
    return this.buildGroup;
  }

  public JButton getStartButton() {
    return this.startButton;
  }

  public JToggleButton getBuildDamageTowerButton() {
    return this.jtbBuildDamageTower;
  }

  public JToggleButton getBuildAOETowerButton() {
    return this.jtbBuildAOETower;
  }

  public JToggleButton getBuildSlowTowerButton() {
    return this.jtbBuildSlowTower;
  }

  public JToggleButton getBuildDarkMatter() {
    return this.jtbBuildDarkMatterTower;
  }

  public JButton getTalentTreeButton() {
    return jbtTalentTree;
  }

  public Color getRGB(int x, int y) {
    return new Color(background.getRGB(x, y));
  }

  public boolean canBuild(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    return ((isGreen(getRGB(x, y))) && (isGreen(getRGB(x + 30, y))) &&
        (isGreen(getRGB(x, y + 13))) && (isGreen(getRGB(x + 30, y + 13))));
  }

  private boolean isGreen(Color c) {
    boolean r = 70 > c.getRed();// && c.getRed() < 255;
    boolean g = 70 < c.getGreen() && c.getGreen() < 255;
    //boolean b = 70 < c.getBlue() && c.getBlue() < 255;
    return (r && g);
  }

  public void addTower(final Tower tower) {
    tower.setBounds(tower.getX(), tower.getY(), tower.getSize().width, tower.getSize().height);
    this.add(tower);
//		this.repaint();
//		this.revalidate();
//		this.validate();
  }

  public void drawRuntimeObjects() {

  }

}
