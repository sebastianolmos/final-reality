package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Set of tests for the Knight character.
 *
 * @author Sebastian Olmos.
 * @see Knight
 */
public class KnightTest extends AbstractPlayerTest {

    private static final String KNIGHT_NAME = "Adelbert";

    /**
     * Setup method.
     * Creates a new knight named Adelbert with 10 speed and links it to a turn queue.
     */
    @BeforeEach
    void setUp() {
        super.basicSetup();
        super.assignCharacter(new Knight(turns, KNIGHT_NAME));
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        super.checkConstruction(new Knight(turns, KNIGHT_NAME),
                new Knight(turns, "Test"),
                new BlackMage(turns, KNIGHT_NAME),
                new Enemy("Enemy", 10, turns));
        assertNotEquals(testCharacter, new Thief(turns, KNIGHT_NAME));
    }

}
