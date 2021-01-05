package com.github.cc3002.finalreality.gui;

import com.github.cc3002.finalreality.gui.scenes.StartMenuScene;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main entry point for the application.
 * <p>
 * Create the main Scene and then other classes will change the root Node as
 * the user submits their input.
 * The first root execute the start menu
 *
 * @author Ignacio Slater Muñoz.
 * @author Sebastián Olmos.
 */
public class FinalReality extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Final Reality demo");
    primaryStage.setResizable(false);

    Group root = new Group();
    int width = 1000;
    int height = 740;
    Scene scene = new Scene(root, width, height);

    StartMenuScene startScene = new StartMenuScene(scene);
    startScene.activate();

    primaryStage.setScene(scene);
    primaryStage.show();
  }

}