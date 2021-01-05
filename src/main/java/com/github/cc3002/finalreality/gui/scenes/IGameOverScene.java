package com.github.cc3002.finalreality.gui.scenes;

/**
 * This represents a game over scene
 * used by the controller to activate the scene depending om the result of the battle
 *
 * @author Sebastián Olmos.
 */
public interface IGameOverScene {

    /**
     * Set the attributes according the victory result
     */
    void setVictory();

    /**
     * Set the attributes according the defeat result
     */
    void setDefeat();

    /**
     * Activate the scene, setting the root of the main scene
     */
    void activate();
}
