package com.github.cc3002.finalreality.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This represents a Class that supply methods to create and generate different
 * elements of the gui, avoiding excessive code duplication
 *
 * @author Sebastián Olmos.
 */
public class GuiGenerator {

    /**
     * Change the position of a Node.
     * @param node
     *     Node to move.
     * @param xPos
     *     position on the x axis.
     * @param yPos
     *     position on the y axis.
     */
    protected void setPos(Node node, int xPos, int yPos) {
        node.setLayoutX(xPos);
        node.setLayoutY(yPos);
    }

    /**
     * Create a Group object at given position.
     * @param xPos
     *     position on the x axis.
     * @param yPos
     *     position on the y axis.
     */
    protected Group createGroup(int xPos, int yPos) {
        Group group = new Group();
        setPos(group, xPos, yPos);
        return group;
    }


    /**
     * Create a Label object with given attributes:
     * @param text
     *      String written in the label
     * @param xPos
     *      Position on the x axis
     * @param yPos
     *      Position on the y axis
     * @param fontSize
     *      Size of the font
     * @param fontWeight
     *      Weight of the font
     * @param r
     *      red component of the font color
     * @param g
     *      green component of the font color
     * @param b
     *      blue component og the font color
     */
    protected Label createLabel(String text, int xPos, int yPos, int fontSize, FontWeight fontWeight, double r, double g, double b) {
        Label label = new Label();
        setPos(label, xPos, yPos);
        label.setText(text);
        label.setFont(Font.font("Arial", fontWeight, fontSize));
        label.setTextFill(Color.color(r,g,b));
        return label;
    }

    /**
     * Create a Label object with normal white font and attributes:
     * @param text
     *      String written in the label
     * @param xPos
     *      Position on the x axis
     * @param yPos
     *      Position on the y axis
     * @param fontSize
     *      Size of the font
     */
    protected Label createLabel(String text, int xPos, int yPos, int fontSize) {
        return createLabel(text, xPos, yPos, fontSize, FontWeight.NORMAL, 1, 1, 1);
    }

    /**
     * Create a Button object with attributes:
     * @param text
     *      String written in the label
     * @param xPos
     *      Position on the x axis
     * @param yPos
     *      Position on the y axis
     * @param width
     *      width of the button
     * @param height
     *      height of the button
     */
    protected Button createButton(String text, int xPos, int yPos, int width, int height) {
        Button button = new Button(text);
        setPos(button, xPos, yPos);
        button.setMinSize(width, height);
        button.setPrefSize(width, height);
        button.setMaxSize(width, height);
        return button;
    }

    /**
     * Create an Image object with attributes
     * @param path
     *      string with the path of the file
     * @param width
     *      width of the Image
     * @param height
     *      height of the Image
     * @param smooth
     *      booleam to make the image pixelated or smooth
     */
    protected Image createImage(String path, int width, int height, boolean smooth) {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(stream, width, height, true, smooth);
        return image;
    }

    /**
     * Create an image object from the path
     * @param path
     *      string of the path to the file.
     */
    protected Image createImage(String path) {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(stream);
        return image;
    }

    /**
     * Create a pixelated Image object with attributes
     * @param path
     *      string of the path to the file.
     * @param width
     *      width of the Image
     * @param height
     *      height of the Image
     */
    protected Image createPixelatedImage(String path, int width, int height) {
        return createImage(path, width, height, false);
    }

    /**
     * Create a smooth Image object with attributes
     * @param path
     *      string of the path to the file.
     * @param width
     *      width of the Image
     * @param height
     *      height of the Image
     */
    protected Image createSmoothImage(String path, int width, int height) {
        return createImage(path, width, height, true);
    }

    /**
     * Create a pixelated ImageView object
     * @param path
     *      string of the path to the file.
     * @param xPos
     *      position on x axis
     * @param yPos
     *      position on y xis
     * @param width
     *      width of the ImageView
     * @param height
     *      height of the ImageView
     */
    protected ImageView createPixelatedImageView(String path, int xPos, int yPos, int width, int height) {
        ImageView imageView = new ImageView(createPixelatedImage(path, width, height));
        setPos(imageView, xPos, yPos);
        return imageView;
    }

    /**
     * Create a smooth ImageView object
     * @param path
     *      string of the path to the file.
     * @param xPos
     *      position on x axis
     * @param yPos
     *      position on y xis
     * @param width
     *      width of the ImageView
     * @param height
     *      height of the ImageView
     */
    protected ImageView createSmoothImageView(String path, int xPos, int yPos, int width, int height) {
        ImageView imageView = new ImageView(createSmoothImage(path, width, height));
        setPos(imageView, xPos, yPos);
        return imageView;
    }

    /**
     * Create an ImageView object without size
     * @param image
     *      object with the image
     * @param xPos
     *      position on x axis
     * @param yPos
     *      position on y axis
     */
    protected ImageView createImageView(Image image, int xPos, int yPos) {
        ImageView imageView = new ImageView(image);
        setPos(imageView, xPos, yPos);
        return imageView;
    }

    /**
     * Create an ImageView object from an Image
     * @param image
     *      object with the image
     * @param xPos
     *      position on x axis
     * @param yPos
     *      position on y axis
     * @param width
     *      width of the ImageView Object
     * @param height
     *      height of the ImageView Object
     */
    protected ImageView createImageView(Image image, int xPos, int yPos, int width, int height) {
        ImageView imageView = new ImageView(image);
        setPos(imageView, xPos, yPos);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    /**
     * Create an ImageView object form path
     * @param path
     *      string of the path to the file
     * @param xPos
     *      position on x axis
     * @param yPos
     *      position on y axis
     * @param width
     *      width of the ImageView Object
     * @param height
     *      height of the ImageView Object
     */
    protected ImageView createImageView(String path, int xPos, int yPos, int width, int height) {
        return createImageView(createImage(path), xPos, yPos, width, height);
    }

    /** Create a textField object at
     * @param xPos
     *      position on x axis
     * @param yPos
     *      position on y axis
     * @param width
     *      width of the field
     * @param height
     *      height of the field
     */
    protected TextField createTextField(int xPos, int yPos, int width, int height) {
        TextField field = new TextField();
        setPos(field, xPos, yPos);
        field.setPrefSize(width, height);
        return field;
    }

    /**
     * Create a flowPane with attributes:
     * @param xPos
     *      position on x axis
     * @param yPos
     *      position on y axis
     * @param alignment
     *      pane element alignment
     * @param xPadding
     *      padding on the left and right side
     * @param yPadding
     *      padding up and down the pane
     * @param gap
     *      gap/distance between elements
     * @param width
     *      width of the grid/pane
     */
    protected FlowPane createFlowPane(int xPos, int yPos, Pos alignment, int xPadding, int yPadding, int gap, int width) {
        FlowPane pane = new FlowPane();
        pane.setLayoutX(xPos);
        pane.setLayoutY(yPos);
        pane.setAlignment(alignment);
        pane.setPadding(new Insets(yPadding, xPadding, yPadding, xPadding));
        pane.setHgap(gap);
        pane.setVgap(gap);
        pane.setMinWidth(width);
        pane.setPrefWidth(width);
        pane.setMaxWidth(width);
        return pane;
    }
}
