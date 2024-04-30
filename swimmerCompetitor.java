import java.util.Arrays;

public abstract class swimmerCompetitor {
    private int uniqueID;
    private int age;
    private String name;
    private String email;
    private String dob;
    private String category;
    private String level;

    public int[] scores;

    // Constructor
    public swimmerCompetitor(int uniqueID, int age, String name, String email, String dob, String category, String level, int [] scores) {
        this.uniqueID = uniqueID;
        this.age = age;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.category = category;
        this.level = level;
        this.scores = scores;
    }

    // Getter and Setter methods
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

    public abstract double getOverallScore();
    public String getFullDetails() {
        String details = "Competitor number: " + uniqueID + ", Name: " + name + ", Gender Category: " + category +
                ", Age: " + age + ", Date of birth: " + dob + ", Level: " + level + ", Email: " + email + "\n" +
                name + " is a " + category + " aged " + age + " and has an overall score of " + getOverallScore() + ".";
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






    // Connecting with the Competition class


    // toString method
    @Override
    public String toString() {
        return "Competitor " + uniqueID +
                "\nName: " + name +
                "\nAge: " + age +
                "\nEmail: " + email +
                "\nDOB: " + dob +
                "\nCategory: " + category +
                "\nLevel: " + level;
    }
}
