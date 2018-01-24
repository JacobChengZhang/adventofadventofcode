import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {
    static final String input = "./src/main/resources/day2_input.txt";

    static List<String> lines;

    public static void main(String[] args) {
        getInput(input);
        System.out.println("[Part 1]Sum: " + calc(lines));
        System.out.println("[Part 2]Sum: " + calcRibbon(lines));
    }

    static void getInput(String filepath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            lines = new ArrayList<>();
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                lines.add(temp);
            }
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

    static int calc(List<String> lines) {
        return lines
                .stream()
                .map(Box::new)
                .map(Box::getArea)
                .collect(Collectors.summingInt(Integer::intValue));
    }

    static int calcRibbon(List<String> lines) {
        return lines
                .stream()
                .map(Box::new)
                .map(Box::getRibbon)
                .collect(Collectors.summingInt(Integer::intValue));
    }


}

class Box {
    final int a;
    final int b;
    final int c;

    public Box(String line) {
        this.a = Integer.parseInt(line.split("x")[0]);
        this.b = Integer.parseInt(line.split("x")[1]);
        this.c = Integer.parseInt(line.split("x")[2]);
    }

    public int getArea() {
        if (a >= b && a >= c) {
            return 2 * (a * b + b * c + c * a) + b * c;
        } else if (b >= c && b >= a) {
            return 2 * (a * b + b * c + c * a) + c * a;
        } else {
            return 2 * (a * b + b * c + c * a) + a * b;
        }
    }

    public int getRibbon() {
        int volume = a * b * c;
        if (a >= b && a >= c) {
            return 2 * (b + c) + volume;
        } else if (b >= c && b >= a) {
            return 2 * (c + a) + volume;
        } else {
            return 2 * (a + b) + volume;
        }
    }

    @Override
    public String toString() {
        return String.format("a: %d, b: %d, c: %d", a, b, c);
    }
}
