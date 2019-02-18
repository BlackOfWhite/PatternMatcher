import java.util.Scanner;
import matcher.WildcardMatcher;

public class Main {

  public static void main(String[] args) {
    String text;
    String pattern;
    if (args.length > 0) {
      if (args.length == 2) {
        text = args[0];
        pattern = args[1];
      } else {
        throw new IllegalArgumentException("Two arguments are required. Found: " + args.length);
      }
    } else {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter text:");
      text = scanner.nextLine();
      System.out.println("Enter pattern to search for:");
      pattern = scanner.nextLine();
    }
    boolean matches = new WildcardMatcher().matches(text, pattern);
    System.out.println("Pattern does" + (matches ? "" : " not") + " match text.");
  }
}
