import java.util.HashMap;
import java.util.Map;

public class scores {
    private Map<Integer, Integer> scores;

    public scores() {
        scores = new HashMap<>();
    }

    public void addScore(int competitorID, int score) {
        scores.put(competitorID, score);
    }

    public int getScore(int competitorID) {
        return scores.getOrDefault(competitorID, 0);
    }



    public double getAverageScore() {
        if (scores.isEmpty()) {
            return 0.0;
        }

        double totalScore = 0;
        for (double score : scores.values()) {
            totalScore += score;
        }

        return totalScore / scores.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Scores: ");
        for (Map.Entry<Integer, Integer> entry : scores.entrySet()) {
            sb.append("Swimmer ").append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
        }
        return sb.toString();
    }
}


