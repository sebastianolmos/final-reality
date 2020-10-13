package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Set of tests for the BlackMage character.
 *
 * @author Sebastian Olmos.
 * @see BlackMage
 */
public class BlackMageTest extends AbstractPlayerTest {

    private static final String BLACK_MAGE_NAME = "Vivi";

    /**
     * Setup method.
     * Creates a new black mage named Vivi with 10 speed and links it to a turn queue.
     */
    @BeforeEach
    void setUp() {
        super.basicSetup();
        super.assignCharacter(new BlackMage(turns, BLACK_MAGE_NAME));
    }

    /**
     * Checks that the class constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        super.checkConstruction(new BlackMage(turns, BLACK_MAGE_NAME),
                new BlackMage(turns, "Test"),
                new Knight(turns, BLACK_MAGE_NAME),
                new Enemy("Enemy", 10, turns));
        assertNotEquals(testCharacter, new Engineer(turns, BLACK_MAGE_NAME));
    }

}
