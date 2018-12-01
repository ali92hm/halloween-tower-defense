package mobs;

import utilities.Position;

/**
 * a mob that is immune to all
 * lightning damage and lightning
 * effects. Has low health and
 * medium speed
 *
 * @author Scorpion
 */

public class LightningImmuneMob extends Mob {

    public static final String MOB_IMAGE_RIGHT = "LightningGolemMobR.png";
    public static final String MOB_IMAGE_LEFT = "LightningGolemMob.png";
    public static final int MOB_WIDTH = 50;
    public static final int MOB_HEIGHT = 50;
    public static final int HEALTH = 100;
    public static final int LEVEL_HEALTH_BOOST = 10;
    public static final int MOB_DEATH_GOLD = 10;
    public static final int MOB_DEATH_GOLD_BOOST = 2;
    public static final int MOB_SPEED = 2;
    public static final int MOB_RADIUS = 30;

    /**
     * constructs a brand new mob
     *
     * @param level
     * @param difficulty
     * @param position
     */

    public LightningImmuneMob(final int level, final int difficulty, final Position position) {
        this.mobPath = MOB_IMAGE_RIGHT;
        this.mobWidth = MOB_WIDTH;
        this.mobHeight = MOB_HEIGHT;
        this.maxHealth = HEALTH + this.getHealthBoost(level, LEVEL_HEALTH_BOOST, difficulty);
        this.health = this.maxHealth;
        this.mobDeathGold = MOB_DEATH_GOLD + (level * MOB_DEATH_GOLD_BOOST);
        this.speed = MOB_SPEED + difficulty;
        this.radius = MOB_RADIUS;
        this.setImage();
        this.position = position;
    }

    /**
     * turns the image of the mob to the right
     */

    public void movingRight() {
        this.mobPath = MOB_IMAGE_RIGHT;
        this.setImage();
    }

    /**
     * turns the image of the mob to the left
     */

    public void movingLeft() {
        this.mobPath = MOB_IMAGE_LEFT;
        this.setImage();
    }

    /**
     * makes this mob immune to lightning attacks
     */

    public boolean immuneToLightning() {
        return true;
    }
}
