import java.util.List;
import java.util.regex.Pattern;

public class SymbolRule implements Rule {
    private static final Pattern NON_ALNUM = Pattern.compile("[^A-Za-z0-9]");
    @Override public int score(PasswordContext ctx) {
        return NON_ALNUM.matcher(ctx.password()).find() ? 2 : 0;
    }
    @Override public void addTips(PasswordContext ctx, List<String> tips) {
        if (!NON_ALNUM.matcher(ctx.password()).find()) tips.add("Add a symbol (e.g., !@#$%).");
    }
}
