package projectiles;

import mobs.Mob;
import models.DriverModel;
import utilities.Position;
import utilities.Vector;

import java.awt.*;
import java.util.ArrayList;

/**
 * creates a projectile continually hits
 * a mob slowing them down
 *
 * @author Scorpion
 */

public class IceBeam extends Projectile {

    public final static String PROJECTILE_IMAGE = "IceBeam.png";
    public final static int PROJECTILE_WIDTH = 20;
    public final static int PROJECTILE_HEIGHT = 10;
    public final static String FIRE_IMAGE = null;
    public final static int FIRE_WIDTH = 0;
    public final static int FIRE_HEIGHT = 0;
    public final static String IMPACT_IMAGE = null;
    public final static int IMPACT_WIDTH = 0;
    public final static int IMPACT_HEIGHT = 0;
    public final static int PROJECTILE_SPEED = 15;
    public final static int PROJECTILE_RADIUS = 2;
    public final static int PROJECTILE_DAMAGE = 5;
    public final static int DAMAGE_DURATION = 1;
    public final static double SLOW_POTENCY = 0.2;
    public final static int SLOW_DURATION = 2;
    public final static int PROJECTILE_HITS = 1;
    public final static int PROJECTILE_MOVEMENTS = 15;

    private Mob attackingMob;

    /**
     * constructor for IceBeam
     *
     * @param model
     * @param position
     * @param attackingMob
     * @param vector
     * @param slowPotencyLevel
     * @param rangeUpgradeLevel
     */

    public IceBeam(final DriverModel model, final Position position, final Mob attackingMob,
                   final Vector vector, final int slowPotencyLevel, final int rangeUpgradeLevel) {
        super(model, position, PROJECTILE_IMAGE, vector, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
        this.attackingMob = attackingMob;
        this.position = position;
        this.speed = PROJECTILE_SPEED;
        this.radius = PROJECTILE_RADIUS;
        this.damage = 0;
        this.damageDuration = DAMAGE_DURATION;
        this.slowPotency = SLOW_POTENCY + (SLOW_POTENCY * slowPotencyLevel);
        this.slowDuration = SLOW_DURATION;
        this.hitTargets = 0;
        this.movements = 0;
        this.maxMovements = PROJECTILE_MOVEMENTS + (3 * rangeUpgradeLevel);
        this.stillAlive = true;
        this.drawRounds = new ArrayList<Position>();
        this.drawRounds.add(this.position);
    }

    /**
     * returns how much the slowing effect is
     *
     * @param level
     * @return
     */

    public static int getSlowPotencyLevelBoost(final int level) {
        return (int) ((SLOW_POTENCY + (SLOW_POTENCY * level)) * 10);
    }

    /**
     * adds the image(s) of any projectile
     * that needs to be drawn to the map
     *
     * @param imageGraphics
     */

    public void addImages(final Graphics imageGraphics) {
        ArrayList<Position> positions = new ArrayList<Position>(this.drawRounds);
        for (Position position : positions) {
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
        if (this.movements >= (this.maxMovements + increase)) {
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
        if (this.movements > 0) {
            this.firePosition = null;
            this.impactPosition = null;
            this.endPosition = null;
        }
        this.position = this.vector.getNextPosition(this.position, this.speed);
        this.drawRounds.clear();
        this.drawRounds.add(this.position);
        for (Mob mob : this.model.allMobs()) {
            if (mob.getPosition().getDistance(this.position) < (mob.getRadius() + this.radius) &&
                    mob.isVisible() && mob.equals(this.attackingMob)) {
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
     * is ice
     *
     * @return boolean
     */

    public boolean isIce() {
        return true;
    }
}
