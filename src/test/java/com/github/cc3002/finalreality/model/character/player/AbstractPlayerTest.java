package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.AbstractCharacterTest;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.types.SwordWeapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public abstract class AbstractPlayerTest extends AbstractCharacterTest {
    protected IWeapon testWeapon;

    @Test
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

    private void tryToEquip(IPlayer character) {
        character.equip(testWeapon);
    }

    @Test
    protected void equipWeaponTest() {
        IPlayer testPlayer = (IPlayer)testCharacter;
        assertNull(testPlayer.getEquippedWeapon());
        testPlayer.equip(testWeapon);
        assertEquals(testWeapon, testPlayer.getEquippedWeapon());
    }

    @Override
    protected void basicSetup() {
        super.basicSetup();
        testWeapon = new SwordWeapon("TestSword", 15, 10);
    }

}
