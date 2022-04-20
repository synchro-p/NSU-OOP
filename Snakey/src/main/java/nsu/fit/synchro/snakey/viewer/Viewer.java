package nsu.fit.synchro.snakey.viewer;

import nsu.fit.synchro.snakey.model.Coordinates;
import nsu.fit.synchro.snakey.model.Field;

import java.util.ArrayList;

public class Viewer {
    public static void printGrid(Field field) {
        byte[][] grid = field.getGrid();
        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getLength(); j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        System.out.println("***");
    }

    public static void printMapByCoordinates(ArrayList<Coordinates> coordinates, Integer y, Integer x) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (coordinates.contains(new Coordinates(j, i))) {
                    System.out.print("e");
                } else
                    System.out.print("f");
            }
            System.out.println();
        }
    }
}
