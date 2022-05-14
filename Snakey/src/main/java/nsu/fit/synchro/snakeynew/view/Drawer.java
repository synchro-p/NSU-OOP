package nsu.fit.synchro.snakeynew.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import nsu.fit.synchro.snakeynew.controller.Util;
import nsu.fit.synchro.snakeynew.model.Coordinates;
import nsu.fit.synchro.snakeynew.model.Field;
import nsu.fit.synchro.snakeynew.model.TileType;

import java.util.ArrayList;
import java.util.Random;

public class Drawer {
    GraphicsContext graphicsContext;
    ArrayList<Image> spritesForBackground;
    ImageCreator imageCreator;
    Integer seed;

    public Drawer(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        imageCreator = new ImageCreator();
        spritesForBackground = Util.namesToListOfImages("/grass_sprite.png", "/grass_sprite_1.png",
                "/grass_sprite_2.png", "/grass_sprite_3.png", "/grass_sprite_4.png", "/grass_sprite_5.png",
                "/grass_sprite_6.png");

        Random randomizerForBackgroundSeed = new Random();
        seed = randomizerForBackgroundSeed.nextInt();
    }

    public void drawAll(Field field) {
        this.drawBackground(field);
        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getHeight(); j++) {
                if (field.getByCoordinates(new Coordinates(i, j)) == TileType.SNAKE) {
                    graphicsContext.drawImage(imageCreator.getImageFromResources("/snake_sprite.png"),
                            20 * i, 20 * j);
                }
                if (field.getByCoordinates(new Coordinates(i,j)) == TileType.OBSTACLE) {
                    graphicsContext.drawImage(imageCreator.getImageFromResources("/wall_sprite.png"),
                            20 * i, 20 * j);
                }
                if (field.getByCoordinates(new Coordinates(i,j)) == TileType.FOOD) {
                    graphicsContext.drawImage(imageCreator.getImageFromResources("/food_sprite.png"),
                            20 * i, 20 * j);
                }
            }
        }
    }

    private void drawBackground(Field field) {
        Random random = new Random(seed);
        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getHeight(); j++) {
                graphicsContext.drawImage(Util.getRandomFromArrayList(spritesForBackground, random),
                        20 * i, 20 * j);
            }
        }
    }
}
