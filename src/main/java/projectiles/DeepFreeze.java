package projectiles;

import mobs.Mob;
import models.DriverModel;
import utilities.Position;
import utilities.Vector;
import views.DriverView;

import java.awt.*;
import java.util.ArrayList;

/**
 * creates a projectile that hits all surrounding main.controllers.mobs
 * stopping them in place
 *
 * @author Scorpion
 */

public class DeepFreeze extends Projectile {

    public final static int PROJECTILE_WIDTH = 50;
    public final static int PROJECTILE_HEIGHT = 50;
    public final static String FIRE_IMAGE = null;
    public final static int FIRE_WIDTH = 0;
    public final static int FIRE_HEIGHT = 0;
    public final static String IMPACT_IMAGE = null;
    public final static int IMPACT_WIDTH = 0;
    public final static int IMPACT_HEIGHT = 0;
    public final static int PROJECTILE_SPEED = 25;
    public final static int PROJECTILE_RADIUS = 75;
    public final static int PROJECTILE_DAMAGE = 100;
    public final static int DAMAGE_DURATION = 1;
    public final static int SLOW_POTENTCY = 1;
    public final static int SLOW_DURATION = 5;
    public final static int PROJECTILE_HITS = -1;
    public final static int PROJECTILE_MOVEMENTS = 3;
    private static String PROJECTILE_IMAGE = "Ring of Ice.png";
    public Position startingPosition;
    public int width = PROJECTILE_WIDTH;
    public int height = PROJECTILE_HEIGHT;

    /**
     * constructor for DeepFreeze
     *
     * @param model
     * @param startingPosition
     * @param vector
     * @param rangeBoostLevel
     * @param durationBoostLevel
     */

    public DeepFreeze(final DriverModel model, final Position startingPosition,
                      final Vector vector, final int rangeBoostLevel, final int durationBoostLevel) {
        super(model, startingPosition, PROJECTILE_IMAGE, vector, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
        this.speed = PROJECTILE_SPEED;
        this.radius = PROJECTILE_RADIUS + (25 * rangeBoostLevel);
        this.damage = 0;
        this.damageDuration = DAMAGE_DURATION;
        this.slowPotency = SLOW_POTENTCY;
        this.slowDuration = SLOW_DURATION + (5 * durationBoostLevel);
        this.startingPosition = startingPosition;
        this.movements = 0;
        this.maxMovements = PROJECTILE_MOVEMENTS + rangeBoostLevel;
        this.stillAlive = true;
        this.drawRounds = new ArrayList<Position>();
        this.drawRounds.add(this.position);
    }

    /**
     * returns how long the projectile lasts for
     *
     * @param level
     * @return
     */

    public static int getDurationLevelBoost(final int level) {
        return SLOW_DURATION + (5 * level);
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
        this.projectileImage = DriverView.getImage(PROJECTILE_IMAGE, width, height);
        this.position = new Position(this.startingPosition.getXCord() - (width / 2), this.startingPosition.getYCord() - (height / 2));
        this.drawRounds.clear();
        this.drawRounds.add(this.position);
        this.movements++;
        if (this.movements > 0) {
            this.firePosition = null;
            this.impactPosition = null;
            this.endPosition = null;
        }

        if (this.movements == 2) {
            for (Mob mob : this.model.allMobs()) {
                if (mob.getPosition().getDistance(this.startingPosition) < (mob.getRadius() + this.radius) && mob.isVisible()) {
                    mob.mobHitBy(this);
                }
            }
        }
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
