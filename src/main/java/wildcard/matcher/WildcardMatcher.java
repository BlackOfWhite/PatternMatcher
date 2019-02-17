package wildcard.matcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WildcardMatcher {

    private static final char WILDCARD = '*';
    private static final char ESC_CHAR = '\\';

    public boolean matches(String pattern, String text) {
        Objects.requireNonNull(pattern, "Pattern must not be null.");
        Objects.requireNonNull(text, "Text must not be null.");
        if (pattern.equals(text)) {
            return true;
        }
        if (pattern.isEmpty()) {
            return false;
        }
        return matchPattern(pattern, text);
    }

    private boolean matchPattern(String pattern, final String text) {
//        pattern = reducePattern(pattern);
        int l = 0; // text last matching position
        // O(n), n -> pattern length
        boolean proceed;
        for (String s : splitString(pattern)) {
            proceed = false;
            while (!proceed) {
                int tmp = l;
                int i = 0;
                try {
                    while (i < s.length() && text.charAt(tmp++) == s.charAt(i)) {
                        i++;
                    }
                } catch (IndexOutOfBoundsException e) {
                    return false;
                }
                if (i == s.length()) {
                    l += i; // match found, proceed with next word
                    proceed = true;
                } else {
                    l++; // word does not match
                }
            }
        }
        return true;
    }

    /**
     * Split string and remove escape characters preceding wildcards.
     *
     * @param pattern
     * @return List of words after splitting pattern.
     */
    private static List<String> splitString(String pattern) {
//        String[] split = pattern.split("(?<!\\\\)\\*");
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (i < pattern.length() - 1 && c == ESC_CHAR && pattern.charAt(i + 1) == WILDCARD) {
                sb.append(WILDCARD);
                i++;
            } else if (c == WILDCARD) {
                if (sb.length() > 0) {
                    list.add(sb.toString());
                    sb.setLength(0);
                }
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0) {
            list.add(sb.toString());
        }
        return list;
    }

    private static String reducePattern(String pattern) {
        StringBuilder sb = new StringBuilder();
        boolean wildcardFound = false;
        for (int i = 0; i < pattern.length(); i++) {
            final char c = pattern.charAt(i);
            if (c == WILDCARD && ((i > 0 && pattern.charAt(i - 1) != ESC_CHAR) || i == 0)) {
                if (!wildcardFound) {
                    wildcardFound = true;
                    sb.append(WILDCARD);
                }
                continue;
            }
            sb.append(c);
            wildcardFound = false;
        }
        return sb.toString();
    }
}
