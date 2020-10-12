package com.github.cc3002.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AbstractWeaponTest {
    private IWeapon testWeapon;

    protected void basicSetup(IWeapon someWeapon){
        testWeapon = someWeapon;
    }

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
