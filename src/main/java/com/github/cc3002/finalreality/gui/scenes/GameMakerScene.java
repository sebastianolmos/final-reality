package com.github.cc3002.finalreality.gui.scenes;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.gui.GameCreator;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.FontWeight;

/**
 * This represents the scene where the user can make the battle, adding the party
 * characters, enemies and weapons, with a max amount of 5 for the party characters an enemies,
 * and 27 for the amount of weapons.
 * You need to create at least 1 party character, 1 enemy and 1 weapon to start a battle
 *
 * @author Sebastián Olmos.
 */
public class GameMakerScene extends ASceneWithAudioAndTimer{
    private final GameCreator gameCreator;
    private Label partyCounterLabel;
    private TextField partyNameField;
    private TextField partyHealthField;
    private TextField partyDefenseField;
    private Label enemiesCounterLabel;
    private TextField enemyNameField;
    private TextField enemyHealthField;
    private TextField enemyDefenseField;
    private TextField enemyDamageField;
    private TextField enemyWeightField;
    private Label weaponsCounterLabel;
    private TextField weaponNameField;
    private TextField weaponDamageField;
    private TextField weaponWeightField;

    // Several constant value of the gui elements
    private static final String SOUND_FILE_NAME = "clips/EventMusic1.wav";

    private static final int TITTLE_FONT_SIZE = 40;
    private static final int COUNTER_FONT_SIZE = 25;
    private static final int PROPERTY_FONT_SIZE = 16;
    private static final int TITTLE_X_POS = 350;
    private static final int TITTLE_Y_POS = 10;
    private static final int COUNTER_X_POS = 20;
    private static final int PARTY_COUNTER_Y_POS = 70;
    private static final int ENEMIES_COUNTER_Y_POS = 260;
    private static final int WEAPONS_COUNTER_Y_POS = 450;

    private static final int PARTY_FIELD_WIDTH = 180;
    private static final int PARTY_FIELD_HEIGHT = 40;
    private static final int PARTY_BUTTON_WIDTH = 180;
    private static final int PARTY_BUTTON_HEIGHT = 40;

    private static final int ENEMIES_FIELD_WIDTH = 180;
    private static final int ENEMIES_FIELD_HEIGHT = 40;
    private static final int ENEMIES_BUTTON_WIDTH = 250;
    private static final int ENEMIES_BUTTON_HEIGHT = 40;

    private static final int WEAPONS_FIELD_WIDTH = 180;
    private static final int WEAPONS_FIELD_HEIGHT = 40;
    private static final int WEAPONS_BUTTON_WIDTH = 180;
    private static final int WEAPONS_BUTTON_HEIGHT = 40;

    private static final int BUTTONS_X_POS = 270;
    private static final int BUTTONS_Y_POS = 650;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;
    private static final int BACK_BUTTON_X_POS = 0;
    private static final int BACK_BUTTON_Y_POS = 0;
    private static final int PLAY_BUTTON_X_POS = 230;
    private static final int PLAY_BUTTON_Y_POS = 0;

    private static final int PARTY_NODES_Y_POS = 120;
    private static final int PARTY_LABEL_Y_OFFSET = 10;
    private static final int PARTY_NAME_NODE_X_POS = 20;
    private static final int PARTY_NAME_FIELD_X_POS = 50;
    private static final int PARTY_HEALTH_NODE_X_POS = 350;
    private static final int PARTY_HEALTH_FIELD_X_POS = 60;
    private static final int PARTY_DEFENSE_NODE_X_POS = 700;
    private static final int PARTY_DEFENSE_FIELD_X_POS = 70;
    private static final int PARTY_BUTTONS_Y_POS = 180;
    private static final int BLACK_MAGE_X_POS = 10;
    private static final int ENGINEER_X_POS = 210;
    private static final int THIEF_X_POS = 410;
    private static final int KNIGHT_X_POS = 610;
    private static final int WHITE_MAGE_X_POS = 810;

    private static final int ENEMIES_NODES_Y_POS = 310;
    private static final int ENEMIES_NODES_Y_POS_2 = 370;
    private static final int ENEMIES_LABEL_Y_OFFSET = 10;
    private static final int ENEMIES_NAME_NODE_X_POS = 20;
    private static final int ENEMIES_NAME_FIELD_X_POS = 50;
    private static final int ENEMIES_HEALTH_NODE_X_POS = 350;
    private static final int ENEMIES_HEALTH_FIELD_X_POS = 60;
    private static final int ENEMIES_DEFENSE_NODE_X_POS = 700;
    private static final int ENEMIES_DEFENSE_FIELD_X_POS = 70;
    private static final int ENEMIES_DAMAGE_NODE_X_POS = 20;
    private static final int ENEMIES_DAMAGE_FIELD_X_POS = 70;
    private static final int ENEMIES_WEIGHT_NODE_X_POS = 350;
    private static final int ENEMIES_WEIGHT_FIELD_X_POS = 60;
    private static final int ENEMY_BUTTON_X_POS = 700;
    private static final int ENEMY_BUTTON_Y_POS = 370;

    private static final int WEAPONS_NODES_Y_POS = 500;
    private static final int WEAPONS_LABEL_Y_OFFSET = 10;
    private static final int WEAPONS_NAME_NODE_X_POS = 20;
    private static final int WEAPONS_NAME_FIELD_X_POS = 50;
    private static final int WEAPONS_DAMAGE_NODE_X_POS = 350;
    private static final int WEAPONS_DAMAGE_FIELD_X_POS = 70;
    private static final int WEAPONS_WEIGHT_NODE_X_POS = 700;
    private static final int WEAPONS_WEIGHT_FIELD_X_POS = 60;
    private static final int WEAPONS_BUTTONS_Y_POS = 560;
    private static final int AXE_X_POS = 10;
    private static final int BOW_X_POS = 210;
    private static final int KNIFE_X_POS = 410;
    private static final int STAFF_X_POS = 610;
    private static final int SWORD_X_POS = 810;

    /**
     * Create the scene adding the root Node to the main scene and initialize the
     * methods for the audio and background
     * @param controller
     *      Reference of the controller that manage the model
     * @param gameCreator
     *      Reference of the creator that manage the gui elements of the characters
     * @param mainScene
     *      Reference of the main scene that needs set the root Node
     */
    public GameMakerScene(GameController controller, GameCreator gameCreator, Scene mainScene) {
        super(controller, mainScene, SOUND_FILE_NAME);
        this.gameCreator = gameCreator;
        var backgroundImage = createSmoothImageView(RESOURCE_PATH + "sprites/background/board.png", 0, 0, 1000, 740);
        addBackground(backgroundImage);
    }

    /**
     * Generate the elements of the gui
     */
    public void run() {
        var tittleLabel = createLabel("Make your Game", TITTLE_X_POS, TITTLE_Y_POS, TITTLE_FONT_SIZE, FontWeight.BOLD, 1, 1, 1);
        addToRoot(tittleLabel);

        createPartyFields();
        createPartyButtons();
        createEnemyFields();
        createEnemyButtons();
        createWeaponFields();
        createWeaponsButtons();
        createFinalButtons();
    }

    /**
     * Generate the fields of the party character to be added
     */
    private void createPartyFields() {
        partyCounterLabel = createLabel("Party Characters: 0 / 5", COUNTER_X_POS, PARTY_COUNTER_Y_POS, COUNTER_FONT_SIZE, FontWeight.BOLD, 1, 1, 1);
        addToRoot(partyCounterLabel);

        var partyNameNode = createGroup(PARTY_NAME_NODE_X_POS, PARTY_NODES_Y_POS);
        var partyNameLabel = createLabel("Name", 0, PARTY_LABEL_Y_OFFSET, PROPERTY_FONT_SIZE, FontWeight.BOLD, 1, 1, 1);
        partyNameNode.getChildren().add(partyNameLabel);
        partyNameField = createTextField(PARTY_NAME_FIELD_X_POS, 0, PARTY_FIELD_WIDTH, PARTY_FIELD_HEIGHT);
        partyNameNode.getChildren().add(partyNameField);
        addToRoot(partyNameNode);

        var partyHealthNode = createGroup(PARTY_HEALTH_NODE_X_POS, PARTY_NODES_Y_POS);
        var partyHealthLabel = createLabel("Health", 0, PARTY_LABEL_Y_OFFSET, PROPERTY_FONT_SIZE, FontWeight.BOLD, 1, 1, 1);
        partyHealthNode.getChildren().add(partyHealthLabel);
        partyHealthField = createTextField(PARTY_HEALTH_FIELD_X_POS, 0, PARTY_FIELD_WIDTH, PARTY_FIELD_HEIGHT);
        partyHealthNode.getChildren().add(partyHealthField);
        addToRoot(partyHealthNode);

        var partyDefenseNode = createGroup(PARTY_DEFENSE_NODE_X_POS, PARTY_NODES_Y_POS);
        var partyDefenseLabel = createLabel("Defense", 0, PARTY_LABEL_Y_OFFSET, PROPERTY_FONT_SIZE, FontWeight.BOLD, 1, 1, 1);
        partyDefenseNode.getChildren().add(partyDefenseLabel);
        partyDefenseField = createTextField(PARTY_DEFENSE_FIELD_X_POS, 0, PARTY_FIELD_WIDTH, PARTY_FIELD_HEIGHT);
        partyDefenseNode.getChildren().add(partyDefenseField);
        addToRoot(partyDefenseNode);
    }

    /**
     * Generate the buttons to create each type of party character
     */
    private void createPartyButtons() {
        var blackMageButton = createButton("Add BlackMage", BLACK_MAGE_X_POS, PARTY_BUTTONS_Y_POS, PARTY_BUTTON_WIDTH, PARTY_BUTTON_HEIGHT);
        blackMageButton.setOnAction(event -> addBlackMage());
        addToRoot(blackMageButton);

        var engineerButton = createButton("Add Engineer", ENGINEER_X_POS, PARTY_BUTTONS_Y_POS, PARTY_BUTTON_WIDTH, PARTY_BUTTON_HEIGHT);
        engineerButton.setOnAction(event -> addEngineer());
        addToRoot(engineerButton);

        var knightButton = createButton("Add Knight", KNIGHT_X_POS, PARTY_BUTTONS_Y_POS, PARTY_BUTTON_WIDTH, PARTY_BUTTON_HEIGHT);
        knightButton.setOnAction(event -> addKnight());
        addToRoot(knightButton);

        var thiefButton = createButton("Add Thief", THIEF_X_POS, PARTY_BUTTONS_Y_POS, PARTY_BUTTON_WIDTH, PARTY_BUTTON_HEIGHT);
        thiefButton.setOnAction(event -> addThief());
        addToRoot(thiefButton);

        var whiteMageButton = createButton("Add WhiteMage", WHITE_MAGE_X_POS, PARTY_BUTTONS_Y_POS, PARTY_BUTTON_WIDTH, PARTY_BUTTON_HEIGHT);
        whiteMageButton.setOnAction(event -> addWhiteMage());
        addToRoot(whiteMageButton);
    }

    /**
     * Generate the fields of the enemies characters to be added
     */
    private void createEnemyFields() {
        enemiesCounterLabel = createLabel("Enemies Characters: 0 / 5", COUNTER_X_POS, ENEMIES_COUNTER_Y_POS, COUNTER_FONT_SIZE, FontWeight.BOLD, 1, 1, 1);
        addToRoot(enemiesCounterLabel);

        var enemiesNameNode = createGroup(ENEMIES_NAME_NODE_X_POS, ENEMIES_NODES_Y_POS);
        var enemiesNameLabel = createLabel("Name", 0, ENEMIES_LABEL_Y_OFFSET, PROPERTY_FONT_SIZE, FontWeight.BOLD, 1, 1, 1);
        enemiesNameNode.getChildren().add(enemiesNameLabel);
        enemyNameField = createTextField(ENEMIES_NAME_FIELD_X_POS, 0, ENEMIES_FIELD_WIDTH, ENEMIES_FIELD_HEIGHT);
        enemiesNameNode.getChildren().add(enemyNameField);
        addToRoot(enemiesNameNode);

        var enemiesHealthNode = createGroup(ENEMIES_HEALTH_NODE_X_POS, ENEMIES_NODES_Y_POS);
        var enemiesHealthLabel = createLabel("Health", 0, ENEMIES_LABEL_Y_OFFSET, PROPERTY_FONT_SIZE, FontWeight.BOLD, 1, 1, 1);
        enemiesHealthNode.getChildren().add(enemiesHealthLabel);
        enemyHealthField = createTextField(ENEMIES_HEALTH_FIELD_X_POS, 0, ENEMIES_FIELD_WIDTH, ENEMIES_FIELD_HEIGHT);
        enemiesHealthNode.getChildren().add(enemyHealthField);
        addToRoot(enemiesHealthNode);

        var enemiesDefenseNode = createGroup(ENEMIES_DEFENSE_NODE_X_POS, ENEMIES_NODES_Y_POS);
        var enemiesDefenseLabel = createLabel("Defense", 0, ENEMIES_LABEL_Y_OFFSET, PROPERTY_FONT_SIZE, FontWeight.BOLD, 1, 1, 1);
        enemiesDefenseNode.getChildren().add(enemiesDefenseLabel);
        enemyDefenseField = createTextField(ENEMIES_DEFENSE_FIELD_X_POS, 0, ENEMIES_FIELD_WIDTH, ENEMIES_FIELD_HEIGHT);
        enemiesDefenseNode.getChildren().add(enemyDefenseField);
        addToRoot(enemiesDefenseNode);

        var enemiesDamageNode = createGroup(ENEMIES_DAMAGE_NODE_X_POS, ENEMIES_NODES_Y_POS_2);
        var enemiesDamageLabel = createLabel("Damage", 0, ENEMIES_LABEL_Y_OFFSET, PROPERTY_FONT_SIZE, FontWeight.BOLD, 1, 1, 1);
        enemiesDamageNode.getChildren().add(enemiesDamageLabel);
        enemyDamageField = createTextField(ENEMIES_DAMAGE_FIELD_X_POS, 0, ENEMIES_FIELD_WIDTH, ENEMIES_FIELD_HEIGHT);
        enemiesDamageNode.getChildren().add(enemyDamageField);
        addToRoot(enemiesDamageNode);

        var enemiesWeightNode = createGroup(ENEMIES_WEIGHT_NODE_X_POS, ENEMIES_NODES_Y_POS_2);
        var enemiesWeightLabel = createLabel("Weight", 0, ENEMIES_LABEL_Y_OFFSET, PROPERTY_FONT_SIZE, FontWeight.BOLD, 1, 1, 1);
        enemiesWeightNode.getChildren().add(enemiesWeightLabel);
        enemyWeightField = createTextField(ENEMIES_WEIGHT_FIELD_X_POS, 0, ENEMIES_FIELD_WIDTH, ENEMIES_FIELD_HEIGHT);
        enemiesWeightNode.getChildren().add(enemyWeightField);
        addToRoot(enemiesWeightNode);
    }

    /**
     * Generate the buttons to create an Enemy character
     */
    private void createEnemyButtons() {
        var enemyButton = createButton("Add Enemy", ENEMY_BUTTON_X_POS, ENEMY_BUTTON_Y_POS, ENEMIES_BUTTON_WIDTH, ENEMIES_BUTTON_HEIGHT);
        enemyButton.setOnAction(event -> addEnemy());
        addToRoot(enemyButton);
    }

    /**
     * Generate the fields of the weapon to be added
     */
    private void createWeaponFields() {
        weaponsCounterLabel = createLabel("Weapons on Inventory: 0 / 27", COUNTER_X_POS, WEAPONS_COUNTER_Y_POS, COUNTER_FONT_SIZE, FontWeight.BOLD, 1, 1, 1);
        addToRoot(weaponsCounterLabel);

        var weaponsNameNode = createGroup(WEAPONS_NAME_NODE_X_POS, WEAPONS_NODES_Y_POS);
        var weaponsNameLabel = createLabel("Name", 0, WEAPONS_LABEL_Y_OFFSET, PROPERTY_FONT_SIZE, FontWeight.BOLD, 1, 1, 1);
        weaponsNameNode.getChildren().add(weaponsNameLabel);
        weaponNameField = createTextField(WEAPONS_NAME_FIELD_X_POS, 0, WEAPONS_FIELD_WIDTH, WEAPONS_FIELD_HEIGHT);
        weaponsNameNode.getChildren().add(weaponNameField);
        addToRoot(weaponsNameNode);

        var weaponsDamageNode = createGroup(WEAPONS_DAMAGE_NODE_X_POS, WEAPONS_NODES_Y_POS);
        var weaponsDamageLabel = createLabel("Damage", 0, WEAPONS_LABEL_Y_OFFSET, PROPERTY_FONT_SIZE, FontWeight.BOLD, 1, 1, 1);
        weaponsDamageNode.getChildren().add(weaponsDamageLabel);
        weaponDamageField = createTextField(WEAPONS_DAMAGE_FIELD_X_POS, 0, WEAPONS_FIELD_WIDTH, WEAPONS_FIELD_HEIGHT);
        weaponsDamageNode.getChildren().add(weaponDamageField);
        addToRoot(weaponsDamageNode);

        var weaponsWeightNode = createGroup(WEAPONS_WEIGHT_NODE_X_POS, WEAPONS_NODES_Y_POS);
        var weaponsWeightLabel = createLabel("Weight", 0, WEAPONS_LABEL_Y_OFFSET, PROPERTY_FONT_SIZE, FontWeight.BOLD, 1, 1, 1);
        weaponsWeightNode.getChildren().add(weaponsWeightLabel);
        weaponWeightField = createTextField(WEAPONS_WEIGHT_FIELD_X_POS, 0, WEAPONS_FIELD_WIDTH, WEAPONS_FIELD_HEIGHT);
        weaponsWeightNode.getChildren().add(weaponWeightField);
        addToRoot(weaponsWeightNode);
    }

    /**
     * Generate the buttons to create each type of weapons
     */
    private void createWeaponsButtons() {
        var axeButton = createButton("Add Axe", AXE_X_POS, WEAPONS_BUTTONS_Y_POS, WEAPONS_BUTTON_WIDTH, WEAPONS_BUTTON_HEIGHT);
        axeButton.setOnAction(event -> addAxe());
        addToRoot(axeButton);

        var bowButton = createButton("Add Bow", BOW_X_POS, WEAPONS_BUTTONS_Y_POS, WEAPONS_BUTTON_WIDTH, WEAPONS_BUTTON_HEIGHT);
        bowButton.setOnAction(event -> addBow());
        addToRoot(bowButton);

        var knifeButton = createButton("Add Knife", KNIFE_X_POS, WEAPONS_BUTTONS_Y_POS, WEAPONS_BUTTON_WIDTH, WEAPONS_BUTTON_HEIGHT);
        knifeButton.setOnAction(event -> addKnife());
        addToRoot(knifeButton);

        var staffButton = createButton("Add Staff", STAFF_X_POS, WEAPONS_BUTTONS_Y_POS, WEAPONS_BUTTON_WIDTH, WEAPONS_BUTTON_HEIGHT);
        staffButton.setOnAction(event -> addStaff());
        addToRoot(staffButton);

        var swordButton = createButton("Add Sword", SWORD_X_POS, WEAPONS_BUTTONS_Y_POS, WEAPONS_BUTTON_WIDTH, WEAPONS_BUTTON_HEIGHT);
        swordButton.setOnAction(event -> addSword());
        addToRoot(swordButton);
    }

    /**
     * Create the buttons to back to the start scene or to make and play the battle
     */
    private void createFinalButtons() {
        var startScene = new StartMenuScene(mainScene);
        Group buttonsNode = createGroup(BUTTONS_X_POS, BUTTONS_Y_POS);
        buttonsNode.setLayoutX(BUTTONS_X_POS);
        buttonsNode.setLayoutY(BUTTONS_Y_POS);

        Button backButton = createButton("Back", BACK_BUTTON_X_POS, BACK_BUTTON_Y_POS, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setOnAction(event -> {stopSound(); stopTimer(); startScene.activate();});
        buttonsNode.getChildren().add(backButton);

        Button playButton = createButton("Make and Play", PLAY_BUTTON_X_POS, PLAY_BUTTON_Y_POS, BUTTON_WIDTH, BUTTON_HEIGHT);
        playButton.setOnAction(event -> makeAndPlay());
        buttonsNode.getChildren().add(playButton);

        addToRoot(buttonsNode);
    }

    /**
     * Method that will be called by the timer
     * updating the counter for the amount of party characters, enemies and weapons
     */
    @Override
    protected void updateInTimer() {
        partyCounterLabel.setText("Party Characters: " + controller.getRemainingPartyCharacters() + " / 5");
        enemiesCounterLabel.setText("Enemies Characters: " + controller.getRemainingEnemies() + " / 5");
        weaponsCounterLabel.setText("Weapons on Inventory: " + controller.getWeaponsAmountOnInventory() + " / 27");
    }


    /**
     * Check if a field contain a number, otherwise
     * clean the given field
     * @param field
     *      Text field to be checked
     */
    private boolean checkFieldType(TextField field) {
        String text = field.getText();
        if (field.getText() == null) {
            return false;
        }
        int result;
        try {
            result = Integer.parseInt(field.getText());
            return true;
        }
        catch (NumberFormatException e)
        {
            result = 0;
            field.clear();
            return false;
        }
    }

    /**
     *  Cut the given string at a certain length,
     * @param name
     *      string to be cut
     */
    private String truncateName(String name) {
        int truncateLength = 12;
        String truncated = name.substring(0, Math.min(name.length(), truncateLength));
        return truncated;
    }

    /**
     * Action of the button that add a black mage
     * First check if the fields are complete and if the int values are numbers
     * Finally clear the fields
     */
    private void addBlackMage() {
        if (partyNameField.getText() !=null && checkFieldType(partyHealthField) && checkFieldType(partyDefenseField)) {
            String name = truncateName(partyNameField.getText());
            int health = Integer.parseInt(partyHealthField.getText());
            int defense = Integer.parseInt(partyDefenseField.getText());
            gameCreator.addBlackMage(name, health, defense);
            partyNameField.clear();
            partyDefenseField.clear();
            partyHealthField.clear();
        }
    }

    /**
     * Action of the button that add an engineer
     * First check if the fields are complete and if the int values are numbers
     * Finally clear the fields
     */
    private void addEngineer() {
        if (partyNameField.getText() !=null && checkFieldType(partyHealthField) && checkFieldType(partyDefenseField)) {
            String name = truncateName(partyNameField.getText());
            int health = Integer.parseInt(partyHealthField.getText());
            int defense = Integer.parseInt(partyDefenseField.getText());
            gameCreator.addEngineer(name, health, defense);
            partyNameField.clear();
            partyDefenseField.clear();
            partyHealthField.clear();
        }
    }

    /**
     * Action of the button that add a thief
     * First check if the fields are complete and if the int values are numbers
     * Finally clear the fields
     */
    private void addThief() {
        if (partyNameField.getText() !=null && checkFieldType(partyHealthField) && checkFieldType(partyDefenseField)) {
            String name = truncateName(partyNameField.getText());
            int health = Integer.parseInt(partyHealthField.getText());
            int defense = Integer.parseInt(partyDefenseField.getText());
            gameCreator.addThief(name, health, defense);
            partyNameField.clear();
            partyDefenseField.clear();
            partyHealthField.clear();
        }
    }

    /**
     * Action of the button that add a knight
     * First check if the fields are complete and if the int values are numbers
     * Finally clear the fields
     */
    private void addKnight() {
        if (partyNameField.getText() !=null && checkFieldType(partyHealthField) && checkFieldType(partyDefenseField)) {
            String name = truncateName(partyNameField.getText());
            int health = Integer.parseInt(partyHealthField.getText());
            int defense = Integer.parseInt(partyDefenseField.getText());
            gameCreator.addKnight(name, health, defense);
            partyNameField.clear();
            partyDefenseField.clear();
            partyHealthField.clear();
        }
    }

    /**
     * Action of the button that add a white mage
     * First check if the fields are complete and if the int values are numbers
     * Finally clear the fields
     */
    private void addWhiteMage() {
        if (partyNameField.getText() !=null && checkFieldType(partyHealthField) && checkFieldType(partyDefenseField)) {
            String name = truncateName(partyNameField.getText());
            int health = Integer.parseInt(partyHealthField.getText());
            int defense = Integer.parseInt(partyDefenseField.getText());
            gameCreator.addWhiteMage(name, health, defense);
            partyNameField.clear();
            partyDefenseField.clear();
            partyHealthField.clear();
        }
    }

    /**
     * Action of the button that add an enemy
     * First check if the fields are complete and if the int values are numbers
     * Finally clear the fields
     */
    private void addEnemy() {
        if (enemyNameField.getText() !=null
                && checkFieldType(enemyHealthField)
                && checkFieldType(enemyDefenseField)
                && checkFieldType(enemyDamageField)
                && checkFieldType(enemyWeightField)) {
            String name = truncateName(enemyNameField.getText());
            int health = Integer.parseInt(enemyHealthField.getText());
            int defense = Integer.parseInt(enemyDefenseField.getText());
            int damage = Integer.parseInt(enemyDamageField.getText());
            int weight = Integer.parseInt(enemyWeightField.getText());
            gameCreator.addEnemy(name, health, defense, damage, weight);
            enemyNameField.clear();
            enemyDefenseField.clear();
            enemyHealthField.clear();
            enemyDamageField.clear();
            enemyWeightField.clear();
        }
    }

    /**
     * Action of the button that add an Axe
     * First check if the fields are complete and if the int values are numbers
     * Finally clear the fields
     */
    private void addAxe() {
        if (weaponNameField.getText() !=null && checkFieldType(weaponDamageField) && checkFieldType(weaponWeightField)) {
            String name = truncateName(weaponNameField.getText());
            int damage = Integer.parseInt(weaponDamageField.getText());
            int weight = Integer.parseInt(weaponWeightField.getText());
            controller.addAxeToInventory(name, weight, damage);
            weaponNameField.clear();
            weaponDamageField.clear();
            weaponWeightField.clear();
        }
    }

    /**
     * Action of the button that add a Bow
     * First check if the fields are complete and if the int values are numbers
     * Finally clear the fields
     */
    private void addBow() {
        if (weaponNameField.getText() !=null && checkFieldType(weaponDamageField) && checkFieldType(weaponWeightField)) {
            String name = truncateName(weaponNameField.getText());
            int damage = Integer.parseInt(weaponDamageField.getText());
            int weight = Integer.parseInt(weaponWeightField.getText());
            controller.addBowToInventory(name, weight, damage);
            weaponNameField.clear();
            weaponDamageField.clear();
            weaponWeightField.clear();
        }
    }

    /**
     * Action of the button that add a Knife
     * First check if the fields are complete and if the int values are numbers
     * Finally clear the fields
     */
    private void addKnife() {
        if (weaponNameField.getText() !=null && checkFieldType(weaponDamageField) && checkFieldType(weaponWeightField)) {
            String name = truncateName(weaponNameField.getText());
            int damage = Integer.parseInt(weaponDamageField.getText());
            int weight = Integer.parseInt(weaponWeightField.getText());
            controller.addKnifeToInventory(name, weight, damage);
            weaponNameField.clear();
            weaponDamageField.clear();
            weaponWeightField.clear();
        }
    }

    /**
     * Action of the button that add a Staff
     * First check if the fields are complete and if the int values are numbers
     * Finally clear the fields
     */
    private void addStaff() {
        if (weaponNameField.getText() !=null && checkFieldType(weaponDamageField) && checkFieldType(weaponWeightField)) {
            String name = truncateName(weaponNameField.getText());
            int damage = Integer.parseInt(weaponDamageField.getText());
            int weight = Integer.parseInt(weaponWeightField.getText());
            controller.addStaffToInventory(name, weight, damage);
            weaponNameField.clear();
            weaponDamageField.clear();
            weaponWeightField.clear();
        }
    }

    /**
     * Action of the button that add a Sword
     * First check if the fields are complete and if the int values are numbers
     * Finally clear the fields
     */
    private void addSword() {
        if (weaponNameField.getText() !=null && checkFieldType(weaponDamageField) && checkFieldType(weaponWeightField)) {
            String name = truncateName(weaponNameField.getText());
            int damage = Integer.parseInt(weaponDamageField.getText());
            int weight = Integer.parseInt(weaponWeightField.getText());
            controller.addSwordToInventory(name, weight, damage);
            weaponNameField.clear();
            weaponDamageField.clear();
            weaponWeightField.clear();
        }
    }

    /**
     * Action of the button that make an start the battle
     * First check if at least there are 1 party character, 1 enemy and 1 weapon
     */
    private void makeAndPlay() {
        if (controller.getRemainingPartyCharacters() > 0
                && controller.getRemainingEnemies() > 0
                && controller.getWeaponsAmountOnInventory() > 0) {
            stopSound();
            stopTimer();
            GameScene gameScene = new GameScene(controller, gameCreator, mainScene);
            gameCreator.create();
            gameScene.activate();
        }
    }
}
