package towers.factories;

import models.DriverModel;
import towers.Tower;
import utilities.Position;
import views.TowerBuyButton;

public interface TowerFactory<T extends Tower> {
    T create(Position pos, DriverModel driver);

    TowerBuyButton getBuyButton();

    int getBasePrice();

    default int getPrice() {
        return (int) (getBasePrice() * T.getDiscountMultiplier());
    }

}
