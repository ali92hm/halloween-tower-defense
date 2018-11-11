package towers.factories;

import models.DriverModel;
import towers.implementations.IcicleTower;
import utilities.Position;
import views.TowerBuyButton;

public class IcicleTowerFactory implements TowerFactory<IcicleTower> {
    @Override
    public IcicleTower create(Position pos, DriverModel driver) {
        return new IcicleTower(pos, driver);
    }

    @Override
    public TowerBuyButton getBuyButton() {
        return new TowerBuyButton("Icicle Tower", "", "IcicleTower.png");
    }

    @Override
    public int getPrice() {
        return IcicleTower.TOWER_COST;
    }
}
