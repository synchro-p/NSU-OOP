package nsu.fit.synchro.snakey.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import nsu.fit.synchro.snakey.model.Direction;
import nsu.fit.synchro.snakey.model.Field;
import nsu.fit.synchro.snakey.model.GameSnake;
import nsu.fit.synchro.snakey.model.Model;
import nsu.fit.synchro.snakey.model.Coordinates;

public class KeyCatchApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        KeyHandler keyHandler = new KeyHandler();

        Scene scene = new Scene(new StackPane(), 2,2);
        scene.setOnKeyReleased(keyHandler);

        primaryStage.setTitle("Trying");
        primaryStage.setScene(scene);
        primaryStage.show();

        Field field = new Field(12, 5);
        field.addObstacle(new nsu.fit.synchro.snakey.model.Coordinates(11,0));
        GameSnake snake = new GameSnake(new Coordinates(0,0), Direction.RIGHT);
        Model model = new Model(field, snake, keyHandler);
        Thread modelThread = new Thread(model);
        modelThread.start();
    }
}
