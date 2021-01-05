package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.gui.scenes.GameOverScene;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to check if the methods for win/lose works properly
 *
 * @author Sebastian Olmos
 * @see GameController
 */
public class WinConditionsTest {
    private GameController controller;

    /**
     * Create the controller and add some characters
     */
    @BeforeEach
    public void setUp() {
        controller = new GameController(3,3,1);
        controller.addWhiteMageToParty("whiteMage", 150, 50);
        controller.addEngineerToParty("engineer", 120, 40);
        controller.addKnightToParty("Knight", 300, 100);

        controller.addEnemyToEnemies("Enemy1", 150, 40, 50, 20);
        controller.addEnemyToEnemies("Enemy2", 170, 40, 50, 20);
        controller.addEnemyToEnemies("Enemy3", 140, 40, 50, 20);

    }

    /**
     * Test that the defeat/lose methods work properly
     */
    @Test
    public void CheckLoseTest() {
        assertEquals(controller.getRemainingPartyCharacters(), 3);

        controller.updatePartyLeft();
        assertEquals(controller.getRemainingPartyCharacters(), 2);
        assertFalse(controller.checkWin());
        assertFalse(controller.checkDefeat());

        controller.updatePartyLeft();
        assertEquals(controller.getRemainingPartyCharacters(), 1);
        assertFalse(controller.checkWin());
        assertFalse(controller.checkDefeat());

        controller.updatePartyLeft();
        assertEquals(controller.getRemainingPartyCharacters(), 0);
        assertFalse(controller.checkWin());
        assertTrue(controller.checkDefeat());
    }

    /**
     * Test that the victory/win methods work properly
     */
    @Test
    public void CheckWinTest() {
        assertEquals(controller.getRemainingEnemies(), 3);

        controller.updateEnemiesLeft();
        assertEquals(controller.getRemainingEnemies(), 2);
        assertFalse(controller.checkWin());
        assertFalse(controller.checkDefeat());

        controller.updateEnemiesLeft();
        assertEquals(controller.getRemainingEnemies(), 1);
        assertFalse(controller.checkWin());
        assertFalse(controller.checkDefeat());

        controller.updateEnemiesLeft();
        assertEquals(controller.getRemainingEnemies(), 0);
        assertTrue(controller.checkWin());
        assertFalse(controller.checkDefeat());
    }
}
