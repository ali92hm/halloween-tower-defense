package models;

import mapdata.MapData;
import mobs.Mob;
import projectiles.Projectile;
import towers.Tower;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;


/**
 * the model that stores all the data
 * for the tower defense game
 *
 * @author Scorpion
 */

public class DriverModel {

    private static int endOfRoundMoney = 200;
    private static int endOfRoundMoneyBoost = 0;

    private RuntimeThread runtimeThread = new RuntimeThread(this);

    private volatile ArrayList<Mob> mobs;
    private volatile ArrayList<Tower> towers = new ArrayList<Tower>();
    private volatile ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    private int level = 1;
    private int talentPoints = 0;
    private int lives = 10;
    private int money = 600;

    private int difficulty = 0;
    private boolean isNormalSpeed = true;

    private String mapImageName;
    private int mapImageWidth;
    private int mapImageHeight;

    private MapData mapData;

    private int tierDepth = 1;
    private boolean[][] disabledButtonArray;

    private ArrayList<ActionListener> actionListenerList;

    private boolean updateScreen = false;
    private int cancelConfirmOption = 0;
    private int startButtonState = 0;
    private int activeScreen = 0;

    /*
     ***************
     * Constructor *
     ***************
     */

    /**
     * constructor for the model
     */

    public DriverModel() {
    }

    /*
     *****************
     * Setup Methods *
     *****************
     */

    /**
     * set the money boost you gain at the
     * end of the round from 0 to 200
     */

    public static void upgradeEndOfLevelMoneyBoost() {
        endOfRoundMoneyBoost = 200;
    }

    /**
     * Concatenates strings
     *
     * @param strings
     * @return String
     */

    public static String buildStrings(String... strings) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    /*
     **************************
     * List modifying methods *
     **************************
     */

    /**
     * creates a new runtime loop
     * which begins a new round
     */

    public synchronized void gameRuntimeLoop() {
        if (!this.runtimeThread.isRunning() && !this.runtimeThread.isSuspended()) {
            this.mobs = mapData.getMobs(this.level);
            this.runtimeThread = new RuntimeThread(this);
            this.runtimeThread.start();
        }
    }

    /**
     * gets the list of main.controllers.mobs currently present in the game
     *
     * @return
     */

    public ArrayList<Mob> allMobs() {
        if (this.mobs == null) {
            return null;
        }
        return new ArrayList<Mob>(this.mobs);
    }

    /**
     * gets the mob on the map at a specific index
     *
     * @param index
     * @return Mob
     */

    public Mob findMob(final int index) {
        return this.mobs.get(index);
    }

    /**
     * adds a mob to the list of main.controllers.mobs on the map
     *
     * @param mob
     */

    public void addMob(final Mob mob) {
        this.mobs.add(mob);
    }

    /**
     * removes a mob from the list of main.controllers.mobs currently
     * active on the map
     *
     * @param mob
     */

    public void removeMob(final Mob mob) {
        this.mobs.remove(mob);
    }

    /**
     * gets how many main.controllers.mobs are currently active on the map
     *
     * @return int
     */

    public int mobsLength() {
        return this.mobs.size();
    }

    /**
     * sorts the main.controllers.mobs on the map in terms of how far they
     * have traveled on the map
     */

    public void sortMobs() {
        ArrayList<Mob> sortedList = new ArrayList<Mob>(this.mobs);
        Collections.sort(sortedList);
        this.mobs = sortedList;
    }

    /**
     * gets the list of main.towers on the map
     *
     * @return ArrayList<Tower>
     */

    public ArrayList<Tower> allTowers() {
        return new ArrayList<Tower>(this.towers);
    }

    /**
     * gets a tower at a specific index location
     *
     * @param index
     * @return Tower
     */

    public Tower findTower(final int index) {
        return this.towers.get(index);
    }

    /**
     * adds a tower to the list of main.towers on the map
     *
     * @param tower
     */

    public void addTower(final Tower tower) {
        this.towers.add(tower);
    }

    /**
     * removes a tower from the list of main.towers on the map
     *
     * @param tower
     */

    public void removeTower(final Tower tower) {
        this.towers.remove(tower);
    }

    /**
     * gets the total number of main.towers on the map
     *
     * @return int
     */

    public int towersLength() {
        return this.towers.size();
    }

    /**
     * gets the list of main.controllers.projectiles on the map
     *
     * @return ArrayList<Projectile>
     */

    public ArrayList<Projectile> allProjectiles() {
        return new ArrayList<Projectile>(this.projectiles);
    }

    /**
     * finds a specific projectile from the list
     *
     * @param index
     * @return Projectiles
     */

    public Projectile findProjectile(final int index) {
        return this.projectiles.get(index);
    }

    /**
     * adds a projectile to the map
     *
     * @param projectile
     */

    public void addProjectile(final Projectile projectile) {
        this.projectiles.add(projectile);
    }

    /**
     * removes a projectile from the map
     *
     * @param projectile
     */

    public void removeProjectile(final Projectile projectile) {
        this.projectiles.remove(projectile);
    }

    /*
     *******************
     * Mutator Methods *
     *******************
     */

    /**
     * gets the total number of projectils
     * on the map
     *
     * @return
     */

    public int projectilesLength() {
        return this.projectiles.size();
    }

    /**
     * sets the game to be running at either normal
     * or fast speed
     *
     * @param isNormalSpeed
     */

    public void setIsNormalSpeed(final boolean isNormalSpeed) {
        this.isNormalSpeed = isNormalSpeed;
    }

    /**
     * gets a specific button from the array of
     * booleans indicating which talents the
     * user has selected
     *
     * @param index1
     * @param index2
     */

    public void setButtonSelected(final int index1, final int index2) {
        this.disabledButtonArray[index1][index2] = true;
    }

    /**
     * gets which round the user is currently on
     *
     * @return int
     */

    public int getLevel() {
        return this.level;
    }

    /**
     * gets how many talent points the user currently has
     *
     * @return int
     */

    public int getTalentPoints() {
        return this.talentPoints;
    }

    /**
     * gets how many lives the user has left
     *
     * @return int
     */

    public int getLives() {
        return this.lives;
    }

    /**
     * gets how much money the user currently has
     *
     * @return int
     */

    public int getMoney() {
        return this.money;
    }

    /**
     * gets the difficulty
     *
     * @return int
     */

    public int getDifficulty() {
        return this.difficulty;
    }

    /*
     ********************
     * Accessor Methods *
     ********************
     */

    /**
     * sets the difficulty field
     *
     * @param val
     */

    public void setDifficulty(final int val) {
        difficulty = val;
    }

    /**
     * return whether or not the game is playing at normal speed
     *
     * @return boolean
     */

    public boolean isNormalSpeed() {
        return this.isNormalSpeed;
    }

    /**
     * gets the background image of the game
     *
     * @return String
     */

    public String getMapImageName() {
        return this.mapImageName;
    }

    /**
     * gets the width of the background image of the game
     *
     * @return int
     */

    public int getMapImageWidth() {
        return this.mapImageWidth;
    }

    /**
     * gets the height of the background image of the game
     *
     * @return int
     */

    public int getMapImageHeight() {
        return this.mapImageHeight;
    }

    /**
     * gets the data the game uses to
     * create the main.controllers.mobs, move the main.controllers.mobs
     * and set the background image
     *
     * @return MapData
     */

    public MapData getMapData() {
        return this.mapData;
    }

    /**
     * sets the data the runtime thread
     * uses for the map, main.controllers.mobs and mob
     * movement
     *
     * @param mapData
     */

    public void setMapData(final MapData mapData) {
        this.mapData = mapData;
        this.mapImageName = mapData.getMapImageName();
        this.mapImageWidth = mapData.getMapImageWidth();
        this.mapImageHeight = mapData.getMapImageHeight();
    }

    /**
     * returns how deep into the talent tree
     * the user is
     *
     * @return int
     */

    public int getTierDepth() {
        return this.tierDepth;
    }

    /**
     * set the depth of the talent tree to
     * be a new depth
     *
     * @param tierDepth
     */

    public void setTierDepth(final int tierDepth) {
        this.tierDepth = tierDepth;
    }

    /**
     * gets the array of values indicating which
     * buttons in the talent tree the user has selected
     *
     * @return boolean[][]
     */

    public boolean[][] getDisabledButtonArray() {
        return this.disabledButtonArray;
    }

    /**
     * returns whether the screen needs to be updated
     *
     * @return boolean
     */

    public boolean isUpdateScreen() {
        return updateScreen;
    }

    /**
     * tells the main.controllers.models that the screen needs to be updated
     *
     * @param updateScreen
     */

    public void setUpdateScreen(final boolean updateScreen) {
        this.updateScreen = updateScreen;
    }

    /**
     * gets the value of the confirmation
     * window for the upgrade panel
     *
     * @return int
     */

    public int getCancelConfirmOption() {
        return cancelConfirmOption;
    }

    /**
     * sets the value of the confirmation panel of the
     * upgrade panel to be one of the three upgrade
     * panels or the sell panel
     *
     * @param cancelConfirmOption
     */

    public void setCancelConfirmOption(final int cancelConfirmOption) {
        this.cancelConfirmOption = cancelConfirmOption;
        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "activateUpgradeSell"));
    }

    /**
     * returns the state of the start button
     *
     * @return int
     */

    public int getStartButtonState() {
        return startButtonState;
    }

    /**
     * sets the state of the start button between ready
     * to start the next game, 1x speed, or 2x speed
     *
     * @param startButtonState
     */

    public void setStartButtonState(final int startButtonState) {
        this.startButtonState = startButtonState;
    }

    /*
     ******************
     * Update Methods *
     ******************
     */

    /**
     * returns which screen is active
     *
     * @return int
     */

    public int getActiveScreen() {
        return activeScreen;
    }

    /**
     * sets which screen is active between the title screen,
     * the main view, and the game screen
     *
     * @param activeScreen
     */

    public void setActiveScreen(final int activeScreen) {
        this.activeScreen = activeScreen;
    }

    /**
     * upon restarting the game, this
     * will reset all the values
     */

    public void resetValues() {
        level = 1;
        money = 600;
        lives = 10;
        talentPoints = 0;
        tierDepth = 0;
        this.isNormalSpeed = true;
        this.disabledButtonArray = new boolean[7][3];
        towers.clear();
        projectiles.clear();
        mobs = new ArrayList<Mob>();
    }

    /**
     * updates which round the user is on,
     * how much money the user has, and
     * how many talent point the user has after
     * a round has completed
     */

    public void updateValuesAfterLevel() {
        this.level++;
        this.money += endOfRoundMoney + endOfRoundMoneyBoost;
        if (this.level % 5 == 0) {
            this.talentPoints += 2;
        }
        this.updateScreen = true;
        this.startButtonState = 0;
        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "incrementLevel"));
    }

    /**
     * adds the amount of money the user gain from
     * killing a mob
     *
     * @param addedMoney
     */

    public void addMobDeathMoney(final int addedMoney) {
        this.money += addedMoney;
        this.updateScreen = true;
        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "addedMoney"));
    }

    /**
     * removes money after a user buys an upgrade
     *
     * @param removedMoney
     */

    public void towerBuyUpgradeMoney(final int removedMoney) {
        this.money -= removedMoney;
        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "removedMoney"));
    }

    /**
     * adds money from selling a tower
     *
     * @param moneyAdded
     */

    public void towerSellMoney(final int moneyAdded) {
        this.money += moneyAdded;
        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "addedMoney"));
    }

    /**
     * removes the number of talent points
     * based on how many talents you bought
     * (lock at 1 currently)
     *
     * @param removedTalentPoint
     */

    public void removedTalentPoints(final int removedTalentPoint) {
        this.talentPoints -= removedTalentPoint;

        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "removed talent points"));
    }

    /*
     *******************
     * Utility Methods *
     *******************
     */

    /**
     * removes a life everytime a mob escapes
     */

    public void lifeLost() {
        this.lives--;
        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "removed talent points"));
    }

    /**
     * updates the main.views
     */

    public void processEvent() {
        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "upgraded tower"));
    }

    /**
     * handles all key events
     */

    public void keyboardHandler() {
        if (this.activeScreen == 0) {
            this.activeScreen = 1;
        }

        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "switch to main view"));
    }

    /**
     * returns the runtime thread of the game
     *
     * @return RuntimeThread
     */

    public RuntimeThread getRuntimeThread() {
        return runtimeThread;
    }

    /**
     * adds an actionlistener for the model
     *
     * @param l
     */

    public synchronized void addActionListener(ActionListener l) {
        if (actionListenerList == null) {
            actionListenerList = new ArrayList<ActionListener>();
        }

        actionListenerList.add(l);
    }

    /**
     * removes an action listener for the model
     */

    public synchronized void removeActionListener(ActionListener l) {
        if (actionListenerList != null && actionListenerList.contains(l)) {
            actionListenerList.remove(l);
        }
    }

    /**
     * Fire TickEvent
     */

    private void processEvent(ActionEvent e) {
        ArrayList<ActionListener> list;

        synchronized (this) {
            if (actionListenerList == null) {
                return;
            }
            list = new ArrayList<ActionListener>(actionListenerList);
        }

        for (int i = 0; i < list.size(); i++) {
            ActionListener listener = (ActionListener) list.get(i);
            listener.actionPerformed(e);
        }
    }
}
