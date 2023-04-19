package com.example.champomatch;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class profileController implements Initializable {
    @FXML
    private Button returned;

    @FXML
    private CheckBox edit;


    @FXML
    private ImageView imageView;

    @FXML
    private TextField name;
    @FXML
    private TextField gender;
    @FXML
    private TextField preffered_gender;
    @FXML
    private TextField size;
    @FXML
    private TextField localisation;
    @FXML
    private TextField age;
    @FXML
    private TextArea bio;
    private Single single ;

    public void setSingle(Single single) {
        this.single = single;
    }

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            // set image
            new ImageLoader(this.imageView, single.getPp());

            //set Name
            this.name.setText(single.getFirstname() + " " + single.getName());

            //set Age
            this.age.setText(Integer.toString(single.getAge()));

            //set Size
            this.size.setText(Integer.toString(single.getHeight()));

            //set Gender
            this.gender.setText(single.getGender());

            //set pref gender
            this.preffered_gender.setText(single.getPreferredGender());

            //set Localisation
            this.localisation.setText(single.getLocalisation());

            //set bio
            this.bio.setText(single.getBio());
        });
    }

    public void changeState(Boolean state){
        this.name.setEditable(state);
        this.age.setEditable(state);
        this.size.setEditable(state);
        this.bio.setEditable(state);
        this.localisation.setEditable(state);
        this.gender.setEditable(state);
        this.preffered_gender.setEditable(state);

        this.name.setFocusTraversable(state);
        this.age.setFocusTraversable(state);
        this.size.setFocusTraversable(state);
        this.bio.setFocusTraversable(state);
        this.localisation.setFocusTraversable(state);
        this.gender.setFocusTraversable(state);
        this.preffered_gender.setFocusTraversable(state);

        this.name.setMouseTransparent(!state);
        this.age.setMouseTransparent(!state);
        this.size.setMouseTransparent(!state);
        this.bio.setMouseTransparent(!state);
        this.localisation.setMouseTransparent(!state);
        this.gender.setMouseTransparent(!state);
        this.preffered_gender.setMouseTransparent(!state);

    }

    public void editSingle(ActionEvent event){
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
                single.ExportToDb();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
