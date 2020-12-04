package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.character.ICharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Tests for prove the management of the enemy characters in the controller
 *
 * @author Sebastian Olmos
 * @see GameController
 */
public class EnemiesManagementTest {
    private static final String[] NAMES = {"CREEPER", "ZOMBIE", "ENDERMAN"};
    private static final int[] HEALTH_VALUES = {100, 80, 120};
    private static final int[] DEFENSE_VALUES = {30, 40, 30};
    private static final int[] WEIGHT_VALUES = {30, 20, 30};
    private static final int[] DAMAGE_VALUES = {100, 30, 60};
    private final Enemy[] CHARACTERS = new Enemy[3];
    private final BlockingQueue<ICharacter> dummyTurns = new LinkedBlockingQueue<>();
    private final GameController testController = new GameController(3,3,10);

    /**
     * Create a set of character to compare.
     */
    @BeforeEach
    public void setUp() {
        CHARACTERS[0] = new Enemy(NAMES[0], WEIGHT_VALUES[0], dummyTurns, HEALTH_VALUES[0], DEFENSE_VALUES[0], DAMAGE_VALUES[0]);
        CHARACTERS[1] = new Enemy(NAMES[1], WEIGHT_VALUES[1], dummyTurns, HEALTH_VALUES[1], DEFENSE_VALUES[1], DAMAGE_VALUES[1]);
        CHARACTERS[2] = new Enemy(NAMES[2], WEIGHT_VALUES[2], dummyTurns, HEALTH_VALUES[2], DEFENSE_VALUES[2], DAMAGE_VALUES[2]);
    }

    /**
     * Make several tests of creation, addition, getters and basic attack
     */
    @Test
    public void managementTest() {
        assertEquals(testController.getRemainingEnemies(), 0);

        testController.addEnemyToEnemies(NAMES[0], HEALTH_VALUES[0], DEFENSE_VALUES[0], WEIGHT_VALUES[0], DAMAGE_VALUES[0]);
        assertEquals(testController.getRemainingEnemies(), 1);
        assertEquals(testController.getEnemyCharacter(0), CHARACTERS[0]);

        testController.addEnemyToEnemies(NAMES[1], HEALTH_VALUES[1], DEFENSE_VALUES[1], WEIGHT_VALUES[1], DAMAGE_VALUES[1]);
        assertEquals(testController.getRemainingEnemies(), 2);
        assertEquals(testController.getEnemyCharacter(1), CHARACTERS[1]);

        testController.addEnemyToEnemies(NAMES[2], HEALTH_VALUES[2], DEFENSE_VALUES[2], WEIGHT_VALUES[2], DAMAGE_VALUES[2]);
        assertEquals(testController.getRemainingEnemies(), 3);
        assertEquals(testController.getEnemyCharacter(2), CHARACTERS[2]);

        Enemy testEnemy = new Enemy("OverloadTest", 10, dummyTurns, 20, 5, 10);
        testController.addEnemyToEnemies("OverloadTest", 20, 5, 10, 10);
        assertEquals(testController.getRemainingEnemies(), 3);
        assertNotEquals(testController.getEnemyCharacter(2), testEnemy);

        gettersTest();
        attackTest();
    }

    /**
     * Test the getters with its creation values
     */
    public void gettersTest() {
        assertEquals(testController.getEnemyCharacterName(0), NAMES[0]);
        assertEquals(testController.getEnemyCharacterHealth(0), HEALTH_VALUES[0]);
        assertEquals(testController.getEnemyCharacterDefense(0), DEFENSE_VALUES[0]);
        assertEquals(testController.getEnemyCharacterDamage(0), DAMAGE_VALUES[0]);
        assertEquals(testController.getEnemyCharacterWeight(0), WEIGHT_VALUES[0]);
        assertEquals(testController.getEnemyCharacterName(1), NAMES[1]);
        assertEquals(testController.getEnemyCharacterHealth(1), HEALTH_VALUES[1]);
        assertEquals(testController.getEnemyCharacterDefense(1), DEFENSE_VALUES[1]);
        assertEquals(testController.getEnemyCharacterDamage(1), DAMAGE_VALUES[1]);
        assertEquals(testController.getEnemyCharacterWeight(1), WEIGHT_VALUES[1]);
        assertEquals(testController.getEnemyCharacterName(2), NAMES[2]);
        assertEquals(testController.getEnemyCharacterHealth(2), HEALTH_VALUES[2]);
        assertEquals(testController.getEnemyCharacterDefense(2), DEFENSE_VALUES[2]);
        assertEquals(testController.getEnemyCharacterDamage(2), DAMAGE_VALUES[2]);
        assertEquals(testController.getEnemyCharacterWeight(2), WEIGHT_VALUES[2]);
    }

    /**
     * Test a basic attack made with the controller.
     */
    public void attackTest() {
        int currentHealth = testController.getEnemyCharacterHealth(1);
        testController.attackTo(testController.getEnemyCharacter(0), testController.getEnemyCharacter(1));
        assertNotEquals(currentHealth, testController.getEnemyCharacterHealth(1));
    }

}
