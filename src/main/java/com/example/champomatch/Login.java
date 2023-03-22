package com.example.champomatch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Login extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(getClass());
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setTitle("User Login");
        stage.setScene(new Scene(root, 1637, 1070));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}