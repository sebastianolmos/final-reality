package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.player.*;

import java.util.Objects;
/**
 * A class that holds all the information of an Axe.
 *
 * @author Sebastian Olmos.
 */
public class NullWeapon implements IWeapon{
    private static NullWeapon nullWeaponInstance;

    private NullWeapon() {
    }

    /**
     * Return the static instance of the null weapon.
     */
    public static NullWeapon getInstance() {
        return nullWeaponInstance == null ? new NullWeapon() : nullWeaponInstance;
    }

    @Override
    public WeaponType getType() {
        return WeaponType.NULL;
    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void equipOnBlackMage(BlackMage player) {

    }

    @Override
    public void equipOnEngineer(Engineer player) {

    }

    @Override
    public void equipOnKnight(Knight player) {

    }

    @Override
    public void equipOnThief(Thief player) {

    }

    @Override
    public void equipOnWhiteMage(WhiteMage player) {

    }

    /**
     * There are equals if they share the same static instance.
     */
    @Override
    public boolean equals(final Object o) {
        return (o instanceof NullWeapon);
    }
}
