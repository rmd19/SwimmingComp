import java.util.ArrayList;
import java.util.List;

public class competitionList {
    private List<swimmerCompetition> listOfCompetitions;

    public competitionList() {
        listOfCompetitions = new ArrayList<>();
    }

    public void addCompetition(swimmerCompetition competition) {
        listOfCompetitions.add(competition);
    }

    public List<swimmerCompetition> getListOfCompetitions() {
        return listOfCompetitions;
    }

    public void removeCompetition(swimmerCompetition competition) {
        listOfCompetitions.remove(competition);
    }

    public void listCompetitions() {
        System.out.println("List of Competitions:");
        for (swimmerCompetition competition : listOfCompetitions) {
            System.out.println(competition);
            System.out.println("------------------------");
        }
    }



    public static void main(String[] args) {
        // Example Usage
        competitionList competitionList = new competitionList();

        // Creating Competitions
        swimmerCompetition competition1 = new swimmerCompetition();
        competition1.setCompetitionName("Competition 1");
        competition1.setDate("2023-01-01");
        competition1.setCategory("Female");
        competition1.setSwimmingStroke("Front Crawl");

        swimmerCompetition competition2 = new swimmerCompetition();
        competition2.setCompetitionName("Competition 2");
        competition2.setDate("2023-02-01");
        competition2.setCategory("Male");
        competition2.setSwimmingStroke("Back Crawl");

        swimmerCompetition competition3 = new swimmerCompetition();
        competition3.setCompetitionName("Competition 3");
        competition3.setDate("2023-02-01");
        competition3.setCategory("Female");
        competition3.setSwimmingStroke("Breast stroke");

        // Adding Competitions to the List
        competitionList.addCompetition(competition1);
        competitionList.addCompetition(competition2);
        competitionList.addCompetition(competition3);

        // Listing Competitions
        competitionList.listCompetitions();
    }
}
