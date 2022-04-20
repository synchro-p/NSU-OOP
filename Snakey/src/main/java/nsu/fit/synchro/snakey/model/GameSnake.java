package nsu.fit.synchro.snakey.model;

import java.util.ArrayDeque;


public class GameSnake {
    private final ArrayDeque<Coordinates> snekParts;
    private Coordinates headCoordinates;
    private Direction direction;

    public GameSnake(Coordinates headCoordinates) {
        this.headCoordinates = headCoordinates;
        direction = Direction.Right;
        this.snekParts = new ArrayDeque<>();
        this.snekParts.add(headCoordinates);
    }

    public Coordinates nextPoint() {
        Coordinates next = null;
        switch (direction) {
            case Up -> next = new Coordinates(headCoordinates.getX(), headCoordinates.getY() - 1);
            case Down -> next = new Coordinates(headCoordinates.getX(), headCoordinates.getY() + 1);
            case Left -> next = new Coordinates(headCoordinates.getX() - 1, headCoordinates.getY());
            case Right -> next = new Coordinates(headCoordinates.getX() + 1, headCoordinates.getY());
        }
        return next;
    }

    public void growTo(Coordinates coordinates) {
        snekParts.add(coordinates);
        headCoordinates = coordinates;
    }

    public Coordinates getHeadCoordinates() {
        return headCoordinates;
    }

    public void updateDirection(Direction direction) {
        this.direction = direction;
    }

    public Coordinates loseTail() {
        Coordinates toLose = snekParts.poll();
        System.out.println(toLose.intoString());
        return toLose;
    }
}
