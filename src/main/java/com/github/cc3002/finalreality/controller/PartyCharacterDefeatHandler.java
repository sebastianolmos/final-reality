package com.github.cc3002.finalreality.controller;

import java.beans.PropertyChangeEvent;

/**
 * This represents a event handler that notify to the controller that
 * an party character was defeated.
 *
 * @author Sebastián Olmos.
 */
public class PartyCharacterDefeatHandler implements IEventHandler {
    private final GameController controller;

    /**
     * Create the event handler.
     * @param controller
     *     Controller that manage the turns and characters.
     */
    public PartyCharacterDefeatHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * Send the message that a party character was defeated and update the remaining number.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.updatePartyLeft();
    }
}
