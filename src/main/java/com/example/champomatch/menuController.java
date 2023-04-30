package com.example.champomatch;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

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
    private TableView<Single> table;

    private ArrayList<Single> list_to_show = JdbcDao.select_single();

    @FXML
    private MenuButton age;
    @FXML
    private MenuButton height;
    @FXML
    private MenuButton gender;

    @FXML
    private Label userText;
    private User user;

    private Set<Gender> selectedGenders;

    private Set<String> selectedHeights;

    private Set<String> selectedAges;

    @FXML
    public void profil(ActionEvent event){
        //affiche SceneBuilder_profile.fxml dans la fenêtre quand on clique sur le bouton bp4
            /*try {
                Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }*/
    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
        // set userText
        this.userText.setText(this.userText.getText() + " " + this.user.getFullName());
        if (this.user.getAdmin() ==1){
            this.userText.setStyle("-fx-text-fill: white");
        }else{
            this.userText.setStyle("-fx-text-fill: green");
        }
        // add all singles to the list
        JdbcDao jdbcDao = new JdbcDao();
        ObservableList<Single> data = FXCollections.observableArrayList(list_to_show);
        // create a table view and add columns
        TableColumn<Single, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Single, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        TableColumn<Single, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        TableColumn<Single, Integer> heightCol = new TableColumn<>("Height");
        heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));
        TableColumn<Single, Gender> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        TableColumn<Single, Gender> preferredGenderCol = new TableColumn<>("Preferred Gender");
        preferredGenderCol.setCellValueFactory(new PropertyValueFactory<>("preferredGender"));
        TableColumn<Single, String> bioCol = new TableColumn<>("Bio");
        bioCol.setCellValueFactory(new PropertyValueFactory<>("bio"));
        TableColumn<Single, String> locationCol = new TableColumn<>("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory<>("localisation"));
        TableColumn<Single, Integer> distanceCol = new TableColumn<>("Distance");
        distanceCol.setCellValueFactory(new PropertyValueFactory<>("distance"));
        TableColumn<Single, Integer> minAgeCol = new TableColumn<>("Minimum Age");
        minAgeCol.setCellValueFactory(new PropertyValueFactory<>("minimunAge"));
        TableColumn<Single, Integer> maxAgeCol = new TableColumn<>("Maximum Age");
        maxAgeCol.setCellValueFactory(new PropertyValueFactory<>("maximunAge"));
        table.getColumns().addAll(nameCol, firstNameCol, ageCol, heightCol, genderCol, preferredGenderCol, bioCol, locationCol, distanceCol, minAgeCol, maxAgeCol);
        table.setItems(data);

        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // check if it's a double click
                Single selectedItem = table.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    // go to profile page
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
                        Parent root = (Parent) loader.load();
                        profileController controller = loader.getController();
                        controller.setSingle(selectedItem);
                        controller.setUser(this.user);
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // action listener for menu buttons
            /*gender.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // if item is selected, add it to the list, else remove them

                    System.out.println( gender.getItems());
                }
            });*/
    });
    }

    @FXML
    public void ajoutClient(ActionEvent actionEvent) {
       // go to profile page
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
            Parent root = (Parent) loader.load();
            profileController controller = loader.getController();
            controller.setSingle(new Single());
            controller.setUser(this.user);
            controller.newSingle=true;
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void users(ActionEvent actionEvent){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("usersList.fxml"));
            Parent root = (Parent) loader.load();
            usersListController controller = loader.getController();
            controller.setUser(this.user);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }





    public void setUser(User user) {
        // set the user
        this.user = user;
    }

    public void disconnect(ActionEvent actionEvent) {
        // go to login page
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = (Parent) loader.load();
            LoginController controller = loader.getController();
            Scene scene = new Scene(root,1550,850);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void update(MouseEvent mouseEvent) {
        // update the list
        Recherche_pertinence tri = new Recherche_pertinence();
      //  gender.get
        //tri.recherche_pert();
        list_to_show = JdbcDao.select_single();
        ObservableList<Single> data = FXCollections.observableArrayList(list_to_show);
        table.setItems(data);

    }


    public void filter(MouseEvent mouseEvent) {
        System.out.println("clicked");
        System.out.println( Arrays.toString(this.gender.getItems().toArray()));
    }
}

