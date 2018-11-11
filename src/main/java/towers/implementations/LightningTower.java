package towers.implementations;

import mobs.Mob;
import models.DriverModel;
import projectiles.ChainLightning;
import projectiles.Projectile;
import projectiles.ThunderBolt;
import towers.Tower;
import utilities.Position;
import utilities.Vector;
import views.Alert;
import views.DriverView;

/**
 * Creates a tower that shoots a projectile
 * that hits a single target
 *
 * @author Scorpion
 */

public class LightningTower extends Tower {

    public static final String TOWER_TURRET_IMAGE = null;
    public static final int TOWER_RANGE = 80;
    public static final int TOWER_FIRE_RATE = 700;
    public static final int TOWER_COST = 250;
    public static final int CHAINING_DISTANCE = 100;
    private static final String TOWER_BASE_IMAGE = "LightningTower.png";
    private static final long serialVersionUID = 1L;
    private static boolean clickedTowerBefore = true;

    /**
     * Constructor for the LightningTower
     *
     * @param location
     * @param model
     */

    public LightningTower(final Position position, final DriverModel model) {
        super(position, TOWER_BASE_IMAGE, TOWER_TURRET_IMAGE);
        this.range = (int) (TOWER_RANGE);
        this.fireRate = (int) (TOWER_FIRE_RATE);
        this.cost = (int) (TOWER_COST * towerCostDecrease);

        this.path1UpgradeName = "Damage";
        this.path1UpgradeIcon = "Damage Icon.png";
        this.path1UpgradeLevel = 0;
        this.path1UpgradeCosts = new int[3];
        this.path1UpgradeCosts[0] = 250;
        this.path1UpgradeCosts[1] = 1350;
        this.path1UpgradeCosts[2] = 2300;

        this.path2UpgradeName = "Fire Rate";
        this.path2UpgradeIcon = "Fire Rate Icon.jpg";
        this.path2UpgradeLevel = 0;
        this.path2UpgradeCosts = new int[3];
        this.path2UpgradeCosts[0] = 200;
        this.path2UpgradeCosts[1] = 950;
        this.path2UpgradeCosts[2] = 3200;

        this.path3UpgradeName = "Range";
        this.path3UpgradeIcon = "Range Icon.png";
        this.path3UpgradeLevel = 0;
        this.path3UpgradeCosts = new int[3];
        this.path3UpgradeCosts[0] = 100;
        this.path3UpgradeCosts[1] = 350;
        this.path3UpgradeCosts[2] = 700;

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
                "Lightning Tower",
                "This tower shoots a single",
                "bolt of lightning at a target",
                "doing high damage.");
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
        Projectile[] projectile = new Projectile[1];
        this.attackingMob = new Mob[1];
        this.chainingMobs = new Mob[2];
        if (this.reloadProgress > 30) {
            this.reloadProgress -= 30;
            return projectile;
        }

        for (Mob mob : model.allMobs()) {
            if (this.position.getDistance(mob.getPosition()) < ((this.range + (20 *
                    this.path3UpgradeLevel)) * rangeBoost) + mob.getRadius()) {
                this.attackingMob[0] = mob;
                break;
            }
        }

        if (chainLightning && this.attackingMob[0] != null) {
            for (Mob mob : model.allMobs()) {
                if (this.position.getDistance(mob.getPosition()) < (this.range * rangeBoost) + mob.getRadius() &&
                        !mob.equals(this.attackingMob[0]) &&
                        mob.getPosition().getDistance(this.attackingMob[0].getPosition()) < CHAINING_DISTANCE) {
                    this.attackingMob[0] = mob;
                    break;
                }
            }
        }

        if (this.attackingMob[0] != null) {
            for (Mob mob : model.allMobs()) {
                double mobDistance = this.attackingMob[0].getPosition().getDistance(mob.getPosition());
                if (!mob.equals(this.attackingMob[0]) && mobDistance < CHAINING_DISTANCE) {
                    if (this.chainingMobs[0] == null) {
                        this.chainingMobs[0] = mob;
                    } else if (this.chainingMobs[1] == null) {
                        this.chainingMobs[1] = mob;
                        break;
                    }
                }
            }
        }

        if (this.attackingMob[0] != null) {
            projectile[0] = this.shootMob(model);
        }
        return projectile;
    }

    /**
     * has the tower actually create and release
     * a projectile
     *
     * @param model
     * @return
     */

    public Projectile shootMob(final DriverModel model) {
        Vector vector = new Vector(this.position, this.attackingMob[0].getPosition(), ThunderBolt.PROJECTILE_SPEED);

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
        this.reloadProgress = (int) (this.fireRate * fireRateBoost) - (175 * this.path2UpgradeLevel);
        if (chainLightning) {
            return new ChainLightning(model, this.position, trajectory, this.attackingMob[0], this.chainingMobs,
                    this.path3UpgradeLevel, this.path1UpgradeLevel);
        }
        return new ThunderBolt(model, this.position, trajectory, this.path3UpgradeLevel, this.path1UpgradeLevel, false);
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
        return (Tower.getChainLightningUpgrade() ? ChainLightning.getDamageLevelBoost(this.path1UpgradeLevel) :
                ThunderBolt.getDamageLevelBoost(this.path1UpgradeLevel));
    }

    /**
     * returns the current value of the attribute
     * for the main.towers second upgrade path
     *
     * @return int
     */

    public int path2CurrentValue() {
        return (int) (60000 / ((this.fireRate * fireRateBoost) - (175 * this.path2UpgradeLevel)));
    }

    /**
     * returns the current value of the attribute
     * for the main.towers third upgrade path
     *
     * @return int
     */

    public int path3CurrentValue() {
        return (int) ((this.range + (20 * this.path3UpgradeLevel)) * rangeBoost);
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
                (Tower.getChainLightningUpgrade() ? ChainLightning.getDamageLevelBoost(this.path1UpgradeLevel + 1) :
                        ThunderBolt.getDamageLevelBoost(this.path1UpgradeLevel + 1));
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
                (int) (60000 / ((this.fireRate * fireRateBoost) - (175 * (this.path2UpgradeLevel + 1))));
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
                (int) ((this.range + (20 * (this.path3UpgradeLevel + 1))) * rangeBoost);
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
