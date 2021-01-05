package com.github.cc3002.finalreality.gui.scenes;

import com.github.cc3002.finalreality.controller.GameController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This represents the scene that display the result of the battle
 * where the user can go to the menu or close the game
 *
 * @author Sebastián Olmos.
 */
public class GameOverScene extends ASceneWithAudio implements IGameOverScene{
    private final GameScene gameScene;
    private String resultString;

    // Constants with file paths
    private static final String RESOURCE_PATH = "src/main/resources/";
    private static final String VICTORY_SOUND_FILE_NAME = "clips/EventMusic4.wav";
    private static final String DEFEAT_SOUND_FILE_NAME = "clips/EventMusic3.wav";

    /**
     * Create the scene adding the root Node to the main scene and initialize the
     * methods for the audio and background
     * @param mainScene
     *      Reference of the main scene that needs set the root Node
     * @param controller
     *      Reference of the controller that manage the model
     * @param gameScene
     *      Reference of the gameScene that needs to stop the timer and the sound
     */
    public GameOverScene(Scene mainScene, GameController controller, GameScene gameScene) {
        super(controller, mainScene, VICTORY_SOUND_FILE_NAME);
        this.gameScene = gameScene;
        controller.setGameOverScene(this);
    }

    /**
     * Generate the elements of the gui depending on the battle result
     */
    @Override
    protected void run() {
        var resultNode = createGroup(330, 200);
        var resultText = new Text(resultString);
        resultText.setStyle("-fx-font: 100px Impact; -fx-fill: linear-gradient(to bottom, #ffffff, #d7d7d7); ");
        resultNode.getChildren().add(resultText);
        addToRoot(resultNode);

        var startScene = new StartMenuScene(mainScene);
        Group buttons = createGroup(330, 500);
        Button menuButton = createButton("Back to Menu", 0, 0, 150, 70);
        menuButton.setOnAction(event -> {stopSound(); startScene.activate();});
        buttons.getChildren().add(menuButton);
        Button closeButton = createButton("Close Game", 200, 0, 150, 70);
        closeButton.setOnAction(event -> {
            stopSound();
            Stage stage = (Stage) closeButton.getScene().getWindow(); stage.close();});
        buttons.getChildren().add(closeButton);

        addToRoot(buttons);
    }

    /**
     * Set the background, tittle and sound to display the victory scene
     */
    @Override
    public void setVictory() {
        gameScene.stopScene();
        var backgroundImage = createSmoothImageView(RESOURCE_PATH + "sprites/background/sunset.png", 0, 0, 1000, 740);
        addBackground(backgroundImage);
        resultString = "VICTORY";
        setSoundFileName(VICTORY_SOUND_FILE_NAME);
    }

    /**
     * Set the background, tittle and sound to defeat the victory scene
     */
    @Override
    public void setDefeat() {
        gameScene.stopScene();
        var backgroundImage = createSmoothImageView(RESOURCE_PATH + "sprites/background/darkroom.png", 0, 0, 1000, 740);
        addBackground(backgroundImage);
        resultString = "DEFEATED";
        setSoundFileName(DEFEAT_SOUND_FILE_NAME);
    }
}
