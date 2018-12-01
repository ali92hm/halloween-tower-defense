package projectiles;

import mobs.Mob;
import models.DriverModel;
import utilities.Position;
import utilities.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * creates a projectile that passes through multiple main.controllers.mobs
 * and adds chain lightning to any affected mob
 *
 * @author Scorpion
 */

public class ChainingDenseLightning extends Projectile {

    public final static int PROJECTILE_WIDTH = 30;
    public final static int PROJECTILE_HEIGHT = 15;
    public final static String FIRE_IMAGE = null;
    public final static int FIRE_WIDTH = 0;
    public final static int FIRE_HEIGHT = 0;
    public final static String IMPACT_IMAGE = null;
    public final static int IMPACT_WIDTH = 0;
    public final static int IMPACT_HEIGHT = 0;
    public final static int PROJECTILE_SPEED = 40;
    public final static int PROJECTILE_RADIUS = 3;
    public final static int PROJECTILE_DAMAGE = 200;
    public final static int DAMAGE_DURATION = 1;
    public final static int SLOW_POTENTCY = 0;
    public final static int SLOW_DURATION = 0;
    public final static int PROJECTILE_HITS = 2;
    public final static int PROJECTILE_MOVEMENTS = 3;
    public static final int CHAINING_DISTANCE = 100;
    private static String PROJECTILE_IMAGE = "DenseLightning.png";
    private Projectile projectile;
    private Projectile[] chainLightning;
    private List<Position> chain1DrawRounds;
    private int rangeBoostLevel;
    private int damageBoostLevel;
    private int currentChain;

    /**
     * constructor for chaining dense lightning
     *
     * @param model
     * @param startingPosition
     * @param vector
     * @param targetMob
     * @param rangeBoostLevel
     * @param targetCountBoostLevel
     */

    public ChainingDenseLightning(final DriverModel model, final Position startingPosition, final Vector vector, final Mob targetMob,
                                  final int rangeBoostLevel, final int targetCountBoostLevel) {
        super(model, startingPosition, PROJECTILE_IMAGE, vector, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
        this.projectile = new DenseLightning(model, startingPosition, vector, rangeBoostLevel,
                targetCountBoostLevel, this.damageBoostLevel);
        this.chainLightning = new Projectile[5];
        this.rangeBoostLevel = rangeBoostLevel;
        this.speed = PROJECTILE_SPEED;
        this.radius = PROJECTILE_RADIUS;
        this.damage = PROJECTILE_DAMAGE;
        this.damageDuration = DAMAGE_DURATION;
        this.slowPotency = SLOW_POTENTCY;
        this.slowDuration = SLOW_DURATION;
        this.hitTargets = 0;
        this.movements = 0;
        this.currentChain = 0;
        this.stillAlive = true;
        this.drawRounds = new ArrayList<Position>();
        this.chain1DrawRounds = new ArrayList<Position>();
        this.drawRounds.add(this.position);
    }

    /**
     * returns how many main.controllers.mobs the projectile can hit
     *
     * @param level
     * @return
     */

    public static int getTargetCountLevelBoost(final int level) {
        return DenseLightning.getTargetCountLevelBoost(level);
    }

    /**
     * returns how much damage the round does
     *
     * @param level
     * @return
     */

    public static int getDamageLevelBoost(final int level) {
        return DenseLightning.getDamageLevelBoost(level);
    }

    /**
     * adds the image(s) of any projectile
     * that needs to be drawn to the map
     *
     * @param imageGraphics
     */

    public void addImages(final Graphics imageGraphics) {
        for (Position position : this.drawRounds) {
            imageGraphics.drawImage(this.projectileImage, (int) position.getXCord(),
                    (int) position.getYCord(), null);
        }

        for (Projectile projectile : this.chainLightning) {
            if (projectile != null && (projectile.stillAlive || !projectile.isSeen())) {
                for (Position position : this.chain1DrawRounds) {
                    projectile.seen();
                    imageGraphics.drawImage(projectile.getProjectileImage(), (int) position.getXCord(),
                            (int) position.getYCord(), null);
                }
            }
        }
    }

    /**
     * checks to see if the projectile is
     * still alive
     */

    public void setAlive() {
        projectile.setAlive();
        this.stillAlive = false;
        if (projectile.stillAlive) {
            this.stillAlive = true;
        }

        for (Projectile projectile : this.chainLightning) {
            if (projectile != null && projectile.stillAlive) {
                this.stillAlive = true;
            }
        }
    }

    /**
     * moves the projectile and checks whether its
     * impacted a mob
     */

    public void updateProjectile() {
        this.setAlive();
        this.drawRounds.clear();
        if (projectile.stillAlive) {
            projectile.updateProjectile();
        }
        this.drawRounds.addAll(projectile.getDrawRounds());

        if (projectile.getHitTargets() != currentChain) {
            for (Mob mob : model.allMobs()) {
                double mobDistance = projectile.getHitMob().getPosition().getDistance(mob.getPosition());
                if (!mob.equals(this.projectile.getHitMob()) && mobDistance < CHAINING_DISTANCE &&
                        currentChain < 5) {
                    this.chainLightning[currentChain] = this.chainDenseLightningVectors(model,
                            projectile.getHitMob(), mob);
                    this.currentChain++;
                    break;
                }
            }
        }

        for (Projectile projectile : this.chainLightning) {
            if (projectile != null) {
                if (projectile.stillAlive) {
                    projectile.updateProjectile();
                }
                this.chain1DrawRounds.addAll(projectile.getDrawRounds());
            }
        }
    }

    /**
     * sets the new vector for the chaining round to go
     *
     * @param model
     * @param targetMob
     * @param chainingMob
     * @return
     */

    public Projectile chainDenseLightningVectors(final DriverModel model, final Mob targetMob, final Mob chainingMob) {
        Vector vector = new Vector(targetMob.getPosition(), chainingMob.getPosition(), ThunderBolt.PROJECTILE_SPEED);

        double xComp = 0;
        double yComp = 0;

        switch (chainingMob.getDirection()) {
            case 'u':
                yComp = (-1) * chainingMob.getSpeed();
                break;
            case 'r':
                xComp = chainingMob.getSpeed();
                break;
            case 'd':
                yComp = chainingMob.getSpeed();
                break;
            case 'l':
                xComp = (-1) * chainingMob.getSpeed();
                break;
        }

        Vector trajectory = vector.findVectorSum(new Vector(xComp, yComp));
        return new ThunderBolt(model, targetMob.getPosition(), trajectory, targetMob,
                this.rangeBoostLevel, this.damageBoostLevel, true);
    }

    /**
     * returns true if the projectile
     * is lightning
     *
     * @return boolean
     */

    public boolean isLightning() {
        return true;
    }
}
