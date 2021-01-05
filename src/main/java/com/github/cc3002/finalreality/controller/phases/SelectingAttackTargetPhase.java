package com.github.cc3002.finalreality.controller.phases;

import com.github.cc3002.finalreality.model.character.ICharacter;

/**
 * This represents the phase when the character is selecting a target to attack
 *
 * @author Sebastián Olmos.
 */
public class SelectingAttackTargetPhase extends Phase{
    private ICharacter targetCharacter = null;

    /**
     * Go to the selecting target phase
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
     * Check if the phase is selecting target
     */
    @Override
    public boolean isSelectingAttackTarget() {
        return true;
    }

    /**
     * Target a character and save its reference
     */
    @Override
    public void selectTarget(ICharacter character) {
        if (character.itsAlive()) {
            targetCharacter = character;
        }
    }

    /**
     * Attack the target character
     */
    @Override
    public void attackTargetCharacter() {
        if (targetCharacter != null) {
            controller.turnCharacterAttackTo(targetCharacter);
            controller.endTurn();
        }
    }

    /**
     * Check if the target character contain a reference
     */
    @Override
    public boolean isACharacterTargeted() {
        return targetCharacter != null;
    }
}
