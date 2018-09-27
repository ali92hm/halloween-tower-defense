package test.views;

import main.views.TowerPanelView;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TowerPanelViewTest {
    TowerPanelView SUT;

    @Test
    public void givenTowerPanelView_WhenCalledGetButtons_AllTooltipsAreUnique(){
        SUT = new TowerPanelView();

        // get list of buttons
        ArrayList<JToggleButton> buttons = SUT.getCurrentTowerButtons();

        // Strip buttons down to just tooltips in new array list
        ArrayList<String> toolTips = new ArrayList<>();
        for (JToggleButton button: buttons)
            toolTips.add(button.getToolTipText());

        /*
            A tooltip is unique so long as the same tooltip cannot be found later in the list
         */
        Boolean allToolTipsUnique = true;
        int i = 0;
        while(i < toolTips.size() && allToolTipsUnique){
            if(toolTips.lastIndexOf(toolTips.get(i)) > i)
                allToolTipsUnique = false;
            i++;
        }
        assertTrue(allToolTipsUnique);
    }

}