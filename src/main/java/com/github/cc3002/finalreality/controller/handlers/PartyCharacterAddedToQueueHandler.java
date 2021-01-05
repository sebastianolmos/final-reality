package com.github.cc3002.finalreality.controller.handlers;

import com.github.cc3002.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;

/**
 * This represents a event handler that notify to the controller that
 * a playable character was added to the turn queue
 *
 * @author Sebastián Olmos.
 */
public class PartyCharacterAddedToQueueHandler implements IEventHandler{
    private final GameController controller;

    /**
     * Create the event handler.
     * @param controller
     *     Controller that manage the turns and characters.
     */
    public PartyCharacterAddedToQueueHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * Send the message that a playable character was added and try to begin a turn.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.tryBeginTurn();
    }
}
