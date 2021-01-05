package com.github.cc3002.finalreality.gui.nodeSelection;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.gui.GuiGenerator;
import com.github.cc3002.finalreality.model.weapon.WeaponType;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This represents the inventory Node that generate
 * the gui elements of the inventory menu
 *
 * @author Sebastián Olmos.
 */
public class NodeInventory extends GuiGenerator implements INodeInventory{
    private final GameController controller;
    private Group menuNode;
    private Label selectedWeaponNameLabel;
    private Label selectedWeaponDamageLabel;
    private Label selectedWeaponWeightLabel;
    private Group selectedSpriteNode;
    private Label equippedWeaponNameLabel;
    private Label equippedWeaponDamageLabel;
    private Label equippedWeaponWeightLabel;
    private Group equippedSpriteNode;
    private Group itemsSpriteButtons;
    private Image quadImage;

    // Several constant values of the gui elements
    private static final String RESOURCE_PATH = "src/main/resources/sprites/";
    private static final int MENU_X_POS = 250;
    private static final int MENU_Y_POS = 170;
    private static final int MENU_WIDTH = 500;
    private static final int MENU_HEIGHT = 380;
    private static final String MENU_SPRITE_NAME = "interface/inventory_menu.png";

    private static final int TITTLE_X_POS = 180;
    private static final int TITTLE_Y_POS = -42;
    private static final int TITTLE_WIDTH = 130;
    private static final int TITTLE_HEIGHT = 40;
    private static final String TITTLE_SPRITE_NAME = "interface/inventory_tittle.png";

    private static final int TITTLE_LABEL_X_POS = 30;
    private static final int TITTLE_LABEL_Y_POS = 10;
    private static final int TITTLE_FONT_SIZE = 18;

    private static final int BUTTONS_X_POS = 110;
    private static final int BUTTONS_Y_POS = 320;
    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 30;
    private static final int BACK_BUTTON_X_POS = 0;
    private static final int BACK_BUTTON_Y_POS = 0;
    private static final int EQUIP_BUTTON_X_POS = 160;
    private static final int EQUIP_BUTTON_Y_POS = 0;

    private static final int ITEMS_X_POS = 40;
    private static final int ITEMS_Y_POS = 150;
    private static final int ITEMS_AMOUNT = 27;
    private static final int ITEMS_SIDE_PADDING = 0;
    private static final int ITEMS_UP_DOWN_PADDING = 10;
    private static final int ITEMS_H_GAP= 5;
    private static final int ITEMS_V_GAP= 5;
    private static final int ITEMS_WIDTH= 420;
    private static final String QUAD_SPRITE_NAME = "interface/quad.png";
    private static final int QUAD_SIZE = 40;
    private static final int ITEM_SPRITE_SIZE = 40;

    private static final int WEAPON_PANEL_WIDTH = 190;
    private static final int WEAPON_PANEL_HEIGHT = 130;
    private static final int WEAPON_PANEL_SPRITE_X_POS = 10;
    private static final int WEAPON_PANEL_SPRITE_Y_POS = 30;
    private static final int WEAPON_PANEL_SPRITE_SIZE = 70;
    private static final int WEAPON_PANEL_NAME_X_POS = 90;
    private static final int WEAPON_PANEL_NAME_Y_POS = 40;
    private static final int WEAPON_PANEL_DAMAGE_X_POS = 90;
    private static final int WEAPON_PANEL_DAMAGE_Y_POS = 60;
    private static final int WEAPON_PANEL_WEIGHT_X_POS = 90;
    private static final int WEAPON_PANEL_WEIGHT_Y_POS = 80;
    private static final int WEAPON_LABEL_FONT_SIZE = 13;

    private static final int SELECTED_WEAPON_PANEL_X_POS = 40;
    private static final int EQUIPPED_WEAPON_PANEL_X_POS = 270;
    private static final int WEAPON_PANEL_Y_POS = 10;
    private static final int WEAPON_PANEL_TITTLE_X_POS = 75;
    private static final int WEAPON_PANEL_TITTLE_Y_POS = 7;
    private static final String WEAPONS_SPRITE_PATH = RESOURCE_PATH + "weapons/";
    private static final List<String> WEAPONS_SPRITE_NAMES = new ArrayList<>(Arrays.asList("axe.png", "bow.png", "knife.png", "staff.png", "sword.png"));
    private final HashMap<WeaponType, Image> weaponImages = new HashMap<>();

    /**
     * Create the inventory menu and set the reference in the controller
     * @param controller
     *      Reference of the gameController
     */
    public NodeInventory(GameController controller) {
        this.controller = controller;
        controller.setInventoryMenuGui(this);
        createMenu();
    }

    /**
     * Generate the gui elements of the menu
     */
    private void createMenu() {
        menuNode = createGroup(MENU_X_POS, MENU_Y_POS);

        createBackAndTittle();
        createQuadPane();
        createSelectedWeaponPanel();
        createEquippedWeaponPanel();
        createButtons();

        loadWeaponsImages();
    }

    /**
     * Create the back sprite and tittle of the menu
     */
    private void createBackAndTittle() {
        ImageView menuSprite;
        menuSprite = createImageView(RESOURCE_PATH + MENU_SPRITE_NAME, 0, 0, MENU_WIDTH, MENU_HEIGHT);
        menuNode.getChildren().add(menuSprite);

        var tittleNode = createGroup(TITTLE_X_POS, TITTLE_Y_POS);
        ImageView tittleSprite;
        tittleSprite = createImageView(RESOURCE_PATH + TITTLE_SPRITE_NAME, 0, 0, TITTLE_WIDTH, TITTLE_HEIGHT);
        tittleNode.getChildren().add(tittleSprite);

        Label tittleLabel = createLabel("Inventory", TITTLE_LABEL_X_POS, TITTLE_LABEL_Y_POS, TITTLE_FONT_SIZE);
        tittleNode.getChildren().add(tittleLabel);

        menuNode.getChildren().add(tittleNode);

        quadImage = createImage(RESOURCE_PATH + QUAD_SPRITE_NAME);
    }

    /**
     * Create the grid of quad sprites
     */
    private void createQuadPane() {
        ImageView quadSprite;
        FlowPane itemsPane = createFlowPane(ITEMS_X_POS, ITEMS_Y_POS, Pos.TOP_LEFT, ITEMS_SIDE_PADDING, ITEMS_UP_DOWN_PADDING, ITEMS_H_GAP, ITEMS_WIDTH);
        for (int i = 0; i < ITEMS_AMOUNT; i++) {
            var b = new Group();
            quadSprite = createImageView(quadImage, 0, 0, QUAD_SIZE, QUAD_SIZE);
            b.getChildren().add(quadSprite);
            itemsPane.getChildren().add(b);
        }
        menuNode.getChildren().add(itemsPane);
        itemsSpriteButtons = createGroup(ITEMS_X_POS, ITEMS_Y_POS);
        menuNode.getChildren().add(itemsSpriteButtons);
    }

    /**
     * Create the elements of the selected weapon panel
     */
    private void createSelectedWeaponPanel() {
        Group selectedWeaponPanel = createGroup(SELECTED_WEAPON_PANEL_X_POS, WEAPON_PANEL_Y_POS);
        var selectedPanelSprite = createImageView(quadImage, 0, 0, WEAPON_PANEL_WIDTH, WEAPON_PANEL_HEIGHT);
        selectedWeaponPanel.getChildren().add(selectedPanelSprite);
        var selectedSprite = createImageView(quadImage, WEAPON_PANEL_SPRITE_X_POS, WEAPON_PANEL_SPRITE_Y_POS, WEAPON_PANEL_SPRITE_SIZE, WEAPON_PANEL_SPRITE_SIZE);
        selectedWeaponPanel.getChildren().add(selectedSprite);
        selectedSpriteNode = createGroup(WEAPON_PANEL_SPRITE_X_POS, WEAPON_PANEL_SPRITE_Y_POS);
        selectedWeaponPanel.getChildren().add(selectedSpriteNode);
        var selectedTittleLabel = createLabel("Selected", WEAPON_PANEL_TITTLE_X_POS, WEAPON_PANEL_TITTLE_Y_POS, WEAPON_LABEL_FONT_SIZE);
        selectedWeaponPanel.getChildren().add(selectedTittleLabel);
        selectedWeaponNameLabel = createLabel(controller.getSelectedWeaponName(), WEAPON_PANEL_NAME_X_POS, WEAPON_PANEL_NAME_Y_POS, WEAPON_LABEL_FONT_SIZE);
        selectedWeaponPanel.getChildren().add(selectedWeaponNameLabel);
        selectedWeaponDamageLabel = createLabel("Damage: " + controller.getSelectedWeaponDamage(), WEAPON_PANEL_DAMAGE_X_POS, WEAPON_PANEL_DAMAGE_Y_POS, WEAPON_LABEL_FONT_SIZE);
        selectedWeaponPanel.getChildren().add(selectedWeaponDamageLabel);
        selectedWeaponWeightLabel = createLabel("Weight: " + controller.getSelectedWeaponWeight(), WEAPON_PANEL_WEIGHT_X_POS, WEAPON_PANEL_WEIGHT_Y_POS, WEAPON_LABEL_FONT_SIZE);
        selectedWeaponPanel.getChildren().add(selectedWeaponWeightLabel);
        menuNode.getChildren().add(selectedWeaponPanel);
    }

    /**
     * Create the elements of the equipped weapon panel
     */
    private void createEquippedWeaponPanel() {
        Group equippedWeaponPanel = createGroup(EQUIPPED_WEAPON_PANEL_X_POS, WEAPON_PANEL_Y_POS);
        var equippedPanelSprite = createImageView(quadImage, 0, 0, WEAPON_PANEL_WIDTH, WEAPON_PANEL_HEIGHT);
        equippedWeaponPanel.getChildren().add(equippedPanelSprite);
        var equippedSprite = createImageView(quadImage, WEAPON_PANEL_SPRITE_X_POS, WEAPON_PANEL_SPRITE_Y_POS, WEAPON_PANEL_SPRITE_SIZE, WEAPON_PANEL_SPRITE_SIZE);
        equippedWeaponPanel.getChildren().add(equippedSprite);
        equippedSpriteNode = createGroup(WEAPON_PANEL_SPRITE_X_POS, WEAPON_PANEL_SPRITE_Y_POS);
        equippedWeaponPanel.getChildren().add(equippedSpriteNode);
        var equippedTittleLabel = createLabel("Equipped", WEAPON_PANEL_TITTLE_X_POS, WEAPON_PANEL_TITTLE_Y_POS, WEAPON_LABEL_FONT_SIZE);
        equippedWeaponPanel.getChildren().add(equippedTittleLabel);
        equippedWeaponNameLabel = createLabel(controller.getEquippedWeaponName(), WEAPON_PANEL_NAME_X_POS, WEAPON_PANEL_NAME_Y_POS, WEAPON_LABEL_FONT_SIZE);
        equippedWeaponPanel.getChildren().add(equippedWeaponNameLabel);
        equippedWeaponDamageLabel = createLabel("Damage: " + controller.getEquippedWeaponDamage(), WEAPON_PANEL_DAMAGE_X_POS, WEAPON_PANEL_DAMAGE_Y_POS, WEAPON_LABEL_FONT_SIZE);
        equippedWeaponPanel.getChildren().add(equippedWeaponDamageLabel);
        equippedWeaponWeightLabel = createLabel("Weight: " + controller.getEquippedWeaponWeight(), WEAPON_PANEL_WEIGHT_X_POS, WEAPON_PANEL_WEIGHT_Y_POS, WEAPON_LABEL_FONT_SIZE);
        equippedWeaponPanel.getChildren().add(equippedWeaponWeightLabel);
        menuNode.getChildren().add(equippedWeaponPanel);
    }

    /**
     * Create the buttons of the menu,
     * they let the user to go back to the action selection
     * and equip the selected weapon
     */
    private void createButtons() {
        Group buttonsNode = createGroup(BUTTONS_X_POS, BUTTONS_Y_POS);

        Button backButton = createButton("Back", BACK_BUTTON_X_POS, BACK_BUTTON_Y_POS, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setOnAction(event -> controller.toActionSelection());
        buttonsNode.getChildren().add(backButton);

        Button equipButton = createButton("Equip", EQUIP_BUTTON_X_POS, EQUIP_BUTTON_Y_POS, BUTTON_WIDTH, BUTTON_HEIGHT);
        equipButton.setOnAction(event -> {controller.equipSelectedWeapon(); updatePanels(); updateInventoryView();});
        buttonsNode.getChildren().add(equipButton);

        menuNode.getChildren().add(buttonsNode);
    }

    /**
     * Create the images sprites of each type of weapon
     */
    private void loadWeaponsImages() {
        var axeImage= createImage(WEAPONS_SPRITE_PATH + WEAPONS_SPRITE_NAMES.get(0));
        weaponImages.put(WeaponType.AXE, axeImage);

        var bowImage = createImage(WEAPONS_SPRITE_PATH + WEAPONS_SPRITE_NAMES.get(1));
        weaponImages.put(WeaponType.BOW, bowImage);

        var knifeImage = createImage(WEAPONS_SPRITE_PATH + WEAPONS_SPRITE_NAMES.get(2));
        weaponImages.put(WeaponType.KNIFE, knifeImage);

        var staffImage = createImage(WEAPONS_SPRITE_PATH + WEAPONS_SPRITE_NAMES.get(3));
        weaponImages.put(WeaponType.STAFF, staffImage);

        var swordImage = createImage(WEAPONS_SPRITE_PATH + WEAPONS_SPRITE_NAMES.get(4));
        weaponImages.put(WeaponType.SWORD, swordImage);

        weaponImages.put(WeaponType.NULL, null);
    }

    /**
     * Update the elements of the panels (equipped and selected weapon)
     */
    private void updatePanels() {
        updateSelectedSprite();
        selectedWeaponNameLabel.setText(controller.getSelectedWeaponName());
        selectedWeaponDamageLabel.setText("Damage: " + controller.getSelectedWeaponDamage());
        selectedWeaponWeightLabel.setText("Weight: " + controller.getSelectedWeaponWeight());

        updateEquippedSprite();
        equippedWeaponNameLabel.setText(controller.getEquippedWeaponName());
        equippedWeaponDamageLabel.setText("Damage: " + controller.getEquippedWeaponDamage());
        equippedWeaponWeightLabel.setText("Weight: " + controller.getEquippedWeaponWeight());
    }

    /**
     * Update the sprite according the type of the selected weapon
     */
    private void updateSelectedSprite() {
        var spriteImage = weaponImages.get(controller.getSelectedWeaponType());
        if (spriteImage != null) {
            var spriteView = createImageView(spriteImage, 0, 0, WEAPON_PANEL_SPRITE_SIZE, WEAPON_PANEL_SPRITE_SIZE);
            selectedSpriteNode.getChildren().clear();
            setPos(selectedSpriteNode, WEAPON_PANEL_SPRITE_X_POS, WEAPON_PANEL_SPRITE_Y_POS);
            selectedSpriteNode.getChildren().add(spriteView);
        }
        else {
            selectedSpriteNode.getChildren().clear();;
        }
    }

    /**
     * Update the sprite according the type of the equipped weapon
     */
    private void updateEquippedSprite() {
        var spriteImage = weaponImages.get(controller.getEquippedWeaponType());
        if (spriteImage != null) {
            var spriteView = createImageView(spriteImage, 0, 0, WEAPON_PANEL_SPRITE_SIZE, WEAPON_PANEL_SPRITE_SIZE);
            equippedSpriteNode.getChildren().clear();
            setPos(equippedSpriteNode, WEAPON_PANEL_SPRITE_X_POS, WEAPON_PANEL_SPRITE_Y_POS);
            equippedSpriteNode.getChildren().add(spriteView);
        }
        else {
            equippedSpriteNode.getChildren().clear();
        }
    }

    /**
     * Update the sprites of the weapons grid
     * according the currents weapons of the inventory
     */
    private void updateItemsSprites() {
        FlowPane itemButtonPane = createFlowPane(ITEMS_X_POS, ITEMS_Y_POS, Pos.TOP_LEFT, ITEMS_SIDE_PADDING, ITEMS_UP_DOWN_PADDING, ITEMS_H_GAP, ITEMS_WIDTH);
        for (int i = 0; i < controller.getWeaponsAmountOnInventory(); i++) {
            var itemButton = createButton("", 0, 0, ITEM_SPRITE_SIZE, ITEM_SPRITE_SIZE);
            var itemView = createImageView(weaponImages.get(controller.getWeaponTypeFromInventory(i)), 0, 0, ITEM_SPRITE_SIZE, ITEM_SPRITE_SIZE);
            itemButton.setGraphic(itemView);
            int finalI = i;
            itemButton.setOnAction(event -> {controller.selectWeapon(finalI); updatePanels();});
            itemButtonPane.getChildren().add(itemButton);
        }
        itemsSpriteButtons.getChildren().clear();
        itemsSpriteButtons.setLayoutX(0);
        itemsSpriteButtons.setLayoutY(0);
        itemsSpriteButtons.getChildren().add(itemButtonPane);
    }

    /**
     * Update all the elements of the menu
     */
    public void updateInventoryView() {
        updatePanels();
        updateItemsSprites();
    }

    /**
     * Return the Group node with the menu elements
     */
    public Group getMenuNode(){
        return menuNode;
    }

    /**
     * Set the visible property according if the menu has to be displayed
     */
    public void update() {
        if (controller.isSelectingWeapon()) {
            menuNode.setVisible(true);
        }
        else {
            menuNode.setVisible(false);
        }
    }

}
