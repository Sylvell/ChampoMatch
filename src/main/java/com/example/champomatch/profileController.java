package com.example.champomatch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import java.io.IOException;

public class profileController {
    @FXML
    private Button returned;

    @FXML
    public void goback(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
