public class Professional extends swimmerCompetitor{

    public Professional(int uniqueID, int age, String name, String email, String dob, String category, String level, int[] scores) {
        super(uniqueID, age, name, email, dob, category, level, scores);
    }

    public double getOverallScore() {
        double sum = 0;
        for (int score : scores) {
            sum += score;
        }
        double average = sum / scores.length;
        return Math.round(average * 10.0) / 10.0;
    }
}