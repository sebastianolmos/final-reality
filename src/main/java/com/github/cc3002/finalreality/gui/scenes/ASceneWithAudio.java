package com.github.cc3002.finalreality.gui.scenes;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.gui.GuiGenerator;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * This represents a "Scene" of the game that is a root Node that is put on
 * the mainScene no execute a part of the game.
 * Contains the methods to play and stop a sound.
 *
 * @author Sebastián Olmos.
 */
public abstract class ASceneWithAudio extends GuiGenerator {
    protected GameController controller;
    private Group root;
    protected Scene mainScene;
    private Clip sound;
    private String soundFileName;

    protected static final String RESOURCE_PATH = "src/main/resources/";
    private static final float DECIBELS = -15;

    /**
     * Create a scene with audio
     * @param controller
     *      controller that manages the game model
     * @param mainScene
     *      main Scene that needs the root Node
     * @param soundFileName
     *      string path to the file of the sound
     */
    public ASceneWithAudio(GameController controller, Scene mainScene, String soundFileName) {
        this.controller = controller;
        this.mainScene = mainScene;
        this.soundFileName = soundFileName;
        root = new Group();
    }

    /**
     * Method that contain the construction of the scene
     */
    protected abstract void run();

    /**
     *  Add a background image to the scene
     * @param backgroundImageView
     *      ImageView object of the background
     */
    public void addBackground(ImageView backgroundImageView) {
        root.getChildren().add(backgroundImageView);
    }

    /**
     * Method to activate and display the content of the scene
     */
    public void activate() {
        run();
        mainScene.setRoot(root);
        playSound();
    }

    /**
     * Play the audio of the scene
     */
    public void playSound() {
        String audioFilePath = RESOURCE_PATH + soundFileName;
        try {
            sound = AudioSystem.getClip();
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(audioFilePath))) {
                sound.open(audioInputStream);
                FloatControl gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(DECIBELS);
                sound.start();
                sound.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stop the audio of the scene
     */
    public void stopSound() {
        sound.stop();
    }

    /** Change the path of the sound, it needs to be executed before activate playSound
     *  (if its necessary)
     * @param fileName
     *      string path to the nee file
     */
    public void setSoundFileName(String fileName) {
        soundFileName = fileName;
    }

    /** Add a Node object to the scene/root Node
     * @param node
     *      object to be added
     */
    public void addToRoot(Node node) {
        root.getChildren().add(node);
    }
}
