package utilities;

/**
 * stores the location of the mob
 *
 * @author Scorpion
 */

public class Position {

    private final double xCord;
    private final double yCord;

    /**
     * constructor for a Position at the origin
     */

    public Position() {
        this.xCord = 0;
        this.yCord = 0;
    }

    /**
     * constructor for a Position where
     * the x and y coordinates are the same
     *
     * @param cord
     */

    public Position(final double cord) {
        this.xCord = cord;
        this.yCord = cord;
    }

    /**
     * constructor for a position with
     * different x and y coordinates
     *
     * @param xCord
     * @param yCord
     */

    public Position(final double xCord, final double yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
    }

    /**
     * returns the x coordinate
     *
     * @return double
     */

    public double getXCord() {
        return this.xCord;
    }

    /**
     * returns the y coordinate
     *
     * @return double
     */

    public double getYCord() {
        return this.yCord;
    }

    /**
     * returns the distance between two positions
     *
     * @param position
     * @return double
     */

    public double getDistance(final Position position) {
        final double xComp = Math.abs(position.getXCord() - this.xCord);
        final double yComp = Math.abs(position.getYCord() - this.yCord);
        return Math.sqrt((xComp * xComp) + (yComp * yComp));
    }
}
