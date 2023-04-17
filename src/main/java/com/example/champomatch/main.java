package com.example.champomatch;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.awt.*;
import java.sql.SQLException;

public class main {

    public static void main(String[] args) {

            JdbcDao dao = new JdbcDao();
           // add image to all gender where pp is not set
        downloadImage image =  new downloadImage();
        for (Single single : dao.select_single()) {
            if (single.getPp() == "file:@images/defaultProfileImage.png") {
                single.setPp( image.get(single.getAge()  ,single.getGender()));
                try {
                    single.ExportToDb();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}


