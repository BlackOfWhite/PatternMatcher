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
                {"*", "", true},
                {"a", "", false},
                {"*", "*", true},
                {"***", "abc", true},
                {"**\\*", "abc", false},
                {"**\\*", "abc", false},
                {"******", "abc", true},
                {"abc", "abc", true},
                {"bLaH", "bLaH", true},
                {"bLah", "bLaH", false},
                {"a*b", "ab", true},
                {"a*b", "ac", false},
                {"a**b", "cabd", true},
                {"*a**b*", "cabd", true},
                {"*a**b*", "a", false},
                {"a*a*a*a", "aaaa", true},
                {"a*a*a*a", "aaa", false},
                {"ab*d", "abc", false},
                {"Hi*", "Hi", true},
                {"*ccd", "abcccd", true},
                {"a*b", "a*abab", true},
                {"a*", "ar", true},
                {"a*ar", "a*aar", true},
                {"mississippi", "miss", false},
                {"*abac*", "ababac", true},
                {"*12*23", "a12b12", false},
                {"a12b", "a12b12", true},
                {"a12b", "12b", false},
                {"*12*12*", "a12b12", true},


                // Wildcard as character
                {"*\\*", "*****", true},
                {"\\*\\*", "**", true},
                {"\\***", "*abc", true},
                {"\\**", "*a", true},
                {"\\**", "a*a", true},
                {"\\**", "aa", false},
                {"\\***\\**", "", false},
                {"\\***\\**", "*", false},
                {"\\***\\**", "**", true},
                {"\\***\\**", "***", true},
                {"\\***", "abc", false},

                // Escape character used without wildcard
                {"\\", "\\", true},
                {"\\", "*\\*", true},
                {"\\\\*", "*\\*", true},
                {"\\\\\\*\\", "\\*\\", false},
                {"*\\\\*\\", "\\\\*\\", true},
                {"\\\\**bc", "\\*", false},
                {"\\\\**bcad", "\\\\**bcabcbcabcad", true},
                {"\\\\**bcad", "\\\\**bcabcbcabca", false},

                // Mixed
                {"a*\\*b***c*d", "abcdefgh", false},
                {"a*\\*b***c*d", "azebra*bcdefghcd", true},
                {"a*\\*b***c*d", "azebra*zbcdefghcd", false},

                // Extended
                {"*issip*ss*", "mississipissippi", true},
                {"xxxx*zzy\\*fffff", "xxxx*zzzzzzzzy*f", false},
                {"xxx*zzy*f", "xxxxzzzzzzzzy*f", true},
                {"xxxx*zzy*fffff", "xxxxzzzzzzzzyf", false},
                {"xxxx*zzy*f", "xxxxzzzzzzzzyf", true},
                {"xy*z*xyz", "xyxyxyzyxyz", true},
                {"*sip", "mississippi", true},
                {"xy*xyz", "xyxyxyxyz", true},
                {"mi*sip*", "mississippi", true},

                // More double wildcard scenarios
                {"XY*Z*XYz", "XYXYXYZYXYz", true},
                {"*SIP*", "missisSIPpi", true},
                {"*issip*PI", "mississipPI", true},
                {"xy*xyz", "xyxyxyxyz", true},
                {"mi*sip*", "miSsissippi", true},
                {"mi*Sip*", "miSsissippi", false},
                {"*oWn*", "oWn", true},


                // Many-wildcard scenarios.
                {"a*a*a*a*a*a*aa*aaa*a*a*b", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                        true},
                {"*a*b*ba*ca*a*aa*aaa*fa*ga*b*", "abababababababababababababababababababaacacacacacacacadaeafagahaiajakalaaaaaaaaaaaaaaaaaffafagaagggagaaaaaaaab", true}
//        bAllPassed &= test("abababababababababababababababababababaacacacacaca\
//                cacadaeafagahaiajakalaaaaaaaaaaaaaaaaaffafagaagggagaaaaaaaab",
//                "*a*b*ba*ca*a*x*aaa*fa*ga*b*", false);
//        bAllPassed &= test("abababababababababababababababababababaacacacacaca\
//                cacadaeafagahaiajakalaaaaaaaaaaaaaaaaaffafagaagggagaaaaaaaab",
//                "*a*b*ba*ca*aaaa*fa*ga*gggg*b*", false);
//        bAllPassed &= test("abababababababababababababababababababaacacacacaca\
//                cacadaeafagahaiajakalaaaaaaaaaaaaaaaaaffafagaagggagaaaaaaaab",
//                "*a*b*ba*ca*aaaa*fa*ga*ggg*b*", true);
//        bAllPassed &= test("aaabbaabbaab", "*aabbaa*a*", true);
//        bAllPassed &= test("a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*",
//                "a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*", true);
//        bAllPassed &= test("aaaaaaaaaaaaaaaaa",
//                "*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*", true);
//        bAllPassed &= test("aaaaaaaaaaaaaaaa",
//                "*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*", false);
//        bAllPassed &= test("abc*abcd*abcde*abcdef*abcdefg*abcdefgh*abcdefghi*a\
//                bcdefghij * abcdefghijk * abcdefghijkl * abcdefghijklm * abcdefghijklmn",
//                "abc*abc*abc*abc*abc*abc*abc*abc*abc*abc*abc*abc*abc*abc*abc*abc*a\
//                bc * ", false);
//                bAllPassed &= test("abc*abcd*abcde*abcdef*abcdefg*abcdefgh*abcdefghi*a\
//                        bcdefghij * abcdefghijk * abcdefghijkl * abcdefghijklm * abcdefghijklmn",
//                        "abc*abc*abc*abc*abc*abc*abc*abc*abc*abc*abc*abc*", true);
//        bAllPassed &= test("abc*abcd*abcd*abc*abcd", "abc*abc*abc*abc*abc",
//                false);
//        bAllPassed &= test(
//                "abc*abcd*abcd*abc*abcd*abcd*abc*abcd*abc*abc*abcd",
//                "abc*abc*abc*abc*abc*abc*abc*abc*abc*abc*abcd", true);
//        bAllPassed &= test("abc", "********a********b********c********",
//                true);
//        bAllPassed &= test("********a********b********c********", "abc",
//                false);
//        bAllPassed &= test("abc", "********a********b********b********",
//                false);
//        bAllPassed &= test("*abc*", "***a*b*c***", true);

        };
    }
}

