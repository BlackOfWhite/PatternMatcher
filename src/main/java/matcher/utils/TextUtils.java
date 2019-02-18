package matcher.utils;

import java.util.ArrayList;
import java.util.List;

public class TextUtils {

  /**
   * Split string and remove escape characters preceding wildcards.
   *
   * @param pattern string that will be split into words
   * @param wildcard wildcard character
   * @param escChar character that can be used to escape wildcard, only if it appears right before wildcard
   * @return List of words after pattern split.
   */
  public static List<String> splitPattern(final String pattern, char wildcard, char escChar) {
    List<String> list = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < pattern.length(); i++) {
      char c = pattern.charAt(i);
      if (i < pattern.length() - 1 && c == escChar && pattern.charAt(i + 1) == wildcard) {
        sb.append(wildcard);
        i++;
      } else if (c == wildcard) {
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
}
