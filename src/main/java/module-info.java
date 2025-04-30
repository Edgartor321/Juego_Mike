module com.unam.aragon.game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.unam.aragon to javafx.fxml;
    exports com.unam.aragon.modelo;
    exports com.unam.aragon.arranque;
    opens com.unam.aragon.arranque to javafx.fxml;
}