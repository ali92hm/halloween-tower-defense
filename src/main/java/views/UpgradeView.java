package views;

import models.DriverModel;
import towerdefence.Driver;
import towers.Tower;
import utilities.TDButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The panel for upgrading main.towers
 *
 * @author Scorpion
 */

public class UpgradeView extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private DriverModel model;
    private Tower selectedTower;

    private JPanel backSellButtonPanel;
    private JPanel towerPanel;
    private JPanel upgradeSellPanel;

    private JLabel headderlabel;
    private JLabel path1Icon;
    private JLabel path2Icon;
    private JLabel path3Icon;
    private JLabel path1Text;
    private JLabel path2Text;
    private JLabel path3Text;
    private JLabel towerIcon;

    private TDButton backButton;
    private JButton sellButton;

    private JButton path1Button;
    private JButton path2Button;
    private JButton path3Button;

    private JButton cancelButton;
    private JButton confirmButton;

    private int iconSize = 30;

    /**
     * constructor for the upgrade panel
     */

    public UpgradeView() {
        super();
        this.setLayout(new BorderLayout());

        this.setBackSellPanel();
        this.setTowerInfoPanel();
        upgradeSellPanel = new JPanel(new GridLayout(4, 1));
        upgradeSellPanel.setPreferredSize(new Dimension(194, 170));
        upgradeSellPanel.setOpaque(false);

        this.add(backSellButtonPanel, BorderLayout.NORTH);
        this.add(towerPanel, BorderLayout.CENTER);
        this.add(upgradeSellPanel, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(194, 400));
        this.setMaximumSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());

    }

    /**
     * top panel with back and sell buttons
     */

    private void setBackSellPanel() {
        backSellButtonPanel = new JPanel();
        backSellButtonPanel.setOpaque(false);
        JPanel filler1 = new JPanel();
        filler1.setOpaque(false);

        JLabel sellLabel = new JLabel("Sell");
        path1Button = new JButton();
        path2Button = new JButton();
        path3Button = new JButton();
        backButton = new TDButton(DriverView.getImage("Back Button.png", 70, iconSize));
        sellButton = new JButton();
        cancelButton = new JButton("Cancel");
        confirmButton = new JButton("Confirm");
        sellButton.setIcon(new ImageIcon(DriverView.getImage("Sell Icon.png", iconSize, iconSize)));
        backButton.setPreferredSize(new Dimension(70, 30));
        sellButton.setPreferredSize(new Dimension(30, 30));
        backButton.setContentAreaFilled(false);
        sellButton.setContentAreaFilled(false);
        filler1.setPreferredSize(new Dimension(30, 30));

        backSellButtonPanel.add(backButton);
        backSellButtonPanel.add(filler1);
        backSellButtonPanel.add(sellButton);
        backSellButtonPanel.add(sellLabel);
    }

    /**
     * panel that informs the user of the tower name,
     * the image of the tower, what upgrades it has,
     * and the buttons for upgrading the tower
     */

    private void setTowerInfoPanel() {
        towerPanel = new JPanel(new BorderLayout());
        towerPanel.setOpaque(false);
        JPanel towerHeaderPanel = new JPanel(new GridLayout(2, 1));
        JPanel towerUpgradePanel = new JPanel(new GridLayout(3, 1));
        towerHeaderPanel.setOpaque(false);
        towerUpgradePanel.setOpaque(false);

        path1Icon = new JLabel(new ImageIcon(DriverView.getImage("UnderConstruction.png", iconSize, iconSize)));
        path2Icon = new JLabel(new ImageIcon(DriverView.getImage("UnderConstruction.png", iconSize, iconSize)));
        path3Icon = new JLabel(new ImageIcon(DriverView.getImage("UnderConstruction.png", iconSize, iconSize)));
        towerIcon = new JLabel(new ImageIcon(DriverView.getImage("UnderConstruction.png", 60, 60)));

        path1Icon.setPreferredSize(new Dimension(iconSize, iconSize));
        path2Icon.setPreferredSize(new Dimension(iconSize, iconSize));
        path3Icon.setPreferredSize(new Dimension(iconSize, iconSize));

        headderlabel = new JLabel("");
        JPanel iconPanel = new JPanel();
        iconPanel.setOpaque(false);
        headderlabel.setHorizontalAlignment(SwingConstants.CENTER);
        towerHeaderPanel.add(headderlabel);
        towerIcon.setPreferredSize(new Dimension(60, 60));
        iconPanel.add(towerIcon);
        towerHeaderPanel.add(iconPanel);

        JPanel upgradeBar1 = new JPanel(new BorderLayout());
        JPanel upgradeBar2 = new JPanel(new BorderLayout());
        JPanel upgradeBar3 = new JPanel(new BorderLayout());
        upgradeBar1.setOpaque(false);
        upgradeBar2.setOpaque(false);
        upgradeBar3.setOpaque(false);

        path1Button.setIcon(new ImageIcon(DriverView.getImage("Upgrade Icon.png", iconSize, iconSize)));
        path2Button.setIcon(new ImageIcon(DriverView.getImage("Upgrade Icon.png", iconSize, iconSize)));
        path3Button.setIcon(new ImageIcon(DriverView.getImage("Upgrade Icon.png", iconSize, iconSize)));

        this.path1Text = new JLabel("");
        this.path2Text = new JLabel("");
        this.path3Text = new JLabel("");

        upgradeBar1.add(path1Icon, BorderLayout.WEST);
        upgradeBar1.add(this.path1Text, BorderLayout.CENTER);
        upgradeBar1.add(path1Button, BorderLayout.EAST);

        upgradeBar2.add(path2Icon, BorderLayout.WEST);
        upgradeBar2.add(this.path2Text, BorderLayout.CENTER);
        upgradeBar2.add(path2Button, BorderLayout.EAST);

        upgradeBar3.add(path3Icon, BorderLayout.WEST);
        upgradeBar3.add(this.path3Text, BorderLayout.CENTER);
        upgradeBar3.add(path3Button, BorderLayout.EAST);

        towerUpgradePanel.add(upgradeBar1);
        towerUpgradePanel.add(upgradeBar2);
        towerUpgradePanel.add(upgradeBar3);

        towerPanel.add(towerHeaderPanel, BorderLayout.CENTER);
        towerPanel.add(towerUpgradePanel, BorderLayout.SOUTH);
    }

    /**
     * panel for canceling or confirming
     * buying an upgrade or selling the tower
     */

    public void cancelConfirmPanel() {
        this.upgradeSellPanel.removeAll();
        JPanel header = new JPanel();
        JPanel value = new JPanel();
        header.setOpaque(false);
        value.setOpaque(false);

        ImagePanel sellIcon = new ImagePanel(DriverView.getImage("Gold Coin Icon.png", 20, 20), 5, 5);
        sellIcon.setPreferredSize(new Dimension(30, 30));

        if (this.model.getCancelConfirmOption() == 0) {
            JPanel emptyPanel1 = new JPanel();
            JPanel emptyPanel2 = new JPanel();
            JPanel emptyPanel3 = new JPanel();
            emptyPanel1.setOpaque(false);
            emptyPanel2.setOpaque(false);
            emptyPanel3.setOpaque(false);

            this.upgradeSellPanel.add(emptyPanel1);
            this.upgradeSellPanel.add(emptyPanel2);
            this.upgradeSellPanel.add(emptyPanel3);
            return;
        }

        if (this.model.getCancelConfirmOption() == 1) {
            ImagePanel upgradeIcon = new ImagePanel(DriverView.getImage(selectedTower.getPath1UpgradeIcon(), 30, 30), 0, 0);
            upgradeIcon.setPreferredSize(new Dimension(30, 30));
            JLabel headLabel = new JLabel(selectedTower.getPath1UpgradeName() + " => " + selectedTower.path1UpgradeValue());
            JLabel upgradeLabel = new JLabel("Upgrade cost: ");
            JLabel valueLabel = new JLabel(selectedTower.getPath1UpgradeCosts()[selectedTower.getPath1UpgradeLevel()] + "");

            header.add(upgradeIcon);
            header.add(headLabel);
            value.add(upgradeLabel);
            value.add(sellIcon);
            value.add(valueLabel);
        } else if (this.model.getCancelConfirmOption() == 2) {
            ImagePanel upgradeIcon = new ImagePanel(DriverView.getImage(selectedTower.getPath2UpgradeIcon(), 30, 30), 0, 0);
            upgradeIcon.setPreferredSize(new Dimension(30, 30));
            JLabel headLabel = new JLabel(selectedTower.getPath2UpgradeName() + " => " + selectedTower.path2UpgradeValue());
            JLabel upgradeLabel = new JLabel("Upgrade cost: ");
            JLabel valueLabel = new JLabel(selectedTower.getPath2UpgradeCosts()[selectedTower.getPath2UpgradeLevel()] + "");

            header.add(upgradeIcon);
            header.add(headLabel);
            value.add(upgradeLabel);
            value.add(sellIcon);
            value.add(valueLabel);
        } else if (this.model.getCancelConfirmOption() == 3) {
            ImagePanel upgradeIcon = new ImagePanel(DriverView.getImage(selectedTower.getPath3UpgradeIcon(), 30, 30), 0, 0);
            upgradeIcon.setPreferredSize(new Dimension(30, 30));
            JLabel headLabel = new JLabel(selectedTower.getPath3UpgradeName() + " => " + selectedTower.path3UpgradeValue());
            JLabel upgradeLabel = new JLabel("Upgrade cost: ");
            JLabel valueLabel = new JLabel(selectedTower.getPath3UpgradeCosts()[selectedTower.getPath3UpgradeLevel()] + "");

            header.add(upgradeIcon);
            header.add(headLabel);
            value.add(upgradeLabel);
            value.add(sellIcon);
            value.add(valueLabel);
        } else if (this.model.getCancelConfirmOption() == 4) {

            ImagePanel upgradeIcon = new ImagePanel(DriverView.getImage(selectedTower.getPath3UpgradeIcon(), 30, 30), 0, 0);
            upgradeIcon.setPreferredSize(new Dimension(30, 30));
            JLabel headLabel1 = new JLabel("Are you sure you want to");
            JLabel headLabel2 = new JLabel("sell the tower?");
            JLabel sellLabel = new JLabel("Sell value: ");
            JLabel valueLabel = new JLabel(selectedTower.getSellValue() + "");

            header.add(headLabel1);
            header.add(headLabel2);
            value.add(sellLabel);
            value.add(sellIcon);
            value.add(valueLabel);

        }

        if (this.selectedTower.getPath1UpgradeLevel() != 3 && this.model.getCancelConfirmOption() == 1) {
            if (this.selectedTower.getPath1UpgradeCosts()[this.selectedTower.getPath1UpgradeLevel()] > model.getMoney()) {
                this.confirmButton.setEnabled(false);
            } else {
                this.confirmButton.setEnabled(true);
            }
        }

        if (this.selectedTower.getPath2UpgradeLevel() != 3 && this.model.getCancelConfirmOption() == 2) {
            if (this.selectedTower.getPath2UpgradeCosts()[this.selectedTower.getPath2UpgradeLevel()] > model.getMoney()) {
                this.confirmButton.setEnabled(false);
            } else {
                this.confirmButton.setEnabled(true);
            }
        }

        if (this.selectedTower.getPath3UpgradeLevel() != 3 && this.model.getCancelConfirmOption() == 3) {
            if (this.selectedTower.getPath3UpgradeCosts()[this.selectedTower.getPath3UpgradeLevel()] > model.getMoney()) {
                this.confirmButton.setEnabled(false);
            } else {
                this.confirmButton.setEnabled(true);
            }
        }

        if (this.model.getCancelConfirmOption() == 4) {
            this.confirmButton.setEnabled(true);
        }

        JPanel buttonPanel = new JPanel();
        JPanel fillerPanel = new JPanel();
        buttonPanel.setOpaque(false);
        fillerPanel.setOpaque(false);
        fillerPanel.setPreferredSize(new Dimension(20, 30));

        buttonPanel.add(cancelButton);
        if (!Driver.isMac()) {
            buttonPanel.add(fillerPanel);
        }
        buttonPanel.add(confirmButton);

        JPanel newFillerPanel = new JPanel();
        newFillerPanel.setOpaque(false);
        this.upgradeSellPanel.add(newFillerPanel);
        this.upgradeSellPanel.add(header);
        this.upgradeSellPanel.add(value);
        this.upgradeSellPanel.add(buttonPanel);
    }

    /**
     * swaps out the cancel confirm button
     */

    public void swapPanels() {
        this.cancelConfirmPanel();
    }

    /**
     * returns the back button
     *
     * @return JButton
     */

    public JButton getBackButton() {
        return backButton;
    }

    /**
     * returns the sell button
     *
     * @return JButton
     */

    public JButton getSellButton() {
        return sellButton;
    }

    /**
     * returns the upgrade path 1 button
     *
     * @return JButton
     */

    public JButton getPath1Button() {
        return path1Button;
    }

    /**
     * returns the upgrade path 2 button
     *
     * @return JButton
     */

    public JButton getPath2Button() {
        return path2Button;
    }

    /**
     * returns the upgrade path 3 button
     *
     * @return JButton
     */

    public JButton getPath3Button() {
        return path3Button;
    }

    /**
     * returns the cancel button
     *
     * @return JButton
     */

    public JButton getCancelButton() {
        return cancelButton;
    }

    /**
     * returns the confirm button
     *
     * @return JButton
     */

    public JButton getConfirmButton() {
        return confirmButton;
    }

    /**
     * gets the currently selected tower
     *
     * @return Tower
     */

    public Tower getTower() {
        return this.selectedTower;
    }

    /**
     * sets a tower as the currently selected
     * tower
     *
     * @param tower
     */

    public void setTower(Tower tower) {
        this.selectedTower = tower;
    }

    /**
     * tells the model to update
     */

    public void needUpdateScreen() {
        this.model.setUpdateScreen(true);
    }

    /**
     * sets the model for the upgrade panel
     *
     * @param model
     */

    public void setModel(DriverModel model) {
        this.model = model;
        this.model.addActionListener(this);
    }

    /**
     * updates the information on the
     * upgraded attributes
     */

    public void updateInfo() {
        path1Icon.setIcon(new ImageIcon(DriverView.getImage(selectedTower.getPath1UpgradeIcon(), iconSize, iconSize)));
        path2Icon.setIcon(new ImageIcon(DriverView.getImage(selectedTower.getPath2UpgradeIcon(), iconSize, iconSize)));
        path3Icon.setIcon(new ImageIcon(DriverView.getImage(selectedTower.getPath3UpgradeIcon(), iconSize, iconSize)));
        towerIcon.setIcon(new ImageIcon(DriverView.resizeImage(selectedTower.getTowerBaseImage(), 60, 60)));

        this.headderlabel.setText(selectedTower.getName());
        this.path1Text.setText(selectedTower.getPath1UpgradeName() + ": " + selectedTower.path1CurrentValue());
        this.path2Text.setText(selectedTower.getPath2UpgradeName() + ": " + selectedTower.path2CurrentValue());
        this.path3Text.setText(selectedTower.getPath3UpgradeName() + ": " + selectedTower.path3CurrentValue());

        if (this.selectedTower.getPath1UpgradeLevel() == 3) {
            this.path1Button.setEnabled(false);
        } else {
            this.path1Button.setEnabled(true);
        }

        if (this.selectedTower.getPath2UpgradeLevel() == 3) {
            this.path2Button.setEnabled(false);
        } else {
            this.path2Button.setEnabled(true);
        }

        if (this.selectedTower.getPath3UpgradeLevel() == 3) {
            this.path3Button.setEnabled(false);
        } else {
            this.path3Button.setEnabled(true);
        }

        if (this.selectedTower.getPath1UpgradeLevel() != 3 && this.model.getCancelConfirmOption() == 1) {
            if (this.selectedTower.getPath1UpgradeCosts()[this.selectedTower.getPath1UpgradeLevel()] > model.getMoney()) {
                this.confirmButton.setEnabled(false);
            } else {
                this.confirmButton.setEnabled(true);
            }
        }

        if (this.selectedTower.getPath2UpgradeLevel() != 3 && this.model.getCancelConfirmOption() == 2) {
            if (this.selectedTower.getPath2UpgradeCosts()[this.selectedTower.getPath2UpgradeLevel()] > model.getMoney()) {
                this.confirmButton.setEnabled(false);
            } else {
                this.confirmButton.setEnabled(true);
            }
        }

        if (this.selectedTower.getPath3UpgradeLevel() != 3 && this.model.getCancelConfirmOption() == 3) {
            if (this.selectedTower.getPath3UpgradeCosts()[this.selectedTower.getPath3UpgradeLevel()] > model.getMoney()) {
                this.confirmButton.setEnabled(false);
            } else {
                this.confirmButton.setEnabled(true);
            }
        }
        this.repaint();
    }

    /**
     * updates the panel anytime something happens
     */

    public void actionPerformed(ActionEvent e) {
        if (this.selectedTower != null && this.model.isUpdateScreen()) {
            this.updateInfo();
            this.model.setUpdateScreen(false);
        }
    }
}
