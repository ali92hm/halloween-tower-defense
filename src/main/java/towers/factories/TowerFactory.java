package towers.factories;

import models.DriverModel;
import towers.Tower;
import utilities.Position;
import views.TowerBuyButton;

public interface TowerFactory<T extends Tower> {

    public T create(Position pos, DriverModel driver);

    public TowerBuyButton getBuyButton();

    public int getPrice();

}
