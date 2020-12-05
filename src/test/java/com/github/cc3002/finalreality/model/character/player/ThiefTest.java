package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.KnifeWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Set of tests for the Thief character.
 *
 * @author Sebastian Olmos.
 * @see Thief
 */
public class ThiefTest extends AbstractPlayerTest {

    private static final String THIEF_NAME = "Zidane";

    /**
     * Setup method.
     * Creates a new thief named Zidane with 10 speed and links it to a turn queue.
     * Create the main weapon
     */
    @BeforeEach
    void setUp() {
        super.basicSetup(new KnifeWeapon("mainWeapon", 8, 10));
        super.assignCharacter(new Thief(turns, THIEF_NAME));
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        super.checkConstruction(new Thief(turns, THIEF_NAME),
                new Thief(turns, "Test"),
                new WhiteMage(turns, THIEF_NAME),
                new Enemy("Enemy", 10, turns));
        assertNotEquals(testCharacter, new WhiteMage(turns, THIEF_NAME));

        super.checkSameClassConstruction(
                new Thief(turns, "CHARACTER_NAME", 100, 20),
                new Thief(turns, "OTHER_NAME", 100, 20),
                new Thief(turns, "CHARACTER_NAME", 200, 20),
                new Thief(turns, "CHARACTER_NAME", 100, 10));

        assertEquals(testCharacter.getCharacterClass(), CharacterClass.THIEF);
    }

    /**
     * Checks that the Thief character only equip compatible weapons.
     */
    @Test
    void weaponEquipmentTest(){
        ArrayList<IWeapon> supported = new ArrayList<>(Arrays.asList(
                testSword, testKnife, testBow
        ));
        equipSupportedWeapons(supported);
        ArrayList<IWeapon> unSupported = new ArrayList<>(Arrays.asList(
                testAxe, testStaff
        ));
        equipUnsupportedWeapons(unSupported);
    }

}
