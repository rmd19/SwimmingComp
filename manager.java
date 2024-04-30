import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class manager {
    private static officialGUI officialGUI;

    public static void main(String[] args) {
        competitorList competitorList = new competitorList();
        readDataFromFile("RunSwimComp.csv", competitorList);

        List<String> listAllCompetitorDetails = competitorList.getAllCompetitorDetails();
        String highestScore = competitorList.getHighestOverallScore();
        Map<Integer, Integer> frequencyReport = competitorList.getFrequencyReport();

        System.out.println("All competitor Details: ");
        for (String details : listAllCompetitorDetails) {
            System.out.println(details);
        }

        System.out.println("\nHighest overall score: ");
        System.out.println(highestScore);

        System.out.println("\nFrequency report: ");
        for (Map.Entry<Integer, Integer> entry : frequencyReport.entrySet()) {
            System.out.println("Score: " + entry.getKey() + " Frequency: " + entry.getValue());
        }

        System.out.println("\nCompetitor with Maximum Overall Score:");
        String maxScoreDetails = competitorList.getCompetitorWithMaximumOverallScore();
        if (maxScoreDetails != null) {
            System.out.println(maxScoreDetails);
        } else {
            System.out.println("No competitors available.");
        }

        // Print details of the competitor with the minimum overall score
        System.out.println("\nCompetitor with Minimum Overall Score:");
        String minScoreDetails = competitorList.getCompetitorWithMinimumOverallScore();
        if (minScoreDetails != null) {
            System.out.println(minScoreDetails);
        } else {
            System.out.println("No competitors available.");
        }


        competitorList.showCompetitorDetails();

        String reportPath = "FinalReport.txt";
        writeReportToFile(reportPath, listAllCompetitorDetails, highestScore, frequencyReport, maxScoreDetails, minScoreDetails);


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                officialGUI = new officialGUI(competitorList);
                officialGUI.setVisible(true);
            }
        });
    }

    private static void writeReportToFile(String reportPath, List<String> listAllCompetitorDetails, String highestScore,
                                          Map<Integer, Integer> frequencyReport, String maxScoreDetails, String minScoreDetails) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(reportPath))) {
            writer.println("All competitor Details: ");
            for (String details : listAllCompetitorDetails) {
                writer.println(details);
            }

            writer.println("\nHighest overall score: ");
            writer.println(highestScore);

            writer.println("\nFrequency report: ");
            for (Map.Entry<Integer, Integer> entry : frequencyReport.entrySet()) {
                writer.println("Score: " + entry.getKey() + " Frequency: " + entry.getValue());
            }

            writer.println("\nCompetitor with Maximum Overall Score:");
            writer.println(maxScoreDetails != null ? maxScoreDetails : "No competitors available.");

            writer.println("\nCompetitor with Minimum Overall Score:");
            writer.println(minScoreDetails != null ? minScoreDetails : "No competitors available.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readDataFromFile(String filename, competitorList competitorList) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int uniqueID = Integer.parseInt(data[0].trim());
                int age = Integer.parseInt(data[1].trim());
                String name = data[2].trim();
                String email = data[3].trim();
                String dob = data[4].trim();
                String Category = data[5].trim();
                String level = data[6].trim();
                int[] scores = Arrays.stream(data)
                        .skip(7) // Skip the first 7 elements
                        .mapToInt(Integer::parseInt)
                        .toArray();

                RMDCompetitor competitor = new RMDCompetitor(uniqueID, age, name, email, dob, level, Category, scores);
                competitorList.addCompetitor(competitor);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
