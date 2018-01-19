import java.util.HashMap;

public class Main {
    // assume that the puzzleInput is valid
    static final String puzzleInput = "R3, L2, L2, R4, L1, R2, R3, R4, L2, R4, L2, L5, L1, R5, R2, R2, L1, R4, R1, L5, L3, R4, R3, R1, L1, L5, L4, L2, R5, L3, L4, R3, R1, L3, R1, L3, R3, L4, R2, R5, L190, R2, L3, R47, R4, L3, R78, L1, R3, R190, R4, L3, R4, R2, R5, R3, R4, R3, L1, L4, R3, L4, R1, L4, L5, R3, L3, L4, R1, R2, L4, L3, R3, R3, L2, L5, R1, L4, L1, R5, L5, R1, R5, L4, R2, L2, R1, L5, L4, R4, R4, R3, R2, R3, L1, R4, R5, L2, L5, L4, L1, R4, L4, R4, L4, R1, R5, L1, R1, L5, R5, R1, R1, L3, L1, R4, L1, L4, L4, L3, R1, R4, R1, R1, R2, L5, L2, R4, L1, R3, L5, L2, R5, L4, R5, L5, R3, R4, L3, L3, L2, R2, L5, L5, R3, R4, R3, R4, R3, R1";

    int x = 0;
    int y = 0;

    Direction direction = Direction.NORTH;

    enum Direction {
        NORTH, EAST, SOUTH, WEST,
    }

    HashMap<Point, Boolean> pointMap = new HashMap<>();

    public static void main(String[] args) {
        Main main = new Main();

        String[] sequences = puzzleInput.split(", ");

        main.checkPoint(0, 0);
        for (String str : sequences) {
            if (str.equals("")) {
                continue;
            }

            if (str.startsWith("R")) {
                main.turnRight();
            }
            else {
                main.turnLeft();
            }

            main.go(Integer.parseInt(str.substring(1)));
        }
    }

    void turnRight() {
        switch (direction) {
            case NORTH: {
                direction = Direction.EAST;
                break;
            }
            case EAST: {
                direction = Direction.SOUTH;
                break;
            }
            case SOUTH: {
                direction = Direction.WEST;
                break;
            }
            case WEST: {
                direction = Direction.NORTH;
                break;
            }
        }
    }

    void turnLeft() {
        switch (direction) {
            case NORTH: {
                direction = Direction.WEST;
                break;
            }
            case EAST: {
                direction = Direction.NORTH;
                break;
            }
            case SOUTH: {
                direction = Direction.EAST;
                break;
            }
            case WEST: {
                direction = Direction.SOUTH;
                break;
            }
        }
    }

    void go(int steps) {
        switch (direction) {
            case NORTH: {
                for (int i = 0; i < steps; i++) {
                    y++;
                    checkPoint(x, y);
                }
                break;
            }
            case EAST: {
                for (int i = 0; i < steps; i++) {
                    x++;
                    checkPoint(x, y);
                }
                break;
            }
            case SOUTH: {
                for (int i = 0; i < steps; i++) {
                    y--;
                    checkPoint(x, y);
                }
                break;
            }
            case WEST: {
                for (int i = 0; i < steps; i++) {
                    x--;
                    checkPoint(x, y);
                }
                break;
            }
        }
    }

    void checkPoint(int x, int y) {

        Point p = new Point(x, y);
        if (pointMap.get(p) == null) {
            pointMap.put(p, true);
        }
        else {
            System.out.println(Math.abs(x) + Math.abs(y));
            System.exit(0);
        }
    }

    class Point {
        final int _x;
        final int _y;

        @Override
        public int hashCode() {
            // aha, 10000 is a magic number. XD
            return _x * 10000 + _y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Point p = (Point) o;
            if (_x != p._x || _y != p._y) {
                return false;
            }
            return true;
        }

        Point(int x, int y) {
            _x = x;
            _y = y;
        }
    }
}
