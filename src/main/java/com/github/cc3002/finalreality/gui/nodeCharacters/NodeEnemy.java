package com.github.cc3002.finalreality.gui.nodeCharacters;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.gui.GuiGenerator;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

/**
 * This represents an enemy character node that
 * generate the gui elements that display its sprite in the battlefield
 *
 * @author Sebastián Olmos.
 */
public class NodeEnemy extends GuiGenerator {
    private final GameController controller;
    private Group figureNode;
    private static final String RESOURCE_PATH = "src/main/resources/sprites/characters/";
    protected String spriteFigureName = "enemySide.png";
    private final int index;

    // Several constant values of the gui elements
    private static final int FIGURE_WIDTH = 52;
    private static final int FIGURE_HEIGHT = 52;
    private static final int FIGURE_CENTRAL_X_POS = 0;
    private static final int FIGURE_BACK_X_POS =  900;
    private static final int FIGURE_FORWARD_X_POS =  550;
    private static final int FIGURE_Y_POS = 0;

    /**
     * Create the enemy character node and initialize its index
     * @param controller
     *      Reference of the gameController
     * @param index
     *      index of the character in the controller enemies list
     */
    public NodeEnemy(GameController controller, int index) {
        this.controller = controller;
        this.index = index;
        createFigureNode();
    }

    /**
     * Create the elements of the figure Node that represents the enemy on the battle field
     */
    private void createFigureNode() {
        figureNode = createGroup(FIGURE_CENTRAL_X_POS, FIGURE_Y_POS);
        ImageView sideView;
        sideView = createPixelatedImageView(RESOURCE_PATH + spriteFigureName, 0, 10, FIGURE_WIDTH, FIGURE_HEIGHT);
        figureNode.getChildren().add(sideView);
    }

    /**
     * Update the position of the gui elements depending if the character
     * is alive and is playing the turn
     */
    public void update() {
        if (controller.getEnemyCharacterHealth(index)== 0) {
            figureToBack();
        } else {
            if (controller.isTurnBeingPlayedByEnemy()) {
                if (controller.getSelectedEnemyCharacterIndex() == index) {
                    figureToSelected();
                }
                else {
                    figureToUnSelected();
                }
            }  else {
                figureToUnSelected();
            }
        }
    }

    /**
     * Return the Group Node with the gui elements of the figure sprite
     */
    public Group getFigureNode() {
        return figureNode;
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
        figureNode.setLayoutX(FIGURE_BACK_X_POS - figureNode.getParent().getLayoutX());
    }
}
