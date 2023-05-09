package com.example.champomatch;

import java.util.ArrayList;

public class RDVcontroller {

    private ArrayList<RendezVous> rdv_list = new ArrayList<RendezVous>();


    public void initialize(){

        // display all rendez-vous from rdv_list
        for (RendezVous rdv : rdv_list) {
            System.out.println(rdv.celib1.getName() + " " + rdv.celib2.getName() + " " + rdv.date + " " + rdv.lieu + " " + rdv.state);
        }

    }
}
