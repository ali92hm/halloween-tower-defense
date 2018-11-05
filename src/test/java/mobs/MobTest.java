package mobs;

import models.DriverModel;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import projectiles.ChainingDenseLightning;
import projectiles.Projectile;
import utilities.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MobTest {
    private Mob sut_BasicMob;
    private Mob sut_EvilPumpkinMob;
    private Mob sut_FireImmuneMob;
    private Mob sut_FrostImmuneMob;
    private Mob sut_GiantPumpkinMob;
    private Mob sut_LaughingPumpkinMob;
    private Mob sut_LightningGolemMob;
    private Mob sut_LightningImmuneMob;
    private Mob sut_PumpkinMob;
    private Mob sut_SpeedyMob;
    private Mob sut_SpiderMob;
    private Mob sut_TankMob;
    private Mob sut_WitchMob;
    private Mob sut_ZombieMob;

    @Mock
    private Projectile chainDenseLightning;

    @BeforeEach
    public void init() {
        sut_BasicMob = new BasicMob(1, 1, new Position(0, 0));
        sut_EvilPumpkinMob = new EvilPumpkinMob(3, 3, new Position(0, 0));
        sut_FireImmuneMob = new FireImmuneMob(1, 3, new Position(0, 0));
        sut_FrostImmuneMob = new FrostImmuneMob(1, 1, new Position(0, 0));
        sut_GiantPumpkinMob = new GiantPumpkinMob(1, 1, new Position(0, 0));
        sut_LaughingPumpkinMob = new LaughingPumpkinMob(1, 1, new Position(0, 0));
        sut_LightningGolemMob = new LightningGolemMob(1, 1, new Position(0, 0));
        sut_LightningImmuneMob = new LightningImmuneMob(1, 1, new Position(0, 0));
        sut_PumpkinMob = new PumpkinMob(1, 1, new Position(0, 0));
        sut_SpeedyMob = new SpeedyMob(1, 1, new Position(0, 0));
        sut_SpiderMob = new SpiderMob(1, 1, new Position(0, 0));
        sut_TankMob = new TankMob(1, 1, new Position(0, 0));
        sut_WitchMob = new WitchMob(1, 1, new Position(0, 0));
        sut_ZombieMob = new ZombieMob(1, 1, new Position(0, 0));

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void givenBasicMob_WhenLevel1EasyDifficulty_ThenHealthBoost45() {
        assertEquals(45, sut_BasicMob.getHealthBoost(1, 30, 1));
    }

    @Test
    public void givenBasicMob_WhenLevel1EasyDifficulty_ThenHealth245() {
        assertEquals(245, sut_BasicMob.getHealth());
    }

    @Test
    public void givenEvilPumpkinMob_WhenLevel3HardDifficulty_ThenHealthBoost1350() {
        assertEquals(1350, sut_EvilPumpkinMob.getHealthBoost(3, 60, 3));
    }

    @Test
    public void givenMob_WhenSetNewPosition_ThenPositionX5() {
        sut_FireImmuneMob.setPosition(new Position(5, 4));
        assertEquals(5, sut_FireImmuneMob.getPosition().getXCord());
    }

    @Test
    public void givenMob_WhenSetNewPosition_ThenPositionY4() {
        sut_FrostImmuneMob.setPosition(5, 4);
        assertEquals(4, sut_FrostImmuneMob.getPosition().getYCord());
    }

    @Test
    public void givenFrostImmuneMob_WhenGetWidth_Then50() {
        assertEquals(50, sut_FrostImmuneMob.getMobWidth());
    }

    @Test
    public void givenFrostImmuneMob_WhenGetHeight_Then50() {
        assertEquals(50, sut_FrostImmuneMob.getMobHeight());
    }

    @Test
    public void givenMob_WhenSetDirectionL_ThenLeft() {
        sut_GiantPumpkinMob.setDirection('l');
        assertEquals('l', sut_GiantPumpkinMob.getDirection());
    }

    @Test
    public void givenMob_WhenDirectionRSetToT_ThenR() {
        sut_GiantPumpkinMob.setDirection('t');
        assertNotEquals('t', sut_GiantPumpkinMob.getDirection());
    }

    @Test
    public void givenMob_WhenGetUUID_ThenNotNull() {
        assertNotNull(sut_LaughingPumpkinMob.getID());
    }

    @Test
    public void givenLaughingPumpkinMob_WhenGetSpeedAndNoSlowdownAndEasy_Then5() {
        assertEquals(5, sut_LaughingPumpkinMob.getSpeed());
    }

    @Test
    public void givenLaughingPumpkinMob_WhenGetSlowPotency_Then0() {
        assertEquals(0, sut_LaughingPumpkinMob.getSlowPotency());
    }

    @Test
    public void givenLightningGolemMob_WhenGetRadius_Then30() {
        assertEquals(30, sut_LightningGolemMob.getRadius());
    }

    @Test
    public void givenMob_WhenTraveledDistance_Then1() {
        sut_LightningGolemMob.traveledDistance();
        assertEquals(1, sut_LightningGolemMob.getDistanceTraveled());
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
        assertEquals(sut_SpiderMob.getID().hashCode() + 31, sut_SpiderMob.hashCode());
    }

    @Test
    public void givenTankMob_WhenWitchMob_ThenNotEquals() {
        assertFalse(sut_TankMob.equals(sut_WitchMob));
    }

    @Test
    public void givenZombieMob_WhenBasicMobTraveledLess_ThenNegative1() {
        sut_ZombieMob.traveledDistance();
        assertTrue(sut_ZombieMob.compareTo(sut_BasicMob) < 0);
    }

    @Test
    public void givenZombieMob_WhenBasicMobTraveledSame_Then0() {
        sut_ZombieMob.traveledDistance();
        sut_BasicMob.traveledDistance();
        assertEquals(0, sut_ZombieMob.compareTo(sut_BasicMob));
    }

    @Test
    public void givenZombieMob_WhenBasicMobTraveledMore_ThenPositive1() {
        sut_BasicMob.traveledDistance();
        assertTrue(sut_ZombieMob.compareTo(sut_BasicMob) > 0);
    }

    @Test
    public void givenBasicMob_WhenHitByChainingDenseLightningAndIsLevel1AndDifficulty1_ThenHealth45() {
        when(chainDenseLightning.getDamage()).thenReturn(200.0);
        when(chainDenseLightning.getDamageDuration()).thenReturn(1.0);
        sut_BasicMob.activate();
        sut_BasicMob.mobHitBy(chainDenseLightning);
        sut_BasicMob.mobDamageTick();
        assertEquals(45, sut_BasicMob.getHealth());
    }

}
