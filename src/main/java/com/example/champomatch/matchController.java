package com.example.champomatch;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class matchController {
    @FXML
    private Label nom1;

    @FXML
    private Label nom2;

    @FXML
    private Label percent;

    @FXML
    private ImageView pp1;

    @FXML
    private ImageView pp2;

    @FXML
    private Button accept;

    @FXML
    private Button decline;

    private User userConnected;

    private Single single;

    private Single single2;

    public void setUserConnected(User userConnected) {
        this.userConnected = userConnected;
    }

    public void setSingle(Single single) {
        this.single = single;
    }
    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            this.nom1.setText(single.getFirstname() + " " + single.getName());
            Matching matching = new Matching();
            JdbcDao jdbcDao = new JdbcDao();
            ArrayList<Single> singles = JdbcDao.select_single();
            Tuple match = matching.findMatch(this.single, singles);
            if (match.getSingle() == null) {
                this.nom2.setText("No match found");
                this.percent.setText("0%");
                // Create a Media object from a file path
               /* Media sound = new Media(getClass().getResource("/emptyMatch.mp3").toString());

                // Create a MediaPlayer object from the Media object
                MediaPlayer mediaPlayer = new MediaPlayer(sound);

                // Play the sound
                mediaPlayer.play();*/
                return;
            }else{
                this.single2 = match.getSingle();
                this.nom2.setText(match.getSingle().getFirstname() + " " + match.getSingle().getName());
                this.percent.setText(match.getScore() + "%");
                new ImageLoader(this.pp2, match.getSingle().getPp());
                // Create a Media object from a file path
                /*Media sound = new Media(getClass().getResource("/emptyMatch.mp3").toString());

                // Create a MediaPlayer object from the Media object
                MediaPlayer mediaPlayer = new MediaPlayer(sound);

                // Play the sound
                mediaPlayer.play();*/
            }

            new ImageLoader(this.pp1, this.single.getPp());
        });
    }

    @FXML
    public void goback(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Parent root = loader.load();
            menuController controller = loader.getController();
            controller.setUser(this.userConnected);
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

    @FXML
    public void tordv(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rdv.fxml"));
            Parent root = loader.load();
            RDVcontroller controller = loader.getController();
            controller.setUserConnected(this.userConnected);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("RDV");
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // when decline button is pressed use goback function
    @FXML
    public void decline(ActionEvent event){
        if (this.single2 == null) {
            return;
        }
        goback(event);
    }

    @FXML
    public void accept(ActionEvent event){
        if (this.single2 == null) {
            return;
        }
        RendezVous lerdv = new RendezVous(this.single, this.single2, RDVcontroller.datealea());
        RDVcontroller.rdv_list.add(lerdv);
        tordv(event);
    }
}
