package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.AbstractCharacterTest;
import com.github.cc3002.finalreality.model.weapon.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class containing the common tests for the playable characters.
 *
 * @author Sebastian Olmos
 * @see IPlayer
 */
public abstract class AbstractPlayerTest extends AbstractCharacterTest {
    protected IWeapon mainWeapon;

    protected AxeWeapon testAxe = new AxeWeapon("testAxe", 1, 1);
    protected BowWeapon testBow = new BowWeapon("testBow", 1, 1);
    protected KnifeWeapon testKnife = new KnifeWeapon("testKnife", 1, 1);
    protected StaffWeapon testStaff = new StaffWeapon("testStaff", 1, 1);
    protected SwordWeapon testSword = new SwordWeapon("testSword", 1, 1);

    /**
     * Checks that the character waits for a character that can use weapons
     */
    @Test @Override
    protected void waitTurnTest() {
        Assertions.assertTrue(turns.isEmpty());
        tryToEquip((IPlayer) testCharacter);
        testCharacter.waitTurn();
        try {
            // Thread.sleep is not accurate so this values may be changed to adjust the
            // acceptable error margin.
            // We're testing that the character waits approximately 1 second.
            Thread.sleep(900);
            assertEquals(0, turns.size());
            Thread.sleep(200);
            assertEquals(1, turns.size());
            assertEquals(testCharacter, turns.peek());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Equip the main weapon.
     * @param character
     *     Character to equip the main weapon.
     */
    private void tryToEquip(IPlayer character) {
        character.equip(mainWeapon);
    }

    /**
     * Checks that the supported weapons can be equipped.
     * @param testWeapons
     *     Array of weapons to check
     */
    protected void equipSupportedWeapons(ArrayList<IWeapon> testWeapons) {
        IPlayer player = (IPlayer)testCharacter;
        for (IWeapon weapon : testWeapons) {
            player.equip(mainWeapon);
            player.equip(weapon);
            assertEquals(weapon, player.getEquippedWeapon());
        }
        player.equip(mainWeapon);
        testCharacter.receiveAttackOf(1000);
        player.equip(testWeapons.get(0));
        assertEquals(mainWeapon, player.getEquippedWeapon());
    }

    /**
     * Checks that the unsupported weapons can not be equipped.
     * @param testWeapons
     *     Array of weapons to check
     */
    protected void equipUnsupportedWeapons(ArrayList<IWeapon> testWeapons) {
        IPlayer player = (IPlayer)testCharacter;
        for (IWeapon weapon : testWeapons) {
            player.equip(mainWeapon);
            player.equip(weapon);
            assertNotEquals(weapon, player.getEquippedWeapon());
        }
    }

    /**
     * Prepare the main weapon and the turns queue .
     * @param testWeapon
     *     Weapon that will be the main weapon
     */
    protected void basicSetup(IWeapon testWeapon) {
        super.basicSetup();
        this.mainWeapon = testWeapon;
    }

    /**
     * Assertion tests that sees the non equality of the same class but with different attributes
     *
     * @param testCharacter
     *      Character to be compared
     * @param differentNameCharacter
     *      Same class character than testCharacter with different Name
     * @param differentHealthCharacter
     *      Same class character than testCharacter with different Health
     * @param differentDefenseCharacter
     *      Same class character than testCharacter with different Defense
     */
    protected void checkSameClassConstruction(final IPlayer testCharacter,
                                              final IPlayer differentNameCharacter,
                                              final IPlayer differentHealthCharacter,
                                              final IPlayer differentDefenseCharacter) {
        assertNotEquals(testCharacter, differentNameCharacter);
        assertNotEquals(testCharacter.hashCode(), differentNameCharacter.hashCode());
        assertNotEquals(testCharacter, differentHealthCharacter);
        assertNotEquals(testCharacter.hashCode(), differentHealthCharacter.hashCode());
        assertNotEquals(testCharacter, differentDefenseCharacter);
        assertNotEquals(testCharacter.hashCode(), differentDefenseCharacter.hashCode());
    }

}
