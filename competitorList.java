import java.util.*;

public class competitorList {
    private List<RMDCompetitor> competitors;

    public competitorList() {
        competitors = new ArrayList<>();
    }

    public void addCompetitor(RMDCompetitor competitor) {
        competitors.add(competitor);
    }

    public List<RMDCompetitor> getAllCompetitors() {
        return new ArrayList<>(competitors);
    }

    // Other methods...

    // This method returns a list of strings containing details of all competitors
    public List<String> getAllCompetitorDetails() {
        List<String> details = new ArrayList<>();
        for (RMDCompetitor competitor : competitors) {
            details.add(competitor.getFullDetails());
        }
        return details;
    }

    public String getHighestOverallScore(){
        RMDCompetitor highestCompScore = competitors.get(0);
        for(RMDCompetitor competitor: competitors){
            if(competitor.getOverallScore() > highestCompScore.getOverallScore()){
                highestCompScore = competitor;
            }
        }
        return highestCompScore.getFullDetails();
    }

    public Map<Integer, Integer> getFrequencyReport(){
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (RMDCompetitor competitor : competitors){
            int[] scores = competitor.getScoreArray();
            for (int score: scores){
                frequencyMap.put(score, frequencyMap.getOrDefault(score, 0) + 1);
            }
        }
        return frequencyMap;
    }
    public RMDCompetitor getCompetitorByNumber(int competitorNumber) {
        for (RMDCompetitor competitor : competitors) {
            if (competitor.getUniqueID() == competitorNumber) {
                return competitor;
            }
        }
        return null;
    }

    public String getCompetitorWithMaximumOverallScore() {
        if (competitors.isEmpty()) {
            return null;
        }
        RMDCompetitor maxScoreCompetitor = Collections.max(competitors, Comparator.comparing(RMDCompetitor::getOverallScore));
        return maxScoreCompetitor.getFullDetails();
    }

    public String getCompetitorWithMinimumOverallScore() {
        if (competitors.isEmpty()) {
            return null;
        }
        RMDCompetitor minScoreCompetitor = Collections.min(competitors, Comparator.comparing(RMDCompetitor::getOverallScore));
        return minScoreCompetitor.getFullDetails();
    }

    public void showCompetitorDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter (Competitor) UniqueID: ");
        int uniqueID = scanner.nextInt();

        for (RMDCompetitor competitor : getAllCompetitors()) {
            if (competitor.getUniqueID() == uniqueID) {
                System.out.println(competitor.getShortDetails());
                return;
            }
        }

        System.out.println("Competitor not found.");
        scanner.close();
    }
    public void removeCompetitor(RMDCompetitor competitor) {
        Iterator<RMDCompetitor> iterator = competitors.iterator();
        while (iterator.hasNext()) {
            RMDCompetitor c = iterator.next();
            if (c.equals(competitor)) {
                iterator.remove();
                break;
            }
        }
    }}







