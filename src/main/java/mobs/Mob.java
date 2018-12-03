package mobs;

import projectiles.DeepFreeze;
import projectiles.IceBeam;
import projectiles.Icicle;
import projectiles.Projectile;
import towers.Tower;
import utilities.DamageTracker;
import utilities.Position;
import views.DriverView;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.UUID;

/**
 * basic abstract class for all main.controllers.mobs
 *
 * @author Scorpion
 */

public abstract class Mob implements Comparable<Mob> {

    private static double mobValueBoost = 1.0;

    protected String mobPath;
    protected BufferedImage mobImage;
    protected int mobWidth;
    protected int mobHeight;
    protected UUID id = UUID.randomUUID();
    protected Position position;
    protected int maxHealth;
    protected int health;
    protected int mobDeathGold;
    protected ArrayList<DamageTracker> damageTracker = new ArrayList<>();
    protected double speed;
    protected double slowPotency;
    protected double slowDuration;
    protected double permifrost = 0.0;
    protected int radius;
    protected double distanceTraveled;
    protected char currentDirection;

    protected boolean visible = true;
    protected boolean isActive = false;
    protected boolean hitByIceBeam = false;

    /**
     * Increases the gold value of the mob
     */

    public static void upgradeMobValueBoost() {
        mobValueBoost = 1.1;
    }

    /**
     * Mutator for the main.controllers.mobs image
     */

    protected void setImage() {
        mobImage = DriverView.getImage(
                this.mobPath, this.mobWidth, this.mobHeight);
    }

    /**
     * returns true if the mob is immune to Fire
     *
     * @return boolean
     */

    protected boolean immuneToFire() {
        return false;
    }

    /**
     * returns true if the mob is immune to Lightning
     *
     * @return boolean
     */

    protected boolean immuneToLightning() {
        return false;
    }

    /**
     * returns true if the mob is immune to Ice
     *
     * @return boolean
     */

    protected boolean immuneToIce() {
        return false;
    }

    /**
     * Tells the mob to move right
     */

    public void movingRight() {
    }

    /**
     * Tells the mob to move left
     */

    public void movingLeft() {
    }

    /*
     ****************************
     * Accessor/Mutator Methods *
     ****************************
     */

    /**
     * Sets the main.controllers.mobs position
     *
     * @param xCord
     * @param yCord
     */

    public void setPosition(final double xCord, final double yCord) {
        this.setPosition(new Position(xCord, yCord));
    }

    public int getHealthBoost(final int level, final int levelHealthBoost, final int difficulty) {
        return (int) (level * level * levelHealthBoost * ((difficulty * 0.5) + 1));
    }

    /**
     * Returns the mob image
     *
     * @return BufferedImage
     */

    public BufferedImage getImage() {
        return this.mobImage;
    }

    /**
     * Returns the mob's width
     *
     * @return int
     */

    public int getMobWidth() {
        return this.mobWidth;
    }

    /**
     * Returns the mob's width
     *
     * @return int
     */

    public int getMobHeight() {
        return this.mobHeight;
    }

    /**
     * Returns the main.controllers.mobs id
     *
     * @return UUID
     */

    public UUID getID() {
        return this.id;
    }

    /**
     * Returns the mob's position
     *
     * @return Position
     */

    public Position getPosition() {
        return this.position;
    }

    /**
     * Sets the position of the mob
     *
     * @param position
     */

    public void setPosition(final Position position) {
        this.position = position;
    }

    /**
     * Returns the mob's health
     *
     * @return int
     */

    public int getHealth() {
        return this.health;
    }

    /**
     * Returns the mob's speed
     *
     * @return double
     */

    public double getSpeed() {
        if (this.slowPotency != 0) {
            return this.speed - (this.slowPotency * this.speed);
        } else {
            return this.speed - (this.permifrost * this.speed);
        }
    }

    /**
     * Return the potency of "freeze" or slow on the mob
     *
     * @return double
     */

    public double getSlowPotency() {
        return this.slowPotency;
    }

    /**
     * Return the duration of "freeze" or slow on the mob
     *
     * @return double
     */

    public double getSlowDuration() {
        return this.slowDuration;
    }

    /**
     * Returns the mob's radius
     *
     * @return int
     */

    public int getRadius() {
        return this.radius;
    }

    /**
     * Returns the distance travel by the mob
     *
     * @return double
     */

    public double getDistanceTraveled() {
        return this.distanceTraveled;
    }

    /**
     * Accessor for the mob's direction of movement
     *
     * @return char
     */

    public char getDirection() {
        return currentDirection;
    }

    /**
     * Set the direction of the mob to move
     *
     * @param currentDirection
     */

    public void setDirection(final char currentDirection) {
        if (currentDirection == 'u' || currentDirection == 'd' || currentDirection == 'r' || currentDirection == 'l')
            this.currentDirection = currentDirection;
    }

    /**
     * Boolean to show the mob on the map
     *
     * @return boolean
     */

    public boolean isVisible() {
        return this.visible;
    }

    /**
     * To check if mob is alive of not
     *
     * @return boolean
     */

    public boolean isActive() {
        return this.isActive;
    }

    /**
     * Method to check if the mob was hit by an Ice beam
     *
     * @return boolean
     */

    public boolean isHitByIceBeam() {
        return this.hitByIceBeam;
    }

    /**
     * The method for drawing the mob's health bar
     *
     * @return BufferedImage
     */

    public BufferedImage getMobHealthBar() {
        BufferedImage image = new BufferedImage(mobWidth, mobHeight + 10, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(this.getImage(), 0, 0, mobWidth, mobHeight, null);

        double percentHealth = ((double) this.health) / this.maxHealth;

        g.setPaint(Color.red);
        g.fill(new Rectangle2D.Double(0, mobHeight, mobWidth, 3));
        g.setPaint(Color.green);
        g.fill(new Rectangle2D.Double(0, mobHeight, mobWidth * percentHealth, 3));

        return image;
    }

    /**
     * To check if mob is active of not
     *
     * @return boolean
     */

    public void activate() {
        this.isActive = true;
    }

    /**
     * Does not show the mob on the map
     */

    public void vanishMob() {
        this.visible = false;
    }

    /**
     * Increments the distance travel by the mob
     *
     * @return
     */

    public void traveledDistance() {
        this.distanceTraveled++;
    }

    /**
     * This method sets the projectile that the mob was hit by.
     *
     * @param projectile
     */

    public void mobHitBy(final Projectile projectile) {
        if (!this.isActive) {
            return;
        }

        if (projectile.isFire() && this.immuneToFire()) {
            return;
        }

        if (projectile.isLightning() && this.immuneToLightning()) {
            return;
        }

        if (projectile.isIce() && this.immuneToIce()) {
            return;
        }

        if (projectile instanceof IceBeam) {
            this.hitByIceBeam = true;
        }

        if (Tower.getIceDamageUpgrade()) {
            double permifrost = 0;
            if (projectile instanceof IceBeam) {
                permifrost = projectile.getSlowPotency() * 0.25;
            } else if (projectile instanceof DeepFreeze) {
                permifrost = 0.3;
            } else if (projectile instanceof Icicle) {
                permifrost = 0.5;
            }

            if (this.permifrost < permifrost) {
                this.permifrost = permifrost;
            }
        }

        this.damageTracker.add(new DamageTracker(projectile.getDamage(), projectile.getDamageDuration()));
        if (projectile.getSlowPotency() >= this.slowPotency) {
            this.slowPotency = projectile.getSlowPotency();
            this.slowDuration = projectile.getSlowDuration();
        }
    }

    /**
     * updates the amount of damage the mob received over
     * the time step
     *
     * @return int
     */

    public int mobDamageTick() {
        this.hitByIceBeam = false;
        for (DamageTracker damageTracker : this.damageTracker) {
            if (damageTracker.getDuration() <= 0) {
                continue;
            }

            this.health -= damageTracker.damageTick();
            if (this.health <= 0) {
                this.visible = false;
                this.position = new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);
                return (int) (this.mobDeathGold * mobValueBoost);
            }
        }

        if (this.slowDuration <= 0) {
            this.slowPotency = 0;
            this.hitByIceBeam = false;
        }
        this.slowDuration--;
        return 0;
    }

    /**
     * The overridden hashCode method
     *
     * @return int
     */

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * The overridden equals method
     *
     * @return boolean
     */

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Mob other = (Mob) obj;
        if (id == null) {
            if (other.getID() != null)
                return false;
        } else if (!id.equals(other.getID())) {
            return false;
        }
        return true;
    }

    /**
     * The overridden compareTo method
     *
     * @return int
     */

    public int compareTo(Mob mob) {
        if (this.distanceTraveled > mob.distanceTraveled) {
            return -1;
        } else if (this.distanceTraveled == mob.distanceTraveled) {
            return 0;
        }

        return 1;
    }
}
