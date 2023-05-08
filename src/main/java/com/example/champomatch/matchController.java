package com.example.champomatch;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
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

    private User userConnected;
    public void setUserConnected(User userConnected) {
        this.userConnected = userConnected;
    }

    private Single single;
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
}
