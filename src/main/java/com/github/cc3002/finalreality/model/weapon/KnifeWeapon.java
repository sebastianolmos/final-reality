package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.player.*;

import java.util.Objects;

/**
 * A class that holds all the information of a Knife.
 *
 * @author Sebastian Olmos.
 */
public class KnifeWeapon extends AbstractWeapon {

    /**
     * Creates a new Knife.
     *
     * @param name
     *     the knife's name
     * @param damage
     *     the knife's damage
     * @param weight
     *      the knife's weight
     */
    public KnifeWeapon(final String name, final int damage, final int weight){
        super(name, damage, weight);
    }

    @Override
    public WeaponType getType() {
        return WeaponType.KNIFE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KnifeWeapon)) {
            return false;
        }
        final KnifeWeapon weapon = (KnifeWeapon) o;
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
     * This weapon type can be equipped in a Knight.
     */
    @Override
    public void equipOnKnight(Knight player) {
        player.setEquippedWeapon(this);
    }

    /**
     * This weapon type can be equipped in a Thief.
     */
    @Override
    public void equipOnThief(Thief player) {
        player.setEquippedWeapon(this);
    }

}
