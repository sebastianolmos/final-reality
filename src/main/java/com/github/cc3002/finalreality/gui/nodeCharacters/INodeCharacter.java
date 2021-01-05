package com.github.cc3002.finalreality.gui.nodeCharacters;

import javafx.scene.Group;

/**
 * This represents a playable character node that
 * generate the gui elements of the figure and portrait menu
 *
 * @author Sebastián Olmos.
 */
public interface INodeCharacter {

    /**
     * Update the position of the gui elements depending if the character
     * is alive and is playing the turn
     */
    void update();

    /**
     * Return the Group Node with the gui elements of the portrait menu
     */
    Group getMenuNode();

    /**
     * Return the Group Node with the gui elements of the figure sprite
     */
    Group getFigureNode();
}
