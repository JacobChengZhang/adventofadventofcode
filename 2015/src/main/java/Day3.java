import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day3 {
    static final String input = "./src/main/resources/day3_input.txt";

    static List<Integer> numbers;

    static Set<Position> posSet;

    static int x = 0;
    static int y = 0;

    static int x2 = 0;
    static int y2 = 0;

    static boolean isSanta = true;

    public static void main(String[] args) {
        getInput(input);
    }

    static void getInput(String filepath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            numbers = new ArrayList<>();
            posSet = new HashSet<>();
            posSet.add(new Position(0, 0));
            int ch = -1;
            while ((ch = reader.read()) != -1) {
                //process(ch);
                processPrime(ch);
            }

            //System.out.println("[Part 1] sum: " + posSet.size());
            System.out.println("[Part 2] sum: " + posSet.size());
            reader.close();
        } catch (IOException ioe1) {
            ioe1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe2) {
                    ioe2.printStackTrace();
                }
            }
        }
    }

    static void process(int direction) {
        switch (direction) {
            case '^': {
                y++;
                break;
            }
            case 'v': {
                y--;
                break;
            }
            case '<': {
                x--;
                break;
            }
            case '>': {
                x++;
                break;
            }
            default: {
                System.out.println("Illegal character found." + (char)direction);
                return;
            }
        }
        posSet.add(new Position(x, y));
    }

    static void processPrime(int direction) {
        switch (direction) {
            case '^': {
                if (isSanta) {
                    y++;
                    isSanta = false;
                } else {
                    y2++;
                    isSanta = true;
                }

                break;
            }
            case 'v': {
                if (isSanta) {
                    y--;
                    isSanta = false;
                } else {
                    y2--;
                    isSanta = true;
                }
                break;
            }
            case '<': {
                if (isSanta) {
                    x--;
                    isSanta = false;
                } else {
                    x2--;
                    isSanta = true;
                }
                break;
            }
            case '>': {
                if (isSanta) {
                    x++;
                    isSanta = false;
                } else {
                    x2++;
                    isSanta = true;
                }
                break;
            }
            default: {
                System.out.println("Illegal character found." + (char)direction);
                return;
            }
        }

        if (isSanta) {
            posSet.add(new Position(x2, y2));
        } else {
            posSet.add(new Position(x, y));
        }

    }
}

class Position {
    final int x;
    final int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

//    @Override
//    public String toString() {
//        return x + "_" + y;
//    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Position) && (((Position)o).x == this.x) && (((Position)o).y == this.y);
    }

    @Override
    public int hashCode() {
        return x * 1000 + y;
    }
}
