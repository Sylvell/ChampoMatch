package com.example.champomatch;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class testHobbiesJavaFX extends Application {

    private FlowPane flowPane;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create a FlowPane to hold the hobbies
        flowPane = new FlowPane(10, 10);

        // Set the padding and alignment of the FlowPane
        flowPane.setPadding(new Insets(10));
        flowPane.setAlignment(Pos.TOP_LEFT);

        // Add some sample hobbies to the FlowPane
        flowPane.getChildren().addAll(
                createHobbyPane("Playing guitar"),
                createHobbyPane("Reading books"),
                createHobbyPane("Watching movies"),
                createHobbyPane("Playing video games"),
                createHobbyPane("Hiking"),
                createHobbyPane("Photography")
        );

        // Create a Pane to hold the FlowPane and a button to add a new hobby
        Pane root = new Pane();

        // Create a Button to add a new hobby
        Button addButton = new Button("+");
        addButton.setLayoutX(10);
        addButton.setLayoutY(10);
        addButton.setOnAction(event -> {
            flowPane.getChildren().add(createHobbyPane(""));
        });

        // Add the FlowPane and the Button to the root Pane
        root.getChildren().addAll(flowPane, addButton);

        // Create a Scene with the root Pane as the root node
        Scene scene = new Scene(root, 800, 200);

        // Set the minimum width of the window to ensure the FlowPane can fit as many hobbies on a single line as possible
        primaryStage.setMinWidth(200);

        // Set the title of the stage
        primaryStage.setTitle("Hobbies List");

        // Set the scene of the stage
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();

        // Add a listener to the width property of the scene to adjust the layout of the FlowPane when the window is resized
        scene.widthProperty().addListener((observable, oldWidth, newWidth) -> {
            // Calculate the maximum number of hobbies that can fit on a single line
            int maxHobbiesPerLine = (int) Math.floor(newWidth.doubleValue() / 120);
            // Calculate the maximum width of the hobby pane as a percentage of the available width
            double maxHobbyWidth = (newWidth.doubleValue() / maxHobbiesPerLine) - (flowPane.getHgap() * (maxHobbiesPerLine - 1)) - flowPane.getPadding().getLeft() - flowPane.getPadding().getRight();
            flowPane.getChildren().forEach(hobbyPane -> {
                ((HBox) hobbyPane).getChildren().get(0).prefWidth(maxHobbyWidth);
            });
        });
    }

    private HBox createHobbyPane(String hobby) {

        // Create a Label to display the hobby with wrap text
        Label label = new Label(hobby);
        label.setWrapText(true);
        label.setPrefWidth(120);

        // Create a Button to delete the hobby
        Button deleteButton    = new Button("x");
        deleteButton.setOnAction(event -> {
            flowPane.getChildren().remove(deleteButton.getParent());
        });

        // Create an HBox to hold the Label and the Button
        HBox hobbyPane = new HBox(5, label, deleteButton);
        hobbyPane.setAlignment(Pos.CENTER_LEFT);

        return hobbyPane;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
