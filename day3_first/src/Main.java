import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    // assume that the puzzleInput is valid
    static final String inputFile = "./input.txt";

    int count = 0;

    void calcInputFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr = null;
            while ((tempStr = reader.readLine()) != null) {
                String[] strList = tempStr.split("\\s+");

                // since strList[0] is "", ignore it
                if (isPossible(Integer.parseInt(strList[1]), Integer.parseInt(strList[2]), Integer.parseInt(strList[3]))) {
                    count++;
                }
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

    boolean isPossible(int a, int b, int c) {
        if (a + b <= c || b + c <= a || c + a <= b) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Main m = new Main();

        m.calcInputFile(inputFile);

        System.out.println(m.count);
    }
}
