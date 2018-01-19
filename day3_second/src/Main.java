import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    // assume that the puzzleInput is valid
    static final String inputFile = "./input.txt";

    int count = 0;

    // Since the input file has 1911 rows, and 1911 is divisible by 3. So I'd omit the check of lines
    void calcInputFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr1 = null;
            String tempStr2 = null;
            String tempStr3 = null;
            while ((tempStr1 = reader.readLine()) != null) {
                tempStr2 = reader.readLine();
                tempStr3 = reader.readLine();

                String[] list1 = tempStr1.split("\\s+");
                String[] list2 = tempStr2.split("\\s+");
                String[] list3 = tempStr3.split("\\s+");

                // since strList[0] is "", ignore it
                if (isPossible(Integer.parseInt(list1[1]), Integer.parseInt(list2[1]), Integer.parseInt(list3[1]))) {
                    count++;
                }
                if (isPossible(Integer.parseInt(list1[2]), Integer.parseInt(list2[2]), Integer.parseInt(list3[2]))) {
                    count++;
                }
                if (isPossible(Integer.parseInt(list1[3]), Integer.parseInt(list2[3]), Integer.parseInt(list3[3]))) {
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
