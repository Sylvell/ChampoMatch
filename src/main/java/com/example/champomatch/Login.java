package com.example.champomatch;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;

import java.awt.*;


public class Login extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(getClass());
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setTitle("User Login");
        stage.getIcons().add(new Image(getClass().getResource("images/logo.png").toExternalForm()));
        // Charger la première scène depuis un fichier FXML
        root = FXMLLoader.load(getClass().getResource("loadingScreen.fxml"));
        Scene scene1 = new Scene(root, 582, 591);
        stage.centerOnScreen();

        // Charger la seconde scène depuis un fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root2 = loader.load();
        LoginController controller = loader.getController();
        controller.stage = stage;
        Scene scene2 = new Scene(root2, 1550, 850);

        // Afficher la première scène
        stage.setScene(scene1);
        stage.show();

        // Mettre en pause pendant 3 secondes
        PauseTransition pause = new PauseTransition(Duration.seconds(0.2));
        pause.setOnFinished(event -> {
            // Afficher la seconde scène
            stage.setScene(scene2);
            stage.centerOnScreen();
            stage.show();
        });
        pause.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

}