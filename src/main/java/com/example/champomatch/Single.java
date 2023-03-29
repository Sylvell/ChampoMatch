package com.example.champomatch;

import java.util.ArrayList;

enum Gender {
    Male,
    Female,
    Other
}

enum Hobbies {
    Sport,
    Sleep,
    Gaming,
    Plage,
    Cooking,
}

public class Single {
    public Integer id = null;
    String name;
    String firstname;
    int age;
    int height;
    Gender gender;
    String pp;
    Gender preferedGender;
    String bio;
    String localisation;
    ArrayList<Images> images = new ArrayList<Images>();
    int distance;
    int minimunAge;
    int maximunAge;
    ArrayList<Single> liked = new ArrayList<Single>();
    ArrayList<Single> unliked = new ArrayList<Single>();
    ArrayList<Single> candidates = new ArrayList<Single>();
    ArrayList<Hobbies> hobbies = new ArrayList<Hobbies>();
    boolean isAlone = true;

    // Constructor


    public Single(String name, String firstname, int age, int height, Gender gender, String pp, Gender preferedGender, String bio, String localisation, int distance, int minimunAge, int maximunAge) {
        this.name = name;
        this.firstname = firstname;
        this.age = age;
        this.height = height;
        this.gender = gender;
        this.pp = pp;
        this.preferedGender = preferedGender;
        this.bio = bio;
        this.localisation = localisation;
        this.distance = distance;
        this.minimunAge = minimunAge;
        this.maximunAge = maximunAge;
    }
    public Single(int id,String name, String firstname, int age, int height, Gender gender, String pp, Gender preferedGender, String bio, String localisation, int distance, int minimunAge, int maximunAge,boolean isAlone) {
        this.id=id;
        this.name = name;
        this.firstname = firstname;
        this.age = age;
        this.height = height;
        this.gender = gender;
        this.pp = pp;
        this.preferedGender = preferedGender;
        this.bio = bio;
        this.localisation = localisation;
        this.distance = distance;
        this.minimunAge = minimunAge;
        this.maximunAge = maximunAge;
        this.isAlone=isAlone;
    }

    public ArrayList<Images> getImages() {
        return images;
    }

    public void setImages(ArrayList<Images> images) {
        this.images = images;
    }

    public ArrayList<Single> getLiked() {
        return liked;
    }

    public void setLiked(ArrayList<Single> liked) {
        this.liked = liked;
    }

    public ArrayList<Single> getUnliked() {
        return unliked;
    }

    public void setUnliked(ArrayList<Single> unliked) {
        this.unliked = unliked;
    }

    public ArrayList<Single> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<Single> candidates) {
        this.candidates = candidates;
    }

    public ArrayList<Hobbies> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<Hobbies> hobbies) {
        this.hobbies = hobbies;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public Gender getPreferedGender() {
        return preferedGender;
    }

    public void setPreferedGender(Gender preferedGender) {
        this.preferedGender = preferedGender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getMinimunAge() {
        return minimunAge;
    }

    public void setMinimunAge(int minimunAge) {
        this.minimunAge = minimunAge;
    }

    public int getMaximunAge() {
        return maximunAge;
    }

    public void setMaximunAge(int maximunAge) {
        this.maximunAge = maximunAge;
    }

    public boolean isAlone() {
        return isAlone;
    }

    public void setAlone(boolean alone) {
        isAlone = alone;
    }

    public void generateSingle() {
        downloadImage Image = new downloadImage();

        new Single("Martin", "Léa", 22, 165,Gender.Female, Image.get(24, 20, "female"), Gender.Male, "Je suis une jeune étudiante passionnée par les réseaux sociaux et l'influence qu'ils ont sur la société.", "Paris", 10, 18, 30);
        new Single("Dubois", "Pierre", 35, 189,Gender.Male, Image.get(37, 33, "male"), Gender.Female, "Je suis un ingénieur logiciel talentueux passionné par la programmation et les nouvelles technologies.", "Lyon", 20, 25, 40);
        new Single("Garcia", "Sophie", 28,169 ,Gender.Female, Image.get(30, 26, "female"), Gender.Male, "Je suis une infirmière passionnée par la médecine et qui souhaite devenir médecin un jour.", "Marseille", 15, 20, 35);
        new Single("Roux", "Alexandre", 42, 165,Gender.Male, Image.get(44, 42, "male"), Gender.Female, "Je suis un enseignant de mathématiques passionné par les mathématiques et j'aime partager mon savoir avec mes élèves.", "Toulouse", 25, 30, 45);
        new Single("Moreau", "Laura", 27, 171,Gender.Female, Image.get(29, 25, "female"), Gender.Female, "Je suis une avocate passionnée par la défense des droits des femmes et des minorités.", "Lille", 10, 25, 40);
        new Single("Mercier", "Maxime", 33,175 ,Gender.Male, Image.get(35, 31, "male"), Gender.Male, "Je suis un designer passionné par la création d'interfaces utilisateur intuitives et esthétiques.", "Bordeaux", 30, 20, 35);
        new Single("Dupont", "Charlotte", 24, 168,Gender.Female, Image.get(26, 26, "female"), Gender.Female, "'Je suis une étudiante en journalisme passionnée par l'actualité et les nouvelles tendances de la société.'", "Nantes", 25, 18, 30);
        new Single("Lefebvre", "Lucas", 29, 198,Gender.Male, Image.get(31, 27, "male"), Gender.Male, "Je suis un développeur web passionné par la création de sites web dynamiques et performants.", "Strasbourg", 20, 25, 40);
        new Single("Bergeron", "Emma", 31, 163,Gender.Female, Image.get(33, 29, "female"), Gender.Male, "Je suis une éducatrice spécialisée passionnée par l'accompagnement des personnes en situation de handicap.", "Montpellier", 15, 25, 40);
        new Single("Girard", "Antoine", 38,177 ,Gender.Male, Image.get(40, 36, "male"), Gender.Female, "Je suis un entrepreneur passionné par la création et le développement d'entreprises innovantes.", "Nice", 25, 30, 45);
    }
}
