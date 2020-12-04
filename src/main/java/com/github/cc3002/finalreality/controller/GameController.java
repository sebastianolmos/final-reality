package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.*;
import com.github.cc3002.finalreality.model.weapon.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This represents a Controller that manage the turns and characters logic that
 * the users will use
 *
 * @author Sebastián Olmos.
 */
public class GameController {
    private final List<IPlayer> party = new ArrayList<>();
    private final int partySize;
    private final List<Enemy> enemies = new ArrayList<>();
    private final int enemiesSize;
    private final IEventHandler characterTurnHandler = new CharacterTurnHandler(this);
    private final BlockingQueue<ICharacter> turns = new LinkedBlockingQueue<>();
    private ICharacter selectedCharacter;
    private final List<IWeapon> inventory = new ArrayList<>();
    private final int inventorySize;
    private final IEventHandler partyCharacterDefeatHandler = new PartyCharacterDefeatHandler(this);
    private final IEventHandler enemyCharacterDefeatHandler = new EnemyCharacterDefeatHandler(this);
    private int partyLeft;
    private int enemiesLeft;
    private boolean isTurnBeingPlayed;

    /**
     * Create a controller with the sizes of the object that will contain.
     * @param partySize
     *     Maximum amount of playable characters allowed.
     * @param enemySize
     *     Maximum amount of enemy characters allowed.
     * @param inventorySize
     *     Maximum amount of inventory weapons allowed.
     */
    public GameController(int partySize, int enemySize, int inventorySize) {
        this.partySize = partySize;
        this.enemiesSize = enemySize;
        this.inventorySize = inventorySize;
        partyLeft = 0;
        enemiesLeft = 0;
        isTurnBeingPlayed = false;
    }

    /**
     * Create a controller with default sizes.
     */
    public GameController(){
        this(5, 5, 10);
    }

    /**
     * Add a playable character to the party and the listeners.
     * @param character
     *     character to be added.
     */
    private void addToParty(IPlayer character) {
        if (party.size() < partySize) {
            character.addPlayTurnListener(characterTurnHandler);
            character.addDefeatListener(partyCharacterDefeatHandler);
            party.add(character);
            partyLeft ++;
        }
    }

    /**
     * Add a enemy character to the party and the listeners.
     * @param name
     *     Name of the new enemy.
     * @param health
     *     Amount of health points.
     * @param defense
     *     Amount of defense points.
     * @param weight
     *     Amount of weight related to turn waiting time
     * @param damage
     *     Damage produced by an attack
     */
    public void addEnemyToEnemies(String name, int health, int defense, int weight, int damage) {
        if (enemies.size() < enemiesSize) {
            Enemy character = new Enemy(name, weight, turns, health, defense, damage);
            character.addPlayTurnListener(characterTurnHandler);
            character.addDefeatListener(enemyCharacterDefeatHandler);
            enemies.add(character);
            enemiesLeft ++;
        }
    }

    /**
     * Add a weapon to the Inventory.
     * @param weapon
     *     Weapon object to be added.
     */
    private void addToInventory(IWeapon weapon) {
        if (inventory.size() < inventorySize) {
            inventory.add(weapon);
        }
    }

    /**
     * Add a Black Mage character to the party.
     * @param name
     *     Name of the new enemy.
     * @param health
     *     Amount of health points.
     * @param defense
     *     Amount of defense points.
     */
    public void addBlackMageToParty(String name, int health, int defense) {
        addToParty(new BlackMage(turns, name, health, defense));
    }

    /**
     * Add an Engineer character to the party.
     * @param name
     *     Name of the new enemy.
     * @param health
     *     Amount of health points.
     * @param defense
     *     Amount of defense points.
     */
    public void addEngineerToParty(String name, int health, int defense) {
        addToParty(new Engineer(turns, name, health, defense));
    }

    /**
     * Add a Knight character to the party.
     * @param name
     *     Name of the new enemy.
     * @param health
     *     Amount of health points.
     * @param defense
     *     Amount of defense points.
     */
    public void addKnightToParty(String name, int health, int defense) {
        addToParty(new Knight(turns, name, health, defense));
    }

    /**
     * Add a Thief character to the party.
     * @param name
     *     Name of the new enemy.
     * @param health
     *     Amount of health points.
     * @param defense
     *     Amount of defense points.
     */
    public void addThiefToParty(String name, int health, int defense) {
        addToParty(new Thief(turns, name, health, defense));
    }

    /**
     * Add a White Mage character to the party.
     * @param name
     *     Name of the new enemy.
     * @param health
     *     Amount of health points.
     * @param defense
     *     Amount of defense points.
     */
    public void addWhiteMageToParty(String name, int health, int defense) {
        addToParty(new WhiteMage(turns, name, health, defense));
    }

    /**
     * Add an Axe to the Inventory.
     * @param name
     *     Name of the new weapon.
     * @param weight
     *     Amount of weight related to turn waiting time.
     * @param damage
     *     Amount of damage produced by an attack.
     */
    public void addAxeToInventory(String name, int weight, int damage) {
        addToInventory(new AxeWeapon(name, damage, weight));
    }

    /**
     * Add a Bow to the Inventory.
     * @param name
     *     Name of the new weapon.
     * @param weight
     *     Amount of weight related to turn waiting time.
     * @param damage
     *     Amount of damage produced by an attack.
     */
    public void addBowToInventory(String name, int weight, int damage) {
        addToInventory(new BowWeapon(name, damage, weight));
    }

    /**
     * Add a Knife to the Inventory.
     * @param name
     *     Name of the new weapon.
     * @param weight
     *     Amount of weight related to turn waiting time.
     * @param damage
     *     Amount of damage produced by an attack.
     */
    public void addKnifeToInventory(String name, int weight, int damage) {
        addToInventory(new KnifeWeapon(name, damage, weight));
    }

    /**
     * Add a Staff to the Inventory.
     * @param name
     *     Name of the new weapon.
     * @param weight
     *     Amount of weight related to turn waiting time.
     * @param damage
     *     Amount of damage produced by an attack.
     */
    public void addStaffToInventory(String name, int weight, int damage) {
        addToInventory(new StaffWeapon(name, damage, weight));
    }

    /**
     * Add a Sword to the Inventory.
     * @param name
     *     Name of the new weapon.
     * @param weight
     *     Amount of weight related to turn waiting time.
     * @param damage
     *     Amount of damage produced by an attack.
     */
    public void addSwordToInventory(String name, int weight, int damage) {
        addToInventory(new SwordWeapon(name, damage, weight));
    }

    /**
     * Check if the turns queue is empty.
     */
    public boolean isTurnsQueueEmpty() {
        return turns.isEmpty();
    }

    /**
     * Return a character of the party.
     * @param index
     *     Integer of the character contained.
     */
    public IPlayer getPartyCharacter(int index) {
        return party.get(index % party.size());
    }

    /**
     * Return the name of a character from the party.
     * @param index
     *     Integer of the character contained.
     */
    public String getPartyCharacterName(int index) {
        return party.get(index % party.size()).getName();
    }

    /**
     * Return the Amount of Health of a character from the party.
     * @param index
     *     Integer of the character contained.
     */
    public int getPartyCharacterHealth(int index) {
        return party.get(index % party.size()).getHealth();
    }

    /**
     * Return the Amount of Defense of a character from the party.
     * @param index
     *     Integer of the character contained.
     */
    public int getPartyCharacterDefense(int index) {
        return party.get(index % party.size()).getDefense();
    }

    /**
     * Return the Damage produced by an attack of a character from the party.
     * @param index
     *     Integer of the character contained.
     */
    public int getPartyCharacterDamage(int index) {
        return party.get(index % party.size()).getDamage();
    }

    /**
     * Return a enemy character of the enemies.
     * @param index
     *     Integer of the character contained.
     */
    public Enemy getEnemyCharacter(int index) {
        return enemies.get(index);
    }

    /**
     * Return the name of an Enemy.
     * @param index
     *     Integer of the character contained.
     */
    public String getEnemyCharacterName(int index) {
        return enemies.get(index % enemies.size()).getName();
    }

    /**
     * Return the Amount of Health of an Enemy.
     * @param index
     *     Integer of the character contained.
     */
    public int getEnemyCharacterHealth(int index) {
        return enemies.get(index % enemies.size()).getHealth();
    }

    /**
     * Return the Amount of Defense of an Enemy.
     * @param index
     *     Integer of the character contained.
     */
    public int getEnemyCharacterDefense(int index) {
        return enemies.get(index % enemies.size()).getDefense();
    }

    /**
     * Return the Damage produced by an attack of an Enemy.
     * @param index
     *     Integer of the character contained.
     */
    public int getEnemyCharacterDamage(int index) {
        return enemies.get(index % enemies.size()).getDamage();
    }

    /**
     * Return the Weight of an Enemy.
     * @param index
     *     Integer of the character contained.
     */
    public int getEnemyCharacterWeight(int index) {
        return enemies.get(index % enemies.size()).getWeight();
    }

    /**
     * Equip a weapon from the inventory to a playable character
     * @param inventoryIndex
     *     Integer of the weapon contained
     * @param character
     *     Object of the character
     */
    public void equipFromInventoryOn(int inventoryIndex, IPlayer character) {
        if (inventoryIndex < inventory.size()) {
            if (character.getEquippedWeapon() == null) {
                IWeapon newWeapon = inventory.get(inventoryIndex);
                character.equip(newWeapon);
                if (character.getEquippedWeapon() != null) {
                    inventory.remove(inventoryIndex);
                }
            } else {
                IWeapon currentWeapon = character.getEquippedWeapon();
                IWeapon newWeapon = inventory.get(inventoryIndex);
                character.equip(newWeapon);
                if (!currentWeapon.equals(character.getEquippedWeapon())) {
                    inventory.set(inventoryIndex, currentWeapon);
                }
            }
        }
    }

    /**
     * Equip a weapon from the inventory to a playable character by his index
     * @param inventoryIndex
     *     Integer of the weapon contained
     * @param partyCharacterIndex
     *     Integer of the playable character contained
     */
    public void equipFromInventoryOn(int inventoryIndex, int partyCharacterIndex) {
        equipFromInventoryOn(inventoryIndex, party.get(partyCharacterIndex));
    }

    /**
     * Return a weapon removed from the inventory
     * @param inventoryIndex
     *     Integer of the weapon contained.
     */
    public IWeapon removeFromInventory(int inventoryIndex) {
        return inventory.remove(inventoryIndex % inventory.size());
    }

    /**
     * Return the amount of weapons in the inventory.
     */
    public int getWeaponsAmountOnInventory() {
        return inventory.size();
    }

    /**
     * Make an attack from a character to another
     * @param attacker
     *     Object of the character that attacks
     * @param receiver
     *     Object of the character that receive the attack.
     */
    public void attackTo(ICharacter attacker, ICharacter receiver) {
        attacker.attack(receiver);
    }

    /**
     * Make an attack from a the character on turn to another character
     * @param receiver
     *     Object of the character that receive the attack.
     */
    public void turnCharacterAttackTo(ICharacter receiver) {
        if (isTurnBeingPlayed) {
            attackTo(selectedCharacter, receiver);
        }
    }

    /**
     * Return the name of the character on turn.
     */
    public String getTurnCharacterName() {
        if (isTurnBeingPlayed) {
            return selectedCharacter.getName();
        }
        else {
            return "";
        }
    }

    /**
     * Return the amount of Health of the character on turn.
     */
    public int getTurnCharacterHealth() {
        if (isTurnBeingPlayed) {
            return selectedCharacter.getHealth();
        }
        else {
            return 0;
        }
    }

    /**
     * Return the amount of Defense of the character on turn.
     */
    public int getTurnCharacterDefense() {
        if (isTurnBeingPlayed) {
            return selectedCharacter.getDefense();
        }
        else {
            return 0;
        }
    }

    /**
     * Return the Damage produce by an attack of the character on turn.
     */
    public int getTurnCharacterDamage() {
        if (isTurnBeingPlayed) {
            return selectedCharacter.getDamage();
        }
        else {
            return 0;
        }
    }

    /**
     * Update the number of remaining characters in the party.
     */
    public void updatePartyLeft() {
        partyLeft --;
        if (checkDefeat()) {
            playerLost();
        }
    }

    /**
     * Update the number of remaining enemies.
     */
    public void updateEnemiesLeft() {
        enemiesLeft --;
        if (checkWin()) {
            playerWon();
        }
    }

    /**
     * Check the win conditions.
     */
    public boolean checkWin() {
        return partyLeft > 0 && enemiesLeft == 0;
    }

    /**
     * Check the lose conditions.
     */
    public boolean checkDefeat() {
        return partyLeft == 0;
    }

    /**
     * Method that be called when the user win.
     */
    public void playerWon() {}

    /**
     * Method that be called when the user lose.
     */
    public void playerLost() {}

    /**
     * Start a new turn if the user si not playing.
     */
    public void startTurn() {
        if (!isTurnBeingPlayed) {
            selectedCharacter = turns.peek();
            isTurnBeingPlayed = true;
        }
    }

    /**
     * End the current turn.
     */
    public void endTurn() {
        selectedCharacter = turns.poll();
        selectedCharacter.waitTurn();
        isTurnBeingPlayed = false;
    }

    /**
     * Check if the user si not playing.
     */
    public boolean isTurnBeingPlayed() {
        return isTurnBeingPlayed;
    }

    /**
     * Return the number of remaining characters of the party.
     */
    public int getRemainingPartyCharacters() {
        return partyLeft;
    }

    /**
     * Return the number of remaining enemies.
     */
    public int getRemainingEnemies() {
        return enemiesLeft;
    }
}
