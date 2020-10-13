package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Set of tests for the Thief character.
 *
 * @author Sebastian Olmos.
 * @see Thief
 */
public class ThiefTest extends AbstractPlayerTest {

    private static final String THIEF_NAME = "Zidane";

    /**
     * Setup method.
     * Creates a new thief named Zidane with 10 speed and links it to a turn queue.
     */
    @BeforeEach
    void setUp() {
        super.basicSetup();
        super.assignCharacter(new Thief(turns, THIEF_NAME));
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        super.checkConstruction(new Thief(turns, THIEF_NAME),
                new Thief(turns, "Test"),
                new WhiteMage(turns, THIEF_NAME),
                new Enemy("Enemy", 10, turns));
        assertNotEquals(testCharacter, new WhiteMage(turns, THIEF_NAME));
    }

}
