package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.CharacterClass;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastian Olmos.
 */
public class Enemy extends AbstractCharacter {

  private final int weight;
  private final int damage;


  /**
   * Creates a new enemy.
   *
   * @param name
   *     the character's name
   * @param weight
   *     the character's weight
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   */
  public Enemy(@NotNull final String name, final int weight,
      @NotNull final BlockingQueue<ICharacter> turnsQueue, int health, int defense, final int damage) {
    super(turnsQueue, name, health, defense);
    this.weight = weight;
    this.damage =  damage;
  }
  public Enemy(@NotNull final String name, final int weight,
               @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    this(name, weight, turnsQueue, 20, 5, 10);
  }


  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return weight;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return getWeight() == enemy.getWeight()
            && getName().equals(enemy.getName())
            && getDamage() == enemy.getDamage();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getWeight(), getName(), getDamage());
  }

  /**
   * The Enemy can't equip weapons, so its value to wait in the queue is the weight.
   */
  @Override
  public int getWeaponWeight() {
    return getWeight();
  }

  @Override
  public CharacterClass getCharacterClass(){
    return CharacterClass.ENEMY;
  }

  @Override
  public int getDamage() {
    return this.damage;
  }

}
