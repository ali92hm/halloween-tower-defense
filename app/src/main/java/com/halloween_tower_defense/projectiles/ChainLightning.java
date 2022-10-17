package com.halloween_tower_defense.projectiles;

import com.halloween_tower_defense.mobs.Mob;
import com.halloween_tower_defense.models.GameModel;
import com.halloween_tower_defense.towers.Tower;
import com.halloween_tower_defense.utilities.Position;
import com.halloween_tower_defense.utilities.Vector;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * creates a projectile that hits a single mob
 * and adds two chain lightning to any affected mob
 *
 * @author Scorpion
 */

public class ChainLightning extends Projectile {

  public static final String PROJECTILE_IMAGE = "lightning.png";
  public static final int PROJECTILE_WIDTH = 30;
  public static final int PROJECTILE_HEIGHT = 15;
  public static final String FIRE_IMAGE = null;
  public static final int FIRE_WIDTH = 0;
  public static final int FIRE_HEIGHT = 0;
  public static final String IMPACT_IMAGE = null;
  public static final int IMPACT_WIDTH = 0;
  public static final int IMPACT_HEIGHT = 0;
  public static final int PROJECTILE_SPEED = 20;
  public static final int PROJECTILE_RADIUS = 3;
  public static final int PROJECTILE_DAMAGE = 200;
  public static final int DAMAGE_DURATION = 1;
  public static final int SLOW_POTENTCY = 0;
  public static final int SLOW_DURATION = 0;
  public static final int PROJECTILE_HITS = 1;
  public static final int PROJECTILE_MOVEMENTS = 3;

  private final Projectile projectile;
  private final Mob targetMob;
  private final Mob[] chainingMobs;
  private final List<Position> chain1DrawRounds;
  private final List<Position> chain2DrawRounds;
  private final int rangeBoostLevel;
  private final int damageBoostLevel;
  private Projectile chain1;
  private Projectile chain2;

  /**
   * constructor for ChainLightning
   *
   * @param model
   * @param startingPosition
   * @param vector
   * @param targetMob
   * @param mobs
   * @param rangeBoostLevel
   * @param damageBoostLevel
   */

  public ChainLightning(final GameModel model, final Position startingPosition,
                        final Vector vector,
                        final Mob targetMob, final Mob[] mobs, final int rangeBoostLevel,
                        final int damageBoostLevel) {
    super(model, startingPosition, PROJECTILE_IMAGE, vector, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
    this.projectile =
        new ThunderBolt(model, startingPosition, vector, rangeBoostLevel, damageBoostLevel, true);
    this.targetMob = targetMob;
    this.chainingMobs = mobs;
    this.rangeBoostLevel = rangeBoostLevel;
    this.damageBoostLevel = damageBoostLevel;
    this.speed = PROJECTILE_SPEED;
    this.radius = PROJECTILE_RADIUS;
    this.damage = PROJECTILE_DAMAGE;
    this.damageDuration = DAMAGE_DURATION;
    this.slowPotency = SLOW_POTENTCY;
    this.slowDuration = SLOW_DURATION;
    this.hitTargets = 0;
    this.movements = 0;
    this.stillAlive = true;
    this.drawRounds = new ArrayList<Position>();
    this.chain1DrawRounds = new ArrayList<Position>();
    this.chain2DrawRounds = new ArrayList<Position>();
    this.drawRounds.add(this.position);
  }

  /**
   * returns how much damage the round does
   *
   * @param level
   * @return
   */

  public static int getDamageLevelBoost(final int level) {
    return Tower.getFireDOTDamageUpgrade() ? ThunderBolt.getDamageLevelBoost(level) * 3 :
        ThunderBolt.getDamageLevelBoost(level);
  }

  /**
   * returns how long the projectile lasts for
   *
   * @param level
   * @return
   */

  public static int getDurationLevelBoost(final int level) {
    return PROJECTILE_MOVEMENTS + (level * 14);
  }

  /**
   * adds the image(s) of any projectile
   * that needs to be drawn to the map
   *
   * @param imageGraphics
   */

  public void addImages(final Graphics imageGraphics) {
    ArrayList<Position> drawRounds = new ArrayList<Position>(this.drawRounds);
    for (Position position : drawRounds) {
      imageGraphics.drawImage(this.projectileImage, (int) position.getXCord(),
          (int) position.getYCord(), null);
    }
    ArrayList<Position> chain1DrawRounds = new ArrayList<Position>(this.chain1DrawRounds);
    for (Position position : chain1DrawRounds) {
      imageGraphics.drawImage(chain1.getProjectileImage(), (int) position.getXCord(),
          (int) position.getYCord(), null);
    }
    ArrayList<Position> chain2DrawRounds = new ArrayList<Position>(this.chain2DrawRounds);
    for (Position position : chain2DrawRounds) {
      imageGraphics.drawImage(chain2.projectileImage, (int) position.getXCord(),
          (int) position.getYCord(), null);
    }
  }

  /**
   * checks to see if the projectile is
   * still alive
   */

  public void setAlive() {
    projectile.setAlive();
    if (!projectile.stillAlive && chainingMobs[0] == null && chainingMobs[1] == null) {
      this.stillAlive = false;
    } else if (!projectile.stillAlive && chain1 == null && chain2 == null) {
      if (chainingMobs[0] != null) {
        this.chain1 = this.chainLightningVectors(model, targetMob, chainingMobs[0]);
      }
      if (chainingMobs[1] != null) {
        this.chain2 = this.chainLightningVectors(model, targetMob, chainingMobs[1]);
      }
    } else if (this.chain1 != null && this.chain2 != null) {
      if (!this.chain1.stillAlive && !this.chain2.stillAlive) {
        this.stillAlive = false;
      }
    } else if (this.chain1 != null) {
      if (!this.chain1.stillAlive) {
        this.stillAlive = false;
      }
    } else if (this.chain2 != null) {
      if (!this.chain2.stillAlive) {
        this.stillAlive = false;
      }
    }
  }

  /**
   * moves the projectile and checks whether its
   * impacted a mob
   */

  public void updateProjectile() {
    this.setAlive();
    this.drawRounds.clear();
    if (projectile.stillAlive) {
      projectile.updateProjectile();
    }
    this.drawRounds.addAll(projectile.getDrawRounds());

    if (chain1 != null) {
      if (chain1.stillAlive) {
        chain1.updateProjectile();
      }
      this.chain1DrawRounds.addAll(chain1.getDrawRounds());
    }

    if (chain2 != null) {
      if (chain2.stillAlive) {
        chain2.updateProjectile();
      }
      this.chain2DrawRounds.addAll(chain2.getDrawRounds());
    }
  }

  /**
   * sets the new vector for the chaining round to go
   *
   * @param model
   * @param targetMob
   * @param chainingMob
   * @return
   */

  public Projectile chainLightningVectors(final GameModel model, final Mob targetMob,
                                          final Mob chainingMob) {
    Vector vector = new Vector(targetMob.getPosition(), chainingMob.getPosition(),
        ThunderBolt.PROJECTILE_SPEED);

    double componentX = 0;
    double componentY = 0;

    switch (chainingMob.getDirection()) {
      case 'u':
        componentY = (-1) * chainingMob.getSpeed();
        break;
      case 'r':
        componentX = chainingMob.getSpeed();
        break;
      case 'd':
        componentY = chainingMob.getSpeed();
        break;
      case 'l':
        componentX = (-1) * chainingMob.getSpeed();
        break;
      default:
    }

    Vector trajectory = vector.findVectorSum(new Vector(componentX, componentY));
    return new ThunderBolt(model, targetMob.getPosition(), trajectory, this.targetMob,
        this.rangeBoostLevel, this.damageBoostLevel, true);
  }

  /**
   * returns true if the projectile
   * is lightning
   *
   * @return boolean
   */

  public boolean isLightning() {
    return true;
  }
}
