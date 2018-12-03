package views;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TowerBuyButtonTest {
    private TowerBuyButton SUT;

    @Test
    public void givenTowerBuyButton_whenConstructorCalledWithBadIconPath_NoExceptionThrown() {
        SUT = new TowerBuyButton("", "", "");
    }

    @Test
    public void givenTowerBuyButton_whenConstructorCalledWithNullTowerName_ThrowsInvalidArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> {
            SUT = new TowerBuyButton(null, "", "");
        });
    }

    @Test
    public void givenTowerBuyButton_whenConstructorCalledWithNullDescription_ThrowsInvalidArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> {
            SUT = new TowerBuyButton("Fire Tower", null, "");
        });
    }

    @Test
    public void givenTowerBuyButton_whenConstructorCalledWithNullImagePath_ThrowsInvalidArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> {
            SUT = new TowerBuyButton("Fire Tower", "", null);
        });
    }

    @Test
    void givenTowerBuyButton_whenConstructedWithFireTower_thenButtonNameIsFireTower() {
        String expected = "Fire Tower";
        SUT = new TowerBuyButton(expected, "", "");
        assertEquals(expected, SUT.getName());
    }
}