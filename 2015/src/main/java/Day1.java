import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day1 {
    static final String input = "./src/main/resources/day1_input.txt";

    static int floor = 0;

    public static void main(String[] args) {
        getInput(input);

        System.out.println("[First Part]At last, it's " + floor + " floor.");
    }

    static void getInput(String filepath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            int ch = 0;
            int num = 1;
            boolean findAns = false;
            while ((ch = reader.read()) != -1) {
                if (ch == '(') {
                    floor++;
                } else if (ch == ')') {
                    floor--;
                } else {
                    System.out.println("Bug found.");
                }

                if (!findAns && floor == -1) {
                    System.out.println("[Second Part]Position is " + num);
                    findAns = true;
                }

                num++;
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
}
