package projectiles;

import mobs.BasicMob;
import mobs.Mob;
import models.DriverModel;
import org.junit.jupiter.api.Test;
import utilities.Position;
import utilities.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectilesTest {
    ChainingDenseLightning sut_ChainingDeseLightning = new ChainingDenseLightning(new DriverModel(), new Position(0, 0), new Vector(), new BasicMob(0, 0, new Position(0, 0)), 0, 0);
    ChainingShock sut_ChainingShock = new ChainingShock(new DriverModel(), new Position(0, 0), new Vector(), new BasicMob(0, 0, new Position(0, 0)), 0, 0);
    ChainLightning sut_ChainLightning = new ChainLightning(new DriverModel(), new Position(0, 0), new Vector(), new BasicMob(0, 0, new Position(0, 0)), new Mob[]{new BasicMob(0, 0, new Position(0, 0))}, 0, 0);
    DeepFreeze sut_DeepFreeze = new DeepFreeze(new DriverModel(), new Position(0, 0), new Vector(), DeepFreeze.PROJECTILE_WIDTH, DeepFreeze.PROJECTILE_HEIGHT);
    DenseLightning sut_DenseLightning = new DenseLightning(new DriverModel(), new Position(0, 0), new Vector(), 0, 0, 0);
    FireBlast sut_FireBlast = new FireBlast(new DriverModel(), new Position(0, 0), new Vector(), 0, 0);
    FireBomb sut_FireBomb = new FireBomb(new DriverModel(), new Position(0, 0), new Vector(), 0, 0);
    IceBeam sut_IceBeam = new IceBeam(new DriverModel(), new Position(0, 0), new BasicMob(0, 0, new Position(0, 0)), new Vector(), 0, 0);
    Icicle sut_Icicle = new Icicle(new DriverModel(), new Position(0, 0), new Vector(), 0, 0);
    PatchOfFire sut_PatchOfFire = new PatchOfFire(new DriverModel(), new Position(0, 0), new Vector(), 0, 0);
    PitOfFire sut_PitOfFire = new PitOfFire(new DriverModel(), new Position(0, 0), new Vector(), 0, 0);
    RingOfFire sut_RingOfFire = new RingOfFire(new DriverModel(), new Position(0, 0), new Vector(), 0, 0);
    Shock sut_Shock = new Shock(new DriverModel(), new Position(0, 0), new Vector(), new BasicMob(0, 0, new Position(0, 0)), 0, 0);
    ThunderBolt sut_ThunderBolt = new ThunderBolt(new DriverModel(), new Position(0, 0), new Vector(), 0, 0, false);

    @Test
    public void givenThunderBolt_whenSetAlive_thenReturnTrue() {
        sut_ThunderBolt.setAlive();
        assertTrue(sut_ThunderBolt.stillAlive);
    }

    @Test
    public void givenThunderBolt_whenSetAlive_thenReturnFalse() {
        sut_ThunderBolt.movements = 10;
        sut_ThunderBolt.setAlive();
        assertFalse(sut_ThunderBolt.stillAlive);
    }

    @Test
    public void givenRingOfFire_whenSetAlive_thenReturnTrue() {
        sut_RingOfFire.setAlive();
        assertTrue(sut_RingOfFire.stillAlive);
    }

    @Test
    public void givenRingOfFire_whenSetAlive_thenReturnFalse() {
        sut_RingOfFire.movements = 10;
        sut_RingOfFire.setAlive();
        assertFalse(sut_RingOfFire.stillAlive);
    }

    @Test
    public void givenShock_whenSetAlive_thenReturnTrue() {
        sut_Shock.setAlive();
        assertTrue(sut_Shock.stillAlive);
    }

    @Test
    public void givenShock_whenSetAlive_thenReturnFalse() {
        sut_Shock.movements = 10;
        sut_Shock.setAlive();
        assertFalse(sut_Shock.stillAlive);
    }

    @Test
    public void givenFireBomb_thenSetAlive_thenReturnTrue() {
        sut_FireBomb.setAlive();
        assertTrue(sut_FireBomb.stillAlive);
    }

    @Test
    public void givenFireBomb_thenSetAlive_thenReturnFalse() {
        sut_FireBomb.movements = 15;
        sut_FireBomb.setAlive();
        assertFalse(sut_FireBomb.stillAlive);
    }

    @Test
    public void givenIceBeam_thenSetAlive_thenReturnTrue(){
        sut_IceBeam.setAlive();
        assertTrue(sut_IceBeam.stillAlive);
    }

    @Test
    public void givenIceBeam_thenSetAlive_thenReturnFalse(){
        sut_IceBeam.movements = 15;
        sut_IceBeam.setAlive();
        assertFalse(sut_IceBeam.stillAlive);
    }

    @Test
    public void givenIcicle_whenSetAlive_thenReturnTrue() {
        sut_Icicle.setAlive();
        assertTrue(sut_Icicle.stillAlive);
    }

    @Test
    public void givenIcicle_whenSetAlive_thenReturnFalse() {
        sut_Icicle.movements = 15;
        sut_Icicle.setAlive();
        assertFalse(sut_Icicle.stillAlive);
    }

    @Test
    public void givenPatchOfFire_whenSetAlive_theReturnTrue() {
        sut_PatchOfFire.setAlive();
        assertTrue(sut_PatchOfFire.stillAlive);
    }

    @Test
    public void givenPatchOfFire_whenSetAlive_theReturnFalse() {
        sut_PatchOfFire.movements = 21;
        sut_PatchOfFire.setAlive();
        assertFalse(sut_PatchOfFire.stillAlive);
    }

    @Test
    public void givenPitOfFire_whenSetAlive_thenReturnTrue() {
        sut_PitOfFire.setAlive();
        assertTrue(sut_PitOfFire.stillAlive);
    }

    @Test
    public void givenPitOfFire_whenSetAlive_thenReturnFalse() {
        sut_PitOfFire.movements = 41;
        sut_PitOfFire.setAlive();
        assertFalse(sut_PitOfFire.stillAlive);
    }

    @Test
    public void givenChainingDenseLightning_whenIsLightning_thenReturnTrue() { assertTrue(sut_ChainingDeseLightning.isLightning()); }

    @Test
    public void givenChainingShock_whenIsLightning_thenReturnTrue() {
        assertTrue(sut_ChainingShock.isLightning());
    }

    @Test
    public void givenChainLightning_whenIsLightning_thenReturnTrue() {
        assertTrue(sut_ChainLightning.isLightning());
    }

    @Test
    public void givenDeepFreeze_whenIsIce_thenReturnTrue() {
        assertTrue(sut_DeepFreeze.isIce());
    }

    @Test
    public void givenDenseLightning_whenIsLightning_thenReturnTrue() {
        assertTrue(sut_DenseLightning.isLightning());
    }

    @Test
    public void givenFireBlast_whenIsFire_thenReturnTrue() {
        assertTrue(sut_FireBlast.isFire());
    }

    @Test
    public void givenFireBomb_thenIsFire_thenReturnTrue() {
        assertTrue(sut_FireBomb.isFire());
    }

    @Test
    public void givenIceBeam_thenIsIceBeam_thenReturnTrue() {
        assertTrue(sut_IceBeam.isIce());
    }

    @Test
    public void givenIcicle_whenIsIce_thenReturnTrue() {
        assertTrue(sut_Icicle.isIce());
    }

    @Test
    public void givenPatchOfFire_whenIsFire_theReturnTrue() {
        assertTrue(sut_PatchOfFire.isFire());
    }

    @Test
    public void givenPitOfFire_whenIsFire_thenReturnTrue() {
        assertTrue(sut_PitOfFire.isFire());
    }

    @Test
    public void givenRingOfFire_whenIsFire_thenReturnTrue() {
        assertTrue(sut_RingOfFire.isFire());
    }

    @Test
    public void givenShock_whenIsLightning_thenReturnTrue() {
        assertTrue(sut_Shock.isLightning());
    }

    @Test
    public void givenThunderBolt_whenIsLightning_thenReturnTrue() {
        assertTrue(sut_ThunderBolt.isLightning());
    }
}
