package com.github.cc3002.finalreality.model.character;

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
}
