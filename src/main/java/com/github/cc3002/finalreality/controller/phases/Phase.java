package com.github.cc3002.finalreality.controller.phases;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.gui.nodeSelection.INodeTargetEnemy;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.WeaponType;
import org.jetbrains.annotations.NotNull;

/**
 * This represents a Phase in the logic of the battle system
 *
 * @author Sebastián Olmos.
 */
public class Phase {
    protected GameController controller;

    /**
     * Set a created controller that used the phases.
     * @param controller
     *     reference of a controller.
     */
    public void setController(final @NotNull GameController controller) {
        this.controller = controller;
    }

    /**
     * Call the controller to change its phase reference
     * @param phase
     *     reference of a controller.
     */
    protected void changePhase(Phase phase) {
        controller.setPhase(phase);
    }

    /**
     * Try to change phase to Selecting action.
     * In default does nothing
     */
    public void toSelectingAction() {
    }

    /**
     * Try to change phase to Selecting weapon.
     * In default does nothing
     */
    public void toSelectingWeapon() {
    }

    /**
     * Try to change phase to Selecting target to attack.
     * In default does nothing
     */
    public void toSelectingAttackTarget() {
    }

    /**
     * Try to change phase to end turn.
     * In default does nothing
     */
    public void toEndingTurn() {
    }

    /**
     * Try to change phase to waiting turn.
     * In default does nothing
     */
    public void toWaitingTurn() {
    }

    /**
     * Check if the Phase is selecting action.
     * In default is false
     */
    public boolean isSelectingAction() {
        return false;
    }

    /**
     * Check if the Phase is selecting weapon.
     * In default is false
     */
    public boolean isSelectingWeapon() {
        return false;
    }

    /**
     * Check if the Phase is selecting target character.
     * In default is false
     */
    public boolean isSelectingAttackTarget() {
        return false;
    }

    /**
     * Try to begin a turn.
     * In default does nothing
     */
    public void beginTurn(){
    }

    /**
     * Try to select a weapon
     * In default does nothing
     * @param weaponIndex
     *     weapon from the inventory to select.
     */
    public void selectWeaponFromInventory(int weaponIndex) {
    }

    /**
     * Try to get the name of the selected weapon
     * By default return an empty string
     */
    public String getSelectedWeaponName() {
        return "";
    }

    /**
     * Try to get the damage of the selected weapon
     * By default return zero
     */
    public int getSelectedWeaponDamage() {
        return 0;
    }

    /**
     * Try to get the weight of the selected weapon
     * By default return zero
     */
    public int getSelectedWeaponWeight() {
        return 0;
    }

    /**
     * Try to get the type of the selected weapon
     * By default return null
     */
    public WeaponType getSelectedWeaponType() {
        return WeaponType.NULL;
    }

    /**
     * Try to get the name of the weapon equipped by the character in turn
     * By default return an empty string
     */
    public String getEquippedWeaponName() {
        return "";
    }

    /**
     * Try to get the damage of the weapon equipped by the character in turn
     * By default return zero
     */
    public int getEquippedWeaponDamage() {
        return 0;
    }

    /**
     * Try to get the weight of the weapon equipped by the character in turn
     * By default return zero
     */
    public int getEquippedWeaponWeight() {
        return 0;
    }

    /**
     * Try to get the type of the weapon equipped by the character in turn
     * By default return null
     */
    public WeaponType getEquippedWeaponType() {
        return WeaponType.NULL;
    }

    /**
     * Try to select a character as a target
     * By default does nothing
     * @param character
     *      character to be the target
     */
    public void selectTarget(ICharacter character) {
    }

    /**
     * Try to equip in the character in turn, the selected weapon
     * By default return null
     */
    public void equipSelectedWeapon() {
    }

    /**
     * Try to attack the target character
     * By default return null
     */
    public void attackTargetCharacter() {
    }

    /**
     * Check if a character is targeted
     * By default return false
     */
    public boolean isACharacterTargeted() {
        return false;
    }

    /**
     * Try to change phase to Selecting target to attack
     * notifying a gui element
     * In default does nothing
     */
    public void toSelectingAttackTarget(INodeTargetEnemy targetMenu) {
    }
}
