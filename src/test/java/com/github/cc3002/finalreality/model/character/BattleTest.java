package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.IPlayer;
import com.github.cc3002.finalreality.model.character.player.Knight;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.SwordWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BattleTest {
    private IPlayer armedCharacter;
    private ICharacter deadCharacter;
    private ICharacter notArmedCharacter;
    private ICharacter foeCharacter;
    private ICharacter deadFoe;
    private IWeapon mainWeapon;

    /**
     * Checks that the attack of a character was null.
     * @param player1
     *     Character that attacks
     * @param player2
     *     Character that receive the attack
     */
    private void nullAttackTest(ICharacter player1, ICharacter player2) {
        int points = player2.getHealth();
        player1.attack(player2);
        assertEquals(points, player2.getHealth());
    }

    /**
     * Checks that the attack of a character was successful.
     * @param player1
     *     Character that attacks
     * @param player2
     *     Character that receive the attack
     */
    private void accurateAttackTest(ICharacter player1, ICharacter player2) {
        int points = player2.getHealth();
        player1.attack(player2);
        assertNotEquals(points, player2.getHealth());
    }

    /**
     * Prepare the characters and the main weapon to test.
     */
    @BeforeEach
    public void SetUp(){
        BlockingQueue<ICharacter>  turns = new LinkedBlockingQueue<>();
        armedCharacter = new Knight(turns, "Vessel", 100, 10);
        deadCharacter = new Knight(turns, "Void", 0, 2);
        notArmedCharacter = new Knight(turns, "Zote", 20, 1);
        foeCharacter = new Enemy("Radiance", 12, turns, 200, 12, 25);
        deadFoe = new Enemy("Hollow Knight", 10, turns, 0, 10, 20);

        mainWeapon = new SwordWeapon("Needle", 20, 10);
        armedCharacter.equip(mainWeapon);
    }

    /**
     * Checks that the attack works properly.
     */
    @Test
    public void AttacksTest() {
        nullAttackTest(deadCharacter, foeCharacter);
        nullAttackTest(foeCharacter, deadCharacter);
        nullAttackTest(deadFoe, armedCharacter);
        nullAttackTest(armedCharacter, deadFoe);
        nullAttackTest(notArmedCharacter, foeCharacter);

        accurateAttackTest(armedCharacter, foeCharacter);
        accurateAttackTest(foeCharacter,armedCharacter);

        armedCharacter.receiveAttackOf(10000);
        nullAttackTest(armedCharacter, deadFoe);
    }
}
