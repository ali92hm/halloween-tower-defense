package utilities;

/**
 * this class stores values
 * for how much damage and how many ticks
 * this damage should be applied for
 * <p>
 * Each mob can have multiple of these applied to it
 *
 * @author Scorpion
 */

public class DamageTracker {

    private double damage;
    private double duration;

    /**
     * constructor for damage that
     * is applied only once
     *
     * @param damage
     */

    public DamageTracker(final double damage) {
        this.damage = damage;
        this.duration = 1;
    }

    /**
     * constructor for DOT damage
     *
     * @param damage
     * @param duration
     */

    public DamageTracker(final double damage, final double duration) {
        this.damage = damage;
        this.duration = duration;
    }

    /**
     * reduces the duration by one
     * and returns the damage for that tick
     *
     * @return double
     */

    public double damageTick() {
        this.duration--;
        return this.damage;
    }

    /**
     * returns how much damage the
     * attack does each tick
     *
     * @return
     */

    public double getDamage() {
        return damage;
    }

    /**
     * returns how many tick are left for
     * a particular attack
     *
     * @return double
     */

    public double getDuration() {
        return duration;
    }

}
