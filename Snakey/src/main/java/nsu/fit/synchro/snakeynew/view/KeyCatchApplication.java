package nsu.fit.synchro.snakeynew.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import nsu.fit.synchro.snakeynew.controller.DirectionController;
import nsu.fit.synchro.snakeynew.model.Coordinates;
import nsu.fit.synchro.snakeynew.model.Direction;
import nsu.fit.synchro.snakeynew.model.Field;
import nsu.fit.synchro.snakeynew.model.ModelInformation;

import java.util.ArrayList;

public class KeyCatchApplication extends Application {
    Stage primaryStage;
    SnakeTimer timer;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        DirectionController controller = new DirectionController(new ModelInformation(Direction.RIGHT,
                new Coordinates(0,0), new ArrayList<>(), 12, 5, this));
        KeyHandler keyHandler = new KeyHandler(controller);
        Scene scene = new Scene(new StackPane(), 20,20);
        scene.setOnKeyReleased(keyHandler);

        primaryStage.setTitle("Trying");
        primaryStage.setScene(scene);
        primaryStage.show();

        timer = new SnakeTimer(controller);
        timer.start();
    }

    public void printGrid(Field field) {
        Viewer.printGrid(field);
    }

    public void gameOver() {
        System.out.println("Game Over!");
        timer.stop();
        primaryStage.close();
    }
}
