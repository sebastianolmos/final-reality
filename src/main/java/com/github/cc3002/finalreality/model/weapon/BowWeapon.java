package com.github.cc3002.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that holds all the information of a Bow.
 *
 * @author Sebastian Olmos.
 */
public class BowWeapon extends AbstractWeapon {

    /**
     * Creates a new Bow.
     *
     * @param name
     *     the bow's name
     * @param damage
     *     the bow's damage
     * @param weight
     *      the bow's weight
     */
    public BowWeapon(final String name, final int damage, final int weight){
        super(name, damage, weight);
    }

    @Override
    public WeaponType getType() {
        return WeaponType.BOW;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BowWeapon)) {
            return false;
        }
        final BowWeapon weapon = (BowWeapon) o;
        return getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDamage(), getWeight(), getType());
    }

}
