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

public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = loader.load();
        MyController controller = loader.getController();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
            main m = new main();
            downloadImage Image = new downloadImage();
/*
            Single s1 = new Single("Martin", "Léa", 22, 165,Gender.Female, Image.get(24, 20, "female"), Gender.Male, "Je suis une jeune étudiante passionnée par les réseaux sociaux et l'influence qu'ils ont sur la société.", "Paris", 10, 18, 30);
            Single s2 = new Single("Dubois", "Pierre", 35, 189,Gender.Male, Image.get(37, 33, "male"), Gender.Female, "Je suis un ingénieur logiciel talentueux passionné par la programmation et les nouvelles technologies.", "Lyon", 20, 25, 40);
            Single s3 = new Single("Garcia", "Sophie", 28,169 ,Gender.Female, Image.get(30, 26, "female"), Gender.Male, "Je suis une infirmière passionnée par la médecine et qui souhaite devenir médecin un jour.", "Marseille", 15, 20, 35);
            Single s4 = new Single("Roux", "Alexandre", 42, 165,Gender.Male, Image.get(44, 42, "male"), Gender.Female, "Je suis un enseignant de mathématiques passionné par les mathématiques et j'aime partager mon savoir avec mes élèves.", "Toulouse", 25, 30, 45);
            Single s5 =  new Single("Moreau", "Laura", 27, 171,Gender.Female, Image.get(29, 25, "female"), Gender.Female, "Je suis une avocate passionnée par la défense des droits des femmes et des minorités.", "Lille", 10, 25, 40);
            Single s6 = new Single("Mercier", "Maxime", 33,175 ,Gender.Male, Image.get(35, 31, "male"), Gender.Male, "Je suis un designer passionné par la création d'interfaces utilisateur intuitives et esthétiques.", "Bordeaux", 30, 20, 35);
            Single s7 = new Single("Dupont", "Charlotte", 24, 168,Gender.Female, Image.get(26, 26, "female"), Gender.Female, "'Je suis une étudiante en journalisme passionnée par l'actualité et les nouvelles tendances de la société.'", "Nantes", 25, 18, 30);
            Single s8 = new Single("Lefebvre", "Lucas", 29, 198,Gender.Male, Image.get(31, 27, "male"), Gender.Male, "Je suis un développeur web passionné par la création de sites web dynamiques et performants.", "Strasbourg", 20, 25, 40);
            Single s9 = new Single("Bergeron", "Emma", 31, 163,Gender.Female, Image.get(33, 29, "female"), Gender.Male, "Je suis une éducatrice spécialisée passionnée par l'accompagnement des personnes en situation de handicap.", "Montpellier", 15, 25, 40);
            Single s10 = new Single("Girard", "Antoine", 38,177 ,Gender.Male, Image.get(40, 36, "male"), Gender.Female, "Je suis un entrepreneur passionné par la création et le développement d'entreprises innovantes.", "Nice", 25, 30, 45);
            try {
                s1.ExportToDb();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        //launch(args);*/
    }
}


