package com.example.champomatch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class profileController implements Initializable {
    @FXML
    private Button returned;

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
    private TextField bio;
    private Single single  = new Single("Martin", "Léa", 22, 165,Gender.Female,"http://champomatch.hdyx5526.odns.fr/ChampoMatch/images/001021.jpg" , Gender.Male, "Je suis une jeune étudiante passionnée par les réseaux sociaux et l'influence qu'ils ont sur la société.", "Paris", 10, 18, 30);
    ;
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

            // set image
            new ImageLoader(this.imageView,single.getPp());

            //set Name
            this.name.setPromptText(single.getFirstname() + " " + single.getName());

            //set Age
            this.age.setPromptText(Integer.toString(single.getAge()));

            //set Size
            this.size.setPromptText(Integer.toString(single.getHeight()));

            //set Gender
            this.gender.setPromptText(single.getGender());

            //set pref gender
            this.preffered_gender.setPromptText(single.getPreferredGender());

            //set Localisation
            this.localisation.setPromptText(single.getLocalisation());

            //set bio
            this.bio.setPromptText(single.getBio());
    }
}
