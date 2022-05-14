package nsu.fit.synchro.snakeynew.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
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
    Label scoreLabel;

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
        Canvas canvas = new Canvas(480, 480);
        scoreLabel = new Label("Score: ");

        GridPane layout = new GridPane();
        layout.add(canvas, 0,0,1,32);
        layout.add(scoreLabel, 1, 0, 1,1);

        drawer = new Drawer(canvas.getGraphicsContext2D());

        ArrayList<Coordinates> obstacles = new ArrayList<>();
        obstacles.add(new Coordinates(11,0));
        DirectionController controller = new DirectionController(new ModelInformation(Direction.RIGHT,
                new Coordinates(0,0), obstacles, 24, 24, this));

        KeyHandler keyHandler = new KeyHandler(controller);

        Scene scene = new Scene(layout, 640,480);
        scene.setOnKeyReleased(keyHandler);

        primaryStage.setTitle("Trying");
        primaryStage.setScene(scene);
        primaryStage.show();

        timer = new SnakeTimer(controller, (Integer)toggleGroup.getSelectedToggle().getUserData());
        timer.start();
    }

    public void drawGrid(Field field) {
        drawer.drawAll(field);
    }

    public void changeScore(Integer newScore) {
        scoreLabel.setText("Score: " + newScore);
    }

    public void gameOver() {
        timer.stop();
        primaryStage.close();

        Label gameOverLabel = new Label("Game Over!");
        Button restartButton = new Button("Restart");
        // todo set actual restart action for restart button
        restartButton.setOnAction((event) -> primaryStage.close());

        VBox box = new VBox(15, gameOverLabel,restartButton);
        box.setAlignment(Pos.BASELINE_CENTER);

        Scene gameOverScene = new Scene(box,160,120);
        primaryStage.setScene(gameOverScene);
        primaryStage.show();
    }
}
