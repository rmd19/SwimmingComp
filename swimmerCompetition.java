import java.util.ArrayList;

public class swimmerCompetition {
    private String competitionName;
    private String date;
    private String category;
    private ArrayList<swimmerCompetitor> competitorList;
    private scores scores;
    private String swimmingStroke;

    public swimmerCompetition() {
        this.competitionName = "";
        this.date = "";
        this.category = "";
        this.competitorList = new ArrayList<>();
        this.scores = new scores();
        this.swimmingStroke = "";
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<swimmerCompetitor> getCompetitorList() {
        return competitorList;
    }

    public void setSwimmingStroke(String swimmingStroke) {
        this.swimmingStroke = swimmingStroke;
    }

    public String getSwimmingStroke() {
        return swimmingStroke;
    }


    public void registerCompetitor(swimmerCompetitor competitor, int score) {
        competitorList.add(competitor);
        scores.addScore(competitor.getUniqueID(), score);
    }


    public scores getScores() {
        return scores;
    }

    @Override
    public String toString() {
        return "Competition Name: " + competitionName +
                "\nDate: " + date +
                "\nCategory: " + category +
                "\nSwimming Stroke: " + swimmingStroke +
                "\nCompetitor List: " + competitorList +
                "\n" + scores;
    }
}

