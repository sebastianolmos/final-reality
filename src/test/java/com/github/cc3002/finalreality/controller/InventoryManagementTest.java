package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.IPlayer;
import com.github.cc3002.finalreality.model.character.player.Knight;
import com.github.cc3002.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Tests for prove the management of the inventory in the controller
 *
 * @author Sebastian Olmos
 * @see GameController
 */
public class InventoryManagementTest {
    private static final String[] NAMES = {"TEST_AXE", "TEST_BOW", "TEST_KNIFE", "TEST_STAFF", "TEST_SWORD"};
    private static final int[] WEIGHT_VALUES = {20, 10, 2, 10, 15};
    private static final int[] DAMAGE_VALUES = {100, 10, 30, 50, 60};
    private final IWeapon[] WEAPONS = new IWeapon[5];
    private final GameController testController = new GameController(5,3,5);

    /**
     * Create a set of weapons to compare and add characters to equip weapons.
     */
    @BeforeEach
    public void setUp() {
        WEAPONS[0] = new AxeWeapon(NAMES[0], DAMAGE_VALUES[0], WEIGHT_VALUES[0]);
        WEAPONS[1] = new BowWeapon(NAMES[1], DAMAGE_VALUES[1], WEIGHT_VALUES[1]);
        WEAPONS[2] = new KnifeWeapon(NAMES[2], DAMAGE_VALUES[2], WEIGHT_VALUES[2]);
        WEAPONS[3] = new StaffWeapon(NAMES[3], DAMAGE_VALUES[3], WEIGHT_VALUES[3]);
        WEAPONS[4] = new SwordWeapon(NAMES[4], DAMAGE_VALUES[4], WEIGHT_VALUES[4]);
        testController.addEngineerToParty("TEST_ENGINEER1", 10, 10);
        testController.addEngineerToParty("TEST_ENGINEER2", 10, 10);
        testController.addThiefToParty("TEST_THIEF1", 10, 10);
        testController.addBlackMageToParty("TEST_MAGE1", 10, 10);
        testController.addKnightToParty("TEST_KNIGHT1", 10, 10);
    }

    /**
     * Test the case when the Index is above the number of weapons in the inventory
     */
    @Test
    void indexTest() {
        // Try to equip with a wrong index so the controller doesn't make the equip
        testController.addSwordToInventory(NAMES[4], WEIGHT_VALUES[4], DAMAGE_VALUES[4]);
        testController.equipFromInventoryOn(3, 4);
        assertEquals(testController.getWeaponsAmountOnInventory(), 1);
        //Remove a weapon from inventory and check if was correct
        IWeapon poppedWeapon = testController.removeFromInventory(1);
        assertEquals(testController.getWeaponsAmountOnInventory(), 0);
        assertEquals(WEAPONS[4], poppedWeapon);

    }
    /**
     * Test the addition of weapon to the inventory.
     */
    @Test
    public void managementTest() {
        assertEquals(testController.getWeaponsAmountOnInventory(), 0);
        //Add weapons and test if where added in the controller
        testController.addAxeToInventory(NAMES[0], WEIGHT_VALUES[0], DAMAGE_VALUES[0]);
        assertEquals(testController.getWeaponsAmountOnInventory(), 1);

        testController.addBowToInventory(NAMES[1], WEIGHT_VALUES[1], DAMAGE_VALUES[1]);
        assertEquals(testController.getWeaponsAmountOnInventory(), 2);

        testController.addKnifeToInventory(NAMES[2], WEIGHT_VALUES[2], DAMAGE_VALUES[2]);
        assertEquals(testController.getWeaponsAmountOnInventory(), 3);

        testController.addStaffToInventory(NAMES[3], WEIGHT_VALUES[3], DAMAGE_VALUES[3]);
        assertEquals(testController.getWeaponsAmountOnInventory(), 4);

        testController.addSwordToInventory(NAMES[4], WEIGHT_VALUES[4], DAMAGE_VALUES[4]);
        assertEquals(testController.getWeaponsAmountOnInventory(), 5);

        //try to add a weapon over the limit and check that was not added in the inventory
        testController.addAxeToInventory("OVERLOAD_TEST", 10, 10);
        assertEquals(testController.getWeaponsAmountOnInventory(), 5);

        //Test the remove method
        IWeapon poppedWeapon = testController.removeFromInventory(4);
        assertEquals(testController.getWeaponsAmountOnInventory(), 4);
        assertEquals(WEAPONS[4], poppedWeapon);

        testController.addSwordToInventory(NAMES[4], WEIGHT_VALUES[4], DAMAGE_VALUES[4]);
        assertEquals(testController.getWeaponsAmountOnInventory(), 5);

        equipTest();
    }

    /**
     * Make tests to different cases when a character equip a weapon from rhe inventory.
     */
    public void equipTest() {
        //Test the equip methods to character (without weapon) that the weapons are not compatible
        testController.equipFromInventoryOn(3, 4);
        assertEquals(testController.getWeaponsAmountOnInventory(), 5);
        assertNotEquals(WEAPONS[3], testController.getPartyCharacter(4).getEquippedWeapon());

        //Test the equip methods to character that the weapons are compatible
        testController.equipFromInventoryOn(0, 0);
        assertEquals(testController.getWeaponsAmountOnInventory(), 4);
        assertEquals(WEAPONS[0], testController.getPartyCharacter(0).getEquippedWeapon());

        //Test the equip methods to character (with weapon) that the weapons are not compatible
        testController.equipFromInventoryOn(2, 0);
        assertEquals(testController.getWeaponsAmountOnInventory(), 4);
        assertNotEquals(WEAPONS[3], testController.getPartyCharacter(0).getEquippedWeapon());

        //Test the equip methods to character that the weapons are compatible and has an equipped weapon
        testController.equipFromInventoryOn(0, 0);
        assertEquals(testController.getWeaponsAmountOnInventory(), 4);
        assertEquals(WEAPONS[1], testController.getPartyCharacter(0).getEquippedWeapon());

        //Test the equip methods to characters that the weapons are compatible, the index of the inventory is decreasing
        testController.equipFromInventoryOn(0, 1);
        assertEquals(testController.getWeaponsAmountOnInventory(), 3);
        assertEquals(WEAPONS[0], testController.getPartyCharacter(1).getEquippedWeapon());

        testController.equipFromInventoryOn(0, 2);
        assertEquals(testController.getWeaponsAmountOnInventory(), 2);
        assertEquals(WEAPONS[2], testController.getPartyCharacter(2).getEquippedWeapon());

        testController.equipFromInventoryOn(0, 3);
        assertEquals(testController.getWeaponsAmountOnInventory(), 1);
        assertEquals(WEAPONS[3], testController.getPartyCharacter(3).getEquippedWeapon());

        testController.equipFromInventoryOn(0, 4);
        assertEquals(testController.getWeaponsAmountOnInventory(), 0);
        assertEquals(WEAPONS[4], testController.getPartyCharacter(4).getEquippedWeapon());
    }

}
