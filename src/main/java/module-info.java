module com.samsteenmans {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.io;
    requires java.logging;

    opens com.samsteenmans to javafx.fxml;
    exports com.samsteenmans;
}