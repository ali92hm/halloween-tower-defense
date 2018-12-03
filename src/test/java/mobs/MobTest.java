package mobs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import projectiles.ChainLightning;
import projectiles.FireBlast;
import projectiles.IceBeam;
import projectiles.Projectile;
import utilities.Position;
import views.DriverView;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
    private Projectile projectile;

    @Mock
    private IceBeam iceMock;

    @Mock
    private FireBlast fireMock;

    @Mock
    private ChainLightning chainMock;

    @BeforeEach
    public void init() {
        sut_BasicMob = new BasicMob(1, 1, new Position(0, 0));
        sut_EvilPumpkinMob = new EvilPumpkinMob(3, 3, new Position(0, 0));
        sut_FireImmuneMob = new FireImmuneMob(1, 1, new Position(0, 0));
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
    public void givenBasicMob_WhenHitBy200DamageAndIsLevel1AndDifficulty1_ThenHealth45() {
        when(projectile.getDamage()).thenReturn(200.0);
        when(projectile.getDamageDuration()).thenReturn(1.0);
        sut_BasicMob.activate();
        sut_BasicMob.mobHitBy(projectile);
        sut_BasicMob.mobDamageTick();
        assertEquals(45, sut_BasicMob.getHealth());
    }

    @Test
    public void givenBasicMob_WhenHitByIceBeam_ThenIsHitByIceBeam() {
        sut_BasicMob.activate();
        sut_BasicMob.mobHitBy(iceMock);
        assertTrue(sut_BasicMob.isHitByIceBeam());
    }

    @Test
    public void givenFrostImmuneMob_WhenHitByIceBeam_ThenNotHitByIceBeam() {
        when(iceMock.isIce()).thenReturn(true);
        sut_FrostImmuneMob.activate();
        sut_FrostImmuneMob.mobHitBy(iceMock);
        assertFalse(sut_FrostImmuneMob.isHitByIceBeam());
    }

    @Test
    public void givenFireImmuneMob_WhenHitByFireBlastAndLevel1Difficulty1_ThenHealth245() {
        when(fireMock.isFire()).thenReturn(true);
        sut_FireImmuneMob.activate();
        sut_FireImmuneMob.mobHitBy(fireMock);
        sut_FireImmuneMob.mobDamageTick();
        assertEquals(245, sut_FireImmuneMob.getHealth());
    }

    @Test
    public void givenLightningImmuneMob_WhenHitByLightningAndLevel1Difficulty1_ThenHealth115() {
        when(chainMock.isLightning()).thenReturn(true);
        sut_LightningImmuneMob.activate();
        sut_LightningImmuneMob.mobHitBy(chainMock);
        sut_LightningImmuneMob.mobDamageTick();
        assertEquals(115, sut_LightningImmuneMob.getHealth());
    }

    @Test
    public void givenFrostImmuneMobJustCreated_WhenGetImage_ThenMatches() {
        BufferedImage img1 = DriverView.getImage("GhostMobR.png", 50, 50);
        for (int x = 0; x < img1.getWidth(); x++)
            for (int y = 0; y < img1.getHeight(); y++)
                assertEquals(img1.getRGB(x, y), sut_FrostImmuneMob.getImage().getRGB(x, y));
    }

    @Test
    public void givenFrostImmuneMobMovingLeft_WhenGetImage_ThenMatches() {
        sut_FrostImmuneMob.movingLeft();
        BufferedImage img1 = DriverView.getImage("GhostMob.png", 50, 50);
        for (int x = 0; x < img1.getWidth(); x++)
            for (int y = 0; y < img1.getHeight(); y++)
                assertEquals(img1.getRGB(x, y), sut_FrostImmuneMob.getImage().getRGB(x, y));
    }

    @Test
    public void givenFrostImmuneMobMovingRight_WhenGetImage_ThenMatches() {
        sut_FrostImmuneMob.movingLeft();
        sut_FrostImmuneMob.movingRight();
        BufferedImage img1 = DriverView.getImage("GhostMobR.png", 50, 50);
        for (int x = 0; x < img1.getWidth(); x++)
            for (int y = 0; y < img1.getHeight(); y++)
                assertEquals(img1.getRGB(x, y), sut_FrostImmuneMob.getImage().getRGB(x, y));
    }

    @Test
    public void givenLightningGolemMobMovingLeft_WhenGetImage_ThenMatches() {
        sut_LightningGolemMob.movingLeft();
        BufferedImage img1 = DriverView.getImage("LightningGolemMob.png", 50, 50);
        for (int x = 0; x < img1.getWidth(); x++)
            for (int y = 0; y < img1.getHeight(); y++)
                assertEquals(img1.getRGB(x, y), sut_LightningGolemMob.getImage().getRGB(x, y));
    }

    @Test
    public void givenLightningGolemMobMovingRight_WhenGetImage_ThenMatches() {
        sut_LightningGolemMob.movingLeft();
        sut_LightningGolemMob.movingRight();
        BufferedImage img1 = DriverView.getImage("LightningGolemMobR.png", 50, 50);
        for (int x = 0; x < img1.getWidth(); x++)
            for (int y = 0; y < img1.getHeight(); y++)
                assertEquals(img1.getRGB(x, y), sut_LightningGolemMob.getImage().getRGB(x, y));
    }

    @Test
    public void givenLightningImmuneMobMovingLeft_WhenGetImage_ThenMatches() {
        sut_LightningImmuneMob.movingLeft();
        BufferedImage img1 = DriverView.getImage("LightningGolemMob.png", 50, 50);
        for (int x = 0; x < img1.getWidth(); x++)
            for (int y = 0; y < img1.getHeight(); y++)
                assertEquals(img1.getRGB(x, y), sut_LightningImmuneMob.getImage().getRGB(x, y));
    }

    @Test
    public void givenLightningImmuneMobMovingRight_WhenGetImage_ThenMatches() {
        sut_LightningImmuneMob.movingLeft();
        sut_LightningImmuneMob.movingRight();
        BufferedImage img1 = DriverView.getImage("LightningGolemMobR.png", 50, 50);
        for (int x = 0; x < img1.getWidth(); x++)
            for (int y = 0; y < img1.getHeight(); y++)
                assertEquals(img1.getRGB(x, y), sut_LightningImmuneMob.getImage().getRGB(x, y));
    }

    @Test
    public void givenSpeedyMobMovingLeft_WhenGetImage_ThenMatches() {
        sut_SpeedyMob.movingLeft();
        BufferedImage img1 = DriverView.getImage("Bat.png", 50, 50);
        for (int x = 0; x < img1.getWidth(); x++)
            for (int y = 0; y < img1.getHeight(); y++)
                assertEquals(img1.getRGB(x, y), sut_SpeedyMob.getImage().getRGB(x, y));
    }

    @Test
    public void givenSpeedyMobMovingRight_WhenGetImage_ThenMatches() {
        sut_SpeedyMob.movingLeft();
        sut_SpeedyMob.movingRight();
        BufferedImage img1 = DriverView.getImage("BatR.png", 50, 50);
        for (int x = 0; x < img1.getWidth(); x++)
            for (int y = 0; y < img1.getHeight(); y++)
                assertEquals(img1.getRGB(x, y), sut_SpeedyMob.getImage().getRGB(x, y));
    }

    @Test
    public void givenTankMobMovingLeft_WhenGetImage_ThenMatches() {
        sut_TankMob.movingLeft();
        BufferedImage img1 = DriverView.getImage("ZombieMob.png", 50, 50);
        for (int x = 0; x < img1.getWidth(); x++)
            for (int y = 0; y < img1.getHeight(); y++)
                assertEquals(img1.getRGB(x, y), sut_TankMob.getImage().getRGB(x, y));
    }

    @Test
    public void givenTankMobMovingRight_WhenGetImage_ThenMatches() {
        sut_TankMob.movingLeft();
        sut_TankMob.movingRight();
        BufferedImage img1 = DriverView.getImage("ZombieMobR.png", 50, 50);
        for (int x = 0; x < img1.getWidth(); x++)
            for (int y = 0; y < img1.getHeight(); y++)
                assertEquals(img1.getRGB(x, y), sut_TankMob.getImage().getRGB(x, y));
    }

    @Test
    public void givenWitchMob_WhenImmuneToLightning_ThenTrue() {
        assertTrue(sut_WitchMob.immuneToLightning());
    }

    @Test
    public void givenWitchMob_WhenImmuneToFire_ThenTrue() {
        assertTrue(sut_WitchMob.immuneToFire());
    }

    @Test
    public void givenWitchMob_WhenImmuneToIce_ThenTrue() {
        assertTrue(sut_WitchMob.immuneToIce());
    }

}
