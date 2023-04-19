package com.example.champomatch;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.github.javafaker.Faker;
import java.awt.*;
import java.sql.SQLException;
import java.util.Locale;

public class main {

    public static void main(String[] args) {
/*
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
        }*/
        Faker faker = new Faker(new Locale("fr"));

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String city = faker.address().city();
        String bio = faker.lorem().sentence(10);
        System.out.println(lastName + " " + firstName + " " + city + " " + bio);

        JdbcDao dao = new JdbcDao();
        try {
            for (User user: dao.getUsers())
            {
                System.out.println(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


