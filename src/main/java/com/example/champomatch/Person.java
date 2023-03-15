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

public class Person {
    String name;
    int age;
    Gender gender;
    String pp;
    Gender preferedGender;
    String bio;
    String localisation;
    ArrayList<Images> images;
    Status status;
    int distance;
    int minimunAge;
    int maximunAge;
    ArrayList<Person> liked;
    ArrayList<Person> unliked;
    ArrayList<Hobbies> Hobbies;
    
}
