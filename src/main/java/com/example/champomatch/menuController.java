package com.example.champomatch;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

import static com.example.champomatch.Recherche_pertinence.recherche_pert;
import static com.example.champomatch.Recherche_pertinence.single_list_id;

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
    @FXML
    private CheckBox malecheck;
    @FXML
    private CheckBox femalecheck;
    @FXML
    private CheckBox othercheck;
    @FXML
    private CheckBox age1;
    @FXML
    private CheckBox age2;
    @FXML
    private CheckBox age3;
    @FXML
    private CheckBox age4;
    @FXML
    private CheckBox age5;
    @FXML
    private CheckBox height1;
    @FXML
    private CheckBox height2;
    @FXML
    private CheckBox height3;
    @FXML
    private CheckBox height4;
    @FXML
    private CheckBox height5;
    @FXML
    private TextField search;

    private ArrayList<CheckBox> cblistAge = new ArrayList<CheckBox>();
    private ArrayList<CheckBox> cblistGender = new ArrayList<CheckBox>();
    private ArrayList<CheckBox> cblistHeight = new ArrayList<CheckBox>();


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
            // add all checkboxes to the list
            this.cblistGender.add(malecheck);
            this.cblistGender.add(femalecheck);
            this.cblistGender.add(othercheck);
            this.cblistAge.add(age1);
            this.cblistAge.add(age2);
            this.cblistAge.add(age3);
            this.cblistAge.add(age4);
            this.cblistAge.add(age5);
            this.cblistHeight.add(height1);
            this.cblistHeight.add(height2);
            this.cblistHeight.add(height3);
            this.cblistHeight.add(height4);
            this.cblistHeight.add(height5);
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
        // if a filter is applied, only show the filtered singles

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
            Single selectedItem = table.getSelectionModel().getSelectedItem();
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) { // check if it's a double click and left click
                if (selectedItem != null) {
                    // go to profile page
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
                        Parent root = loader.load();
                        profileController controller = loader.getController();
                        controller.setSingle(selectedItem);
                        controller.setUser(this.user);
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Profile of " + selectedItem.getFirstname() + " " + selectedItem.getName());
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (event.getButton() == MouseButton.SECONDARY) { // check if it's a right click
                // show context menu
                ContextMenu contextMenu = new ContextMenu();
                MenuItem findAMatchItem = new MenuItem("Find a match");
                findAMatchItem.setOnAction(event1 -> {
                    if (selectedItem != null) {
                        // go to match page
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("match.fxml"));
                            Parent root = loader.load();
                            matchController controller = loader.getController();
                            controller.setSingle(selectedItem);
                            controller.setUserConnected(this.user);
                            Scene scene = new Scene(root,900,600);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.centerOnScreen();
                            stage.setTitle("Match");
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                contextMenu.getItems().add(findAMatchItem);
                table.setContextMenu(contextMenu);
            }


        });
        // for all checkboxes in cblist, add an action listener
            for (  CheckBox cb : this.cblistGender) {
                cb.setOnAction(event -> this.checkboxfilters());
            }
            for (  CheckBox cb : this.cblistAge) {
                cb.setOnAction(event -> this.checkboxfilters());
            }
            for (  CheckBox cb : this.cblistHeight) {
                cb.setOnAction(event -> this.checkboxfilters());
            }

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
            Parent root = loader.load();
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
            Parent root = loader.load();
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
            Parent root = loader.load();
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
    public void unready(ActionEvent actionEvent) {
        // go to unready page
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("unready.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,900,600);
            UnreadyController controller = loader.getController();
            controller.setUserConnected(this.user);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void rdv(ActionEvent actionEvent) {
        // go to unready page
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rdv.fxml"));
            Parent root = loader.load();
            RDVcontroller controller = loader.getController();
            controller.setUserConnected(this.user);
            Scene scene = new Scene(root,900,600);
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

    /*
    public void filter(MouseEvent mouseEvent) {
        System.out.println("clicked");
        System.out.println( Arrays.toString(this.gender.getItems().toArray()));
    }*/


    // when there are letters in the text field, only show the singles that match the letters
    public void filter(KeyEvent keyEvent) {
        // get the text from the text field
        String text = this.search.getText();
        // if the text is empty, show all singles
        if (text.equals("")){
            ObservableList<Single> data = FXCollections.observableArrayList(list_to_show);
            table.setItems(data);
        }
        else
        {
            // else, show only the singles that match the text
            ArrayList<Single> list = new ArrayList<>();
            for (Single single : list_to_show){
                // if the text contains the name and the first name, add it to the list
                if (text.split(" ").length == 2){
                    if (single.getName().toLowerCase().contains(text.split(" ")[0].toLowerCase()) && single.getFirstname().toLowerCase().contains(text.split(" ")[1].toLowerCase())){
                        list.add(single);
                    }
                }
                // if the name or the first name contains the text, add it to the list
                else if (single.getName().toLowerCase().contains(text.toLowerCase()) || single.getFirstname().toLowerCase().contains(text.toLowerCase())){
                    list.add(single);
                }
            }
            ObservableList<Single> data = FXCollections.observableArrayList(list);
            table.setItems(data);
        }





    }

    public void checkboxfilters(){
        // for each checkbox, if it's selected, update list_to_show with singles that match the checkbox
        HashSet<Gender> genders = new HashSet<>();
        HashSet<String> age = new HashSet<>();
        HashSet<String> height = new HashSet<>();
        /*
        for (CheckBox checkBox : this.cblistGender){
            if (checkBox.isSelected()){
                // filter checkboxes by name
                Node parent = checkBox.getParent();
                MenuItem menuItem = (MenuItem) parent.getUserData();
                System.out.println(menuItem.getText());
                genders.add(Gender.valueOf(menuItem.getText()));

            }
        }
        for (CheckBox checkBox : this.cblistAge){
            if (checkBox.isSelected()){
                // filter checkboxes by age
                age.add(checkBox.getText());
            }
        }

        for (CheckBox checkBox : this.cblistHeight){
            if (checkBox.isSelected()){
                // filter checkboxes by height
                height.add(checkBox.getText());
            }
        }

        // update list_to_show
        List<Single> list = new ArrayList<>();
        Set<Integer> result = recherche_pert(genders,age, height);
        for(Integer id : result){
            list.add(single_list_id.get(id));
        }

        ObservableList<Single> data = FXCollections.observableArrayList(list);
        table.setItems(data);*/
    }

}

