import java.io.UnsupportedEncodingException;
import java.security.*;

public class Main {
    // assume that the puzzleInput is valid
    static final String input = "cxdnnyjw";

    byte[] message = null;
    MessageDigest md = null;
    byte[] digest = null;

    char md5(int num) {

        try {
            message = (input + Integer.toString(num)).getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }

        md.update(message);
        digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            String hex = Integer.toHexString(0xFF & digest[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }

        if (sb.toString().startsWith("00000")) {
            return sb.charAt(5);
        }
        else {
            return '!';
        }
    }

    public static void main(String[] args) {
        Main m = new Main();

        StringBuilder sb = new StringBuilder();
        int times = 0;
        for (int i = 0; times < 8; i++) {
            char ch = m.md5(i);
            if (ch != '!') {
                sb.append(ch);
                times++;
            }
        }

        System.out.println(sb.toString());
    }
}
