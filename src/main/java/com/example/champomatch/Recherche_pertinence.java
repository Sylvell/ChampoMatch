package com.example.champomatch;

import java.util.ArrayList;
import java.util.HashMap;

public class Recherche_pertinence {

    public static HashMap<Hobbies, ArrayList<Integer>> dico_hobbies = new HashMap<Hobbies, ArrayList<Integer>>();
    public static HashMap<Gender, ArrayList<Integer>> dico_genders = new HashMap<Gender, ArrayList<Integer>>();

    public static ArrayList<Single> single_list = JdbcDao.select_single();

    public static void fill_dico_hobbies() {
        for (Single single : single_list) {
            for (Hobbies hobby : single.getHobbies()) {
                if (!dico_hobbies.containsKey(hobby)) {
                    dico_hobbies.put(hobby, new ArrayList<Integer>());
                }
                dico_hobbies.get(hobby).add(single.getId());
            }
        }

    }

    public static void fill_dico_genders() {

        for (Single single : single_list) {
            Gender gender = single.getGender();
            if (!dico_genders.containsKey(gender)) {
                dico_genders.put(gender, new ArrayList<Integer>());
            }
            dico_genders.get(gender).add(single.getId());
        }



    }




    public static void main(String[] args) {

        fill_dico_hobbies();
        fill_dico_genders();
        System.out.println(dico_hobbies.get(Hobbies.Sport));
        System.out.println(dico_genders.get(Gender.Female));
        System.out.println(JdbcDao.select_single());
    }
















}
