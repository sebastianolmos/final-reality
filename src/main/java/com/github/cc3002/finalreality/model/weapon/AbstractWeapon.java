package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.player.*;

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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    /**
     * Returns nothing to default equip methods.
     */
    @Override
    public void equipOnBlackMage(BlackMage player) {}

    @Override
    public void equipOnEngineer(Engineer player) {}

    @Override
    public void equipOnKnight(Knight player) {}

    @Override
    public void equipOnThief(Thief player) {}

    @Override
    public void equipOnWhiteMage(WhiteMage player) {}
}
