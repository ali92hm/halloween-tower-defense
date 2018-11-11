package towers.factories;

import models.DriverModel;
import towers.implementations.FireTower;
import utilities.Position;
import views.TowerBuyButton;

public class FireTowerFactory implements TowerFactory<FireTower> {
    @Override
    public FireTower create(Position pos, DriverModel driver) {
        return new FireTower(pos, driver);
    }

    @Override
    public TowerBuyButton getBuyButton() {
        return new TowerBuyButton("Fire Tower", "", "FireTower.png");
    }

    @Override
    public int getPrice() {
        return FireTower.TOWER_COST;
    }
}
