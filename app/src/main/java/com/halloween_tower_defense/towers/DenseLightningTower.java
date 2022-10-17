package com.halloween_tower_defense.towers;

import com.halloween_tower_defense.mobs.Mob;
import com.halloween_tower_defense.models.GameModel;
import com.halloween_tower_defense.projectiles.ChainingDenseLightning;
import com.halloween_tower_defense.projectiles.DenseLightning;
import com.halloween_tower_defense.projectiles.Projectile;
import com.halloween_tower_defense.utilities.Position;
import com.halloween_tower_defense.utilities.Vector;
import com.halloween_tower_defense.views.Alert;
import com.halloween_tower_defense.views.GameView;

/**
 * Creates a tower that shoots a projectile
 * that goes through multiple targets
 *
 * @author Scorpion
 */

public class DenseLightningTower extends Tower {
  public static final String TOWER_BASE_IMAGE = "DenseLightningTower.png";
  public static final String TOWER_TURRET_IMAGE = null;
  public static final int TOWER_RANGE = 130;
  public static final int TOWER_FIRE_RATE = 1000;
  public static final int TOWER_COST = 600;

  private static boolean towerUnlocked = false;
  private static boolean clickedTowerBefore = false;

  private static final long serialVersionUID = 1L;

  /**
   * Constructor for the DenseLightningTower
   *
   * @param location
   * @param model
   */

  public DenseLightningTower(final Position location, final GameModel model) {
    super(location, TOWER_BASE_IMAGE, TOWER_TURRET_IMAGE);
    this.range = TOWER_RANGE;
    this.fireRate = TOWER_FIRE_RATE;
    this.cost = TOWER_COST;

    this.path1UpgradeName = "Damage";
    this.path1UpgradeIcon = "Damage Icon.png";
    this.path1UpgradeLevel = 0;
    this.path1UpgradeCosts = new int[3];
    this.path1UpgradeCosts[0] = 700;
    this.path1UpgradeCosts[1] = 2100;
    this.path1UpgradeCosts[2] = 4650;

    this.path2UpgradeName = "Targets";
    this.path2UpgradeIcon = "Count Icon.jpeg";
    this.path2UpgradeLevel = 0;
    this.path2UpgradeCosts = new int[3];
    this.path2UpgradeCosts[0] = 550;
    this.path2UpgradeCosts[1] = 1150;
    this.path2UpgradeCosts[2] = 3100;

    this.path3UpgradeName = "Range";
    this.path3UpgradeIcon = "Range Icon.png";
    this.path3UpgradeLevel = 0;
    this.path3UpgradeCosts = new int[3];
    this.path3UpgradeCosts[0] = 700;
    this.path3UpgradeCosts[1] = 1150;
    this.path3UpgradeCosts[2] = 1900;

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

  public static boolean clickedTower(final GameView view) {
    if (clickedTowerBefore) {
      return true;
    }
    new Alert(view, GameView.getImage(TOWER_BASE_IMAGE, 50, 50),
        "Dense Lightning Tower",
        "This tower shoots a single",
        "bolt of lightning which goes",
        "through multiple targets damaging",
        "each target it hits.");
    clickedTowerBefore = true;
    return false;
  }

  /**
   * method to tell towers to attack a mob if
   * their fire rate cool down is finished
   *
   * @param model
   * @return Projectile[]
   */

  public Projectile[] attackMob(final GameModel model) {
    Projectile[] projectiles = new Projectile[1];
    this.attackingMob = new Mob[1];
    this.chainingMobs = new Mob[1];
    if (this.reloadProgress > 30) {
      this.reloadProgress -= 30;
      return projectiles;
    }

    this.mobTravelDistance = 0;
    for (Mob mob : model.allMobs()) {
      if (this.position.getDistance(mob.getPosition()) < ((this.range + (40 *
          this.path3UpgradeLevel)) * rangeBoost) + mob.getRadius()) {
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

  public Projectile shootMob(final GameModel model) {
    Vector vector = new Vector(this.position, this.attackingMob[0].getPosition(),
        DenseLightning.PROJECTILE_SPEED);

    double componentX = 0;
    double componentY = 0;

    switch (this.attackingMob[0].getDirection()) {
      case 'u':
        componentY = (-1) * this.attackingMob[0].getSpeed();
        break;
      case 'r':
        componentX = this.attackingMob[0].getSpeed();
        break;
      case 'd':
        componentY = this.attackingMob[0].getSpeed();
        break;
      case 'l':
        componentX = (-1) * this.attackingMob[0].getSpeed();
        break;
    }

    Vector trajectory = vector.findVectorSum(new Vector(componentX, componentY));
    this.reloadProgress = (int) (this.fireRate * fireRateBoost);
    if (this.towerTurretImage != null) {
      this.towerTurretImage = GameView.rotateImage(towerTurretImage, trajectory.getAngle());
    }

    if (chainLightning) {
      return new ChainingDenseLightning(model, this.position, trajectory, this.attackingMob[0],
          this.path3UpgradeLevel, this.path2UpgradeLevel);
    }
    return new DenseLightning(model, this.position, trajectory, this.path3UpgradeLevel,
        this.path2UpgradeLevel, this.path1UpgradeLevel);
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
   * for the towers first upgrade path
   *
   * @return int
   */

  public int path1CurrentValue() {
    return (getChainLightningUpgrade() ?
        ChainingDenseLightning.getDamageLevelBoost(this.path1UpgradeLevel) :
        DenseLightning.getDamageLevelBoost(this.path1UpgradeLevel));
  }

  /**
   * returns the current value of the attribute
   * for the towers second upgrade path
   *
   * @return int
   */

  public int path2CurrentValue() {
    return DenseLightning.getTargetCountLevelBoost(this.path2UpgradeLevel);
  }

  /**
   * returns the current value of the attribute
   * for the towers third upgrade path
   *
   * @return int
   */

  public int path3CurrentValue() {
    return (int) ((this.range + (40 * this.path3UpgradeLevel)) * rangeBoost);
  }

  /**
   * returns the value of the attribute
   * if it were to go to the next upgrade
   * for the towers first upgrade path
   *
   * @return int
   */

  public int path1UpgradeValue() {
    return this.path1UpgradeLevel == 3 ? -1 :
        (getChainLightningUpgrade() ?
            ChainingDenseLightning.getDamageLevelBoost(this.path1UpgradeLevel + 1) :
            DenseLightning.getDamageLevelBoost(this.path1UpgradeLevel + 1));
  }

  /**
   * returns the value of the attribute
   * if it were to go to the next upgrade
   * for the towers second upgrade path
   *
   * @return int
   */

  public int path2UpgradeValue() {
    return this.path2UpgradeLevel == 3 ? -1 :
        DenseLightning.getTargetCountLevelBoost(this.path2UpgradeLevel + 1);
  }

  /**
   * returns the value of the attribute
   * if it were to go to the next upgrade
   * for the towers third upgrade path
   *
   * @return int
   */

  public int path3UpgradeValue() {
    return this.path3UpgradeLevel == 3 ? -1 :
        (int) ((this.range + (40 * (this.path3UpgradeLevel + 1))) * rangeBoost);
  }

  /**
   * upgrades the first upgrade path to the next level
   *
   * @param model
   */

  public void upgradePath1(final GameModel model) {
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

  public void upgradePath2(final GameModel model) {
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

  public void upgradePath3(final GameModel model) {
    if (this.path3UpgradeLevel == 3) {
      model.towerBuyUpgradeMoney(this.path3UpgradeCosts[this.path3UpgradeLevel]);
      this.path3UpgradeLevel = -1;
    } else {
      model.towerBuyUpgradeMoney(this.path3UpgradeCosts[this.path3UpgradeLevel]);
      this.path3UpgradeLevel++;
    }
  }
}
