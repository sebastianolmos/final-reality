package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WhiteMageTest extends AbstractPlayerTest {

    private static final String WHITE_MAGE_NAME = "Eiko";

    @BeforeEach
    void setUp() {
        super.basicSetup();
        super.assignCharacter(new WhiteMage(turns, WHITE_MAGE_NAME));
    }

    @Test
    void constructorTest() {
        super.checkConstruction(new WhiteMage(turns, WHITE_MAGE_NAME),
                new WhiteMage(turns, "Test"),
                new Knight(turns, WHITE_MAGE_NAME),
                new Enemy("Enemy", 10, turns));
        assertNotEquals(testCharacter, new BlackMage(turns, WHITE_MAGE_NAME));
    }

}
