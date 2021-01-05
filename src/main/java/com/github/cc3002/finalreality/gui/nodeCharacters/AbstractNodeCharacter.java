package com.github.cc3002.finalreality.gui.nodeCharacters;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.gui.GuiGenerator;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * This represents a playable character node that
 * generate the gui elements that display its information and the
 * actions that can take the user
 *
 * @author Sebastián Olmos.
 */
public abstract class AbstractNodeCharacter extends GuiGenerator implements INodeCharacter{
    private final GameController controller;
    private Group menuNode;
    private Group dataNode;
    private Group buttonsNode;
    private Group figureNode;
    private static final String RESOURCE_PATH = "src/main/resources/sprites/characters/";
    protected String spriteFigureName;
    protected String spritePortraitName;
    private final int partyIndex;
    private final int initHealth;
    private Label nameLabel;
    private Label healthLabel;
    private Label defenseLabel;

    // Several constant values of the gui elements
    private static final int FIGURE_WIDTH = 56;
    private static final int FIGURE_HEIGHT = 75;
    private static final int FIGURE_CENTRAL_X_POS = 0;
    private static final int FIGURE_BACK_X_POS = 30;
    private static final int FIGURE_FORWARD_X_POS = 400;
    private static final int FIGURE_Y_POS = 0;

    private static final int PORTRAIT_WIDTH = 56;
    private static final int PORTRAIT_HEIGHT = 56;
    private static final int PORTRAIT_X_POS = 5;
    private static final int PORTRAIT_Y_POS = 0;

    private static final int TEXT_SIZE = 13;
    private static final int NAME_LABEL_X_POS = 70;
    private static final int NAME_LABEL_Y_POS = 0;
    private static final int HP_LABEL_X_POS = 70;
    private static final int HP_LABEL_Y_POS = 20;
    private static final int DF_LABEL_X_POS = 70;
    private static final int DF_LABEL_Y_POS = 40;

    private static final int DATA_NODE_X_POS = 10;
    private static final int DATA_NODE_Y_POS = 10;

    private static final int BUTTON_WIDTH = 128;
    private static final int BUTTON_HEIGHT = 32;

    private static final int ATTACK_X_POS = 20;
    private static final int ATTACK_Y_POS = 30;

    private static final int EQUIP_X_POS = 20;
    private static final int EQUIP_Y_POS = 70;

    private static final int BUTTONS_NODE_X_POS = 10;
    private static final int BUTTONS_NODE_Y_POS = 45;

    private static final int MENU_WIDTH = 180;
    private static final int MENU_HEIGHT = 400;
    private static final int MENU_POS_X = 0;
    private static final int MENU_Y_POS_SELECTED = -85;
    private static final int MENU_Y_POS_NON_SELECTED = 0;
    private static final String MENU_SPRITE_PATH = "src/main/resources/sprites/interface/menu1.png";

    /**
     * Create the character node and initialize the character initHealth and its index
     * @param controller
     *      Reference of the gameController
     * @param partyIndex
     *      index of the character in the controller party list
     * @param initHealth
     *      initial amount of life
     */
    public AbstractNodeCharacter(GameController controller, int partyIndex, int initHealth) {
        this.controller = controller;
        this.partyIndex = partyIndex;
        this.initHealth = initHealth;
    }

    /**
     * Method that change the path of the figure sprite file
     */
    public abstract void setSideImagePath();

    /**
     * Method that change the path of the Portrait sprite file
     */
    public abstract void setPortraitImagePath();

    /**
     * Create the node with the gui elements
     */
    protected void createNodes() {
        createFigureNode();
        createMenuNode();
    }

    /**
     * Create the elements of the portrait menu that consist in
     * an image depending of the character type, its name, defense and current health
     * And the action buttons if is playing the turn
     */
    protected void createMenuNode() {
        menuNode = createGroup(MENU_POS_X, MENU_Y_POS_NON_SELECTED);
        ImageView sprite;
        sprite = createSmoothImageView(MENU_SPRITE_PATH, 0, 0, MENU_WIDTH, MENU_HEIGHT);
        menuNode.getChildren().add(sprite);

        dataNode = createGroup(DATA_NODE_X_POS, DATA_NODE_Y_POS);

        ImageView portrait;
        portrait = createPixelatedImageView(RESOURCE_PATH + spritePortraitName, PORTRAIT_X_POS, PORTRAIT_Y_POS, PORTRAIT_WIDTH, PORTRAIT_HEIGHT);
        dataNode.getChildren().add(portrait);

        nameLabel = createLabel(controller.getPartyCharacterName(partyIndex), NAME_LABEL_X_POS, NAME_LABEL_Y_POS, TEXT_SIZE);
        dataNode.getChildren().add(nameLabel);

        healthLabel = createLabel("HP: " + controller.getPartyCharacterHealth(partyIndex) + "/" + initHealth, HP_LABEL_X_POS, HP_LABEL_Y_POS, TEXT_SIZE);
        dataNode.getChildren().add(healthLabel);

        defenseLabel = createLabel("DF: " + controller.getPartyCharacterDefense(partyIndex), DF_LABEL_X_POS, DF_LABEL_Y_POS, TEXT_SIZE);
        dataNode.getChildren().add(defenseLabel);

        menuNode.getChildren().add(dataNode);

        buttonsNode = createGroup(BUTTONS_NODE_X_POS, BUTTONS_NODE_Y_POS);

        Button attackButton = createButton("Attack", ATTACK_X_POS, ATTACK_Y_POS, BUTTON_WIDTH, BUTTON_HEIGHT);
        attackButton.setOnAction(event -> controller.toAttackTargetSelection());
        buttonsNode.getChildren().add(attackButton);

        Button equipButton = createButton("Inventory", EQUIP_X_POS, EQUIP_Y_POS, BUTTON_WIDTH, BUTTON_HEIGHT);
        equipButton.setOnAction(event -> controller.toWeaponSelection());
        buttonsNode.getChildren().add(equipButton);

        menuNode.getChildren().add(buttonsNode);
    }

    /**
     * Create the elements of the figure Node that consist in
     * an image depending of the character type and represents the character in the battle field
     */
    protected void createFigureNode() {
        figureNode = createGroup(FIGURE_CENTRAL_X_POS, FIGURE_Y_POS);
        ImageView sideView;
        sideView = createPixelatedImageView(RESOURCE_PATH + spriteFigureName, 0, 10, FIGURE_WIDTH, FIGURE_HEIGHT);
        figureNode.getChildren().add(sideView);
    }

    @Override
    public void update() {
        healthLabel.setText("HP: " + controller.getPartyCharacterHealth(partyIndex) + "/" + initHealth);
        if (controller.getPartyCharacterHealth(partyIndex) == 0) {
            menuToUnSelected();
            figureToBack();
        } else {
            if (controller.isTurnBeingPlayedByParty()) {
                if (controller.getSelectedPartyCharacterIndex() == partyIndex) {
                    menuToSelected();
                    figureToSelected();
                }
                else {
                    menuToUnSelected();
                    figureToUnSelected();
                }
            } else {
                menuToUnSelected();
                figureToUnSelected();
            }
        }
    }

    @Override
    public Group getMenuNode() {
        return menuNode;
    }

    @Override
    public Group getFigureNode() {
        return figureNode;
    }


    /**
     * Set the position of the portrait menu to his selected position
     */
    private void menuToSelected() {
        menuNode.setLayoutY(MENU_Y_POS_SELECTED);
        buttonsNode.setVisible(true);
    }

    /**
     * Set the position of the portrait menu to his normal (unselected) position
     */
    private void menuToUnSelected() {
        menuNode.setLayoutY(MENU_Y_POS_NON_SELECTED);
        buttonsNode.setVisible(false);
    }

    /**
     * Set the position of the figure sprite to the selected position
     */
    private void figureToSelected() {
        figureNode.setLayoutX(FIGURE_FORWARD_X_POS - figureNode.getParent().getLayoutX());
    }

    /**
     * Set the position of the figure sprite to the normal (unselected) position
     */
    private void figureToUnSelected() {
        figureNode.setLayoutX(FIGURE_CENTRAL_X_POS);
    }

    /**
     * Set the position of the figure sprite to the dead position
     */
    private void figureToBack() {
        figureNode.setLayoutX(FIGURE_BACK_X_POS  - figureNode.getParent().getLayoutX());
    }
}
