import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;

import java.io.File;
import java.io.FileNotFoundException;

class Calculator{
    public static void main(String[] args) throws Exception {
        Handler d = new Handler();
        d.readFromFile("sample_fysisk.csv","sample_psykisk.csv");
    }
}

class Handler{
    HashMap<String, Person> persons = new HashMap<String, Person>();
    MeasurementContainer measurements = new MeasurementContainer();

    public void readFromFile(String fysisk, String psykisk){
        Scanner fysisk_leser = null;
        Scanner psykisk_leser = null;

        try{
            fysisk_leser = new Scanner(new File(fysisk));
            psykisk_leser = new Scanner(new File(psykisk));
        } catch (FileNotFoundException e){
            System.out.println("\nFile not found!!");
            System.out.println("\n\tCSV Files: \t$ [ fysisk psykisk ]");
        }

        int nummer = 0;

        String fysiskHeader = fysisk_leser.nextLine(); // Remove first sentence of csv-file
        String psykiskHeader = psykisk_leser.nextLine(); // Remove first sentence of csv-file

        this.readPhysical(fysiskHeader, fysisk_leser);
        this.readPsychological(psykiskHeader, psykisk_leser);

    }

    private void readPhysical(String header, Scanner leser) {
      while (leser.hasNextLine()){
          String innlest = leser.nextLine();
          String[] parsed = innlest.split(",");
          String[] headers = header.split(",");

          //System.out.println("*********************");


          String timestamp = parsed[0];
          String name = parsed[1];
          String numberOfSteps = parsed[2];
          String hoursOfSleep = parsed[3];

          /*
          System.out.println("Name: " + name);
          System.out.println("Timestamp: " + timestamp);
          System.out.println("Number of steps: " + numberOfSteps);
          System.out.println("Hours of sleep: " + hoursOfSleep);

          System.out.println("*********************"); */


          Person p = null;
          Date d = new Date(timestamp);
          Measurement m = null;
          if (! persons.containsKey(name)){
              p = new Person(name);
              persons.put(name, p);

          } else {
              p = persons.get(name);
          }

        //  m = new Measurement(headers[0], d, Double.parseDouble(parsed[0]));
          //p.addMeasure(m);
          //m = new Measurement(headers[1], d, Double.parseDouble(parsed[1]));
          //p.addMeasure(m);
          m = new Measurement(headers[2], d, Double.parseDouble(parsed[2]));
          p.addMeasure(m);
          m = new Measurement(headers[3], d, Double.parseDouble(parsed[3]));
          p.addMeasure(m);

      }

      this.printAll();
    }

    private void readPsychological(String header, Scanner leser) {

      while (leser.hasNextLine()){
          String innlest = leser.nextLine();
          String[] parsed = innlest.split(",");
          String[] headers = header.split(",");

          //System.out.println("*********************");


          String timestamp = parsed[0];
          String name = parsed[1];
          String measurementNumber = parsed[2];
          String energy = parsed[3];
          String stress = parsed[4];
          String productivity = parsed[5];
          String meals = parsed[6];
          String alcoholUnits = parsed[7];
          String comments = parsed[8];

          /*System.out.println("Name: " + name);
          System.out.println("Timestamp: " + timestamp);
          System.out.println("MeasurementNumber: " + measurementNumber);
          System.out.println("Energy level: " + energy);

          System.out.println("*********************"); */

          Person p = null;
          Date d = new Date(timestamp);
          Measurement m = null;
          if (! persons.containsKey(name)){
              p = new Person(name);
              persons.put(name, p);

          } else {
              p = persons.get(name);
          }

          /*m = new Measurement(headers[0], d, Double.parseDouble(parsed[0]));
          p.addMeasure(m);
          m = new Measurement(headers[1], d, Double.parseDouble(parsed[1]));
          p.addMeasure(m); */
          //m = new Measurement(headers[2], d, Double.parseDouble(parsed[2]));
          //p.addMeasure(m);
          m = new Measurement(headers[3], d, Double.parseDouble(parsed[3]));
          p.addMeasure(m);
          m = new Measurement(headers[4], d, Double.parseDouble(parsed[4]));
          p.addMeasure(m);
          m = new Measurement(headers[5], d, Double.parseDouble(parsed[5]));
          p.addMeasure(m);
          m = new Measurement(headers[6], d, Double.parseDouble(parsed[6]));
          p.addMeasure(m);
          m = new Measurement(headers[7], d, Double.parseDouble(parsed[7]));
          p.addMeasure(m);
          m = new Measurement(headers[8], d, Double.parseDouble(parsed[8]));
          p.addMeasure(m);

      }

      this.printAll();
    }

    public void printAll() {
      //this.measurements.printAll();
      for(Person pers: persons.values()) {
        pers.printAll();
      }
    }
}

class Person{
    private String name;
    //private ArrayList<Double> hoursOfSleep = null;
    MeasurementContainer measurements = null;

    Person(String name){
        this.name = name;
        //hoursOfSleep = new ArrayList<Double>();
        measurements = new MeasurementContainer();
    }

    public void addMeasure(Measurement m){
        this.measurements.addMeasurement(m);
    }

    public void printAll(){
        System.out.println("\n\nName: " + name);
        this.measurements.printAll();
    }
}
