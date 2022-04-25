package nsu.fit.synchro.snakeynew.view;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import nsu.fit.synchro.snakeynew.controller.DirectionController;

public class KeyHandler implements EventHandler<KeyEvent> {
    DirectionController controller;

    public KeyHandler (DirectionController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(KeyEvent event) {
        if (Converter.convertKeyEventToDirection(event) != null) {
            controller.setDirection(Converter.convertKeyEventToDirection(event));
        }
    }
}
