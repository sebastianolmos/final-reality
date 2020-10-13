package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * An abstract class that holds the common behaviour of all the playable characters in the game.
 * Can equip a weapon.
 *
 * @author Sebastian Olmos
 */
public abstract class AbstractPlayer extends AbstractCharacter implements IPlayer {

    private IWeapon equippedWeapon = null;

    protected AbstractPlayer(@NotNull BlockingQueue<ICharacter> turnsQueue,
                             @NotNull String name) {
        super(turnsQueue, name);
    }

    @Override
    public void equip(IWeapon weapon) {
        this.equippedWeapon = weapon;
    }

    @Override
    public IWeapon getEquippedWeapon() {
        return equippedWeapon;
    }

    /**
     * Those character can equip weapon, so his weapon's weight define his waitTurn time.
     */
    @Override
    public int getWeaponWeight() {
        return getEquippedWeapon().getWeight();
    }

}
