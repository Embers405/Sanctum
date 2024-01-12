package io.ruin.utility;

import java.security.SecureRandom;
import java.util.List;

/**
 * Basic utility class for anything extra we need since the other one is in a separate dependency...
 */
public final class Utils {

    private Utils() {
    }

    public static <T> T randomTypeOfList(List<T> list) {
        if (list == null || list.size() == 0)
            return null;
        return list.get(new SecureRandom().nextInt(list.size()));
    }

    public static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }


    public static String formatMoneyString(int amount) {
        String rawString = String.format("%d", amount);
        int length = rawString.length();

        String result = rawString;
        if (length >= 13) {
            result = rawString.substring(0, rawString.length() - 12) + "," + rawString.substring(rawString.length() - 12, rawString.length() - 9) + "," + rawString.substring(rawString.length() - 9, rawString.length() - 6) + "," + rawString.substring(rawString.length() - 6, rawString.length() - 3) + "," + rawString.substring(rawString.length() - 3, rawString.length());
        } else if (length >= 10) {
            result = rawString.substring(0, rawString.length() - 9) + "," + rawString.substring(rawString.length() - 9, rawString.length() - 6) + "," + rawString.substring(rawString.length() - 6, rawString.length() - 3) + "," + rawString.substring(rawString.length() - 3, rawString.length());

        } else if (length >= 7) {
            result = rawString.substring(0, rawString.length() - 6) + "," + rawString.substring(rawString.length() - 6, rawString.length() - 3) + "," + rawString.substring(rawString.length() - 3, rawString.length());

        } else if (length >= 4) {
            result = rawString.substring(0, rawString.length() - 3) + "," + rawString.substring(rawString.length() - 3, rawString.length());
        }
        return result;
    }

    public static int largest(int[] arr) {
        int i;

        // Initialize maximum element
        int max = arr[0];

        // Traverse array elements from second and
        // compare every element with current max
        for (i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];

        return max;
    }

    public static int random(int i, int i1) {
        return i;
    }
}
