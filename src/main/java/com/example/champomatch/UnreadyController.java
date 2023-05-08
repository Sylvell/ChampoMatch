package com.example.champomatch;

import javafx.application.Platform;

public class UnreadyController {
    public void initialize() {
        Platform.runLater(() -> {
            System.out.println("UnreadyController");
        });
        }
}
