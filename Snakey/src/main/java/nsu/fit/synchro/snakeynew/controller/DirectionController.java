package nsu.fit.synchro.snakeynew.controller;

import nsu.fit.synchro.snakeynew.model.Direction;
import nsu.fit.synchro.snakeynew.model.Model;
import nsu.fit.synchro.snakeynew.model.ModelInformation;

import java.util.HashMap;

public class DirectionController {
    Direction direction;
    Model model;
    Direction previousStepDirection;
    HashMap<Direction, Direction> oppositeDirections = Util.oppositeDirectionsMap();

    public DirectionController(ModelInformation information) {
        model = new Model(information.getStartingPosition(),
                information.getObstacles(), information.getWidth(),
                information.getHeight(), information.getApplication());
        direction = information.getStartingDirection();
    }

    public void setDirection(Direction direction) {
        if (direction != oppositeDirections.get(this.previousStepDirection))
            this.direction = direction;
    }

    public void makeStep() {
        this.model.makeStep(direction);
        previousStepDirection = direction;
    }
}
