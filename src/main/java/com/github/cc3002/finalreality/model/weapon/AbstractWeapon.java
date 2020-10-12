package com.github.cc3002.finalreality.model.weapon;

public abstract class AbstractWeapon implements IWeapon{
    private final String name;
    private final int damage;
    private final int weight;

    protected AbstractWeapon(final String name, final int damage, final int weight){
        this.name = name;
        this.damage = damage;
        this.weight = weight;
    }

    protected String getName() {
        return name;
    }

    protected int getDamage() {
        return damage;
    }

    public int getWeight() {
        return weight;
    }
}
