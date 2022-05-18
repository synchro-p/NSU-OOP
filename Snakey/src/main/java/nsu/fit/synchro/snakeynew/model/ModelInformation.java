package nsu.fit.synchro.snakeynew.model;

import nsu.fit.synchro.snakeynew.view.KeyCatchApplication;

public record ModelInformation(Direction startingDirection,
                               Coordinates startingPosition,
                               Integer width, Integer height,
                               KeyCatchApplication application) {

    public Direction getStartingDirection() {
        return startingDirection;
    }

    public Coordinates getStartingPosition() {
        return startingPosition;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public KeyCatchApplication getApplication() {
        return application;
    }
}
