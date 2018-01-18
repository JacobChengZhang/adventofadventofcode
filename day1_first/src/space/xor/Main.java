package space.xor;

public class Main {
    // assume that the puzzleInput is valid
    static final String puzzleInput = "R3, L2, L2, R4, L1, R2, R3, R4, L2, R4, L2, L5, L1, R5, R2, R2, L1, R4, R1, L5, L3, R4, R3, R1, L1, L5, L4, L2, R5, L3, L4, R3, R1, L3, R1, L3, R3, L4, R2, R5, L190, R2, L3, R47, R4, L3, R78, L1, R3, R190, R4, L3, R4, R2, R5, R3, R4, R3, L1, L4, R3, L4, R1, L4, L5, R3, L3, L4, R1, R2, L4, L3, R3, R3, L2, L5, R1, L4, L1, R5, L5, R1, R5, L4, R2, L2, R1, L5, L4, R4, R4, R3, R2, R3, L1, R4, R5, L2, L5, L4, L1, R4, L4, R4, L4, R1, R5, L1, R1, L5, R5, R1, R1, L3, L1, R4, L1, L4, L4, L3, R1, R4, R1, R1, R2, L5, L2, R4, L1, R3, L5, L2, R5, L4, R5, L5, R3, R4, L3, L3, L2, R2, L5, L5, R3, R4, R3, R4, R3, R1";

    static int x = 0;
    static int y = 0;

    static Direction direction = Direction.NORTH;

    enum Direction {
        NORTH, EAST, SOUTH, WEST,
    }

    public static void main(String[] args) {
        String[] sequences = puzzleInput.split(", ");

        for (String str : sequences) {
            if (str.equals("")) {
                continue;
            }

            if (str.startsWith("R")) {
                turnRight();
            }
            else {
                turnLeft();
            }

            go(Integer.parseInt(str.substring(1)));
        }

        System.out.println(Math.abs(x) + Math.abs(y));
    }

    static void turnRight() {
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

    static void turnLeft() {
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

    static void go(int steps) {
        switch (direction) {
            case NORTH: {
                y += steps;
                break;
            }
            case EAST: {
                x += steps;
                break;
            }
            case SOUTH: {
                y -= steps;
                break;
            }
            case WEST: {
                x -= steps;
                break;
            }
        }
    }
}
