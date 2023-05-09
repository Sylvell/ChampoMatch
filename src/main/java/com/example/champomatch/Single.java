package com.example.champomatch;

import javafx.scene.image.Image;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;

enum Gender {
    Male,
    Female,
    Other
}

enum Hobbies {
    Default,
    Sport,
    Sleep,
    Gaming,
    Plage,
    Cooking,
    Shopping,
    Cinema,
    Reading,
    Music,
    Dance,
    Travel,
    Animals,
    Photography,
    Drawing,
    Painting,
    Writing,
    Yoga,
    Meditation,
    Hiking,
    Fishing,
    Camping,
    Gardening,
    Swimming,
    Running,
    Cycling,
    Climbing,
    Skiing,
    Surfing,
    Eating,
    Drinking,
    Partying,
    Singing,
    Playing,
    Watching,
    Coding,
    Learning,
    Teaching,
    Helping,
    Volunteering,
    Working,
    Studying,
}
enum Status {
    Single,
    Married,
    Divorced,
}
public class Single {
    public Integer id = null;
    String name;
    String firstname;
    int age;
    int height;
    Gender gender;
    String pp = "file:@images/defaultProfileImage.png";
    Gender preferedGender;
    String bio;
    String localisation;
    ArrayList<Image> images = new ArrayList<Image>();
    int distance;
    int minimunAge;
    int maximunAge;
    ArrayList<Single> liked = new ArrayList<Single>();
    ArrayList<Single> unliked = new ArrayList<Single>();
    ArrayList<Single> candidates = new ArrayList<Single>();
    ArrayList<Hobbies> hobbies = new ArrayList<Hobbies>();
    boolean isAlone = true;
    Status status = Status.Single;
    // Constructor


    public Single(String name, String firstname, int age, int height, Gender gender, String pp, Gender preferedGender, String bio, String localisation, int distance, int minimunAge, int maximunAge) {
        this.name = name;
        this.firstname = firstname;
        this.age = age;
        this.height = height;
        this.gender = gender;
        if (pp != null && ! pp.equals("http://champomatch.hdyx5526.odns.fr/ChampoMatch/images/")){
            this.pp = pp;
        }
        this.preferedGender = preferedGender;
        // encoding bio to utf-8
        byte[] raw = bio.getBytes(StandardCharsets.UTF_8);
        this.bio = new String(raw, StandardCharsets.UTF_8);
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
        if (pp != null && ! pp.equals("http://champomatch.hdyx5526.odns.fr/ChampoMatch/images/")){
            this.pp = pp;
        }
        this.preferedGender = preferedGender;
        // encoding bio to utf-8
        byte[] raw = bio.getBytes(StandardCharsets.UTF_8);
        this.bio = new String(raw, StandardCharsets.UTF_8);

        this.localisation = localisation;
        this.distance = distance;
        this.minimunAge = minimunAge;
        this.maximunAge = maximunAge;
        this.isAlone=isAlone;
    }

    // constructor for the database


    public Single(int id){
        this.id = id;
    }
    public Single(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getGender() {
        return gender.toString();
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

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
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

    public boolean isAlone() {
        return isAlone;
    }

    public void setAlone(boolean alone) {
        isAlone = alone;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public void addHobby(Hobbies hobby) {
        hobbies.add(hobby);
    }
public String getStatus() {
        return status.toString();
    }
    public String getPreferredGender() {
        return preferedGender.toString();
    }
    public void ExportToDb() throws SQLException {
        JdbcDao dao = new JdbcDao();
        dao.ExportSingle(this);
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", gender=" + gender +
                ", pp='" + pp + '\'' +
                ", preferedGender=" + preferedGender +
                ", bio='" + bio + '\'' +
                ", localisation='" + localisation + '\'' +
                ", images=" + images +
                ", distance=" + distance +
                ", minimunAge=" + minimunAge +
                ", maximunAge=" + maximunAge +
                ", isAlone=" + isAlone +
                ", status=" + status;
    }
}
