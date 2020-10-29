package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.player.*;

import java.util.Objects;

/**
 * A class that holds all the information of a Staff.
 *
 * @author Sebastian Olmos.
 */
public class StaffWeapon extends AbstractWeapon {

    /**
     * Creates a new Staff.
     *
     * @param name
     *     the staff's name
     * @param damage
     *     the staff's damage
     * @param weight
     *      the staff's weight
     */
    public StaffWeapon(final String name, final int damage, final int weight){
        super(name, damage, weight);
    }

    @Override
    public WeaponType getType() {
        return WeaponType.STAFF;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StaffWeapon)) {
            return false;
        }
        final StaffWeapon weapon = (StaffWeapon) o;
        return getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDamage(), getWeight(), getType());
    }

    /**
     * This weapon type can be equipped in a Black Mage.
     */
    @Override
    public void equipOnBlackMage(BlackMage player) {
        player.setEquippedWeapon(this);
    }

    /**
     * This weapon type can be equipped in a White Mage.
     */
    @Override
    public void equipOnWhiteMage(WhiteMage player) {
        player.setEquippedWeapon(this);
    }

}
