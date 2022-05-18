package nsu.fit.synchro.snakeynew.model;

import java.util.ArrayDeque;


public class Snake {
    private final ArrayDeque<Coordinates> snakeParts;
    private Coordinates headCoordinates;

    public Snake(Coordinates headCoordinates) {
        this.headCoordinates = headCoordinates;
        this.snakeParts = new ArrayDeque<>();
        this.snakeParts.add(headCoordinates);
    }

    public void growTo(Coordinates coordinates) {
        snakeParts.add(coordinates);
        headCoordinates = coordinates;
    }

    public Coordinates getHeadCoordinates() {
        return headCoordinates;
    }

    public Coordinates loseTail() {
        return snakeParts.poll();
    }
}
