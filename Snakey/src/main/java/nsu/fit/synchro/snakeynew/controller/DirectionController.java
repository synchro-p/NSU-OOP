package nsu.fit.synchro.snakeynew.controller;

import nsu.fit.synchro.snakeynew.model.Direction;
import nsu.fit.synchro.snakeynew.model.Model;
import nsu.fit.synchro.snakeynew.model.ModelInformation;

public class DirectionController {
    Direction direction;
    Model model;
    public DirectionController(ModelInformation information) {
        model = new Model(information.getStartingPosition(),
                information.getObstacles(), information.getWidth(),
                information.getHeight(), information.getApplication());
        direction = information.getStartingDirection();
    }

    public void setDirection(Direction direction) {
        if (!(this.direction == Direction.LEFT && direction == Direction.RIGHT ||
                this.direction == Direction.UP && direction == Direction.DOWN ||
                this.direction == Direction.RIGHT && direction == Direction.LEFT ||
                this.direction == Direction.DOWN && direction == Direction.UP
        ))
        this.direction = direction;
    }

    public void makeStep() {
        this.model.makeStep(direction);
    }
}
