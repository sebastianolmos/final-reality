package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.BlackMage;
import com.github.cc3002.finalreality.model.character.player.CharacterClass;
import com.github.cc3002.finalreality.model.character.player.Knight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EnemyTest extends AbstractCharacterTest{

    private static final String ENEMY_NAME = "Goblin";

    @BeforeEach
    void setUp() {
        super.basicSetup();
        super.assignCharacter(new Enemy(ENEMY_NAME, 10, turns));
    }

    @Test
    public void waitTurnTest() {
        Assertions.assertTrue(turns.isEmpty());
        testCharacter.waitTurn();
        try {
            // Thread.sleep is not accurate so this values may be changed to adjust the
            // acceptable error margin.
            // We're testing that the character waits approximately 1 second.
            Thread.sleep(900);
            assertEquals(0, turns.size());
            Thread.sleep(200);
            assertEquals(1, turns.size());
            assertEquals(testCharacter, turns.peek());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void constructorTest() {
        super.checkConstruction(new Enemy(ENEMY_NAME, 10, turns),
                new Enemy("Test", 10, turns),
                new Knight(turns, ENEMY_NAME),
                new BlackMage(turns, ENEMY_NAME));
        assertEquals(testCharacter.getCharacterClass(), CharacterClass.ENEMY);
        assertNotEquals(new Enemy(ENEMY_NAME, 14, turns), testCharacter);
    }

}
