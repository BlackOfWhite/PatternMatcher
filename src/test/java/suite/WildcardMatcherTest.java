package suite;

import static org.junit.Assert.assertEquals;

import com.github.peterwippermann.junit4.parameterizedsuite.ParameterContext;
import java.util.Collections;
import java.util.List;
import matcher.WildcardMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class WildcardMatcherTest {

  final private String pattern;
  final private String text;
  final private boolean result;
  final private WildcardMatcher matcher = new WildcardMatcher();

  public WildcardMatcherTest(String pattern, String text, boolean result) {
    super();
    this.pattern = pattern;
    this.text = text;
    this.result = result;
  }

  @Parameterized.Parameters(name = "Parameters are {0}, {1} and {2}")
  public static List params() {
    if (ParameterContext.isParameterSet()) {
      return Collections.singletonList(ParameterContext.getParameter(Object[].class));
    } else {
      // if the test case is not executed as part of a ParameterizedSuite, you can define fallback parameters
    }
    return Collections.EMPTY_LIST;
  }

  @Test
  public void matchTest() {
    assertEquals("Pattern: " + pattern + ", text: " + text, result, matcher.matches(pattern, text));
  }
}
