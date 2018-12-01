package views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class MainViewTest {

    private MainView sut;

    @BeforeEach
    public void init() {
        sut = new MainView(mock(BufferedImage.class));
        sut.resetMainView();
    }

    @Test
    public void givenMainView_WhenResetMainView_ThenMap1IsSelected() {
        assertTrue(sut.getMap1().isSelected());
    }

    @Test
    public void givenMainView_WhenResetMainView_ThenEasySelected() {
        assertTrue(sut.getEasyButton().isSelected());
    }

    @Test
    public void givenMainView_WhenResetMainViewAndSetHard_ThenHardSelected() {
        sut.getHardButton().setSelected(true);
        assertTrue(sut.getHardButton().isSelected());
    }

    @Test
    public void givenMainView_WhenResetMainView_ThenHardNotSelected() {
        assertFalse(sut.getHardButton().isSelected());
    }

    @Test
    public void givenMainView_WhenResetMainViewAndSetMedium_ThenMediumSelected() {
        sut.getMediumButton().setSelected(true);
        assertTrue(sut.getMediumButton().isSelected());
    }

}
