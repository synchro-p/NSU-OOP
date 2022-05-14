package nsu.fit.synchro.snakeynew.model;

import java.util.ArrayList;
import java.util.Random;

public class Field {
    private final TileType[][] grid;
    private final Integer width;
    private final Integer height;
    private final ArrayList<Coordinates> emptyTiles;
    private final Random random;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.emptyTiles = new ArrayList<>();
        grid = new TileType[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.wipe(new Coordinates(i, j));
            }
        }
        random = new Random();
    }

    public void addObstacle(Coordinates coordinates) {
        grid[coordinates.getX()][coordinates.getY()] = TileType.OBSTACLE;
        emptyTiles.remove(coordinates);
    }

    public void addSnake(Coordinates coordinates) {
        grid[coordinates.getX()][coordinates.getY()] = TileType.SNAKE;
        emptyTiles.remove(coordinates);
    }

    public void addFood(Coordinates coordinates) {
        grid[coordinates.getX()][coordinates.getY()] = TileType.FOOD;
        emptyTiles.remove(coordinates);
    }

    public void addRandomFood() {
        if (emptyTiles.size() > 0) {
            this.addFood(emptyTiles.get(random.nextInt(emptyTiles.size())));
        }
    }

    public void wipe(Coordinates coordinates) {
        grid[coordinates.getX()][coordinates.getY()] = TileType.EMPTY;
        emptyTiles.add(coordinates);
    }

    public boolean isValidSnakePosition(Coordinates coordinates) {
        return this.getByCoordinates(coordinates) == TileType.EMPTY || this.getByCoordinates(coordinates) == TileType.FOOD;
    }

    public TileType[][] getGrid() {
        return grid;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public TileType getByCoordinates(Coordinates coordinates) {
        return this.grid[coordinates.getX()][coordinates.getY()];
    }

    public boolean isFood(Coordinates coordinates) {
        return this.getByCoordinates(coordinates) == TileType.FOOD;
    }
}
