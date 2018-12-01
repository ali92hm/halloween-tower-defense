package mobs;

import utilities.Position;

/**
 * mob placeholder that is never used
 *
 * @author Scorpion
 */

public class WitchMob extends Mob {
    public static final String MOB_IMAGE = "WitchMob.png";
    public static final int MOB_WIDTH = 50;
    public static final int MOB_HEIGHT = 50;
    public static final int HEALTH = 400;
    public static final int LEVEL_HEALTH_BOOST = 60;
    public static final int MOB_DEATH_GOLD = 10;
    public static final int MOB_DEATH_GOLD_BOOST = 2;
    public static final int MOB_SPEED = 1;
    public static final int MOB_RADIUS = 30;

    /**
     * constructs a brand new mob
     *
     * @param level
     * @param difficulty
     * @param position
     */

    public WitchMob(final int level, final int difficulty, final Position position) {
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

    public boolean immuneToLightning() {
        return true;
    }

    public boolean immuneToFire() {
        return true;
    }

    public boolean immuneToIce() {
        return true;
    }

}