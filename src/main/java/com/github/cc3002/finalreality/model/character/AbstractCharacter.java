package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.CharacterClass;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

import static java.lang.Math.max;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Sebastian Olmos.
 */
public abstract class AbstractCharacter implements ICharacter {

  protected final BlockingQueue<ICharacter> turnsQueue;
  protected final String name;
  private ScheduledExecutorService scheduledExecutor;
  private int health;
  private final int defense;

  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, int health, int defense) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.health = health;
    this.defense = defense;
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor
            .schedule(this::addToQueue, getWeaponWeight() / 10, TimeUnit.SECONDS);
  }

  /**
   * Adds this character to the turns queue.
   */
  private void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public abstract CharacterClass getCharacterClass();

  @Override
  public int getHealth() {
    return health;
  }

  /**
   * Change the life points.
   * @param health
   *     amount of health to change.
   */
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Returns the defense points.
   */
  public int getDefense() {
    return defense;
  }

  @Override
  public boolean itsAlive() {
    return getHealth()>0;
  }

  /**
   * Can receive damage if this character it's alive.
   */
  @Override
  public void receiveAttackOf(int damage) {
    if (this.itsAlive()) {
      int trueDamage = max(damage - this.getDefense(), 0);
      int health = max(this.getHealth() - trueDamage, 0);
      this.setHealth(health);
    }
  }

}
