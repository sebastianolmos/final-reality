package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.weapon.BowWeapon;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Set of tests for the Engineer character.
 *
 * @author Sebastian Olmos.
 * @see Engineer
 */
public class EngineerTest extends AbstractPlayerTest {

    private static final String ENGINEER_NAME = "Cid";

    /**
     * Setup method.
     * Creates a new engineer named Cid with 10 speed and links it to a turn queue.
     * Create the main weapon
     */
    @BeforeEach
    void setUp() {
        super.basicSetup(new BowWeapon("mainWeapon", 12, 10));
        super.assignCharacter(new Engineer(turns, ENGINEER_NAME));
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        super.checkConstruction(new Engineer(turns, ENGINEER_NAME),
                new Engineer(turns, "Test"),
                new WhiteMage(turns, ENGINEER_NAME),
                new Enemy("Enemy", 10, turns));
        assertNotEquals(testCharacter, new Knight(turns, ENGINEER_NAME));

        super.checkSameClassConstruction(
                new Engineer(turns, "CHARACTER_NAME", 100, 20),
                new Engineer(turns, "OTHER_NAME", 100, 20),
                new Engineer(turns, "CHARACTER_NAME", 200, 20),
                new Engineer(turns, "CHARACTER_NAME", 100, 10));

        assertEquals(testCharacter.getCharacterClass(), CharacterClass.ENGINEER);
    }

    /**
     * Checks that the Engineer character only equip compatible weapons.
     */
    @Test
    void weaponEquipmentTest(){
        ArrayList<IWeapon> supported = new ArrayList<>(Arrays.asList(
                testAxe, testBow
        ));
        equipSupportedWeapons(supported);
        ArrayList<IWeapon> unSupported = new ArrayList<>(Arrays.asList(
                testStaff, testSword, testKnife
        ));
        equipUnsupportedWeapons(unSupported);
    }

}
