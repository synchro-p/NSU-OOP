package nsu.fit.synchro.snakey.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import nsu.fit.synchro.snakey.model.Direction;

public class KeyHandler implements EventHandler<KeyEvent> {
    Direction direction = Direction.Right;

    @Override
    public void handle(KeyEvent event) {
        direction = Converter.convertKeyEventToDirection(event);
    }

    //todo - checks for current direction
    public Direction getDirection() {
        return direction;
    }
}
