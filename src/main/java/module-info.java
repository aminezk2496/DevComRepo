module com.example.devcom {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.codec;
    requires commons.validator;

    requires itextpdf;
    requires virtualizedfx;
    requires org.slf4j.simple;
    requires twilio;
    requires org.slf4j;
    requires javafx.graphics;
    requires javafx.base;
    requires java.mail;
    requires restfb;
    requires javafx.web;



    exports Controllers;
    exports Entities;
    opens Controllers to javafx.fxml;
    opens Entities to javafx.fxml;


}