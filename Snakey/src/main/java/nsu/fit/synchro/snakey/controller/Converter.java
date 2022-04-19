package nsu.fit.synchro.snakey.controller;

import javafx.scene.input.KeyEvent;
import nsu.fit.synchro.snakey.model.Direction;

public class Converter {
    public static Direction convertKeyEventToDirection(KeyEvent event) {
        return switch (event.getCode().getName()) {
            case "Up" -> Direction.Up;
            case "Down" -> Direction.Down;
            case "Right" -> Direction.Right;
            case "Left" -> Direction.Left;
            default -> null;
        };
    }
}
