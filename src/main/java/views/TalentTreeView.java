package views;

import models.DriverModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The talent tree in our tower defense game
 *
 * @author Scorpion
 */

public class TalentTreeView extends JPanel implements ActionListener {

    public static final int BUTTON_HEIGHT = 50;
    public static final int BUTTON_WIDTH = 50;
    private static final long serialVersionUID = 1L;
    private static boolean disableTierAfterSelection = false;
    private GridBagLayout gbLayout = new GridBagLayout();
    private DriverModel model;

    private JPanel tier1 = new JPanel(gbLayout);
    private JPanel tier2 = new JPanel(gbLayout);
    private JPanel tier3 = new JPanel(gbLayout);
    private JPanel tier4 = new JPanel(gbLayout);
    private JPanel tier5 = new JPanel(gbLayout);
    private JPanel tier6 = new JPanel(gbLayout);
    private JPanel tier7 = new JPanel(gbLayout);
    private JPanel acceptPanel = new JPanel();

    private JToggleButton increaseDamage = new JToggleButton("", false);
    private JToggleButton increaseFireRate = new JToggleButton("", false);
    private JToggleButton increaseRange = new JToggleButton("", false);

    private JToggleButton increaseGoldLevel = new JToggleButton("", false);
    private JToggleButton increaseGoldEnemy = new JToggleButton("", false);
    private JToggleButton reduceGoldTower = new JToggleButton("", false);

    private JToggleButton chainLightning = new JToggleButton("", false);
    private JToggleButton damageOverTime = new JToggleButton("", false);
    private JToggleButton frostDamage = new JToggleButton("", false);

    private JToggleButton piercingShotTower = new JToggleButton();
    private JToggleButton patchOfFireTower = new JToggleButton("", false);
    private JToggleButton stoppingTower = new JToggleButton("", false);

    private JToggleButton multiShotTower = new JToggleButton("", false);
    private JToggleButton rangedExplosionTower = new JToggleButton("", false);
    private JToggleButton icicleTower = new JToggleButton("", false);

    private JToggleButton lightningMine = new JToggleButton("", false);
    private JToggleButton fireMine = new JToggleButton("", false);
    private JToggleButton frostMine = new JToggleButton("", false);

    private JToggleButton elementalTower = new JToggleButton("", false);
    private JToggleButton boostTower = new JToggleButton("", false);
    private JToggleButton moneyTower = new JToggleButton("", false);

    private JButton accept = new JButton("Accept");

    /**
     * Constructor for the Talent Tree View.
     */

    public TalentTreeView(final DriverModel model) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.model = model;
        this.model.addActionListener(this);
        this.setOpaque(false);

        tier1.setOpaque(false);
        tier2.setOpaque(false);
        tier3.setOpaque(false);
        tier4.setOpaque(false);
        tier5.setOpaque(false);
        tier6.setOpaque(false);
        tier7.setOpaque(false);
        acceptPanel.setOpaque(false);

        add(tier1);
        add(tier2);
        add(tier3);
        add(tier4);
        add(tier5);
        add(tier6);
        add(tier7);
        add(acceptPanel);

        addIcons();

        if (disableTierAfterSelection) {
            groupButtonsInTiers();
        } else {
            groupButtonsTogether();
        }
        addButtons();
        setToolTips();
    }

    /**
     * Creates button groups for the three buttons in each tier. This is so that only button button in each tier can be selected.
     */

    public void groupButtonsInTiers() {

        ButtonGroup t1 = new ButtonGroup();
        ButtonGroup t2 = new ButtonGroup();
        ButtonGroup t3 = new ButtonGroup();
        ButtonGroup t4 = new ButtonGroup();
        ButtonGroup t5 = new ButtonGroup();
        ButtonGroup t6 = new ButtonGroup();
        ButtonGroup t7 = new ButtonGroup();

        t1.add(increaseDamage);
        t1.add(increaseFireRate);
        t1.add(increaseRange);

        t2.add(increaseGoldLevel);
        t2.add(increaseGoldEnemy);
        t2.add(reduceGoldTower);

        t3.add(chainLightning);
        t3.add(damageOverTime);
        t3.add(frostDamage);

        t4.add(piercingShotTower);
        t4.add(patchOfFireTower);
        t4.add(stoppingTower);

        t5.add(multiShotTower);
        t5.add(rangedExplosionTower);
        t5.add(icicleTower);

        t6.add(lightningMine);
        t6.add(fireMine);
        t6.add(frostMine);

        t7.add(elementalTower);
        t7.add(boostTower);
        t7.add(moneyTower);
    }

    /**
     * Adds all of the buttons to a single button group.
     */

    public void groupButtonsTogether() {

        ButtonGroup bg = new ButtonGroup();

        bg.add(increaseDamage);
        bg.add(increaseFireRate);
        bg.add(increaseRange);

        bg.add(increaseGoldLevel);
        bg.add(increaseGoldEnemy);
        bg.add(reduceGoldTower);

        bg.add(chainLightning);
        bg.add(damageOverTime);
        bg.add(frostDamage);

        bg.add(piercingShotTower);
        bg.add(patchOfFireTower);
        bg.add(stoppingTower);

        bg.add(multiShotTower);
        bg.add(rangedExplosionTower);
        bg.add(icicleTower);

        bg.add(lightningMine);
        bg.add(fireMine);
        bg.add(frostMine);

        bg.add(elementalTower);
        bg.add(boostTower);
        bg.add(moneyTower);
    }

    /**
     * Setting the icons for
     */

    public void addIcons() {
        increaseDamage.setIcon(new ImageIcon(DriverView.getImage("IncreaseDamage.png", BUTTON_WIDTH, BUTTON_WIDTH)));
        increaseFireRate.setIcon(new ImageIcon(DriverView.getImage("FireRate.png", BUTTON_WIDTH, BUTTON_WIDTH)));
        increaseRange.setIcon(new ImageIcon(DriverView.getImage("Range.png", BUTTON_WIDTH, BUTTON_WIDTH)));
        increaseGoldLevel.setIcon(new ImageIcon(DriverView.getImage("EndLevelGold.png", BUTTON_WIDTH, BUTTON_WIDTH)));
        increaseGoldEnemy.setIcon(new ImageIcon(DriverView.getImage("MobGold.png", BUTTON_WIDTH, BUTTON_WIDTH)));
        reduceGoldTower.setIcon(new ImageIcon(DriverView.getImage("TowerGold.png", BUTTON_WIDTH, BUTTON_WIDTH)));
        chainLightning.setIcon(new ImageIcon(DriverView.getImage("ChainLightning.png", BUTTON_WIDTH, BUTTON_WIDTH)));
        damageOverTime.setIcon(new ImageIcon(DriverView.getImage("FieryClock.png", BUTTON_WIDTH, BUTTON_WIDTH)));
        frostDamage.setIcon(new ImageIcon(DriverView.getImage("IceDamage.png", BUTTON_WIDTH, BUTTON_WIDTH)));
        piercingShotTower.setIcon(new ImageIcon(DriverView.getImage("DenseLightningTower.png", BUTTON_WIDTH, BUTTON_WIDTH)));
        patchOfFireTower.setIcon(new ImageIcon(DriverView.getImage("PatchOfFireTower.png", BUTTON_WIDTH, BUTTON_WIDTH)));
        stoppingTower.setIcon(new ImageIcon(DriverView.getImage("FreezeTower.png", BUTTON_WIDTH, BUTTON_WIDTH)));
        multiShotTower.setIcon(new ImageIcon(DriverView.getImage("TeslaTower.png", BUTTON_WIDTH, BUTTON_WIDTH)));
        rangedExplosionTower.setIcon(new ImageIcon(DriverView.getImage("FireBombTower.png", BUTTON_WIDTH, BUTTON_WIDTH)));
        icicleTower.setIcon(new ImageIcon(DriverView.getImage("IcicleTower.png", BUTTON_WIDTH, BUTTON_WIDTH)));

        ImageIcon comingSoon = new ImageIcon(DriverView.getImage("ComingSoon.png", BUTTON_WIDTH, BUTTON_WIDTH));

        lightningMine.setIcon(comingSoon);
        lightningMine.setDisabledIcon(comingSoon);

        fireMine.setIcon(comingSoon);
        fireMine.setDisabledIcon(comingSoon);

        frostMine.setIcon(comingSoon);
        frostMine.setDisabledIcon(comingSoon);

        elementalTower.setIcon(comingSoon);
        elementalTower.setDisabledIcon(comingSoon);

        boostTower.setIcon(comingSoon);
        boostTower.setDisabledIcon(comingSoon);

        moneyTower.setIcon(comingSoon);
        moneyTower.setDisabledIcon(comingSoon);
    }

    /**
     * Adds the buttons to the different tiers JPanels
     */

    public void addButtons() {
        layoutPanel(tier1, increaseDamage, increaseFireRate, increaseRange);
        layoutPanel(tier2, increaseGoldLevel, increaseGoldEnemy, reduceGoldTower);
        layoutPanel(tier3, chainLightning, damageOverTime, frostDamage);
        layoutPanel(tier4, piercingShotTower, patchOfFireTower, stoppingTower);
        layoutPanel(tier5, multiShotTower, rangedExplosionTower, icicleTower);
        layoutPanel(tier6, lightningMine, fireMine, frostMine);
        layoutPanel(tier7, elementalTower, boostTower, moneyTower);
        //acceptPanel.add(accept);
    }

    /**
     * Adds three buttons to a talent tree panel that has a specific layout.
     *
     * @param panel
     * @param but1
     * @param but2
     * @param but3
     */

    public void layoutPanel(JPanel panel, JToggleButton but1, JToggleButton but2, JToggleButton but3) {

        panel.setLayout(new GridLayout(1, 0));
        JPanel tempPanel1 = new JPanel();
        JPanel tempPanel2 = new JPanel();
        JPanel tempPanel3 = new JPanel();

        tempPanel1.setOpaque(false);
        tempPanel2.setOpaque(false);
        tempPanel3.setOpaque(false);

        but1.setContentAreaFilled(false);
        but2.setContentAreaFilled(false);
        but3.setContentAreaFilled(false);

        but1.setPreferredSize(new Dimension(50, 50));
        but2.setPreferredSize(new Dimension(50, 50));
        but3.setPreferredSize(new Dimension(50, 50));

        tempPanel1.add(but1);
        tempPanel2.add(but2);
        tempPanel3.add(but3);

        panel.add(tempPanel1);
        panel.add(tempPanel2);
        panel.add(tempPanel3);
    }

    /**
     * Sets all of the tool tips for the JToggleButtons.
     */

    public void setToolTips() {

        increaseDamage.setToolTipText("<html>" + "Increase Tower Damage" + "<br>" + "<br>" + "Increase the damage your main.towers do." + "<html>");
        increaseFireRate.setToolTipText("<html>" + "Increase Tower Fire Rate" + "<br>" + "<br>" + "Increase the rate at which your main.towers fire." + "<html>");
        increaseRange.setToolTipText("<html>" + "Increase Tower Range" + "<br>" + "<br>" + "Increase the range of your main.towers." + "<html>");
        increaseGoldLevel.setToolTipText("<html>" + "Increase Gold per Level" + "<br>" + "<br>" + "Increase the gold you gain at the end of every level." + "<html>");
        increaseGoldEnemy.setToolTipText("<html>" + "Increase Gold per Enemy" + "<br>" + "<br>" + "Increase the gold you gain from killing an enemy." + "<html>");
        reduceGoldTower.setToolTipText("<html>" + "Reduce Gold per Tower" + "<br>" + "<br>" + "Decrease the cost of main.towers by X gold." + "<html>");
        chainLightning.setToolTipText("<html>" + "Chain Lightning" + "<br>" + "<br>" + "Gives your lightning tower the ability to chain lightning" + "<html>");
        damageOverTime.setToolTipText("<html>" + "Damage Over Time" + "<br>" + "<br>" + "Damage from your fire main.towers now deals additional damage over time." + "<html>");
        frostDamage.setToolTipText("<html>" + "Frost Damage" + "<br>" + "<br>" + "Your frost main.towers now deal damage." + "<html>");
        piercingShotTower.setToolTipText("<html>" + "Piercing Shot Tower" + "<br>" + "<br>" + "Unlock a lightning tower thats main.controllers.projectiles pierce enemies." + "<html>");
        patchOfFireTower.setToolTipText("<html>" + "Patch of Fire Tower" + "<br>" + "<br>" + "Unlock a fire tower that leaves a patch of fire after it shoots." + "<html>");
        stoppingTower.setToolTipText("<html>" + "Stopping Tower" + "<br>" + "<br>" + "Unlock a frost tower that stops enemies in their tracks." + "<html>");
        multiShotTower.setToolTipText("<html>" + "Multi Shot Tower" + "<br>" + "<br>" + "Unlock a lightning tower that shoots multiple shots." + "<html>");
        rangedExplosionTower.setToolTipText("<html>" + "Ranged Explosion Tower" + "<br>" + "<br>" + "Unlock a fire tower that shoots a ranged explosion. " + "<html>");
        icicleTower.setToolTipText("<html>" + "Icicle Tower" + "<br>" + "<br>" + "Unlock a frost tower that leaves icicles on the ground after it attacks." + "<html>");
        lightningMine.setToolTipText("<html>" + "Lightning Mine" + "<br>" + "<br>" + "Unlock a mine that deals single target lightning damage." + "<html>");
        fireMine.setToolTipText("<html>" + "Fire Mine" + "<br>" + "<br>" + "Unlock a mine that deals multiple target fire damage." + "<html>");
        frostMine.setToolTipText("<html>" + "Frost Mine" + "<br>" + "<br>" + "Unlock a mine that freezes enemies in place." + "<html>");
        elementalTower.setToolTipText("<html>" + "Elemental Tower" + "<br>" + "<br>" + "Unlock an elemental tower that deals mixed elemental damage." + "<html>");
        boostTower.setToolTipText("<html>" + "Boost Tower" + "<br>" + "<br>" + "Unlock a tower that boots the damage of main.towers surrounding it." + "<html>");
        moneyTower.setToolTipText("<html>" + "Money Tower" + "<br>" + "<br>" + "Unlock a tower that gives you addiontal money whenever enemies die near it." + "<html>");
        accept.setToolTipText("<html>" + "Accepting the changes to the talent tree finalizes your talent choices for each tier." + "<html>");
    }

    /**
     * Determines the number of talent points required for a specific talent.
     *
     * @param selectedButtons
     * @return
     */

    public int talentPointCost(final boolean[][] selectedButtons) {
        int talentPointCost = 0;

        if (selectedButtons[0][0] == !increaseDamage.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[0][1] == !increaseFireRate.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[0][2] == !increaseRange.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[1][0] == !increaseGoldLevel.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[1][1] == !increaseGoldEnemy.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[1][2] == !reduceGoldTower.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[2][0] == !chainLightning.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[2][1] == !damageOverTime.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[2][2] == !frostDamage.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[3][0] == !piercingShotTower.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[3][1] == !patchOfFireTower.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[3][2] == !stoppingTower.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[4][0] == !multiShotTower.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[4][1] == !rangedExplosionTower.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[4][2] == !icicleTower.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[5][0] == !lightningMine.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[5][1] == !fireMine.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[5][2] == !frostMine.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[6][0] == !elementalTower.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[6][1] == !boostTower.isSelected()) {
            talentPointCost++;
        }
        if (selectedButtons[6][2] == !moneyTower.isSelected()) {
            talentPointCost++;
        }

        return talentPointCost;
    }

    /**
     * set buttons to be enabled
     *
     * @param tierDepth
     * @param selectedButtons
     */

    public void enableButtons(final int tierDepth, final boolean[][] selectedButtons) {
        this.disableButtons();

        if (disableTierAfterSelection) {
            if (tierDepth == 0) {
                this.enableTier1();
            } else if (tierDepth == 1) {
                this.enableTier2();
            } else if (tierDepth == 2) {
                this.enableTier3();
            } else if (tierDepth == 3) {
                this.enableTier4();
            } else if (tierDepth == 4) {
                this.enableTier5();
            } else if (tierDepth == 5) {
                this.enableTier6();
            } else if (tierDepth == 6) {
                this.enableTier7();
            }
        } else {
            this.enableTier1();
            if (tierDepth > 0) {
                this.enableTier2();
            }
            if (tierDepth > 1) {
                this.enableTier3();
            }
            if (tierDepth > 2) {
                this.enableTier4();
            }
            if (tierDepth > 3) {
                this.enableTier5();
            }
            if (tierDepth > 4) {
                this.enableTier6();
            }
            if (tierDepth > 5) {
                this.enableTier7();
            }

            if (selectedButtons[0][0]) {
                increaseDamage.setEnabled(false);
            }
            if (selectedButtons[0][1]) {
                increaseFireRate.setEnabled(false);
            }
            if (selectedButtons[0][2]) {
                increaseRange.setEnabled(false);
            }
            if (selectedButtons[1][0]) {
                increaseGoldLevel.setEnabled(false);
            }
            if (selectedButtons[1][1]) {
                increaseGoldEnemy.setEnabled(false);
            }
            if (selectedButtons[1][2]) {
                reduceGoldTower.setEnabled(false);
            }
            if (selectedButtons[2][0]) {
                chainLightning.setEnabled(false);
            }
            if (selectedButtons[2][1]) {
                damageOverTime.setEnabled(false);
            }
            if (selectedButtons[2][2]) {
                frostDamage.setEnabled(false);
            }
            if (selectedButtons[3][0]) {
                piercingShotTower.setEnabled(false);
            }
            if (selectedButtons[3][1]) {
                patchOfFireTower.setEnabled(false);
            }
            if (selectedButtons[3][2]) {
                stoppingTower.setEnabled(false);
            }
            if (selectedButtons[4][0]) {
                multiShotTower.setEnabled(false);
            }
            if (selectedButtons[4][1]) {
                rangedExplosionTower.setEnabled(false);
            }
            if (selectedButtons[4][2]) {
                icicleTower.setEnabled(false);
            }
            if (selectedButtons[5][0]) {
                lightningMine.setEnabled(false);
            }
            if (selectedButtons[5][1]) {
                fireMine.setEnabled(false);
            }
            if (selectedButtons[5][2]) {
                frostMine.setEnabled(false);
            }
            if (selectedButtons[6][0]) {
                elementalTower.setEnabled(false);
            }
            if (selectedButtons[6][1]) {
                boostTower.setEnabled(false);
            }
            if (selectedButtons[6][2]) {
                moneyTower.setEnabled(false);
            }

            accept.setEnabled(true);
        }
    }

    /**
     * Disables all of the buttons before the user has access to the talent tree.
     */

    public void disableButtons() {

        increaseDamage.setEnabled(false);
        increaseFireRate.setEnabled(false);
        increaseRange.setEnabled(false);
        increaseGoldLevel.setEnabled(false);
        increaseGoldEnemy.setEnabled(false);
        reduceGoldTower.setEnabled(false);
        chainLightning.setEnabled(false);
        damageOverTime.setEnabled(false);
        frostDamage.setEnabled(false);
        piercingShotTower.setEnabled(false);
        patchOfFireTower.setEnabled(false);
        stoppingTower.setEnabled(false);
        multiShotTower.setEnabled(false);
        rangedExplosionTower.setEnabled(false);
        icicleTower.setEnabled(false);
        lightningMine.setEnabled(false);
        fireMine.setEnabled(false);
        frostMine.setEnabled(false);
        elementalTower.setEnabled(false);
        boostTower.setEnabled(false);
        moneyTower.setEnabled(false);
        accept.setEnabled(false);
    }

    /**
     * Enables all of the buttons in tier 1.
     */

    public void enableTier1() {

        increaseDamage.setEnabled(true);
        increaseFireRate.setEnabled(true);
        increaseRange.setEnabled(true);

    }

    /**
     * Enables all of the buttons in tier 2.
     */

    public void enableTier2() {

        increaseGoldLevel.setEnabled(true);
        increaseGoldEnemy.setEnabled(true);
        reduceGoldTower.setEnabled(true);

    }

    /**
     * Enables all of the buttons in tier 3.
     */

    public void enableTier3() {

        chainLightning.setEnabled(true);
        damageOverTime.setEnabled(true);
        frostDamage.setEnabled(true);

    }

    /**
     * Enables all of the buttons in tier 4
     */

    public void enableTier4() {

        piercingShotTower.setEnabled(true);
        patchOfFireTower.setEnabled(true);
        stoppingTower.setEnabled(true);

    }

    /**
     * Enables all of the buttons in tier 5.
     */

    public void enableTier5() {

        multiShotTower.setEnabled(true);
        rangedExplosionTower.setEnabled(true);
        icicleTower.setEnabled(true);

    }

    /**
     * Enables all of the buttons in tier 6.
     */

    public void enableTier6() {

        lightningMine.setEnabled(true);
        fireMine.setEnabled(true);
        frostMine.setEnabled(true);

    }

    /**
     * Enables all of the buttons in tier 6.
     */

    public void enableTier7() {

        elementalTower.setEnabled(true);
        boostTower.setEnabled(true);
        moneyTower.setEnabled(true);

    }

    /**
     * Returns the talent that the user has selected from the first tier. If no button is selected the method returns null.
     */

    public int tier1Selected() {

        if (increaseDamage.isSelected() == true)
            return 0;

        else if (increaseFireRate.isSelected() == true)
            return 1;

        else if (increaseRange.isSelected() == true)
            return 2;

        else
            return -1;
    }

    /**
     * Returns the talent that the user has selected from the second tier. If no button is selected the method returns null.
     */

    public int tier2Selected() {

        if (increaseGoldLevel.isSelected() == true)
            return 0;

        else if (increaseGoldEnemy.isSelected() == true)
            return 1;

        else if (reduceGoldTower.isSelected() == true)
            return 2;

        else
            return -1;
    }

    /**
     * Returns the talent that the user has selected from the third tier. If no button is selected the method returns null.
     */

    public int tier3Selected() {

        if (chainLightning.isSelected() == true)
            return 0;

        else if (damageOverTime.isSelected() == true)
            return 1;

        else if (frostDamage.isSelected() == true)
            return 2;

        else
            return -1;
    }

    /**
     * Returns the talent that the user has selected from the fourth tier. If no button is selected the method returns null.
     */

    public int tier4Selected() {

        if (piercingShotTower.isSelected() == true)
            return 0;

        else if (patchOfFireTower.isSelected() == true)
            return 1;

        else if (stoppingTower.isSelected() == true)
            return 2;

        else
            return -1;
    }

    /**
     * Returns the talent that the user has selected from the fifth tier. If no button is selected the method returns null.
     */

    public int tier5Selected() {

        if (multiShotTower.isSelected() == true)
            return 0;

        else if (rangedExplosionTower.isSelected() == true)
            return 1;

        else if (icicleTower.isSelected() == true)
            return 2;

        else
            return -1;
    }

    /**
     * Returns the talent that the user has selected from the sixth tier. If no button is selected the method returns null.
     */

    public int tier6Selected() {

        if (lightningMine.isSelected() == true)
            return 0;

        else if (fireMine.isSelected() == true)
            return 1;

        else if (frostMine.isSelected() == true)
            return 2;

        else
            return -1;
    }

    /**
     * Returns the talent that the user has selected from the seventh tier. If no button is selected the method returns null.
     */

    public int tier7Selected() {

        if (elementalTower.isSelected() == true)
            return 0;

        else if (boostTower.isSelected() == true)
            return 1;

        else if (moneyTower.isSelected() == true)
            return 2;

        else
            return -1;
    }

    /**
     * Adds an action listener to every component.
     *
     * @param l
     */

    public void addListenerToAll(ActionListener l) {
        addListenerToAll(this.getComponents(), l);
    }

    /**
     * Adds an action listener to every component given in an array.
     */

    private void addListenerToAll(Component[] components, ActionListener l) {
        for (Component c : components) {
            if (c instanceof JPanel) {
                addListenerToAll(((JPanel) c).getComponents(), l);
            } else if (c instanceof AbstractButton) {
                ((AbstractButton) c).addActionListener(l);
            }
        }
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getIncreaseDamage() {
        return increaseDamage;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getIncreaseFireRate() {
        return increaseFireRate;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getIncreaseRange() {
        return increaseRange;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getIncreaseGoldLevel() {
        return increaseGoldLevel;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getIncreaseGoldEnemy() {
        return increaseGoldEnemy;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getReduceGoldTower() {
        return reduceGoldTower;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getChainLightning() {
        return chainLightning;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getDamageOverTime() {
        return damageOverTime;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getFrostDamage() {
        return frostDamage;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getPiercingShotTower() {
        return piercingShotTower;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getPatchOfFireTower() {
        return patchOfFireTower;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getStoppingTower() {
        return stoppingTower;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getMultiShotTower() {
        return multiShotTower;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getRangedExplosionTower() {
        return rangedExplosionTower;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getIcicleTower() {
        return icicleTower;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getLightningMine() {
        return lightningMine;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getFireMine() {
        return fireMine;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getFrostMine() {
        return frostMine;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getElementalTower() {
        return elementalTower;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getBoostTower() {
        return boostTower;
    }

    /**
     * @return JToggleButton
     */

    public JToggleButton getMoneyTower() {
        return moneyTower;
    }

    /**
     * @return JButton
     */

    public JButton getAcceptButton() {
        return accept;
    }

    /**
     * repaints the talent tree view
     */

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(DriverView.getImage("TalentTreeBackground.png", 700, 650), 0, 0, null);
    }

    /**
     * updates the talent tree view when the model updates
     */

    public void actionPerformed(ActionEvent e) {
        if (this.model.getTalentPoints() > 0) {
            this.enableButtons(this.model.getTierDepth(), this.model.getDisabledButtonArray());
        }
        this.repaint();
    }
}