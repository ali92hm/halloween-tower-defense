package towers.factories;

import models.DriverModel;
import towers.implementations.PatchOfFireTower;
import utilities.Position;
import views.TowerBuyButton;

public class PatchOfFireTowerFactory implements TowerFactory<PatchOfFireTower> {
    @Override
    public PatchOfFireTower create(Position pos, DriverModel driver) {
        return new PatchOfFireTower(pos, driver);
    }

    @Override
    public TowerBuyButton getBuyButton() {
        return new TowerBuyButton("Fire Patch Tower", "", "PatchOfFireTower.png");
    }

    @Override
    public int getPrice() {
        return PatchOfFireTower.TOWER_COST;
    }
}
