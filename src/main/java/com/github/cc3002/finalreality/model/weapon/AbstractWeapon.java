package com.github.cc3002.finalreality.model.weapon;

/**
 * An abstract class that holds the common behaviour of all the weapons in the game.
 *
 * @author Sebastian Olmos.
 */
public abstract class AbstractWeapon implements IWeapon{
    private final String name;
    private final int damage;
    private final int weight;

    protected AbstractWeapon(final String name, final int damage, final int weight){
        this.name = name;
        this.damage = damage;
        this.weight = weight;
    }
    /**
     * Returns the weapon's name.
     */
    protected String getName() {
        return name;
    }

    /**
     * Returns the weapon's damage.
     */
    protected int getDamage() {
        return damage;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
