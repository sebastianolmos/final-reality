package com.github.cc3002.finalreality.gui.scenes;

import com.github.cc3002.finalreality.controller.GameController;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;

/**
 * This represents a "Scene" of the game with audio and dynamic elements
 * that needs to be updated with a timer
 *
 * @author Sebastián Olmos.
 */
public abstract class ASceneWithAudioAndTimer extends ASceneWithAudio{
    private AnimationTimer timer;

    /**
     * Create a scene with audio
     * @param controller
     *      controller that manages the game model
     * @param mainScene
     *      main Scene that needs the root Node
     * @param soundFileName
     *      string path to the file of the sound
     */
    public ASceneWithAudioAndTimer(GameController controller, Scene mainScene, String soundFileName) {
        super(controller, mainScene, soundFileName);
    }

    /**
     * Method that contains the code that needs to be updated
     */
    protected abstract void updateInTimer();

    /**
     * The scene need to start the animator with its activation
     */
    @Override
    public void activate() {
        super.activate();
        startAnimator();
    }


    /**
     * Method that update every amount of time and start the timer
     */
    private void startAnimator() {
        timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                updateInTimer();
            }
        };
        timer.start();
    }

    /**
     * Method that stop the timer
     */
    public void stopTimer() {
        timer.stop();
    }
}
