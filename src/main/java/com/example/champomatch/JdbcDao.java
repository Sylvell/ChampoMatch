package com.example.champomatch;


import javafx.scene.image.Image;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;

public class JdbcDao {

    // Replace below database url, username and password with your actual database credentials
    private static final String DATABASE_URL = "jdbc:mysql://montou.o2switch.net:3306/hdyx5526_ChampoMatch?serverTimezone=Europe/London&characterEncoding=UTF-8";
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

            case "Female":
                return Gender.Female;

            case "Male":
                return Gender.Male;

            case "Other":
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM single");
            ResultSet resultSet = preparedStatement.executeQuery("SELECT * FROM single");

            ArrayList<Single> singles_list = new ArrayList<Single>();
            while (resultSet.next()) {
                Single person = new Single(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("firstname"),
                        resultSet.getInt("age"),
                        resultSet.getInt("height"),
                        string_to_gender(resultSet.getString("gender")),
                        resultSet.getString("pp"),
                        string_to_gender(resultSet.getString("preferred_gender")),
                        resultSet.getString("bio"),
                        resultSet.getString("localisation"),
                        resultSet.getInt("distance"),
                        resultSet.getInt("minimum_age"),
                        resultSet.getInt("maximum_age"),
                        resultSet.getBoolean("is_alone"));


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
        // check if the single already exist
        String sql = "SELECT * FROM single WHERE name=? and firstname=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, single.getName());
        preparedStatement.setString(2, single.getFirstname());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {

            // store the id of the select
            int id = resultSet.getInt("id");
            // update single in the db
            sql = "UPDATE single set name=?,firstname=?,age=?,height=?,gender=?,preferred_gender=?,bio=?,localisation=?,status=?,distance=?,minimum_age=?,maximum_age=?,pp=? WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, single.getName());
            preparedStatement.setString(2, single.getFirstname());
            preparedStatement.setInt(3, single.getAge());
            preparedStatement.setInt(4, single.getHeight());
            preparedStatement.setString(5, single.getGender());
            preparedStatement.setString(6, single.getPreferredGender());
            // escape single quote
            String bio = single.getBio().replaceAll("'", " ");
            preparedStatement.setString(7, bio);
            preparedStatement.setString(8, single.getLocalisation());
            preparedStatement.setString(9, single.getStatus());
            preparedStatement.setInt(10, single.getDistance());
            preparedStatement.setInt(11, single.getMinimunAge());
            preparedStatement.setInt(12, single.getMaximunAge());
            preparedStatement.setString(13, single.getPp());
            preparedStatement.setInt(14, id);
            preparedStatement.executeUpdate();
            return;
        }

        // insert single
         sql = "INSERT INTO single (name,firstname,age,height,gender,preferred_gender,bio,localisation,status,distance,minimum_age,maximum_age,pp) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
         preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, single.getName());
        preparedStatement.setString(2, single.getFirstname());
        preparedStatement.setInt(3, single.getAge());
        preparedStatement.setInt(4, single.getHeight());
        preparedStatement.setString(5, single.getGender());
        preparedStatement.setString(6, single.getPreferredGender());
        // escape single quote
        String bio = single.getBio().replaceAll("'", " ");
        preparedStatement.setString(7, bio);
        preparedStatement.setString(8, single.getLocalisation());
        preparedStatement.setString(9, single.getStatus());
        preparedStatement.setInt(10, single.getDistance());
        preparedStatement.setInt(11, single.getMinimunAge());
        preparedStatement.setInt(12, single.getMaximunAge());
        preparedStatement.setString(13, single.getPp());
        //System.out.println(preparedStatement.toString());
        preparedStatement.executeUpdate();

        // get the id of the single
        sql = "SELECT id FROM single WHERE name = ? AND firstname = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, single.getName());
        preparedStatement.setString(2, single.getFirstname());
         resultSet = preparedStatement.executeQuery();
        resultSet.next();
        single.setId(resultSet.getInt("id"));


        // insert hobbies
        for (Hobbies hobby: single.getHobbies()) {
             sql = "INSERT INTO hobbies  VALUES (Default,?, ?)";
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, single.getId());
            preparedStatement.setString(2, hobby.toString());
            preparedStatement.executeUpdate();
        }

        // insert images
        for (Image image: single.getImages()) {
             sql = "INSERT INTO images  VALUES (Default,?, ?)";
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, single.getId());
            preparedStatement.setString(2, image.getUrl());
            preparedStatement.executeUpdate();
        }

        // insert unliked
        for (Single unlike: single.getUnliked()) {
             sql = "INSERT INTO unlike  VALUES (Default,?, ?)";
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, single.getId());
            preparedStatement.setInt(2, unlike.getId());
            preparedStatement.executeUpdate();
        }

        // insert liked
        for (Single like: single.getLiked()) {
             sql = "INSERT INTO like VALUES (Default,?, ?)";
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, single.getId());
            preparedStatement.setInt(2, like.getId());
            preparedStatement.executeUpdate();
        }

    }

}
