package projectiles;

import mobs.Mob;
import models.DriverModel;
import utilities.Position;
import utilities.Vector;

import java.awt.*;
import java.util.ArrayList;

/**
 * creates a projectile that shoots
 * in a line of lightning from the tower
 * hitting a single mob
 *
 * @author Scorpion
 */

public class ThunderBolt extends Projectile {

    public final static String PROJECTILE_IMAGE = "lightning.png";
    public final static int PROJECTILE_WIDTH = 30;
    public final static int PROJECTILE_HEIGHT = 15;
    public final static String FIRE_IMAGE = null;
    public final static int FIRE_WIDTH = 0;
    public final static int FIRE_HEIGHT = 0;
    public final static String IMPACT_IMAGE = null;
    public final static int IMPACT_WIDTH = 0;
    public final static int IMPACT_HEIGHT = 0;
    public final static int PROJECTILE_SPEED = 20;
    public final static int PROJECTILE_RADIUS = 3;
    public final static int PROJECTILE_DAMAGE = 400;
    public final static int DAMAGE_DURATION = 1;
    public final static int SLOW_POTENTCY = 0;
    public final static int SLOW_DURATION = 0;
    public final static int PROJECTILE_HITS = 1;
    public final static int PROJECTILE_MOVEMENTS = 6;

    /**
     * constructor for ThunderBolt
     *
     * @param model
     * @param startingPosition
     * @param vector
     * @param rangeBoostLevel
     * @param damageBoostLevel
     * @param chain
     */

    public ThunderBolt(final DriverModel model, final Position startingPosition, final Vector vector,
                       final int rangeBoostLevel, final int damageBoostLevel, final boolean chain) {
        super(model, startingPosition, PROJECTILE_IMAGE, vector, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
        this.speed = PROJECTILE_SPEED;
        this.radius = PROJECTILE_RADIUS;
        this.damage = PROJECTILE_DAMAGE;
        this.damage += (((this.damage * 2) / 3) * damageBoostLevel * damageBoostLevel);
        this.damageDuration = DAMAGE_DURATION;
        this.slowPotency = SLOW_POTENTCY;
        this.slowDuration = SLOW_DURATION;
        this.hitTargets = 0;
        this.maxHitTargets = 1;
        this.movements = 0;
        this.maxMovements = PROJECTILE_MOVEMENTS + (2 * rangeBoostLevel);
        this.stillAlive = true;
        this.drawRounds = new ArrayList<Position>();
        this.drawRounds.add(this.position);
    }

    /**
     * constructor for chaining version of a ThunderBolt
     *
     * @param model
     * @param startingPosition
     * @param vector
     * @param chainingMob
     * @param rangeBoostLevel
     * @param damageBoostLevel
     * @param chain
     */

    public ThunderBolt(final DriverModel model, final Position startingPosition, final Vector vector, final Mob chainingMob,
                       final int rangeBoostLevel, final int damageBoostLevel, final boolean chain) {
        super(model, startingPosition, PROJECTILE_IMAGE, vector, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
        this.speed = PROJECTILE_SPEED;
        this.radius = PROJECTILE_RADIUS;
        this.damage = PROJECTILE_DAMAGE;
        this.damage += (((this.damage * 2) / 3) * damageBoostLevel * damageBoostLevel);
        this.damageDuration = DAMAGE_DURATION;
        this.slowPotency = SLOW_POTENTCY;
        this.slowDuration = SLOW_DURATION;
        this.hitTargets = 0;
        this.maxHitTargets = 1;
        this.movements = 0;
        this.maxMovements = PROJECTILE_MOVEMENTS + (2 * rangeBoostLevel);
        this.stillAlive = true;
        this.drawRounds = new ArrayList<Position>();
        this.drawRounds.add(this.position);
        this.chainingMob = chainingMob;
    }

    /**
     * returns how much damage the round does
     *
     * @param level
     * @return
     */

    public static int getDamageLevelBoost(final int level) {
        int damage = PROJECTILE_DAMAGE;
        return (int) ((damage + (((damage * 2) / 3) * level * level)) * damageBoost);
    }

    /**
     * adds the image(s) of any projectile
     * that needs to be drawn to the map
     *
     * @param imageGraphics
     */

    public void addImages(final Graphics imageGraphics) {
        ArrayList<Position> tempPosition = new ArrayList<Position>(this.drawRounds);
        for (Position position : tempPosition) {
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
            increase = 3;
        }
        if (this.movements >= this.maxMovements + increase) {
            this.stillAlive = false;
            this.endPosition = this.position;
        }
    }

    /**
     * moves the projectile and checks whether its
     * impacted a mob
     */

    public void updateProjectile() {
        this.setAlive();
        if (!this.stillAlive) {
            return;
        }
        if (this.movements > 0) {
            this.firePosition = null;
            this.impactPosition = null;
            this.endPosition = null;
        }

        this.position = this.vector.getNextPosition(this.position, this.speed);
        this.drawRounds.add(this.position);
        for (Mob mob : this.model.allMobs()) {
            if (mob.equals(chainingMob)) {
                continue;
            }

            if (mob.getPosition().getDistance(this.position) < (mob.getRadius() + this.radius) && mob.isVisible()) {
                mob.mobHitBy(this);
                this.impactPosition = this.position;
                this.stillAlive = false;
                return;
            }
        }
        this.movements++;

        this.setAlive();
        if (!this.stillAlive) {
            return;
        }

        this.position = this.vector.getNextPosition(this.position, this.speed);
        this.drawRounds.add(this.position);
        for (Mob mob : this.model.allMobs()) {
            if (mob.equals(chainingMob)) {
                continue;
            }
            if (mob.getPosition().getDistance(this.position) < (mob.getRadius() + this.radius) && mob.isVisible()) {
                mob.mobHitBy(this);
                this.stillAlive = false;
                this.impactPosition = this.position;
                return;
            }
        }
        this.movements++;

        this.setAlive();
        if (!this.stillAlive) {
            return;
        }

        this.position = this.vector.getNextPosition(this.position, this.speed);
        this.drawRounds.add(this.position);
        for (Mob mob : this.model.allMobs()) {
            if (mob.equals(chainingMob)) {
                continue;
            }
            if (mob.getPosition().getDistance(this.position) < (mob.getRadius() + this.radius) && mob.isVisible()) {
                mob.mobHitBy(this);
                this.stillAlive = false;
                this.impactPosition = this.position;
                return;
            }
        }
        this.movements++;
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
