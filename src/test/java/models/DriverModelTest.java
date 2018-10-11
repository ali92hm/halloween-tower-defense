package models;

import models.DriverModel;
import views.DriverView;
import java.awt.event.ActionListener;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DriverModelTest extends DriverView implements ActionListener {

    private static DriverModel model = new DriverModel();
    public DriverModelTest() {
        super(model);
    }

    private DriverModelTest setup() {
        DriverModelTest sut = new DriverModelTest();
        sut.switchToMainScreen();
        return sut;
    }

    @Test
    public void givenMainView_WhenResetCalled_ThenMap1Selected() {
        assertTrue(setup().getMainView().getMap1().isSelected());
    }

    @Test
    public void givenMainView_WhenMap1Selected_ThenEasySelected() {
        assertTrue(setup().getMainView().getEasyButton().isSelected());
    }

    @Test
    public void givenMainView_WhenMap1Selected_ThenHardNotSelected() {
        assertFalse(setup().getMainView().getHardButton().isSelected());
    }

    @Test
    public void givenMainView_WhenMediumSelected_ThenMediumSelected() {
        DriverModelTest sut = setup();
        sut.getMainView().getMediumButton().setSelected(true);
        assertTrue(sut.getMainView().getMediumButton().isSelected());
    }

    @Test
    public void givenMainView_WhenMediumSelected_ThenEasyNotSelected() {
        DriverModelTest sut = setup();
        sut.getMainView().getMediumButton().setSelected(true);
        assertFalse(sut.getMainView().getEasyButton().isSelected());
    }
}