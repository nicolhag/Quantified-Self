import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Date;

import java.io.File;
import java.io.FileNotFoundException;

class Calculator{
    public static void main(String[] args) throws Exception {
        Handler d = new Handler();

        if (args.length == 0){
            Scanner term = new Scanner(System.in);
            System.out.println("Hva heter filen med fysiske data?");
            String fysisk = term.nextLine();

            System.out.println("Hva heter filen med psykiske data?");
            String psykisk = term.nextLine();
            d.readFromFile(fysisk, psykisk);
        } else if (args[0].trim().equals("default")){
            d.readFromFile("fysisk.csv", "psykisk.csv");
        } else {
            d.readFromFile(args[0],args[1]);
        }

        d.printLabels();

        d.printGeneralAverage("Antall skritt");
        d.printGeneralAverage("Antall timer søvn");
        d.printGeneralAverage("Energinivå");
        d.printGeneralAverage("Stressnivå");
        d.printGeneralAverage("Produktivitet");

    }
}

// Denne klassen brukes til å gjennomføre beregninger på containerne
class Handler{
    HashMap<String, Person> persons = new HashMap<String, Person>();
    MeasurementContainer measurements = new MeasurementContainer();
    String[] psykiskLabels = null;
    String[] fysiskLabels = null;

    /*
    *
    *  METHODS COMBINING AND ANALYSING DATA FROM THE DATA COLLECTIONS
    *
    */

    public void printGeneralAverage(String field) {
      List<Measurable> m2 = new ArrayList<Measurable>();
      //if(psykiskLabels.contains(field) || fysiskLabels.contains(field)) {

        for(Measurable msr : (ArrayList<Measurable>) this.measurements.getMeasurements()) {
          String s = msr.getMeasurementType();

          if(s.equals(field))
          {
            //System.out.println(msr.getMeasurementType() +  ": " + msr.getValue());
            m2.add(msr);
          }
        }

        double[] values = this.measurements.getMeasurementValues(m2);
        double mean = this.measurements.getMean(values);
        double mode = this.measurements.getMode(values);
        double median = this.measurements.getMedian(values);

        System.out.println("\nTOTAL AVERAGE: " + field);
        System.out.println("Number of data points: " + values.length);
        System.out.println("Mean: " + mean);
        System.out.println("Mode: " + mode);
        System.out.println("Median: " + median);

        m2 = null;
      //}
    }

    /*
    *
    *  METHODS FOR READING THE TWO CSV-FILES
    *  GENERAL HELP METHODS
    *
    */
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

        fysiskLabels = fysiskHeader.split(",");
        psykiskLabels = psykiskHeader.split(",");

        this.readPhysical(fysiskHeader, fysisk_leser);
        this.readPsychological(psykiskHeader, psykisk_leser);

    }

    public void printLabels() {
      System.out.println("Labels: ");
      for(String s: fysiskLabels) System.out.println("> " + s);
      for(String s: psykiskLabels) System.out.println("> " + s);
      System.out.println("");
    }

    private void readPhysical(String header, Scanner leser) {
      String[] headers = header.split(",");

      while (leser.hasNextLine()){
          String innlest = leser.nextLine();
          String[] parsed = innlest.split(",");

          //System.out.println("*********************");


          String timestamp = parsed[0];
          String name = parsed[1];
          String numberOfSteps = parsed[2];
          String hoursOfSleep = parsed[3];

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
          measurements.addMeasurement(m);

          m = new Measurement(headers[3], d, Double.parseDouble(parsed[3]));
          p.addMeasure(m);
          measurements.addMeasurement(m);

      }
    }

    private void readPsychological(String header, Scanner leser) {

      String[] headers = header.split(",");

      while (leser.hasNextLine()){
          String innlest = leser.nextLine();
          String[] parsed = innlest.split(",");

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
          measurements.addMeasurement(m);

          m = new Measurement(headers[4], d, Double.parseDouble(parsed[4]));
          p.addMeasure(m);
          measurements.addMeasurement(m);

          m = new Measurement(headers[5], d, Double.parseDouble(parsed[5]));
          p.addMeasure(m);
          measurements.addMeasurement(m);

          m = new Measurement(headers[6], d, Double.parseDouble(parsed[6]));
          p.addMeasure(m);
          measurements.addMeasurement(m);

          m = new Measurement(headers[7], d, Double.parseDouble(parsed[7]));
          p.addMeasure(m);
          measurements.addMeasurement(m);

          m = new Measurement(headers[8], d, Double.parseDouble(parsed[8]));
          p.addMeasure(m);
          measurements.addMeasurement(m);

      }
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
