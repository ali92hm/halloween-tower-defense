package utilities;

/**
 * stores where a mob is located at
 * and which direction it is heading
 *
 * @author Scorpion
 */

public class MobMotionDirector {

    private Position point;
    private int direction;

    /**
     * constructor for the MobMotionDirector
     *
     * @param point
     * @param direction
     */

    public MobMotionDirector(final Position point, final int direction) {
        this.point = point;
        this.direction = direction;
    }

    /**
     * returns the location of the mob
     *
     * @return Position
     */

    public Position getPoint() {
        return this.point;
    }

    /**
     * gets the direction of the mob
     *
     * @return
     */

    public int getDirection() {
        return this.direction;
    }
}
