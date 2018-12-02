package towers.implementations;

import models.DriverModel;
import projectiles.Projectile;
import towers.Tower;
import utilities.Position;

public class MoneyTower extends Tower {
    public static final String TOWER_TURRET_IMAGE = null;
    public static final int TOWER_RANGE = 0;
    public static final int TOWER_FIRE_RATE = 0;
    public static final int TOWER_COST = 0;
    private static final String TOWER_BASE_IMAGE = null;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param location
     * @param model
     */
    public MoneyTower(final Position position) {
        super(position, TOWER_BASE_IMAGE, TOWER_TURRET_IMAGE);
        this.range = TOWER_RANGE;
        this.fireRate = TOWER_FIRE_RATE;
        this.cost = TOWER_COST;
    }

    public Projectile[] attackMob(DriverModel model) {
        return new Projectile[0];
    }

    public void upgradePath1(final DriverModel model) {
    }

    public void upgradePath2(final DriverModel model) {
    }

    @Override
    public int path1CurrentValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int path2CurrentValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int path3CurrentValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int path1UpgradeValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int path2UpgradeValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int path3UpgradeValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void upgradePath3(DriverModel model) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getRange() {
        // TODO Auto-generated method stub
        return 0;
    }
}
