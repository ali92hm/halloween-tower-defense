package test.towerdefence;

import main.controllers.InfoListener;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DriverTest {
    @Test
    public void givenInfoListener_whenGetInfo_thenInfoNotNull() {
        InfoListener sut = new InfoListener();
        assertNotNull(sut.getInfo());
    }
}