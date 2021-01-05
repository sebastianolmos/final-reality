package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.controller.handlers.IEventHandler;
import com.github.cc3002.finalreality.model.character.player.CharacterClass;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Sebastián Olmos.
 */
public interface ICharacter {

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   * Depends on getWeaponWeight() value
   */
  void waitTurn();

  /**
   * Returns this character's name.
   */
  String getName();

  /**
   * Returns this character's value that is used to calculate the waitTurn()
   */
  int getWeaponWeight();

  /**
   * Returns this character's class.
   */
  CharacterClass getCharacterClass();

  /**
   * Returns this character's health points.
   */
  int getHealth();

  /**
   * Returns this character's defense points.
   */
  int getDefense();

  /**
   * Returns this Character's amount of damage .
   */
  int getDamage();

  /**
   * this character attacks a foe character
   * @param foe
   *     the character that receives the attack
   */
  void attack(ICharacter foe);

  /**
   * this character receives an amount of damage
   * @param damage
   *     amount to calculate health points lost
   */
  void receiveAttackOf(int damage);

  /**
   * Returns this character's state of life.
   */
  boolean itsAlive();

  /**
   * Add a listener to notify when the character die
   * @param defeatHandler
   *     Handler that call the event
   */
  void addDefeatListener(IEventHandler defeatHandler);

  /**
   * Add a listener to notify when the character was added to the turn queue
   * @param addToQueueHandler
   *     Handler that call the event
   */
  void addToQueueListener(IEventHandler addToQueueHandler);

  /**
   * Check if the character can be play by the user
   */
  boolean isPlayable();
}
