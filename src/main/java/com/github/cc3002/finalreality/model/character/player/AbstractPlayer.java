package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.NullWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * An abstract class that holds the common behaviour of all the playable characters in the game.
 * Can equip a weapon.
 *
 * @author Sebastian Olmos
 */
public abstract class AbstractPlayer extends AbstractCharacter implements IPlayer {

    private IWeapon equippedWeapon = NullWeapon.getInstance();

    protected AbstractPlayer(@NotNull BlockingQueue<ICharacter> turnsQueue,
                             @NotNull String name, int health, int defense) {
        super(turnsQueue, name, health, defense);
    }

    @Override
    public IWeapon getEquippedWeapon() {
        return equippedWeapon;
    }

    /**
     * Change the equipped weapon.
     * @param weapon
     *     weapon to equip.
     */
    public void setEquippedWeapon(IWeapon weapon) {
        equippedWeapon = weapon;
    }

    /**
     * Those character can equip weapon, so his weapon's weight define his waitTurn time.
     */
    @Override
    public int getWeaponWeight() {
        return getEquippedWeapon().getWeight();
    }

    @Override
    public int getDamage() {
        return equippedWeapon.getDamage();
    }

    /**
     * Those character can be played by the user.
     */
    @Override
    public boolean isPlayable() {
        return true;
    }

}
