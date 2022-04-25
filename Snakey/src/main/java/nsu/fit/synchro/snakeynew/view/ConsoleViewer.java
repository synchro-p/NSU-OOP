package nsu.fit.synchro.snakeynew.view;

import nsu.fit.synchro.snakeynew.model.Field;

public class ConsoleViewer {
    public static void printGrid(Field field) {
        byte[][] grid = field.getGrid();
        for (int i = 0; i < field.getHeight(); i++) {
            for (int j = 0; j < field.getWidth(); j++) {
                System.out.print(grid[j][i]);
            }
            System.out.println();
        }
        System.out.println("***");
    }
}
