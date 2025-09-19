import java.util.List;

public class LengthRule implements Rule {
    @Override public int score(PasswordContext ctx) {
        int len = ctx.password().length();
        if (len >= 12) return 2;
        if (len >= 8)  return 1;
        return 0;
    }
    @Override public void addTips(PasswordContext ctx, List<String> tips) {
        if (ctx.password().length() < 12) tips.add("Use at least 12 characters.");
    }
}
