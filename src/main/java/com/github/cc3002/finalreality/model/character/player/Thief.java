package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a Thief Character.
 *
 * @author Sebastian Olmos.
 */
public class Thief extends AbstractPlayer {

    /**
     * Creates a new Thief.
     *
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param name
     *     the character's name
     */
    public Thief(@NotNull BlockingQueue<ICharacter> turnsQueue,
                 @NotNull String name, int health, int defense) {
        super(turnsQueue, name, health, defense);
    }

    public Thief(@NotNull BlockingQueue<ICharacter> turnsQueue,
                 @NotNull String name) {
        this(turnsQueue, name, 25, 7);
    }

    @Override
    public CharacterClass getCharacterClass(){
        return CharacterClass.THIEF;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Thief)) {
            return false;
        }
        final Thief that = (Thief) o;
        return getName().equals(that.getName()) &&
                getHealth() == that.getHealth() &&
                getDefense() == that.getDefense();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHealth(), getDefense());
    }

    /**
     * Equip a weapon on a Thief character if it's alive.
     */
    @Override
    public void equip(IWeapon weapon) {
        if (itsAlive()) {
            weapon.equipOnThief(this);
        }
    }

}
