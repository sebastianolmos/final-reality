package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * An abstract class that holds the common behaviour of all the playable characters in the game
 * that can use mage skills that use mana.
 *
 * @author Sebastian Olmos
 */
public abstract class AbstractMage extends AbstractPlayer {

    private int mana;

    protected AbstractMage(@NotNull BlockingQueue<ICharacter> turnsQueue,
                           @NotNull String name) {
        super(turnsQueue, name);
        mana = 0;
    }

}
