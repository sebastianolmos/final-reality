package com.github.cc3002.finalreality.model.weapon.types;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AxeWeaponTest extends AbstractWeaponTest {
    private static final String AXE_NAME = "Test Axe";
    private static final String OTHER_WEAPON_NAME = "Test Other Weapon";
    private static final String OTHER_AXE_NAME = "Test Other Axe";
    private static final int DAMAGE = 15;
    private static final int OTHER_DAMAGE = 100;
    private static final int SPEED = 10;
    private static final int OTHER_SPEED = 42;

    @BeforeEach
    void setUp(){
        super.basicSetup(new AxeWeapon(AXE_NAME, DAMAGE, SPEED));
    }

    @Test
    void constructorTest(){
        super.checkConstruction(new AxeWeapon(AXE_NAME, DAMAGE, SPEED),
                new AxeWeapon(OTHER_AXE_NAME, DAMAGE, SPEED),
                new AxeWeapon(AXE_NAME, OTHER_DAMAGE, SPEED),
                new AxeWeapon(AXE_NAME, DAMAGE, OTHER_SPEED),
                new SwordWeapon(OTHER_WEAPON_NAME, DAMAGE, SPEED));
    }
}
