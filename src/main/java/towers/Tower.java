package towers;

import mobs.Mob;
import models.DriverModel;
import projectiles.Projectile;
import utilities.Position;
import utilities.TDButton;
import views.DriverView;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.UUID;

/**
 * abstract class for the tower
 *
 * @author Scorpion
 */

public abstract class Tower extends TDButton {

    protected static final int TOWER_HEIGHT = 40;
    protected static final int TOWER_WIDTH = 40;
    private static final long serialVersionUID = 1L;
    protected static double towerCostDecrease = 1.0;
    protected static double fireRateBoost = 1.0;
    protected static double rangeBoost = 1.0;

    protected static boolean chainLightning = false;
    protected static boolean fireDOTDamage = false;
    protected static boolean iceDamage = false;

    protected final String towerName;
    protected final UUID id;

    protected BufferedImage towerBaseImage;
    protected BufferedImage towerTurretImage;
    protected Position position;
    protected int range;
    protected int fireRate;
    protected int cost;
    protected boolean showRange = false;

    protected int reloadProgress;
    protected Mob[] attackingMob;
    protected Mob[] chainingMobs;
    protected int mobTravelDistance;

    protected String path1UpgradeName;
    protected String path1UpgradeIcon;
    protected int path1UpgradeLevel;
    protected int[] path1UpgradeCosts;

    protected String path2UpgradeName;
    protected String path2UpgradeIcon;
    protected int path2UpgradeLevel;
    protected int[] path2UpgradeCosts;

    protected String path3UpgradeName;
    protected String path3UpgradeIcon;
    protected int path3UpgradeLevel;
    protected int[] path3UpgradeCosts;

    /**
     * super constructor for every tower
     *
     * @param position
     * @param towerBaseImage
     * @param towerTurretImage
     */

    protected Tower(final Position position, final String towerBaseImage,
                    final String towerTurretImage) {
        this.towerName = this.getClass().getSimpleName();
        this.id = UUID.randomUUID();
        this.setContentAreaFilled(false);
        this.position = new Position(position.getXCord() + (TOWER_WIDTH / 2), position.getYCord() + (TOWER_HEIGHT / 2));
        this.setPreferredSize(new Dimension(TOWER_HEIGHT, TOWER_WIDTH));
        this.setMaximumSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());
        this.setLocation((int) position.getXCord() + (TOWER_HEIGHT / 2), (int) position.getYCord() + (TOWER_WIDTH / 2));
        this.setBounds((int) position.getXCord(), (int) position.getYCord(), TOWER_HEIGHT, TOWER_WIDTH);
        this.towerBaseImage = DriverView.getImage(towerBaseImage, TOWER_WIDTH, TOWER_HEIGHT);
        this.towerTurretImage = DriverView.getImage(towerTurretImage, TOWER_WIDTH, TOWER_HEIGHT);
        this.setIcon(new ImageIcon(this.towerBaseImage));
    }

    public static double getDiscountMultiplier() {
        return towerCostDecrease;
    }

    /**
     * decreases the cool down rate of each tower by 10%
     */

    public static void upgradeFireRateBoost() {
        fireRateBoost = 0.9;
    }

    /**
     * increases the range of each tower by 10%
     */

    public static void upgradeRangeBoost() {
        rangeBoost = 1.1;
    }

    /**
     * decreases the cost of each tower by 10%
     */

    public static void upgradeTowerCostDecrease() {
        towerCostDecrease = 0.9;
    }

    /**
     * gives all lightning main.towers the ability to shoot
     * main.controllers.projectiles that chain lightning
     */

    public static void upgradeChainLightning() {
        chainLightning = true;
    }

    /**
     * gives all fire main.towers the ability to shoot
     * main.controllers.projectiles that damage over time
     */

    public static void upgradeFireDOTDamage() {
        fireDOTDamage = true;
    }

    /**
     * gives all ice main.towers the ability to shoot
     * main.controllers.projectiles that slow main.controllers.mobs down even after
     * the effect has worn off
     */

    public static void upgradeIceDamage() {
        iceDamage = true;
    }

    /**
     * returns whether lightning main.towers have chain lightning
     *
     * @return boolean
     */

    public static boolean getChainLightningUpgrade() {
        return chainLightning;
    }

    /**
     * returns whether fire main.towers have dot damage
     *
     * @return boolean
     */

    public static boolean getFireDOTDamageUpgrade() {
        return fireDOTDamage;
    }

    /**
     * returns whether ice main.towers have permifrost
     *
     * @return boolean
     */

    public static boolean getIceDamageUpgrade() {
        return iceDamage;
    }

    /**
     * returns the current value of the attribute
     * for the main.towers first upgrade path
     *
     * @return int
     */

    public abstract int path1CurrentValue();

    /**
     * returns the current value of the attribute
     * for the main.towers second upgrade path
     *
     * @return int
     */

    public abstract int path2CurrentValue();

    /**
     * returns the current value of the attribute
     * for the main.towers third upgrade path
     *
     * @return int
     */

    public abstract int path3CurrentValue();

    /**
     * returns the value of the attribute
     * if it were to go to the next upgrade
     * for the main.towers first upgrade path
     *
     * @return int
     */

    public abstract int path1UpgradeValue();

    /**
     * returns the value of the attribute
     * if it were to go to the next upgrade
     * for the main.towers second upgrade path
     *
     * @return int
     */

    public abstract int path2UpgradeValue();

    /**
     * returns the value of the attribute
     * if it were to go to the next upgrade
     * for the main.towers third upgrade path
     *
     * @return int
     */

    public abstract int path3UpgradeValue();

    /**
     * method to tell main.towers to attack a mob if
     * their fire rate cool down is finished
     *
     * @param model
     * @return Projectile[]
     */

    public abstract Projectile[] attackMob(final DriverModel model);

    /**
     * upgrades the first upgrade path to the next level
     *
     * @param model
     */

    public abstract void upgradePath1(final DriverModel model);

    /**
     * upgrades the second upgrade path to the next level
     *
     * @param model
     */

    public abstract void upgradePath2(final DriverModel model);

    /**
     * upgrades the third upgrade path to the next level
     *
     * @param model
     */

    public abstract void upgradePath3(final DriverModel model);

    /**
     * returns the name of the tower
     *
     * @return String
     */

    public String getName() {
        return this.towerName;
    }

    /**
     * returns the id of the tower
     *
     * @return UUID
     */

    public UUID getID() {
        return this.id;
    }

    /**
     * returns the main.towers base image
     *
     * @return BufferedImage
     */

    public BufferedImage getTowerBaseImage() {
        return this.towerBaseImage;
    }

    /**
     * returns the image of the main.towers turret
     *
     * @return BufferedImage
     */

    public BufferedImage getTowerTurretImage() {
        return this.towerTurretImage;
    }

    /**
     * gets the position of the tower
     *
     * @return Position
     */

    public Position getPosition() {
        return this.position;
    }

    /**
     * returns how far away the tower can shoot
     *
     * @return int
     */

    public abstract int getRange();

    /**
     * returns how fast the tower fires
     *
     * @return int
     */

    public int getFireRate() {
        return fireRate;
    }

    /**
     * returns whether the tower should be
     * displaying its footprint
     *
     * @return boolean
     */

    public boolean getShowRange() {
        return showRange;
    }

    /**
     * sets whether the tower should be showing
     * its range footprint
     *
     * @param show
     */

    public void setShowRange(final boolean show) {
        showRange = show;
    }

    /**
     * returns the name of the first upgrade path
     *
     * @return String
     */

    public String getPath1UpgradeName() {
        return path1UpgradeName;
    }

    /**
     * gets the name of the icon for the first
     * upgrade path
     *
     * @return String
     */

    public String getPath1UpgradeIcon() {
        return path1UpgradeIcon;
    }

    /**
     * gets how many times the first
     * upgrade path has been upgraded
     *
     * @return int
     */

    public int getPath1UpgradeLevel() {
        return path1UpgradeLevel;
    }

    /**
     * gets the cost of every upgrade
     * for the first upgrade path
     *
     * @return int[]
     */

    public int[] getPath1UpgradeCosts() {
        return path1UpgradeCosts;
    }

    /**
     * returns the name of the second upgrade path
     *
     * @return String
     */

    public String getPath2UpgradeName() {
        return path2UpgradeName;
    }

    /**
     * gets the name of the icon for the second
     * upgrade path
     *
     * @return String
     */

    public String getPath2UpgradeIcon() {
        return path2UpgradeIcon;
    }

    /**
     * gets how many times the first
     * upgrade path has been upgraded
     *
     * @return int
     */

    public int getPath2UpgradeLevel() {
        return path2UpgradeLevel;
    }

    /**
     * gets the cost of every upgrade
     * for the second upgrade path
     *
     * @return int[]
     */

    public int[] getPath2UpgradeCosts() {
        return path2UpgradeCosts;
    }

    /**
     * returns the name of the third upgrade path
     *
     * @return String
     */

    public String getPath3UpgradeName() {
        return path3UpgradeName;
    }

    /**
     * gets the name of the icon for the third
     * upgrade path
     *
     * @return String
     */

    public String getPath3UpgradeIcon() {
        return path3UpgradeIcon;
    }

    /**
     * gets how many times the third
     * upgrade path has been upgraded
     *
     * @return int
     */

    public int getPath3UpgradeLevel() {
        return path3UpgradeLevel;
    }

    /**
     * gets the cost of every upgrade
     * for the third upgrade path
     *
     * @return int[]
     */

    public int[] getPath3UpgradeCosts() {
        return path3UpgradeCosts;
    }

    /**
     * calculates and returns the total value
     * of the tower returned upon selling
     *
     * @return int
     */

    public int getSellValue() {
        int value = this.cost;
        if (this.path1UpgradeLevel > 0) {
            value += this.path1UpgradeCosts[0];
        }

        if (this.path1UpgradeLevel > 1) {
            value += this.path1UpgradeCosts[1];
        }

        if (this.path1UpgradeLevel > 2) {
            value += this.path1UpgradeCosts[2];
        }

        if (this.path2UpgradeLevel > 0) {
            value += this.path2UpgradeCosts[0];
        }

        if (this.path2UpgradeLevel > 1) {
            value += this.path2UpgradeCosts[1];
        }

        if (this.path2UpgradeLevel > 2) {
            value += this.path2UpgradeCosts[2];
        }

        if (this.path3UpgradeLevel > 0) {
            value += this.path3UpgradeCosts[0];
        }

        if (this.path3UpgradeLevel > 1) {
            value += this.path3UpgradeCosts[1];
        }

        if (this.path3UpgradeLevel > 2) {
            value += this.path3UpgradeCosts[2];
        }

        return (int) ((value * 2) / 3);
    }

    /**
     * repaints the tower onto the map
     */

    public void paintComponent(final Graphics g) {
        super.paintComponents(g);
        g.drawImage(this.towerBaseImage, 0, 0, this);
    }
}