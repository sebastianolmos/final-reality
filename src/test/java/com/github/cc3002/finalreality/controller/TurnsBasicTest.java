package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.BlackMage;
import com.github.cc3002.finalreality.model.character.player.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test the critical parts of the turn, which are the beginning and ending
 *
 * @author Sebastian Olmos
 * @see GameController
 */
public class TurnsBasicTest {
    private GameController controller;
    private BlockingQueue<ICharacter> dummyQueue = new LinkedBlockingQueue<>();

    /**
     * Create the controller, add some characters and weapons, and set the enemy wait time
     */
    @BeforeEach
    public void setUp() {
        controller = new GameController(2,1,1);
        controller.addKnightToParty("partyCharacter1", 100, 20);
        controller.addEnemyToEnemies("enemyCharacter", 100, 20, 0, 1000);
        controller.addSwordToInventory("swordWeapon", 0, 1000);
        controller.equipFromInventoryOn(0, 0);
        controller.setEnemyWaitTime(50);
    }

    /**
     * Test to check that the tryBeginTurn() dont work in the incorrect places.
     */
    @Test
    public void startTurnTest() {
        controller.basicTurnSetup();
        assertTrue(controller.isSelectingAction());
        controller.toAttackTargetSelection();
        assertTrue(controller.isSelectingAttackTarget());
        controller.tryBeginTurn();
        assertFalse(controller.isSelectingAction());

        controller.toActionSelection();
        controller.toWeaponSelection();
        assertTrue(controller.isSelectingWeapon());
        controller.tryBeginTurn();
        assertFalse(controller.isSelectingAction());
    }

    /**
     * Test to check that the endTurn() methods works in the correct phase, and the selected character change
     */
    @Test
    public void endTurnTest(){
        assertTrue(controller.isTurnsQueueEmpty());
        controller.addBlackMageToParty("partyCharacter2", 90, 30);
        controller.basicTurnSetup();
        assertEquals(controller.getSelectedCharacter(),
                new Knight(dummyQueue, "partyCharacter1", 100, 20));
        controller.toAttackTargetSelection();
        assertTrue(controller.isSelectingAttackTarget());
        controller.endTurn();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(controller.getSelectedCharacter().getName(), "partyCharacter2");
        assertTrue(controller.isSelectingAction());
        assertEquals(controller.getSelectedCharacter(),
                new BlackMage(dummyQueue, "partyCharacter2", 90, 30));

    }

    /**
     * Test to check that attacking other character end the current turn and damage the target
     */
    @Test
    public void targetSelectAndAttackTest(){
        controller.basicTurnSetup();
        controller.toAttackTargetSelection();
        controller.targetEnemyCharacter(0);
        controller.attackTarget();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(controller.getSelectedCharacter(),
                new Knight(dummyQueue, "partyCharacter1", 100, 20));
        assertFalse(controller.getEnemyCharacter(0).itsAlive());
    }

    /**
     * Test that check the automatic turn of the enemy, that kills the playable character.
     */
    @Test
    public void enemyTurnTest() {
        controller.basicTurnSetup();
        controller.toAttackTargetSelection();
        controller.endTurn();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(controller.getPartyCharacter(0).itsAlive());
    }
}
