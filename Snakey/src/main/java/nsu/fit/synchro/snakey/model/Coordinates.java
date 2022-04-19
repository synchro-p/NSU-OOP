package nsu.fit.synchro.snakey.model;

public record Coordinates(Integer x, Integer y) {
    public Integer getY() {
        return y;
    }

    public Integer getX() {
        return x;
    }

    public String intoString() {
        return "[" + x + "," + y + "]";
    }
}
