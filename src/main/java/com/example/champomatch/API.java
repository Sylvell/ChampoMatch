package com.example.champomatch;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 record Coordinates(float Latitude, float Longitude) {
}


public class API {
    public Coordinates  getCityCoordinates(String city) {
        try{
        String url = "https://api.api-ninjas.com/v1/city?name=" + city + "&country=FR";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-Api-Key", "kDsVrXGI4qbp9WU1qI85sw==QJZd2pQLnAE6NKWP");
        con.setRequestProperty("Content-Type", "application/json");

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read the response from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


            //Parse the response
            JSONObject json = new JSONObject(response.toString().replace("[", "").replace("]", ""));
            return new Coordinates(json.getFloat("latitude"), json.getFloat("longitude"));
        }}
        catch(Exception e){
            return null;
        }
        return null;
    }
    public static double distance(Coordinates city1, Coordinates city2) {
        try{
        double lat1 = city1.Latitude();
        double lon1 = city1.Longitude();
        double lat2 = city2.Latitude();
        double lon2 = city2.Longitude();

        final int R = 6371; // Radius of the earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // Distance in km
        return distance;
        }catch(Exception e){
            return -1;
        }
    }
    public static void main(String[] args) {
        API api = new API();
        Coordinates city1 = api.getCityCoordinates("Paris");
        Coordinates city2 = api.getCityCoordinates("Albi");
        System.out.println(distance(city1, city2));
    }
}
