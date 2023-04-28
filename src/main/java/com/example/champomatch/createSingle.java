package com.example.champomatch;
import com.github.javafaker.Faker;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Random;


import static java.lang.System.exit;

public class createSingle {
    public static void main(String[] args) {
        int nbresingles =1000;
        JdbcDao dao = new JdbcDao();
        Faker faker = new Faker(new Locale("fr"));
        downloadImage image =  new downloadImage();
        Random random=new Random();
        for (int i = 0; i < nbresingles; i++) {
            // create single
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String city = faker.address().city();
            String bio = faker.lorem().sentence(10);
            int age = faker.number().numberBetween(18, 100);
            int maxAge = faker.number().numberBetween(age, 100);
            int minAge = faker.number().numberBetween(18, age);
            int height = faker.number().numberBetween(150, 215);
            int distance = faker.number().numberBetween(100, 1500);


            // get random Gender
            Gender[] values=Gender.values();
            Gender gender = values[random.nextInt(values.length)];
            Gender prefferedGender = values[random.nextInt(values.length)];
            String imagelink = null;

            if (gender != Gender.Other){
                imagelink = image.get(age, String.valueOf(gender));
            }else{
                imagelink = "file:@images/OtherGender.png";
            }

            // create single
            Single s = new Single(lastName,firstName,age,height,gender,imagelink,prefferedGender,bio,city,distance,minAge,maxAge);
            System.out.println(s);
            try {
                s.ExportToDb();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
