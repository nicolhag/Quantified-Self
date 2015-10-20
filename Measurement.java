import java.util.Date;

public class Measurement implements Measurable {

  String measurementType = null;
  Date date = null;
  Double value = null;
  Person person = null;

  public Measurement(String measurementType, Date date, Double value) {
    this.measurementType = measurementType;
    this.date = date;
    this.value = value;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public Double getValue() {
    return value;
  }

  public String getMeasurementType() {
    return measurementType;
  }

  public Date getDate() {
    return date;
  }

  public boolean hasDateRegistered(Date date) {
    return false;
  }

}
