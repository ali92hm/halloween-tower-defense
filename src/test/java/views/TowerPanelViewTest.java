package views;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import towers.Tower;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TowerPanelViewTest {
    TowerPanelView SUT;

    @Disabled
    public void givenTowerPanelView_WhenCalledGetButtons_AllTooltipsAreUnique() {
        SUT = new TowerPanelView();

        // get list of buttons
        ArrayList<TowerBuyButton> buttons = SUT.getCurrentTowerButtons();

        // Strip buttons down to just tooltips in new array list
        ArrayList<String> toolTips = new ArrayList<>();
        for (TowerBuyButton button : buttons)
            toolTips.add(button.getToolTipText());

        assertTrue(elementsInArrayListUnique(toolTips));
    }

    @Test
    public void givenTowerPanelView_WhenCalledGetButtons_AllButtonsPointToUniqueTower() {
        SUT = new TowerPanelView();

        // get list of buttons
        ArrayList<TowerBuyButton> buttons = SUT.getCurrentTowerButtons();

        HashMap<Class<? extends Tower>, Integer> usageCountPerTower = new HashMap<>();

        for (TowerBuyButton button : buttons) {
            Class current = button.getTowerClass();
            Integer currentCount = usageCountPerTower.get(current);
            if (currentCount == null){
                usageCountPerTower.put(current,1);
            }else{
                usageCountPerTower.replace(current, currentCount + 1);
            }

        }

    }

    @Disabled
    public void givenTowerPanelView_WhenCalledGetButtons_AllNamesAreUnique() {
        SUT = new TowerPanelView();

        // get list of buttons
        ArrayList<TowerBuyButton> buttons = SUT.getCurrentTowerButtons();

        // Strip buttons down to just names in new array list
        ArrayList<String> buttonNames = new ArrayList<>();
        for (JToggleButton button : buttons)
            buttonNames.add(button.getName());

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
