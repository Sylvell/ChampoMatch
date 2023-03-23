package com.example.champomatch;

import java.util.ArrayList;

enum Gender {
    Male,
    female,
    NonBinary,
    Other
  }
enum Status {
    Online,
    Offline,
    Hide,
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
    int age;
    Gender gender;
    String pp;
    Gender preferedGender;
    String bio;
    String localisation;
    ArrayList<Images> images = new ArrayList<Images>();
    Status status;
    int distance;
    int minimunAge;
    int maximunAge;
    ArrayList<Single> liked = new ArrayList<Single>();
    ArrayList<Single> unliked = new ArrayList<Single>();
    ArrayList<Single> candidates = new ArrayList<Single>();
    ArrayList<Hobbies> hobbies = new ArrayList<Hobbies>();
    boolean isAlone = true;

    // Constructor


    public Single(String name, int age, Gender gender, String pp, Gender preferedGender, String bio, String localisation, Status status, int distance, int minimunAge, int maximunAge) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.pp = pp;
        this.preferedGender = preferedGender;
        this.bio = bio;
        this.localisation = localisation;
        this.status = status;
        this.distance = distance;
        this.minimunAge = minimunAge;
        this.maximunAge = maximunAge;
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
}
