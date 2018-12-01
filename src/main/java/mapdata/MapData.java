package mapdata;

import mobs.Mob;
import utilities.Position;

import java.util.ArrayList;

public abstract class MapData {

    private final String mapImageName = "testTrack.png";
    private final int mapImageWidth = 700;
    private final int mapImageHeight = 650;

    /**
     * gets the background image of the map
     *
     * @return mapImageName
     */

    public String getMapImageName() {
        return this.mapImageName;
    }

    /**
     * gets the width of the background image of the map
     *
     * @return mapImageWidth
     */

    public int getMapImageWidth() {
        return this.mapImageWidth;
    }

    /**
     * gets the height of the background image of the map
     *
     * @return mapImageHeight
     */

    public int getMapImageHeight() {
        return this.mapImageHeight;
    }

    /**
     * compiles a list of main.controllers.mobs to
     * run based on the current level
     * and returns it
     *
     * @param level
     * @return main.controllers.mobs
     */

    public abstract ArrayList<Mob> getMobs(final int level);

    /**
     * updates the position of the main.controllers.mobs
     * and changes their direction if needed
     *
     * @return position
     */

    public abstract Position travelDistance(final Mob mob);
}
