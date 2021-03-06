
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
// Denne klassen brukes til å gjennomføre beregninger på containerne
public class Handler{
    private HashMap<String, Person> persons;
    private MeasurementContainer measurements;
    private String[] psykiskLabels, fysiskLabels;
    private final static String[] validWeekdays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    private final static String[] norwegianWeekdays = {"mandager", "tirsdager", "onsdager", "torsdager", "fredager", "lørdager", "søndager"};

    public Handler(){
        persons = new HashMap<>();
        measurements = new MeasurementContainer();
    }

    /*
    *
    *  METHODS COMBINING AND ANALYSING DATA FROM THE DATA COLLECTIONS
    *
    */

    public void printMenu(){
        System.out.println("VELKOMMEN TIL MYSTISK STATISTIKK OM OSS!!");
        System.out.println("0. Avslutt hele greia");
        System.out.println("1. Print ut alle mulige mål du kan skrive inn");
        System.out.println("2. Print ut gjennomsnittsverdier: Alle mål, For alle personer samlet");
        System.out.println("3. Print ut gjennomsnittsverdier: Alle mål, For enkeltpersoner");
        System.out.println("4. Print ut gjennomsnittsverdi: Ett mål, For enkeltpersoner");
        System.out.println("5. Print ut en gjennomsnittsverdi for ett mål på én Person på én ukedag");
        System.out.println("6. Print ut gjennomsnittsverdier for alle mål på én Person på én ukedag");
        System.out.println("7. Print ut dagen med HØYEST forekomst av ett mål på én Person");
        System.out.println("8. Print ut dagen med LAVEST forekomst av ett mål på én Person");
        System.out.println("9. Finne alle gjennomsnitt for en gitt måling sine ulike nivå, én Person");
    }

    public void ordrelokke(){
        String read = "1";
        while (!read.equals("0")){
            printMenu();
            System.out.println("Skriv inn ditt valg (1-9):");
            Scanner reader = new Scanner(System.in);
            read = reader.nextLine();

            switch(read){
                case "1": printLabels(); break;
                case "2":
                for ( String s : getAllValidLabels() ){
                    printGeneralAverage(s);
                } break;
                case "3":
                for ( String s : getAllValidLabels() ){
                    System.out.print("\nGJENNOMSNITTLIG " + s.toUpperCase() + ":");
                    printAllPersonAverage(s);
                } break;
                case "4":
                System.out.println("Hvem ønsker du å se på?");
                String navn = reader.nextLine();
                System.out.println("Hvilket mål ønsker du å se på?");
                for (String s : getAllValidLabels()){
                    System.out.println("- '" + s + "'");
                }
                String hvilket = reader.nextLine();
                printPersonAverage(navn, hvilket); break;
                case "5":
                System.out.println("Hvem ønsker du å se på?");
                navn = reader.nextLine();
                System.out.println("Hvilket mål ønsker du å se på?");
                for (String s : getAllValidLabels()){
                    System.out.println("- '" + s + "'");
                }
                hvilket = reader.nextLine();
                System.out.println("Hvilken ukedag ønsker du å se på?");
                for (String s : validWeekdays){
                    System.out.println("- '" + s + "'");
                }
                String ukedag = reader.nextLine();
                printPersonAverageOnWeekday(navn, hvilket, ukedag); break;
                case "6":
                System.out.println("Hvem ønsker du å se på?");
                navn = reader.nextLine();
                System.out.println("Hvilken ukedag ønsker du å se på?");
                for (String s : validWeekdays){
                    System.out.println("- '" + s + "'");
                }
                ukedag = reader.nextLine();
                for ( String measure : getAllValidLabels() ){
                    printPersonAverageOnWeekday(navn, measure, ukedag);
                }
                break;
                case "7":
                System.out.println("Hvem ønsker du å se på?");
                navn = reader.nextLine();
                System.out.println("Hvilket mål ønsker du å se på?");
                for (String s : getAllValidLabels()){
                    System.out.println("- '" + s + "'");
                }
                hvilket = reader.nextLine();
                printPersonMostFrequentDayOnMeasure(navn, hvilket);
                break;
                case "8":
                System.out.println("Hvem ønsker du å se på?");
                navn = reader.nextLine();
                System.out.println("Hvilket mål ønsker du å se på?");
                for (String s : getAllValidLabels()){
                    System.out.println("- '" + s + "'");
                }
                hvilket = reader.nextLine();
                printPersonLeastFrequentDayOnMeasure(navn, hvilket);
                break;
                case "9":
                System.out.println("Hvem ønsker du å se på?");
                navn = reader.nextLine();
                printAllMeasureTowardsStressLevel(navn);
                break;

            }
        }
    }

    public void printAllMeasureTowardsStressLevel(String name){
        String[] allLabels = getAllValidLabels();
        Person person = persons.get(name);

        for (String s : allLabels){
            System.out.println("Gjennomsnittsverdi av målingen " + s + ", ved stressnivåene: ");
            person.printMeasureTowardsStressLevels(s);
        }



    }

    public void printPersonMostFrequentDayOnMeasure(String name, String label){
        Person person = persons.get(name);
        int result = person.getTheMostFrequentDayOnMeasure(label);
        double valueOfHighest = person.getTheMostFrequentDayOnMeasureValue(label);
        System.out.printf("\n\n%s har høyest forekomst av %s : %s med verdien %s\n\n", person.toString(),label, norwegianWeekdays[result], valueOfHighest);
    }

    public void printPersonLeastFrequentDayOnMeasure(String name, String label){
        Person person = persons.get(name);
        int result = person.getTheLeastFrequentDayOnMeasure(label);
        double valueOfLowest = person.getTheLeastFrequentDayOnMeasureValue(label);
        System.out.printf("\n\n%s har lavest forekomst av %s : %s med verdien %s\n\n", person.toString(),label, norwegianWeekdays[result], valueOfLowest);
    }

    public void printPersonAverageOnWeekday(String nameOfPerson, String nameOfMeasurement, String day){
        Person person = persons.get(nameOfPerson);
        MeasurementContainer m = person.filterOut(nameOfMeasurement, day);
        List<Measurable> m2 = m.getMeasurements();
        double[] values = measurements.getMeasurementValues(m2);
        System.out.printf("%s%s%s på %s er: %.2f\n", nameOfPerson, " sitt gjennomsnitt av : ",nameOfMeasurement, getNorwegianWeekday(day), m.getMean(values));
    }

    public String getNorwegianWeekday(String eng){
        int i = 0;
        for (i = 0; i < validWeekdays.length; i++){
            if (validWeekdays[i].equals(eng)){
                return norwegianWeekdays[i];
            }
        }
        return null;
    }

    public String[] getAllValidWeekdays(){
        return validWeekdays;
    }

    public void printAllPersonAverage(String nameOfMeasurement) {
        System.out.println();

        for (Person p : persons.values()){
            printPersonAverage(p.toString(), nameOfMeasurement);
        }
    }

    public void printPersonAverage(String nameOfPerson, String nameOfMeasurement) {
        Person p = persons.get(nameOfPerson);
        List<Measurable> m2 = p.getMeasurements();
        List<Measurable> newList = new ArrayList<Measurable>();

        for(Measurable msr : (ArrayList<Measurable>) m2) {
          String s = msr.getMeasurementType();

          if(s.equals(nameOfMeasurement))
          {
            newList.add(msr);
          }
        }

        double[] values = this.measurements.getMeasurementValues(newList);
        double mean = this.measurements.getMean(values);

        System.out.printf("%s har på målingen %s : %.3f\n", nameOfPerson,nameOfMeasurement, mean);

    }

    public void printGeneralAverage(String field) {
      List<Measurable> m2 = new ArrayList<Measurable>();
      //if(psykiskLabels.contains(field) || fysiskLabels.contains(field)) {

        for(Measurable msr : (ArrayList<Measurable>) this.measurements.getMeasurements()) {
          String s = msr.getMeasurementType();
          if(s.equals(field))
          {
            m2.add(msr);
          }
        }
        double[] values = this.measurements.getMeasurementValues(m2);
        double mean = this.measurements.getMean(values);
        double mode = this.measurements.getMode(values);
        double median = this.measurements.getMedian(values);
        double stdDev = this.measurements.getStandardDeviation(values);
        double variance = this.measurements.getVariance(values);

        System.out.println("\nSjekker mål: " + field.toUpperCase());
        System.out.println("Antall datapunkter: " + values.length);
        System.out.println("Gjennomsnittsverdi: " + mean);
        System.out.println("Typetall: " + mode);
        System.out.println("Median: " + median);
        System.out.println("Standardavvik: " + stdDev);
        System.out.println("Varians: " + variance);
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

        readPhysical(fysiskHeader, fysisk_leser);
        readPsychological(psykiskHeader, psykisk_leser);

    }

    private void readPhysical(String header, Scanner leser) {
      String[] headers = header.split(",");

      while (leser.hasNextLine()){
          String innlest = leser.nextLine();
          String[] parsed = innlest.split(",");

          String timestamp = parsed[0];
          String personName = parsed[1];
          String personNameTag = headers[1];
          double numberOfSteps = Double.parseDouble(parsed[2]);
          String numberOfStepsTag = headers[2];
          double hoursOfSleep = Double.parseDouble(parsed[3]);
          String hoursOfSleepTag = headers[3];

          Person person = null;
          Date date = new Date(timestamp);
          if (! persons.containsKey(personName)){
              person = new Person(personName);
              persons.put(personName, person);
          } else {
              person = persons.get(personName);
          }

          // SUPERHACKZ FOR ADDING A DAY TO THE DATE READ
          Calendar cal = Calendar.getInstance();
          cal.setTime(date);
          cal.add(Calendar.DAY_OF_MONTH, 1);
          Date theDayAfter = cal.getTime();

          cal = Calendar.getInstance();
          cal.setTime(date);
          cal.add(Calendar.DAY_OF_MONTH, -1);
          Date theDayBefore = cal.getTime();

          Measurement newMeasurement = new Measurement(numberOfStepsTag, theDayBefore, numberOfSteps);
          person.addMeasure(newMeasurement);
          measurements.addMeasurement(newMeasurement);

          newMeasurement = new Measurement(hoursOfSleepTag, theDayBefore, hoursOfSleep);
          person.addMeasure(newMeasurement);
          measurements.addMeasurement(newMeasurement);

      }
    }

    public static String toddMMyy(Date day){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
        String date = formatter.format(day); return date;
    }

    private void readPsychological(String header, Scanner leser) {

      String[] headers = header.split(",");

      while (leser.hasNextLine()){
          String innlest = leser.nextLine();
          String[] parsed = innlest.split(",");

          String timestamp = parsed[0];
          String personName = parsed[1];

          int measurementNumber = Integer.parseInt(parsed[2].charAt(0) + "");
          String energy = parsed[3];
          String stress = parsed[4];
          String productivity = parsed[5];
          String socialOmgang = parsed[6];
          String meals = parsed[7];
          String alcoholUnits = parsed[8];
          String comments = null;
          // valgfritt å fylle inn "andre kommentarer"
          if (parsed.length == 10){
            comments = parsed[9];
          }


          Person person = null;
          Date date = new Date(timestamp);
          if (! persons.containsKey(personName)){
              person = new Person(personName);
              persons.put(personName, person);
          } else {
              person = persons.get(personName);
          }

          Calendar cal = Calendar.getInstance();
          cal.setTime(date);
          cal.add(Calendar.DAY_OF_MONTH, -1);
          Date theDayBefore = cal.getTime();

          for (int i = 3; i < 8; i++){
              Measurement newMeasurement = new Measurement(headers[i], date, Double.parseDouble(parsed[i]));
              newMeasurement.setMeasurementNumber(measurementNumber);
              newMeasurement.setStressLevel(Double.parseDouble(stress));
              person.addMeasure(newMeasurement);
              measurements.addMeasurement(newMeasurement);
              if (comments != null){
                  newMeasurement.setComment(comments);
              }
          }

          Measurement newMeasurement = new Measurement(headers[8], theDayBefore, Double.parseDouble(parsed[8]));
          newMeasurement.setMeasurementNumber(measurementNumber);
          newMeasurement.setStressLevel(Double.parseDouble(stress));
          person.addMeasure(newMeasurement);
          measurements.addMeasurement(newMeasurement);
      }
    }

    public void printLabels() {
      System.out.println("Labels: ");

      for(String s: fysiskLabels){
          System.out.println("> " + s);
      }
      for(String s: psykiskLabels){
          System.out.println("> " + s);
      }
      System.out.println();
    }

    public String[] getAllValidLabels(){
        String[] allLabels = new String[8];
        int i = 0;
        // 2
        for(int k = 2; k < 4; k++){
            String s = fysiskLabels[k];
            allLabels[i++] = s;
        }

        for(int k = 3; k < 9; k++){
            String s = psykiskLabels[k];
            allLabels[i++] = s;
        }
        return allLabels;
    }

    public void printAll() {
      for(Person pers: persons.values()) {
        pers.printAll();
      }
    }
}
