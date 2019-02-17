import com.github.peterwippermann.junit4.parameterizedsuite.ParameterContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import wildcard.matcher.WildcardMatcher;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class WildcardMatcherTest {

    final private String pattern;
    final private String text;
    final private boolean result;

    @Parameterized.Parameters(name = "Parameters are {0}, {1} and {2}")
    public static List params() {
        if (ParameterContext.isParameterSet()) {
            return Collections.singletonList(ParameterContext.getParameter(Object[].class));
        } else {
            // if the test case is not executed as part of a ParameterizedSuite, you can define fallback parameters
        }
        return Collections.EMPTY_LIST;
    }

    public WildcardMatcherTest(String pattern, String text, boolean result) {
        super();
        this.pattern = pattern;
        this.text = text;
        this.result = result;
    }

    @Test
    public void matchTest() {
        assertEquals("Pattern: " + pattern + ", text: " + text, result, new WildcardMatcher().matches(pattern, text));
    }
}
