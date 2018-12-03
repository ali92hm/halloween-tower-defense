package views;

import models.DriverModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import towers.factories.TowerFactory;
import utilities.Position;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class TowerPanelViewTest {
    TowerPanelView SUT;

    @BeforeEach
    public void setup() {
        SUT = new TowerPanelView(mock(DriverModel.class));
    }

    @Test
    public void givenTowerPanelView_WhenCalledGetButtons_AllTooltipsAreUnique() {
        // Strip buttons down to just tooltips in new array list
        ArrayList<String> toolTips = new ArrayList<>();

        for (TowerFactory factory : SUT.getTowerFactories())
            toolTips.add(factory.getBuyButton().getToolTipText());

        assertTrue(elementsInArrayListUnique(toolTips));
    }

    @Test
    public void givenTowerPanelView_WhenCalledGetButtons_AllButtonsPointToUniqueTower() {
        ArrayList<Class> towerClasses = new ArrayList<>();

        for (TowerFactory factory : SUT.getTowerFactories())
            towerClasses.add(factory.create(mock(Position.class), mock(DriverModel.class)).getClass());

        assertTrue(elementsInArrayListUnique(towerClasses));
    }

    @Test
    public void givenTowerPanelView_WhenCalledGetButtons_AllNamesAreUnique() {
        // Strip buttons down to just names in new array list
        ArrayList<String> buttonNames = new ArrayList<>();
        for (TowerFactory factory : SUT.getTowerFactories())
            buttonNames.add(factory.getBuyButton().getName());

        assertTrue(elementsInArrayListUnique(buttonNames));
    }

    public boolean elementsInArrayListUnique(ArrayList list) {
        Boolean elementsUnique = true;
        int i = 0;

        /*
            An element isn't unique if found again later in list
         */
        while (i < list.size() && elementsUnique) {
            if (list.lastIndexOf(list.get(i)) > i)
                elementsUnique = false;
            i++;
        }
        return elementsUnique;
    }
}
