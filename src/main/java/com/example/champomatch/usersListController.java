package com.example.champomatch;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class usersListController  implements Initializable {
    private User user;
    @FXML
    private Button returned;
    @FXML
    private TableView<User> table;
    @FXML
    private TextField text;
    private ObservableList<User> data;
    @FXML
    public void goback(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Parent root = (Parent) loader.load();
            menuController controller = loader.getController();
            controller.setUser(user);
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
            // fill the table with the list of users
            JdbcDao jdbcDao = new JdbcDao();
            try {
               this.data = FXCollections.observableArrayList(jdbcDao.getUsers());
            TableColumn<User, String> idCol = new TableColumn<>("ID");
            TableColumn<User, String> nameCol = new TableColumn<>("fullName");
            TableColumn<User, String> emailCol = new TableColumn<>("email");
            TableColumn<User, Boolean> adminCol = new TableColumn<>("admin");
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email_id"));
            adminCol.setCellValueFactory(new PropertyValueFactory<>("admin"));
            this.table.getColumns().addAll(idCol,nameCol, emailCol, adminCol);
            this.table.setItems(data);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            this.table.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) { // check if it's a single click
                    User selectedItem = this.table.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        // go to profile page
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("userProfile.fxml"));
                            Parent root = (Parent) loader.load();
                            userProfileController controller = loader.getController();
                            controller.setUser(selectedItem);
                            controller.setConnectedUser(this.user);
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
        });
        }

    public void setUser(User user) {
        this.user = user;
    }

    public void addUser(ActionEvent actionEvent) {
            // go to profile page
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("userProfile.fxml"));
                Parent root = (Parent) loader.load();
                userProfileController controller = loader.getController();
                controller.setUser(null);
                controller.setConnectedUser(this.user);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    public void filter(KeyEvent actionEvent) {
        // fill the table with users that match the filter
        JdbcDao jdbcDao = new JdbcDao();
        Connection connection = null;
        String name = "";
        String firstName = "";
        String sql = "";
        if (this.text.getText() == "" || this.text.getText() == null) {
             name = "";
             firstName = "";
             sql = "SELECT * FROM registration";
        }
        if (this.text.getText().split(" ").length == 1) {
            name = text.getText().split(" ")[0];
             sql = "SELECT * FROM registration WHERE full_name LIKE '%"+name+"%' || email_id LIKE '%"+name+"%'";
        } else if (this.text.getText().split(" ").length == 2) {
            name = text.getText().split(" ")[0];
            firstName = text.getText().split(" ")[1];
             sql = "SELECT * FROM registration WHERE full_name LIKE '%"+name+"%"+firstName+"%'";
        }

        try {
            connection=jdbcDao.getConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                ArrayList<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    User user = new User(
                   resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("email_id"),
                    resultSet.getInt("admin"));
                    users.add(user);
                }
                this.data = FXCollections.observableArrayList(users);
                this.table.setItems(data);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
