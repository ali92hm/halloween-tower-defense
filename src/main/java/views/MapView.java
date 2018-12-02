package views;

import mobs.Mob;
import models.DriverModel;
import projectiles.Projectile;
import towers.Tower;
import towers.implementations.*;
import utilities.Position;
import utilities.TDButton;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The map panel for the tower defense game
 *
 * @author Scorpion
 */

public class MapView extends JPanel {

    private static final long serialVersionUID = 1L;

    private String[] startingTutorialImages = {

            "MobTutorial.png",
            "TowerTutorial.png",
            "TowerBuildingTutorial.png",
            "MissionTutorial.png",
            "StatTutorial.png",
            "ControlTutorial.png"

    };

    private String[] talentTreeTutorial = {
            "TalentPointTutorial.png",
            "TalentTreeTutorial.png",
            "ImmuneLevel1MobTutorial.png"
    };

    private String[] batMobTutorial = {
            "ImmuneLevel2MobTutorial.png"
    };

    private String[] giantPumpkinTutorial = {
            "BossMobTutorial.png"
    };

    private String[] activeTutorial;
    private int imageWalkthrough = 0;

    private DriverModel model;
    private DriverView view;
    private BufferedImage image;
    private BufferedImage background;
    private TDButton skipButton;
    private TDButton backButton;
    private TDButton continueButton;
    private JLabel navLabel;
    private ArrayList<Tower> towers;
    private boolean talentTreeActive = false;
    private int tutorialActive = -1;

    /**
     * constructs the map view
     *
     * @param image
     * @param model
     * @param view
     */

    public MapView(final String image, DriverModel model, DriverView view) {

        this.towers = new ArrayList<Tower>();

        this.image = DriverView.getImage(image, 700, 650);
        this.model = model;
        this.view = view;
        this.background = DriverView.getImage(image, 700, 650);
        this.setupFrame();

        skipButton = new TDButton(DriverView.getImage("X Close.png", 30, 30));
        backButton = new TDButton(DriverView.getImage("Back Arrow.png", 40, 40));
        continueButton = new TDButton(DriverView.getImage("Forward Arrow.png", 40, 40));
        navLabel = new JLabel("");
        skipButton.setBounds(600, 70, 30, 30);
        backButton.setBounds(425, 450, 40, 40);
        continueButton.setBounds(465, 450, 40, 40);
        navLabel.setBounds(375, 450, 50, 40);

        this.add(skipButton);
        this.add(backButton);
        this.add(continueButton);
        this.add(navLabel);
    }

    /**
     * sets up the size of the panel
     */

    public void setupFrame() {

        this.setLayout(null);

        this.setPreferredSize(new Dimension(706, 650));
        this.setMaximumSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());
    }

    /**
     * hides or shows the main.towers as buttons
     *
     * @param isGoingToHide
     */

    public void showTowers(final boolean isGoingToHide) {
        for (Tower tower : this.towers) {
            tower.setVisible(isGoingToHide);
        }
    }

    /**
     * sets up and displays the tutorial with
     * all its buttons
     *
     * @param tutorial
     */

    public void startTutorial(final int tutorial) {
        this.imageWalkthrough = 0;
        this.tutorialActive = tutorial;
        switch (tutorial) {
            case 0:
                this.activeTutorial = this.startingTutorialImages;
                break;
            case 1:
                this.activeTutorial = this.talentTreeTutorial;
                break;
            case 2:
                this.activeTutorial = this.batMobTutorial;
                break;
            case 3:
                this.activeTutorial = this.giantPumpkinTutorial;
                break;
        }

        this.activateButtons(true);
        this.changeTutorial();
        this.view.getSidePanelView().getTowerView().getBuildButtonGroup().clearSelection();
        this.showTowers(false);
        this.view.getSidePanelView().repaint();
    }

    /**
     * moves to the next slide in the tutorial
     */

    public void stepForwardsInTutorial() {
        this.imageWalkthrough++;
        this.view.getSidePanelView().repaint();
        this.changeTutorial();
    }

    /**
     * moves to the previous slide in the tutorial
     */

    public void stepBackwardsInTutorial() {
        this.imageWalkthrough--;
        this.view.getSidePanelView().repaint();
        this.changeTutorial();
    }

    /**
     * changes the slide of the tutorial
     * updating button for if they should
     * be activated
     */

    private void changeTutorial() {
        this.repaint();

        this.navLabel.setText((this.imageWalkthrough + 1) + " / " + this.activeTutorial.length);
        this.navLabel.setForeground(Color.WHITE);

        this.skipButton.setVisible(false);
        this.skipButton.setVisible(true);

        if (this.imageWalkthrough == 0) {
            this.backButton.setVisible(false);
        } else {
            this.backButton.setVisible(true);
        }

        if (this.imageWalkthrough == this.activeTutorial.length - 1) {
            this.continueButton.setVisible(false);
        } else {
            this.continueButton.setVisible(true);
        }
    }

    /**
     * removes the tutorial from the screen
     */

    public void hideTutorial() {
        this.tutorialActive = -1;
    }

    /**
     * repaints the panel
     */

    public void paintComponent(final Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.image, 0, 0, null);
        for (final Projectile projectile : this.model.allProjectiles()) {
            projectile.addImages(g2);

//			if (projectile.getFireImage() != null && projectile.getFirePosition() != null) {
//				imageGraphics.drawImage(projectile.getFireImage(), (int) projectile.getFirePosition().getXCord(), 
//						(int) projectile.getFirePosition().getYCord(), null);
//			}
//			if (projectile.getImpactImage() != null && projectile.getImpactPosition() != null) {
//				imageGraphics.drawImage(projectile.getImpactImage(), (int) projectile.getImpactPosition().getXCord(), 
//						(int) projectile.getImpactPosition().getYCord(), null);
//			}
        }
        if (model.allMobs() != null) {
            for (Mob mob : model.allMobs()) {
                if (!mob.isVisible()) {
                    model.removeMob(mob);
                    continue;
                }

                g2.drawImage(mob.getMobHealthBar(),
                        (int) (mob.getPosition().getXCord() - (mob.getMobWidth() / 2)),
                        (int) (mob.getPosition().getYCord() - (mob.getMobHeight() / 2)),
                        null);
            }
        }

        if (model.allTowers() != null) {
            for (final Tower tower : model.allTowers()) {
                if (tower.getTowerBaseImage() != null) {
                    g2.drawImage(tower.getTowerBaseImage(), tower.getX(), tower.getY(), null);
                }
                if (tower.getTowerTurretImage() != null) {
                    g2.drawImage(tower.getTowerTurretImage(), tower.getX(), tower.getY(), null);
                }
                if (tower.getShowRange()) {
                    int range = tower.getRange();
                    g2.setColor(new Color(0.2f, 1f, 0.2f, 0.4f));
                    g2.fillOval(tower.getX() + (tower.getWidth() / 2) - range, tower.getY() + (tower.getHeight() / 2) - range, range * 2, range * 2);
                }
            }
        }

        if (this.getMousePosition() != null && this.view.getSidePanelView().getTowerView().getBuildButtonGroup().getSelectedButton() != null) {

            int range = 0;
            switch (this.view.getSidePanelView().getTowerView().getBuildButtonGroup().getSelectedButton().getName()) {
                case "LightningTower":
                    range = LightningTower.TOWER_RANGE;
                    break;
                case "FireTower":
                    range = FireTower.TOWER_RANGE;
                    break;
                case "IceTower":
                    range = IceTower.TOWER_RANGE;
                    break;
                case "DenseLightningTower":
                    range = DenseLightningTower.TOWER_RANGE;
                    break;
                case "PatchOfFireTower":
                    range = PatchOfFireTower.TOWER_RANGE;
                    break;
                case "FreezeTower":
                    range = FreezeTower.TOWER_RANGE;
                    break;
                case "TeslaTower":
                    range = TeslaTower.TOWER_RANGE;
                    break;
                case "FireBombTower":
                    range = FireBombTower.TOWER_RANGE;
                    break;
                case "IcicleTower":
                    range = IcicleTower.TOWER_RANGE;
                    break;
            }

            int x = this.getMousePosition().x;
            int y = this.getMousePosition().y;
            try {
                if (this.canBuild(new Position(x, y))) {
                    g2.setColor(new Color(0.2f, 1f, 0.2f, 0.4f));
                } else {
                    g2.setColor(new Color(1f, 0, 0, 0.5f));
                }

                g2.fillOval(x - range, y - range, range * 2, range * 2);
                Icon icon = this.view.getSidePanelView().getTowerView().getBuildButtonGroup().getSelectedButton().getIcon();
                icon.paintIcon(null, g2, x - (icon.getIconWidth() / 2), y - (icon.getIconHeight() / 2));
            } catch (ArrayIndexOutOfBoundsException e) {

            }
        }

        if (tutorialActive != -1) {
            g2.drawImage(DriverView.getImage(this.activeTutorial[this.imageWalkthrough], 700, 650), 0, 0, null);
        } else if (this.talentTreeActive) {
        } else if (this.model.getRuntimeThread().isSuspended()) {
            g2.setColor(new Color(0, 0, 0, 0.7f));
            g2.fill(new Rectangle2D.Double(0, 0, 700, 650));
        }
    }

    /**
     * gets the color value of the pixel at the x and y coordinate
     *
     * @param x
     * @param y
     * @return Color
     */

    public Color getRGB(int x, int y) {
        return new Color(background.getRGB(x, y));
    }

    /**
     * sets the navigation and skip buttons
     * active if the tutorial is up
     *
     * @param active
     */

    public void activateButtons(final boolean active) {
        this.skipButton.setVisible(active);
        this.backButton.setVisible(false);
        this.continueButton.setVisible(active);
        this.navLabel.setVisible(active);
    }

    /**
     * returns the skip button
     *
     * @return JButton
     */

    public JButton getSkipButton() {
        return skipButton;
    }

    /**
     * returns the back button
     *
     * @return JButton
     */

    public JButton getBackButton() {
        return backButton;
    }

    /**
     * returns the continue button
     *
     * @return JButton
     */

    public JButton getContinueButton() {
        return continueButton;
    }

    /**
     * returns whether the talent tree
     * is being displayed
     *
     * @return boolean
     */

    public boolean isTalentTreeActive() {
        return this.talentTreeActive;
    }

    /**
     * sets the talent tree to be active or not
     *
     * @param talentTreeActive
     */

    public void setTalentTreeActive(final boolean talentTreeActive) {
        this.talentTreeActive = talentTreeActive;
    }

    /**
     * returns whether the tutorial is active
     *
     * @return int
     */

    public int getTutorialActive() {
        return this.tutorialActive;
    }

    /**
     * returns whether a tower can be built at the position
     *
     * @param p
     * @return boolean
     */

    public boolean canBuild(Position p) {
        int x = (int) (p.getXCord() - (TowerPanelView.TOWER_ICON_WIDTH) / 2);
        int y = (int) (p.getYCord() - (TowerPanelView.TOWER_ICON_HEIGHT) / 2);
        boolean topLeft = isGreen(getRGB(x, y));
        boolean topRight = isGreen(getRGB(x + TowerPanelView.TOWER_ICON_WIDTH, y));
        boolean bottomLeft = isGreen(getRGB(x, y + TowerPanelView.TOWER_ICON_HEIGHT));
        boolean bottomRight = isGreen(getRGB(x + TowerPanelView.TOWER_ICON_WIDTH, y + TowerPanelView.TOWER_ICON_HEIGHT));

        return (topLeft && topRight && bottomLeft && bottomRight);
    }

    /**
     * checks whether a location is red or green
     * green is true
     *
     * @param c
     * @return boolean
     */

    private boolean isGreen(Color c) {
        return c.getRed() < c.getGreen();
    }

    /**
     * adds a tower to the map view
     *
     * @param tower
     */

    public void addTower(final Tower tower) {
        tower.setBounds(tower.getX(), tower.getY(), tower.getSize().width, tower.getSize().height);
        this.add(tower);
        this.towers.add(tower);
    }

    /**
     * removes a specific tower from the map view
     *
     * @param tower
     */

    public void removeTower(final Tower tower) {
        this.remove(tower);
        this.towers.remove(tower);
    }

    /**
     * remove tower components from the map view
     */

    public void clearTowers() {
        for (Tower tower : this.towers) {
            this.remove(tower);
        }
    }

    /**
     * sets the cursor
     */

    public void setDefaultCursor() {
        this.setCursor(new Cursor(0));
    }
}
