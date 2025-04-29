module com.unam.aragon.game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.unam.aragon.game to javafx.fxml;
    exports com.unam.aragon.game;
}