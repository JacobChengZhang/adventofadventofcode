import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class Main {
    // assume that the puzzleInput is valid
    static final String input = "./input.txt";

    static Bot[] outputArr = new Bot[40]; // since max index of output box is 19
    static Bot[] botArr = new Bot[400]; // 400 is enough I guess

    static ArrayList<String> valueList = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < outputArr.length; i++) {
            outputArr[i] = new Bot(i, true);
        }

        for (int i = 0; i < botArr.length; i++) {
            botArr[i] = new Bot(i, false);
        }

        getInput(input);
    }

    private static void getInput(String filepath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            String str;
            while ((str = reader.readLine()) != null) {
                parseString(str);
            }

            proceedChips();
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

    private static void parseString(String str) {
        if (str.startsWith("value")) {
            valueList.add(str);
        } else {
            String[] strList = str.split(" ");

            try {
                if (strList[5].equals("output")) {
                    botArr[Integer.parseInt(strList[1])].botLow = outputArr[Integer.parseInt(strList[6])];
                } else {
                    botArr[Integer.parseInt(strList[1])].botLow = botArr[Integer.parseInt(strList[6])];
                }

                if (strList[10].equals("output")) {
                    botArr[Integer.parseInt(strList[1])].botHigh = outputArr[Integer.parseInt(strList[11])];
                } else {
                    botArr[Integer.parseInt(strList[1])].botHigh = botArr[Integer.parseInt(strList[11])];
                }
            } catch (Exception e) {
                System.out.println("Error in parsing");
                System.out.println(Integer.parseInt(strList[1]) + " " + Integer.parseInt(strList[6]) + " " + Integer.parseInt(strList[11]));
            }
        }
    }

    private static void proceedChips() {
        for (String str : valueList) {
            String[] strList = str.split(" ");
            botArr[Integer.parseInt(strList[5])].proceed(Integer.parseInt(strList[1]));
        }
    }

    private static class Bot {
        Bot botLow = null;
        Bot botHigh = null;

        int chipLow = -1;
        int chipHigh = -1;

        int seq = -1;
        boolean isOutput;

        Bot(int seq, boolean isOutput) {
            this.seq = seq;
            this.isOutput = isOutput;
        }

        void proceed(int value) {
            if (chipLow == -1) {
                chipLow = value;
            } else {
                if (chipLow <= value) {
                    chipHigh = value;
                } else {
                    chipHigh = chipLow;
                    chipLow = value;
                }

                if (chipLow == 17 && chipHigh == 61) {
                    System.out.println("seq " + seq);
                }

                if (botLow != null && botHigh != null) {
                    botLow.proceed(chipLow);
                    botHigh.proceed(chipHigh);
                    chipLow = -1;
                    chipHigh = -1;
                    //System.out.println("low chip to bot" + botLow.seq + ". high chip to bot" + botHigh.seq);
                } else {
                    System.out.println("Bug appears.");
                }
            }
        }
    }
}
