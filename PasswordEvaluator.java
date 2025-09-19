import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PasswordEvaluator {
    private final List<Rule> rules;
    public PasswordEvaluator(List<Rule> rules) { this.rules = rules; }

    public EvaluationResult evaluate(PasswordContext ctx) {
        int score = 0;
        List<String> tips = new ArrayList<>();

        for (Rule r : rules) {
            score += r.score(ctx);
            r.addTips(ctx, tips);
        }

        score = Math.max(0, Math.min(10, score));
        if (score >= 9) tips.clear();

        String crackTime = roughCrackTime(ctx.password());
        return new EvaluationResult(score, tips, crackTime);
    }

    private String roughCrackTime(String pw) {
        boolean lower = pw.chars().anyMatch(Character::isLowerCase);
        boolean upper = pw.chars().anyMatch(Character::isUpperCase);
        boolean digit = pw.chars().anyMatch(Character::isDigit);
        boolean symb  = Pattern.compile("[^A-Za-z0-9]").matcher(pw).find();

        int charset = 0;
        if (lower) charset += 26;
        if (upper) charset += 26;
        if (digit) charset += 10;
        if (symb)  charset += 33;
        if (charset == 0) charset = 1;

        double guesses = Math.pow(charset, pw.length());
        double seconds = guesses / 1e10;
        return humanize(seconds);
    }

    private String humanize(double s) {
        if (s < 1) return "less than 1 second";
        String[] units = {"sec's","min's","hr's","day's","yr's","centurys"};
        double[] div = {60,60,24,365,100};
        int i = 0;
        while (i < div.length && s >= div[i]) { s /= div[i]; i++; }
        return String.format(java.util.Locale.US, "%.1f %s", s, units[i]);
    }
}
