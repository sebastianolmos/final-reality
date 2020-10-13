package com.github.cc3002.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Abstract class containing the common tests for all the types of weapons.
 *
 * @author Sebastian Olmos
 * @see IWeapon
 */
public class AbstractWeaponTest {
    private IWeapon testWeapon;

    /**
     * Add a weapon to make the tests
     */
    protected void basicSetup(IWeapon someWeapon){
        testWeapon = someWeapon;
    }

    /**
     * Assertion tests that sees the equality of testWeapon and other weapons
     *
     * @param expectedWeapon
     *      Weapon expected to be equals to testWeapon
     * @param sameWeaponDifferentName
     *      Same type weapon than testWeapon with different name
     * @param sameWeaponDifferentDmg
     *      Same type weapon than testWeapon with different damage value
     * @param sameWeaponDifferentWeight
     *      Same type weapon than testWeapon with different wight value
     * @param differentWeapon
     *      Different type weapon than testWeapon
     */
    protected void checkConstruction(final IWeapon expectedWeapon,
                                     final IWeapon sameWeaponDifferentName,
                                     final IWeapon sameWeaponDifferentDmg,
                                     final IWeapon sameWeaponDifferentWeight,
                                     final IWeapon differentWeapon){
        assertEquals(testWeapon, testWeapon);
        assertEquals(testWeapon, expectedWeapon);
        assertNotEquals(testWeapon, sameWeaponDifferentName);
        assertNotEquals(testWeapon, sameWeaponDifferentDmg);
        assertNotEquals(testWeapon, sameWeaponDifferentWeight);
        assertNotEquals(testWeapon, differentWeapon);

        assertEquals(testWeapon.hashCode(), expectedWeapon.hashCode());
        assertNotEquals(testWeapon.hashCode(), sameWeaponDifferentName.hashCode());
        assertNotEquals(testWeapon.hashCode(), differentWeapon.hashCode());

    }
}
