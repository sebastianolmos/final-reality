package com.github.cc3002.finalreality.model.weapon;

/**
 * This represents a weapon from the game that can be equipped by the player's character
 *
 * @author Sebastián Olmos.
 */
public interface IWeapon {

    /**
     * Returns this weapon's type.
     */
    WeaponType getType();

    /**
     * Returns this weapon's weight.
     */
    int getWeight();
}
