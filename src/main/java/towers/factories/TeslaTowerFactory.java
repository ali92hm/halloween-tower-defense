package towers.factories;

import models.DriverModel;
import towers.implementations.TeslaTower;
import utilities.Position;
import views.TowerBuyButton;

public class TeslaTowerFactory implements TowerFactory<TeslaTower> {
    private TowerBuyButton buyButton = new TowerBuyButton(
            "Tesla Tower",
            "Shoots electric charges",
            "TeslaTower.png");
    ;

    @Override
    public TeslaTower create(Position pos, DriverModel driver) {
        return new TeslaTower(pos, driver);
    }

    @Override
    public TowerBuyButton getBuyButton() {
        return this.buyButton;
    }

    @Override
    public int getBasePrice() {
        return TeslaTower.TOWER_COST;
    }
}
