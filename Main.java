import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
    public class Main {
        //Testing all the methods
        public static void main(String[] args) {


            // Ann = new swimmerCompetitor(100, 23, "Ann Sthen", "ann@gmail.com", "01/01/2002", "Female", "1");
            // George = new swimmerCompetitor(101, 31, "George Beth Shon", "george@gmail.com", "02/01/2002", "Male", "1");

            //.out.println(George.getFullDetails());
            //.out.println(Ann.getFullDetails());

            //.out.println(Ann.getShortDetails());
            //.out.println(George.getShortDetails());



            int[] ann2Scores = {4, 5, 3};
            int[] george2Scores = {3, 2 ,1};

            RMDCompetitor Ann2 = new RMDCompetitor(100, 23, "Ann Sthen", "ann@gmail.com", "01/01/2002", "Female", "1", ann2Scores);
            System.out.println(Ann2.getFullDetails());
            RMDCompetitor George2 = new RMDCompetitor(100, 23, "George Beth Shon", "george@gmail.com", "09/08/1992", "Male", "1", george2Scores);
            System.out.println(George2.getFullDetails());


            System.out.println(Ann2.getShortDetails());
            System.out.println(George2.getShortDetails());

            System.out.println(Ann2.getShortDetails());




        }
}

