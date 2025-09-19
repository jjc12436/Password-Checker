//Interfaece for rules

import java.util.List;

public interface Rule {
    int score(PasswordContext ctx);                 // +/- points
    void addTips(PasswordContext ctx, List<String> tips); // suggestions if needed
}

