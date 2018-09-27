package tests;

import mobs.*;
import org.junit.jupiter.api.Test;
import utilities.Position;

import static org.junit.jupiter.api.Assertions.*;

public class MobTest {
    private Mob sut_BasicMob = new BasicMob(1, 1, new Position(0, 0));
    private Mob sut_EvilPumpkinMob = new EvilPumpkinMob(3, 3, new Position(0, 0));

    @Test
    public void givenBasicMob_WhenLevel1EasyDifficulty_ThenHealthBoost45() {
        assertEquals(sut_BasicMob.getHealthBoost(1, 30, 1), 45);
    }

    @Test
    public void givenBasicMob_WhenLevel1EasyDifficulty_ThenHealth200() {
        assertEquals(sut_BasicMob.getHealth(), 245);
    }

    @Test
    public void givenEvilPumpkinMob_WhenLevel3HardDifficulty_ThenHealthBoost() {
        assertEquals(sut_EvilPumpkinMob.getHealthBoost(), 40);
    }
}
