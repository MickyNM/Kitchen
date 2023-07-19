module com.example.kuhinja {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.kuhinja to javafx.fxml;
    exports com.example.kuhinja;
    exports com.example.kuhinja.podaci;
    opens com.example.kuhinja.podaci to javafx.fxml;
}