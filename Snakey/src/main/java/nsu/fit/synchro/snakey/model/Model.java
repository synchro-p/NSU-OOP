package nsu.fit.synchro.snakey.model;

import nsu.fit.synchro.snakey.controller.KeyHandler;
import nsu.fit.synchro.snakey.viewer.Viewer;

import java.util.ArrayList;
import java.util.Random;

public class Model implements Runnable {
    Field field;
    GameSnake snake;
    ArrayList<Coordinates> emptyTiles = new ArrayList<>();
    KeyHandler keyHandler;

    public Model(Field field, GameSnake snake, KeyHandler keyHandler) {
        this.field = field;
        this.snake = snake;
        this.keyHandler = keyHandler;
    }

    @Override
    public void run() {
        //Init
        boolean gameOver = false;
        field.addSnake(snake.getHeadCoordinates());
        byte[][] grid = field.getGrid();
        System.out.println(grid.length + " " + grid[0].length);
        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getLength(); j++) {
                if (field.getByCoordinates(new Coordinates(j, i)) == (byte) 0) {
                    emptyTiles.add(new Coordinates(j, i));
                }
            }
        }
        int foodQuantity = grid.length * grid[0].length / 15 + 1;
        if (emptyTiles.size() < foodQuantity) {
            throw (new RuntimeException("Faulty level - not enough empty tiles for food"));
        }
        Random random = new Random();
        for (int i = 0; i < foodQuantity; i++) {
            int index = random.nextInt(emptyTiles.size());
            field.addFood(emptyTiles.remove(index));
        }

        //Model
        while (!gameOver) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Controls
            Direction direction = keyHandler.getDirection();
            snake.updateDirection(direction);
            Coordinates nextPoint = snake.nextPoint(grid[0].length, grid.length);
            System.out.println(nextPoint.intoString());
            if (!field.isFood(nextPoint)) {
                Coordinates toLose = snake.loseTail();
                field.wipe(toLose);
                emptyTiles.add(toLose);
            } else {
                if (emptyTiles.size() > 0) {
                    int index = random.nextInt(emptyTiles.size());
                    field.addFood(emptyTiles.remove(index));
                }
            }
            if (field.isValidSnakePosition(nextPoint)) {
                snake.growTo(nextPoint);
                emptyTiles.remove(nextPoint);
                field.addSnake(nextPoint);
                //Viewer
                Viewer.printGrid(field);
                Viewer.printMapByCoordinates(emptyTiles, grid.length, grid[0].length);
            } else gameOver = true;
        }

        //Game over section
        System.out.println("Game Over!");
    }
}
