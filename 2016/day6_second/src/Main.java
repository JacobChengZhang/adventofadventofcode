import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;

public class Main {
    // assume that the puzzleInput is valid
    static final String input = "./input.txt";

    public static void main(String[] args) {
        List<String> list = zip(getInput(input));

        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append((char)getErrorCorrectedChar(str));
        }

        System.out.println(sb.toString());
    }

    private static List<String> zip(List<String> strings) {
        int length = strings.get(0).length();
        return IntStream
                .range(0, length)
                .mapToObj(i -> {
                    StringBuilder sb = new StringBuilder();
                    for (String s : strings) {
                        sb.append(s.charAt(i));
                    }
                    return sb.toString();
                })
                .collect(toList());
    }

    private static List<String> getInput(String filepath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            String tempStr = null;
            List<String> list = new ArrayList<>();
            while ((tempStr = reader.readLine()) != null) {
                list.add(tempStr);
            }
            reader.close();
            return list;
        }
        catch (IOException ioex) {
            ioex.printStackTrace();
            return null;
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

    private static int getErrorCorrectedChar(String str) {
        int[] arr = new int[123]; // ascii of 'z' is 122
        for (int i = 0; i < str.length(); i++) {
            arr[str.charAt(i)]++;
        }

        int minVal = Integer.MAX_VALUE;
        int index = 97; // ascii of 'a'

        for (int i = index; i < arr.length; i++) {
            if (arr[i] < minVal) {
                index = i;
                minVal = arr[i];
            }
        }

        return index;
    }
}
