package towers.implementations;

import mobs.Mob;
import models.DriverModel;
import projectiles.IceBeam;
import projectiles.Projectile;
import projectiles.ThunderBolt;
import towers.Tower;
import utilities.Position;
import utilities.Vector;
import views.Alert;
import views.DriverView;

/**
 * Creates a tower that shoots a projectile
 * that slows a mob down
 *
 * @author Scorpion
 */

public class IceTower extends Tower {
    public static final String TOWER_BASE_IMAGE = "IceTower.png";
    public static final String TOWER_TURRET_IMAGE = null;
    public static final int TOWER_RANGE = 180;
    public static final int TOWER_FIRE_RATE = 1;
    public static final int TOWER_COST = 350;
    public static final int NUMBER_MOBS_CAN_ATTACK = 2;
    private static final long serialVersionUID = 1L;
    private static boolean clickedTowerBefore = true;

    /**
     * Constructor for the IceTower
     *
     * @param location
     * @param model
     */

    public IceTower(final Position location, final DriverModel model) {
        super(location, TOWER_BASE_IMAGE, TOWER_TURRET_IMAGE);
        this.range = (int) (TOWER_RANGE);
        this.fireRate = (int) (TOWER_FIRE_RATE);
        this.cost = TOWER_COST;

        this.path1UpgradeName = "Slow Mob";
        this.path1UpgradeIcon = "Damage Icon.png";
        this.path1UpgradeLevel = 0;
        this.path1UpgradeCosts = new int[3];
        this.path1UpgradeCosts[0] = 200;
        this.path1UpgradeCosts[1] = 800;
        this.path1UpgradeCosts[2] = 1600;

        this.path2UpgradeName = "Targets";
        this.path2UpgradeIcon = "Count Icon.jpeg";
        this.path2UpgradeLevel = 0;
        this.path2UpgradeCosts = new int[3];
        this.path2UpgradeCosts[0] = 500;
        this.path2UpgradeCosts[1] = 1150;
        this.path2UpgradeCosts[2] = 2400;

        this.path3UpgradeName = "Range";
        this.path3UpgradeIcon = "Range Icon.png";
        this.path3UpgradeLevel = 0;
        this.path3UpgradeCosts = new int[3];
        this.path3UpgradeCosts[0] = 400;
        this.path3UpgradeCosts[1] = 800;
        this.path3UpgradeCosts[2] = 1600;

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
                "Thick Ice Tower",
                "This tower shoots several",
                "beams of ice slowing all",
                "affected main.controllers.mobs.");
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
        Projectile[] projectiles = new Projectile[5];
        this.attackingMob = new Mob[5];
        if (this.reloadProgress > 30) {
            this.reloadProgress -= 30;
            return projectiles;
        }

        this.attackingMob[0] = null;
        this.attackingMob[1] = null;
        this.attackingMob[2] = null;
        this.attackingMob[3] = null;
        this.attackingMob[4] = null;
        for (Mob mob : model.allMobs()) {
            if (this.position.getDistance(mob.getPosition()) < (this.range + (45 *
                    this.path3UpgradeLevel) * rangeBoost) + mob.getRadius() && !mob.isHitByIceBeam()) {
                if (this.attackingMob[0] == null) {
                    this.attackingMob[0] = mob;
                } else if (this.attackingMob[1] == null) {
                    this.attackingMob[1] = mob;
                } else if (this.attackingMob[2] == null) {
                    this.attackingMob[2] = mob;
                } else if (this.attackingMob[3] == null) {
                    this.attackingMob[3] = mob;
                } else if (this.attackingMob[4] == null) {
                    this.attackingMob[4] = mob;
                }
            }
        }
        if (this.attackingMob[0] != null && (NUMBER_MOBS_CAN_ATTACK + this.path2UpgradeLevel) > 0) {
            projectiles[0] = this.shootMob(this.attackingMob[0], model);
        }
        if (this.attackingMob[1] != null && (NUMBER_MOBS_CAN_ATTACK + this.path2UpgradeLevel) > 1) {
            projectiles[1] = this.shootMob(this.attackingMob[1], model);
        }
        if (this.attackingMob[2] != null && (NUMBER_MOBS_CAN_ATTACK + this.path2UpgradeLevel) > 2) {
            projectiles[2] = this.shootMob(this.attackingMob[2], model);
        }
        if (this.attackingMob[3] != null && (NUMBER_MOBS_CAN_ATTACK + this.path2UpgradeLevel) > 3) {
            projectiles[3] = this.shootMob(this.attackingMob[3], model);
        }
        if (this.attackingMob[4] != null && (NUMBER_MOBS_CAN_ATTACK + this.path2UpgradeLevel) > 4) {
            projectiles[4] = this.shootMob(this.attackingMob[4], model);
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

    public Projectile shootMob(final Mob currentAttackingMob, final DriverModel model) {
        Vector vector = new Vector(this.position, currentAttackingMob.getPosition(), ThunderBolt.PROJECTILE_SPEED);

        double xComp = 0;
        double yComp = 0;

        switch (currentAttackingMob.getDirection()) {
            case 'u':
                yComp = (-1) * currentAttackingMob.getSpeed();
                break;
            case 'r':
                xComp = currentAttackingMob.getSpeed();
                break;
            case 'd':
                yComp = currentAttackingMob.getSpeed();
                break;
            case 'l':
                xComp = (-1) * currentAttackingMob.getSpeed();
                break;
        }

        Vector trajectory = vector.findVectorSum(new Vector(xComp, yComp));
        this.reloadProgress = (int) (this.fireRate * fireRateBoost);
        return new IceBeam(model, this.position, currentAttackingMob, trajectory, this.path1UpgradeLevel, this.path3UpgradeLevel);
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
        return (int) IceBeam.getSlowPotencyLevelBoost(this.path1UpgradeLevel);
    }

    /**
     * returns the current value of the attribute
     * for the main.towers second upgrade path
     *
     * @return int
     */

    public int path2CurrentValue() {
        return NUMBER_MOBS_CAN_ATTACK + this.path2UpgradeLevel;
    }

    /**
     * returns the current value of the attribute
     * for the main.towers third upgrade path
     *
     * @return int
     */

    public int path3CurrentValue() {
        return (int) (this.range + (45 * this.path3UpgradeLevel) * rangeBoost);
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
                (int) IceBeam.getSlowPotencyLevelBoost(this.path1UpgradeLevel + 1);
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
                (NUMBER_MOBS_CAN_ATTACK + this.path2UpgradeLevel + 1);
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
                (int) (this.range + (45 * (this.path3UpgradeLevel + 1)) * rangeBoost);
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
