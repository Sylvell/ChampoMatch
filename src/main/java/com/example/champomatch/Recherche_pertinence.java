package com.example.champomatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Recherche_pertinence {

    public static HashMap<Hobbies, ArrayList<Integer>> dico_hobbies = new HashMap<Hobbies, ArrayList<Integer>>();
    public static HashMap<Gender, ArrayList<Integer>> dico_genders = new HashMap<Gender, ArrayList<Integer>>();

    public static HashMap<String, ArrayList<Integer>> dico_age = new HashMap<String, ArrayList<Integer>>();

    // dico to store the id of singles with a height in the right range
    public static HashMap<String, ArrayList<Integer>> dico_height = new HashMap<String, ArrayList<Integer>>();

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

    public static void fill_dico_height() {
        for (Single single : single_list) {
            int height = single.getHeight();
            if (height < 150) {
                if (!dico_height.containsKey("150-")) {
                    dico_height.put("150-", new ArrayList<Integer>());
                }
                dico_height.get("150-").add(single.getId());
            } else if (height >= 150 && height < 160) {
                if (!dico_height.containsKey("150-160")) {
                    dico_height.put("150-160", new ArrayList<Integer>());
                }
                dico_height.get("150-160").add(single.getId());
            } else if (height >= 160 && height < 170) {
                if (!dico_height.containsKey("160-170")) {
                    dico_height.put("160-170", new ArrayList<Integer>());
                }
                dico_height.get("160-170").add(single.getId());
            } else if (height >= 170 && height < 180) {
                if (!dico_height.containsKey("170-180")) {
                    dico_height.put("170-180", new ArrayList<Integer>());
                }
                dico_height.get("170-180").add(single.getId());
            } else if (height >= 180) {
                if (!dico_height.containsKey("180+")) {
                    dico_height.put("180+", new ArrayList<Integer>());
                }
                dico_height.get("180+").add(single.getId());
            }
        }
    }
    public static Set<Integer> recherche_pert(Gender gender, String age_range, String height_range) {

        Set<Integer> result = new HashSet<Integer>();

        /* ArrayList<Integer> hobby_ids = new ArrayList<Integer>();
        for (Hobbies hobby : hobbies) {
            if (dico_hobbies.containsKey(hobby)) {
                hobby_ids.addAll(dico_hobbies.get(hobby));
            }
        } */

        Set<Integer> gender_ids = new HashSet<Integer>();
        if (dico_genders.containsKey(gender)) {
            gender_ids.addAll(dico_genders.get(gender));
        }

        Set<Integer> age_ids = new HashSet<Integer>();
        if (dico_age.containsKey(age_range)) {
            age_ids.addAll(dico_age.get(age_range));
        }

        Set<Integer> height_ids = new HashSet<Integer>();
        if (dico_height.containsKey(height_range)) {
            height_ids.addAll(dico_height.get(height_range));
        }

        //result.addAll(hobby_ids);
        result.addAll(gender_ids);
        result.retainAll(age_ids);
        result.retainAll(height_ids);

        return result;
    }




    public static void main(String[] args) {

        fill_dico_hobbies();
        fill_dico_genders();
        fill_dico_age();
        fill_dico_height();
        //ArrayList<Hobbies> hobbies = new ArrayList<Hobbies>();
        //hobbies.add(Hobbies.Cooking);
        Gender gender = Gender.Female;
        String age_range = "18-24";
        String height_range = "160-170";
        Set<Integer> result = recherche_pert(gender, age_range, height_range);
        System.out.println(result);
        //System.out.println(JdbcDao.select_single());

    }


}
