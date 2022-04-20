package nsu.fit.synchro.snakey.model;

import nsu.fit.synchro.snakey.model.Coordinates;

import java.util.Arrays;

public class Layout {
    private final byte[][] grid;
    private final Integer width;
    private final Integer length;


    public Layout(int length, int width) {
        this.width = width;
        this.length = length;
        grid = new byte[width][length];
        for (byte[] ass :
                grid) {
            Arrays.fill(ass, (byte) 0);
        }
    }

    public void addObstacle(Coordinates coordinates) {
        grid[coordinates.getY()][coordinates.getX()] = (byte) 1;
    }

    public void addSnake(Coordinates coordinates) {
        grid[coordinates.getY()][coordinates.getX()] = (byte) 2;
    }

    public void addFood(Coordinates coordinates) {
        grid[coordinates.getY()][coordinates.getX()] = (byte) 3;
    }

    public void wipe(Coordinates coordinates) {
        grid[coordinates.getY()][coordinates.getX()] = (byte) 0;
    }

    public boolean isValidSnakePosition(Coordinates coordinates) {
        return this.getByCoordinates(coordinates) == (byte) 0 || this.getByCoordinates(coordinates) == (byte) 3;
    }

    public byte[][] getGrid() {
        return grid;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getLength() {
        return length;
    }

    public byte getByCoordinates(Coordinates coordinates) {
        return this.grid[coordinates.getY()][coordinates.getX()];
    }

    public boolean isFood(Coordinates coordinates) {
        return this.getByCoordinates(coordinates) == (byte) 3;
    }
}
