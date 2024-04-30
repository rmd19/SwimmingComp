import java.util.Arrays;
public class RMDCompetitor {
    private int uniqueID;
    private int age;
    private String name;
    private String email;
    private String dob;
    private String category;
    private String level;
    private int[] scores;

    public RMDCompetitor(int uniqueID, int age, String name, String email, String dob, String category, String level, int[] scores) {
        this.uniqueID = uniqueID;
        this.age = age;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.category = category;
        this.level = level;
        this.scores = scores;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDOB() {
        return dob;
    }

    public void setDOB(String dob) {
        this.dob = dob;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int[] getScoreArray() {
        return scores;
    }


    public void setScoreArray(int[] scores){
        this.scores = scores;
    }

    public double getOverallScore(){
        double sum = 0;
        for (int score : scores) {
            sum += score;
        }
        double average = sum / scores.length;
        return Math.round(average * 10.0) / 10.0;
    };

    public String getFullDetails() {
        String scoresString = Arrays.toString(this.scores).replaceAll("[//[//]]", "");
        String details = "Competitor number: " + uniqueID + "\nName: " + name + "\nGender Category: " + category + "\nAge: " + age + "\nDate of birth: " + dob + "\nLevel: " + level + "\nEmail: "+ email + ".\n" +
                name + " is a " + category + " this person is aged  " + age + " and recieved these scores " + scoresString +  ". \nThis person is a Level " + level + " swimmer." +  "\nThis gives him an overall score of " + getOverallScore() + ".\n";
        return details;
    }

    public String getShortDetails() {
        // Extracting initials from the name
        String[] nameParts = name.split(" ");
        String initials = "";
        for (String part : nameParts) {
            if (!part.isEmpty()) {
                initials += part.charAt(0);
            }
        }

        return "CN " + uniqueID + " (" + initials + ") has overall score " + getOverallScore() + ".";
    }

    @Override
    public String toString() {
        return "Competitor " + uniqueID +
                "\nName: " + name +
                "\nAge: " + age +
                "\nEmail: " + email +
                "\nDOB: " + dob +
                "\nCategory: " + category +
                "\nLevel: " + level +
                "\nScores: " + Arrays.toString(scores);
    }


}

