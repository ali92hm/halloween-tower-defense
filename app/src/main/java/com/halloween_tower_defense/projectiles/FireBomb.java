package com.halloween_tower_defense.projectiles;

import com.halloween_tower_defense.mobs.Mob;
import com.halloween_tower_defense.models.GameModel;
import com.halloween_tower_defense.towers.Tower;
import com.halloween_tower_defense.utilities.Position;
import com.halloween_tower_defense.utilities.Vector;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * creates a projectile that hits a mob
 * creating a fire blast projectile
 *
 * @author Scorpion
 */

public class FireBomb extends Projectile {

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
  public static final int PROJECTILE_DAMAGE = 100;
  public static final int PROJECTILE_DOT_DAMAGE = 10;
  public static final int DAMAGE_DURATION = 1;
  public static final int DOT_DAMAGE_DURATION = 30;
  public static final int SLOW_POTENTCY = 0;
  public static final int SLOW_DURATION = 0;
  public static final int PROJECTILE_HITS = 1;
  public static final int PROJECTILE_MOVEMENTS = 10;
  private static final String PROJECTILE_IMAGE = "FireBombProjectile.png";

  /**
   * constructor for FireBomb
   *
   * @param model
   * @param startingPosition
   * @param vector
   * @param durationBoostLevel
   * @param damageBoostLevel
   */

  public FireBomb(final GameModel model, final Position startingPosition, final Vector vector,
                  final int durationBoostLevel, final int damageBoostLevel) {
    super(model, startingPosition, PROJECTILE_IMAGE, vector, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
    this.speed = PROJECTILE_SPEED;
    this.radius = PROJECTILE_RADIUS;
    this.damage = Tower.getFireDOTDamageUpgrade() ?
        PROJECTILE_DOT_DAMAGE + (((PROJECTILE_DOT_DAMAGE * 4) / 3) * 2 *
            damageBoostLevel * damageBoostLevel) : PROJECTILE_DAMAGE + (PROJECTILE_DAMAGE * 2 *
        damageBoostLevel * damageBoostLevel);
    this.damageDuration = Tower.getFireDOTDamageUpgrade() ? DOT_DAMAGE_DURATION : DAMAGE_DURATION;
    this.slowPotency = SLOW_POTENTCY;
    this.slowDuration = SLOW_DURATION;
    this.hitTargets = 0;
    this.maxHitTargets = PROJECTILE_HITS;
    this.movements = 0;
    this.maxMovements = PROJECTILE_MOVEMENTS;
    this.stillAlive = true;
    this.damageBoostLevel = damageBoostLevel;
    this.durationBoostLevel = durationBoostLevel;
    this.drawRounds = new ArrayList<Position>();
    this.drawRounds.add(this.position);
  }

  /**
   * returns how much damage the round does
   *
   * @param level
   * @return
   */

  public static int getDamageLevelBoost(final int level) {
    return FireBlast.getDamageLevelBoost(level);
  }

  /**
   * adds the image(s) of any projectile
   * that needs to be drawn to the map
   *
   * @param imageGraphics
   */

  public void addImages(final Graphics imageGraphics) {
    for (Position position : this.drawRounds) {
      imageGraphics.drawImage(this.projectileImage, (int) position.getXCord(),
          (int) position.getYCord(), null);
    }
  }

  /**
   * checks to see if the projectile is
   * still alive
   */

  public void setAlive() {
    int increase = 0;
    if (rangeBoost) {
      increase = 4;
    }
    if (this.movements > this.maxMovements + increase) {
      this.stillAlive = false;
    }
  }

  /**
   * moves the projectile and checks whether its
   * impacted a mob
   */

  public void updateProjectile() {
    this.setAlive();
    this.position = this.vector.getNextPosition(this.position, this.speed);
    this.drawRounds.clear();
    this.drawRounds.add(this.position);
    this.movements++;
    if (this.movements > 0) {
      this.firePosition = null;
      this.impactPosition = null;
      this.endPosition = null;
    }
    for (Mob mob : this.model.allMobs()) {
      if (mob.getPosition().getDistance(this.position) < (mob.getRadius() + this.radius) &&
          mob.isVisible() &&
          !mob.equals(this.hitMob)) {
        mob.mobHitBy(this);
        this.hitTargets++;
        this.hitMob = mob;
        this.model.addProjectile(new FireBlast(this.model, this.position, null,
            this.rangeBoostLevel, this.damageBoostLevel));
      }
      if (this.hitTargets >= this.maxHitTargets) {
        this.stillAlive = false;
      }
    }
  }

  /**
   * returns true if the projectile
   * is fire
   *
   * @return boolean
   */

  public boolean isFire() {
    return true;
  }
}
