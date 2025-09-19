import java.util.List;

public class DigitRule implements Rule {
    @Override public int score(PasswordContext ctx) {
        return ctx.password().chars().anyMatch(Character::isDigit) ? 2 : 0;
    }
    @Override public void addTips(PasswordContext ctx, List<String> tips) {
        if (ctx.password().chars().noneMatch(Character::isDigit)) tips.add("Add at least one number (0-9).");
    }
}
