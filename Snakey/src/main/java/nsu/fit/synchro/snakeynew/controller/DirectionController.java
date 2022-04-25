package nsu.fit.synchro.snakeynew.controller;

import nsu.fit.synchro.snakeynew.model.Direction;
import nsu.fit.synchro.snakeynew.model.Model;
import nsu.fit.synchro.snakeynew.model.ModelInformation;

public class DirectionController {
    Direction direction;
    Model model;
    Direction previousStepDirection;
    public DirectionController(ModelInformation information) {
        model = new Model(information.getStartingPosition(),
                information.getObstacles(), information.getWidth(),
                information.getHeight(), information.getApplication());
        direction = information.getStartingDirection();
    }

    public void setDirection(Direction direction) {
        if (!(this.previousStepDirection == Direction.LEFT && direction == Direction.RIGHT ||
                this.previousStepDirection == Direction.UP && direction == Direction.DOWN ||
                this.previousStepDirection == Direction.RIGHT && direction == Direction.LEFT ||
                this.previousStepDirection == Direction.DOWN && direction == Direction.UP
        ))
        this.direction = direction;
    }

    public void makeStep() {
        this.model.makeStep(direction);
        previousStepDirection = direction;
    }
}
