package com.github.cc3002.finalreality.controller.phases;

import com.github.cc3002.finalreality.gui.nodeSelection.INodeTargetEnemy;

/**
 * This represents the phase when the character is selecting an action
 *
 * @author Sebastián Olmos.
 */
public class SelectingActionPhase extends Phase{

    /**
     * Go to the selecting target phase
     */
    @Override
    public void toSelectingAttackTarget() {
        changePhase(new SelectingAttackTargetPhase());
    }

    /**
     * Go to the selecting target phase notifying to the guiElement
     * and this update its display
     */
    @Override
    public void toSelectingAttackTarget(INodeTargetEnemy targetMenu) {
        targetMenu.updateInfoPanel(0);
        targetMenu.updateEnemySelection();
        toSelectingAttackTarget();
    }

    /**
     * Go to the selecting weapon phase and notify
     * to the gui to update the display of the inventory
     */
    @Override
    public void toSelectingWeapon() {
        changePhase(new SelectingWeaponPhase());
        controller.updateInventoryMenu();
    }

    /**
     * Check if the phase is selecting action
     */
    @Override
    public boolean isSelectingAction() {
        return true;
    }
}
