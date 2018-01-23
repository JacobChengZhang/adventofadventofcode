import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    // assume that the puzzleInput is valid
    static final String input = "./input.txt";

    static int count = 0;

    public static void main(String[] args) {
        getInput(input);
    }

    private static void getInput(String filepath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            String str;
            while ((str = reader.readLine()) != null) {
                calc(str);
            }

            reader.close();
        }
        catch (IOException ioex) {
            ioex.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException ioex2) {
                    ioex2.printStackTrace();
                }
            }
        }
    }

    private static void calc(String s) {
        int index = 0;
        int length = s.length();

        while(index < length) {
            if (s.charAt(index) != '(') {
                index++;
                count++;
            }
            else {
                int index2 = s.indexOf(')', index);
                String[] tempList = s.substring(index + 1, index2).split("x");
                count += Integer.parseInt(tempList[0]) * Integer.parseInt(tempList[1]);
                index = index2 + 1;
                index += Integer.parseInt(tempList[0]);
            }
        }

        System.out.println(count);
    }
}
