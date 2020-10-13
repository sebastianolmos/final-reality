package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.cc3002.finalreality.model.weapon.BowWeapon;
import com.github.cc3002.finalreality.model.weapon.SwordWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the Bow weapon.
 *
 * @author Sebastian Olmos.
 * @see BowWeapon
 */
public class BowWeaponTest extends AbstractWeaponTest {

    private static final String BOW_NAME = "Test Bow";
    private static final String OTHER_WEAPON_NAME = "Test Other Weapon";
    private static final String OTHER_BOW_NAME = "Test Other Bow";
    private static final int DAMAGE = 15;
    private static final int OTHER_DAMAGE = 100;
    private static final int SPEED = 10;
    private static final int OTHER_SPEED = 42;

    /**
     * Setup method.
     * Creates a new bow weapon with some attributes to test.
     */
    @BeforeEach
    void setUp(){
        super.basicSetup(new BowWeapon(BOW_NAME, DAMAGE, SPEED));
    }

    /**
     * Checks that the weapon type constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        super.checkConstruction(new BowWeapon(BOW_NAME, DAMAGE, SPEED),
                new BowWeapon(OTHER_BOW_NAME, DAMAGE, SPEED),
                new BowWeapon(BOW_NAME, OTHER_DAMAGE, SPEED),
                new BowWeapon(BOW_NAME, DAMAGE, OTHER_SPEED),
                new SwordWeapon(OTHER_WEAPON_NAME, DAMAGE, SPEED));
    }

}
