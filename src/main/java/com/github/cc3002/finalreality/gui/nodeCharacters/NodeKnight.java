package com.github.cc3002.finalreality.gui.nodeCharacters;

import com.github.cc3002.finalreality.controller.GameController;

/**
 * This represents a playable Knight node that
 * generate the gui elements that display its information and the
 * actions that can take the user
 *
 * @author Sebastián Olmos.
 */
public class NodeKnight extends AbstractNodeCharacter{

    /**
     * Create the knight node and initialize the character initHealth and its index
     * And sets the images file path then create the group nodes
     * @param controller
     *      Reference of the gameController
     * @param partyIndex
     *      index of the character in the controller party list
     * @param initHealth
     *      initial amount of life
     */
    public NodeKnight(GameController controller, int partyIndex, int initHealth) {
        super(controller, partyIndex, initHealth);
        setSideImagePath();
        setPortraitImagePath();
        createNodes();
    }

    /**
     * Sets the path to the knight figure image file
     */
    @Override
    public void setSideImagePath() {
        spriteFigureName = "knightSide.png";
    }

    /**
     * Sets the path to the knight portrait image file
     */
    @Override
    public void setPortraitImagePath() {
        spritePortraitName = "knightPortrait.png";
    }
}
