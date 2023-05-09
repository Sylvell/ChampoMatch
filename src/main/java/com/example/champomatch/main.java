package com.example.champomatch;

import java.sql.SQLException;
import java.util.Random;

public class main {

    public static void main(String[] args) {
        JdbcDao dao = new JdbcDao();
        for (Single s : dao.select_single()) {
            if (s.getHobbies().isEmpty()){
                // randomize Hobbies number between 1 and 5
                Random rand = new Random();
                int nbreHobbies = rand.nextInt(5) + 1;
                for (int i = 0; i < nbreHobbies; i++) {
                    // get random Hobby
                    Hobbies[] values = Hobbies.values();
                    Hobbies hobby = values[(int) (Math.random() * values.length)];
                    s.addHobby(hobby);
                }
                try {
                    s.ExportToDb();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }
        }
    }
}

