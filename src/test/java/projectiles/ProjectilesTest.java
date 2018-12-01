package projectiles;

import mobs.BasicMob;
import mobs.Mob;
import models.DriverModel;
import org.junit.jupiter.api.Test;
import utilities.Position;
import utilities.Vector;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void givenChainingDenseLightning_whenIsLightning_thenReturnTrue() {
        assertTrue(sut_ChainingDeseLightning.isLightning());
    }

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
    public void givenShock_whenIs_thenReturnTrue() {
        assertTrue(sut_Shock.isLightning());
    }

    @Test
    public void givenThunderBolt_whenIsLightning_thenReturnTrue() {
        assertTrue(sut_ThunderBolt.isLightning());
    }
}
