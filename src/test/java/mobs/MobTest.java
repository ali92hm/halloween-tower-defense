package mobs;

import utilities.Position;
import mobs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MobTest {
    private Mob sut_BasicMob = new BasicMob(1, 1, new Position(0, 0));
    private Mob sut_EvilPumpkinMob = new EvilPumpkinMob(3, 3, new Position(0, 0));
    private Mob sut_FireImmuneMob = new FireImmuneMob(1, 3, new Position(0, 0));
    private Mob sut_FrostImmuneMob = new FrostImmuneMob(1, 1, new Position(0, 0));
    private Mob sut_GiantPumpkinMob = new GiantPumpkinMob(1, 1, new Position(0, 0));
    private Mob sut_LaughingPumpkinMob = new LaughingPumpkinMob(1, 1, new Position(0, 0));
    private Mob sut_LightningGolemMob = new LightningGolemMob(1, 1, new Position(0, 0));
    private Mob sut_LightningImmuneMob = new LightningImmuneMob(1, 1, new Position(0, 0));
    private Mob sut_PumpkinMob = new PumpkinMob(1, 1, new Position(0, 0));
    private Mob sut_SpeedyMob = new SpeedyMob(1, 1, new Position(0, 0));
    private Mob sut_SpiderMob = new SpiderMob(1, 1, new Position(0, 0));
    private Mob sut_TankMob = new TankMob(1, 1, new Position(0, 0));
    private Mob sut_WitchMob = new WitchMob(1, 1, new Position(0, 0));
    private Mob sut_ZombieMob = new ZombieMob(1, 1, new Position(0, 0));

    @Test
    public void givenBasicMob_WhenLevel1EasyDifficulty_ThenHealthBoost45() {
        assertEquals(sut_BasicMob.getHealthBoost(1, 30, 1), 45);
    }

    @Test
    public void givenBasicMob_WhenLevel1EasyDifficulty_ThenHealth200() {
        assertEquals(sut_BasicMob.getHealth(), 245);
    }

    @Test
    public void givenEvilPumpkinMob_WhenLevel3HardDifficulty_ThenHealthBoost1350() {
        assertEquals(sut_EvilPumpkinMob.getHealthBoost(3, 60,3), 1350);
    }

    @Test
    public void givenMob_WhenSetNewPosition_ThenPositionX5() {
        sut_FireImmuneMob.setPosition(new Position(5, 4));
        assertEquals(sut_FireImmuneMob.getPosition().getXCord(), 5);
    }

    @Test
    public void givenMob_WhenSetNewPosition_ThenPositionY4() {
        sut_FrostImmuneMob.setPosition(5, 4);
        assertEquals(sut_FrostImmuneMob.getPosition().getYCord(), 4);
    }

    @Test
    public void givenFrostImmuneMob_WhenGetWidth_Then50() {
        assertEquals(sut_FrostImmuneMob.getMobWidth(), 50);
    }

    @Test
    public void givenFrostImmuneMob_WhenGetHeight_Then50() {
        assertEquals(sut_FrostImmuneMob.getMobHeight(), 50);
    }

    @Test
    public void givenMob_WhenSetDirectionL_ThenLeft() {
        sut_GiantPumpkinMob.setDirection('l');
        assertEquals(sut_GiantPumpkinMob.getDirection(), 'l');
    }

    @Test
    public void givenMob_WhenDirectionRSetToT_ThenR() {
        sut_GiantPumpkinMob.setDirection('t');
        assertNotEquals(sut_GiantPumpkinMob.getDirection(), 't');
    }

    @Test
    public void givenMob_WhenGetUUID_ThenNotNull() {
        assertNotNull(sut_LaughingPumpkinMob.getID());
    }

    @Test
    public void givenLaughingPumpkinMob_WhenGetSpeedAndNoSlowdownAndEasy_Then5() {
        assertEquals(sut_LaughingPumpkinMob.getSpeed(), 5);
    }

    @Test
    public void givenLaughingPumpkinMob_WhenGetSlowPotency_Then0() {
        assertEquals(sut_LaughingPumpkinMob.getSlowPotency(), 0);
    }

    @Test
    public void givenLightningGolemMob_WhenGetRadius_Then30() {
        assertEquals(sut_LightningGolemMob.getRadius(), 30);
    }

    @Test
    public void givenMob_WhenTraveledDistance_Then1() {
        sut_LightningGolemMob.traveledDistance();
        assertEquals(sut_LightningGolemMob.getDistanceTraveled(), 1);
    }

    @Test
    public void givenMob_WhenVisible_ThenTrue() {
        assertTrue(sut_LightningGolemMob.isVisible());
    }

    @Test
    public void givenMob_WhenVanished_ThenNotVisible() {
        sut_LightningImmuneMob.vanishMob();
        assertFalse(sut_LightningImmuneMob.isVisible());
    }

    @Test
    public void givenMob_WhenActivated_ThenIsActive() {
        sut_PumpkinMob.activate();
        assertTrue(sut_PumpkinMob.isActive());
    }

    @Test
    public void givenMob_WhenNotActivated_ThenNotActive() {
        assertFalse(sut_LightningImmuneMob.isActive());
    }

    @Test
    public void givenMob_WhenNotHitByIceBeam_ThenFalse() {
        assertFalse(sut_SpeedyMob.isHitByIceBeam());
    }

    @Test
    public void givenMob_WhenGetHashCode_ThenIDHashPlus31() {
        assertEquals(sut_SpiderMob.hashCode(), sut_SpiderMob.getID().hashCode() + 31);
    }

    @Test
    public void givenTankMob_WhenWitchMob_ThenNotEquals() {
        assertFalse(sut_TankMob.equals(sut_WitchMob));
    }

    @Test
    public void givenZombieMob_WhenBasicMobTraveledLess_ThenNegative1() {
        sut_ZombieMob.traveledDistance();
        assertEquals(sut_ZombieMob.compareTo(sut_BasicMob), -1);
    }

    @Test
    public void givenZombieMob_WhenBasicMobTraveledSame_Then0() {
        sut_ZombieMob.traveledDistance();
        sut_BasicMob.traveledDistance();
        assertEquals(sut_ZombieMob.compareTo(sut_BasicMob), 0);
    }

    @Test
    public void givenZombieMob_WhenBasicMobTraveledMore_ThenPositive1() {
        sut_BasicMob.traveledDistance();
        assertEquals(sut_ZombieMob.compareTo(sut_BasicMob), 1);
    }

    /* How to test BufferedImage? */
}
