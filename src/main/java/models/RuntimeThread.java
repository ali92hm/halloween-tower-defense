package models;

import mobs.Mob;
import projectiles.Projectile;
import towers.Tower;
import utilities.Position;

/**
 * this is the thread that updates the
 * main.controllers.mobs positions, when the main.towers fire
 * and the projectile movements and hits
 *
 * @author Scorpion
 */

public class RuntimeThread extends Thread {

    private final DriverModel model;
    private volatile boolean isRunning = false;
    private volatile boolean isSuspended = false;
    private volatile boolean isTalentTreeActive = false;
    private boolean canActivateMob = false;
    private boolean gameOver = false;
    private boolean endGame = false;
    private boolean restart = false;

    /**
     * constructs the runtime thread
     *
     * @param model
     */

    public RuntimeThread(final DriverModel model) {
        this.model = model;
    }

    /**
     * begins the runtime thread
     * this happens at the start of
     * every level
     */

    public void run() {
        try {
            this.isRunning = true;
            int activeMobs = 0;
            int mobReleaseCooldown = 0;

            while (isRunning) {
                boolean endRound = true;
                this.model.sortMobs();

                if (mobReleaseCooldown > 0 && !this.isSuspended && !this.isTalentTreeActive) {
                    mobReleaseCooldown--;
                }
                if (mobReleaseCooldown == 0 && activeMobs < model.mobsLength() && !this.isSuspended && !this.isTalentTreeActive) {
                    mobReleaseCooldown = 20;
                    canActivateMob = true;
                }

                for (Mob mob : model.allMobs()) {
                    if (mob.isVisible()) {
                        endRound = false;
                        break;
                    }
                }
                int speed;
                if (model.isNormalSpeed()) {
                    speed = 50;
                } else {
                    speed = 25;
                }
                Thread.sleep(speed);
                for (Mob mob : model.allMobs()) {
                    if (!mob.isVisible()) {
                        continue;
                    }

                    if (!mob.isActive() && canActivateMob && !this.isSuspended && !this.isTalentTreeActive) {
                        mob.activate();
                        canActivateMob = false;
                    }

                    if (mob.isActive() && !this.isSuspended && !this.isTalentTreeActive) {
                        Position newPosition = this.model.getMapData().travelDistance(mob);
                        mob.setPosition(newPosition);
                    }
                }

                for (Tower tower : this.model.allTowers()) {
                    if (!this.isSuspended && !this.isTalentTreeActive) {
                        Projectile[] projectiles = tower.attackMob(this.model);
                        for (Projectile projectile : projectiles) {
                            if (projectile != null) {
                                this.model.addProjectile(projectile);
                            }
                        }
                    }
                }

                for (final Projectile projectile : this.model.allProjectiles()) {
                    if (!projectile.stillAlive()) {
                        this.model.removeProjectile(projectile);
                        continue;
                    }

                    if (!this.isSuspended && !this.isTalentTreeActive) {
                        projectile.updateProjectile();
                    }

                    endRound = false;
                }

                for (Mob mob : model.allMobs()) {

                    if (!mob.isVisible()) {
                        model.removeMob(mob);
                        continue;
                    }

                    int mobMoney = mob.mobDamageTick();
                    if (mobMoney > 0) {
                        model.addMobDeathMoney(mobMoney);
                    }
                }

                if (model.getLives() < 1) {
                    this.gameOver = true;
                }

                if (gameOver || endGame || restart) {
                    break;
                }

                if (endRound) {
                    break;
                }
                this.model.processEvent();
            }
        } catch (InterruptedException e) {
        }

        if (gameOver) {
            this.model.setStartButtonState(0);
            model.resetValues();
            this.model.setActiveScreen(1);
            this.setSuspended(false);
        } else if (endGame) {
            this.model.setStartButtonState(0);
            model.resetValues();
            this.model.setActiveScreen(1);
        } else if (restart) {
            this.model.setStartButtonState(0);
            model.resetValues();
        } else {
            this.model.updateValuesAfterLevel();
        }
        this.model.processEvent();
        this.halt();
    }

    /**
     * stops the thread
     */

    public void halt() {
        this.isRunning = false;
    }

    /**
     * returns whether the thread is
     * actively running or not
     *
     * @return boolean
     */

    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * returns whether the game is
     * updating or not
     *
     * @return boolean
     */

    public boolean isSuspended() {
        return this.isSuspended;
    }

    /**
     * sets whether the games loop
     * should be updating the main.controllers.mobs
     * main.towers, and main.controllers.projectiles or not
     *
     * @param isSuspended
     */

    public void setSuspended(final boolean isSuspended) {
        this.isSuspended = isSuspended;
    }

    /**
     * returns whether the talent tree
     * is active or not
     *
     * @return boolean
     */

    public boolean isTalentTreeActive() {
        return this.isTalentTreeActive;
    }

    /**
     * sets whether the talent tree is active or not
     *
     * @param isTalentTreeActive
     */

    public void setTalentTreeActive(final boolean isTalentTreeActive) {
        this.isTalentTreeActive = isTalentTreeActive;
    }

    /**
     * tells the model the game is over
     */

    public void endGame() {
        this.endGame = true;
    }

    /**
     * tells the model to reset itself
     */

    public void restart() {
        this.restart = true;
    }
}
