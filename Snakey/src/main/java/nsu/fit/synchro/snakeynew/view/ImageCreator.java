package nsu.fit.synchro.snakeynew.view;

import javafx.scene.image.Image;

public class ImageCreator {
    public Image getImageFromResources(String name) {
        return new Image(getClass().getResource(name).toString());
    }
}
