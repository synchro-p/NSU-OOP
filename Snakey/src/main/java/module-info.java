module nsu.fit.synchro.snakey.controller {
    requires javafx.controls;
    requires javafx.fxml;

    opens nsu.fit.synchro.snakey.controller to javafx.fxml;
    exports nsu.fit.synchro.snakey.controller;
}