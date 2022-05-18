package nsu.fit.synchro.snakeynew.model;

import nsu.fit.synchro.snakeynew.view.KeyCatchApplication;

import java.util.ArrayList;

public class Model {
    private final Field field;
    private Snake snake;
    private final KeyCatchApplication application;
    private Integer currentScore;

    public Model(Coordinates startingPosition, ArrayList<Coordinates> obstacles,
                 Integer width, Integer height, KeyCatchApplication application) {
        field = new Field(width, height);
        this.initSnake(startingPosition);
        for (Coordinates coordinates : obstacles) {
            field.addObstacle(coordinates);
        }
        field.addSnake(startingPosition);
        currentScore = 1;

        for (int i = 0; i <= width * height / 100; i++) {
            field.addRandomFood();
        }

        this.application = application;
        application.draw(field);
        application.changeScore(currentScore);
    }

    private void initSnake(Coordinates startingPosition) {
        snake = new Snake(startingPosition);
    }

    public void makeStep(Direction direction) {
        Coordinates nextPoint = direction.getNext(this.field.getWidth(), this.field.getHeight(),
                this.snake.getHeadCoordinates());
        if (field.isFood(nextPoint)) {
            field.addRandomFood();
            currentScore++;
        } else {
            Coordinates toLose = snake.loseTail();
            field.wipe(toLose);
        }
        if (field.isValidSnakePosition(nextPoint)) {
            snake.growTo(nextPoint);
            field.addSnake(nextPoint);
            application.draw(field);
            application.changeScore(currentScore);
        } else {
            application.gameOver();
        }
    }
}
