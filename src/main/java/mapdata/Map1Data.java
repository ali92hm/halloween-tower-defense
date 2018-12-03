package mapdata;

import mobs.*;
import models.DriverModel;
import utilities.Position;

import java.util.ArrayList;
import java.util.Random;

/**
 * stores the data for a map including
 * the mob motions on the map, the main.controllers.mobs
 * that will spawn
 *
 * @author Scorpion
 */

public class Map1Data extends MapData {

    private final double X_START = 460;
    private final double Y_START = -50;

    private final DriverModel model;
    private final int difficulty;

    /**
     * sets the model and the difficulty fields
     *
     * @param model
     * @param difficulty
     */

    public Map1Data(final DriverModel model, final int difficulty) {
        this.model = model;
        this.difficulty = difficulty;
    }

    /**
     * compiles a list of main.controllers.mobs to
     * run based on the current level
     * and returns it
     *
     * @param level
     * @return main.controllers.mobs
     */

    public ArrayList<Mob> getMobs(final int level) {
        Random random = new Random();
        final ArrayList<Mob> mobs = new ArrayList<>();

        int mobLevel = getMobLevel(level);
        int mobCount = 6 + (level / 2);

        while (mobs.size() < mobCount) {
            int randomMobLevel = random.nextInt(mobLevel);
            Mob mob;
            Position startPos = new Position(X_START, Y_START);

            if (randomMobLevel < 4)
                mob = new BasicMob(level, difficulty, startPos);
            else if (randomMobLevel < 6)
                mob = new ProfessorMob(level, difficulty, startPos);
            else if (randomMobLevel < 7)
                mob = new TankMob(level, difficulty, startPos);
            else if (randomMobLevel < 10)
                mob = new SpeedyMob(level, difficulty, startPos);
            else if (randomMobLevel < 12)
                mob = new FireImmuneMob(level, difficulty, startPos);
            else if (randomMobLevel < 14)
                mob = new LightningImmuneMob(level, difficulty, startPos);
            else if (randomMobLevel < 16)
                mob = new FrostImmuneMob(level, difficulty, startPos);
            else
                mob = new GiantPumpkinMob(level, difficulty, startPos);

            mobs.add(mob);
        }
        return mobs;
    }

    public int getMobLevel(final int level) {
        int mobLevel = 0;

        if (level < 5)
            mobLevel = 10;
        else if (level < 10)
            mobLevel = 12;
        else if (level < 15)
            mobLevel = 16;
        else
            mobLevel = 17;
        return mobLevel;
    }

    /**
     * updates the position of the main.controllers.mobs
     * and changes their direction if needed
     *
     * @return position
     */

    public Position travelDistance(final Mob mob) {
        double xCord = mob.getPosition().getXCord();
        double yCord = mob.getPosition().getYCord();

        for (int i = 0; i < mob.getSpeed(); i++) {
            if (mob.getDistanceTraveled() < 173) {
                yCord++;
                mob.setDirection('d');
            } else if (mob.getDistanceTraveled() < 337) {
                xCord++;
                mob.setDirection('r');
                mob.movingRight();
            } else if (mob.getDistanceTraveled() < 738) {
                yCord++;
                mob.setDirection('d');
            } else if (mob.getDistanceTraveled() < 1283) {
                xCord--;
                mob.setDirection('l');
                mob.movingLeft();
            } else if (mob.getDistanceTraveled() < 1427) {
                yCord--;
                mob.setDirection('u');
            } else if (mob.getDistanceTraveled() < 1830) {
                xCord++;
                mob.setDirection('r');
                mob.movingRight();
            } else if (mob.getDistanceTraveled() < 1960) {
                yCord--;
                mob.setDirection('u');
            } else if (mob.getDistanceTraveled() < 2365) {
                xCord--;
                mob.setDirection('l');
                mob.movingLeft();
            } else if (mob.getDistanceTraveled() < 2515) {
                yCord--;
                mob.setDirection('u');
            } else if (mob.getDistanceTraveled() < 2715) {
                xCord++;
                mob.setDirection('r');
                mob.movingRight();
            } else if (mob.getDistanceTraveled() < 2865) {
                yCord--;
                mob.setDirection('u');
            } else {
                if (mob.isVisible()) {
                    model.lifeLost();
                }
                mob.vanishMob();
            }
            mob.traveledDistance();
        }

        return new Position(xCord, yCord);
    }
}