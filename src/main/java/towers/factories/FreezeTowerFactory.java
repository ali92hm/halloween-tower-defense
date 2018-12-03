package towers.factories;

import models.DriverModel;
import towers.implementations.FreezeTower;
import utilities.Position;
import views.TowerBuyButton;

public class FreezeTowerFactory implements TowerFactory<FreezeTower> {
    private TowerBuyButton buyButton = new TowerBuyButton(
            "Freeze Tower",
            "Freezes enemies for a short duration",
            "FreezeTower.png");

    @Override
    public FreezeTower create(Position pos, DriverModel driver) {
        return new FreezeTower(pos, driver);
    }

    @Override
    public TowerBuyButton getBuyButton() {
        return this.buyButton;
    }

    @Override
    public int getBasePrice() {
        return FreezeTower.TOWER_COST;
    }
}
