package towers.factories;

import models.DriverModel;
import towers.implementations.IceTower;
import utilities.Position;
import views.TowerBuyButton;

public class IceTowerFactory implements TowerFactory<IceTower> {

    private TowerBuyButton buyButton = new TowerBuyButton("Ice Tower", "", "IceTower.png");

    @Override
    public IceTower create(Position pos, DriverModel driver) {
        return new IceTower(pos, driver);
    }

    @Override
    public TowerBuyButton getBuyButton() {
        return this.buyButton;
    }

    @Override
    public int getPrice() {
        return IceTower.TOWER_COST;
    }
}
