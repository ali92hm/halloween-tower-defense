package towerdefence;

import controllers.InfoListener;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class DriverTest {
    @Test
    public void givenInfoListener_whenGetInfo_thenInfoNotNull() {
        InfoListener sut = new InfoListener();
        assertNotNull(sut.getInfo());
    }
}