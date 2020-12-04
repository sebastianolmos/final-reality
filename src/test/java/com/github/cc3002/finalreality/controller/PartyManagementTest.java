package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.*;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.SwordWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Tests for prove the management of the party characters in the controller
 *
 * @author Sebastian Olmos
 * @see GameController
 */
public class PartyManagementTest {
    private static final String[] NAMES = {"VIVI", "CID", "ADELBERT", "ZIDANE", "EIKO"};
    private static final int[] HEALTH_VALUES = {80, 70, 120, 50, 60};
    private static final int[] DEFENSE_VALUES = {20, 15, 40, 10, 30};
    private final IPlayer[] CHARACTERS = new IPlayer[5];
    private final BlockingQueue<ICharacter> dummyTurns = new LinkedBlockingQueue<>();
    private final GameController testController = new GameController();

    /**
     * Create a set of character to compare.
     */
    @BeforeEach
    public void setUp() {
        CHARACTERS[0] = new BlackMage(dummyTurns, NAMES[0], HEALTH_VALUES[0], DEFENSE_VALUES[0]);
        CHARACTERS[1] = new Engineer(dummyTurns, NAMES[1], HEALTH_VALUES[1], DEFENSE_VALUES[1]);
        CHARACTERS[2] = new Knight(dummyTurns, NAMES[2], HEALTH_VALUES[2], DEFENSE_VALUES[2]);
        CHARACTERS[3] = new Thief(dummyTurns, NAMES[3], HEALTH_VALUES[3], DEFENSE_VALUES[3]);
        CHARACTERS[4] = new WhiteMage(dummyTurns, NAMES[4], HEALTH_VALUES[4], DEFENSE_VALUES[4]);
    }

    /**
     * Make several tests of creation, addition, getters and basic attack
     */
    @Test
    public void managementTest() {
        assertEquals(testController.getRemainingPartyCharacters(), 0);

        testController.addBlackMageToParty(NAMES[0], HEALTH_VALUES[0], DEFENSE_VALUES[0]);
        assertEquals(testController.getRemainingPartyCharacters(), 1);
        assertEquals(testController.getPartyCharacter(0), CHARACTERS[0]);

        testController.addEngineerToParty(NAMES[1], HEALTH_VALUES[1], DEFENSE_VALUES[1]);
        assertEquals(testController.getRemainingPartyCharacters(), 2);
        assertEquals(testController.getPartyCharacter(1), CHARACTERS[1]);

        testController.addKnightToParty(NAMES[2], HEALTH_VALUES[2], DEFENSE_VALUES[2]);
        assertEquals(testController.getRemainingPartyCharacters(), 3);
        assertEquals(testController.getPartyCharacter(2), CHARACTERS[2]);

        testController.addThiefToParty(NAMES[3], HEALTH_VALUES[3], DEFENSE_VALUES[3]);
        assertEquals(testController.getRemainingPartyCharacters(), 4);
        assertEquals(testController.getPartyCharacter(3), CHARACTERS[3]);

        testController.addWhiteMageToParty(NAMES[4], HEALTH_VALUES[4], DEFENSE_VALUES[4]);
        assertEquals(testController.getRemainingPartyCharacters(), 5);
        assertEquals(testController.getPartyCharacter(4), CHARACTERS[4]);

        IPlayer testCharacter = new BlackMage(dummyTurns, "OverloadTest", 10, 10);
        testController.addBlackMageToParty("OverloadTest", 10, 10);
        assertEquals(testController.getRemainingPartyCharacters(), 5);
        assertNotEquals(testController.getPartyCharacter(4), testCharacter);

        gettersTest();
        attackTest();
    }

    /**
     * Test the getters with its creation values
     */
    public void gettersTest() {
        assertEquals(testController.getPartyCharacterName(0), NAMES[0]);
        assertEquals(testController.getPartyCharacterHealth(0), HEALTH_VALUES[0]);
        assertEquals(testController.getPartyCharacterDefense(0), DEFENSE_VALUES[0]);
        assertEquals(testController.getPartyCharacterName(1), NAMES[1]);
        assertEquals(testController.getPartyCharacterHealth(1), HEALTH_VALUES[1]);
        assertEquals(testController.getPartyCharacterDefense(1), DEFENSE_VALUES[1]);
        assertEquals(testController.getPartyCharacterName(2), NAMES[2]);
        assertEquals(testController.getPartyCharacterHealth(2), HEALTH_VALUES[2]);
        assertEquals(testController.getPartyCharacterDefense(2), DEFENSE_VALUES[2]);
        assertEquals(testController.getPartyCharacterName(3), NAMES[3]);
        assertEquals(testController.getPartyCharacterHealth(3), HEALTH_VALUES[3]);
        assertEquals(testController.getPartyCharacterDefense(3), DEFENSE_VALUES[3]);
        assertEquals(testController.getPartyCharacterName(4), NAMES[4]);
        assertEquals(testController.getPartyCharacterHealth(4), HEALTH_VALUES[4]);
        assertEquals(testController.getPartyCharacterDefense(4), DEFENSE_VALUES[4]);
        //
        testController.addSwordToInventory("TestSword",10,100);
        testController.equipFromInventoryOn(0, 2);
        assertEquals(testController.getPartyCharacterDamage(4), 0);
        assertEquals(testController.getPartyCharacterDamage(2), 100);
    }

    /**
     * Test a basic attack made with the controller.
     */
    public void attackTest() {
        int currentHealth = testController.getPartyCharacterHealth(3);
        testController.attackTo(testController.getPartyCharacter(2), testController.getPartyCharacter(3));
        assertNotEquals(currentHealth, testController.getPartyCharacterHealth(3));
    }
}
