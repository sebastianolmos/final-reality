package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.StaffWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Set of tests for the BlackMage character.
 *
 * @author Sebastian Olmos.
 * @see BlackMage
 */
public class BlackMageTest extends AbstractPlayerTest {

    private static final String BLACK_MAGE_NAME = "Vivi";

    /**
     * Setup method.
     * Creates a new black mage named Vivi with 10 speed and links it to a turn queue.
     * Create the main weapon.
     */
    @BeforeEach
    void setUp() {
        super.basicSetup(new StaffWeapon("mainWeapon", 2, 10));
        super.assignCharacter(new BlackMage(turns, BLACK_MAGE_NAME));
    }

    /**
     * Checks that the class constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        super.checkConstruction(new BlackMage(turns, BLACK_MAGE_NAME),
                new BlackMage(turns, "Test"),
                new Knight(turns, BLACK_MAGE_NAME),
                new Enemy("Enemy", 10, turns));
        assertNotEquals(testCharacter, new Engineer(turns, BLACK_MAGE_NAME));
    }

    /**
     * Checks that the Black Mage character only equip compatible weapons.
     */
    @Test
    void weaponEquipmentTest(){
        ArrayList<IWeapon> supported = new ArrayList<>(Arrays.asList(
                testKnife, testStaff
        ));
        equipSupportedWeapons(supported);
        ArrayList<IWeapon> unSupported = new ArrayList<>(Arrays.asList(
                testAxe, testSword, testBow
        ));
        equipUnsupportedWeapons(unSupported);
    }

}
