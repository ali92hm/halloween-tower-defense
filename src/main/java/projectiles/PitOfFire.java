package projectiles;

import mobs.Mob;
import models.DriverModel;
import towers.Tower;
import utilities.Position;
import utilities.Vector;

import java.awt.*;
import java.util.ArrayList;

/**
 * creates a projectile that hits a mob creating
 * a patch of fire where the mob was that effects
 * any mob passing over it
 *
 * @author Scorpion
 */

public class PitOfFire extends Projectile {

    public final static int PROJECTILE_WIDTH = 70;
    public final static int PROJECTILE_HEIGHT = 70;
    public final static String FIRE_IMAGE = null;
    public final static int FIRE_WIDTH = 0;
    public final static int FIRE_HEIGHT = 0;
    public final static String IMPACT_IMAGE = null;
    public final static int IMPACT_WIDTH = 0;
    public final static int IMPACT_HEIGHT = 0;
    public final static int PROJECTILE_SPEED = 0;
    public final static int PROJECTILE_RADIUS = 35;
    public final static int PROJECTILE_DAMAGE = 40;
    public final static int PROJECTILE_DOT_DAMAGE = 2;
    public final static int DAMAGE_DURATION = 1;
    public final static int DOT_DAMAGE_DURATION = 60;
    public final static int SLOW_POTENTCY = 0;
    public final static int SLOW_DURATION = 0;
    public final static int PROJECTILE_HITS = -1;
    public final static int PROJECTILE_MOVEMENTS = 40;
    private static String PROJECTILE_IMAGE = "FirePit.png";
    public Position startingPosition;
    public int width = PROJECTILE_WIDTH;
    public int height = PROJECTILE_HEIGHT;

    /**
     * constructor for PitOfFire
     *
     * @param model
     * @param startingPosition
     * @param vector
     * @param durationBoostLevel
     * @param damageBoostLevel
     */

    public PitOfFire(final DriverModel model, final Position startingPosition,
                     final Vector vector, final int durationBoostLevel, final int damageBoostLevel) {
        super(model, startingPosition, PROJECTILE_IMAGE, vector, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
        this.speed = PROJECTILE_SPEED;
        this.radius = PROJECTILE_RADIUS;
        this.damage = Tower.getFireDOTDamageUpgrade() ? PROJECTILE_DOT_DAMAGE + (((PROJECTILE_DOT_DAMAGE * 4) / 3) *
                damageBoostLevel * damageBoostLevel) : PROJECTILE_DAMAGE + (PROJECTILE_DAMAGE *
                damageBoostLevel * damageBoostLevel);
        this.damageDuration = Tower.getFireDOTDamageUpgrade() ? DOT_DAMAGE_DURATION : DAMAGE_DURATION;
        this.slowPotency = SLOW_POTENTCY;
        this.slowDuration = SLOW_DURATION;
        this.startingPosition = startingPosition;
        this.movements = 0;
        this.maxMovements = PROJECTILE_MOVEMENTS + (durationBoostLevel * 14);
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
        return (int) ((Tower.getFireDOTDamageUpgrade() ? PROJECTILE_DOT_DAMAGE + (((PROJECTILE_DOT_DAMAGE * 4) / 3) *
                level * level) : PROJECTILE_DAMAGE + (PROJECTILE_DAMAGE *
                level * level)) * damageBoost);
    }

    /**
     * returns how much long the projectile effects the mob
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
            increase = 10;
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
        this.movements++;

        for (Mob mob : this.model.allMobs()) {
            if (mob.getPosition().getDistance(this.startingPosition) < (mob.getRadius() + this.radius) && mob.isVisible()) {
                mob.mobHitBy(this);
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
