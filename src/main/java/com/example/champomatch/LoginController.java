package com.example.champomatch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public static Stage stage;

    @FXML
    private TextField emailIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Label errorLabel;

    private User user;



    @FXML
    public void login(ActionEvent event) throws SQLException {


        if (emailIdField.getText().isEmpty()) {
            infoBox(
                    "Please enter your email id");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            infoBox(
                    "Please enter a password");
            return;
        }

        String emailId = emailIdField.getText();
        String password = passwordField.getText();

        JdbcDao jdbcDao = new JdbcDao();
        boolean flag = jdbcDao.validate(emailId, password);

        if (!flag) {

            infoBox("Please enter correct Email and Password");
        } else {
            // Connected
            user = jdbcDao.getUser(emailId);
            try {
                //fermer la fenetre de login pour afficher la fenetre menu.fxml
                ((Node) (event.getSource())).getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
                Pane root = loader.load();
                menuController menuController = loader.getController();
                menuController.setUser(user);
                Scene scene = new Scene(root);
                Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Menu");
                stage.show();
                stage.centerOnScreen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void infoBox(String infoMessage) {
        this.errorLabel.setVisible(true);
        this.errorLabel.setText(infoMessage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //resize the window to the size of the scene when the window size is changed

        //vbox.prefWidthProperty().setValue(pane.getWidth());

    }
}
