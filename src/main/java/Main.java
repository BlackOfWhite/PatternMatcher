import wildcard.matcher.WildcardMatcher;

public class Main {

    public static void main(String[] args) {
        String text = null;
        String pattern = null;
        if (args.length > 0) {
            if (args.length == 2) {
                text = args[0];
                pattern = args[1];
            } else {
                throw new IllegalArgumentException("Two arguments are required. Found: " + args.length);
            }
        } else {
            //        Console console = new Console();
        }
        boolean matches = new WildcardMatcher().matches(text, pattern);
        System.out.println("Pattern does" + (matches ? "" : " not") + " match text.");
    }

}
