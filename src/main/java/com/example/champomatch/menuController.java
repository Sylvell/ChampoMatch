package com.example.champomatch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

public class menuController {

    @FXML
    private MenuButton hobbies ;

    @FXML
    private Button bp1;

    @FXML
    private Button bp2;

    @FXML
    private Button bp3;

    @FXML
    private Button bp4;

    @FXML
    private Button bp5;

    @FXML
    private Button bp6;

    @FXML
    public void menu(ActionEvent event){
        // au click du bp1, on affiche le fichier fxml loadingScreen

    }
/*
    @FXML
    public void initialize() {
        hobbies.setOnAction(event -> {
            MenuItem selectedItem = hobbies.getItems().stream()
                    .filter(item -> item instanceof CheckMenuItem && ((CheckMenuItem) item).isSelected())
                    .findFirst().orElse(null);
            System.out.println("here");
            if (selectedItem != null) {
                System.out.println("Selected item: " + selectedItem.getText());
            }
        });
    }*/
}