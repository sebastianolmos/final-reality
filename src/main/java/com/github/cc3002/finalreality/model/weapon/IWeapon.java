package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.player.*;

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

    /**
     * Returns this weapon's damage.
     */
    int getDamage();

    /**
     * Returns the weapon's name.
     */
    String getName();

    /**
     * Equip a weapon on a BlackMage character if it's compatible.
     * @param player
     *     BlackMage character to equip the weapon.
     */
    void equipOnBlackMage(BlackMage player);

    /**
     * Equip a weapon on a Engineer character if it's compatible.
     * @param player
     *     Engineer character to equip the weapon.
     */
    void equipOnEngineer(Engineer player);

    /**
     * Equip a weapon on a Knight character if it's compatible.
     * @param player
     *     Knight character to equip the weapon.
     */
    void equipOnKnight(Knight player);

    /**
     * Equip a weapon on a Thief character if it's compatible.
     * @param player
     *     Thief character to equip the weapon.
     */
    void equipOnThief(Thief player);

    /**
     * Equip a weapon on a WhiteMage character if it's compatible.
     * @param player
     *     WhiteMage character to equip the weapon.
     */
    void equipOnWhiteMage(WhiteMage player);
}
