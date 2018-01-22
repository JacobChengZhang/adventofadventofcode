import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    // assume that the puzzleInput is valid
    static final String input = "./input.txt";

    static boolean[][] arr = new boolean[50][6];
    static int count = 0;
    static final int width = 50;
    static final int height = 6;

    public static void main(String[] args) {
        getInput(input);
        System.out.println(count());
    }

    private static void getInput(String filepath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                if (tempStr.startsWith("rect")) {
                    String[] tempList1 = tempStr.split("\\s");
                    String[] tempList2 = tempList1[1].split("x");

                    rect(Integer.parseInt(tempList2[0]), Integer.parseInt(tempList2[1]));
                } else if (tempStr.startsWith("rotate row")) {
                    String[] tempList1 = tempStr.split("\\s");

                    rotate(true, Integer.parseInt(tempList1[2].substring(2)), Integer.parseInt(tempList1[4]));
                } else if (tempStr.startsWith("rotate column")) {
                    String[] tempList1 = tempStr.split("\\s");

                    rotate(false, Integer.parseInt(tempList1[2].substring(2)), Integer.parseInt(tempList1[4]));
                } else {
                    System.out.println("Fxcking bug in input file!");
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

    private static void rect(int i, int j) {
        for (int y = 0; y < j; y++) {
            for (int x = 0; x < i; x++) {
                arr[x][y] = true;
            }
        }
    }

    private static void rotate(boolean isRow, int i, int num) {
        if (isRow) {
            boolean[] tempRow = new boolean[width];
            num %= 50;
            for (int x = 0; x < width; x++) {
                tempRow[(x + num) % width] = arr[x][i];
            }

            for (int x = 0; x < width; x++) {
                arr[x][i] = tempRow[x];
            }
        } else { // column
            boolean[] tempColumn = new boolean[height];
            num %= 6;
            for (int y = 0; y < height; y++) {
                tempColumn[(y + num) % height] = arr[i][y];
            }

            for (int y = 0; y < height; y++) {
                arr[i][y] = tempColumn[y];
            }
        }
    }

    private static int count() {
        int count = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (arr[x][y] == true) {
                    count++;
                }
            }
        }

        return count;
    }

    private static void print() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(arr[x][y]);
            }

            System.out.print("\n");
        }
    }
}
