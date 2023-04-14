package com.example.champomatch;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.CacheHint;

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