package nsu.fit.synchro.snakeynew.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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
    Drawer drawer;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        ArrayList<Coordinates> obstacles = new ArrayList<>();
        obstacles.add(new Coordinates(11,0));
        DirectionController controller = new DirectionController(new ModelInformation(Direction.RIGHT,
                new Coordinates(0,0), obstacles, 12, 5, this));

        KeyHandler keyHandler = new KeyHandler(controller);

        Group group = new Group();
        Canvas canvas = new Canvas(640, 480);
        group.getChildren().add(canvas);
        drawer = new Drawer(canvas.getGraphicsContext2D());

        Scene scene = new Scene(group, 640,480);
        scene.setOnKeyReleased(keyHandler);

        primaryStage.setTitle("Trying");
        primaryStage.setScene(scene);
        primaryStage.show();

        timer = new SnakeTimer(controller);
        timer.start();
    }

    public void drawGrid(Field field) {
        drawer.drawGrid(field);
    }

    public void gameOver() {
        System.out.println("Game Over!");
        timer.stop();
        primaryStage.close();
    }
}
