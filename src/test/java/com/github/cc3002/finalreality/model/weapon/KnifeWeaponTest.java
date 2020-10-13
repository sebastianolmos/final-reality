package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.cc3002.finalreality.model.weapon.KnifeWeapon;
import com.github.cc3002.finalreality.model.weapon.SwordWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the Knife weapon.
 *
 * @author Sebastian Olmos.
 * @see KnifeWeapon
 */
public class KnifeWeaponTest extends AbstractWeaponTest {

    private static final String KNIFE_NAME = "Test Knife";
    private static final String OTHER_WEAPON_NAME = "Test Other Weapon";
    private static final String OTHER_KNIFE_NAME = "Test Other Knife";
    private static final int DAMAGE = 15;
    private static final int OTHER_DAMAGE = 100;
    private static final int SPEED = 10;
    private static final int OTHER_SPEED = 42;

    /**
     * Setup method.
     * Creates a new knife weapon with some attributes to test.
     */
    @BeforeEach
    void setUp(){
        super.basicSetup(new KnifeWeapon(KNIFE_NAME, DAMAGE, SPEED));
    }

    /**
     * Checks that the weapon type constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        super.checkConstruction(new KnifeWeapon(KNIFE_NAME, DAMAGE, SPEED),
                new KnifeWeapon(OTHER_KNIFE_NAME, DAMAGE, SPEED),
                new KnifeWeapon(KNIFE_NAME, OTHER_DAMAGE, SPEED),
                new KnifeWeapon(KNIFE_NAME, DAMAGE, OTHER_SPEED),
                new SwordWeapon(OTHER_WEAPON_NAME, DAMAGE, SPEED));
    }

}
