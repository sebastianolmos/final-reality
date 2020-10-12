package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EngineerTest extends AbstractPlayerTest {

    private static final String ENGINEER_NAME = "Cid";

    @BeforeEach
    void setUp() {
        super.basicSetup();
        super.assignCharacter(new Engineer(turns, ENGINEER_NAME));
    }

    @Test
    void constructorTest() {
        super.checkConstruction(new Engineer(turns, ENGINEER_NAME),
                new Engineer(turns, "Test"),
                new WhiteMage(turns, ENGINEER_NAME),
                new Enemy("Enemy", 10, turns));
        assertNotEquals(testCharacter, new Knight(turns, ENGINEER_NAME));
    }

}
