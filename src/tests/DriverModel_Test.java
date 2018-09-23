package tests;

import models.DriverModel;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import views.DriverView;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

public class DriverModel_Test extends DriverView implements ActionListener {

    private static DriverModel model = new DriverModel();
    public DriverModel_Test() {
        super(model);
    }

    private DriverModel_Test setup() {
        DriverModel_Test sut = new DriverModel_Test();
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
        DriverModel_Test sut = setup();
        sut.getMainView().getMediumButton().setSelected(true);
        assertTrue(sut.getMainView().getMediumButton().isSelected());
    }

    @Test
    public void givenMainView_WhenMediumSelected_ThenEasyNotSelected() {
        DriverModel_Test sut = setup();
        sut.getMainView().getMediumButton().setSelected(true);
        assertFalse(sut.getMainView().getEasyButton().isSelected());
    }
}