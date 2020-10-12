package com.github.cc3002.finalreality.model.character;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public abstract class AbstractCharacterTest {

    protected BlockingQueue<ICharacter> turns;
    protected ICharacter testCharacter;

    protected abstract void waitTurnTest();

    protected void checkConstruction(final ICharacter expectedCharacter,
                                     final ICharacter sameClassDifferentCharacter,
                                     final ICharacter differentClassCharacter,
                                     final ICharacter foeCharacter){

        assertEquals(testCharacter, testCharacter);
        assertEquals(testCharacter, expectedCharacter);
        assertEquals(testCharacter.hashCode(), expectedCharacter.hashCode());
        assertNotEquals(sameClassDifferentCharacter, testCharacter);
        assertNotEquals(sameClassDifferentCharacter.hashCode(), testCharacter.hashCode());
        assertNotEquals(differentClassCharacter, testCharacter);
        assertNotEquals(differentClassCharacter.hashCode(), testCharacter.hashCode());
        assertNotEquals(foeCharacter, testCharacter);
        assertNotEquals(foeCharacter.hashCode(), testCharacter.hashCode());
    }

    protected void basicSetup() {
        turns = new LinkedBlockingQueue<>();
    }

    protected void assignCharacter(ICharacter someCharacter) {testCharacter = someCharacter;}


}
