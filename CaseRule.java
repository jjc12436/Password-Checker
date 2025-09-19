import java.util.List;

public class CaseRule implements Rule {
    @Override public int score(PasswordContext ctx) {
        boolean lower = ctx.password().chars().anyMatch(Character::isLowerCase);
        boolean upper = ctx.password().chars().anyMatch(Character::isUpperCase);
        return (lower && upper) ? 2 : 0;
    }
    @Override public void addTips(PasswordContext ctx, List<String> tips) {
        boolean lower = ctx.password().chars().anyMatch(Character::isLowerCase);
        boolean upper = ctx.password().chars().anyMatch(Character::isUpperCase);
        if (!(lower && upper)) tips.add("Mix UPPER and lower case letters.");
    }
}
