package com.halloween_tower_defense.projectiles;

import com.halloween_tower_defense.mobs.Mob;
import com.halloween_tower_defense.models.GameModel;
import com.halloween_tower_defense.towers.Tower;
import com.halloween_tower_defense.utilities.Position;
import com.halloween_tower_defense.utilities.Vector;
import com.halloween_tower_defense.views.GameView;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * creates a projectile that expands out from the
 * target hitting all effected mobs
 *
 * @author Scorpion
 */

public class RingOfFire extends Projectile {

  public static final int PROJECTILE_WIDTH = 50;
  public static final int PROJECTILE_HEIGHT = 50;
  public static final String FIRE_IMAGE = null;
  public static final int FIRE_WIDTH = 0;
  public static final int FIRE_HEIGHT = 0;
  public static final String IMPACT_IMAGE = null;
  public static final int IMPACT_WIDTH = 0;
  public static final int IMPACT_HEIGHT = 0;
  public static final int PROJECTILE_SPEED = 25;
  public static final int PROJECTILE_RADIUS = 75;
  public static final int PROJECTILE_DAMAGE = 150;
  public static final int PROJECTILE_DOT_DAMAGE = 10;
  public static final int DAMAGE_DURATION = 1;
  public static final int DOT_DAMAGE_DURATION = 45;
  public static final int SLOW_POTENTCY = 0;
  public static final int SLOW_DURATION = 0;
  public static final int PROJECTILE_HITS = -1;
  public static final int PROJECTILE_MOVEMENTS = 3;
  private static final String PROJECTILE_IMAGE = "Ring of Fire.png";
  public Position startingPosition;
  public int width = PROJECTILE_WIDTH;
  public int height = PROJECTILE_HEIGHT;

  /**
   * constructor for RingOfFire
   *
   * @param model
   * @param startingPosition
   * @param vector
   * @param rangeBoostLevel
   * @param damageBoostLevel
   */

  public RingOfFire(final GameModel model, final Position startingPosition,
                    final Vector vector, final int rangeBoostLevel, final int damageBoostLevel) {
    super(model, startingPosition, PROJECTILE_IMAGE, vector, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
    this.speed = PROJECTILE_SPEED;
    this.radius = PROJECTILE_RADIUS + (25 * rangeBoostLevel);
    this.damage = Tower.getFireDOTDamageUpgrade() ?
        PROJECTILE_DOT_DAMAGE + (((PROJECTILE_DOT_DAMAGE * 4) / 3) *
            damageBoostLevel * damageBoostLevel) : PROJECTILE_DAMAGE + (PROJECTILE_DAMAGE *
        damageBoostLevel * damageBoostLevel);
    this.damageDuration = Tower.getFireDOTDamageUpgrade() ? DOT_DAMAGE_DURATION : DAMAGE_DURATION;
    this.slowPotency = SLOW_POTENTCY;
    this.slowDuration = SLOW_DURATION;
    this.startingPosition = startingPosition;
    this.movements = 0;
    this.maxMovements = PROJECTILE_MOVEMENTS + rangeBoostLevel;
    this.stillAlive = true;
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
    return (int) ((Tower.getFireDOTDamageUpgrade() ?
        PROJECTILE_DOT_DAMAGE + (((PROJECTILE_DOT_DAMAGE * 4) / 3) *
            level * level) : PROJECTILE_DAMAGE + (PROJECTILE_DAMAGE *
        level * level)) * damageBoost);
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
      increase = 1;
    }

    if (this.movements >= this.maxMovements + increase) {
      this.stillAlive = false;
    }
  }

  /**
   * moves the projectile and checks whether its
   * impacted a mob
   */

  public void updateProjectile() {
    this.setAlive();
    this.width += PROJECTILE_SPEED;
    this.height += PROJECTILE_SPEED;
    this.projectileImage = GameView.getImage(PROJECTILE_IMAGE, width, height);
    this.position = new Position(this.startingPosition.getXCord() - (width / 2),
        this.startingPosition.getYCord() - (height / 2));
    this.drawRounds.clear();
    this.drawRounds.add(this.position);
    this.movements++;
    if (this.movements > 0) {
      this.firePosition = null;
      this.impactPosition = null;
      this.endPosition = null;
    }

    if (this.movements == this.maxMovements - 1) {
      for (Mob mob : this.model.allMobs()) {
        if (mob.getPosition().getDistance(this.startingPosition) <
            (mob.getRadius() + this.radius) && mob.isVisible()) {
          mob.mobHitBy(this);
        }
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
