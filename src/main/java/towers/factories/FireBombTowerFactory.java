package towers.factories;

import models.DriverModel;
import towers.implementations.FireBombTower;
import utilities.Position;
import views.TowerBuyButton;

public class FireBombTowerFactory implements TowerFactory<FireBombTower> {
    private TowerBuyButton buyButton = new TowerBuyButton(
            "Fire-Bomb Tower",
            "Shoots flaming bombs",
            "FireBombTower.png");

    @Override
    public FireBombTower create(Position pos, DriverModel driver) {
        return new FireBombTower(pos, driver);
    }

    @Override
    public TowerBuyButton getBuyButton() {
        return this.buyButton;
    }

    @Override
    public int getBasePrice() {
        return FireBombTower.TOWER_COST;
    }
}
