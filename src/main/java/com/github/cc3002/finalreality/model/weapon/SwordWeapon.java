package com.github.cc3002.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that holds all the information of a Sword.
 *
 * @author Sebastian Olmos.
 */
public class SwordWeapon extends AbstractWeapon {

    /**
     * Creates a new Sword.
     *
     * @param name
     *     the sword's name
     * @param damage
     *     the sword's damage
     * @param weight
     *      the sword's weight
     */
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
