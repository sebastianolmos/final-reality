package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.player.*;

import java.util.Objects;

/**
 * A class that holds all the information of an Axe.
 *
 * @author Sebastian Olmos.
 */
public class AxeWeapon extends AbstractWeapon {

    /**
     * Creates a new Axe.
     *
     * @param name
     *     the axe's name
     * @param damage
     *     the axe's damage
     * @param weight
     *      the axe's weight
     */
    public AxeWeapon(final String name, final int damage, final int weight){
        super(name, damage, weight);
    }

    @Override
    public WeaponType getType() {
        return WeaponType.AXE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AxeWeapon)) {
            return false;
        }
        final AxeWeapon weapon = (AxeWeapon) o;
        return getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDamage(), getWeight(), getType());
    }

    /**
     * This weapon type can be equipped in an Engineer.
     */
    @Override
    public void equipOnEngineer(Engineer player) {
        player.setEquippedWeapon(this);
    }

    /**
     * This weapon type can be equipped in a Knight.
     */
    @Override
    public void equipOnKnight(Knight player) {
        player.setEquippedWeapon(this);
    }

}
