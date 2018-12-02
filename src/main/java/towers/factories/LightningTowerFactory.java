package towers.factories;

import models.DriverModel;
import towers.implementations.LightningTower;
import utilities.Position;
import views.TowerBuyButton;

public class LightningTowerFactory implements TowerFactory<LightningTower> {
    private TowerBuyButton buyButton = new TowerBuyButton(
            "Lightning Tower",
            "Shoots single shots of lightning",
            "LightningTower.png");

    @Override
    public LightningTower create(Position pos, DriverModel driver) {
        return new LightningTower(pos, driver);
    }

    @Override
    public TowerBuyButton getBuyButton() {
        return this.buyButton;
    }

    @Override
    public int getBasePrice() {
        return LightningTower.TOWER_COST;
    }
}
