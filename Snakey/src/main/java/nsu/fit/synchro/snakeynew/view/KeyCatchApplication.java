package nsu.fit.synchro.snakeynew.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import nsu.fit.synchro.snakeynew.controller.DirectionController;
import nsu.fit.synchro.snakeynew.model.*;

import java.util.ArrayList;
import java.util.Timer;

public class KeyCatchApplication extends Application {
    Timer timer;
    Stage primaryStage;

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

        timer = new Timer();
        timer.schedule(new SnakeTimerTask(controller), 2000, 2000);
    }

    public void printGrid(Field field) {
        Viewer.printGrid(field);
    }

    public void gameOver() throws Exception {
        System.out.println("Game Over!");
        timer.cancel();
        this.stop();
        // fixme Code below throws exception, game cannot finish on its own
        // primaryStage.close();
    }
}
