package towers.factories;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import views.TowerPanelView;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TowerFactoryTest {

    private static Stream productionTowerFactories() {
        return TowerPanelView.getTowerFactories().stream();
    }

    @ParameterizedTest
    @MethodSource("productionTowerFactories")
    public void givenTowerFactory_whenGetBuyButtonCalled_returnsSameBuyButtonEveryTime(TowerFactory SUT) {
        // This enforces the business rule of only creating one Buy Button per factory
        assertTrue(SUT.getBuyButton() == SUT.getBuyButton());
    }
}