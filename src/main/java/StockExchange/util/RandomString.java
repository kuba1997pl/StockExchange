package StockExchange.util;

import java.util.Random;

/**
 * Utility class used to generate random char sequences
 */
public class RandomString {
    private static Random generator = new Random();

    private static final String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lowercase = "abcdefghijklmnopqrstuwxyz";

    /**
     *
     * @param length length of the returned random string
     * @return Random string
     */
    public static String nextString(int length) {
        return nextString(length, upperCase + lowercase);
    }

    public static String nextString(int length, boolean onlyUppercase) {
        return nextString(length, upperCase);
    }

    private static String nextString(int length, String letters) {
        if (length < 1) throw new IllegalArgumentException("String length cannot be less than 1");
        char[] buf = new char[length];
        char[] given = letters.toCharArray();
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = given[generator.nextInt(given.length)];
        return new String(buf);
    }
}
