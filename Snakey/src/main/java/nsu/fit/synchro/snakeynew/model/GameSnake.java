package nsu.fit.synchro.snakeynew.model;

import java.util.ArrayDeque;


public class GameSnake {
    private final ArrayDeque<Coordinates> snekParts;
    private Coordinates headCoordinates;

    public GameSnake(Coordinates headCoordinates) {
        this.headCoordinates = headCoordinates;
        this.snekParts = new ArrayDeque<>();
        this.snekParts.add(headCoordinates);
    }

    public Coordinates nextPoint(Integer width, Integer height, Direction direction) {
        Coordinates next = null;
        switch (direction) {
            case UP -> {
                next = new Coordinates(headCoordinates.getX(), headCoordinates.getY() - 1);
                if (next.getY() == -1) {
                    next = new Coordinates(next.getX(), height - 1);
                }
            }
            case DOWN -> {
                next = new Coordinates(headCoordinates.getX(), headCoordinates.getY() + 1);
                if (next.getY().equals(height)) {
                    next = new Coordinates(next.getX(), 0);
                }
            }
            case LEFT -> {
                next = new Coordinates(headCoordinates.getX() - 1, headCoordinates.getY());
                if (next.getX().equals(-1)) {
                    next = new Coordinates(width-1, next.getY());
                }
            }
            case RIGHT -> {
                next = new Coordinates(headCoordinates.getX() + 1, headCoordinates.getY());
                if (next.getX().equals(width)) {
                    next = new Coordinates(0, next.getY());
                }
            }
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

    public Coordinates loseTail() {
        Coordinates toLose = snekParts.poll();
        if (toLose != null) {
            System.out.println(toLose.intoString());
        }
        return toLose;
    }
}
