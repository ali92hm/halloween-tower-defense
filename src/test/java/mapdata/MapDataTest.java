package mapdata;

import mobs.Mob;
import models.DriverModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import utilities.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MapDataTest {

    @Mock
    DriverModel model;
    @Mock
    Mob mob;
    private Map1Data sut;

    @BeforeEach
    void setup() {
        sut = new Map1Data(model, 1);
    }

    /* Test getMobLevel given Multiple levels */

    @Test
    void givenLevelFour_whenMobLevel_thenMobLevelTen() {
        assertEquals(10, sut.getMobLevel(4));
    }

    @Test
    void givenLevelFive_whenMobLevel_thenMobLevelTwelve() {
        assertEquals(12, sut.getMobLevel(5));
    }

    @Test
    void givenLevelFourteen_whenMobLevel_thenMobLevelSixteen() {
        assertEquals(16, sut.getMobLevel(14));
    }

    @Test
    void givenLevelSixteen_whenMobLevel_TheMobLevelSeventeen() {
        assertEquals(17, sut.getMobLevel(16));
    }

    /* Test mob count given levels */

    @Test
    void givenLevel1_whenGetMobs_MobCountEqualsSix() {
        assertEquals(6, sut.getMobs(1).size());
    }

    @Test
    void givenLeven10_whenGetMobds_MobCountEqualsEleven() {
        assertEquals(11, sut.getMobs(10).size());
    }

    @Test
    void givenMob_whenGetPosition_PostionIsZeroZero() {
        mob = mock(Mob.class);
        when(mob.getPosition()).thenReturn(new Position(0, 0));

        Position expected = new Position(0, 0);
        assertEquals(expected.getXCord(), sut.travelDistance(mob).getXCord());
        assertEquals(expected.getYCord(), sut.travelDistance(mob).getYCord());
    }
}
