package test.views;

import main.towers.Tower;
import main.views.TowerBuyButton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TowerBuyButtonTest {
    private TowerBuyButton SUT;
    @Test
    public void givenTowerBuyButton_whenConstructorCalledWithNullTowerType_ThrowsInvalidArgumentException(){

        assertThrows(IllegalArgumentException.class, () -> {
            SUT = new TowerBuyButton(null, "","");
        });
    }

    @Test
    public void givenTowerBuyButton_whenConstructorCalledWithNullDescription_ThrowsInvalidArgumentException(){

        assertThrows(IllegalArgumentException.class, () -> {
            SUT = new TowerBuyButton(Tower.class, null,"");
        });
    }

    @Test
    public void givenTowerBuyButton_whenConstructorCalledWithNullImagePath_ThrowsInvalidArgumentException(){

        assertThrows(IllegalArgumentException.class, () -> {
            SUT = new TowerBuyButton(Tower.class, "",null);
        });
    }

    @Test void givenTowerBuyButton_whenConstructorCalledWithValidImage_thenImageIconNotNull(){
        SUT = new TowerBuyButton(Tower.class, "", "Lightning.png");
        assertNotNull(SUT.getIcon());
    }

}