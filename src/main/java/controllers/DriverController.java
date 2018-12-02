package controllers;

import mapdata.Map1Data;
import mobs.Mob;
import models.DriverModel;
import projectiles.Projectile;
import towers.Tower;
import towers.implementations.*;
import utilities.Position;
import views.DriverView;
import views.MapView;
import views.TalentPointAlert;
import views.TowerPanelView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Stores all the listeners for the driver view
 *
 * @author Scorpion
 */

public class DriverController {

    private static DriverModel model;
    private static DriverView view;
    private TDKeyboardListener keyboardListener = new TDKeyboardListener();
    private TDMouseMotionListener mouseMotionListener = new TDMouseMotionListener();
    private TDMouseListener mouseListener = new TDMouseListener();
    private TDMouseWheelListener mouseWheelListener = new TDMouseWheelListener();
    private TowerListener towerListener = new TowerListener();
    private TDItemListener itemListener = new TDItemListener();
    private ButtonMouseListener buttonMouseListener = new ButtonMouseListener();
    private ButtonMouseMotionListener buttonMouseMotionListener = new ButtonMouseMotionListener();

    /*
     *****************
     * Setup Methods *
     *****************
     */

    /**
     * sets the model field to the model
     *
     * @param model
     */

    public void setModel(final DriverModel model) {
        DriverController.model = model;
    }

    /**
     * sets the view field to the main view
     *
     * @param view
     */

    public void setView(final DriverView view) {

        if (view != null) {
            DriverController.view = view;
            addComponentEventListener();
        }

    }

    /*
     ************************
     * Title View Listeners *
     ************************
     */

    /**
     * build tower method that handles
     * both click and drag as well as
     * click and place
     */

    private void buildTower() {
        if (view.getMapView() == null || view.getMapView().getMousePosition() == null) {
            view.getSidePanelView().repaint();
            return;
        }

        if (view.getMapView().canBuild(new Position(view.getMapView().getMousePosition().getX(),
                view.getMapView().getMousePosition().getY()))) {
            Tower tower = null;
            Position towerPosition = new Position(
                    view.getMapView().getMousePosition().getX() - (TowerPanelView.TOWER_ICON_WIDTH) / 2,
                    view.getMapView().getMousePosition().getY() - (TowerPanelView.TOWER_ICON_HEIGHT) / 2);
                try {
                    tower = view.getSidePanelView().getTowerView().createSelectedTower(towerPosition, model);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }

                tower.addActionListener(towerListener);
                view.getMapView().addTower(tower);
                model.addTower(tower);
                view.getSidePanelView().getUpgradeView().setTower(tower);
                view.getSidePanelView().getUpgradeView().needUpdateScreen();
                model.setCancelConfirmOption(0);
                model.processEvent();

                if (model.allTowers() != null) {
                    for (Tower t : model.allTowers()) {
                        t.setShowRange(false);
                    }
                }

                view.getSidePanelView().getTowerView().getBuildButtonGroup().clearSelection();
            }

            view.getMapView().repaint();
        }

        /*
         ***********************
         * Main View Listeners *
         ***********************
         */

        /**
         * adds the main driver view listeners
         * to the main frame at the start
         */

        private void addComponentEventListener () {
            view.addKeyListener(keyboardListener);
            view.addMouseWheelListener(mouseWheelListener);

            view.getMainView().getMap1().addActionListener(new ButtonUpdateListener());
            view.getMainView().getMap2().addActionListener(new ButtonUpdateListener());
            view.getMainView().getMap3().addActionListener(new ButtonUpdateListener());
            view.getMainView().getEasyButton().addActionListener(new ButtonUpdateListener());
            view.getMainView().getMediumButton().addActionListener(new ButtonUpdateListener());
            view.getMainView().getHardButton().addActionListener(new ButtonUpdateListener());
            view.getMainView().getExitButton().addActionListener(new ExitListener());
            view.getMainView().getInfoButton().addActionListener(new InfoListener());
            view.getMainView().getStartButton().addActionListener(new MapViewButtonListener());
            view.getMainView().getStartButton().addActionListener(new DifficultyListener());

            view.getTalentTreeMenuItem().addActionListener(new TalentTreeListener());//temp

            view.getSidePanelView().getTowerView().getBuildButtonGroup().addItemListenerToAll(itemListener);
            view.getSidePanelView().getTowerView().getBuildButtonGroup().addMouseListenerToAll(buttonMouseListener);
            view.getSidePanelView().getTowerView().getBuildButtonGroup().addMouseMotionListenerToAll(buttonMouseMotionListener);
            view.getSidePanelView().getButtonView().getStartButton().addActionListener(new StartListener());
            view.getSidePanelView().getButtonView().getTalentTreeButton().addActionListener(new TalentTreeListener());
            view.getSidePanelView().getTalentView().getBack().addActionListener(new TalentTreeListener());
            view.getSidePanelView().getTalentView().getAccept().addActionListener(new TalentPointListener());
            view.getSidePanelView().getButtonView().getHomeButton().addActionListener(new MenuListener());
            view.getSidePanelView().getUpgradeView().getBackButton().addActionListener(new ReturnListener());
            view.getSidePanelView().getUpgradeView().getPath1Button().addActionListener(new UpgradeListener());
            view.getSidePanelView().getUpgradeView().getPath2Button().addActionListener(new UpgradeListener());
            view.getSidePanelView().getUpgradeView().getPath3Button().addActionListener(new UpgradeListener());
            view.getSidePanelView().getUpgradeView().getSellButton().addActionListener(new UpgradeListener());
            view.getSidePanelView().getUpgradeView().getCancelButton().addActionListener(new CancelListener());
            view.getSidePanelView().getUpgradeView().getConfirmButton().addActionListener(new ConfirmListener());
        }

        /**
         * keyboard listener for the driver view
         *
         * @author Scorpion
         */

        private class TDKeyboardListener implements KeyListener {
            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                DriverController.model.keyboardHandler();
            }

            public void keyTyped(KeyEvent e) {
            }
        }

        /**
         * updates the start button when
         * a map and difficulty are selected
         *
         * @author Scorpion
         */

        private class ButtonUpdateListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {

                if (view.getMainView().getMap2().isSelected()) {
                    view.getMainView().getMap2().setBorder(view.getMainView().getSelectionBorder());

                } else {
                    view.getMainView().getMap2().setBorder(view.getMainView().getDeSelectionBorder());
                }

                if (view.getMainView().getMap3().isSelected()) {
                    view.getMainView().getMap3().setBorder(view.getMainView().getSelectionBorder());

                } else {
                    view.getMainView().getMap3().setBorder(view.getMainView().getDeSelectionBorder());
                }

                if (view.getMainView().getEasyButton().isSelected()) {
                    view.getMainView().getEasyButton().setBorder(view.getMainView().getSelectionBorder());
                } else {
                    view.getMainView().getEasyButton().setBorder(view.getMainView().getDeSelectionBorder());
                }

                if (view.getMainView().getMediumButton().isSelected()) {
                    view.getMainView().getMediumButton().setBorder(view.getMainView().getSelectionBorder());
                } else {
                    view.getMainView().getMediumButton().setBorder(view.getMainView().getDeSelectionBorder());
                }

                if (view.getMainView().getHardButton().isSelected()) {
                    view.getMainView().getHardButton().setBorder(view.getMainView().getSelectionBorder());
                } else {
                    view.getMainView().getHardButton().setBorder(view.getMainView().getDeSelectionBorder());
                }

            }
        }

        /**
         * sets the difficulty and map
         * of the model
         *
         * @author Scorpion
         */

        private class DifficultyListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {

                if (view.getMainView().getEasyButton().isSelected() == true) {
                    model.setDifficulty(1);
                } else if (view.getMainView().getMediumButton().isSelected() == true) {
                    model.setDifficulty(2);
                } else if (view.getMainView().getHardButton().isSelected() == true) {
                    model.setDifficulty(3);
                }

                if (view.getMainView().getMap1().isSelected() == true) {
                    model.setMapData(new Map1Data(model, model.getDifficulty()));
                } else if (view.getMainView().getMap2().isSelected() == true) {
                    // model.setMapData(new Map2Data(model.getDifficulty()));
                } else if (view.getMainView().getMap3().isSelected() == true) {
                    // model.setMapData(new Map3Data(model.getDifficulty()));
                }

            }
        }

        /**
         * listens for when the main view start
         * button has been selected and then
         * moves the user from the main view to
         * the map view. Adds necessary listeners
         * during the construction of the map view
         *
         * @author Scorpion
         */

        private class MapViewButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent arg0) {
                view.getSidePanelView().getInfoView().setModel(model);
                view.getSidePanelView().getUpgradeView().setModel(model);
                view.getSidePanelView().getInfoView().setText();

                view.setMap(new MapView("testTrack.png", model, view));

                view.getMapView().getSkipButton().addActionListener(new SkipButtonListener());
                view.getMapView().getBackButton().addActionListener(new BackButtonListener());
                view.getMapView().getContinueButton().addActionListener(new ContinueButtonListener());

                if (view.isShownStartingTutorial()) {
                    view.getMapView().getSkipButton().setVisible(false);
                    view.getMapView().getBackButton().setVisible(false);
                    view.getMapView().getContinueButton().setVisible(false);
                }

                view.getTalentTreeView().addListenerToAll(new TalentTreeActiveAcceptButtonListener());
                view.getMapView().addMouseMotionListener(mouseMotionListener);
                view.getMapView().addMouseListener(mouseListener);
                view.getMapView().addKeyListener(keyboardListener);
                view.getTalentTreeView().addListenerToAll(new TalentTreePanelListener());
            }
        }

        /**
         * listens for if the exit
         * button is pressed and brings
         * up the confirmation panel
         *
         * @author Scorpion
         */

        private class ExitListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                        "User Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        }

        /**
         * Listener for building tower on drag
         */
        private class ButtonMouseListener extends MouseAdapter {
            public void mouseReleased(MouseEvent e) {
                if (view.getMapView().getCursor().getName().endsWith("Dragged")) {
                    buildTower();
                }
                view.getMapView().repaint();
            }
        }

        /**
         * listens for updates to talent points to
         * activate the talent buttons
         *
         * @author Scorpion
         */

        /*
         *********************
         * MapView Listeners *
         *********************
         */

        private class TalentTreeActiveAcceptButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (model.getTalentPoints() > 0) {
                    view.getSidePanelView().getTalentView().getAccept().setEnabled(true);
                } else {
                    view.getSidePanelView().getTalentView().getAccept().setEnabled(false);
                }
            }
        }

        /**
         * listens for clicks on the talent tree
         * upgrade buttons to see if we have enough
         * points for the talent. If so, activates the
         * accept button.
         *
         * @author Scorpion
         */

        private class TalentTreePanelListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {

                // each if checks for which buttons are selected
                if (view.getTalentTreeView().getIncreaseDamage().isSelected()) {
                    view.getSidePanelView().switchToTalentPanel();
                    view.getSidePanelView().getTalentView().setTalentInfo(DriverView.getImage("IncreaseDamage.png", 50, 50),
                            "Increased Damage", "Increases the damage", "of all main.towers.");
                }

                if (view.getTalentTreeView().getIncreaseFireRate().isSelected()) {
                    view.getSidePanelView().switchToTalentPanel();
                    view.getSidePanelView().getTalentView().setTalentInfo(DriverView.getImage("FireRate.png", 50, 50),
                            "Increased Fire Rate", "Increases the fire rate", "of all main.towers.");

                }

                if (view.getTalentTreeView().getIncreaseRange().isSelected()) {
                    view.getSidePanelView().switchToTalentPanel();
                    view.getSidePanelView().getTalentView().setTalentInfo(DriverView.getImage("Range.png", 50, 50),
                            "Increased Range", "Increases the range", "of all main.towers.");
                }

                if (view.getTalentTreeView().getIncreaseGoldLevel().isSelected()) {
                    view.getSidePanelView().switchToTalentPanel();
                    view.getSidePanelView().getTalentView().setTalentInfo(DriverView.getImage("EndLevelGold.png", 50, 50),
                            "Increase Level Gold", "Each time you complete a level", "you will gain additional gold.");
                }

                if (view.getTalentTreeView().getIncreaseGoldEnemy().isSelected()) {
                    view.getSidePanelView().switchToTalentPanel();
                    view.getSidePanelView().getTalentView().setTalentInfo(DriverView.getImage("MobGold.png", 50, 50),
                            "Increased Enemy Gold", "Each time you kill an enemy", "you will gain additional gold.");
                }

                if (view.getTalentTreeView().getReduceGoldTower().isSelected()) {
                    view.getSidePanelView().switchToTalentPanel();
                    view.getSidePanelView().getTalentView().setTalentInfo(DriverView.getImage("TowerGold.png", 50, 50),
                            "Reduced Tower Cost", "Each tower you place", "will cost less gold.");
                }

                if (view.getTalentTreeView().getChainLightning().isSelected()) {
                    view.getSidePanelView().switchToTalentPanel();
                    view.getSidePanelView().getTalentView().setTalentInfo(DriverView.getImage("ChainLightning.png", 50, 50),
                            "Chain Lightning", "Makes your lightning main.towers chain", "their lightning between enemies");
                }

                if (view.getTalentTreeView().getDamageOverTime().isSelected()) {
                    view.getSidePanelView().switchToTalentPanel();
                    view.getSidePanelView().getTalentView().setTalentInfo(DriverView.getImage("FieryClock.png", 50, 50),
                            "Damage Over Time", "Makes your fire main.towers cause", "additional damage over time.");
                }

                if (view.getTalentTreeView().getFrostDamage().isSelected()) {
                    view.getSidePanelView().switchToTalentPanel();
                    view.getSidePanelView().getTalentView().setTalentInfo(DriverView.getImage("IceDamage.png", 50, 50),
                            "Ice Damage", "All frost main.towers will", "now do damage.");
                }

                if (view.getTalentTreeView().getPiercingShotTower().isSelected()) {
                    view.getSidePanelView().switchToTalentPanel();
                    view.getSidePanelView().getTalentView().setTalentInfo(DriverView.getImage("DenseLightningTower.png", 50, 50),
                            "Dense Lightning Tower", "Lightning tower that shoots", "lightning bolts through enemies.");
                }

                if (view.getTalentTreeView().getPatchOfFireTower().isSelected()) {
                    view.getSidePanelView().switchToTalentPanel();
                    view.getSidePanelView().getTalentView().setTalentInfo(DriverView.getImage("PatchOfFireTower.png", 50, 50),
                            "Patch Of Fire Tower", "Fire tower that shoots fire.", "Damages enemies who walk over it.");
                }

                if (view.getTalentTreeView().getStoppingTower().isSelected()) {
                    view.getSidePanelView().switchToTalentPanel();
                    view.getSidePanelView().getTalentView().setTalentInfo(DriverView.getImage("FreezeTower.png", 50, 50),
                            "Freeze Tower", "Frost tower that temporarily", "stops nearby enemies.");
                }

                if (view.getTalentTreeView().getMultiShotTower().isSelected()) {
                    view.getSidePanelView().switchToTalentPanel();
                    view.getSidePanelView().getTalentView().setTalentInfo(DriverView.getImage("TeslaTower.png", 50, 50),
                            "Tesla Tower", "Lightning tower that shoots", "multiple shots at the same time.");
                }

                if (view.getTalentTreeView().getRangedExplosionTower().isSelected()) {
                    view.getSidePanelView().switchToTalentPanel();
                    view.getSidePanelView().getTalentView().setTalentInfo(DriverView.getImage("FireBombTower.png", 50, 50),
                            "Fire Bomb Tower", "Fire Tower that shoots area", "of effect damage at enemies.");
                }

                if (view.getTalentTreeView().getIcicleTower().isSelected()) {
                    view.getSidePanelView().switchToTalentPanel();
                    view.getSidePanelView().getTalentView().setTalentInfo(DriverView.getImage("IcicleTower.png", 50, 50),
                            "Icicle Tower", "I have no idea what", "the icicle tower does.");
                }
            }
        }

        /**
         * listens to see if we have talent
         * points and which talents have already
         * been acquired to see which talents
         * should be active
         *
         * @author Scorpion
         */

        private class TalentPointListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {

                int tierDepth = 0;

                if (view.getTalentTreeView().getIncreaseDamage().isSelected()) {
                    Projectile.upgradeDamageBoost();
                    model.setButtonSelected(0, 0);
                    tierDepth = 1;
                }

                if (view.getTalentTreeView().getIncreaseFireRate().isSelected()) {
                    Tower.upgradeFireRateBoost();
                    model.setButtonSelected(0, 1);
                    tierDepth = 1;
                }

                if (view.getTalentTreeView().getIncreaseRange().isSelected()) {
                    Tower.upgradeRangeBoost();
                    Projectile.upgradeRangeBoost();
                    model.setButtonSelected(0, 2);
                    tierDepth = 1;
                }

                if (view.getTalentTreeView().getIncreaseGoldLevel().isSelected()) {
                    DriverModel.upgradeEndOfLevelMoneyBoost();
                    model.setButtonSelected(1, 0);
                    tierDepth = 2;
                }

                if (view.getTalentTreeView().getIncreaseGoldEnemy().isSelected()) {
                    Mob.upgradeMobValueBoost();
                    model.setButtonSelected(1, 1);
                    tierDepth = 2;
                }

                if (view.getTalentTreeView().getReduceGoldTower().isSelected()) {
                    Tower.upgradeTowerCostDecrease();
                    model.setButtonSelected(1, 2);
                    tierDepth = 2;
                }

                if (view.getTalentTreeView().getChainLightning().isSelected()) {
                    Tower.upgradeChainLightning();
                    model.setButtonSelected(2, 0);
                    tierDepth = 3;
                }

                if (view.getTalentTreeView().getDamageOverTime().isSelected()) {
                    Tower.upgradeFireDOTDamage();
                    model.setButtonSelected(2, 1);
                    tierDepth = 3;
                }

                if (view.getTalentTreeView().getFrostDamage().isSelected()) {
                    Tower.upgradeIceDamage();
                    model.setButtonSelected(2, 2);
                    tierDepth = 3;
                }

                if (view.getTalentTreeView().getPiercingShotTower().isSelected()) {
                    DenseLightningTower.unlockTower();
                    model.setButtonSelected(3, 0);
                    tierDepth = 4;
                }

                if (view.getTalentTreeView().getPatchOfFireTower().isSelected()) {
                    PatchOfFireTower.unlockTower();
                    model.setButtonSelected(3, 1);
                    tierDepth = 4;
                }

                if (view.getTalentTreeView().getStoppingTower().isSelected()) {
                    FreezeTower.unlockTower();
                    model.setButtonSelected(3, 2);
                    tierDepth = 4;
                }

                if (view.getTalentTreeView().getMultiShotTower().isSelected()) {
                    TeslaTower.unlockTower();
                    model.setButtonSelected(4, 0);
                    tierDepth = 5;
                }

                if (view.getTalentTreeView().getRangedExplosionTower().isSelected()) {
                    FireBombTower.unlockTower();
                    model.setButtonSelected(4, 1);
                    tierDepth = 5;
                }

                if (view.getTalentTreeView().getIcicleTower().isSelected()) {
                    IcicleTower.unlockTower();
                    model.setButtonSelected(4, 2);
                    tierDepth = 5;
                }

                if (view.getTalentTreeView().getLightningMine().isSelected()) {
                    // Lightning Mine
                    model.setButtonSelected(5, 0);
                    tierDepth = 6;
                }

                if (view.getTalentTreeView().getFireMine().isSelected()) {
                    // Fire Mine
                    model.setButtonSelected(5, 1);
                    tierDepth = 6;
                }

                if (view.getTalentTreeView().getFrostMine().isSelected()) {
                    // Frost Min
                    model.setButtonSelected(5, 2);
                    tierDepth = 6;
                }

                if (view.getTalentTreeView().getElementalTower().isSelected()) {
                    // Elemental Tower
                    model.setButtonSelected(6, 0);
                    tierDepth = 7;
                }

                if (view.getTalentTreeView().getBoostTower().isSelected()) {
                    // Boost Tower
                    model.setButtonSelected(6, 1);
                    tierDepth = 7;
                }

                if (view.getTalentTreeView().getMoneyTower().isSelected()) {
                    // Money Tower
                    model.setButtonSelected(6, 2);
                    tierDepth = 7;
                }

                if (model.getTierDepth() < tierDepth) {
                    model.setTierDepth(tierDepth);
                }

                model.removedTalentPoints(1);
                if (model.getTalentPoints() == 0) {
                    view.getTalentTreeView().disableButtons();
                } else {
                    view.getTalentTreeView().enableButtons(model.getTierDepth(), model.getDisabledButtonArray());
                }
                view.getSidePanelView().getTalentView().getAccept().setEnabled(false);
            }
        }

        /*
         **********************
         * Map View Listeners *
         **********************
         */

        /**
         * listens for the talent tree toggling
         * the talent between visible and invisible.
         *
         * @author Scorpion
         */

        private class TalentTreeListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                view.triggerTalentTree();
            }
        }

        /**
         * listens for the x-button on the tutorial
         * and updates the view
         *
         * @author Scorpion
         */

        private class SkipButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent arg0) {
                view.getMapView().activateButtons(false);
                view.getMapView().repaint();
                view.getSidePanelView().getButtonView().getStartButton().setEnabled(true);
                view.getSidePanelView().getTowerView().enableTowerButtons();
                view.markTutorialRead();
                view.getMapView().hideTutorial();
                view.getMapView().showTowers(true);
                model.processEvent();

                if (model.getLevel() != 1) {
                    view.getSidePanelView().getButtonView().getTalentTreeButton().setEnabled(true);
                }
                view.getMapView().repaint();
            }
        }

        /**
         * listens for the back button on
         * the tutorial and goes to the
         * previous slide
         *
         * @author Scorpion
         */

        private class BackButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent arg0) {
                view.getMapView().stepBackwardsInTutorial();
            }
        }

        /*
         ************************
         * Side Panel Listeners *
         ************************
         */

        /**
         * listens for the forwards button on
         * the tutorial and goes to the
         * next slide
         *
         * @author Scorpion
         */

        private class ContinueButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent arg0) {
                view.getMapView().stepForwardsInTutorial();
            }
        }

        /**
         * listens for if the user clicks
         * a tower so it can bring up the
         * upgrade panel
         *
         * @author Scorpion
         */

        private class TowerListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                view.getSidePanelView().getUpgradeView().setTower((Tower) e.getSource());
                view.getSidePanelView().getUpgradeView().needUpdateScreen();
                model.setCancelConfirmOption(0);
                view.setUpgradePanel();
                model.processEvent();

                if (model.allTowers() != null) {
                    for (Tower t : model.allTowers()) {
                        t.setShowRange(false);
                    }
                }
                ((Tower) e.getSource()).setShowRange(true);

                view.getMapView().repaint();
            }
        }

        /**
         * toggle button listener for when the user
         * clicks on a tower
         *
         * @author Scorpion
         */

        private class TDItemListener implements ItemListener {
            public void itemStateChanged(ItemEvent ev) {
                boolean haveTower = false;
                if (ev.getStateChange() == ItemEvent.SELECTED) {
                    switch (view.getSidePanelView().getTowerView().getBuildButtonGroup().getSelectedButton().getName()) {
                        case "DamageTower":
                            haveTower = LightningTower.clickedTower(view);
                            break;
                        case "FireTower":
                            haveTower = FireTower.clickedTower(view);
                            break;
                        case "IceTower":
                            haveTower = IceTower.clickedTower(view);
                            break;
                        case "DenseLightningTower":
                            haveTower = DenseLightningTower.clickedTower(view);
                            break;
                        case "PatchOfFireTower":
                            haveTower = PatchOfFireTower.clickedTower(view);
                            break;
                        case "FreezeTower":
                            haveTower = FreezeTower.clickedTower(view);
                            break;
                        case "TeslaTower":
                            haveTower = TeslaTower.clickedTower(view);
                            break;
                        case "FireBombTower":
                            haveTower = FireBombTower.clickedTower(view);
                            break;
                        case "IcicleTower":
                            haveTower = IcicleTower.clickedTower(view);
                            break;
                        default:
                            return;
                    }
                }

                // handles when the user clicks to place the tower
                if (haveTower) {
                    BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Cursor c = toolkit.createCustomCursor(cursorImg, new Point(0, 0), "tower");
                    view.getMapView().setCursor(c);
                    view.getMapView().repaint();
                } else {
                    view.getSidePanelView().getTowerView().getBuildButtonGroup().clearSelection();
                    view.getMapView().setDefaultCursor();
                }

                if (ev.getStateChange() == ItemEvent.DESELECTED) {
                    view.getMapView().setDefaultCursor();
                    view.getMapView().repaint();
                }
            }
        }

        /**
         * listener for mouse motion to repaint the map
         *
         * @author Scorpion
         */

        private class TDMouseMotionListener extends MouseMotionAdapter {
            public void mouseMoved(MouseEvent e) {
                view.getMapView().repaint();
            }
        }

        /**
         * listens for when the user attempts to click
         * and drag the tower
         *
         * @author Scorpion
         */

        private class ButtonMouseMotionListener extends MouseMotionAdapter {
            public void mouseDragged(MouseEvent e) {
                if (((AbstractButton) e.getSource()).isEnabled() && !view.getMapView().getCursor().getName().equals("towerDragged")) {
                    ((AbstractButton) e.getSource()).setSelected(true);
                    BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Cursor c = toolkit.createCustomCursor(cursorImg, new Point(0, 0), "towerDragged");
                    view.getMapView().setCursor(c);
                }

                view.getMapView().repaint();
            }
        }

        /**
         * Listener for building tower on click
         */

        private class TDMouseListener extends MouseAdapter {
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (view.getMapView().getCursor().getName().endsWith("tower") ||
                            view.getMapView().getCursor().getName().endsWith("Dragged")) {
                        buildTower();
                    } else {
                        view.getSidePanelView().switchToTowerPanel();
                        if (model.allTowers() != null) {
                            for (Tower t : model.allTowers()) {
                                t.setShowRange(false);
                            }
                        }
                        view.getSidePanelView().repaint();
                    }
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    view.getSidePanelView().getTowerView().getBuildButtonGroup().clearSelection();
                }
                view.getMapView().repaint();
            }
        }

        /**
         * Listens for the start button/
         * speedup slow down button to be
         * pressed and adjusts the game and
         * the button accordingly
         *
         * @author Scorpion
         */

        private class StartListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (model.getTalentPoints() > 0 && !model.getRuntimeThread().isAlive()) {
                    new TalentPointAlert(model, view);
                } else {
                    if (!model.getRuntimeThread().isAlive()) {
                        view.getSidePanelView().getButtonView().getStartButton().setToggleImage();
                        model.setStartButtonState(view.getSidePanelView().getButtonView().getStartButton().getButtonState());
                    } else if (model.isNormalSpeed()) {
                        view.getSidePanelView().getButtonView().getStartButton().setImage2();
                        model.setStartButtonState(view.getSidePanelView().getButtonView().getStartButton().getButtonState());
                        model.setIsNormalSpeed(false);
                    } else {
                        view.getSidePanelView().getButtonView().getStartButton().setImage3();
                        model.setStartButtonState(view.getSidePanelView().getButtonView().getStartButton().getButtonState());
                        model.setIsNormalSpeed(true);
                    }
                    model.gameRuntimeLoop();
                }
            }
        }

        /**
         * Listens for if the home button
         * is clicked and pops up the pause
         * menu
         *
         * @author Scorpion
         */

        private class MenuListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                model.getRuntimeThread().setSuspended(true);
                model.setCancelConfirmOption(0);
                view.getMapView().showTowers(false);
                view.getMapView().repaint();
                if (model.allTowers() != null) {
                    for (Tower t : model.allTowers()) {
                        t.setShowRange(false);
                    }
                }

                // pause menu
                Object[] options = {"Resume", "Restart Game", "Main Menu", "Quit Game"};
                int selection = JOptionPane.showOptionDialog(null, "Game Paused", "Select an option.",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                switch (selection) {

                    case 0:
                        model.getRuntimeThread().setSuspended(false);
                        break;
                    case 1:
                        model.getRuntimeThread().restart();
                        model.getRuntimeThread().setSuspended(false);
                        model.setIsNormalSpeed(false);
                        model.resetValues();
                        model.processEvent();
                        view.getMapView().repaint();
                        view.getSidePanelView().getButtonView().getStartButton().setImage1();
                        view.getSidePanelView().getButtonView().getStartButton().resetToggle();
                        view.getMapView().clearTowers();
                        view.getMapView().showTowers(true);
                        break;
                    case 2:
                        model.getRuntimeThread().endGame();
                        model.getRuntimeThread().setSuspended(false);
                        model.setIsNormalSpeed(false);
                        model.resetValues();
                        view.getSidePanelView().getButtonView().getStartButton().setImage1();
                        view.getSidePanelView().getButtonView().getStartButton().resetToggle();
                        view.getMapView().clearTowers();
                        view.getMapView().showTowers(true);
                        view.switchToMainScreen();
                        break;
                    case 3:
                        System.exit(0);
                        break;
                }

                view.getSidePanelView().repaint();
                view.getMapView().repaint();
            }
        }

        /**
         * listens for if the back button is
         * pressed in the upgrade panel and
         * brings back the tower panel
         *
         * @author Scorpion
         */

        private class ReturnListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (model.allTowers() != null) {
                    for (Tower t : model.allTowers()) {
                        t.setShowRange(false);
                    }
                }
                view.setTowerPanel();
                view.getMapView().repaint();
            }
        }

        /**
         * listens for if an upgrade button
         * or the sell button is pressed in
         * the upgrade panel and brings up
         * confirmation panel for each
         *
         * @author Scorpion
         */

        private class UpgradeListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == view.getSidePanelView().getUpgradeView().getPath1Button()) {
                    model.setCancelConfirmOption(1);
                }

                if (e.getSource() == view.getSidePanelView().getUpgradeView().getPath2Button()) {
                    model.setCancelConfirmOption(2);
                }

                if (e.getSource() == view.getSidePanelView().getUpgradeView().getPath3Button()) {
                    model.setCancelConfirmOption(3);
                }

                if (e.getSource() == view.getSidePanelView().getUpgradeView().getSellButton()) {
                    model.setCancelConfirmOption(4);
                }

                view.getSidePanelView().getUpgradeView().needUpdateScreen();
                view.setUpgradePanel();
            }
        }

        /**
         * listens for if the confirm
         * option is selected in the
         * confirmation panel in the
         * update panel
         *
         * @author Scorpion
         */

        private class ConfirmListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (model.getCancelConfirmOption() == 1) {
                    view.getSidePanelView().getUpgradeView().getTower().upgradePath1(model);
                    view.getMapView().repaint();
                }

                if (model.getCancelConfirmOption() == 2) {
                    view.getSidePanelView().getUpgradeView().getTower().upgradePath2(model);
                    view.getMapView().repaint();
                }

                if (model.getCancelConfirmOption() == 3) {
                    view.getSidePanelView().getUpgradeView().getTower().upgradePath3(model);
                    view.getMapView().repaint();
                }

                if (model.getCancelConfirmOption() == 4) {
                    model.towerSellMoney(view.getSidePanelView().getUpgradeView().getTower().getSellValue());
                    view.getMapView().removeTower(view.getSidePanelView().getUpgradeView().getTower());
                    model.removeTower(view.getSidePanelView().getUpgradeView().getTower());
                    view.getMapView().repaint();
                }

                view.getSidePanelView().getUpgradeView().needUpdateScreen();
                model.setCancelConfirmOption(0);
                model.processEvent();
                view.setUpgradePanel();
            }
        }

        /**
         * listens for if the cancel
         * option is selected in the
         * confirmation panel in the
         * update panel
         *
         * @author Scorpion
         */

        private class CancelListener implements ActionListener {
            public void actionPerformed(ActionEvent arg0) {
                model.setCancelConfirmOption(0);
                model.processEvent();
                view.setUpgradePanel();
            }
        }

        /**
         * unused
         *
         * @author Scorpion
         */

        private class TDMouseWheelListener implements MouseWheelListener {
            public void mouseWheelMoved(MouseWheelEvent e) {
            }
        }
    }
