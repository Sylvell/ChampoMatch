package com.example.champomatch;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class userProfileController  implements Initializable {
    private User connectedUser;
    private User user;

    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField admin;
    @FXML
    private CheckBox edit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            if (this.user != null) {
                // fill the fields with the user's data
                this.name.setText(this.user.getFullName());
                this.email.setText(this.user.getEmail_id());
                this.admin.setText(String.valueOf(this.user.getAdmin()));
            }

        });
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void setConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }

    public void goback(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("usersList.fxml"));
            Parent root = (Parent) loader.load();
            usersListController controller = loader.getController();
            controller.setUser(user);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void changeState(Boolean state){
        this.name.setEditable(state);
        this.email.setEditable(state);
        this.admin.setEditable(state);


        this.name.setFocusTraversable(state);
        this.email.setFocusTraversable(state);
        this.admin.setFocusTraversable(state);


        this.name.setMouseTransparent(!state);
        this.email.setMouseTransparent(!state);
        this.admin.setMouseTransparent(!state);


    }

    public void editUser(ActionEvent event){
        // checkbox not selected
        /*try {
                single.ExportToDb();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }*/
        // checkbox selected
        this.changeState(edit.isSelected());
        if (! edit.isSelected()){
            try {
                user.ExportToDb();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
