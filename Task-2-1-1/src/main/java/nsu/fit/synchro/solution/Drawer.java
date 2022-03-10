package nsu.fit.synchro.solution;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Drawer extends JPanel {
    private int[] yys = null;
    private int HEIGHT = 720;
    private int WIDTH = 1080;
    private final int scale = 100;

    public void setHEIGHT(Integer newHeight) {
        HEIGHT = newHeight;
    }

    public void setWIDTH(Integer newWidth) {
        WIDTH = newWidth;
    }

    public void setYs(ArrayList<Double> points) {
        points = points.stream().mapToDouble(a -> a * HEIGHT).boxed().collect(Collectors.toCollection(ArrayList::new));
        yys = points.stream().mapToInt(a -> HEIGHT - a.intValue() / scale).toArray();
    }

    /**
     * Draws a 6-point graph, x-coordinates are defined via WIDTH parameter, y-coordinates - via setter
     *
     * @param graphics graphics parameter used to communicate with JFrame class (?)
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.setBackground(Color.WHITE);
        graphics.setColor(Color.BLUE);
        int[] xs = {WIDTH / 6 - WIDTH / 12, WIDTH * 2 / 6 - WIDTH / 12, WIDTH * 3 / 6 - WIDTH / 12,
                WIDTH * 4 / 6 - WIDTH / 12, WIDTH * 5 / 6 - WIDTH / 12, WIDTH - WIDTH / 12};
        graphics.drawPolyline(xs, yys, 6);
    }
}
