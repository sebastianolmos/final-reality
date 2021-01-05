package com.github.cc3002.finalreality.gui.scenes;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.gui.RandomBackground;
import com.github.cc3002.finalreality.gui.GameCreator;
import com.github.cc3002.finalreality.gui.nodeCharacters.INodeCharacter;
import com.github.cc3002.finalreality.gui.nodeCharacters.NodeEnemy;
import com.github.cc3002.finalreality.gui.nodeSelection.NodeInventory;
import com.github.cc3002.finalreality.gui.nodeSelection.NodeTargetEnemy;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import java.util.List;

/**
 * This represents the scene with the battle system
 * is activated until all the party characters die or all the enemies die
 *
 * @author Sebastián Olmos.
 */
public class GameScene extends ASceneWithAudioAndTimer{
    private final GameCreator gameCreator;
    private List<INodeCharacter> partyNodes;
    private List<NodeEnemy> enemiesNodes;
    private final NodeInventory inventory;
    private final NodeTargetEnemy targetMenu;

    private static final String SOUND_FILE_NAME = "clips/BattleMusic2.wav";

    /**
     * Create the scene adding the root Node to the main scene and initialize the
     * methods for the audio, background and the timer
     * @param controller
     *      Reference of the controller that manage the model
     * @param creator
     *      Reference of the creator that manage the gui elements of the characters
     * @param main
     *      Reference of the main scene that needs set the root Node
     */
    public GameScene(GameController controller,GameCreator creator, Scene main) {
        super(controller, main, SOUND_FILE_NAME);
        this.gameCreator = creator;
        inventory = new NodeInventory(this.controller);
        targetMenu = new NodeTargetEnemy(this.controller);
        var backgroundSelection = new RandomBackground();
        var backgroundImage = new ImageView(backgroundSelection.getImage());
        addBackground(backgroundImage);
    }

    /**
     * Generate the elements of the gui according the characters
     */
    @Override
    public void run() {
        partyNodes = gameCreator.getPartyNodes();
        enemiesNodes = gameCreator.getEnemiesNodes();

        var partyFigures = gameCreator.getPositionedPartyFiguresNodes();
        var partyMenus = gameCreator.getPositionedPartyMenuNodes();
        var enemiesFigures = gameCreator.getPositionedEnemiesNodes();
        var inventoryNode = inventory.getMenuNode();
        var targetMenuNode = targetMenu.getNode();

        for (Group partyFigure : partyFigures) {
            addToRoot(partyFigure);
        }
        for (Group partyMenu: partyMenus) {
            addToRoot(partyMenu);
        }
        for (Group enemyFigure: enemiesFigures) {
            addToRoot(enemyFigure);
        }
        addToRoot(inventoryNode);
        addToRoot(targetMenuNode);
        inventory.updateInventoryView();
        GameOverScene gameOverScene = new GameOverScene(mainScene, controller, this);
    }

    /**
     * Method that will be called by the timer
     * updating the elements of the characters and the menu panels
     */
    @Override
    protected void updateInTimer() {
        for (INodeCharacter nodeCharacter: partyNodes) {
            nodeCharacter.update();
        }
        for (NodeEnemy nodeEnemy: enemiesNodes) {
            nodeEnemy.update();
        }
        inventory.update();
        targetMenu.update();
    }

    /**
     * Stop the sound and timer
     */
    public void stopScene() {
        stopTimer();
        stopSound();
    }
}
