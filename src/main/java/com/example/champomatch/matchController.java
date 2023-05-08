package com.example.champomatch;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.HashMap;

public class matchController {
    @FXML
    private Label nom1;

    @FXML
    private Label nom2;

    @FXML
    private Label percent;

    private User userConnected;
    public void setUserConnected(User userConnected) {
        this.userConnected = userConnected;
    }

    private Single single;
    public void setSingle(Single user) {
        this.single = single;
    }
    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            nom1.setText(single.getFirstname() + " " + single.getName());
            Matching matching = new Matching();
            JdbcDao jdbcDao = new JdbcDao();
            ArrayList<Single> singles = JdbcDao.select_single();
            Tuple match = matching.findMatch(singles.get(4), singles);
            nom2.setText(match.getSingle().getFirstname() + " " + match.getSingle().getName());
            percent.setText(match.getScore() + "%");
        });
        }
}
