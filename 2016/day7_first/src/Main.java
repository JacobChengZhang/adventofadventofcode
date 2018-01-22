import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;

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
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                if (check(tempStr)) {
                    count++;
                }
            }

            System.out.println(count);
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

    private static boolean check(String str) {
        int length = str.length();
        int index = 0;
        int value = 0;
        boolean hasABBA = false;

        boolean inBracket = false;

        while (index < length - 3) {
            value = str.charAt(index);
            if (value == '[') {
                inBracket = true;
                index++;
            } else if (value == ']') {
                inBracket = false;
                index++;
            } else {
                if (hasABBA && inBracket == false) {
                    index++;
                    continue;
                }
                if (str.charAt(index) != str.charAt(index + 1) && str.charAt(index + 1) == str.charAt(index + 2) && str.charAt(index) == str.charAt(index + 3)) {
                    if (inBracket) {
                        return false;
                    } else {
                        hasABBA = true;
                    }
                }
                index++;
            }
        }

        return hasABBA;
    }
}
