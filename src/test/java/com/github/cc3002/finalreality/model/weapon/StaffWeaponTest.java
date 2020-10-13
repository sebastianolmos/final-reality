package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.cc3002.finalreality.model.weapon.StaffWeapon;
import com.github.cc3002.finalreality.model.weapon.SwordWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the Staff weapon.
 *
 * @author Sebastian Olmos.
 * @see StaffWeapon
 */
public class StaffWeaponTest extends AbstractWeaponTest {

    private static final String STAFF_NAME = "Test Staff";
    private static final String OTHER_WEAPON_NAME = "Test Other Weapon";
    private static final String OTHER_STAFF_NAME = "Test Other Staff";
    private static final int DAMAGE = 15;
    private static final int OTHER_DAMAGE = 100;
    private static final int SPEED = 10;
    private static final int OTHER_SPEED = 42;

    /**
     * Setup method.
     * Creates a new staff weapon with some attributes to test.
     */
    @BeforeEach
    void setUp(){
        super.basicSetup(new StaffWeapon(STAFF_NAME, DAMAGE, SPEED));
    }

    /**
     * Checks that the weapon type constructor and equals method works properly.
     */
    @Test
    void constructorTest(){
        super.checkConstruction(new StaffWeapon(STAFF_NAME, DAMAGE, SPEED),
                new StaffWeapon(OTHER_STAFF_NAME, DAMAGE, SPEED),
                new StaffWeapon(STAFF_NAME, OTHER_DAMAGE, SPEED),
                new StaffWeapon(STAFF_NAME, DAMAGE, OTHER_SPEED),
                new SwordWeapon(OTHER_WEAPON_NAME, DAMAGE, SPEED));
    }

}
