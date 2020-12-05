package com.github.cc3002.finalreality.controller.handler;

import com.github.cc3002.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;

/**
 * This represents a event handler that notify to the controller that
 * an enemy character was defeated.
 *
 * @author Sebastián Olmos.
 */
public class EnemyCharacterDefeatHandler implements IEventHandler {
    private final GameController controller;

    /**
     * Create the event handler.
     * @param controller
     *     Controller that manage the turns and characters.
     */
    public EnemyCharacterDefeatHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * Send the message that an enemy was defeated and update the remaining number.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.updateEnemiesLeft();
    }
}