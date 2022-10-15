package com.halloween_tower_defense.projectiles;

import com.halloween_tower_defense.mobs.Mob;
import com.halloween_tower_defense.models.DriverModel;
import com.halloween_tower_defense.utilities.Position;
import com.halloween_tower_defense.utilities.Vector;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * creates a projectile that hits a single mob
 * and adds a single chain lightning to any affected mob
 *
 * @author Scorpion
 */

public class ChainingShock extends Projectile {

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
  public static final int PROJECTILE_DAMAGE = 15;
  public static final int DAMAGE_DURATION = 1;
  public static final int SLOW_POTENTCY = 0;
  public static final int SLOW_DURATION = 0;
  public static final int PROJECTILE_HITS = 1;
  public static final int PROJECTILE_MOVEMENTS = 3;
  public static final int CHAINING_DISTANCE = 100;

  private static final String PROJECTILE_IMAGE = "lightning.png";

  private final Projectile projectile;
  private final Projectile[] chainLightning;
  private final List<Position> chain1DrawRounds;
  private final int rangeBoostLevel;
  private final int currentChain;
  private int damageBoostLevel;

  /**
   * constructor for ChainingShock
   *
   * @param model
   * @param startingPosition
   * @param vector
   * @param targetMob
   * @param rangeBoostLevel
   * @param targetCountBoostLevel
   */

  public ChainingShock(final DriverModel model, final Position startingPosition,
                       final Vector vector, final Mob targetMob,
                       final int rangeBoostLevel, final int targetCountBoostLevel) {
    super(model, startingPosition, PROJECTILE_IMAGE, vector, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
    this.projectile =
        new Shock(model, startingPosition, vector, rangeBoostLevel, this.damageBoostLevel);
    this.chainLightning = new Projectile[5];
    this.rangeBoostLevel = rangeBoostLevel;
    this.speed = PROJECTILE_SPEED;
    this.radius = PROJECTILE_RADIUS;
    this.damage = PROJECTILE_DAMAGE;
    this.damageDuration = DAMAGE_DURATION;
    this.slowPotency = SLOW_POTENTCY;
    this.slowDuration = SLOW_DURATION;
    this.hitTargets = 0;
    this.movements = 0;
    this.currentChain = 0;
    this.stillAlive = true;
    this.drawRounds = new ArrayList<Position>();
    this.chain1DrawRounds = new ArrayList<Position>();
    this.drawRounds.add(this.position);
  }

  /**
   * returns how much damage the round does
   *
   * @param level
   * @return
   */

  public static int getDamageLevelBoost(final int level) {
    return Shock.getDamageLevelBoost(level);
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

    for (Projectile projectile : this.chainLightning) {
      if (projectile != null) {
        if (projectile.stillAlive || !projectile.isSeen()) {
          for (Position position : this.chain1DrawRounds) {
            projectile.seen();
            imageGraphics.drawImage(projectile.getProjectileImage(), (int) position.getXCord(),
                (int) position.getYCord(), null);
          }
        }
      }
    }
  }

  /**
   * checks to see if the projectile is
   * still alive
   */

  public void setAlive() {
    projectile.setAlive();
    this.stillAlive = projectile.stillAlive;

    for (Projectile projectile : this.chainLightning) {
      if (projectile != null) {
        if (projectile.stillAlive) {
          this.stillAlive = true;
        }
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
      this.drawRounds.addAll(projectile.getDrawRounds());
    }

    if (!projectile.stillAlive && this.projectile.getHitMob() != null) {
      for (Mob mob : model.allMobs()) {
        double mobDistance = projectile.getHitMob().getPosition().getDistance(mob.getPosition());
        if (!mob.equals(this.projectile.getHitMob()) && mobDistance < CHAINING_DISTANCE &&
            currentChain < 5) {

          this.chainLightning[currentChain] = this.chainDenseLightningVectors(model,
              projectile.getHitMob(), mob);
          break;
        }
      }
    }

    this.chain1DrawRounds.clear();
    for (Projectile projectile : this.chainLightning) {
      if (projectile != null) {
        if (projectile.stillAlive) {
          projectile.updateProjectile();
        }
        this.chain1DrawRounds.addAll(projectile.getDrawRounds());
      }
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

  public Projectile chainDenseLightningVectors(final DriverModel model, final Mob targetMob,
                                               final Mob chainingMob) {
    Vector vector =
        new Vector(targetMob.getPosition(), chainingMob.getPosition(), Shock.PROJECTILE_SPEED);

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
    }

    Vector trajectory = vector.findVectorSum(new Vector(componentX, componentY));
    return new Shock(model, targetMob.getPosition(), trajectory, targetMob,
        this.rangeBoostLevel, this.damageBoostLevel);
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
