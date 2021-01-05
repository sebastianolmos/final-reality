package com.github.cc3002.finalreality.gui;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.gui.nodeCharacters.*;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents a class that manage the game controller to add the user input
 * and generate the models and gui elements to make a battle
 *
 * @author Sebastián Olmos.
 */
public class GameCreator {
    private final GameController controller;
    private final List<INodeCharacter> partyNodes;
    private final List<NodeEnemy> enemiesNodes;
    private final List<Group> positionedPartyFiguresNodes;
    private final List<Group> positionedPartyMenuNodes;
    private final List<Group> positionedEnemiesNodes;

    // Several constant values of the gui elements
    private static final int PARTY_BOTTOM_X_POS = 100;
    private static final int PARTY_BOTTOM_Y_POS = 480;
    private static final int PARTY_TOP_X_POS = 300;
    private static final int PARTY_TOP_Y_POS = 300;

    private static final int PARTY_MENU_Y_POS= 670;
    private static final int PARTY_MENU_WIDTH = 180;
    private static final int PARTY_MENU_GAP = 20;

    private static final int ENEMIES_BOTTOM_X_POS = 820;
    private static final int ENEMIES_BOTTOM_Y_POS = 500;
    private static final int ENEMIES_TOP_X_POS = 650;
    private static final int ENEMIES_TOP_Y_POS = 320;


    /**
     * Get the game controller reference and create the different list that will contain the game elements
     * @param controller
     *      Reference of the gameController
     */
    public GameCreator(GameController controller) {
        this.controller = controller;
        this.partyNodes = new ArrayList<>();
        this.enemiesNodes = new ArrayList<>();
        positionedPartyFiguresNodes = new ArrayList<>();
        positionedPartyMenuNodes = new ArrayList<>();
        positionedEnemiesNodes = new ArrayList<>();
    }

    /**
     * Add a blackMage to the model using the controller and generate its elements in the gui
     * @param name
     *      String with the name of the character
     * @param health
     *      Health value of the character
     * @param defense
     *      Defense value of the character
     */
    public void addBlackMage(String name, int health, int defense) {
        if (controller.getRemainingPartyCharacters() < 5) {
            controller.addBlackMageToParty(name, health, defense);
            partyNodes.add(new NodeBlackMage(controller, controller.getRemainingPartyCharacters() - 1, health));
        }
    }

    /**
     * Add an engineer to the model using the controller and generate its elements in the gui
     * @param name
     *      String with the name of the character
     * @param health
     *      Health value of the character
     * @param defense
     *      Defense value of the character
     */
    public void addEngineer(String name, int health, int defense) {
        if (controller.getRemainingPartyCharacters() < 5) {
            controller.addEngineerToParty(name, health, defense);
            partyNodes.add(new NodeEngineer(controller, controller.getRemainingPartyCharacters() - 1, health));
        }
    }

    /**
     * Add a knight to the model using the controller and generate its elements in the gui
     * @param name
     *      String with the name of the character
     * @param health
     *      Health value of the character
     * @param defense
     *      Defense value of the character
     */
    public void addKnight(String name, int health, int defense) {
        if (controller.getRemainingPartyCharacters() < 5) {
            controller.addKnightToParty(name, health, defense);
            partyNodes.add(new NodeKnight(controller, controller.getRemainingPartyCharacters() - 1, health));
        }
    }

    /**
     * Add a thief to the model using the controller and generate its elements in the gui
     * @param name
     *      String with the name of the character
     * @param health
     *      Health value of the character
     * @param defense
     *      Defense value of the character
     */
    public void addThief(String name, int health, int defense) {
        if (controller.getRemainingPartyCharacters() < 5) {
            controller.addThiefToParty(name, health, defense);
            partyNodes.add(new NodeThief(controller, controller.getRemainingPartyCharacters() - 1, health));
        }
    }

    /**
     * Add a whiteMage to the model using the controller and generate its elements in the gui
     * @param name
     *      String with the name of the character
     * @param health
     *      Health value of the character
     * @param defense
     *      Defense value of the character
     */
    public void addWhiteMage(String name, int health, int defense) {
        if (controller.getRemainingPartyCharacters() < 5) {
            controller.addWhiteMageToParty(name, health, defense);
            partyNodes.add(new NodeWhiteMage(controller, controller.getRemainingPartyCharacters() - 1, health));
        }
    }

    /**
     * Add an enemy to the model using the controller and generate its elements in the gui
     * @param name
     *      String with the name of the enemy
     * @param health
     *      Health value of the enemy
     * @param defense
     *      Defense value of the enemy
     * @param damage
     *      Damage value of the enemy
     * @param weight
     *      Weight value of the enemy
     */
    public void addEnemy(String name, int health, int defense, int damage, int weight) {
        if (controller.getRemainingEnemies() < 5) {
            controller.addEnemyToEnemies(name, health, defense, weight, damage);
            enemiesNodes.add(new NodeEnemy(controller, controller.getRemainingEnemies() - 1));
        }
    }

    /**
     * Position the characters gui elements in the scene and call the controller to start the game
     */
    public void create() {
        positionParty();
        positionEnemies();
        controller.basicTurnSetup();
    }


    /**
     * Create a default 5 vs 5 battle
     */
    public void createDefaultGame() {
        addKnight("Knight", 400, 50);
        addBlackMage("BlackMage", 300, 20);
        addEngineer("Engineer", 280, 60);
        addThief("Thief", 200, 30);
        addWhiteMage("WhiteMage", 250, 70);

        addEnemy("Enemy1", 110, 22, 120, 20);
        addEnemy("Enemy2", 120, 40, 140, 70);
        addEnemy("Enemy3", 130, 26, 103, 15);
        addEnemy("Enemy4", 140, 33, 72, 12);
        addEnemy("Enemy5", 150, 11, 80, 15);

        controller.addAxeToInventory("WoodenAxe", 20, 70);
        controller.equipFromInventoryOn(0, 0);
        controller.addKnifeToInventory("WoodenKnife", 10, 50);
        controller.equipFromInventoryOn(0, 1);
        controller.addBowToInventory("WoodenBow", 12, 55);
        controller.equipFromInventoryOn(0, 2);
        controller.addSwordToInventory("WoodenSword", 15, 65);
        controller.equipFromInventoryOn(0, 3);
        controller.addStaffToInventory("WoodenStaff", 17, 60);
        controller.equipFromInventoryOn(0, 4);
        controller.addAxeToInventory("DiamondAxe", 60, 120);
        controller.addBowToInventory("DiamondBow", 53, 80);
        controller.addKnifeToInventory("DiamondKnife", 40, 60);
        controller.addStaffToInventory("DiamondStaff", 50, 110);
        controller.addSwordToInventory("DiamondSword", 55, 100);

        create();
    }


    /**
     * Create a 3 vs 1 boss battle
     */
    public void createBossBattle() {
        addKnight("Hero 1", 400, 50);
        addBlackMage("Hero 2", 300, 20);
        addKnight("Hero 3", 400, 50);

        addEnemy("Final Boss", 4000, 30, 250, 120);

        controller.addAxeToInventory("MasterAxe", 60, 150);
        controller.equipFromInventoryOn(0, 0);
        controller.addStaffToInventory("MasterStaff", 80, 170);
        controller.equipFromInventoryOn(0, 1);
        controller.addSwordToInventory("MasterSword", 50, 140);
        controller.equipFromInventoryOn(0, 2);
        controller.addAxeToInventory("LightAxe", 24, 60);
        controller.addKnifeToInventory("LightKnife", 8, 30);
        controller.addStaffToInventory("LightStaff", 16, 36);
        controller.addSwordToInventory("LightSword", 20, 50);

        create();
    }

    /**
     * Return a list with the character nodes that manage the gui elements to each party character
     */
    public List<INodeCharacter> getPartyNodes() {
        return partyNodes;
    }

    /**
     * Return a list with the enemy nodes that manage the gui elements to each enemies character
     */
    public List<NodeEnemy> getEnemiesNodes() {
        return enemiesNodes;
    }

    /**
     * Return the positioned Group node with the figure on the battlefield to each party character
     */
    public List<Group> getPositionedPartyFiguresNodes() {
        return positionedPartyFiguresNodes;
    }

    /**
     * Return the positioned Group node with the portrait menu of each party character
     */
    public List<Group> getPositionedPartyMenuNodes() {
        return positionedPartyMenuNodes;
    }

    /**
     * Return the positioned Group node with the figure on the battlefield to each enemies character
     */
    public List<Group> getPositionedEnemiesNodes() {
        return positionedEnemiesNodes;
    }


    /**
     * Position the figures nodes of the party characters in the battlefield
     * and the portrait menu's
     */
    private void positionParty() {
        if (partyNodes.size() == 1) {
            var node = partyNodes.get(0);
            var tempGroup = new Group();
            int posX = PARTY_BOTTOM_X_POS + ((PARTY_TOP_X_POS - PARTY_BOTTOM_X_POS) >> 1);
            int posY = PARTY_BOTTOM_Y_POS - ((PARTY_BOTTOM_Y_POS - PARTY_TOP_Y_POS) >> 1);
            tempGroup.setLayoutX(posX);
            tempGroup.setLayoutY(posY);
            tempGroup.getChildren().add(node.getFigureNode());
            positionedPartyFiguresNodes.add(tempGroup);

            var tempMenuGroup = new Group();
            tempMenuGroup.setLayoutX(PARTY_MENU_GAP >> 1);
            tempMenuGroup.setLayoutY(PARTY_MENU_Y_POS);
            tempMenuGroup.getChildren().add(node.getMenuNode());
            positionedPartyMenuNodes.add(tempMenuGroup);
        } else {
            int gaps = partyNodes.size() - 1;
            int xStep = (PARTY_TOP_X_POS - PARTY_BOTTOM_X_POS) / gaps;
            int yStep = (PARTY_BOTTOM_Y_POS - PARTY_TOP_Y_POS) / gaps;

            for (int i = 0; i < partyNodes.size(); i++) {
                var node = partyNodes.get(i);
                var tempGroup = new Group();
                tempGroup.setLayoutX(PARTY_BOTTOM_X_POS + i * xStep);
                tempGroup.setLayoutY(PARTY_BOTTOM_Y_POS - i * yStep);
                tempGroup.getChildren().add(node.getFigureNode());
                positionedPartyFiguresNodes.add(tempGroup);

                var tempMenuGroup = new Group();
                tempMenuGroup.setLayoutX((PARTY_MENU_GAP >> 1) + (i * PARTY_MENU_WIDTH) + (i*PARTY_MENU_GAP));
                tempMenuGroup.setLayoutY(PARTY_MENU_Y_POS);
                tempMenuGroup.getChildren().add(node.getMenuNode());
                positionedPartyMenuNodes.add(tempMenuGroup);
            }
        }
    }

    /**
     * Position the figures nodes of the enemies characters in the battlefield
     */
    private void positionEnemies() {
        if (enemiesNodes.size() == 1) {
            var node = enemiesNodes.get(0);
            var tempGroup = new Group();
            int posX = ENEMIES_BOTTOM_X_POS - ((ENEMIES_BOTTOM_X_POS - ENEMIES_TOP_X_POS) >> 1);
            int posY = ENEMIES_BOTTOM_Y_POS - ((ENEMIES_BOTTOM_Y_POS - ENEMIES_TOP_Y_POS) >> 1);
            tempGroup.setLayoutX(posX);
            tempGroup.setLayoutY(posY);
            tempGroup.getChildren().add(node.getFigureNode());
            positionedEnemiesNodes.add(tempGroup);
        } else {
            int gaps = enemiesNodes.size() - 1;
            int xStep = (ENEMIES_BOTTOM_X_POS - ENEMIES_TOP_X_POS) / gaps;
            int yStep = (ENEMIES_BOTTOM_Y_POS - ENEMIES_TOP_Y_POS) / gaps;

            for (int i = 0; i < enemiesNodes.size(); i++) {
                var node = enemiesNodes.get(i);
                var tempGroup = new Group();
                tempGroup.setLayoutX(ENEMIES_BOTTOM_X_POS - i * xStep);
                tempGroup.setLayoutY(ENEMIES_BOTTOM_Y_POS - i * yStep);
                tempGroup.getChildren().add(node.getFigureNode());
                positionedEnemiesNodes.add(tempGroup);
            }
        }
    }

}
