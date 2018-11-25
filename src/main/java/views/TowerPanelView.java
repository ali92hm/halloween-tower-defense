package views;

import models.DriverModel;
import towers.Tower;
import towers.factories.*;
import utilities.Position;
import utilities.TDButtonGroup;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * the side panel view that contains
 * all of the main.towers
 *
 * @author Scorpion
 */

public class TowerPanelView extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public static final int TOWER_ICON_WIDTH = 40;
    public static final int TOWER_ICON_HEIGHT = 40;
    private DriverModel model;
    private TDButtonGroup buildGroup = new TDButtonGroup();
    private static ArrayList<TowerFactory> towerFactories = new ArrayList<>(Arrays.asList(
            new DenseLightningTowerFactory(),
            new FireBombTowerFactory(),
            new FireTowerFactory(),
            new FreezeTowerFactory(),
            new IceTowerFactory(),
            new IcicleTowerFactory(),
            new LightningTowerFactory(),
            new PatchOfFireTowerFactory(),
            new TeslaTowerFactory()
    ));

    /**
     * constructs the tower panel
     */

    public TowerPanelView(DriverModel model) {
        this.model = model;
        this.model.addActionListener(this);
        this.setupFrame();
        this.enableTowerButtons();
    }

    /**
     * sets of the panel with the preferred
     * size and opacity
     */

    public void setupFrame() {

        this.setOpaque(true);
        this.layoutTowers();

        this.setPreferredSize(new Dimension(194, 800));
        this.setMaximumSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());
    }

    public void enableTowerButtons() {
        for (TowerFactory factory : this.towerFactories) {
            factory.getBuyButton().setEnabled(this.model.getMoney() >= factory.getPrice());
        }

    }

    /**
     * adds the tower buttons to the panel
     */

    public void layoutTowers() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 15;
        c.ipady = 15;

        for (int i = 0; i < towerFactories.size(); i++) {
            c.gridy = i % 3;
            c.gridx = i / 3;
            this.add(layoutTowerPanel(towerFactories.get(i)), c);
        }

        enableTowerButtons();
    }

    /**
     * sets up each tower with its labels
     * for the name and cost
     */

    public JPanel layoutTowerPanel(TowerFactory factory) {

        TowerBuyButton buyButton = factory.getBuyButton();

        JPanel panel = new JPanel(new BorderLayout());
        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        JLabel towerName = new JLabel(buyButton.getName());
        JLabel towerLabel = new JLabel("Tower");
        towerName.setFont(new Font("Serif", Font.BOLD, 10));
        towerLabel.setFont(new Font("Serif", Font.BOLD, 10));
        towerName.setHorizontalAlignment(SwingConstants.CENTER);
        towerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        labelPanel.add(towerName);
        labelPanel.add(towerLabel);
        JLabel towerCost = new JLabel(factory.getPrice() + "g");
        towerCost.setFont(new Font("Serif", Font.BOLD, 10));

        panel.add(labelPanel, BorderLayout.NORTH);
        panel.add(buyButton, BorderLayout.CENTER);
        panel.add(towerCost, BorderLayout.SOUTH);

        buildGroup.add(buyButton);
        return panel;
    }

    /**
     * takes buttons and puts them into button groups
     *
     * @return TDButtonGroup
     */

    public TDButtonGroup getBuildButtonGroup() {
        return this.buildGroup;
    }

    /**
     * when the model updates this view, checks
     * if the buttons should be updated
     */

    public void actionPerformed(ActionEvent e) {
        enableTowerButtons();
    }

    public Tower createSelectedTower(Position position, DriverModel driver) {
        TowerBuyButton buyButton = buildGroup.getSelectedButton();
        TowerFactory towerFactory = null;
        int i = 0;
        while (towerFactory == null && i < towerFactories.size()) {
            TowerFactory current = towerFactories.get(i);

            if (current.getBuyButton() == buyButton)
                towerFactory = current;

            i++;
        }

        return towerFactory.create(position, driver);
    }

    public ArrayList<TowerFactory> getTowerFactories() {
        return this.towerFactories;
    }
}
