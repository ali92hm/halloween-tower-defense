package towers.factories;

import models.DriverModel;
import towers.implementations.TeslaTower;
import utilities.Position;
import views.TowerBuyButton;

public class TeslaTowerFactory implements TowerFactory<TeslaTower> {
    @Override
    public TeslaTower create(Position pos, DriverModel driver) {
        return new TeslaTower(pos, driver);
    }

    @Override
    public TowerBuyButton getBuyButton() {
        return new TowerBuyButton("Tesla Tower", "", "TeslaTower.png");
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
