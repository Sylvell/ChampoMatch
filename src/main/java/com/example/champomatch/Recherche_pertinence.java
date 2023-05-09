package com.example.champomatch;

import java.util.*;

public class Recherche_pertinence {
    public static JdbcDao jdbcDao = new JdbcDao();

    public static HashMap<Hobbies, ArrayList<Integer>> dico_hobbies = new HashMap<Hobbies, ArrayList<Integer>>();
    public static HashMap<Gender, ArrayList<Integer>> dico_genders = new HashMap<Gender, ArrayList<Integer>>();

    public static HashMap<String, ArrayList<Integer>> dico_age = new HashMap<String, ArrayList<Integer>>();

    public static HashMap<String, ArrayList<Integer>> dico_height = new HashMap<String, ArrayList<Integer>>();

    public static ArrayList<Single> single_list = jdbcDao.select_single();

    public static HashMap<Integer,Single> single_list_id ;

    public Recherche_pertinence(){
        // fill single_list_id to keep track of the id of each single
        this.single_list_id = new HashMap<Integer, Single>();
        for (Single single : this.single_list) {
            this.single_list_id.put(single.getId(),single);
        }
    }
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
    public static Set<Integer> recherche_pert(Set<Gender> gender, Set<String> age_range, Set<String> height_range) {

        fill_dico_hobbies();
        fill_dico_genders();
        fill_dico_age();
        fill_dico_height();
        Set<Integer> result = new HashSet<Integer>();

        if (gender != null && gender.size() > 0) {
            Set<Gender> nonSelectedGenderValues = new HashSet<>(Arrays.asList(Gender.values()));
            nonSelectedGenderValues.removeAll(gender);
            for (Gender g : nonSelectedGenderValues){
                dico_genders.remove(g);
            }
        }


        if (age_range != null && age_range.size() > 0) {
            Set<String> nonSelectedAgeValues = new HashSet<>(Arrays.asList("18-24", "25-32", "33-42", "43-50", "50+"));
            nonSelectedAgeValues.removeAll(age_range);
            for (String age : nonSelectedAgeValues){
                dico_age.remove(age);
            }
        }

        if (height_range != null && height_range.size() > 0) {
            Set<String> nonSelectedHeightValues = new HashSet<>(Arrays.asList("150-", "150-160", "160-170", "170-180", "180+"));
            nonSelectedHeightValues.removeAll(height_range);
            for (String height : nonSelectedHeightValues){
                dico_height.remove(height);
            }
        }


       // convert each dico to a set of id
        Set<Integer> genders = new HashSet<Integer>();
        for (ArrayList<Integer> list : dico_genders.values()) {
            list.forEach(id-> genders.add(id));
        }

        Set<Integer> ages = new HashSet<Integer>();
        for (ArrayList<Integer> list : dico_age.values()) {
            list.forEach(id-> ages.add(id));
        }

        Set<Integer> heights = new HashSet<Integer>();
        for (ArrayList<Integer> list : dico_height.values()) {
            list.forEach(id-> heights.add(id));
        }

        result.addAll(genders);
        result.retainAll(ages);
        result.retainAll(heights);


        return result;
    }






    public static void main(String[] args) {
        Recherche_pertinence recherche_pertinence = new Recherche_pertinence();
        fill_dico_hobbies();
        fill_dico_genders();
        fill_dico_age();
        fill_dico_height();
        Gender gender = Gender.Male;
        String age_range = "42-50";
        String height_range = "160-170";
        Set<Integer> result = recherche_pert(null, new HashSet<String>(Arrays.asList("18-24")), new HashSet<String>(Arrays.asList("160-170")));
        System.out.println(result);


        for (Integer id : result) {
            System.out.println(single_list_id.get(id).toString());
        }
    }

}
