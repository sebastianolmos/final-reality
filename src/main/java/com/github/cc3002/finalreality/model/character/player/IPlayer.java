package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.weapon.IWeapon;

/**
 * This represents a character that can be controlled by the player.
 *
 * @author Sebastián Olmos.
 */
public interface IPlayer {

    /**
     * Equips a weapon to the character.
     */
    void equip(IWeapon weapon);

    /**
     * Return this character's equipped weapon.
     */
    IWeapon getEquippedWeapon();

}
