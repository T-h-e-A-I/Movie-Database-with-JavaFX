module com.example.javafxdemo2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;

    opens com.example.javafxdemo2 to javafx.fxml;
    exports com.example.javafxdemo2;
    exports server;
    opens server to javafx.fxml;
}