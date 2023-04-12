package com.example.champomatch;

import java.util.ArrayList;
import java.util.HashMap;

public class Recherche_pertinence {

    public static HashMap<Hobbies, ArrayList<Integer>> dico_hobbies = new HashMap<Hobbies, ArrayList<Integer>>();
    public static HashMap<Gender, ArrayList<Integer>> dico_genders = new HashMap<Gender, ArrayList<Integer>>();

    public static HashMap<String, ArrayList<Integer>> dico_age = new HashMap<String, ArrayList<Integer>>();

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
            Gender gender = Gender.valueOf(single.getGender());
            if (!dico_genders.containsKey(gender)) {
                dico_genders.put(gender, new ArrayList<Integer>());
            }
            dico_genders.get(gender).add(single.getId());
        }



    }

    // function to fill dico age putting id of singles in the right age range
    public static void fill_dico_age() {

        for (Single single : single_list) {
            int age = single.getAge();
            if (age >= 18 && age < 25) {
                if (!dico_age.containsKey("18-24")) {
                    dico_age.put("18-24", new ArrayList<Integer>());
                }
                dico_age.get("18-24").add(single.getId());


            } else if (age >= 25 && age < 33) {
                if (!dico_age.containsKey("25-32")) {
                    dico_age.put("25-32", new ArrayList<Integer>());
                }
                dico_age.get("25-32").add(single.getId());


            } else if (age >= 33 && age < 43) {
                if (!dico_age.containsKey("33-42")) {
                    dico_age.put("33-42", new ArrayList<Integer>());
                }
                dico_age.get("33-42").add(single.getId());


            } else if (age >= 43 && age < 51) {
                if (!dico_age.containsKey("43-50")) {
                    dico_age.put("43-50", new ArrayList<Integer>());
                }
                dico_age.get("43-50").add(single.getId());


            } else if (age > 50) {
                if (!dico_age.containsKey("50+")) {
                    dico_age.put("50+", new ArrayList<Integer>());
                }
                dico_age.get("50+").add(single.getId());
            }
        }
    }




    public static void main(String[] args) {

        fill_dico_hobbies();
        fill_dico_genders();
        fill_dico_age();
        System.out.println(dico_age.get("18-24"));
        System.out.println(dico_genders.get(Gender.Female));
        System.out.println(JdbcDao.select_single());
    }
















}
