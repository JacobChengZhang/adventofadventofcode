import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class Main {
    // assume that the puzzleInput is valid
    static final String inputFile = "./input.txt";

    void calcInputFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr = null;
            while ((tempStr = reader.readLine()) != null) {
                String[] strList = tempStr.split("-");
                checkAndCalc(strList);
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

    void checkAndCalc(String[] list) {
        String temp = list[list.length - 1]; // handle sectorID[checksum]
        int tempIndex = temp.indexOf('[');

        int sectorID = Integer.parseInt(temp.substring(0, tempIndex));

        int times = sectorID % 26; // As we all know, there are 26 characters in English.

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.length - 1; i++) {
            String str = list[i];
            for (int j = 0; j < str.length(); j++) {
                sb.append((char)shiftChar(str.charAt(j), times));
            }
            sb.append(" ");
        }

        if (sb.toString().contains("northpole")) {
            System.out.println(sectorID);
        }
    }

    int shiftChar(int ch, int times) {
        // ascii 97 -> 122
        return (97 + ((ch - 97 + times) % 26));
    }

    public static void main(String[] args) {
        Main m = new Main();

        m.calcInputFile(inputFile);
    }
}
