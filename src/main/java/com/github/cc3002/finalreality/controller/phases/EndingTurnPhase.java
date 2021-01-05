package com.github.cc3002.finalreality.controller.phases;

import com.github.cc3002.finalreality.controller.GameController;
import org.jetbrains.annotations.NotNull;

/**
 * This represents the phase when the turn is ending
 *
 * @author Sebastián Olmos.
 */
public class EndingTurnPhase extends Phase{

    /**
     * Set the controller and call a function to go on with the other turn.
     * @param controller
     *     reference of a controller.
     */
    @Override
    public void setController(final @NotNull GameController controller) {
        super.setController(controller);
        endingTurn();
    }

    /**
     * Go on with the turns depending if the queue is empty
     */
    public void endingTurn() {
        if (!controller.isTurnsQueueEmpty()) {
            controller.startTurn();
        }
        else {
            toWaitingTurn();
        }
    }

    /**
     * Go to the waiting turn phase
     */
    @Override
    public void toWaitingTurn() {
        changePhase(new WaitingTurnPhase());
    }

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

}
