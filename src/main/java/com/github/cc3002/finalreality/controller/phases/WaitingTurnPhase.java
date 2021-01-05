package com.github.cc3002.finalreality.controller.phases;

/**
 * This represents the phase when the character waiting to begin a turn
 *
 * @author Sebastián Olmos.
 */
public class WaitingTurnPhase extends Phase{

    /**
     * Go to the selecting action phase
     */
    @Override
    public void toSelectingAction() {
        changePhase(new SelectingActionPhase());
    }

    /**
     * Go to the ending turn phase
     */
    @Override
    public void toEndingTurn() {
        changePhase(new EndingTurnPhase());
    }

    /**
     * Call the controller to start a new turn
     */
    @Override
    public void beginTurn() {
        controller.startTurn();
    }

}
