module com.example.courseworkitfu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires lombok;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.courseworkitfu to javafx.fxml;
    exports com.example.courseworkitfu;
    opens com.example.courseworkitfu.fxControllers to javafx.fxml;
    exports com.example.courseworkitfu.fxControllers;
    opens com.example.courseworkitfu.model to org.hibernate.orm.core;
    exports com.example.courseworkitfu.model;
}