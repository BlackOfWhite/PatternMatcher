package suite;

import com.github.peterwippermann.junit4.parameterizedsuite.ParameterizedSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;

@RunWith(ParameterizedSuite.class)
@Suite.SuiteClasses({
        WildcardMatcherTest.class
})
public class TestSuite {
    @Parameterized.Parameter()
    public String pattern;
    @Parameterized.Parameter(1)
    public String text;
    @Parameterized.Parameter(2)
    public boolean result;

    @Parameterized.Parameters(name = "Parameters are {0}, {1} and {2}")
    public static Object[] params() {
        return new Object[][]{
                // Simple
                {"", "", true},
//                {"", "*", true},
//                {"", "a", false},
//                {"*", "*", true},
//                {"abc", "***", true},
//                {"abc", "**\\*", false},
//                {"abc", "**\\*", false},
//                {"abc", "******", true},
//                {"abc", "abc", true},
//                {"bLaH", "bLaH", true},
//                {"bLaH", "bLah", false},
//                {"ab", "a*b", true},
//                {"ac", "a*b", false},
//                {"cabd", "a**b", true},
//                {"cabd", "*a**b*", true},
//                {"a", "*a**b*", false},
//                {"aaaa", "a*a*a*a", true},
//                {"aaa", "a*a*a*a", false},
//                {"abc", "ab*d", false},
//                {"Hi", "Hi*", true},
//                {"abcccd", "*ccd", true},
//                {"a*abab", "a*b", true},
//                {"ar", "a*", true},
//                {"a*aar", "a*ar", true},
//                {"miss", "mississippi", false},
//                {"ababac", "*abac*", true},
//                {"a12b12", "*12*23", false},
//                {"a12b12", "a12b", true},
//                {"12b", "a12b", false},
//                {"a12b12", "*12*12*", true},
//
//
//                // Wildcard as character
//                {"*****", "*\\*", true},
//                { "**", "\\*\\*",true},
//                {"*abc", "\\***", true},
//                { "*a", "\\**",true},
//                { "a*a","\\**", true},
//                { "aa", "\\**",false},
//                { "", "\\***\\**",false},
//                { "*", "\\***\\**",false},
//                { "**","\\***\\**", true},
//                { "***", "\\***\\**",true},
//                {"abc","\\***",  false},
//
//                // Escape character used without wildcard
//                {"\\", "\\", true},
//                {"*\\*", "\\", true},
//                { "*\\*","\\\\*", true},
//                {"\\*\\", "\\\\\\*\\", false},
//                { "\\\\*\\","*\\\\*\\", true},
//                { "\\*","\\\\**bc", false},
//                { "\\\\**bcabcbcabcad","\\\\**bcad", true},
//                { "\\\\**bcabcbcabca","\\\\**bcad", false},
//
//                // Mixed
//                {"a*\\*b***c*d", "abcdefgh", false},
//                {"a*\\*b***c*d", "azebra*bcdefghcd", true},
//                {"a*\\*b***c*d", "azebra*zbcdefghcd", false},
//
//                // Extended
//                { "mississipissippi", "*issip*ss*",true},
//                {"xxxx*zzzzzzzzy*f", "xxxx*zzy\\*fffff", false},
//                {"xxxxzzzzzzzzy*f","xxx*zzy*f",  true},
//                { "xxxxzzzzzzzzyf", "xxxx*zzy*fffff",false},
//                { "xxxxzzzzzzzzyf","xxxx*zzy*f", true},
//                { "xyxyxyzyxyz","xy*z*xyz", true},
//                { "mississippi","*sip", true},
//                { "xyxyxyxyz", "xy*xyz",true},
//                { "mississippi","mi*sip*", true},
//
//                // More double wildcard scenarios
//                { "XYXYXYZYXYz", "XY*Z*XYz",true},
//                { "missisSIPpi", "*SIP*",true},
//                {"mississipPI","*issip*PI",  true},
//                { "xyxyxyxyz","xy*xyz", true},
//                {"miSsissippi","mi*sip*",  true},
//                {"miSsissippi","mi*Sip*",  false},
//                {"oWn","*oWn*",  true},
        };
    }
}

