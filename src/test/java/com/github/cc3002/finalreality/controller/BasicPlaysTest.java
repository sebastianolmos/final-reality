package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.model.character.ICharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to simulate basic battles to prove a win and a lose scenario.
 *
 * @author Sebastian Olmos
 * @see GameController
 */
public class BasicPlaysTest {
    private GameController controller;

    /**
     * Create the controller ans set the enemy wait time to make the test faster
     */
    @BeforeEach
    public void setUp() {
        controller = new GameController(2,2,2);
        controller.setEnemyWaitTime(50);
    }

    /**
     * Simulate a battle when the player win
     */
    @Test
    public void winningPlayTest() throws InterruptedException {
        controller.addKnightToParty("Link", 1000, 10);
        controller.addSwordToInventory("MasterSword", 0, 1000);
        controller.addEnemyToEnemies("Bokoblin1", 20, 10, 0, 20);
        controller.addEnemyToEnemies("Bokoblin2", 20, 10, 0, 30);

        assertEquals(controller.getRemainingPartyCharacters(), 1);
        assertEquals(controller.getRemainingEnemies(), 2);

        controller.basicTurnSetup();

        assertEquals(controller.getSelectedCharacter().getName(), "Link");

        controller.toWeaponSelection();
        controller.selectWeapon(0);
        controller.equipSelectedWeapon();
        controller.toActionSelection();

        controller.toAttackTargetSelection();
        assertTrue(controller.isSelectingAttackTarget());
        controller.targetEnemyCharacter(0);
        controller.attackTarget();
        assertEquals(controller.getRemainingEnemies(), 1);

        Thread.sleep(300);

        assertEquals(controller.getRemainingPartyCharacters(), 1);
        assertEquals(controller.getRemainingEnemies(), 1);
        assertFalse(controller.getEnemyCharacter(0).itsAlive());
        assertTrue(controller.getEnemyCharacter(1).itsAlive());
        assertTrue(controller.isSelectingAction());
        assertEquals(controller.getPartyCharacter(0).getHealth(), 980);
        assertEquals(controller.getSelectedCharacter().getName(), "Link");
        controller.toAttackTargetSelection();
        controller.targetEnemyCharacter(1);
        controller.attackTarget();
        assertEquals(controller.getRemainingEnemies(), 0);

        assertTrue(controller.checkWin());
        assertFalse(controller.checkDefeat());

    }
    /**
     * Simulate a battle when the player lose
     */
    @Test
    public void losingPlay() throws InterruptedException {
        controller.addKnightToParty("Siegmeyer", 100, 10);
        controller.addKnightToParty("Solaire", 100, 10);
        controller.addSwordToInventory("Zweihander", 0, 20);
        controller.addSwordToInventory("Claymore", 0, 20);
        controller.equipFromInventoryOn(0, 0);
        controller.equipFromInventoryOn(0, 1);
        controller.addEnemyToEnemies("Gwyn", 1000, 10, 0, 50);

        assertEquals(controller.getRemainingPartyCharacters(), 2);
        assertEquals(controller.getRemainingEnemies(), 1);

        controller.basicTurnSetup();

        assertEquals(controller.getSelectedCharacter().getName(), "Siegmeyer");
        controller.toAttackTargetSelection();
        controller.targetEnemyCharacter(0);
        controller.attackTarget();
        assertEquals(controller.getRemainingEnemies(), 1);
        assertEquals(controller.getEnemyCharacter(0).getHealth(), 990);

        Thread.sleep(100);
        assertEquals(controller.getSelectedCharacter().getName(), "Solaire");
        controller.toAttackTargetSelection();
        controller.targetEnemyCharacter(0);
        controller.attackTarget();
        assertEquals(controller.getRemainingEnemies(), 1);
        assertEquals(controller.getEnemyCharacter(0).getHealth(), 980);

        Thread.sleep(100);
        assertEquals(controller.getRemainingPartyCharacters(), 2);
        assertEquals(controller.getRemainingEnemies(), 1);
        assertTrue(controller.getPartyCharacter(0).getHealth() == 60 ||
                controller.getPartyCharacter(1).getHealth() == 60);
    }

}
