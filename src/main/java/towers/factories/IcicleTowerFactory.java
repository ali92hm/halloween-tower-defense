package towers.factories;

import models.DriverModel;
import towers.implementations.IcicleTower;
import utilities.Position;
import views.TowerBuyButton;

public class IcicleTowerFactory implements TowerFactory<IcicleTower> {
    private TowerBuyButton buyButton = new TowerBuyButton(
            "Icicle Tower",
            "Shoots jagged icicles",
            "IcicleTower.png");

    @Override
    public IcicleTower create(Position pos, DriverModel driver) {
        return new IcicleTower(pos, driver);
    }

    @Override
    public TowerBuyButton getBuyButton() {
        return this.buyButton;
    }

    @Override
    public int getBasePrice() {
        return IcicleTower.TOWER_COST;
    }
}
