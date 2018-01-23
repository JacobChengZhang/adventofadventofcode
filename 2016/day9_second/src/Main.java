import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    // assume that the puzzleInput is valid
    static final String input = "./input.txt";

    public static void main(String[] args) {
        getInput(input);
    }

    private static void getInput(String filepath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(calc(str));
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

    private static long calc(String s) {
        long length = s.length();
        long index = 0;
        long count = 0;

        while(index < length) {
            if (s.charAt((int)index) != '(') {
                index++;
                count++;
            }
            else {
                long index2 = s.indexOf(')', (int)index);
                String[] tempList = s.substring((int)index + 1, (int)index2).split("x");
                //count += Integer.parseInt(tempList[0]) * Integer.parseInt(tempList[1]);
                count += Integer.parseInt(tempList[1]) * calc(s.substring((int)index2 + 1, (int)index2 + 1 + Integer.parseInt(tempList[0])));
                index = index2 + 1 + Integer.parseInt(tempList[0]);
            }
        }

        return count;
    }
}
