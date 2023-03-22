package com.example.champomatch;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class downloadImage {

    // get json from url
    public static String getJsonFromUrl(String urlStr) {
        try {
            // Specify the URL of the JSON data
            // Create a URL object
            URL url = new URL(urlStr);

            // Create an HTTP connection object
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Set the request method and headers
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Read the response from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Return the response
            return response.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    public static void downloadImage(String url, String filename) {
        try {
            java.net.URL url2 = new java.net.URL(url);
            java.io.InputStream is = url2.openStream();
            java.io.OutputStream os = new java.io.FileOutputStream(filename);
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void get(){
        // Parse the JSON data
        JSONObject json = new JSONObject(getJsonFromUrl("https://fakeface.rest/face/json?minimum_age=18"));

        // Extract data from the JSON object
        String name = json.getString("filename");
        int age = json.getInt("age");
        String imaage_url= json.getString("image_url");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Image url: " + imaage_url);

    }


    // main method
    public static void main(String[] args) {
       downloadImage image =  new downloadImage();
       image.get();
    }


}
