package towers.factories;

import models.DriverModel;
import towers.implementations.PatchOfFireTower;
import utilities.Position;
import views.TowerBuyButton;

public class PatchOfFireTowerFactory implements TowerFactory<PatchOfFireTower> {
    private TowerBuyButton buyButton = new TowerBuyButton(
            "Fire-Patch Tower",
            "Shoots a fireball that leaves a fire patch",
            "PatchOfFireTower.png");

    @Override
    public PatchOfFireTower create(Position pos, DriverModel driver) {
        return new PatchOfFireTower(pos, driver);
    }

    @Override
    public TowerBuyButton getBuyButton() {
        return this.buyButton;
    }

    @Override
    public int getBasePrice() {
        return PatchOfFireTower.TOWER_COST;
    }
}
