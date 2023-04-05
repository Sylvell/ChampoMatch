package com.example.champomatch;


import java.sql.*;
import java.util.ArrayList;

public class JdbcDao {

    // Replace below database url, username and password with your actual database credentials
    private static final String DATABASE_URL = "jdbc:mysql://montou.o2switch.net:3306/hdyx5526_ChampoMatch?serverTimezone=Europe/London";
    private static final String DATABASE_USERNAME = "hdyx5526_ChampoMatch";
    private static final String DATABASE_PASSWORD = "ChampoMatch";
    private static final String SELECT_QUERY = "SELECT * FROM registration WHERE email_id = ? and password = ?";

    public boolean validate(String emailId, String password) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return false;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    // function that convert a String to a Gender enum
    static Gender string_to_gender(String s){

        switch (s){

            case "female":
                return Gender.Female;

            case "male":
                return Gender.Male;

            case "other":
                return Gender.Other;


        }
        return null;
    }

    // function to get Hobbies with sql select
    public static void  select_hobbies() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM hobbies");
            ResultSet resultSet = preparedStatement.executeQuery("SELECT * FROM hobbies");


            while (resultSet.next()) {


                Single celib = new Single(resultSet.getInt("single_id"));
                celib.addHobby(Hobbies.valueOf(resultSet.getString("name")));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        // function to execute an sql select and return fetch result in an array

    public static ArrayList<Single> select_single() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM single, images, hobbies WHERE single.pp_id = images.single_id");
            ResultSet resultSet = preparedStatement.executeQuery("SELECT * FROM single, images, hobbies WHERE single.pp_id = images.single_id");

            ArrayList<Single> singles_list = new ArrayList<Single>();
            while (resultSet.next()) {
                Single person = new Single(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("firstname"),
                        resultSet.getInt("age"),
                        resultSet.getInt("height"),
                        string_to_gender(resultSet.getString("gender")),
                        resultSet.getString("url"),
                        string_to_gender(resultSet.getString("prefered_gender")),
                        resultSet.getString("bio"),
                        resultSet.getString("localisation"),
                        resultSet.getInt("distance"),
                        resultSet.getInt("minimun_age"),
                        resultSet.getInt("maximun_age"),
                        resultSet.getBoolean("isAlone"));


                singles_list.add(person);
            }

            return singles_list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    // create a new single in the database

    public  void ExportSingle(Single single) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        // insert hobbies
        for (Hobbies hobby: single.getHobbies()) {
            String sql = "INSERT INTO hobbies (single_id, hobby) VALUES (single.getID(), hobby.toString())";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
        }

        // insert images
        for (Images image: single.getImages()) {
            String sql = "INSERT INTO images (single_id, url) VALUES (single.getID(), image.getUrl())";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
        }

        // insert unliked
        for (Single unlike: single.getUnliked()) {
            String sql = "INSERT INTO unlike (single_id, unlike_id) VALUES (single.getID(), unlike.getID())";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
        }

        // insert liked
        for (Single like: single.getLiked()) {
            String sql = "INSERT INTO like (single_id, like_id) VALUES (single.getID(), like.getID())";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
        }

        // insert single
        String sql = "INSERT INTO single (id,name,firstname,age,height,gender,bio,localisation,status,distance,minimun_age,maximun_age,is_alone) VALUES (single.getId(), single.getName(), single.getFirstname(), single.getAge(), single.getHeight(), single.getGender(), single.getBio(), single.getLocalisation(), single.getStatus(), single.getDistance(), single.getMinimun_age(), single.getMaximun_age(), single.isAlone())";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate(sql);
    }

}
