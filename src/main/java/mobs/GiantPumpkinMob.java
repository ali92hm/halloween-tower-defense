package mobs;

import utilities.Position;

/**
 * Boss mob placeholder that is not used
 *
 * @author Scorpion
 */

public class GiantPumpkinMob extends Mob {

    public static final String MOB_IMAGE = "LaughingPumpkinMob.png";
    public static final int MOB_WIDTH = 75;
    public static final int MOB_HEIGHT = 75;
    public static final int HEALTH = 800;
    public static final int LEVEL_HEALTH_BOOST = 120;
    public static final int MOB_DEATH_GOLD = 50;
    public static final int MOB_DEATH_GOLD_BOOST = 10;
    public static final int MOB_SPEED = 1;
    public static final int MOB_RADIUS = 42;

    /**
     * constructs a brand new mob
     *
     * @param level
     * @param difficulty
     * @param position
     */

    public GiantPumpkinMob(final int level, final int difficulty, final Position position) {
        this.mobPath = MOB_IMAGE;
        this.mobWidth = MOB_WIDTH;
        this.mobHeight = MOB_HEIGHT;
        this.speed = MOB_SPEED + (difficulty * 2);
        this.maxHealth = HEALTH + this.getHealthBoost(level, LEVEL_HEALTH_BOOST, difficulty);
        this.health = this.maxHealth;
        this.mobDeathGold = MOB_DEATH_GOLD + (level * MOB_DEATH_GOLD_BOOST);
        this.speed = MOB_SPEED + difficulty;
        this.radius = MOB_RADIUS;
        this.setImage();
        this.position = position;
    }

}
