package StockExchange.util;

import java.util.Random;

/**
 * Utility class used to generate random char sequences
 */
public class RandomString {
    private static Random generator = new Random();

    private static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     *
     * @param length length of the returned random string
     * @return Random string
     */
    public static String nextString(int length) {
        if (length < 1) throw new IllegalArgumentException("String length cannot be less than 1");
        char[] buf = new char[length];
        char[] letters = RandomString.letters.toCharArray();
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = letters[generator.nextInt(letters.length)];
        return new String(buf);
    }
}
