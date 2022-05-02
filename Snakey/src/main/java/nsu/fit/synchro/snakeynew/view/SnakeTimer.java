package nsu.fit.synchro.snakeynew.view;

import javafx.animation.AnimationTimer;
import nsu.fit.synchro.snakeynew.controller.DirectionController;

public class SnakeTimer extends AnimationTimer {
    private long lastActivated;
    private final DirectionController controller;
    private Boolean firstTime;
    private final Integer timeout;

    public SnakeTimer(DirectionController controller, Integer timeout) {
        this.controller = controller;
        this.firstTime = true;
        this.timeout = timeout;
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
