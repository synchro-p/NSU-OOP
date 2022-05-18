package nsu.fit.synchro.snakeynew.controller;

import nsu.fit.synchro.snakeynew.model.Coordinates;
import nsu.fit.synchro.snakeynew.model.Direction;
import nsu.fit.synchro.snakeynew.model.Model;
import nsu.fit.synchro.snakeynew.model.ModelInformation;

import java.util.ArrayList;
import java.util.HashMap;

public class DirectionController {
    Direction direction;
    Model model;
    Direction previousStepDirection;
    HashMap<Direction, Direction> oppositeDirections = Util.oppositeDirectionsMap();

    public DirectionController(ModelInformation information) {
        ArrayList<Coordinates> obstacles = new ArrayList<>();
        for (int i = 0; i < information.getHeight() / 2; i++) {
            obstacles.add(new Coordinates(i, 0));
            obstacles.add(new Coordinates(i, information.getHeight() - 1));
            obstacles.add(new Coordinates(0, i));
            obstacles.add(new Coordinates(information.getWidth() - 1, i));
        }

        for (int i = information.getHeight() / 2 + 1; i < information.getHeight(); i++) {
            obstacles.add(new Coordinates(i, 0));
            obstacles.add(new Coordinates(i, information.getHeight() - 1));
            obstacles.add(new Coordinates(0, i));
            obstacles.add(new Coordinates(information.getWidth() - 1, i));
        }

        model = new Model(information.getStartingPosition(),
                obstacles, information.getWidth(),
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
