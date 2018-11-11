package towers.factories;

import models.DriverModel;
import towers.implementations.FreezeTower;
import utilities.Position;
import views.TowerBuyButton;

public class FreezeTowerFactory implements TowerFactory<FreezeTower> {
    @Override
    public FreezeTower create(Position pos, DriverModel driver) {
        return new FreezeTower(pos, driver);
    }

    @Override
    public TowerBuyButton getBuyButton() {
        return new TowerBuyButton("Freeze Tower", "", "FreezeTower.png");
    }

    @Override
    public int getPrice() {
        return FreezeTower.TOWER_COST;
    }
}
