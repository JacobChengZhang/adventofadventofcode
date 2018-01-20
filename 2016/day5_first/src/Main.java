import java.io.UnsupportedEncodingException;
import java.security.*;

public class Main {
    // assume that the puzzleInput is valid
    static final String input = "a";//"cxdnnyjw";

    byte[] message = null;
    MessageDigest md = null;
    byte[] digest = null;

    void md5() {
        try {
            message = input.getBytes("UTF-8");
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

        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Main m = new Main();

        m.md5();
    }
}
