package com.github.cc3002.finalreality.model.weapon.types;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SwordWeaponTest extends AbstractWeaponTest {

    private static final String SWORD_NAME = "Test Sword";
    private static final String OTHER_WEAPON_NAME = "Test Other Weapon";
    private static final String OTHER_SWORD_NAME = "Test Other Sword";
    private static final int DAMAGE = 15;
    private static final int OTHER_DAMAGE = 100;
    private static final int SPEED = 10;
    private static final int OTHER_SPEED = 42;

    @BeforeEach
    void setUp(){
        super.basicSetup(new SwordWeapon(SWORD_NAME, DAMAGE, SPEED));
    }

    @Test
    void constructorTest(){
        super.checkConstruction(new SwordWeapon(SWORD_NAME, DAMAGE, SPEED),
                new SwordWeapon(OTHER_SWORD_NAME, DAMAGE, SPEED),
                new SwordWeapon(SWORD_NAME, OTHER_DAMAGE, SPEED),
                new SwordWeapon(SWORD_NAME, DAMAGE, OTHER_SPEED),
                new AxeWeapon(OTHER_WEAPON_NAME, DAMAGE, SPEED));
    }

}
