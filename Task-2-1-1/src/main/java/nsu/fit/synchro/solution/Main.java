package nsu.fit.synchro.solution;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Drawer drawer = new Drawer();
        drawer.setYs(new TimeMeasurer().measure());
        JFrame frame = new JFrame("Focksmash");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(drawer);
        frame.setSize (1920,1080);
        frame.setVisible(true);
    }
}
