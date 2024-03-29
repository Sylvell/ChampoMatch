package com.example.champomatch;

import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class ImageLoader {

    private static final Map<String, Image> cache = new HashMap<>();

    public ImageLoader(ImageView imageView, String url) {
        setImage(imageView,url);
    }


    public static Image loadImage(String url) {
        if (cache.containsKey(url)) {
            return cache.get(url);
        }
        if (url =="http://champomatch.hdyx5526.odns.fr/ChampoMatch/images/"){
            url = "file:@images/defaultProfileImage.png";
        }
        Image image = new Image(url, true);
        cache.put(url, image);

        return image;
    }

    public static void setImage(ImageView imageView, String url) {
        imageView.setImage(loadImage(url));
        imageView.setCache(true);
        imageView.setCacheHint(CacheHint.SPEED);
    }
}