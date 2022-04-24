package nsu.fit.synchro.snakeynew.view;

import javafx.scene.input.KeyEvent;
import nsu.fit.synchro.snakeynew.model.Direction;

public class Converter {
    public static Direction convertKeyEventToDirection(KeyEvent event) {
        return switch (event.getCode().getName()) {
            case "Up" -> Direction.UP;
            case "Down" -> Direction.DOWN;
            case "Right" -> Direction.RIGHT;
            case "Left" -> Direction.LEFT;
            default -> null;
        };
    }
}
