package com.github.cc3002.finalreality.controller.phases;

import com.github.cc3002.finalreality.model.character.player.IPlayer;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.NullWeapon;
import com.github.cc3002.finalreality.model.weapon.WeaponType;

/**
 * This represents the phase when the character is selecting weapon / inventory
 *
 * @author Sebastián Olmos.
 */
public class SelectingWeaponPhase extends Phase{
    private IWeapon selectedWeapon = NullWeapon.getInstance();
    private int selectedWeaponIndex = 0;

    /**
     * Go to the selecting action phase
     */
    @Override
    public void toSelectingAction() {
        changePhase(new SelectingActionPhase());
    }

    /**
     * Check if the phase is selecting weapon
     */
    @Override
    public boolean isSelectingWeapon() {
        return true;
    }

    /**
     * Select a weapon from the inventory a store the reference and index
     */
    @Override
    public void selectWeaponFromInventory(int weaponIndex) {
        selectedWeapon = controller.getWeaponFromInventory(weaponIndex);
        selectedWeaponIndex = weaponIndex;
    }

    /**
     * Return the damage of the selected weapon
     */
    @Override
    public int getSelectedWeaponDamage() {
        return selectedWeapon.getDamage();
    }

    /**
     * Return the weight of the selected weapon
     */
    @Override
    public int getSelectedWeaponWeight() {
        return selectedWeapon.getWeight();
    }

    /**
     * Return the name of the selected weapon
     */
    @Override
    public String getSelectedWeaponName() {
        return selectedWeapon.getName();
    }

    /**
     * Return the enum type of the selected weapon
     */
    @Override
    public WeaponType getSelectedWeaponType() {
        return selectedWeapon.getType();
    }

    /**
     * Return the name of the weapon equipped by the character in turn
     */
    @Override
    public String getEquippedWeaponName() {
        IPlayer selectedCharacter = (IPlayer) controller.getSelectedCharacter();
        return selectedCharacter.getEquippedWeapon().getName();
    }

    /**
     * Return the damage of the weapon equipped by the character in turn
     */
    @Override
    public int getEquippedWeaponDamage() {
        IPlayer selectedCharacter = (IPlayer) controller.getSelectedCharacter();
        return selectedCharacter.getEquippedWeapon().getDamage();
    }

    /**
     * Return the weight of the weapon equipped by the character in turn
     */
    @Override
    public int getEquippedWeaponWeight() {
        IPlayer selectedCharacter = (IPlayer) controller.getSelectedCharacter();
        return selectedCharacter.getEquippedWeapon().getWeight();
    }

    /**
     * Return the enum type of the weapon equipped by the character in turn
     */
    @Override
    public WeaponType getEquippedWeaponType() {
        IPlayer selectedCharacter = (IPlayer) controller.getSelectedCharacter();
        return selectedCharacter.getEquippedWeapon().getType();
    }

    /**
     * Call the controller to equip the selected weapon.
     */
    @Override
    public void equipSelectedWeapon() {
        controller.equipOnSelectedCharacter(selectedWeaponIndex);
    }
}
