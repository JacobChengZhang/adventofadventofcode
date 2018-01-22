import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    // assume that the puzzleInput is valid
    static final String input = "./input.txt";

    static List<String> outsideStrList = new ArrayList<>();
    static List<String> insideStrList = new ArrayList<>();

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
        parseString(str);
        for (String outListStr : outsideStrList) {
            for (int i = 0; i < outListStr.length() - 2; i++) {
                char ch0 = outListStr.charAt(i);
                char ch1 = outListStr.charAt(i + 1);
                char ch2 = outListStr.charAt(i + 2);
                if (ch0 != ch1 && ch2 == ch0) {
                    String bab = new StringBuilder().append(ch1).append(ch0).append(ch1).toString();
                    if (findBAB(bab)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static void parseString(String str) {
        outsideStrList.clear();
        insideStrList.clear();

        int value = 0;
        int startIndex = 0;
        int endIndex = 0;
        boolean inBracket = false;

        while (endIndex < str.length()) {
            value = str.charAt(endIndex);
            if (value == '[') {
                outsideStrList.add(str.substring(startIndex, endIndex));
                startIndex = endIndex + 1;
                endIndex = startIndex;
                inBracket = true;

            } else if (value == ']') {
                insideStrList.add(str.substring(startIndex, endIndex));
                startIndex = endIndex + 1;
                endIndex = startIndex;
                inBracket = false;
            } else {
                endIndex++;
            }
        }

        if (startIndex < endIndex) {
            outsideStrList.add(str.substring(startIndex, str.length()));
        }

        outsideStrList.removeIf(String::isEmpty);
        insideStrList.removeIf(String::isEmpty);
    }

    private static boolean findBAB(String str) {
        for (String inListStr : insideStrList) {
            if (inListStr.contains(str)) {
                return true;
            }
        }

        return false;
    }
}
