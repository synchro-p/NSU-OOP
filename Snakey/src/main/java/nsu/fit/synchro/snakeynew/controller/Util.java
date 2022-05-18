package nsu.fit.synchro.snakeynew.controller;

import javafx.scene.image.Image;
import nsu.fit.synchro.snakeynew.model.Direction;
import nsu.fit.synchro.snakeynew.view.ImageCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Util {
    public static HashMap<Direction, Direction> oppositeDirectionsMap() {
        HashMap<Direction, Direction> result = new HashMap<>();
        result.put(Direction.RIGHT, Direction.LEFT);
        result.put(Direction.LEFT, Direction.RIGHT);
        result.put(Direction.DOWN, Direction.UP);
        result.put(Direction.UP, Direction.DOWN);
        return result;
    }

    public static <T> T getRandomFromArrayList(ArrayList<T> list, Random random) {
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    public static ArrayList<Image> namesToListOfImages(String... names) {
        ImageCreator imageCreator = new ImageCreator();
        ArrayList<Image> result = new ArrayList<>();
        for (String name : names) {
            result.add(imageCreator.getImageFromResources(name));
        }
        return result;
    }
}
