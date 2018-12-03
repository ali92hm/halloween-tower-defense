package mobs;

import utilities.Position;

/**
 * the standard mob of the game which
 * has an average amount of health and
 * an average speed
 *
 * @author Scorpion
 */

public class BasicMob extends Mob {

    public static final String MOB_IMAGE = "EvilPumpkinMob.png";
    public static final int MOB_WIDTH = 50;
    public static final int MOB_HEIGHT = 50;
    public static final int HEALTH = 200;
    public static final int LEVEL_HEALTH_BOOST = 30;
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

    public BasicMob(final int level, final int difficulty, final Position position) {
        this.mobPath = MOB_IMAGE;
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

}
