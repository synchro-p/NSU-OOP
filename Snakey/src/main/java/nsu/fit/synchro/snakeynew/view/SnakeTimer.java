package nsu.fit.synchro.snakeynew.view;

import javafx.animation.AnimationTimer;
import nsu.fit.synchro.snakeynew.controller.DirectionController;

public class SnakeTimer extends AnimationTimer {
    private long lastActivated;
    private final DirectionController controller;

    public SnakeTimer(DirectionController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(long now) {
        if (now - lastActivated > 2000000000) {
            lastActivated = now;
            controller.makeStep();
        }
    }
}
