package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.weapon.types.WeaponType;

public interface IWeapon {

    WeaponType getType();

    int getWeight();
}
