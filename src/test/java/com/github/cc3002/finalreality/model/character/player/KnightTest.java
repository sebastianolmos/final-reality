package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.SwordWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Set of tests for the Knight character.
 *
 * @author Sebastian Olmos.
 * @see Knight
 */
public class KnightTest extends AbstractPlayerTest {

    private static final String KNIGHT_NAME = "Adelbert";

    /**
     * Setup method.
     * Creates a new knight named Adelbert with 10 speed and links it to a turn queue.
     * Create the main weapon
     */
    @BeforeEach
    void setUp() {
        super.basicSetup(new SwordWeapon("mainWeapon", 10,10));
        super.assignCharacter(new Knight(turns, KNIGHT_NAME));
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        super.checkConstruction(new Knight(turns, KNIGHT_NAME),
                new Knight(turns, "Test"),
                new BlackMage(turns, KNIGHT_NAME),
                new Enemy("Enemy", 10, turns));
        assertNotEquals(testCharacter, new Thief(turns, KNIGHT_NAME));

        super.checkSameClassConstruction(
                new Knight(turns, "CHARACTER_NAME", 100, 20),
                new Knight(turns, "OTHER_NAME", 100, 20),
                new Knight(turns, "CHARACTER_NAME", 200, 20),
                new Knight(turns, "CHARACTER_NAME", 100, 10));

        assertEquals(testCharacter.getCharacterClass(), CharacterClass.KNIGHT);
    }

    /**
     * Checks that the Knight character only equip compatible weapons.
     */
    @Test
    void weaponEquipmentTest(){
        ArrayList<IWeapon> supported = new ArrayList<>(Arrays.asList(
                testSword, testAxe, testKnife
        ));
        equipSupportedWeapons(supported);
        ArrayList<IWeapon> unSupported = new ArrayList<>(Arrays.asList(
                testStaff, testBow
        ));
        equipUnsupportedWeapons(unSupported);
    }

}
