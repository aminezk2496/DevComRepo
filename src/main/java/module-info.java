module com.devcom.discovertnproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.devcom.discovertnproject to javafx.fxml;
    exports com.devcom.discovertnproject;
    exports com.devcom.discovertnproject.controllers;
    opens com.devcom.discovertnproject.controllers to javafx.fxml;
}