package com.example.champomatch;


import javafx.scene.image.Image;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

public class JdbcDao {

    // Replace below database url, username and password with your actual database credentials
    private static final String DATABASE_URL = "jdbc:mysql://montou.o2switch.net:3306/hdyx5526_ChampoMatch?serverTimezone=Europe/London&characterEncoding=UTF-8";
    private static final String DATABASE_USERNAME = "hdyx5526_ChampoMatch";
    private static final String DATABASE_PASSWORD = "ChampoMatch";
    private static final String SELECT_QUERY = "SELECT * FROM registration WHERE email_id = ? and password = ?";
    static Connection connection;

    public JdbcDao(){
        if (connection == null){
            try {
                connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static ArrayList<Single> SinglesCache;


    public boolean validate(String emailId, String password) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);
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
            if (!connection.isValid(5)) { // Check if connection is valid for 5 seconds
                // Reconnect to the database
                connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
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
            if (!connection.isValid(5)) { // Check if connection is valid for 5 seconds
                // Reconnect to the database
                connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (SinglesCache != null){
            return SinglesCache;
        }
        try {
            // SELECT single.*,GROUP_CONCAT(hobbies.name SEPARATOR ',') as hobbies FROM `hobbies` LEFT JOIN `single` ON single_id = single.id GROUP BY single.id;
            //Arrays.asList(string.split(" , "))
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT single.*,GROUP_CONCAT(hobbies.name SEPARATOR ',') as hobbies FROM `hobbies` LEFT JOIN `single` ON single_id = single.id GROUP BY single.id");
            ResultSet resultSet = preparedStatement.executeQuery();

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
                for (String hobby : resultSet.getString("hobbies").split(",")) {
                    if (StringUtils.isNotBlank(hobby)) {
                        person.addHobby(Hobbies.valueOf(hobby));
                    }
                }
                // add hobbies to the single from the hobbies table
               /* preparedStatement = connection.prepareStatement("SELECT * FROM hobbies WHERE single_id=?");
                preparedStatement.setInt(1, person.getId());
                ResultSet hobbies_resultSet = preparedStatement.executeQuery();
                while (hobbies_resultSet.next()) {
                    person.addHobby(Hobbies.valueOf(hobbies_resultSet.getString("name")));
                }
                */
                singles_list.add(person);
            }
            SinglesCache = singles_list;
            return singles_list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    // create a new single in the database

    public  void ExportSingle(Single single) throws SQLException {
        if (!connection.isValid(5)) { // Check if connection is valid for 5 seconds
            // Reconnect to the database
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        }
        // check if the single already exist by checking if the name and firstname are already in the db or if the id is already in the db
        PreparedStatement preparedStatement = null;
        String sql = "";
        int id = 0;
        ResultSet resultSet;
        String bio;
        if (single.getId() != null && single.getId() != 0) {
            id = single.getId();
        } else {
            sql = "SELECT * FROM single WHERE name=? and firstname=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, single.getName());
            preparedStatement.setString(2, single.getFirstname());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");


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
                bio = single.getBio().replaceAll("'", " ");
                preparedStatement.setString(7, bio);
                preparedStatement.setString(8, single.getLocalisation());
                preparedStatement.setString(9, single.getStatus());
                preparedStatement.setInt(10, single.getDistance());
                preparedStatement.setInt(11, single.getMinimunAge());
                preparedStatement.setInt(12, single.getMaximunAge());
                preparedStatement.setString(13, single.getPp());
                preparedStatement.setInt(14, id);
                preparedStatement.executeUpdate();
                System.out.println("single updated" + "\n" + preparedStatement);
            }
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
        bio = single.getBio().replaceAll("'", " ");
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


        // insert hobbies if they are not already in the db and delete the ones that are not in the single object
        for (Hobbies hobby : single.getHobbies()) {
            sql = "SELECT * FROM hobbies WHERE single_id=? AND name=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, single.getId());
            preparedStatement.setString(2, hobby.toString());
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                sql = "INSERT INTO hobbies VALUES (Default,?, ?,'')";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, single.getId());
                preparedStatement.setString(2, hobby.toString());
                preparedStatement.executeUpdate();
            } else if (hobby.name() != resultSet.getString("name")) {
                sql = "DELETE FROM hobbies WHERE single_id=? AND name=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, single.getId());
                preparedStatement.setString(2, hobby.toString());
                preparedStatement.executeUpdate();
            }
        }


        // insert images
        for (Image image : single.getImages()) {
            sql = "INSERT INTO images  VALUES (Default,?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, single.getId());
            preparedStatement.setString(2, image.getUrl());
            preparedStatement.executeUpdate();
        }

        // insert unliked
        for (Single unlike : single.getUnliked()) {
            sql = "INSERT INTO unlike  VALUES (Default,?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, single.getId());
            preparedStatement.setInt(2, unlike.getId());
            preparedStatement.executeUpdate();
        }

        // insert liked
        for (Single like : single.getLiked()) {
            sql = "INSERT INTO like VALUES (Default,?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, single.getId());
            preparedStatement.setInt(2, like.getId());
            preparedStatement.executeUpdate();
        }

    }


    //function to get all users from the database
    public ArrayList<User> getUsers() throws SQLException {
        if (!connection.isValid(5)) { // Check if connection is valid for 5 seconds
            // Reconnect to the database
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        }
        String sql = "Select * from registration";
        ArrayList<User> users = new ArrayList<User>();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            User user = null;
            try {
                user = new User(
                resultSet.getInt("id"),
                resultSet.getString("full_name"),
               resultSet.getString("email_id"),
                resultSet.getInt("admin")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            users.add(user);
        }
        return users;
    }

    public User getUser(String emailId) {
        try {
            if (!connection.isValid(5)) { // Check if connection is valid for 5 seconds
                // Reconnect to the database
                connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            String sql = "Select * from registration where email_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, emailId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("email_id"),
                        resultSet.getInt("admin")
                );
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void ExportUser(User user) throws SQLException {
        if (!connection.isValid(5)) { // Check if connection is valid for 5 seconds
            // Reconnect to the database
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        }
        // check if the user already exist
        if (user.getId() != null){
            // update user in the db
            String sql = "UPDATE registration set full_name=?,email_id=?,admin=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail_id());
            preparedStatement.setInt(3, user.getAdmin());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
        }else {
            // insert user in the db
            String sql = "INSERT INTO registration (full_name,email_id,admin) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail_id());
            preparedStatement.setInt(3, user.getAdmin());
            preparedStatement.executeUpdate();
        }
    }
    // get sql connection
    public Connection getConnection() throws SQLException {
        if (!connection.isValid(5)) { // Check if connection is valid for 5 seconds
            // Reconnect to the database
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        }
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    }
}
