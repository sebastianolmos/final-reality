package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a White Mage Character.
 *
 * @author Sebastian Olmos.
 */
public class WhiteMage extends AbstractMage {

    /**
     * Creates a new White Mage.
     *
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param name
     *     the character's name
     */
    public WhiteMage(@NotNull BlockingQueue<ICharacter> turnsQueue,
                     @NotNull String name){
        super(turnsQueue, name);
    }

    @Override
    public CharacterClass getCharacterClass(){
        return CharacterClass.WHITE_MAGE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WhiteMage)) {
            return false;
        }
        final WhiteMage that = (WhiteMage) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCharacterClass(), getName());
    }

}
