package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.controller.handlers.*;
import com.github.cc3002.finalreality.controller.phases.Phase;
import com.github.cc3002.finalreality.controller.phases.WaitingTurnPhase;
import com.github.cc3002.finalreality.gui.nodeSelection.*;
import com.github.cc3002.finalreality.gui.scenes.IGameOverScene;
import com.github.cc3002.finalreality.gui.scenes.NullGameOverScene;
import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.*;
import com.github.cc3002.finalreality.model.weapon.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

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
    private final BlockingQueue<ICharacter> turns = new LinkedBlockingQueue<>();
    private ICharacter selectedCharacter;
    private final List<IWeapon> inventory = new ArrayList<>();
    private final int inventorySize;
    private final IEventHandler partyCharacterDefeatHandler = new PartyCharacterDefeatHandler(this);
    private final IEventHandler enemyCharacterDefeatHandler = new EnemyCharacterDefeatHandler(this);
    private int partyLeft;
    private int enemiesLeft;
    private boolean isTurnBeingPlayed;
    private Phase phase;
    private final IEventHandler partyCharacterAddedHandler = new PartyCharacterAddedToQueueHandler(this);
    private final IEventHandler enemyCharacterAddedHandler = new EnemyCharacterAddedToQueueHandler(this);
    private INodeTargetEnemy targetMenu = new NullNodeTargetEnemy();
    private int enemyWaitTime = 1000;
    private INodeInventory inventoryMenu = new NullNodeInventory();
    private ScheduledExecutorService scheduledExecutor;
    private IGameOverScene gameOverScene = new NullGameOverScene();

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
        setPhase(new WaitingTurnPhase());
    }

    /**
     * Create a controller with default sizes.
     */
    public GameController(){
        this(5, 5, 10);
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
        phase.setController(this);
    }

    /**
     * Add a playable character to the party and the listeners.
     * @param character
     *     character to be added.
     */
    private void addToParty(IPlayer character) {
        if (party.size() < partySize) {
            character.addDefeatListener(partyCharacterDefeatHandler);
            character.addToQueueListener(partyCharacterAddedHandler);
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
            character.addDefeatListener(enemyCharacterDefeatHandler);
            character.addToQueueListener(enemyCharacterAddedHandler);
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
            if (character.getEquippedWeapon().equals(NullWeapon.getInstance())) {
                IWeapon newWeapon = inventory.get(inventoryIndex);
                character.equip(newWeapon);
                if (newWeapon.equals(character.getEquippedWeapon())) {
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
     * Equip a weapon from the inventory to the selected Character that is playable
     * @param inventoryIndex
     *     Integer of the weapon contained
     */
    public void equipOnSelectedCharacter(int inventoryIndex) {
        equipFromInventoryOn(inventoryIndex, (IPlayer) selectedCharacter);
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
     * Return a weapon from the inventory
     * @param inventoryIndex
     *     Integer of the weapon contained.
     */
    public IWeapon getWeaponFromInventory(int inventoryIndex) {
        return inventory.get(inventoryIndex% inventory.size());
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
        attackTo(selectedCharacter, receiver);
    }

    /**
     * Get the instance of the selected Characters
     */
    public ICharacter getSelectedCharacter() {
        return selectedCharacter;
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
     * Method that be called when the user win and
     * call methods of the gui
     */
    public void playerWon() {
        gameOverScene.setVictory();
        gameOverScene.activate();
    }

    /**
     * Method that be called when the user lose and
     * call methods of the gui
     */
    public void playerLost() {
        gameOverScene.setDefeat();
        gameOverScene.activate();
    }

    /**
     * End the current turn.
     */
    public void endTurn() {
        turns.poll();
        selectedCharacter.waitTurn();
        phase.toEndingTurn();
    }

    /**
     * Check if the user is playing.
     */
    public boolean isTurnBeingPlayed() {
        return isSelectingAction() ||
                isSelectingWeapon() ||
                isSelectingAttackTarget();
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

    /**
     * Call a Phase method to change to the Selecting Attack target phase.
     */
    public void toAttackTargetSelection() {
        phase.toSelectingAttackTarget(targetMenu);
    }

    /**
     * Call a Phase method to change to the Selecting weapon phase.
     */
    public void toWeaponSelection() {
        phase.toSelectingWeapon();
    }

    /**
     * Call a Phase method to try the selection of a weapon.
     * @param weaponIndex
     *     Weapon index to be selected from the inventory
     */
    public void selectWeapon(int weaponIndex) {
        phase.selectWeaponFromInventory(weaponIndex);
    }

    /**
     * Call a Phase method to try to equip the selected weapon
     */
    public void equipSelectedWeapon() {
        phase.equipSelectedWeapon();

    }

    /**
     * Call a Phase method to try to change the phase to selecting action
     */
    public void toActionSelection() {
        phase.toSelectingAction();
    }

    /**
     * Call a Phase method to try to attack the target character
     */
    public void attackTarget() {
        phase.attackTargetCharacter();
    }

    /**
     * Call a Phase method to try to begin a new turn
     */
    public void tryBeginTurn() {
        phase.beginTurn();
    }

    /**
     * Start a new turn depending of the character type
     */
    public void startTurn() {
        selectedCharacter = turns.peek();

        if (!selectedCharacter.itsAlive()) {
            turns.poll();
            phase.toEndingTurn();
        }
        else {
            if (selectedCharacter.isPlayable()) {
                phase.toSelectingAction();
            } else {
                playEnemyTurn();
            }
        }
    }

    /**
     * Return a list of the index corresponding to the location in the party list
     */
    private List<Integer> getAlivePlayerIndex() {
        List<Integer> alives = new ArrayList<>();
        for (int i = 0; i < party.size(); i++) {
            if (party.get(i).itsAlive()) {
                alives.add(i);
            }
        }
        return alives;
    }

    /**
     * Execute the attack of an enemy character
     */
    private void playEnemyTurn() {
        selectedCharacter = turns.peek();
        phase.toSelectingAction();
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor
                .schedule(this::enemyAction, enemyWaitTime, TimeUnit.MILLISECONDS);
    }

    /**
     * Random attack of the enemy character that is executed after a timer
     */
    private void enemyAction() {
        scheduledExecutor.shutdown();
        phase.toSelectingAttackTarget();
        long seed = new Random().nextLong();
        Random rand = new Random(seed);
        List<Integer> aliveCharacters = getAlivePlayerIndex();
        int index = rand.nextInt(aliveCharacters.size());
        int randIndex = aliveCharacters.get(index);

        targetPartyCharacter(randIndex);
        attackTarget();
    }

    /**
     * Call a Phase method to try to get the name of the selected Weapon
     */
    public String getSelectedWeaponName() {
        return phase.getSelectedWeaponName();
    }

    /**
     * Call a Phase method to try to get the damage of the selected Weapon
     */
    public int getSelectedWeaponDamage() {
        return phase.getSelectedWeaponDamage();
    }

    /**
     * Call a Phase method to try to get the weight of the selected Weapon
     */
    public int getSelectedWeaponWeight() {
        return phase.getSelectedWeaponWeight();
    }

    /**
     * Call a Phase method to try to get the type of the selected Weapon
     */
    public WeaponType getSelectedWeaponType() {
        return phase.getSelectedWeaponType();
    }

    /**
     * Call a Phase method to try to get the name of the weapon equipped by the selected Character
     */
    public String getEquippedWeaponName() {
        return phase.getEquippedWeaponName();
    }

    /**
     * Call a Phase method to try to get the damage of the weapon equipped by the selected Character
     */
    public int getEquippedWeaponDamage() {
        return phase.getEquippedWeaponDamage();
    }

    /**
     * Call a Phase method to try to get the weight of the weapon equipped by the selected Character
     */
    public int getEquippedWeaponWeight() {
        return phase.getEquippedWeaponWeight();
    }

    /**
     * Call a Phase method to try to get the type of the weapon equipped by the selected Character
     */
    public WeaponType getEquippedWeaponType() {
        return phase.getEquippedWeaponType();
    }

    /**
     * Call a Phase method to try to target/select a character to attack
     * @param character
     *      character to be targeted
     */
    private void targetCharacter(ICharacter character) {
        phase.selectTarget(character);
    }

    /**
     * Call a Phase method to try to target/select an enemy character to attack
     * @param enemyIndex
     *      enemy character index to be targeted
     */
    public void targetEnemyCharacter(int enemyIndex) {
        targetCharacter(enemies.get(enemyIndex % enemies.size()));
    }

    /**
     * Call a Phase method to try to target/select a party character to attack
     * @param partyIndex
     *      party character index to be targeted
     */
    public void targetPartyCharacter(int partyIndex) {
        targetCharacter(party.get(partyIndex % party.size()));
    }

    /**
     * Put all the characters in the turn queue to start the battle
     * first enter the party characters the the enemies, without considering the weight
     */
    public void basicTurnSetup() {
        for (IPlayer partyCharacter : party) {
            turns.add(partyCharacter);
        }
        for (Enemy enemyCharacter : enemies) {
            turns.add(enemyCharacter);
        }
        tryBeginTurn();
    }

    /**
     * Check if the character playing is in the selecting action phase
     */
    public boolean isSelectingAction() {
        return phase.isSelectingAction();
    }

    /**
     * Check if the character playing is in the selecting weapon phase
     */
    public boolean isSelectingWeapon() {
        return phase.isSelectingWeapon();
    }

    /**
     * Check if the character playing is in the selecting target phase
     */
    public boolean isSelectingAttackTarget() {
        return phase.isSelectingAttackTarget();
    }

    /**
     * Get a copy of the turn queue
     */
    public BlockingQueue<ICharacter> getTurnsQueue() {
        return new LinkedBlockingQueue<>(turns);
    }

    /**
     * Get the index in the party list of a playable character
     */
    public int getSelectedPartyCharacterIndex() {
        return party.indexOf((IPlayer) selectedCharacter);
    }

    /**
     * Get the index in the enemies list of a enemy character
     */
    public int getSelectedEnemyCharacterIndex() {
        return enemies.indexOf((Enemy) selectedCharacter);
    }

    /**
     * Check if the current turn is being played by a party character
     */
    public boolean isTurnBeingPlayedByParty() {
        return isTurnBeingPlayed() && selectedCharacter.isPlayable();
    }

    /**
     * Check if the current turn is being played by an enemy character
     */
    public boolean isTurnBeingPlayedByEnemy() {
        return isTurnBeingPlayed() && !selectedCharacter.isPlayable();
    }

    /**
     * Return the type of a weapon from the inventory
     * @param weaponIndex
     *      index of the weapon to be consulted
     */
    public WeaponType getWeaponTypeFromInventory(int weaponIndex) {
        return inventory.get(weaponIndex%inventory.size()).getType();
    }

    /**
     * Get the size of the enemies list
     */
    public int getEnemiesSize() {
        return enemies.size();
    }

    /**
     * Call the phase to ask if is any character targeted
     */
    public boolean isACharacterTargeted() {
        return phase.isACharacterTargeted();
    }

    /**
     * Set the reference of a gui element
     * @param targetMenu
     *      gui element that manages the display of enemies to attack
     */
    public void setTargetMenuGui(INodeTargetEnemy targetMenu) {
        this.targetMenu = targetMenu;
    }

    /**
     * Set the reference of a gui element
     * @param inventoryMenu
     *      gui element that manages the display of the inventory and weapons
     */
    public void setInventoryMenuGui(INodeInventory inventoryMenu) {
        this.inventoryMenu = inventoryMenu;
    }

    /**
     * Call a gui element to refresh the current list of weapons in the inventory
     */
    public void updateInventoryMenu() {
        inventoryMenu.updateInventoryView();
    }

    /**
     * Set the reference of a gui element
     * @param gameOverScene
     *      gui element that manages the scene of game over
     */
    public void setGameOverScene(IGameOverScene gameOverScene) {
        this.gameOverScene = gameOverScene;
    }

    /**
     * Set the time it takes an enemy character to execute its turn
     * @param enemyWaitTime
     *      Time in milliseconds that takes
     */
    public void setEnemyWaitTime(int enemyWaitTime) {
        this.enemyWaitTime = enemyWaitTime;
    }
}
