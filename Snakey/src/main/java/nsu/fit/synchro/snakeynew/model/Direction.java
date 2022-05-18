package nsu.fit.synchro.snakeynew.model;

public enum Direction {
    RIGHT {
        @Override
        public Coordinates getNext(Integer width, Integer height, Coordinates currentCoordinates) {
            Coordinates next = new Coordinates(currentCoordinates.getX() + 1, currentCoordinates.getY());
            if (next.getX().equals(width)) {
                next = new Coordinates(0, next.getY());
            }
            return next;
        }
    },
    LEFT {
        @Override
        public Coordinates getNext(Integer width, Integer height, Coordinates currentCoordinates) {
            Coordinates next = new Coordinates(currentCoordinates.getX() - 1, currentCoordinates.getY());
            if (next.getX().equals(-1)) {
                next = new Coordinates(width - 1, next.getY());
            }
            return next;
        }
    },
    UP {
        @Override
        public Coordinates getNext(Integer width, Integer height, Coordinates currentCoordinates) {
            Coordinates next = new Coordinates(currentCoordinates.getX(), currentCoordinates.getY() - 1);
            if (next.getY() == -1) {
                next = new Coordinates(next.getX(), height - 1);
            }
            return next;
        }
    },
    DOWN {
        @Override
        public Coordinates getNext(Integer width, Integer height, Coordinates currentCoordinates) {
            Coordinates next = new Coordinates(currentCoordinates.getX(), currentCoordinates.getY() + 1);
            if (next.getY().equals(height)) {
                next = new Coordinates(next.getX(), 0);
            }
            return next;
        }
    };

    public abstract Coordinates getNext(Integer width, Integer height, Coordinates currentCoordinates);
}
