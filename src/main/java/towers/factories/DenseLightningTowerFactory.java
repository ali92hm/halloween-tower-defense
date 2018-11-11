package towers.factories;

import models.DriverModel;
import towers.implementations.DenseLightningTower;
import utilities.Position;
import views.TowerBuyButton;

public class DenseLightningTowerFactory implements TowerFactory<DenseLightningTower> {
    private TowerBuyButton buyButton = new TowerBuyButton("Bolt Tower", "", "DenseLightningTower.png");

    @Override
    public DenseLightningTower create(Position pos, DriverModel driver) {
        return new DenseLightningTower(pos, driver);
    }

    @Override
    public TowerBuyButton getBuyButton() {
        return buyButton;
    }

    @Override
    public int getPrice() {
        return DenseLightningTower.TOWER_COST;
    }
}
