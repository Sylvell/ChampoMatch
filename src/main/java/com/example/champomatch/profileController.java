package com.example.champomatch;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
    @FXML
    private FlowPane hobbies;
    private Single single ;
    private User user;


    public boolean newSingle = false;

    public void setSingle(Single single) {
        this.single = single;
    }


    @FXML
    public void goback(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Parent root = loader.load();
            menuController controller = loader.getController();
            controller.setUser(user);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Menu");
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            if(!this.newSingle){

            // set image
            new ImageLoader(this.imageView, this.single.getPp());

            //set Name
            this.name.setText(this.single.getFirstname() + " " + this.single.getName());

            //set Age
            this.age.setText(Integer.toString(this.single.getAge()));

            //set Size
            this.size.setText(Integer.toString(this.single.getHeight()));

            //set Gender
            this.gender.setText(this.single.getGender());

            //set pref gender
            this.preffered_gender.setText(this.single.getPreferredGender());

            //set Localisation
            this.localisation.setText(this.single.getLocalisation());

            //set bio
            this.bio.setText(this.single.getBio());
            }
            ArrayList<Hobbies> test = new ArrayList<Hobbies>();
             test.add(Hobbies.Plage);
            test.add(Hobbies.Cinema);
            test.add(Hobbies.Cooking);
            test.add(Hobbies.Gaming);
            test.add(Hobbies.Music);
            test.add(Hobbies.Sport);
            test.add(Hobbies.Sleep);
            test.add(Hobbies.Shopping);
            test.add(Hobbies.Reading);
            test.add(Hobbies.Dance);
            test.add(Hobbies.Travel);



            //this.single.setHobbies(test);
            //set hobbies
            for (Hobbies hobby : this.single.getHobbies()) {
                this.hobbies.getChildren().add(new Label(hobby.name()));
            }

            // add action listener to image
            this.imageView.setOnMouseClicked(event -> {

                   // create new window
                StackPane secondaryLayout = new StackPane();
                Scene secondScene = new Scene(secondaryLayout, 900, 900);
                    Stage newWindow = new Stage();
                newWindow.setScene(secondScene);

                    //add image to new window
                       ImageView imageView = new ImageView();
                       new ImageLoader(imageView, this.single.getPp());
                          imageView.setFitHeight(900);
                            imageView.setFitWidth(900);
                            imageView.setPreserveRatio(true);
                            secondaryLayout.getChildren().add(imageView);
                    newWindow.setX(event.getSceneX()+300);
                    newWindow.setY(0);
                    newWindow.show();
            });
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
            // set new values to single
            single.setAge(Integer.parseInt(this.age.getText()));
            single.setBio(this.bio.getText());
            single.setLocalisation(this.localisation.getText());
            single.setHeight(Integer.parseInt(this.size.getText()));
            single.setGender(Gender.valueOf(this.gender.getText()));
            single.setPreferedGender(Gender.valueOf(this.preffered_gender.getText()));
            single.setName(this.name.getText().split(" ")[1]);
            single.setFirstname(this.name.getText().split(" ")[0]);

            try {
                single.ExportToDb();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

}
