package com.github.cc3002.finalreality.model.character;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Abstract class containing the common tests for all the types of characters.
 *
 * @author Sebastian Olmos
 * @see ICharacter
 */
public abstract class AbstractCharacterTest {

    protected BlockingQueue<ICharacter> turns;
    protected ICharacter testCharacter;

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    protected abstract void waitTurnTest();

    /**
     * Assertion tests that sees the equality of testCharacter and other characters
     *
     * @param expectedCharacter
     *      Character expected to be equals to testCharacter
     * @param sameClassDifferentCharacter
     *      Same class character than testCharacter with different attributes
     * @param differentClassCharacter
     *      Different class character than testCharacter
     * @param foeCharacter
     *      Character of the opposite align than testCharacter
     */
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

    /**
     * Make the turns queue to test.
     */
    protected void basicSetup() {
        turns = new LinkedBlockingQueue<>();
    }

    /**
     * Prepare the character to test.
     */
    protected void assignCharacter(ICharacter someCharacter) {
        testCharacter = someCharacter;}


}
