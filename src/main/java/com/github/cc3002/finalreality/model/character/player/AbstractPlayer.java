package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

public abstract class AbstractPlayer extends AbstractCharacter implements IPlayer {

    private IWeapon equippedWeapon = null;

    protected AbstractPlayer(@NotNull BlockingQueue<ICharacter> turnsQueue,
                             @NotNull String name) {
        super(turnsQueue, name);
    }

    @Override
    public void equip(IWeapon weapon) {
        this.equippedWeapon = weapon;
    }

    @Override
    public IWeapon getEquippedWeapon() {
        return equippedWeapon;
    }

    @Override
    public int getWeaponWeight() {
        return getEquippedWeapon().getWeight();
    }

}
