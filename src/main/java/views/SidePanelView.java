package views;

import models.DriverModel;

import javax.swing.*;
import java.awt.*;

/**
 * Contains all the components
 * for the tower defense part of the game
 *
 * @author Scorpion
 */

public class SidePanelView extends JPanel {

    private static final long serialVersionUID = 1L;

    private DriverModel model;
    private DriverView view;
    private InfoView infoPanel;
    private ButtonView buttonPanel;
    private UpgradeView upgradePanel;
    private TowerPanelView towerPanel;
    private TalentSideView talentPanel;
    private CardLayout midPanelCards;
    private JPanel flipPanel;

    /**
     * Creates a Side Panel View. Contains the different panels that swap throughout the game.
     */

    public SidePanelView(final DriverModel model, final DriverView view) {

        this.model = model;
        this.view = view;
        this.talentPanel = new TalentSideView();
        this.buttonPanel = new ButtonView();
        this.upgradePanel = new UpgradeView();
        this.towerPanel = new TowerPanelView(model);
        this.infoPanel = new InfoView();

        this.buttonPanel.setModel(this.model, this.view);
        this.upgradePanel.setModel(this.model);

        this.infoPanel.setModel(this.model);

        this.midPanelCards = new CardLayout();
        this.flipPanel = new JPanel(this.midPanelCards);
        this.flipPanel.add(towerPanel, "TowerPanel");
        this.flipPanel.add(upgradePanel, "UpgradePanel");
        this.flipPanel.add(talentPanel, "TalentPanel");

        this.setLayout(new BorderLayout());
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(flipPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.midPanelCards.show(this.flipPanel, "TowerPanel");
    }

    /**
     * Getter for the buttonview.
     *
     * @return ButtonView
     */

    public ButtonView getButtonView() {
        return buttonPanel;
    }

    /**
     * Getter for the upgradeview.
     *
     * @return UpgradeView
     */

    public UpgradeView getUpgradeView() {
        return upgradePanel;
    }

    /**
     * Getter for the tower view.
     *
     * @return TowerPanelView
     */

    public TowerPanelView getTowerView() {
        return towerPanel;
    }

    /**
     * Getter for the talent view.
     *
     * @return TalentSideView
     */

    public TalentSideView getTalentView() {
        return talentPanel;
    }

    /**
     * Getter for the infoview.
     *
     * @return InfoView
     */

    public InfoView getInfoView() {
        return infoPanel;
    }

    /**
     * Swaps the center panel to the upgrade panel.
     */

    public void switchToUpgradePanel() {
        this.midPanelCards.show(this.flipPanel, "UpgradePanel");
    }

    /**
     * Swaps the center panel the to talent panel.
     */

    public void switchToTalentPanel() {
        this.midPanelCards.show(this.flipPanel, "TalentPanel");
    }

    /**
     * Swaps the center panel to the tower panel.
     */

    public void switchToTowerPanel() {
        this.midPanelCards.show(this.flipPanel, "TowerPanel");
    }
}
