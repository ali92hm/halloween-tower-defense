package towers.implementations;

import mobs.Mob;
import models.DriverModel;
import projectiles.PatchOfFire;
import projectiles.Projectile;
import towers.Tower;
import utilities.Position;
import utilities.Vector;
import views.Alert;
import views.DriverView;

/**
 * Creates a tower that shoots a projectile
 * that hits a target leaving a pit of fire
 *
 * @author Scorpion
 */

public class PatchOfFireTower extends Tower {
    public static final String TOWER_BASE_IMAGE = "PatchOfFireTower.png";
    public static final String TOWER_TURRET_IMAGE = null;
    public static final int TOWER_RANGE = 100;
    public static final int TOWER_FIRE_RATE = 2500;
    public static final int TOWER_COST = 750;
    private static final long serialVersionUID = 1L;
    private static boolean towerUnlocked = false;
    private static boolean clickedTowerBefore = false;

    /**
     * Constructor for the PatchOfFireTower
     *
     * @param location
     * @param model
     */

    public PatchOfFireTower(final Position location, final DriverModel model) {
        super(location, TOWER_BASE_IMAGE, TOWER_TURRET_IMAGE);
        this.range = TOWER_RANGE;
        this.fireRate = TOWER_FIRE_RATE;
        this.cost = TOWER_COST;
        this.path1UpgradeName = "Damage";
        this.path1UpgradeIcon = "Damage Icon.png";
        this.path1UpgradeLevel = 0;
        this.path1UpgradeCosts = new int[3];
        this.path1UpgradeCosts[0] = 850;
        this.path1UpgradeCosts[1] = 1750;
        this.path1UpgradeCosts[2] = 4800;

        this.path2UpgradeName = "Range";
        this.path2UpgradeIcon = "Range Icon.png";
        this.path2UpgradeLevel = 0;
        this.path2UpgradeCosts = new int[3];
        this.path2UpgradeCosts[0] = 500;
        this.path2UpgradeCosts[1] = 850;
        this.path2UpgradeCosts[2] = 1500;

        this.path3UpgradeName = "Duration";
        this.path3UpgradeIcon = "Duration Icon.png";
        this.path3UpgradeLevel = 0;
        this.path3UpgradeCosts = new int[3];
        this.path3UpgradeCosts[0] = 750;
        this.path3UpgradeCosts[1] = 1500;
        this.path3UpgradeCosts[2] = 3700;

        model.towerBuyUpgradeMoney(this.cost);
    }

    /**
     * unlocks the tower
     */

    public static void unlockTower() {
        towerUnlocked = true;
    }

    /**
     * returns whether the tower is unlocked
     *
     * @return boolean
     */

    public static boolean isTowerUnlocked() {
        return towerUnlocked;
    }

    /**
     * Method that shows the tower's tutorial
     * if its the first time users clicked on it
     *
     * @return boolean
     */

    public static boolean clickedTower(final DriverView view) {
        if (clickedTowerBefore) {
            return true;
        }
        new Alert(view, DriverView.getImage(TOWER_BASE_IMAGE, 50, 50),
                "Patch of Fire Tower",
                "This tower shoots a single",
                "round that sets a fire on the",
                "track damaging any mob that",
                "crosses it.");
        clickedTowerBefore = true;
        return false;
    }

    /**
     * method to tell main.towers to attack a mob if
     * their fire rate cool down is finished
     *
     * @param model
     * @return Projectile[]
     */

    public Projectile[] attackMob(final DriverModel model) {
        Projectile[] projectiles = new Projectile[1];
        this.attackingMob = new Mob[1];
        this.chainingMobs = new Mob[1];
        if (this.reloadProgress > 30) {
            this.reloadProgress -= 30;
            return projectiles;
        }

        this.mobTravelDistance = 0;
        for (Mob mob : model.allMobs()) {
            if (this.position.getDistance(mob.getPosition()) < ((this.range + (10 *
                    this.path2UpgradeLevel)) * rangeBoost) + mob.getRadius()) {
                this.attackingMob[0] = mob;
                break;
            }
        }

        if (this.attackingMob[0] != null) {
            projectiles[0] = this.shootMob(model);
        }

        return projectiles;
    }

    /**
     * has the tower actually create and release
     * a projectile
     *
     * @param model
     * @return
     */

    public Projectile shootMob(final DriverModel model) {
        Vector vector = new Vector(this.position, this.attackingMob[0].getPosition(), PatchOfFire.PROJECTILE_SPEED);

        double xComp = 0;
        double yComp = 0;

        switch (this.attackingMob[0].getDirection()) {
            case 'u':
                yComp = (-1) * this.attackingMob[0].getSpeed();
                break;
            case 'r':
                xComp = this.attackingMob[0].getSpeed();
                break;
            case 'd':
                yComp = this.attackingMob[0].getSpeed();
                break;
            case 'l':
                xComp = (-1) * this.attackingMob[0].getSpeed();
                break;
        }

        Vector trajectory = vector.findVectorSum(new Vector(xComp, yComp));
        this.reloadProgress = (int) (this.fireRate * fireRateBoost);
        if (this.towerTurretImage != null) {
            this.towerTurretImage = DriverView.rotateImage(towerTurretImage, trajectory.getAngle());
        }

        return new PatchOfFire(model, this.position, trajectory, this.path3UpgradeLevel, this.path1UpgradeLevel);
    }

    /**
     * getting the range of a tower
     *
     * @return int
     */

    public int getRange() {
        return path2CurrentValue();
    }

    /**
     * returns the current value of the attribute
     * for the main.towers first upgrade path
     *
     * @return int
     */

    public int path1CurrentValue() {
        return PatchOfFire.getDamageLevelBoost(this.path1UpgradeLevel);
    }

    /**
     * returns the current value of the attribute
     * for the main.towers second upgrade path
     *
     * @return int
     */

    public int path2CurrentValue() {
        return (int) ((this.range + (10 * this.path2UpgradeLevel)) * rangeBoost);
    }

    /**
     * returns the current value of the attribute
     * for the main.towers third upgrade path
     *
     * @return int
     */

    public int path3CurrentValue() {
        return (int) ((PatchOfFire.getDurationLevelBoost(this.path3UpgradeLevel)) * rangeBoost);
    }

    /**
     * returns the value of the attribute
     * if it were to go to the next upgrade
     * for the main.towers first upgrade path
     *
     * @return int
     */

    public int path1UpgradeValue() {
        return this.path1UpgradeLevel == 3 ? -1 :
                PatchOfFire.getDamageLevelBoost(this.path1UpgradeLevel + 1);
    }

    /**
     * returns the value of the attribute
     * if it were to go to the next upgrade
     * for the main.towers second upgrade path
     *
     * @return int
     */

    public int path2UpgradeValue() {
        return this.path2UpgradeLevel == 3 ? -1 :
                (int) ((this.range + (10 * (this.path2UpgradeLevel + 1))) * rangeBoost);
    }

    /**
     * returns the value of the attribute
     * if it were to go to the next upgrade
     * for the main.towers third upgrade path
     *
     * @return int
     */

    public int path3UpgradeValue() {
        return this.path3UpgradeLevel == 3 ? -1 :
                (int) ((PatchOfFire.getDurationLevelBoost(this.path3UpgradeLevel + 1)) * rangeBoost);
    }

    /**
     * upgrades the first upgrade path to the next level
     *
     * @param model
     */

    public void upgradePath1(final DriverModel model) {
        if (this.path1UpgradeLevel == 3) {
            model.towerBuyUpgradeMoney(this.path1UpgradeCosts[this.path1UpgradeLevel]);
            this.path1UpgradeLevel = -1;
        } else {
            model.towerBuyUpgradeMoney(this.path1UpgradeCosts[this.path1UpgradeLevel]);
            this.path1UpgradeLevel++;
        }
    }

    /**
     * upgrades the second upgrade path to the next level
     *
     * @param model
     */

    public void upgradePath2(final DriverModel model) {
        if (this.path2UpgradeLevel == 3) {
            model.towerBuyUpgradeMoney(this.path2UpgradeCosts[this.path2UpgradeLevel]);
            this.path2UpgradeLevel = -1;
        } else {
            model.towerBuyUpgradeMoney(this.path2UpgradeCosts[this.path2UpgradeLevel]);
            this.path2UpgradeLevel++;
        }
    }

    /**
     * upgrades the third upgrade path to the next level
     *
     * @param model
     */

    public void upgradePath3(final DriverModel model) {
        if (this.path3UpgradeLevel == 3) {
            model.towerBuyUpgradeMoney(this.path3UpgradeCosts[this.path3UpgradeLevel]);
            this.path3UpgradeLevel = -1;
        } else {
            model.towerBuyUpgradeMoney(this.path3UpgradeCosts[this.path3UpgradeLevel]);
            this.path3UpgradeLevel++;
        }
    }
}
