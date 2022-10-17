package com.halloween_tower_defense.towers;

import com.halloween_tower_defense.mobs.Mob;
import com.halloween_tower_defense.models.GameModel;
import com.halloween_tower_defense.projectiles.ChainingShock;
import com.halloween_tower_defense.projectiles.Projectile;
import com.halloween_tower_defense.projectiles.Shock;
import com.halloween_tower_defense.projectiles.ThunderBolt;
import com.halloween_tower_defense.utilities.Position;
import com.halloween_tower_defense.utilities.Vector;
import com.halloween_tower_defense.views.Alert;
import com.halloween_tower_defense.views.GameView;

/**
 * Creates a tower that shoots multiple projectiles
 * that hit several targets
 *
 * @author Scorpion
 */

public class TeslaTower extends Tower {
  private static final String TOWER_BASE_IMAGE = "TeslaTower.png";
  public static final String TOWER_TURRET_IMAGE = null;
  public static final int TOWER_RANGE = 80;
  public static final int TOWER_FIRE_RATE = 50;
  public static final int TOWER_COST = 1000;
  public static final int CHAINING_DISTANCE = 100;
  public static final int NUMBER_MOBS_CAN_ATTACK = 2;

  private static boolean towerUnlocked = false;
  private static boolean clickedTowerBefore = false;

  private static final long serialVersionUID = 1L;

  /**
   * Constructor for the TeslaTower
   *
   * @param location
   * @param model
   */

  public TeslaTower(final Position location, final GameModel model) {
    super(location, TOWER_BASE_IMAGE, TOWER_TURRET_IMAGE);
    this.range = TOWER_RANGE;
    this.fireRate = TOWER_FIRE_RATE;
    this.cost = TOWER_COST;

    this.path1UpgradeName = "Damage";
    this.path1UpgradeIcon = "Damage Icon.png";
    this.path1UpgradeLevel = 0;
    this.path1UpgradeCosts = new int[3];
    this.path1UpgradeCosts[0] = 1100;
    this.path1UpgradeCosts[1] = 3200;
    this.path1UpgradeCosts[2] = 7200;

    this.path2UpgradeName = "Targets";
    this.path2UpgradeIcon = "Count Icon.jpeg";
    this.path2UpgradeLevel = 0;
    this.path2UpgradeCosts = new int[3];
    this.path2UpgradeCosts[0] = 1500;
    this.path2UpgradeCosts[1] = 4100;
    this.path2UpgradeCosts[2] = 8100;

    this.path3UpgradeName = "Range";
    this.path3UpgradeIcon = "Range Icon.png";
    this.path3UpgradeLevel = 0;
    this.path3UpgradeCosts = new int[3];
    this.path3UpgradeCosts[0] = 1400;
    this.path3UpgradeCosts[1] = 2800;
    this.path3UpgradeCosts[2] = 3600;

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
        "Tesla Tower",
        "This tower shoots multiple",
        "bolt of lightning at a multiple",
        "targets very rapidly.");
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
      if (this.position.getDistance(mob.getPosition()) < (this.range + (20 *
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

  public Projectile shootMob(final Mob currentAttackingMob, final GameModel model) {
    Vector vector =
        new Vector(this.position, currentAttackingMob.getPosition(), ThunderBolt.PROJECTILE_SPEED);

    double componentX = 0;
    double componentY = 0;

    switch (currentAttackingMob.getDirection()) {
      case 'u':
        componentY = (-1) * currentAttackingMob.getSpeed();
        break;
      case 'r':
        componentX = currentAttackingMob.getSpeed();
        break;
      case 'd':
        componentY = currentAttackingMob.getSpeed();
        break;
      case 'l':
        componentX = (-1) * currentAttackingMob.getSpeed();
        break;
    }

    Vector trajectory = vector.findVectorSum(new Vector(componentX, componentY));
    this.reloadProgress = (int) (this.fireRate * fireRateBoost);

    if (chainLightning) {
      return new ChainingShock(model, this.position, trajectory, this.attackingMob[0],
          this.path3UpgradeLevel, this.path2UpgradeLevel);
    }
    return new Shock(model, this.position, trajectory, this.path3UpgradeLevel,
        this.path1UpgradeLevel);
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
    return Shock.getDamageLevelBoost(this.path1UpgradeLevel);
  }

  /**
   * returns the current value of the attribute
   * for the towers second upgrade path
   *
   * @return int
   */

  public int path2CurrentValue() {
    return NUMBER_MOBS_CAN_ATTACK + this.path2UpgradeLevel;
  }

  /**
   * returns the current value of the attribute
   * for the towers third upgrade path
   *
   * @return int
   */

  public int path3CurrentValue() {
    return (int) (this.range + (20 * this.path3UpgradeLevel) * rangeBoost);
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
        Shock.getDamageLevelBoost(this.path1UpgradeLevel + 1);
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
        (NUMBER_MOBS_CAN_ATTACK + this.path2UpgradeLevel + 1);
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
        (int) (this.range + (20 * (this.path3UpgradeLevel + 1)) * rangeBoost);
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
