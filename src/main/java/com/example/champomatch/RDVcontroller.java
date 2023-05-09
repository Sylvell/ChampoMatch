package com.example.champomatch;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class RDVcontroller {

    private ArrayList<RendezVous> rdv_list = new ArrayList<RendezVous>();

    @FXML
    private Button Retu;

    @FXML
    private ListView listrdv;

    private User userConnected;

    public void setUserConnected(User userConnected) {
        this.userConnected = userConnected;
    }

    public void setUser(User user) {
        // set the user
        this.user = user;
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

    public static Date datealea() {
        Random random = new Random();

        // Date de début et date de fin
        Date startDate = new Date(2023, 1, 1);
        Date endDate = new Date(2023, 5, 9);

        // Calcul de la plage de dates
        long startMillis = startDate.getTime();
        long endMillis = endDate.getTime();
        long randomMillis = (long) (startMillis + (endMillis - startMillis) * random.nextDouble());

        // Création de la date aléatoire
        Date randomDate = new Date(randomMillis);

        return randomDate;
    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            for (int i = 0; i < 5; i++) {
                Single s1 = Recherche_pertinence.single_list.get((int) (Math.random() * Recherche_pertinence.single_list.size()));
                Single s2 = Recherche_pertinence.single_list.get((int) (Math.random() * Recherche_pertinence.single_list.size()));
                RendezVous rdv = new RendezVous(s1, s2, datealea() ,s1.getLocalisation());
                rdv_list.add(rdv);
            }

            // Affichage des rendez-vous
            for (RendezVous rdv : rdv_list) {
                listrdv.getItems().add(rdv.celib1.getFirstname() + " " + rdv.celib1.getName() + " et " + rdv.celib2.getFirstname() + " " + rdv.celib2.getName() + " le " + rdv.date.toString() + " à " + rdv.lieu);
            }
        });
    }

}
