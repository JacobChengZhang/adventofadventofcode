import com.sun.istack.internal.NotNull;

import java.io.UnsupportedEncodingException;
import java.security.*;

public class Main {
    // assume that the puzzleInput is valid
    static final String input = "cxdnnyjw";

    byte[] message = null;
    MessageDigest md = null;
    byte[] digest = null;
    @NotNull
    char[] result = new char[8];

    void md5(int num) {

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

        if (sb.toString().startsWith("00000") && sb.charAt(5) >= 48 && sb.charAt(5) <= 55 && result[sb.charAt(5) - 48] == 0) {
            result[sb.charAt(5) - 48] = sb.charAt(6);
        }
    }

    boolean checkResult() {
        for (int i = 0; i < 8; i++) {
            if (result[i] == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Main m = new Main();

        for (int i = 0; !m.checkResult(); i++) {
            m.md5(i);
        }

        System.out.println(m.result);
    }
}
