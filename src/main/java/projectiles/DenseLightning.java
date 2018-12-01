package projectiles;

import mobs.Mob;
import models.DriverModel;
import utilities.Position;
import utilities.Vector;

import java.awt.*;
import java.util.ArrayList;

/**
 * creates a projectile that passes through multiple main.controllers.mobs
 *
 * @author Scorpion
 */

public class DenseLightning extends Projectile {

    public final static int PROJECTILE_WIDTH = 30;
    public final static int PROJECTILE_HEIGHT = 15;
    public final static String FIRE_IMAGE = null;
    public final static int FIRE_WIDTH = 0;
    public final static int FIRE_HEIGHT = 0;
    public final static String IMPACT_IMAGE = null;
    public final static int IMPACT_WIDTH = 0;
    public final static int IMPACT_HEIGHT = 0;
    public final static int PROJECTILE_SPEED = 40;
    public final static int PROJECTILE_RADIUS = 3;
    public final static int PROJECTILE_DAMAGE = 400;
    public final static int DAMAGE_DURATION = 1;
    public final static int SLOW_POTENTCY = 0;
    public final static int SLOW_DURATION = 0;
    public final static int PROJECTILE_HITS = 2;
    public final static int PROJECTILE_MOVEMENTS = 3;
    private static String PROJECTILE_IMAGE = "DenseLightning.png";

    /**
     * constructor for DenseLightning
     *
     * @param model
     * @param startingPosition
     * @param vector
     * @param rangeBoostLevel
     * @param targetCountBoostLevel
     * @param damageBoostLevel
     */

    public DenseLightning(final DriverModel model, final Position startingPosition, final Vector vector,
                          final int rangeBoostLevel, final int targetCountBoostLevel, final int damageBoostLevel) {
        super(model, startingPosition, PROJECTILE_IMAGE, vector, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
        this.speed = PROJECTILE_SPEED;
        this.radius = PROJECTILE_RADIUS;
        this.damage = PROJECTILE_DAMAGE + (PROJECTILE_DAMAGE * (damageBoostLevel * damageBoostLevel));
        this.damageDuration = DAMAGE_DURATION;
        this.slowPotency = SLOW_POTENTCY;
        this.slowDuration = SLOW_DURATION;
        this.hitTargets = 0;
        this.maxHitTargets = PROJECTILE_HITS + (targetCountBoostLevel * 3);
        this.movements = 0;
        this.maxMovements = PROJECTILE_MOVEMENTS + (3 * rangeBoostLevel);
        this.stillAlive = true;
        this.drawRounds = new ArrayList<Position>();
        this.drawRounds.add(this.position);
    }

    /**
     * constructor for DenseLightning
     *
     * @param model
     * @param startingPosition
     * @param vector
     * @param chainingMob
     * @param rangeBoostLevel
     * @param targetCountBoostLevel
     * @param damageBoostLevel
     */

    public DenseLightning(final DriverModel model, final Position startingPosition, final Vector vector, final Mob chainingMob,
                          final int rangeBoostLevel, final int targetCountBoostLevel, final int damageBoostLevel) {
        super(model, startingPosition, PROJECTILE_IMAGE, vector, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
        this.speed = PROJECTILE_SPEED;
        this.radius = PROJECTILE_RADIUS;
        this.damage = PROJECTILE_DAMAGE + (PROJECTILE_DAMAGE * (damageBoostLevel * damageBoostLevel));
        this.damageDuration = DAMAGE_DURATION;
        this.slowPotency = SLOW_POTENTCY;
        this.slowDuration = SLOW_DURATION;
        this.hitTargets = 0;
        this.maxHitTargets = PROJECTILE_HITS + (targetCountBoostLevel * 3);
        this.movements = 0;
        this.maxMovements = PROJECTILE_MOVEMENTS + (3 * rangeBoostLevel);
        this.stillAlive = true;
        this.drawRounds = new ArrayList<Position>();
        this.drawRounds.add(this.position);
        this.chainingMob = chainingMob;
    }

    /**
     * returns how many main.controllers.mobs the projectile can hit
     *
     * @param level
     * @return
     */

    public static int getTargetCountLevelBoost(final int level) {
        return PROJECTILE_HITS + (level * 3);
    }

    /**
     * returns how much damage the round does
     *
     * @param level
     * @return
     */

    public static int getDamageLevelBoost(final int level) {
        return (int) ((PROJECTILE_DAMAGE + (200 * (level * level))) * damageBoost);
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
        if (rangeBoost == true) {
            increase = 2;
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
            if (mob.getPosition().getDistance(this.position) < (mob.getRadius() + this.radius) && mob.isVisible() &&
                    !mob.equals(this.hitMob)) {
                mob.mobHitBy(this);
                this.hitTargets++;
                this.hitMob = mob;
            }
            if (this.hitTargets >= this.maxHitTargets) {
                this.stillAlive = false;
            }
        }
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
