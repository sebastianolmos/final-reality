package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.weapon.IWeapon;

public interface IPlayer {

    void equip(IWeapon weapon);

    IWeapon getEquippedWeapon();

}
