package mobs;

import utilities.Position;

/**
 * the speedy mob of the game which
 * has low health and fast speed
 *
 * @author Scorpion
 */

public class SpeedyMob extends Mob {
    public static final String MOB_IMAGE_RIGHT = "BatR.png";
    public static final String MOB_IMAGE_LEFT = "Bat.png";
    public static final int MOB_WIDTH = 50;
    public static final int MOB_HEIGHT = 50;
    public static final int HEALTH = 100;
    public static final int LEVEL_HEALTH_BOOST = 15;
    public static final int MOB_DEATH_GOLD = 10;
    public static final int MOB_DEATH_GOLD_BOOST = 2;
    public static final int MOB_SPEED = 4;
    public static final int MOB_RADIUS = 30;

    /**
     * constructs a brand new mob
     *
     * @param level
     * @param difficulty
     * @param position
     */

    public SpeedyMob(final int level, final int difficulty, final Position position) {
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
}
