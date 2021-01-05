package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.controller.handlers.IEventHandler;
import com.github.cc3002.finalreality.model.character.player.CharacterClass;

import java.beans.PropertyChangeSupport;
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
  private final PropertyChangeSupport defeatNotification = new PropertyChangeSupport(this);
  private final PropertyChangeSupport addedToQueueNotification = new PropertyChangeSupport(this);

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
    addedToQueueNotification.firePropertyChange("A character was added to the turns queue", true, false);
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

  @Override
  public int getDefense() {
    return defense;
  }

  @Override
  public boolean itsAlive() {
    return getHealth()>0;
  }

  /**
   * Character can attack if it's alive.
   */
  @Override
  public void attack(ICharacter foe) {
    if (this.itsAlive()) {
      foe.receiveAttackOf(this.getDamage());
    }
  }

  /**
   * Can receive damage if this character it's alive.
   */
  @Override
  public void receiveAttackOf(int damage) {
    if (this.itsAlive()) {
      int trueDamage = max(damage - this.getDefense(), 0);
      int currentHealth = this.getHealth();
      int health = max(currentHealth - trueDamage, 0);
      this.setHealth(health);
      defeatNotification.firePropertyChange("This character was defeated", true, itsAlive());
    }
  }

  @Override
  public void addDefeatListener(IEventHandler defeatHandler) {
    defeatNotification.addPropertyChangeListener(defeatHandler);
  }

  @Override
  public void addToQueueListener(IEventHandler addToQueueHandler) {
    addedToQueueNotification.addPropertyChangeListener(addToQueueHandler);
  }

}
