package nsu.fit.synchro.snakeynew.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import nsu.fit.synchro.snakeynew.model.Coordinates;
import nsu.fit.synchro.snakeynew.model.Field;

public class Drawer {
    GraphicsContext graphicsContext;
    public Drawer(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public void drawGrid(Field field) {
        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getHeight(); j++) {
                Color toPaint = switch (field.getByCoordinates(new Coordinates(i, j))) {
                    case ((byte) 1) -> Color.FIREBRICK;
                    case ((byte) 2) -> Color.YELLOW;
                    case ((byte) 3) -> Color.ORANGE;
                    default -> Color.LIGHTGREEN;
                };
                graphicsContext.setFill(toPaint);
                graphicsContext.fillRect(20*i, 20*j, 20, 20);
            }
        }
    }
}
