import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;

class Calculator{
    public static void main(String[] args) throws Exception {
        Handler d = new Handler();
        d.readFromFile("sample_fysisk.csv");
    }
}

class Handler{
    HashMap<String, Person> persons = new HashMap<String, Person>();

    public void readFromFile(String filnavn){
        Scanner leser = null;

        try{
            leser = new Scanner(new File(filnavn));
        } catch (FileNotFoundException e){
            System.out.println("File not found!!");
        }

        int nummer = 0;

        leser.nextLine(); // Remove first sentence of csv-file

        while (leser.hasNextLine()){
            String innlest = leser.nextLine();
            String[] parsed = innlest.split(",");

            System.out.println("*********************");
            System.out.println("*********************");

            String timestamp = parsed[0];
            String name = parsed[1];
            String numberOfSteps = parsed[2];
            String hoursOfSleep = parsed[3];

            System.out.println("Name: " + name);
            System.out.println("Timestamp: " + timestamp);
            System.out.println("Number of steps: " + numberOfSteps);
            System.out.println("Hours of sleep: " + hoursOfSleep);

            System.out.println("*********************");
            System.out.println("*********************");

            if (! persons.containsKey(name)){
                Person newP = new Person(name);
                persons.put(name, newP);
                Date d = new Date(timestamp);
                Measurement m = new Measurement(measureName, value, d);
                newP.addMeasure(m);
            } else {
                Person p = persons.get(name);
                p.addHoursOfSleep(Double.parseDouble(hoursOfSleep.trim()));
            }

        }

        for (Person p : persons.values()){
            p.printAllHours();
        }
    }
}

class Person{
    private String name;
    private ArrayList<Double> hoursOfSleep;
    Person(String name){
        this.name = name;
        hoursOfSleep = new ArrayList<Double>();
    }

    public void addMeasure(Measurement m){
        hoursOfSleep.add(hours);
    }

    public void printAllHours(){
        System.out.println(name + " sover: ");
        for (Double d : hoursOfSleep){
            System.out.println("Sov: " + d);
        }
    }
}
