package com.github.cc3002.finalreality.gui.nodeSelection;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.gui.GuiGenerator;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import java.util.List;

/**
 * This represents the target enemy Node that generate
 * the gui elements of the target Enemy menu
 *
 * @author Sebastián Olmos.
 */
public class NodeTargetEnemy extends GuiGenerator implements INodeTargetEnemy{
    private final GameController controller;
    private Group targetMenuNode;
    private Image frontEnemySprite;
    private Image quadImage;
    private Group enemiesSelection;
    private Group enemiesSelectionButtons;
    private ImageView targetEnemyImage;
    private Label targetEnemyNameLabel;
    private Label targetEnemyHealthLabel;
    private Label targetEnemyDefenseLabel;
    private Label targetEnemyDamageLabel;
    private Label targetEnemyWeightLabel;

    // Several constant values of the gui elements
    private static final int MENU_X_POS = 250;
    private static final int MENU_Y_POS = 170;
    private static final int MENU_WIDTH = 500;
    private static final int MENU_HEIGHT = 380;
    private static final int TITTLE_X_POS = 120;
    private static final int TITTLE_Y_POS = -42;
    private static final int TITTLE_WIDTH = 260;
    private static final int TITTLE_HEIGHT = 40;
    private static final int TITTLE_LABEL_X_POS = 30;
    private static final int TITTLE_LABEL_Y_POS = 10;
    private static final int TITTLE_LABEL_FONT_SIZE = 18;
    private static final int TARGET_PANEL_X_POS = 130;
    private static final int TARGET_PANEL_Y_POS = 15;
    private static final int TARGET_PANEL_WIDTH = 220;
    private static final int TARGET_PANEL_HEIGHT = 120;
    private static final int TARGET_SPRITE_PANEL_X_POS = 10;
    private static final int TARGET_SPRITE_PANEL_Y_POS = 25;
    private static final int TARGET_SPRITE_PANEL_SIZE = 80;
    private static final int TARGET_SPRITE_QUAD_SIZE = 82;
    private static final int TITTLE_TARGET_LABEL_X_POS = 80;
    private static final int TITTLE_TARGET_LABEL_Y_POS = 5;
    private static final int TITTLE_TARGET_LABEL_FONT_SIZE = 13;
    private static final int TARGET_INFO_LABEL_X_POS = 110;
    private static final int TARGET_NAME_LABEL_Y_POS = 25;
    private static final int TARGET_HEALTH_LABEL_Y_POS = 42;
    private static final int TARGET_DEFENSE_LABEL_Y_POS = 59;
    private static final int TARGET_DAMAGE_LABEL_Y_POS = 76;
    private static final int TARGET_WEIGHT_LABEL_Y_POS = 93;
    private static final int TARGET_INFO_LABEL_FONT_SIZE = 11;

    private static final int ENEMIES_X_POS = 30;
    private static final int ENEMIES_Y_POS = 140;
    private static final int ENEMIES_AMOUNT = 5;
    private static final int ENEMIES_SIDE_PADDING = 0;
    private static final int ENEMIES_UP_DOWN_PADDING = 10;
    private static final int ENEMIES_H_GAP= 10;
    private static final int ENEMIES_V_GAP= 10;
    private static final int ENEMIES_WIDTH= 450;

    private static final int SELECT_BUTTON_WIDTH = 140;
    private static final int SELECT_BUTTON_HEIGHT = 75;
    private static final int SELECT_SPRITE_PANEL_X_POS = 5;
    private static final int SELECT_SPRITE_PANEL_Y_POS = 20;
    private static final int SELECT_SPRITE_PANEL_SIZE = 50;
    private static final int SELECT_SPRITE_ENEMY_SIZE = 45;
    private static final int SELECT_LABEL_FONT_SIZE = 12;
    private static final int SELECT_INFO_LABEL_X_POS = 67;
    private static final int SELECT_NAME_LABEL_Y_POS = 25;
    private static final int SELECT_HEALTH_LABEL_Y_POS = 45;

    private static final int BUTTONS_X_POS = 110;
    private static final int BUTTONS_Y_POS = 330;
    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 30;
    private static final int BACK_BUTTON_X_POS = 0;
    private static final int BACK_BUTTON_Y_POS = 0;
    private static final int ATTACK_BUTTON_X_POS = 160;
    private static final int ATTACK_BUTTON_Y_POS = 0;

    private static final String RESOURCE_PATH = "src/main/resources/sprites/";
    private static final String MENU_SPRITE_NAME = "interface/inventory_menu.png";
    private static final String TITTLE_SPRITE_NAME = "interface/inventory_tittle.png";
    private static final String QUAD_SPRITE_NAME = "interface/quad.png";
    private static final String ENEMY_SPRITE_NAME = "characters/front_enemy.png";

    /**
     * Create the target enemy menu and set the reference in the controller
     * @param controller
     *      Reference of the gameController
     */
    public NodeTargetEnemy(GameController controller) {
        this.controller = controller;
        createMenuNode();
        controller.setTargetMenuGui(this);
    }

    /**
     * Generate the gui elements of the menu
     */
    private void createMenuNode() {
        targetMenuNode = createGroup(MENU_X_POS, MENU_Y_POS);
        createMenuAndTittle();
        createTargetPanel();
        createButtons();
    }

    /**
     * Create the back sprite and tittle of the menu
     */
    private void createMenuAndTittle() {
        frontEnemySprite = createPixelatedImage(RESOURCE_PATH + ENEMY_SPRITE_NAME, 32, 32);
        ImageView menuSprite;
        menuSprite = createImageView(RESOURCE_PATH + MENU_SPRITE_NAME, 0, 0, MENU_WIDTH, MENU_HEIGHT);
        targetMenuNode.getChildren().add(menuSprite);

        var tittleNode = createGroup(TITTLE_X_POS, TITTLE_Y_POS);
        ImageView tittleSprite;
        tittleSprite = createImageView(RESOURCE_PATH + TITTLE_SPRITE_NAME, 0, 0, TITTLE_WIDTH, TITTLE_HEIGHT);
        tittleNode.getChildren().add(tittleSprite);

        Label tittleLabel = createLabel("Select an enemy to attack", TITTLE_LABEL_X_POS, TITTLE_LABEL_Y_POS, TITTLE_LABEL_FONT_SIZE);
        tittleNode.getChildren().add(tittleLabel);

        targetMenuNode.getChildren().add(tittleNode);

        quadImage = createImage(RESOURCE_PATH + QUAD_SPRITE_NAME);
    }

    /**
     * Create the panel with the information of the enemy targeted
     */
    private void createTargetPanel() {
        Group targetPanel = createGroup(TARGET_PANEL_X_POS, TARGET_PANEL_Y_POS);
        var targetPanelSprite = createImageView(quadImage, 0, 0, TARGET_PANEL_WIDTH, TARGET_PANEL_HEIGHT);
        targetPanel.getChildren().add(targetPanelSprite);
        var targetPanelEnemy = createGroup(TARGET_SPRITE_PANEL_X_POS, TARGET_SPRITE_PANEL_Y_POS);
        var targetQuad = createImageView(quadImage, 0, 0, TARGET_SPRITE_QUAD_SIZE, TARGET_SPRITE_QUAD_SIZE);
        targetEnemyImage = createImageView(frontEnemySprite, 0, 0, TARGET_SPRITE_PANEL_SIZE, TARGET_SPRITE_PANEL_SIZE);
        targetPanelEnemy.getChildren().add(targetQuad);
        targetPanelEnemy.getChildren().add(targetEnemyImage);
        targetPanel.getChildren().add(targetPanelEnemy);
        var targetTittleLabel = createLabel("Targeted enemy", TITTLE_TARGET_LABEL_X_POS, TITTLE_TARGET_LABEL_Y_POS, TITTLE_TARGET_LABEL_FONT_SIZE);
        targetPanel.getChildren().add(targetTittleLabel);
        targetEnemyNameLabel = createLabel("NAMEEEE", TARGET_INFO_LABEL_X_POS, TARGET_NAME_LABEL_Y_POS, TARGET_INFO_LABEL_FONT_SIZE);
        targetPanel.getChildren().add(targetEnemyNameLabel);
        targetEnemyHealthLabel = createLabel("HP : 1000", TARGET_INFO_LABEL_X_POS, TARGET_HEALTH_LABEL_Y_POS, TARGET_INFO_LABEL_FONT_SIZE);
        targetPanel.getChildren().add(targetEnemyHealthLabel);
        targetEnemyDefenseLabel = createLabel("DF : 1000", TARGET_INFO_LABEL_X_POS, TARGET_DEFENSE_LABEL_Y_POS, TARGET_INFO_LABEL_FONT_SIZE);
        targetPanel.getChildren().add(targetEnemyDefenseLabel);
        targetEnemyDamageLabel = createLabel("Damage : 100", TARGET_INFO_LABEL_X_POS, TARGET_DAMAGE_LABEL_Y_POS, TARGET_INFO_LABEL_FONT_SIZE);
        targetPanel.getChildren().add(targetEnemyDamageLabel);
        targetEnemyWeightLabel = createLabel("Weight : 35", TARGET_INFO_LABEL_X_POS, TARGET_WEIGHT_LABEL_Y_POS, TARGET_INFO_LABEL_FONT_SIZE);
        targetPanel.getChildren().add(targetEnemyWeightLabel);

        targetMenuNode.getChildren().add(targetPanel);

        enemiesSelection = createGroup(ENEMIES_X_POS, ENEMIES_Y_POS);
        enemiesSelectionButtons = new Group();
        enemiesSelection.getChildren().add(enemiesSelectionButtons);
        targetMenuNode.getChildren().add(enemiesSelection);
    }

    /**
     * Create the buttons of the menu,
     * they let the user to go back to the action selection
     * and attack the enemy targeted
     */
    private void createButtons() {
        Group buttonsNode = createGroup(BUTTONS_X_POS, BUTTONS_Y_POS);

        Button backButton = createButton("Back", BACK_BUTTON_X_POS, BACK_BUTTON_Y_POS, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setOnAction(event -> controller.toActionSelection());
        buttonsNode.getChildren().add(backButton);

        Button equipButton = createButton("Attack", ATTACK_BUTTON_X_POS, ATTACK_BUTTON_Y_POS, BUTTON_WIDTH, BUTTON_HEIGHT);
        equipButton.setOnAction(event -> {controller.attackTarget();});
        buttonsNode.getChildren().add(equipButton);

        targetMenuNode.getChildren().add(buttonsNode);
    }

    /**
     * Return the Group node with the menu elements
     */
    public Group getNode() {
        return targetMenuNode;
    }

    /**
     * Set the visible property according if the menu has to be displayed
     */
    public void update() {
        targetMenuNode.setVisible(controller.isSelectingAttackTarget() && controller.isTurnBeingPlayedByParty());
    }

    /**
     * Update the button that let target an enemy according the remaining enemies
     */
    public void updateEnemySelection() {
        var remainingIndices = getRemainingEnemiesIndices();
        FlowPane enemiesButtonPane = createFlowPane(0, 0, Pos.CENTER, ENEMIES_SIDE_PADDING, ENEMIES_UP_DOWN_PADDING, ENEMIES_H_GAP, ENEMIES_WIDTH);
        for (int i = 0; i < remainingIndices.size(); i++) {
            var buttonContent = new Group();
            var quadSprite = createImageView(quadImage, SELECT_SPRITE_PANEL_X_POS, SELECT_SPRITE_PANEL_Y_POS, SELECT_SPRITE_PANEL_SIZE, SELECT_SPRITE_PANEL_SIZE);
            buttonContent.getChildren().add(quadSprite);
            var enemySprite = createImageView(frontEnemySprite, SELECT_SPRITE_PANEL_X_POS, SELECT_SPRITE_PANEL_Y_POS, SELECT_SPRITE_ENEMY_SIZE, SELECT_SPRITE_ENEMY_SIZE);
            buttonContent.getChildren().add(enemySprite);
            var nameLabel = createLabel(controller.getEnemyCharacterName(remainingIndices.get(i)), SELECT_INFO_LABEL_X_POS, SELECT_NAME_LABEL_Y_POS, SELECT_LABEL_FONT_SIZE,
                    FontWeight.NORMAL, 0, 0, 0);
            buttonContent.getChildren().add(nameLabel);
            var healthLabel = createLabel("HP : " + controller.getEnemyCharacterHealth(remainingIndices.get(i)), SELECT_INFO_LABEL_X_POS, SELECT_HEALTH_LABEL_Y_POS,
                    SELECT_LABEL_FONT_SIZE, FontWeight.NORMAL, 0, 0, 0);
            buttonContent.getChildren().add(healthLabel);

            var itemButton = new Button("", buttonContent);
            itemButton.setLayoutX(0);
            itemButton.setLayoutY(0);
            itemButton.setMinSize(SELECT_BUTTON_WIDTH, SELECT_BUTTON_HEIGHT);
            itemButton.setPrefSize(SELECT_BUTTON_WIDTH, SELECT_BUTTON_HEIGHT);
            itemButton.setMaxSize(SELECT_BUTTON_WIDTH, SELECT_BUTTON_HEIGHT);
            int finalI = remainingIndices.get(i);
            itemButton.setOnAction(event -> {controller.targetEnemyCharacter(finalI); updateInfoPanel(finalI);});
            enemiesButtonPane.getChildren().add(itemButton);
        }
        enemiesSelectionButtons.getChildren().clear();
        enemiesSelectionButtons.setLayoutX(0);
        enemiesSelectionButtons.setLayoutY(0);
        enemiesSelectionButtons.getChildren().add(enemiesButtonPane);
    }

    /**
     * Update the information of the enemy targeted according the given index
     * @param targetedIndex
     *      index of the enemy that is targeted
     */
    public void updateInfoPanel(int targetedIndex) {
        if (controller.isACharacterTargeted()) {
            targetEnemyImage.setVisible(true);
            targetEnemyNameLabel.setText(controller.getEnemyCharacterName(targetedIndex));
            targetEnemyHealthLabel.setText("HP : " + controller.getEnemyCharacterHealth(targetedIndex));
            targetEnemyDefenseLabel.setText("DF : " + controller.getEnemyCharacterDefense(targetedIndex));
            targetEnemyDamageLabel.setText("Damage : " + controller.getEnemyCharacterDamage(targetedIndex));
            targetEnemyWeightLabel.setText("Weight : " + controller.getEnemyCharacterWeight(targetedIndex));
        }
        else {
            targetEnemyImage.setVisible(false);
            targetEnemyNameLabel.setText("");
            targetEnemyHealthLabel.setText("");
            targetEnemyDefenseLabel.setText("");
            targetEnemyDamageLabel.setText("");
            targetEnemyWeightLabel.setText("");
        }
    }

    /**
     * Return a list with the indices of alive enemies
     */
    private List<Integer> getRemainingEnemiesIndices() {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < controller.getEnemiesSize(); i++) {
            int tempHealth = controller.getEnemyCharacterHealth(i);
            if (tempHealth > 0) {
                indices.add(i);
            }
        }
        return indices;
    }

}
