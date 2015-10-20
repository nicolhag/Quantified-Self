import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class MeasurementContainer {

  //HashMap<Date, Double> measurements;
  List<Measurable> measurements;

  public MeasurementContainer() {
    this.measurements = new ArrayList<Measurable>();
  }

  public void addMeasurement(Measurement m) {
    this.measurements.add(m);
  }

  public boolean removeMeasurement(Measurement m) {
    if(this.containsMeasurement(m)) {
      this.measurements.remove(m);
      return true;
    }

    return false;
  }

  public void printAll() {
    for(Measurable m: measurements) {
      System.out.println(m.getMeasurementType() + ": " + m.getValue());
    }
  }

  // Returns all measurements
  public List getMeasurements() {
    return measurements;
  }

  // Creates a new int[] from a double[]
  public int[] doubleToIntArray(double[] values) {
    int[] intValues = new int[values.length];
    for(int i = 0; i < values.length; i++) {
      intValues[i] = (int) values[i];
    }
    return intValues;
  }

  public List getAllMeasurementsByDate(Date date) {
    new Date();
    return null;
  }

  public boolean containsMeasurement(Measurement m) {
    if(this.measurements.contains(m)) return true;
    return false;
  }
  /* Returns measurement mean */
  public double getMean(double[] m)  {
    double sum = 0;
    for (int i = 0; i < m.length; i++) {
      sum += m[i];
    }
    return sum / m.length;
  }

  /* Returns measurement mode */
  public double getMode(int[] a) {
    int maxValue = 0, maxCount = 0;

    for (int i = 0; i < a.length; ++i) {
      int count = 0;
      for (int j = 0; j < a.length; ++j) {
        if (a[j] == a[i]) ++count;
      }
      if (count > maxCount) {
        maxCount = count;
        maxValue = a[i];
      }
    }

    return maxValue;
  }

  /* Return measurement median */
  public double getMedian(double[] m) {
    int middle = m.length/2;
      if (m.length%2 == 1) {
        return m[middle];
      } else {
        return (m[middle-1] + m[middle]) / 2.0;
      }
  }

  double variance(double[] m){
      int n = m.length;
      double variance;
      double variancePartialSum = 0;
      for (int i = 0; i<m.length; i++){
          double partSum = m[i]-getMean(m);
          variancePartialSum += (partSum*partSum);
      }
      variance = (variancePartialSum/n);
      return variance;
  }

  double standardDeviation(double[] m){
      return Math.sqrt(variance(m));
  }

}
