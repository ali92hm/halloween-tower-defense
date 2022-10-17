package com.halloween_tower_defense.views;

import com.halloween_tower_defense.models.GameModel;
import com.halloween_tower_defense.towers.DenseLightningTower;
import com.halloween_tower_defense.towers.FireBombTower;
import com.halloween_tower_defense.towers.FireTower;
import com.halloween_tower_defense.towers.FreezeTower;
import com.halloween_tower_defense.towers.IceTower;
import com.halloween_tower_defense.towers.IcicleTower;
import com.halloween_tower_defense.towers.LightningTower;
import com.halloween_tower_defense.towers.PatchOfFireTower;
import com.halloween_tower_defense.towers.TeslaTower;
import com.halloween_tower_defense.utilities.ImageUtility;
import com.halloween_tower_defense.utilities.TDButtonGroup;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

/**
 * the side panel view that contains
 * all of the towers
 *
 * @author Scorpion
 */

public class TowerPanelView extends JPanel implements ActionListener {

  public static final int TOWER_ICON_WIDTH = 40;
  public static final int TOWER_ICON_HEIGHT = 40;
  private static final long serialVersionUID = 1L;
  private final TDButtonGroup buildGroup = new TDButtonGroup();
  private GameModel model;
  private JToggleButton jtbBuildDamageTower;
  private JToggleButton jtbBuildAOETower;
  private JToggleButton jtbBuildSlowTower;
  private JToggleButton jtbBuildDenseLightningTower;
  private JToggleButton jtbBuildPatchOfFireTower;
  private JToggleButton jtbBuildFreezeTower;
  private JToggleButton jtbBuildTeslaTower;
  private JToggleButton jtbBuildFireBombTower;
  private JToggleButton jtbBuildIcicleTower;
  private JToggleButton test7;
  private JToggleButton test8;
  private JToggleButton test9;
  private boolean towerPanelEnabled = false;

  /**
   * constructs the tower panel
   */

  public TowerPanelView() {
    this.setupFrame();
    this.setToolTips();
  }

  /**
   * sets of the panel with the preferred
   * size and opacity
   */

  public void setupFrame() {

    this.setOpaque(true);
    this.layoutTowers();

    this.setPreferredSize(new Dimension(194, 800));
    this.setMaximumSize(this.getPreferredSize());
    this.setMinimumSize(this.getPreferredSize());
  }

  /**
   * enables all the towers to be clicked
   *
   * @param enable
   */

  public void enableTowerButtons(final boolean enable) {
    this.towerPanelEnabled = enable;
  }

  /**
   * adds the tower buttons to the panel
   */

  public void layoutTowers() {

    this.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    createTowers();


    c.anchor = GridBagConstraints.NORTHWEST;
    c.weightx = 1;
    c.weighty = 1;
    c.ipadx = 15;
    c.ipady = 15;
    c.gridx = 0;
    c.gridy = 0;
    this.add(
        layoutTowerPanel(jtbBuildDamageTower, "Lightning", "Cost: " + LightningTower.TOWER_COST),
        c);

    c.ipadx = 15;
    c.ipady = 15;
    c.gridx = 1;
    c.gridy = 0;
    this.add(layoutTowerPanel(jtbBuildAOETower, "Fire Burst", "Cost: " + FireTower.TOWER_COST), c);

    c.ipadx = 15;
    c.ipady = 15;
    c.gridx = 2;
    c.gridy = 0;
    this.add(layoutTowerPanel(jtbBuildSlowTower, "Thick Ice", "Cost: " + IceTower.TOWER_COST), c);

    c.ipadx = 15;
    c.ipady = 15;
    c.gridx = 0;
    c.gridy = 1;
    this.add(layoutTowerPanel(jtbBuildDenseLightningTower, "Dense L.",
        "Cost: " + DenseLightningTower.TOWER_COST), c);

    c.ipadx = 15;
    c.ipady = 15;
    c.gridx = 1;
    c.gridy = 1;
    this.add(layoutTowerPanel(jtbBuildPatchOfFireTower, "Patch of Fire",
        "Cost: " + PatchOfFireTower.TOWER_COST), c);

    c.ipadx = 15;
    c.ipady = 15;
    c.gridx = 2;
    c.gridy = 1;
    this.add(layoutTowerPanel(jtbBuildFreezeTower, "Freeze", "Cost: " + FreezeTower.TOWER_COST), c);

    c.ipadx = 15;
    c.ipady = 15;
    c.gridx = 0;
    c.gridy = 2;
    this.add(layoutTowerPanel(jtbBuildTeslaTower, "Tesla", "Cost: " + TeslaTower.TOWER_COST), c);

    c.ipadx = 15;
    c.ipady = 15;
    c.gridx = 1;
    c.gridy = 2;
    this.add(
        layoutTowerPanel(jtbBuildFireBombTower, "Fire Bomb", "Cost: " + FireBombTower.TOWER_COST),
        c);

    c.ipadx = 15;
    c.ipady = 15;
    c.gridx = 2;
    c.gridy = 2;
    this.add(layoutTowerPanel(jtbBuildIcicleTower, "Icicle", "Cost: " + IcicleTower.TOWER_COST), c);
  }

  /**
   * sets up each tower button
   */

  public void createTowers() {

    BufferedImage damageButtonImage =
        ImageUtility.getImage("DamageTower.png", TOWER_ICON_WIDTH, TOWER_ICON_HEIGHT);
    jtbBuildDamageTower = new JToggleButton(new ImageIcon(damageButtonImage, "DamageTower.png"));
    jtbBuildDamageTower.setName("DamageTower");
    buildGroup.add(jtbBuildDamageTower);

    BufferedImage AOEButtonImage =
        ImageUtility.getImage("FireTower.png", TOWER_ICON_WIDTH, TOWER_ICON_HEIGHT);
    jtbBuildAOETower = new JToggleButton(new ImageIcon(AOEButtonImage, "FireTower.png"));
    jtbBuildAOETower.setName("FireTower");
    buildGroup.add(jtbBuildAOETower);

    BufferedImage slowButtonImage =
        ImageUtility.getImage("IceTower.png", TOWER_ICON_WIDTH, TOWER_ICON_HEIGHT);
    jtbBuildSlowTower = new JToggleButton(new ImageIcon(slowButtonImage, "IceTower.png"));
    jtbBuildSlowTower.setName("IceTower");
    buildGroup.add(jtbBuildSlowTower);

    BufferedImage straightShotButtonImage =
        ImageUtility.getImage("DenseLightningTower.png", TOWER_ICON_WIDTH, TOWER_ICON_HEIGHT);
    jtbBuildDenseLightningTower =
        new JToggleButton(new ImageIcon(straightShotButtonImage, "DenseLightningTower.png"));
    jtbBuildDenseLightningTower.setDisabledIcon(
        new ImageIcon(ImageUtility.getImage("LockedTower.png", 40, 40)));
    jtbBuildDenseLightningTower.setName("DenseLightningTower");
    buildGroup.add(jtbBuildDenseLightningTower);

    BufferedImage patchOfFireButtonImage =
        ImageUtility.getImage("PatchOfFireTower.png", TOWER_ICON_WIDTH, TOWER_ICON_HEIGHT);
    jtbBuildPatchOfFireTower =
        new JToggleButton(new ImageIcon(patchOfFireButtonImage, "PatchOfFireTower.png"));
    jtbBuildPatchOfFireTower.setDisabledIcon(
        new ImageIcon(ImageUtility.getImage("LockedTower.png", 40, 40)));
    jtbBuildPatchOfFireTower.setName("PatchOfFireTower");
    buildGroup.add(jtbBuildPatchOfFireTower);

    BufferedImage freezeButtonImage =
        ImageUtility.getImage("FreezeTower.png", TOWER_ICON_WIDTH, TOWER_ICON_HEIGHT);
    jtbBuildFreezeTower = new JToggleButton(new ImageIcon(freezeButtonImage, "FreezeTower.png"));
    jtbBuildFreezeTower.setDisabledIcon(
        new ImageIcon(ImageUtility.getImage("LockedTower.png", 40, 40)));
    jtbBuildFreezeTower.setName("FreezeTower");
    buildGroup.add(jtbBuildFreezeTower);

    BufferedImage teslaButtonImage =
        ImageUtility.getImage("TeslaTower.png", TOWER_ICON_WIDTH, TOWER_ICON_HEIGHT);
    jtbBuildTeslaTower = new JToggleButton(new ImageIcon(teslaButtonImage, "TeslaTower.png"));
    jtbBuildTeslaTower.setDisabledIcon(
        new ImageIcon(ImageUtility.getImage("LockedTower.png", 40, 40)));
    jtbBuildTeslaTower.setName("TeslaTower");
    jtbBuildTeslaTower.setEnabled(false);
    buildGroup.add(jtbBuildTeslaTower);

    BufferedImage fireBombButtonImage =
        ImageUtility.getImage("FireBombTower.png", TOWER_ICON_WIDTH, TOWER_ICON_HEIGHT);
    jtbBuildFireBombTower =
        new JToggleButton(new ImageIcon(fireBombButtonImage, "FireBombTower.png"));
    jtbBuildFireBombTower.setDisabledIcon(
        new ImageIcon(ImageUtility.getImage("LockedTower.png", 40, 40)));
    jtbBuildFireBombTower.setName("FireBombTower");
    jtbBuildFireBombTower.setEnabled(false);
    buildGroup.add(jtbBuildFireBombTower);

    BufferedImage icicleButtonImage =
        ImageUtility.getImage("IcicleTower.png", TOWER_ICON_WIDTH, TOWER_ICON_HEIGHT);
    jtbBuildIcicleTower = new JToggleButton(new ImageIcon(icicleButtonImage, "IcicleTower.png"));
    jtbBuildIcicleTower.setDisabledIcon(
        new ImageIcon(ImageUtility.getImage("LockedTower.png", 40, 40)));
    jtbBuildIcicleTower.setName("IcicleTower");
    jtbBuildIcicleTower.setEnabled(false);
    buildGroup.add(jtbBuildIcicleTower);

    BufferedImage underConstructionButtonImage =
        ImageUtility.getImage("ComingSoon.png", TOWER_ICON_WIDTH, TOWER_ICON_HEIGHT);
    BufferedImage underConstructionButtonImageDisable =
        ImageUtility.getImage("ComingSoon.png", TOWER_ICON_WIDTH, TOWER_ICON_HEIGHT);
    test7 = new JToggleButton(new ImageIcon(underConstructionButtonImage, "UnderConstruction.png"));
    test7.setDisabledIcon(new ImageIcon(underConstructionButtonImageDisable));
    test7.setName("UnderConstruction");
    test7.setEnabled(false);
    buildGroup.add(test7);

    test8 = new JToggleButton(new ImageIcon(underConstructionButtonImage, "UnderConstruction.png"));
    test8.setDisabledIcon(new ImageIcon(underConstructionButtonImageDisable));
    test8.setName("UnderConstruction");
    test8.setEnabled(false);
    buildGroup.add(test8);

    test9 = new JToggleButton(new ImageIcon(underConstructionButtonImage, "UnderConstruction.png"));
    test9.setDisabledIcon(new ImageIcon(underConstructionButtonImageDisable));
    test9.setName("UnderConstruction");
    test9.setEnabled(false);
    buildGroup.add(test9);

  }

  /**
   * sets up each tower with its labels
   * for the name and cost
   *
   * @param tower
   * @param name
   * @param cost
   * @return JPanel
   */

  public JPanel layoutTowerPanel(JToggleButton tower, String name, String cost) {

    JPanel panel = new JPanel(new BorderLayout());
    JPanel labelPanel = new JPanel(new GridLayout(2, 1));
    JLabel towerName = new JLabel(name);
    JLabel towerLabel = new JLabel("Tower");
    towerName.setFont(new Font("Serif", Font.BOLD, 10));
    towerLabel.setFont(new Font("Serif", Font.BOLD, 10));
    towerName.setHorizontalAlignment(SwingConstants.CENTER);
    towerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    labelPanel.add(towerName);
    labelPanel.add(towerLabel);
    JLabel towerCost = new JLabel(cost);
    towerCost.setFont(new Font("Serif", Font.BOLD, 10));

    panel.add(labelPanel, BorderLayout.NORTH);
    panel.add(tower, BorderLayout.CENTER);
    panel.add(towerCost, BorderLayout.SOUTH);

    return panel;
  }

  /**
   * sets the model for the view
   *
   * @param model
   */

  public void setModel(GameModel model) {
    this.model = model;
    this.model.addActionListener(this);
  }

  /**
   * takes buttons and puts them into button groups
   *
   * @return TDButtonGroup
   */

  public TDButtonGroup getBuildButtonGroup() {
    return this.buildGroup;
  }

  /**
   * when the model updates this view, checks
   * if the buttons should be updated
   */

  public void actionPerformed(ActionEvent e) {
    disableTowersButtons();
  }

  /**
   * when the model updates this view, checks
   * if the buttons should be updated
   */

  private void disableTowersButtons() {
    test7.setEnabled(false);
    test8.setEnabled(false);
    test9.setEnabled(false);

    this.jtbBuildDamageTower.setEnabled(
        model.getMoney() >= LightningTower.TOWER_COST && this.towerPanelEnabled);

    this.jtbBuildAOETower.setEnabled(
        model.getMoney() >= FireTower.TOWER_COST && this.towerPanelEnabled);

    this.jtbBuildSlowTower.setEnabled(
        model.getMoney() >= IceTower.TOWER_COST && this.towerPanelEnabled);

    if (!DenseLightningTower.isTowerUnlocked() || !this.towerPanelEnabled) {
      this.jtbBuildDenseLightningTower.setEnabled(false);
    } else if (model.getMoney() < DenseLightningTower.TOWER_COST) {
      this.jtbBuildDenseLightningTower.setDisabledIcon(null);
      this.jtbBuildDenseLightningTower.setEnabled(false);
    } else {
      this.jtbBuildDenseLightningTower.setEnabled(true);
    }

    if (!PatchOfFireTower.isTowerUnlocked() || !this.towerPanelEnabled) {
      this.jtbBuildPatchOfFireTower.setEnabled(false);
    } else if (model.getMoney() < PatchOfFireTower.TOWER_COST) {
      this.jtbBuildPatchOfFireTower.setDisabledIcon(null);
      this.jtbBuildPatchOfFireTower.setEnabled(false);
    } else {
      this.jtbBuildPatchOfFireTower.setEnabled(true);
    }

    if (!FreezeTower.isTowerUnlocked() || !this.towerPanelEnabled) {
      this.jtbBuildFreezeTower.setEnabled(false);
    } else if (model.getMoney() < FreezeTower.TOWER_COST) {
      this.jtbBuildFreezeTower.setDisabledIcon(null);
      this.jtbBuildFreezeTower.setEnabled(false);
    } else {
      this.jtbBuildFreezeTower.setEnabled(true);
    }

    if (!TeslaTower.isTowerUnlocked() || !this.towerPanelEnabled) {
      this.jtbBuildTeslaTower.setEnabled(false);
    } else if (model.getMoney() < TeslaTower.TOWER_COST) {
      this.jtbBuildTeslaTower.setDisabledIcon(null);
      this.jtbBuildTeslaTower.setEnabled(false);
    } else {
      this.jtbBuildTeslaTower.setEnabled(true);
    }

    if (!FireBombTower.isTowerUnlocked() || !this.towerPanelEnabled) {
      this.jtbBuildFireBombTower.setEnabled(false);
    } else if (model.getMoney() < FireBombTower.TOWER_COST) {
      this.jtbBuildFireBombTower.setDisabledIcon(null);
      this.jtbBuildFireBombTower.setEnabled(false);
    } else {
      this.jtbBuildFireBombTower.setEnabled(true);
    }

    if (!IcicleTower.isTowerUnlocked() || !this.towerPanelEnabled) {
      this.jtbBuildIcicleTower.setEnabled(false);
    } else if (model.getMoney() < IcicleTower.TOWER_COST) {
      this.jtbBuildIcicleTower.setDisabledIcon(null);
      this.jtbBuildIcicleTower.setEnabled(false);
    } else {
      this.jtbBuildIcicleTower.setEnabled(true);
    }

  }

  /**
   * sets the tool tips for each button
   */

  private void setToolTips() {

    UIManager.put("ToolTip.background", new ColorUIResource(255, 247, 200)); //#fff7c8
    Border border = BorderFactory.createLineBorder(new Color(76, 79, 83));    //#4c4f53
    UIManager.put("ToolTip.border", border);
    ToolTipManager.sharedInstance().setInitialDelay(250);
    ToolTipManager.sharedInstance().setDismissDelay(15000);

    jtbBuildDamageTower.setToolTipText("<html>" + "Lightning Tower"
        + "<br>"
        + LightningTower.TOWER_COST + "g"
        + "<br>"
        + "Shoots a single shot of lightning at an enemy." + "<html>");

    jtbBuildAOETower.setToolTipText("<html>" + "Fire Tower"
        + "<br>"
        + FireTower.TOWER_COST + "g"
        + "<br>"
        + "Emits a firey explosion around the tower itself." + "<html>");

    jtbBuildSlowTower.setToolTipText("<html>" + "Ice Tower"
        + "<br>"
        + IceTower.TOWER_COST + "g"
        + "<br>"
        + "Shoots a single enemy and slows their movement down." + "<html>");

    jtbBuildDenseLightningTower.setToolTipText("<html>" + "Dense Lightning Tower"
        + "<br>"
        + DenseLightningTower.TOWER_COST + "g"
        + "<br>"
        + "Shoots a single shot of lightning at an enemy that pierces"
        + "<br>"
        + " through and can hit multiple targets." + "<html>");

    jtbBuildPatchOfFireTower.setToolTipText("<html>" + "Patch Of Fire Tower"
        + "<br>"
        + PatchOfFireTower.TOWER_COST + "g"
        + "<br>"
        + "Shoots a fireball at the target and leaves a patch of fire "
        + "<br>"
        + "on the ground that damages enemies that walk over it." + "<html>");

    jtbBuildFreezeTower.setToolTipText("<html>" + "Freeze Tower"
        + "<br>"
        + FreezeTower.TOWER_COST + "g"
        + "<br>"
        + "Freezes all enemies around the tower in place for a short duration." + "<html>");

    jtbBuildTeslaTower.setToolTipText("<html>" + "Lightning Tower"
        + "<br>"
        + LightningTower.TOWER_COST + "g"
        + "<br>"
        + "Shoots a single shot of lightning at an enemy." + "<html>");

    jtbBuildFireBombTower.setToolTipText("<html>" + "Lightning Tower"
        + "<br>"
        + LightningTower.TOWER_COST + "g"
        + "<br>"
        + "Shoots a single shot of lightning at an enemy." + "<html>");

    jtbBuildIcicleTower.setToolTipText("<html>" + "Lightning Tower"
        + "<br>"
        + LightningTower.TOWER_COST + "g"
        + "<br>"
        + "Shoots a single shot of lightning at an enemy." + "<html>");

    test7.setToolTipText("<html>" + "Lightning Tower"
        + "<br>"
        + LightningTower.TOWER_COST + "g"
        + "<br>"
        + "Shoots a single shot of lightning at an enemy." + "<html>");

    test8.setToolTipText("<html>" + "Lightning Tower"
        + "<br>"
        + LightningTower.TOWER_COST + "g"
        + "<br>"
        + "Shoots a single shot of lightning at an enemy." + "<html>");

    test9.setToolTipText("<html>" + "Lightning Tower"
        + "<br>"
        + LightningTower.TOWER_COST + "g"
        + "<br>"
        + "Shoots a single shot of lightning at an enemy." + "<html>");

  }
}
