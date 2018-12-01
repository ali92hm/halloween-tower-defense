package towers.factories;

import models.DriverModel;
import towers.implementations.FireTower;
import utilities.Position;
import views.TowerBuyButton;

public class FireTowerFactory implements TowerFactory<FireTower> {
    private TowerBuyButton buyButton = new TowerBuyButton(
            "Fire Tower",
            "Emits a wave of fire",
            "FireTower.png");

    @Override
    public FireTower create(Position pos, DriverModel driver) {
        return new FireTower(pos, driver);
    }

    @Override
    public TowerBuyButton getBuyButton() {
        return this.buyButton;
    }

    @Override
    public int getBasePrice() {
        return FireTower.TOWER_COST;
    }
}
