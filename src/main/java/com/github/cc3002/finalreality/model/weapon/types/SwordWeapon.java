package com.github.cc3002.finalreality.model.weapon.types;

import com.github.cc3002.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

public class SwordWeapon extends AbstractWeapon {

    public SwordWeapon(final String name, final int damage, final int weight){
        super(name, damage, weight);
    }

    @Override
    public WeaponType getType() {
        return WeaponType.SWORD;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SwordWeapon)) {
            return false;
        }
        final SwordWeapon weapon = (SwordWeapon) o;
        return getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDamage(), getWeight(), getType());
    }

}
