package com.github.cc3002.finalreality.gui.scenes;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.gui.GameCreator;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * This represents the start scene where the user can chose to play between
 * a default battle, a boss battle or make the battle
 *
 * @author Sebastián Olmos.
 */
public class StartMenuScene extends ASceneWithAudio{
    private final GameCreator gameCreator;
    private static final String SOUND_FILE_NAME = "clips/BattleMusic1.wav";

    /**
     * Create the scene adding the root Node to the main scene and initialize the
     * methods for the audio and background and creating the controller and the gameCreator
     * @param mainScene
     *      Reference of the mainScene that needs set the root Node
     */
    public StartMenuScene(Scene mainScene) {
        super(new GameController(5, 5, 27), mainScene, SOUND_FILE_NAME);
        gameCreator = new GameCreator(this.controller);
        var backgroundImage = createSmoothImageView(RESOURCE_PATH + "sprites/background/earth.png", 0, 0, 1000, 740);
        addBackground(backgroundImage);
    }

    /**
     * Generate the elements of the gui, the game tittle and the buttons to change scene
     */
    @Override
    protected void run() {
        Group tittleNode = createGroup(230, 180);
        Text text = new Text("FINAL REALITY");
        text.setStyle("-fx-font: 100px Impact; -fx-fill: linear-gradient(to bottom, #ffffff, #d7d7d7); ");
        tittleNode.getChildren().add(text);
        var demoLabel = createLabel("DEMO", 530, -10, 17, FontWeight.BOLD, 1, 1, 1 );
        tittleNode.getChildren().add(demoLabel);
        var authorLabel = createLabel("by Sebastian Olmos", 200, 20, 16);
        tittleNode.getChildren().add(authorLabel);
        addToRoot(tittleNode);

        GameScene gameScene = new GameScene(controller, gameCreator, mainScene);
        GameMakerScene makerScene = new GameMakerScene(controller, gameCreator, mainScene);

        Group buttons = createGroup(430, 420);
        Button makeButton = createButton("Make your Battle", 0, 0, 150, 50);
        makeButton.setOnAction(event -> {stopSound(); makerScene.activate();});
        buttons.getChildren().add(makeButton);
        Button defaultButton = createButton("Default Battle", 0, 70, 150, 50);
        defaultButton.setOnAction(event -> {gameCreator.createDefaultGame(); stopSound(); gameScene.activate(); });
        buttons.getChildren().add(defaultButton);
        Button bossButton = createButton("Boss Battle", 0, 140, 150, 50);
        bossButton.setOnAction(event -> {gameCreator.createBossBattle(); stopSound(); gameScene.activate(); });
        buttons.getChildren().add(bossButton);

        addToRoot(buttons);
    }

}
