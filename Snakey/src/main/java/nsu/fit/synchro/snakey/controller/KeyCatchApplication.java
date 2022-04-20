package nsu.fit.synchro.snakey.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import nsu.fit.synchro.snakey.model.GameSnake;
import nsu.fit.synchro.snakey.model.Layout;
import nsu.fit.synchro.snakey.model.Model;


public class KeyCatchApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        KeyHandler keyHandler = new KeyHandler();
        primaryStage.setTitle("Trying");

        Scene scene = new Scene(new StackPane(), 2,2);
        scene.setOnKeyReleased(keyHandler);

        primaryStage.setScene(scene);
        primaryStage.show();

        Layout layout = new Layout(12, 5);
        layout.addObstacle(new nsu.fit.synchro.snakey.model.Coordinates(11,0));
        GameSnake snake = new GameSnake(new nsu.fit.synchro.snakey.model.Coordinates(0,0));
        Model model = new Model(layout, snake, keyHandler);
        Thread modelThread = new Thread(model);
        modelThread.start();
    }
}
