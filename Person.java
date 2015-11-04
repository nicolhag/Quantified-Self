import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import java.io.File;
import java.io.FileNotFoundException;

public class Person{
    private String name;
    MeasurementContainer measurements = null;

    Person(String name){
        this.name = name;
        measurements = new MeasurementContainer();
    }

    public MeasurementContainer filterOut(String label, String day){
        MeasurementContainer filtered = new MeasurementContainer();
        for (Measurable m : measurements.getMeasurements()){
            // System.out.println("Sjekker på person: " + name + " om '"+ label + "' om m.getmes: '" + m.getMeasurementType() + "' med date:" + day + "mot:" + m.getDate().toString());
            if (m.getDate().toString().contains(day) && m.getMeasurementType().equalsIgnoreCase(label)){
                filtered.addMeasurement((Measurement) m);
            }
        }
        return filtered;
    }

    public MeasurementContainer filterOut(String label){
        MeasurementContainer filtered = new MeasurementContainer();
        for (Measurable m : measurements.getMeasurements()){
            // System.out.println("Sjekker på person: " + name + " om '"+ label + "' om m.getmes: '" + m.getMeasurementType() + "' med date:" + day + "mot:" + m.getDate().toString());
            if (m.getMeasurementType().equalsIgnoreCase(label)){
                filtered.addMeasurement((Measurement) m);
            }
        }
        return filtered;
    }
    public MeasurementContainer filterOutWhereStressLevelEquals(String label, int stressLvl){
        MeasurementContainer filtered = new MeasurementContainer();
        for (Measurable m : measurements.getMeasurements()){
            // System.out.println("Sjekker på person: " + name + " om '"+ label + "' om m.getmes: '" + m.getMeasurementType() + "' med date:" + day + "mot:" + m.getDate().toString());

            if (m.getMeasurementType().equalsIgnoreCase(label) && m.getStressLevel() == (double) stressLvl){
                filtered.addMeasurement((Measurement) m);
            }
        }
        return filtered;
    }

    public void printMeasureTowardsStressLevels(String label){
        final int MAX_STRESS_LEVEL = 5;
        for (int i = 1; i <= MAX_STRESS_LEVEL; i++){
            MeasurementContainer allFiltered = filterOutWhereStressLevelEquals(label, i);

            List<Measurable> m2 = allFiltered.getMeasurements();
            double[] values = measurements.getMeasurementValues(m2);
            System.out.println("Stressnivå " + i + ": " + allFiltered.getMean(values));
        }
    }

// Mon osv..
    public int getTheMostFrequentDayOnMeasure(String label){
        String[] validWeekdays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        double[] allmeasures = new double[7];

        for (int i = 0; i < validWeekdays.length; i++){

            MeasurementContainer collected = filterOut(label, validWeekdays[i]);
            List<Measurable> m2 = collected.getMeasurements();
            double[] values = measurements.getMeasurementValues(m2);

            allmeasures[i] = collected.getMean(values);
        }
        return findMaxIndex(allmeasures);
    }
    public int getTheLeastFrequentDayOnMeasure(String label){
        String[] validWeekdays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        double[] allmeasures = new double[7];

        for (int i = 0; i < validWeekdays.length; i++){

            MeasurementContainer collected = filterOut(label, validWeekdays[i]);
            List<Measurable> m2 = collected.getMeasurements();
            double[] values = measurements.getMeasurementValues(m2);

            allmeasures[i] = collected.getMean(values);
        }
        return findMinIndex(allmeasures);
    }

    public double getTheMostFrequentDayOnMeasureValue(String label){
        String[] validWeekdays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        double[] allmeasures = new double[7];

        for (int i = 0; i < validWeekdays.length; i++){

            MeasurementContainer collected = filterOut(label, validWeekdays[i]);
            List<Measurable> m2 = collected.getMeasurements();
            double[] values = measurements.getMeasurementValues(m2);

            allmeasures[i] = collected.getMean(values);
        }
        return allmeasures[findMaxIndex(allmeasures)];
    }

    public double getTheLeastFrequentDayOnMeasureValue(String label){
        String[] validWeekdays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        double[] allmeasures = new double[7];

        for (int i = 0; i < validWeekdays.length; i++){

            MeasurementContainer collected = filterOut(label, validWeekdays[i]);
            List<Measurable> m2 = collected.getMeasurements();
            double[] values = measurements.getMeasurementValues(m2);

            allmeasures[i] = collected.getMean(values);
        }
        return allmeasures[findMinIndex(allmeasures)];
    }

    public int findMaxIndex(double[] lst){
        int highestIndex = 0;
        for (int i = 0; i < lst.length; i++){
            if (lst[i] > lst[highestIndex]){
                highestIndex = i;
            }
        }
        return highestIndex;
    }

    public int findMinIndex(double[] lst){
        int lowestIndex = 0;
        for (int i = 0; i < lst.length; i++){
            if (lst[i] < lst[lowestIndex]){
                lowestIndex = i;
            }
        }
        return lowestIndex;
    }


    public String toString(){
        return name;
    }

    public ArrayList<Measurable> getMeasurements(){
        return (ArrayList<Measurable>) measurements.getMeasurements();
    }

    public void addMeasure(Measurement m){
        this.measurements.addMeasurement(m);
    }

    public void printAll(){
        System.out.println("\n\nName: " + name);
        this.measurements.printAll();
    }
}
