package com.github.cc3002.finalreality.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for prove the management of he turns and win/lose cases
 *
 * @author Sebastian Olmos
 * @see GameController
 */
public class TurnsManagementTest {
    GameController testController;

    /**
     * Test the getters of the character on turn.
     */
    @Test
    public void turnCharacterTest() throws InterruptedException {
        String[] NAMES = {"PARTY_CHARACTER", "ENEMY_CHARACTER"};
        int[] HEALTH_VALUES = {100, 500};
        int[] DEFENSE_VALUES = {50, 40};
        int[] DAMAGE_VALUES = {60, 80};
        testController = new GameController();
        testController.addKnightToParty(NAMES[0], HEALTH_VALUES[0], DEFENSE_VALUES[0]);
        testController.addSwordToInventory("WEAPON", 0, DAMAGE_VALUES[0]);
        testController.equipFromInventoryOn(0, 0);
        testController.addEnemyToEnemies(NAMES[1], HEALTH_VALUES[1], DEFENSE_VALUES[1], 0, DAMAGE_VALUES[1]);

        assertEquals("", testController.getTurnCharacterName());
        assertEquals(0, testController.getTurnCharacterHealth());
        assertEquals(0, testController.getTurnCharacterDefense());
        assertEquals(0, testController.getTurnCharacterDamage());

        int currentHealth = testController.getPartyCharacterHealth(0);
        testController.turnCharacterAttackTo(testController.getPartyCharacter(0));
        assertEquals(currentHealth, testController.getPartyCharacterHealth(0));

        testController.getPartyCharacter(0).waitTurn();
        Thread.sleep(10);
        testController.getEnemyCharacter(0).waitTurn();
        Thread.sleep(10);

        assertEquals(NAMES[0], testController.getTurnCharacterName());
        assertEquals(HEALTH_VALUES[0], testController.getTurnCharacterHealth());
        assertEquals(DEFENSE_VALUES[0], testController.getTurnCharacterDefense());
        assertEquals(DAMAGE_VALUES[0], testController.getTurnCharacterDamage());
        testController.endTurn();
        Thread.sleep(10);

        assertEquals(NAMES[1], testController.getTurnCharacterName());
        assertEquals(HEALTH_VALUES[1], testController.getTurnCharacterHealth());
        assertEquals(DEFENSE_VALUES[1], testController.getTurnCharacterDefense());
        assertEquals(DAMAGE_VALUES[1], testController.getTurnCharacterDamage());
        testController.endTurn();
    }

    /**
     * Test a run of turns when the user wins.
     */
    @Test
    public void partyCharacterWinTest() throws InterruptedException {
        testController = new GameController(1, 2, 5);
        testController.addKnightToParty("Link", 1000, 10);
        testController.addSwordToInventory("MasterSword", 0, 1000);
        testController.equipFromInventoryOn(0, 0);
        testController.addEnemyToEnemies("Bokoblin1", 10, 10, 0, 20);
        testController.addEnemyToEnemies("Bokoblin2", 10, 10, 0, 20);

        assertEquals(testController.getRemainingPartyCharacters(), 1);
        assertEquals(testController.getRemainingEnemies(), 2);
        assertFalse(testController.isTurnBeingPlayed());
        assertTrue(testController.isTurnsQueueEmpty());
        testController.getPartyCharacter(0).waitTurn();
        Thread.sleep(10);
        testController.getEnemyCharacter(0).waitTurn();
        Thread.sleep(10);
        testController.getEnemyCharacter(1).waitTurn();

        Thread.sleep(10);
        assertTrue(testController.isTurnBeingPlayed());
        assertEquals(testController.getTurnCharacterName(), "Link");
        testController.turnCharacterAttackTo(testController.getEnemyCharacter(0));
        testController.endTurn();
        assertFalse(testController.isTurnBeingPlayed());
        assertEquals(testController.getRemainingPartyCharacters(), 1);
        assertEquals(testController.getRemainingEnemies(), 1);

        Thread.sleep(10);
        assertTrue(testController.isTurnBeingPlayed());
        assertEquals(testController.getTurnCharacterName(), "Bokoblin1");
        assertEquals(testController.getTurnCharacterHealth(), 0);
        testController.endTurn();
        assertFalse(testController.isTurnBeingPlayed());

        Thread.sleep(10);
        assertTrue(testController.isTurnBeingPlayed());
        assertEquals(testController.getTurnCharacterName(), "Bokoblin2");
        testController.turnCharacterAttackTo(testController.getPartyCharacter(0));
        testController.endTurn();
        assertFalse(testController.isTurnBeingPlayed());
        assertEquals(testController.getRemainingPartyCharacters(), 1);
        assertEquals(testController.getRemainingEnemies(), 1);

        Thread.sleep(10);
        assertTrue(testController.isTurnBeingPlayed());
        assertEquals(testController.getTurnCharacterName(), "Link");
        testController.turnCharacterAttackTo(testController.getEnemyCharacter(1));
        assertEquals(testController.getTurnCharacterHealth(), 990);
        testController.endTurn();
        assertFalse(testController.isTurnBeingPlayed());
        assertEquals(testController.getRemainingPartyCharacters(), 1);
        assertEquals(testController.getRemainingEnemies(), 0);

        assertTrue(testController.checkWin());
        assertFalse(testController.checkDefeat());
    }

    /**
     * Test a run of turns when the user loses.
     */
    @Test
    public void enemyCharacterWinTest() throws InterruptedException {
        testController = new GameController(2, 1, 5);
        testController.addKnightToParty("Siegmeyer", 10, 10);
        testController.addKnightToParty("Solaire", 10, 10);
        testController.addSwordToInventory("Zweihander", 0, 20);
        testController.addSwordToInventory("Claymore", 0, 20);
        testController.equipFromInventoryOn(0, 0);
        testController.equipFromInventoryOn(0, 1);
        testController.addEnemyToEnemies("Gwyn", 1000, 10, 0, 1000);

        assertEquals(testController.getRemainingPartyCharacters(), 2);
        assertEquals(testController.getRemainingEnemies(), 1);
        assertFalse(testController.isTurnBeingPlayed());
        assertTrue(testController.isTurnsQueueEmpty());
        testController.getEnemyCharacter(0).waitTurn();
        Thread.sleep(10);
        testController.getPartyCharacter(0).waitTurn();
        Thread.sleep(10);
        testController.getPartyCharacter(1).waitTurn();

        Thread.sleep(10);
        assertTrue(testController.isTurnBeingPlayed());
        assertEquals(testController.getTurnCharacterName(), "Gwyn");
        testController.turnCharacterAttackTo(testController.getPartyCharacter(0));
        testController.endTurn();
        assertFalse(testController.isTurnBeingPlayed());
        assertEquals(testController.getRemainingPartyCharacters(), 1);
        assertEquals(testController.getRemainingEnemies(), 1);

        Thread.sleep(10);
        assertTrue(testController.isTurnBeingPlayed());
        assertEquals(testController.getTurnCharacterName(), "Siegmeyer");
        assertEquals(testController.getTurnCharacterHealth(), 0);
        testController.endTurn();
        assertFalse(testController.isTurnBeingPlayed());

        Thread.sleep(10);
        assertTrue(testController.isTurnBeingPlayed());
        assertEquals(testController.getTurnCharacterName(), "Solaire");
        testController.turnCharacterAttackTo(testController.getEnemyCharacter(0));
        testController.endTurn();
        assertFalse(testController.isTurnBeingPlayed());
        assertEquals(testController.getRemainingPartyCharacters(), 1);
        assertEquals(testController.getRemainingEnemies(), 1);

        Thread.sleep(10);
        assertTrue(testController.isTurnBeingPlayed());
        assertEquals(testController.getTurnCharacterName(), "Gwyn");
        testController.turnCharacterAttackTo(testController.getPartyCharacter(1));
        assertEquals(testController.getTurnCharacterHealth(), 990);
        testController.endTurn();
        assertFalse(testController.isTurnBeingPlayed());
        assertEquals(testController.getRemainingPartyCharacters(), 0);
        assertEquals(testController.getRemainingEnemies(), 1);

        assertTrue(testController.checkDefeat());
        assertFalse(testController.checkWin());
    }

}
