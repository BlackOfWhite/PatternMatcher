package matcher;

import static matcher.utils.TextUtils.splitPattern;

import java.util.Objects;

public class WildcardMatcher {

  private static final char DEFAULT_WILDCARD = '*';
  private static final char DEFAULT_ESC_CHAR = '\\';
  private final char WILDCARD;
  private final char ESC_CHAR;

  public WildcardMatcher() {
    this(DEFAULT_WILDCARD, DEFAULT_ESC_CHAR);
  }

  public WildcardMatcher(char wildcard, char escapeChar) {
    this.WILDCARD = wildcard;
    this.ESC_CHAR = escapeChar;
  }

  /**
   * Matches the given string against the expressions of this matcher.
   *
   * @param pattern pattern to find
   * @param text string to find pattern within
   * @return <code>true</code>, if the expression matches
   */
  public boolean matches(final String text, final String pattern) {
    Objects.requireNonNull(text, "Text must not be null.");
    Objects.requireNonNull(pattern, "Pattern must not be null.");
    if (pattern.equals(text)) {
      return true;
    }
    if (pattern.isEmpty()) {
      return false;
    }
    return matchPattern(text, pattern);
  }

  /**
   * Search for a pattern in a text. Loop through list of words separated by {@link #WILDCARD} and try to find each of them in the given text.
   *
   * @return <code>true</code> if text contains pattern, <code>false</code> if it does not.
   */
  private boolean matchPattern(final String text, final String pattern) {
    int ti = -1;
    for (String word : splitPattern(pattern, WILDCARD, ESC_CHAR)) {
      ti += word.length();
      while (true) {
        if (ti >= text.length()) {
          return false;
        }
        int offset = getPatternOffset(word, text, ti);
        if (offset > 0) {
          ti += offset;
        } else {
          break;
        }
      }
    }
    return true;
  }

  /**
   * Check if given pattern exists in the text.
   *
   * @param word pattern to find in text
   * @param text text containing the pattern
   * @param index position in text to start to search from
   * @return offset greater than zero if at least one character in the text does not match pattern's character at the respective position.
   * Offset equal zero means that pattern was found.
   */
  private int getPatternOffset(final String word, final String text, int index) {
    int offset = 0;
    char c = 0;
    boolean mismatch = false;
    for (int p = word.length() - 1; p >= 0; p--) {
      if (!mismatch && (c = text.charAt(index--)) != word.charAt(p)) {
        offset++;
        mismatch = true;
      } else if (mismatch) {
        if (c == word.charAt(p)) {
          break;
        }
        offset++;
      }
    }
    return offset;
  }
}
