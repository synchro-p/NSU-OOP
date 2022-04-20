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

    public Coordinates nextPoint(Integer width, Integer length) {
        Coordinates next = null;
        switch (direction) {
            case Up -> next = new Coordinates(headCoordinates.getX(), headCoordinates.getY() - 1);
            case Down -> next = new Coordinates(headCoordinates.getX(), headCoordinates.getY() + 1);
            case Left -> next = new Coordinates(headCoordinates.getX() - 1, headCoordinates.getY());
            case Right -> next = new Coordinates(headCoordinates.getX() + 1, headCoordinates.getY());
        }
        if (next.getX() == -1) {
            next = new Coordinates(width - 1, next.getY());
        }
        if (next.getX().equals(width)) {
            next = new Coordinates(0, next.getY());
        }
        if (next.getY() == -1) {
            next = new Coordinates(next.getX(), length - 1);
        }
        if (next.getY().equals(length)) {
            next = new Coordinates(next.getX(), 0);
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
        if (toLose != null) {
            System.out.println(toLose.intoString());
        }
        return toLose;
    }
}
