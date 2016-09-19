package nju.financecity_android.util;

/**
 * Created by coral on 16-9-13.
 */
public class StringUtil {
    public static int parseInt(String s) {
        if (s.matches("[0-9]+")) {
            return Integer.parseInt(s);
        }
        return 0;
    }
}
