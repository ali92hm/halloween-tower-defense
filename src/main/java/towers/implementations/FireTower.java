package towers.implementations;

import mobs.Mob;
import models.DriverModel;
import projectiles.Projectile;
import projectiles.RingOfFire;
import towers.Tower;
import utilities.Position;
import views.Alert;
import views.DriverView;

/**
 * Creates a tower that shoots a projectile
 * that blasts the area with fire effecting
 * all main.controllers.mobs around it
 *
 * @author Scorpion
 */

public class FireTower extends Tower {

    public static final String TOWER_TURRET_IMAGE = null;
    public static final int TOWER_RANGE = 75;
    public static final int TOWER_FIRE_RATE = 800;
    public static final int TOWER_COST = 300;
    private static final String TOWER_BASE_IMAGE = "FireTower.png";
    private static final long serialVersionUID = 1L;
    private static boolean clickedTowerBefore = true;

    /**
     * Constructor for the FireTower
     *
     * @param location
     * @param model
     */

    public FireTower(final Position position, final DriverModel model) {
        super(position, TOWER_BASE_IMAGE, TOWER_TURRET_IMAGE);
        this.range = (int) (TOWER_RANGE);
        this.fireRate = (int) (TOWER_FIRE_RATE);
        this.cost = TOWER_COST;

        this.path1UpgradeName = "Damage";
        this.path1UpgradeIcon = "Damage Icon.png";
        this.path1UpgradeLevel = 0;
        this.path1UpgradeCosts = new int[3];
        this.path1UpgradeCosts[0] = 350;
        this.path1UpgradeCosts[1] = 850;
        this.path1UpgradeCosts[2] = 1750;

        this.path2UpgradeName = "Fire Rate";
        this.path2UpgradeIcon = "Fire Rate Icon.jpg";
        this.path2UpgradeLevel = 0;
        this.path2UpgradeCosts = new int[3];
        this.path2UpgradeCosts[0] = 350;
        this.path2UpgradeCosts[1] = 750;
        this.path2UpgradeCosts[2] = 1800;

        this.path3UpgradeName = "Range";
        this.path3UpgradeIcon = "Range Icon.png";
        this.path3UpgradeLevel = 0;
        this.path3UpgradeCosts = new int[3];
        this.path3UpgradeCosts[0] = 400;
        this.path3UpgradeCosts[1] = 950;
        this.path3UpgradeCosts[2] = 2200;

        model.towerBuyUpgradeMoney(this.cost);
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
                "Fire Burst Tower",
                "This tower blasts the area",
                "with fire burning all main.controllers.mobs",
                "around it.");
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

    public Projectile[] attackMob(DriverModel model) {
        Projectile[] projectiles = new Projectile[1];
        if (this.reloadProgress > 30) {
            this.reloadProgress -= 30;
            return projectiles;
        }

        this.mobTravelDistance = 0;

        this.reloadProgress = (int) (((this.fireRate) - (200 * this.path2UpgradeLevel)) * fireRateBoost);

        for (Mob mob : model.allMobs()) {
            if ((this.position.getDistance(mob.getPosition()) < (((this.range + (25 *
                    this.path3UpgradeLevel)) * rangeBoost) + mob.getRadius())) && (mob.getDistanceTraveled() > this.mobTravelDistance)) {
                    projectiles[0] = new RingOfFire(model, this.position, null, this.path3UpgradeLevel, this.path1UpgradeLevel);
            }
        }
        return projectiles;
    }

    /**
     * getting the range of a tower
     *
     * @return int
     */

    public int getRange() {
        return path3CurrentValue();
    }

    /**
     * returns the current value of the attribute
     * for the main.towers first upgrade path
     *
     * @return int
     */

    public int path1CurrentValue() {
        return RingOfFire.getDamageLevelBoost(this.path1UpgradeLevel);
    }

    /**
     * returns the current value of the attribute
     * for the main.towers second upgrade path
     *
     * @return int
     */

    public int path2CurrentValue() {
        return (int) (60000 / ((this.fireRate) - (200 * this.path2UpgradeLevel)) * fireRateBoost);
    }

    /**
     * returns the current value of the attribute
     * for the main.towers third upgrade path
     *
     * @return int
     */

    public int path3CurrentValue() {
        return (int) ((this.range + (25 * this.path3UpgradeLevel)) * rangeBoost);
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
                RingOfFire.getDamageLevelBoost(this.path1UpgradeLevel + 1);
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
                (int) (60000 / ((this.fireRate) - (200 * (this.path2UpgradeLevel + 1))) * fireRateBoost);
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
                (int) ((this.range + (25 * (this.path3UpgradeLevel + 1))) * rangeBoost);
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
