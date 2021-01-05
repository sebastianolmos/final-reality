package com.github.cc3002.finalreality.gui.nodeSelection;

/**
 * This represents a target enemy menu
 * used by the controller to update the elements of the menu displayed by the gui
 *
 * @author Sebastián Olmos.
 */
public interface INodeTargetEnemy {

    /**
     * Call methods to the gui to update the info panel in the menu
     * @param targetedIndex
     *      index of the enemy that is targeted
     */
    void updateInfoPanel(int targetedIndex);

    /**
     * Call methods to the gui to update the enemy buttons in the menu
     */
    void updateEnemySelection();
}
