package com.github.cc3002.finalreality.gui;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

/**
 * This represents a class that generate a random background
 * that represent the battlefield in the game
 *
 * @author Sebastián Olmos.
 */
public class RandomBackground {
    private static final String RESOURCE_PATH = "src/main/resources/sprites/background/";
    private final Image[] images = new Image[7];

    /**
     * Try to load all the possible background images
     */
    public RandomBackground(){
        try {
            buildImages();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load all the possible background images
     * @throws FileNotFoundException
     */
    private void buildImages() throws FileNotFoundException {
        for (int i = 0; i < 7; i++) {
            images[i] = new Image(new FileInputStream(RESOURCE_PATH + "bb" + (i + 1) + ".png"));
        }
    }

    /**
     * Return a Image object with a random background
     */
    public Image getImage() {
        long seed = new Random().nextLong();
        Random rand = new Random(seed);
        int index = rand.nextInt(7);
        return images[index];
    }
}
