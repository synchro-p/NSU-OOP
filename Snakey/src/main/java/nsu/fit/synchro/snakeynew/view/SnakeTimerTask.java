package nsu.fit.synchro.snakeynew.view;

import nsu.fit.synchro.snakeynew.controller.DirectionController;

import java.util.TimerTask;

public class SnakeTimerTask extends TimerTask {
    DirectionController controller;

    public SnakeTimerTask(DirectionController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        controller.makeStep();
    }
}
