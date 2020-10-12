package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class Engineer extends AbstractPlayer {

    public Engineer(@NotNull BlockingQueue<ICharacter> turnsQueue,
                    @NotNull String name) {
        super(turnsQueue, name);
    }

    @Override
    public CharacterClass getCharacterClass(){
        return CharacterClass.ENGINEER;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Engineer)) {
            return false;
        }
        final Engineer that = (Engineer) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCharacterClass(), getName());
    }

}
