import wildcard.matcher.WildcardMatcher;

public class Main {

    public static void main(String[] args) {
        String pattern = null;
        String text = null;
        if (args.length > 0) {
            if (args.length == 2) {
                pattern = args[0];
                text = args[1];
            } else {
                throw new IllegalArgumentException("Two arguments are required. Found: " + args.length);
            }
        } else {
            //        Console console = new Console();
        }
        boolean ok = new WildcardMatcher().matches(pattern, text);
        System.out.println("Pattern does" + (ok ? "" : " not") + " match text.");
    }

}
