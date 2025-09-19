import java.util.Collections;
import java.util.List;

public class EvaluationResult {
    private final int score;             
    private final List<String> tips;      
    private final String crackTime;       

    public EvaluationResult(int score, List<String> tips, String crackTime) {
        this.score = score;
        this.tips = Collections.unmodifiableList(tips);
        this.crackTime = crackTime;
    }
    public int getScore() { return score; }
    public List<String> getTips() { return tips; }
    public String getCrackTime() { return crackTime; }
}
