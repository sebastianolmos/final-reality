package com.github.cc3002.finalreality.controller;

import java.beans.PropertyChangeEvent;

/**
 * This represents a event handler that notify to the controller to play a turn.
 *
 * @author Sebastián Olmos.
 */
public class CharacterTurnHandler implements IEventHandler{
    private final GameController controller;

    /**
     * Create the event handler.
     * @param controller
     *     Controller that manage the turns.
     */
    public CharacterTurnHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * Send the message to start a new turn.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.startTurn();
    }
}
