import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class Main {
    // assume that the puzzleInput is valid
    static final String inputFile = "./input.txt";

    int sum = 0;

    int[] arr = new int[123]; // ascii of 'z' is 122

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

    void clearArr() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
    }

    void checkAndCalc(String[] list) {
        String temp = list[list.length - 1]; // handle sectorID[checksum]
        int tempIndex = temp.indexOf('[');

        int sectorID = Integer.parseInt(temp.substring(0, tempIndex));
        String checksum = temp.substring(tempIndex + 1, temp.length() - 1);

        clearArr();

        for (int i = 0; i < list.length - 1; i++) {
            String str = list[i];
            for (int j = 0; j < str.length(); j++) {
                arr[str.charAt(j)]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        int times = 5;
        while (times > 0) {
            int maxVal = 0;
            int index = 0;
            for (int i = 97; i < arr.length; i++) {
                if (arr[i] > maxVal) {
                    index = i;
                    maxVal = arr[i];
                }
            }

            arr[index] = 0;

            sb.append((char)index);
            times--;
        }


        if (checksum.equals(sb.toString())) {
            sum += sectorID;
        }
    }

    public static void main(String[] args) {
        Main m = new Main();

        m.calcInputFile(inputFile);

        System.out.println(m.sum);
    }
}
