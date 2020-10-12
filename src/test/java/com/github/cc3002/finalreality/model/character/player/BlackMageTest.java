package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BlackMageTest extends AbstractPlayerTest {

    private static final String BLACK_MAGE_NAME = "Vivi";

    @BeforeEach
    void setUp() {
        super.basicSetup();
        super.assignCharacter(new BlackMage(turns, BLACK_MAGE_NAME));
    }

    @Test
    void constructorTest() {
        super.checkConstruction(new BlackMage(turns, BLACK_MAGE_NAME),
                new BlackMage(turns, "Test"),
                new Knight(turns, BLACK_MAGE_NAME),
                new Enemy("Enemy", 10, turns));
        assertNotEquals(testCharacter, new Engineer(turns, BLACK_MAGE_NAME));
    }

}
