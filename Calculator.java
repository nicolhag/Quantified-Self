import java.util.Scanner;
import java.util.HashMap;
import java.io.File;

class Calculator{
    public static void main(String[] args) throws Exception {
        Handler d = new Handler();
        d.readFromFile("sample_fysisk.csv");
    }
}

class Handler{
    HashMap<String, Person> persons = new HashMap<String, Person>();

    private void readFromFile(String filnavn){
        Scanner leser = new Scanner(new File(filnavn));
        int nummer = 0;

        while (leser.hasNextLine()){
            String innlest = leser.nextLine();
            String[] parsed = innlest.split(",");

            System.out.println("*********************");
            System.out.println("*********************");

            String timestamp = parsed[0];
            String name = parsed[1];
            String numberOfSteps = parsed[2];
            String hoursOfSleep = parsed[3];

            System.out.println("Name: " + navn);
            System.out.println("Timestamp: " + timestamp);
            System.out.println("Number of steps: " + antallSkritt);
            System.out.println("Hours of sleep: " + antalltimerSovn);

            System.out.println("*********************");
            System.out.println("*********************");

        }
    }
}

class Person{
    String name;

}
