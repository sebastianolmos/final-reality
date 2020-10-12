package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class KnightTest extends AbstractPlayerTest {

    private static final String KNIGHT_NAME = "Adelbert";

    @BeforeEach
    void setUp() {
        super.basicSetup();
        super.assignCharacter(new Knight(turns, KNIGHT_NAME));
    }

    @Test
    void constructorTest() {
        super.checkConstruction(new Knight(turns, KNIGHT_NAME),
                new Knight(turns, "Test"),
                new BlackMage(turns, KNIGHT_NAME),
                new Enemy("Enemy", 10, turns));
        assertNotEquals(testCharacter, new Thief(turns, KNIGHT_NAME));
    }

}
