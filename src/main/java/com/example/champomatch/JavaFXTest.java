package com.example.champomatch;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
        // Create a pane to hold the canvas

public class JavaFXTest extends Application {

    private Canvas canvas;
    private GraphicsContext gc;
    private double mouseX, mouseY;
    private Color strokeColor = Color.BLACK;

    // enum to store all colors
    private enum Colors {
        BLACK(Color.BLACK),
        RED(Color.RED),
        ORANGE(Color.ORANGE),
        YELLOW(Color.YELLOW),
        GREEN(Color.GREEN),
        BLUE(Color.BLUE),
        INDIGO(Color.INDIGO),
        VIOLET(Color.VIOLET),
        WHITE(Color.WHITE);

        private Color color;

        Colors(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a canvas to draw on
        canvas = new Canvas(400, 400);

        // Get the graphics context for the canvas
        gc = canvas.getGraphicsContext2D();

        // Set the initial stroke color and width
        gc.setStroke(strokeColor);
        gc.setLineWidth(2);

        HBox colorButtonBox = new HBox();

        // Create buttons to change the stroke color from enum colors
        //loop from enum colors
        for (Colors c : Colors.values()) {
            Button button = new Button(c.name().toLowerCase());
            //use button style to make it a circle and background color
            button.setStyle(
                    "-fx-background-radius: 5em; " +
                            "-fx-min-width: 30px; " +
                            "-fx-min-height: 30px; " +
                            "-fx-max-width: 30px; " +
                            "-fx-max-height: 30px;" +
                            "-fx-background-color: " + c.name()+ ";"
            );

            //add event handler to change the stroke color
            button.setOnAction(event -> {
                strokeColor = c.getColor();
                gc.setStroke(strokeColor);
            });
            colorButtonBox.getChildren().add(button);
        }

        // Create a ERASE button to clear the canvas
        Button whiteButton = new Button("Erase");
        whiteButton.setOnAction(event -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });

        // Create a "Save" button to save the canvas as an image
        Button saveButton = new Button("Save");


        // Create a pane to hold the canvas and buttons
        Pane root = new Pane(canvas, colorButtonBox, whiteButton,saveButton);
        colorButtonBox.setLayoutY(canvas.getHeight() + 10);
        whiteButton.setLayoutY(canvas.getHeight() + 50);


        // Add an event handler to the "Save" button to handle mouse click events
        saveButton.setOnMouseClicked(event -> {
            // Get project directory
            String projectDir = System.getProperty("user.dir");
            System.out.println(projectDir);
            // Create a file object to save to timestamped file
            File file = new File(projectDir + "/src/main/resources/com/example/champomatch/images/"+System.currentTimeMillis()+".png");

                // Create a writable image from the canvas
                WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                canvas.snapshot(null, writableImage);

                // Write the writable image to the file
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }

        });



        // Add an event handler to the canvas to handle mouse press events
        canvas.setOnMousePressed(event -> {
            // Record the current mouse coordinates
            mouseX = event.getX();
            mouseY = event.getY();
        });

        // Add an event handler to the canvas to handle mouse drag events
        canvas.setOnMouseDragged(event -> {
            // Draw a line from the last recorded coordinates to the current coordinates
            gc.strokeLine(mouseX, mouseY, event.getX(), event.getY());

            // Update the recorded coordinates to the current coordinates
            mouseX = event.getX();
            mouseY = event.getY();
        });

        // Create a scene with the root pane and set it as the primary stage's scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        // Set the title of the stage and show the scene
        primaryStage.setTitle("Draw with Mouse Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
