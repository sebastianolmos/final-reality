package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests to prove the functionality of the basicSetup method.
 *
 * @author Sebastian Olmos
 * @see GameController
 */
public class BasicSetupTest {
    private BlockingQueue<ICharacter> dummyTurns;
    private GameController controller;
    private final String[] partyNames = {"PARTY_CHARACTER1", "PARTY_CHARACTER_2", "PARTY_CHARACTER_3", "PARTY_CHARACTER_4", "PARTY_CHARACTER_5"};
    private final int[] partyDefenseValues = {50, 20, 60, 15, 10};
    private final int[] partyHealthValues = {200, 300, 150, 240, 180};

    private final String[] enemiesNames = {"ENEMY_CHARACTER1", "ENEMY_CHARACTER_2", "ENEMY_CHARACTER_3", "ENEMY_CHARACTER_4", "ENEMY_CHARACTER_5"};
    private final int[] enemiesDefenseValues = {10, 13, 10, 11, 12};
    private final int[] enemiesHealthValues = {90, 100, 80,120, 95};
    private final int[] enemiesWeightValues = {20, 50, 10, 30, 20};
    private final int[] enemiesDamageValues = {50, 60, 40, 50, 20};

    /**
     * Create the controller an add some characters
     */
    @BeforeEach
    public void setUp() {
        controller = new GameController(5, 5, 1);
        dummyTurns = new LinkedBlockingQueue<>();
        controller.addBlackMageToParty(partyNames[0], partyHealthValues[0], partyDefenseValues[0]);
        controller.addEngineerToParty(partyNames[1], partyHealthValues[1], partyDefenseValues[1]);
        controller.addKnightToParty(partyNames[2], partyHealthValues[2], partyDefenseValues[2]);
        controller.addThiefToParty(partyNames[3], partyHealthValues[3], partyDefenseValues[3]);
        controller.addWhiteMageToParty(partyNames[4], partyHealthValues[4], partyDefenseValues[4]);

        controller.addEnemyToEnemies(enemiesNames[0], enemiesHealthValues[0], enemiesDefenseValues[0], enemiesWeightValues[0], enemiesDamageValues[0]);
        controller.addEnemyToEnemies(enemiesNames[1], enemiesHealthValues[1], enemiesDefenseValues[1], enemiesWeightValues[1], enemiesDamageValues[1]);
        controller.addEnemyToEnemies(enemiesNames[2], enemiesHealthValues[2], enemiesDefenseValues[2], enemiesWeightValues[2], enemiesDamageValues[2]);
        controller.addEnemyToEnemies(enemiesNames[3], enemiesHealthValues[3], enemiesDefenseValues[3], enemiesWeightValues[3], enemiesDamageValues[3]);
        controller.addEnemyToEnemies(enemiesNames[4], enemiesHealthValues[4], enemiesDefenseValues[4], enemiesWeightValues[4], enemiesDamageValues[4]);
    }

    /**
     * Make the setup and check if the order of the characters is correct
     */
    @Test
    public  void setUpTest() {
        controller.basicTurnSetup();
        var turnsCopy = controller.getTurnsQueue();
        assertEquals(turnsCopy.poll(), new BlackMage(dummyTurns, partyNames[0], partyHealthValues[0], partyDefenseValues[0]));

        assertEquals(turnsCopy.poll(), new Engineer(dummyTurns, partyNames[1], partyHealthValues[1], partyDefenseValues[1]));

        assertEquals(turnsCopy.poll(), new Knight(dummyTurns, partyNames[2], partyHealthValues[2], partyDefenseValues[2]));

        assertEquals(turnsCopy.poll(), new Thief(dummyTurns, partyNames[3], partyHealthValues[3], partyDefenseValues[3]));

        assertEquals(turnsCopy.poll(), new WhiteMage(dummyTurns, partyNames[4], partyHealthValues[4], partyDefenseValues[4]));

        assertEquals(turnsCopy.poll(), new Enemy(enemiesNames[0], enemiesWeightValues[0], dummyTurns, enemiesHealthValues[0], enemiesDefenseValues[0], enemiesDamageValues[0]));

        assertEquals(turnsCopy.poll(), new Enemy(enemiesNames[1], enemiesWeightValues[1], dummyTurns, enemiesHealthValues[1], enemiesDefenseValues[1], enemiesDamageValues[1]));

        assertEquals(turnsCopy.poll(), new Enemy(enemiesNames[2], enemiesWeightValues[2], dummyTurns, enemiesHealthValues[2], enemiesDefenseValues[2], enemiesDamageValues[2]));

        assertEquals(turnsCopy.poll(), new Enemy(enemiesNames[3], enemiesWeightValues[3], dummyTurns, enemiesHealthValues[3], enemiesDefenseValues[3], enemiesDamageValues[3]));

        assertEquals(turnsCopy.poll(), new Enemy(enemiesNames[4], enemiesWeightValues[4], dummyTurns, enemiesHealthValues[4], enemiesDefenseValues[4], enemiesDamageValues[4]));
    }
}
