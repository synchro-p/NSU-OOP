package nsu.fit.synchro.snakeynew.model;

import java.util.ArrayList;
import java.util.Random;

public class Field {
    private final byte[][] grid;
    private final Integer width;
    private final Integer height;
    private final ArrayList<Coordinates> emptyTiles;
    private final Random random;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.emptyTiles = new ArrayList<>();
        grid = new byte[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.wipe(new Coordinates(i, j));
            }
        }
        random = new Random();
        this.addRandomFood();
    }

    public void addObstacle(Coordinates coordinates) {
        grid[coordinates.getX()][coordinates.getY()] = (byte) 1;
        emptyTiles.remove(coordinates);
    }

    public void addSnake(Coordinates coordinates) {
        grid[coordinates.getX()][coordinates.getY()] = (byte) 2;
        emptyTiles.remove(coordinates);
    }

    public void addFood(Coordinates coordinates) {
        grid[coordinates.getX()][coordinates.getY()] = (byte) 3;
        emptyTiles.remove(coordinates);
    }

    public void addRandomFood() {
        if (emptyTiles.size() > 0) {
            this.addFood(emptyTiles.get(random.nextInt(emptyTiles.size())));
        }
    }

    public void wipe(Coordinates coordinates) {
        grid[coordinates.getX()][coordinates.getY()] = (byte) 0;
        emptyTiles.add(coordinates);
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

    public Integer getHeight() {
        return height;
    }

    public byte getByCoordinates(Coordinates coordinates) {
        return this.grid[coordinates.getX()][coordinates.getY()];
    }

    public boolean isFood(Coordinates coordinates) {
        return this.getByCoordinates(coordinates) == (byte) 3;
    }
}
