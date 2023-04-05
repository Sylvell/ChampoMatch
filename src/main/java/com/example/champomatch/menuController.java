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
    }
}
