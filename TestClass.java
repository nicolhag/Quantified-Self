import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class TestClass {

  public static void main(String[] args) {
    MeasurementContainer m = new MeasurementContainer();
    double[] values = {2.0,2.0,3.0,4.0,5.0,6.43,2.7,5.7,9.0,1.2,10.56};
    m.addMeasurement(new Measurement(null, new Date(), 2.0));
    m.addMeasurement(new Measurement(null, new Date(), 7.0));
    m.addMeasurement(new Measurement(null, new Date(), 19.0));

    System.out.println("Mode: " + m.getMode(values));
    System.out.println("Mean: " + m.getMean(values));
    System.out.println("Median: " + m.getMedian(values));

    List<Measurement> all = m.getMeasurements();

    for(Measurement ms: all) {
      System.out.println(ms.getValue());
    }

  }

}
