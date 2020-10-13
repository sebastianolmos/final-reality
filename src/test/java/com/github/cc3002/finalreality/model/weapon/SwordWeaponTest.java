package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.cc3002.finalreality.model.weapon.AxeWeapon;
import com.github.cc3002.finalreality.model.weapon.SwordWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the Sword weapon.
 *
 * @author Sebastian Olmos.
 * @see SwordWeapon
 */
public class SwordWeaponTest extends AbstractWeaponTest {

    private static final String SWORD_NAME = "Test Sword";
    private static final String OTHER_WEAPON_NAME = "Test Other Weapon";
    private static final String OTHER_SWORD_NAME = "Test Other Sword";
    private static final int DAMAGE = 15;
    private static final int OTHER_DAMAGE = 100;
    private static final int SPEED = 10;
    private static final int OTHER_SPEED = 42;

    /**
     * Setup method.
     * Creates a new sword weapon with some attributes to test.
     */
    @BeforeEach
    void setUp(){
        super.basicSetup(new SwordWeapon(SWORD_NAME, DAMAGE, SPEED));
    }

    /**
     * Checks that the weapon type constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        super.checkConstruction(new SwordWeapon(SWORD_NAME, DAMAGE, SPEED),
                new SwordWeapon(OTHER_SWORD_NAME, DAMAGE, SPEED),
                new SwordWeapon(SWORD_NAME, OTHER_DAMAGE, SPEED),
                new SwordWeapon(SWORD_NAME, DAMAGE, OTHER_SPEED),
                new AxeWeapon(OTHER_WEAPON_NAME, DAMAGE, SPEED));
    }

}
