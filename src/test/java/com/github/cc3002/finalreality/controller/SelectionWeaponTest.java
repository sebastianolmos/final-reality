package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.model.character.player.IPlayer;
import com.github.cc3002.finalreality.model.weapon.AxeWeapon;
import com.github.cc3002.finalreality.model.weapon.NullWeapon;
import com.github.cc3002.finalreality.model.weapon.SwordWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests to check the functionality of the controller to equip weapons from the inventory.
 *
 * @author Sebastian Olmos
 * @see GameController
 */
public class SelectionWeaponTest {
    private String equipedName = "EQUIPED";
    private int equipedWeight = 30;
    private int equipedDamage = 60;
    private String selectedName = "SELECTED";
    private int selectedWeight = 10;
    private int selectedDamage = 50;

    private GameController controller;

    /**
     * Create the controller, add some characters and weapons, and go to the selecting weapon phase
     */
    @BeforeEach
    public void setUp() {
        controller = new GameController(1,1,2);
        controller.addKnightToParty("testCharacter", 200, 50);
        controller.addSwordToInventory(equipedName, equipedWeight, equipedDamage);
        controller.addAxeToInventory(selectedName, selectedWeight, selectedDamage);
        controller.basicTurnSetup();
        controller.startTurn();
        controller.toWeaponSelection();
    }

    /**
     * Several assert to prove that the equip methods works properly
     */
    @Test
    public void selectionTest() {
        assertTrue(controller.isSelectingWeapon());

        assertEquals(controller.getWeaponFromInventory(0), new SwordWeapon(equipedName, equipedDamage, equipedWeight));
        assertEquals(controller.getWeaponFromInventory(1), new AxeWeapon(selectedName, selectedDamage, selectedWeight));

        var selectedCharacter = (IPlayer) controller.getSelectedCharacter();
        assertEquals(selectedCharacter.getEquippedWeapon(), NullWeapon.getInstance());

        controller.equipOnSelectedCharacter(0);
        assertEquals(selectedCharacter.getEquippedWeapon(), new SwordWeapon(equipedName, equipedDamage, equipedWeight));

        assertEquals(controller.getWeaponFromInventory(0), new AxeWeapon(selectedName, selectedDamage, selectedWeight));

        assertEquals(controller.getSelectedWeaponDamage(), 0);
        assertEquals(controller.getSelectedWeaponName(), "");
        assertEquals(controller.getSelectedWeaponWeight(), 0);

        controller.selectWeapon(0);

        assertEquals(controller.getSelectedWeaponDamage(), selectedDamage);
        assertEquals(controller.getSelectedWeaponName(), selectedName);
        assertEquals(controller.getSelectedWeaponWeight(), selectedWeight);

        controller.equipSelectedWeapon();
        assertEquals(selectedCharacter.getEquippedWeapon(), new AxeWeapon(selectedName, selectedDamage, selectedWeight));

    }
}
