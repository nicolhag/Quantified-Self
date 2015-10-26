import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
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
