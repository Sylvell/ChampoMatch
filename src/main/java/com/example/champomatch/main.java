package com.example.champomatch;

import java.sql.SQLException;

public class main {

    public static void main(String[] args) {
        JdbcDao dao = new JdbcDao();
        for (Single s : dao.select_single()) {
                // randomize Hobbies number between 1 and 5
                int nbreHobbies = (int) (Math.random() * 5) + 1;
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

