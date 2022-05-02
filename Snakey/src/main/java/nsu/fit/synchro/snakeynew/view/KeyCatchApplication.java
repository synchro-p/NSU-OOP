package nsu.fit.synchro.snakeynew.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
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

        ToggleGroup group = new ToggleGroup();
        RadioButton easyDifficulty = new RadioButton("easy difficulty");
        easyDifficulty.setToggleGroup(group);
        easyDifficulty.setSelected(true);
        easyDifficulty.setUserData(1000000000);
        RadioButton hardDifficulty = new RadioButton("hard difficulty");
        hardDifficulty.setToggleGroup(group);
        hardDifficulty.setUserData(500000000);


        Button startButton = new Button("Start game");
        startButton.setOnAction((event) -> this.startConfigured(group));

        VBox vBox = new VBox(15, startButton,easyDifficulty,hardDifficulty);
        vBox.setAlignment(Pos.BASELINE_CENTER);

        Scene setupScene = new Scene(vBox,160,120);

        primaryStage.setScene(setupScene);
        primaryStage.show();
    }

    public void startConfigured(ToggleGroup toggleGroup){
        Group group = new Group();
        Canvas canvas = new Canvas(640, 480);
        group.getChildren().add(canvas);
        drawer = new Drawer(canvas.getGraphicsContext2D());

        ArrayList<Coordinates> obstacles = new ArrayList<>();
        obstacles.add(new Coordinates(11,0));
        DirectionController controller = new DirectionController(new ModelInformation(Direction.RIGHT,
                new Coordinates(0,0), obstacles, 32, 24, this));

        KeyHandler keyHandler = new KeyHandler(controller);

        Scene scene = new Scene(group, 640,480);
        scene.setOnKeyReleased(keyHandler);

        primaryStage.setTitle("Trying");
        primaryStage.setScene(scene);
        primaryStage.show();

        timer = new SnakeTimer(controller, (Integer)toggleGroup.getSelectedToggle().getUserData());
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
