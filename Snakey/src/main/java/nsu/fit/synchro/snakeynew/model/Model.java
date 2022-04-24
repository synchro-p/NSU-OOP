package nsu.fit.synchro.snakeynew.model;

import nsu.fit.synchro.snakeynew.view.KeyCatchApplication;

import java.util.ArrayList;

public class Model {
    private final Field field;
    private GameSnake snake;
    private final KeyCatchApplication application;
    public Model (Coordinates startingPosition, ArrayList<Coordinates> obstacles,
                  Integer width, Integer height, KeyCatchApplication application) {
        field = new Field(width, height);
        this.initSnake(startingPosition);
        for (Coordinates coordinates : obstacles) {
            field.addObstacle(coordinates);
        }
        field.addSnake(startingPosition);
        this.application = application;
    }

    private void initSnake(Coordinates startingPosition) {
        snake = new GameSnake(startingPosition);
    }

    public void makeStep(Direction direction) {
        Coordinates nextPoint = snake.nextPoint(field.getWidth(), field.getHeight(), direction);
        System.out.println(nextPoint.intoString());
        if (field.isFood(nextPoint)) {
            field.addRandomFood();
        } else {
            Coordinates toLose = snake.loseTail();
            field.wipe(toLose);
        }
        if (field.isValidSnakePosition(nextPoint)) {
            snake.growTo(nextPoint);
            field.addSnake(nextPoint);
            application.printGrid(field);
        }
        else {
            try {
                application.gameOver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
