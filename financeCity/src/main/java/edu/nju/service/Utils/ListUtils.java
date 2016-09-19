package edu.nju.service.Utils;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Created by Sun YuHao on 2016/9/7.
 */
public class ListUtils {
    public static void eliminateDumplicatedString(List<String> list) {
        if (list == null || list.size() == 0) {
            return;
        }

        HashSet<String> h  =   new HashSet<>(list);
        list.clear();
        list.addAll(h);
    }
}
