module nsu.fit.synchro.snakeynew {
    requires javafx.controls;
    requires javafx.fxml;

    opens nsu.fit.synchro.snakeynew.view to javafx.fxml;
    exports nsu.fit.synchro.snakeynew.controller;
    exports nsu.fit.synchro.snakeynew.model;
    exports nsu.fit.synchro.snakeynew.view;
    opens nsu.fit.synchro.snakeynew.controller to javafx.fxml;
}