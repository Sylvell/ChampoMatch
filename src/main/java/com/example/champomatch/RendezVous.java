package com.example.champomatch;

import java.util.Date;

enum State{
    en_attente,
    bien_passe,
    mal_passe,

}
public class RendezVous {

    public Single celib1;
    public Single celib2;
    public Date date;

    public String lieu;

    public State state = State.en_attente;


    public RendezVous(Single s1, Single s2, Date date, String lieu){
        this.celib1= s1;
        this.celib2= s2;
        this.date= date;
        this.lieu= lieu;



    }






}
