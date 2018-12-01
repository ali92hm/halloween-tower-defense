package projectiles;

import mobs.Mob;
import models.DriverModel;
import utilities.Position;
import utilities.Vector;
import views.DriverView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.UUID;

/**
 * Abstract class for creating and
 * firing main.controllers.projectiles
 *
 * @author Scorpion
 */

public abstract class Projectile {

    protected static double damageBoost = 1.0;
    protected static boolean rangeBoost = false;
    protected final String projectileName;
    protected final UUID id;
    protected int damageBoostLevel;
    protected int rangeBoostLevel;
    protected int durationBoostLevel;
    protected DriverModel model;
    protected BufferedImage projectileImage;
    protected BufferedImage fireImage;
    protected BufferedImage impactImage;
    protected Position position;
    protected Position firePosition;
    protected Position impactPosition;
    protected Position endPosition;
    protected Vector vector;
    protected double speed;
    protected double radius;
    protected double damage;
    protected double damageDuration;
    protected double slowPotency;
    protected double slowDuration;
    protected boolean stillAlive;
    protected int hitTargets;
    protected int maxHitTargets;
    protected int movements;
    protected int maxMovements;
    protected List<Position> drawRounds;
    protected Mob chainingMob;
    protected Mob hitMob;
    protected boolean seen = false;

    /**
     * constructor for a projectile
     *
     * @param model
     * @param location
     * @param projectileImage
     * @param vector
     * @param width
     * @param height
     */

    public Projectile(final DriverModel model, final Position location, final String projectileImage,
                      final Vector vector, final int width, final int height) {
        this.projectileName = this.getClass().getSimpleName();
        this.id = UUID.randomUUID();
        this.model = model;
        this.position = new Position(location.getXCord() - (width / 2), location.getYCord() - (height / 2));
        this.vector = vector;
        this.projectileImage = DriverView.getImage(projectileImage, width, height);
        if (vector == null) {
            return;
        }
        this.projectileImage = vector.getAngle() < 0 ?
                DriverView.rotateImage(this.projectileImage, vector.getAngle() + Math.PI) :
                DriverView.rotateImage(this.projectileImage, vector.getAngle());
    }

    /**
     * increases the damage all main.controllers.projectiles do by 10%
     */

    public static void upgradeDamageBoost() {
        damageBoost = 1.1;
    }

    /**
     * increases the range of all main.controllers.projectiles by a fixed amount
     */

    public static void upgradeRangeBoost() {
        rangeBoost = true;
    }

    /**
     * adds the image(s) of any projectile
     * that needs to be drawn to the map
     *
     * @param imageGraphics
     */

    public abstract void addImages(final Graphics imageGraphics);

    /**
     * moves the projectile and checks whether its
     * impacted a mob
     */

    public abstract void updateProjectile();

    /**
     * sets the image of the projectile for
     * when it was just fired
     *
     * @param location
     * @param projectileImage
     * @param width
     * @param height
     */

    public void setFireImage(final Position location, final String projectileImage, final int width, final int height) {
        this.firePosition = new Position(location.getXCord() - (width / 2), location.getYCord() - (height / 2));
        this.fireImage = DriverView.getImage(projectileImage, width, height);
        if (vector == null) {
            return;
        }
        this.fireImage = vector.getAngle() < 0 ?
                DriverView.rotateImage(this.fireImage, vector.getAngle() + Math.PI) :
                DriverView.rotateImage(this.fireImage, vector.getAngle());
    }

    /**
     * sets the image of the projectile for
     * when it hits a target
     *
     * @param location
     * @param projectileImage
     * @param height
     * @param width
     */

    public void setImpactImage(final Position location, final String projectileImage, final int height, final int width) {
        this.impactImage = DriverView.getImage(projectileImage, height, width);
        if (vector == null) {
            return;
        }
        this.impactImage = vector.getAngle() < 0 ?
                DriverView.rotateImage(this.impactImage, (vector.getAngle() + Math.PI)) :
                DriverView.rotateImage(this.impactImage, vector.getAngle());
    }

    /**
     * checks to see if the projectile is
     * still alive
     */

    public abstract void setAlive();

    /**
     * is set to true once the projectile
     * has been drawn once to the screen
     */

    public void seen() {
        this.seen = true;
    }

    /**
     * returns true if the projectile
     * is fire
     *
     * @return boolean
     */

    public boolean isFire() {
        return false;
    }

    /**
     * returns true if the projectile
     * is lightning
     *
     * @return boolean
     */

    public boolean isLightning() {
        return false;
    }

    /**
     * returns true if the projectile
     * is ice
     *
     * @return boolean
     */

    public boolean isIce() {
        return false;
    }

    /*
     **************************
     * Accessors and Mutators *
     **************************
     */

    /**
     * returns how many times the projectile
     * has moved
     *
     * @return int
     */

    public int getMovements() {
        return movements;
    }

    /**
     * returns the name of the projectile
     *
     * @return String
     */

    public String getName() {
        return this.projectileName;
    }

    /**
     * returns the id of the projectile
     *
     * @return UUID
     */

    public UUID getID() {
        return this.id;
    }

    /**
     * returns the image of the projectile
     *
     * @return BufferedImage
     */

    public BufferedImage getProjectileImage() {
        return projectileImage;
    }

    /**
     * returns the fired image of the projectile
     *
     * @return BufferedImage
     */

    public BufferedImage getFireImage() {
        return fireImage;
    }

    /**
     * returns the impact image of the projectile
     *
     * @return BufferedImage
     */

    public BufferedImage getImpactImage() {
        return impactImage;
    }

    /**
     * returns the position of the projectile
     *
     * @return Position
     */

    public Position getPosition() {
        return position;
    }

    /**
     * returns the starting position of the projectile
     *
     * @return Position
     */

    public Position getFirePosition() {
        return firePosition;
    }

    /**
     * returns the impact position of the projectile
     *
     * @return Position
     */

    public Position getImpactPosition() {
        return impactPosition;
    }

    /**
     * returns the vector the projectile is following
     *
     * @return Vector
     */

    public Vector getVector() {
        return vector;
    }

    /**
     * returns the speed of the projectile
     *
     * @return double
     */

    public double getSpeed() {
        return speed;
    }

    /**
     * returns the amount of damage the projectile does
     *
     * @return double
     */

    public double getDamage() {
        return this.damage * damageBoost;
    }

    /**
     * returns how long the projectile
     * damages the mob for
     *
     * @return double
     */

    public double getDamageDuration() {
        return damageDuration;
    }

    /**
     * returns how much the projectile
     * slows the mob down by
     *
     * @return double
     */

    public double getSlowPotency() {
        return slowPotency;
    }

    /**
     * returns how long the projectile
     * slows the mob down for
     *
     * @return double
     */

    public double getSlowDuration() {
        return slowDuration;
    }

    /**
     * returns how many targets the projectile
     * has hit
     *
     * @return int
     */

    public int getHitTargets() {
        return this.hitTargets;
    }

    /**
     * returns which mob the round hit
     *
     * @return Mob
     */

    public Mob getHitMob() {
        return this.hitMob;
    }

    /**
     * returns whether or not the round is still active
     *
     * @return boolean
     */

    public boolean stillAlive() {
        return stillAlive;
    }

    /**
     * returns the list of positions where the projectile
     * needs to be drawn
     *
     * @return List<Position>
     */

    public List<Position> getDrawRounds() {
        return drawRounds;
    }

    /**
     * returns whether or not the projectile
     * has been seen
     *
     * @return boolean
     */

    public boolean isSeen() {
        return seen;
    }
}
