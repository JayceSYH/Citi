package nju.financecity_android.util;

/**
 * Created by coral on 16-9-11.
 */
public class DataParser {
    public static int parseInt(String s) {
        if (s.equals("null")) return 0;
        return Integer.parseInt(s);
    }

    public static double parseDouble(String s) {
        if (s.equals("null")) return 0;
        return Double.parseDouble(s);
    }
}
