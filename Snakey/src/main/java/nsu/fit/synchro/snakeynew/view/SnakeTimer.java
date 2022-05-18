package nsu.fit.synchro.snakeynew.view;

import javafx.animation.AnimationTimer;
import nsu.fit.synchro.snakeynew.controller.DirectionController;

public class SnakeTimer extends AnimationTimer {
    private long lastActivated;
    private final DirectionController controller;
    private Boolean firstTime;
    private final Integer timeout;

    public enum Difficulty {
        HARD,
        EASY
    }

    public SnakeTimer(DirectionController controller, Difficulty difficulty) {
        this.controller = controller;
        this.firstTime = true;
        this.timeout = switch (difficulty) {
            case EASY -> 1000000000;
            case HARD -> 500000000;
        };
    }

    @Override
    public void handle(long now) {
        if (firstTime) {
            firstTime = false;
            lastActivated = now;
        }
        if (now - lastActivated > timeout) {
            lastActivated = now;
            controller.makeStep();
        }
    }
}
