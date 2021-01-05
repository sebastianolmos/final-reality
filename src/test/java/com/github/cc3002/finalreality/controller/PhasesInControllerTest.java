package com.github.cc3002.finalreality.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to check the navigation between phases from the controller.
 *
 * @author Sebastian Olmos
 * @see GameController
 */
public class PhasesInControllerTest {
    private GameController controller;

    /**
     * Create the controller.
     */
    @BeforeEach
    public void setUp() {
        controller = new GameController(1,1,1);
    }

    /**
     * Test to check that the moves between phases is the appropriate
     */
    @Test
    public void flowTest() {
        controller.addKnightToParty("TestCharacter", 100, 20);
        assertTrue(controller.isTurnsQueueEmpty());

        assertFalse(controller.isSelectingAction());
        controller.basicTurnSetup();
        assertFalse(controller.isTurnsQueueEmpty());

        assertTrue(controller.isSelectingAction());

        controller.toAttackTargetSelection();
        assertTrue(controller.isSelectingAttackTarget());

        controller.toActionSelection();
        assertTrue(controller.isSelectingAction());

        controller.toWeaponSelection();
        assertTrue(controller.isSelectingWeapon());

        controller.toActionSelection();
        assertTrue(controller.isSelectingAction());
    }

}
