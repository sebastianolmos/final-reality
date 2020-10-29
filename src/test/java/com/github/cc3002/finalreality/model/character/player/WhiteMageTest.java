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
 * Set of tests for the White Mage character.
 *
 * @author Sebastian Olmos.
 * @see WhiteMage
 */
public class WhiteMageTest extends AbstractPlayerTest {

    private static final String WHITE_MAGE_NAME = "Eiko";

    /**
     * Setup method.
     * Creates a new white mage named Eiko with 10 speed and links it to a turn queue.
     * Create the main weapon
     */
    @BeforeEach
    void setUp() {
        super.basicSetup(new StaffWeapon("mainWeapon", 3, 10));
        super.assignCharacter(new WhiteMage(turns, WHITE_MAGE_NAME));
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        super.checkConstruction(new WhiteMage(turns, WHITE_MAGE_NAME),
                new WhiteMage(turns, "Test"),
                new Knight(turns, WHITE_MAGE_NAME),
                new Enemy("Enemy", 10, turns));
        assertNotEquals(testCharacter, new BlackMage(turns, WHITE_MAGE_NAME));
    }

    /**
     * Checks that the White Mage character only equip compatible weapons.
     */
    @Test
    void weaponEquipmentTest(){
        ArrayList<IWeapon> supported = new ArrayList<>(Arrays.asList(
                testStaff
        ));
        equipSupportedWeapons(supported);
        ArrayList<IWeapon> unSupported = new ArrayList<>(Arrays.asList(
                testSword, testAxe, testBow, testKnife
        ));
        equipUnsupportedWeapons(unSupported);
    }

}
