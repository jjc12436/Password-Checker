import java.io.Console;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class PasswordChecker {
    public static void main(String[] args) {
        String pw = (args.length > 0) ? args[0] : prompt("Enter password: ");

        List<Rule> rules = List.of(
            new LengthRule(),
            new CaseRule(),
            new DigitRule(),
            new SymbolRule(),
            new BannedPatternRule()
        );

        PasswordEvaluator evaluator = new PasswordEvaluator(rules);
        EvaluationResult r = evaluator.evaluate(new PasswordContext(pw));

        System.out.println("Score: " + r.getScore() + "/10");
        System.out.println("Estimated crack time: " + r.getCrackTime());
        if (r.getTips().isEmpty()) {
            System.out.println("Tips: (none) — looking strong!");
        } else {
            System.out.println("Tips:");
            for (String t : r.getTips()) System.out.println("  • " + t);
        }
    }

    private static String prompt(String label) {
        Console c = System.console();
        if (c != null) {
            char[] ch = c.readPassword(label);
            return new String(ch);
        }
        System.out.print(label);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
