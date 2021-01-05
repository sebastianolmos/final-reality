package com.github.cc3002.finalreality.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests that simulate a very basic battle
 *
 * @author Sebastian Olmos
 * @see GameController
 */
public class VeryBasicBattleTest {
    private GameController controller;

    /**
     * Create the controller an set the enemy wait time to make the test faster
     */
    @BeforeEach
    public void setUp() {
        controller = new GameController(2,2,2);
        controller.setEnemyWaitTime(50);
    }

    /**
     * Simulate the battle when the winner is the player
     */
    @Test
    public void winningPlayTest() throws InterruptedException {
        controller.addKnightToParty("Link", 1000, 5);
        controller.addSwordToInventory("MasterSword", 0, 15);
        controller.addEnemyToEnemies("Bokoblin1", 20, 10, 0, 40);

        assertEquals(controller.getRemainingPartyCharacters(), 1);
        assertEquals(controller.getRemainingEnemies(), 1);

        controller.basicTurnSetup();

        assertEquals(controller.getSelectedCharacter().getName(), "Link");

        controller.toWeaponSelection();
        controller.selectWeapon(0);
        controller.equipSelectedWeapon();
        controller.toActionSelection();

        controller.toAttackTargetSelection();
        assertTrue(controller.isSelectingAttackTarget());
        controller.targetEnemyCharacter(1);
        controller.attackTarget();

        Thread.sleep(300);

        assertEquals(controller.getRemainingPartyCharacters(), 1);
        assertEquals(controller.getRemainingEnemies(), 1);
        assertTrue(controller.getPartyCharacter(0).itsAlive());
        assertTrue(controller.isSelectingAction());
        assertEquals(controller.getEnemyCharacterDamage(0), 40);
        assertEquals(controller.getPartyCharacter(0).getHealth(), 965);
        assertEquals(controller.getSelectedCharacter().getName(), "Link");

    }
}
