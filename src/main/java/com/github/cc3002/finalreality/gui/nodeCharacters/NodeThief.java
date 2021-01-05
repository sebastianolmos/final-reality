package com.github.cc3002.finalreality.gui.nodeCharacters;

import com.github.cc3002.finalreality.controller.GameController;

/**
 * This represents a playable Thief node that
 * generate the gui elements that display its information and the
 * actions that can take the user
 *
 * @author Sebastián Olmos.
 */
public class NodeThief extends AbstractNodeCharacter{

    /**
     * Create the thief node and initialize the character initHealth and its index
     * And sets the images file path then create the group nodes
     * @param controller
     *      Reference of the gameController
     * @param partyIndex
     *      index of the character in the controller party list
     * @param initHealth
     *      initial amount of life
     */
    public NodeThief(GameController controller, int partyIndex, int initHealth) {
        super(controller, partyIndex, initHealth);
        setSideImagePath();
        setPortraitImagePath();
        createNodes();
    }

    /**
     * Sets the path to the thief figure image file
     */
    @Override
    public void setSideImagePath() {
        spriteFigureName = "thiefSide.png";
    }

    /**
     * Sets the path to the thief portrait image file
     */
    @Override
    public void setPortraitImagePath() {
        spritePortraitName = "thiefPortrait.png";
    }
}
