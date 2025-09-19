import java.util.List;

public class BannedPatternRule implements Rule {
    private static final String[] BANNED = {"password","1234","qwerty","letmein","admin"};
    @Override public int score(PasswordContext ctx) {
        String lower = ctx.password().toLowerCase();
        for (String b : BANNED) if (lower.contains(b)) return -1;
        return 0;
    }
    @Override public void addTips(PasswordContext ctx, List<String> tips) {
        String lower = ctx.password().toLowerCase();
        for (String b : BANNED) {
            if (lower.contains(b)) { tips.add("Avoid common patterns like \"" + b + "\"."); break; }
        }
    }
}
