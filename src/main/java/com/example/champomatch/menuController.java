package com.example.champomatch;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

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

    @FXML
    private MenuButton age;
    @FXML
    private MenuButton height;
    @FXML
    private MenuButton gender;

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
        // add all singles to the list
        JdbcDao jdbcDao = new JdbcDao();
        ObservableList<Single> data = FXCollections.observableArrayList(jdbcDao.select_single());
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
            if (event.getClickCount() == 1) { // check if it's a single click
                Single selectedItem = table.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    // go to profile page
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
                        Parent root = (Parent) loader.load();
                        profileController controller = loader.getController();
                        controller.setSingle(selectedItem);
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
    }
    /*
    @FXML
    public void ajoutClient(ActionEvent actionEvent) {
        // go to profile page
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
            Parent root = (Parent) loader.load();
            profileController controller = loader.getController();
            controller.setSingle(selectedItem);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }*/

    @FXML
    public void filter(ActionEvent actionEvent) {
        // get selected values from the menu buttons

    }
}

