package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ThiefTest extends AbstractPlayerTest {

    private static final String THIEF_NAME = "Zidane";

    @BeforeEach
    void setUp() {
        super.basicSetup();
        super.assignCharacter(new Thief(turns, THIEF_NAME));
    }

    @Test
    void constructorTest() {
        super.checkConstruction(new Thief(turns, THIEF_NAME),
                new Thief(turns, "Test"),
                new WhiteMage(turns, THIEF_NAME),
                new Enemy("Enemy", 10, turns));
        assertNotEquals(testCharacter, new WhiteMage(turns, THIEF_NAME));
    }

}
