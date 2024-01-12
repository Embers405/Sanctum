package io.ruin.api.utils;

import java.util.Map;

public class XenPost {

    private static final String URL = "https://obsidian-ps.com/intergration/index.php";
    private static final String AUTH = "s5T4zaigCbbs1p5gKuVr1RZ9THiFst";

    public static String post(String file, Map<Object, Object> map) {
        if (true) {
            return null;
        }
        map.put("auth", AUTH);
        map.put("file", file);
        return PostWorker.postArray(URL, map);
    }

}
