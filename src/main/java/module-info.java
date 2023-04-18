module com.example.champomatch {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires org.json;
    requires java.sql;
    requires junit;
    requires com.fasterxml.jackson.databind;
    requires org.apache.commons.lang3;
    requires javafaker;
    opens com.example.champomatch to javafx.fxml;
    exports com.example.champomatch;

}