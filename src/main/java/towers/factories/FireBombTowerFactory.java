package towers.factories;

import models.DriverModel;
import towers.implementations.FireBombTower;
import utilities.Position;
import views.TowerBuyButton;

public class FireBombTowerFactory implements TowerFactory<FireBombTower> {
    @Override
    public FireBombTower create(Position pos, DriverModel driver) {
        return new FireBombTower(pos, driver);
    }

    @Override
    public TowerBuyButton getBuyButton() {
        return new TowerBuyButton("Fire Bomb Tower", "", "FireBombTower.png");
    }

    @Override
    public int getPrice() {
        return FireBombTower.TOWER_COST;
    }
}
